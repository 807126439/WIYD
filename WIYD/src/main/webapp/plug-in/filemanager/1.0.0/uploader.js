//返回一个uploader对象(webuploader对象)。
//参数一：必选，uploader容器的选择器、jq或Dom对象。
//参数二:可选，创建webuploader的创建参数
function createUploader(elem,opt) {
  if (!elem) return;
  var $wrap = $(elem),
    $queueContainer = $wrap.find('.queuelist'),// 文件队列容器
    $queue = $('<ul class="filelist"></ul>').appendTo($queueContainer),// 文件队列
    $dndarea = $wrap.find('.dndarea'),
    $statusBar = $wrap.find('.statusbar'), // 状态栏，包括进度和控制按钮
    $info = $statusBar.find('.info'),// 文件总体选择信息。
    $filePicker = $wrap.find('.filepicker'),// 添加按钮
    $filePicker2 = $wrap.find('.filepicker2'),// 添加按钮2
    $upload = $wrap.find('.btn_upload'),// 上传按钮
    $placeHolder = $wrap.find('.placeholder'),// 没选择文件之前的内容。
    $progress = $statusBar.find('.progress').hide(),// 总体进度条

    fileCount = 0,// 添加的文件数量
    fileSize = 0,// 添加的文件总大小
    ratio = window.devicePixelRatio || 1,// 优化retina, 在retina下这个值是2
    thumbnailWidth = 110 * ratio,// 缩略图大小
    thumbnailHeight = 110 * ratio,
    state = 'pedding',// 可能有pedding, ready, uploading, confirm, done.
    percentages = {},// 所有文件的进度信息，key为file id

    supportTransition = (function() {
      var s = document.createElement('p').style,
        r = 'transition' in s ||
        'WebkitTransition' in s ||
        'MozTransition' in s ||
        'msTransition' in s ||
        'OTransition' in s;
      s = null;
      return r;
    })(),

    // WebUploader实例
    uploader,

    // WebUploader创建默认配置
    defaultOpt={
      pick: {
        id: $filePicker2,
        label: '继续添加'
      },
      dnd:$dndarea,
      accept: {
        title: '',
        extensions: 'gif,jpg,jpeg,bmp,png,doc,docx,ppt,pptx,xls,xlsx,wps,dps,et,pdf,txt,rar,zip,3gp,mp4,avi,mpv2,mpg,mpe,mpeg,rmvb,rm,asf,divx,wmv,mkv,vob,flv,ogm,qt',
        mimeTypes: '*'
      },
      // swf文件路径
      swf: path+'/plug-in/h-ui/lib/webuploader/0.1.5/Uploader.swf',
      disableGlobalDnd: true,
      chunked: true,
      server: path+'/file/upload.do',
      fileNumLimit: 1,
      fileSizeLimit: 50 * 1024 * 1024,
      fileSingleSizeLimit: 10 * 1024 * 1024 
    };

  if (!WebUploader.Uploader.support()) {
    alert('Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
    throw new Error('WebUploader does not support the browser you are using.');
  }

  // 实例化
  uploader = WebUploader.create($.extend({},defaultOpt,opt));
  
  // 再次添加“继续添加件”的按钮，解决多个有拖拽功能上传插件的bug
  uploader.addButton({
    id: $filePicker2,
    label: '继续添加'
  }); 
  
  // 添加“添加文件”的按钮，
  uploader.addButton({
    id: $filePicker,
    label: '点击选择文件'
  });

  // 当有文件添加进来时执行，负责view的创建
  function addFile(file) {
    var $li = $('<li class="' + file.id + '">' +
        '<p class="title">' + file.name + '</p>' +
        '<p class="imgWrap"></p>' +
        '<p class="progress"><span></span></p>' +
        '</li>'),

      $btns = $('<div class="file-panel">' +
        '<span class="cancel">删除</span>' +
        '<span class="rotateRight">向右旋转</span>' +
        '<span class="rotateLeft">向左旋转</span></div>').appendTo($li),
      $prgress = $li.find('p.progress span'),
      $wrap = $li.find('p.imgWrap'),
      $info = $('<p class="error"></p>'),

      showError = function(code) {
        switch (code) {
          case 'exceed_size':
            text = '文件大小超出';
            break;

          case 'interrupt':
            text = '上传暂停';
            break;

          default:
            text = '上传失败，请重试';
            break;
        }

        $info.text(text).appendTo($li);
      };

    if (file.getStatus() === 'invalid') {
      showError(file.statusText);
    } else {
      // @todo lazyload
      $wrap.text('预览中');
      uploader.makeThumb(file, function(error, src) {
        if (error) {
          $wrap.text('不能预览');
          $wrap.empty().append('<div class="fileicon fileicon-' + matchType(file.ext) + '"></div>');
          return;
        }

        var img = $('<img src="' + src + '">');
        $wrap.empty().append(img);
      }, thumbnailWidth, thumbnailHeight);

      percentages[file.id] = [file.size, 0];
      file.rotation = 0;
    }

    file.on('statuschange', function(cur, prev) {
      if (prev === 'progress') {
        $prgress.hide().width(0);
      } else if (prev === 'queued') {
        $li.off('mouseenter mouseleave');
        $btns.remove();
      }

      // 成功
      if (cur === 'error' || cur === 'invalid') {
        showError(file.statusText);
        percentages[file.id][1] = 1;
      } else if (cur === 'interrupt') {
        showError('interrupt');
      } else if (cur === 'queued') {
        percentages[file.id][1] = 0;
      } else if (cur === 'progress') {
        $info.remove();
        $prgress.css('display', 'block');
      } else if (cur === 'complete') {
        $li.append('<span class="success"></span>');
      }

      $li.removeClass('state-' + prev).addClass('state-' + cur);
    });

    $li.on('mouseenter', function() {
      $btns.stop().animate({ height: 30 });
    });

    $li.on('mouseleave', function() {
      $btns.stop().animate({ height: 0 });
    });

    $btns.on('click', 'span', function() {
      var index = $(this).index(),
        deg;

      switch (index) {
        case 0:
          uploader.removeFile(file);
          return;

        case 1:
          file.rotation += 90;
          break;

        case 2:
          file.rotation -= 90;
          break;
      }

      if (supportTransition) {
        deg = 'rotate(' + file.rotation + 'deg)';
        $wrap.css({
          '-webkit-transform': deg,
          '-mos-transform': deg,
          '-o-transform': deg,
          'transform': deg
        });
      } else {
        $wrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (~~((file.rotation / 90) % 4 + 4) % 4) + ')');
      }


    });

    $li.appendTo($queue);
  }

  // 负责view的销毁
  function removeFile(file) {
    var $li = $wrap.find('.' + file.id);

    delete percentages[file.id];
    updateTotalProgress();
    $li.off().find('.file-panel').off().end().remove();
  }

  function updateTotalProgress() {
    var loaded = 0,
      total = 0,
      spans = $progress.children(),
      percent;

    $.each(percentages, function(k, v) {
      total += v[0];
      loaded += v[0] * v[1];
    });

    percent = total ? loaded / total : 0;

    spans.eq(0).text(Math.round(percent * 100) + '%');
    spans.eq(1).css('width', Math.round(percent * 100) + '%');
    updateStatus();
  }

  function updateStatus() {
    var text = '',
      stats;

    if (state === 'ready') {
      text = '选中' + fileCount + '个文件，共' +
        WebUploader.formatSize(fileSize) + '。';
    } else if (state === 'confirm') {
      stats = uploader.getStats();
      if (stats.uploadFailNum) {
        text = '已成功上传' + stats.successNum + '个文件，' +
          stats.uploadFailNum + '个上传失败，点击<a class="retry" href="javascript:void(0)">重新上传</a>'
      }

    } else {
      stats = uploader.getStats();
      text = '共' + fileCount + '个文件（' +
        WebUploader.formatSize(fileSize) +
        '），已上传' + stats.successNum + '个';

      if (stats.uploadFailNum) {
        text += '，失败' + stats.uploadFailNum + '个';
      }
    }

    $info.html(text);
  }

  function setState(val) {
    var file, stats;

    if (val === state) {
      return;
    }

    $upload.removeClass('state-' + state);
    $upload.addClass('state-' + val);
    state = val;

    switch (state) {
      case 'pedding':
        $placeHolder.removeClass('element-invisible');
        $queue.parent().removeClass('filled');
        $queue.hide();
        $statusBar.addClass('element-invisible');
        uploader.refresh();
        break;

      case 'ready':
        $placeHolder.addClass('element-invisible');
        $filePicker2.removeClass('element-invisible');
        $upload.removeClass('disabled');
        $queue.parent().addClass('filled');
        $queue.show();
        $statusBar.removeClass('element-invisible');
        uploader.refresh();
        break;

      case 'uploading':
        $filePicker2.addClass('element-invisible');
        $progress.show();
        $upload.text('暂停上传').removeClass('disabled');
        break;

      case 'paused':
        $progress.show();
        $upload.text('继续上传');
        break;

      case 'confirm':
        $progress.hide();
        $filePicker2.removeClass('element-invisible');
        $upload.text('开始上传').addClass('disabled');

        stats = uploader.getStats();
        if (stats.successNum && !stats.uploadFailNum) {
          setState('finish');
          return;
        }
        break;
      case 'finish':
        stats = uploader.getStats();
        if (stats.successNum) {
          if (typeof layer!=='undefined') {
            layer.msg('上传成功');
          }else{
            alert('上传成功');
          }
        } else {
          // 没有成功的，重设
          state = 'done';
          location.reload();
        }
        break;
    }

    updateStatus();
  }

  uploader.onUploadProgress = function(file, percentage) {
    var $li = $wrap.find('.' + file.id),
      $percent = $li.find('.progress span');

    $percent.css('width', percentage * 100 + '%');
    percentages[file.id][1] = percentage;
    updateTotalProgress();
  };

  uploader.onFileQueued = function(file) {
    fileCount++;
    fileSize += file.size;

    if (fileCount === 1) {
      $placeHolder.addClass('element-invisible');
      $statusBar.show();
    }

    addFile(file);
    setState('ready');
    updateTotalProgress();
  };

  uploader.onFileDequeued = function(file) {
    fileCount--;
    fileSize -= file.size;

    if (!fileCount) {
      setState('pedding');
    }

    removeFile(file);
    updateTotalProgress();

  };

  uploader.on('all', function(type) {
    var stats;
    switch (type) {
      case 'uploadFinished':
        setState('confirm');
        break;

      case 'startUpload':
        setState('uploading');
        break;

      case 'stopUpload':
        setState('paused');
        break;

    }
  });

  
  uploader.onError = function(code) {
    var txt = '';
    switch (code) {
      case 'F_EXCEED_SIZE':
        txt = '文件太小超过限制';
        break;

      case 'Q_EXCEED_SIZE_LIMIT':
        txt = '总太小超过限制';
        break;

      case 'F_DUPLICATE':
        txt = '已有同名的上传文件';
        break;

      case 'Q_EXCEED_NUM_LIMIT':
        txt = '数量超出限制';
        break;

      case 'Q_TYPE_DENIED':
        txt = '不支持的文件类型';
        break;

      default:
        txt = code;
    }
    if (typeof layer!=='undefined') {
      layer.msg(txt);
    }else{
      alert(txt);
    }
  };

  $upload.on('click', function() {
    if ($(this).hasClass('disabled')) {
      return false;
    }

    if (state === 'ready') {
      uploader.upload();
    } else if (state === 'paused') {
      uploader.upload();
    } else if (state === 'uploading') {
      uploader.stop();
    }
  });

  $info.on('click', '.retry', function() {
    uploader.retry();
  });

  
  //根据后缀名匹配文件图标
  function matchType(ext) {
    switch (ext) {
      case 'png':
      case 'gif':
      case 'jpg':
      case 'jpeg':
      case 'bmp':
        return 'img';
      case 'doc':
      case 'docx':
      case 'wps':
        return 'doc';
      case 'ppt':
      case 'pptx':
      case 'dps':
        return 'ppt';
      case 'xls':
      case 'xlsx':
      case 'et':
        return 'xls';
      case 'pdf':
        return 'pdf';
      case 'txt':
        return 'txt';
      case 'rar':
      case 'zip':
        return 'rar';
      case '3gp':
      case 'mp4':
      case 'avi':
      case 'mpv2':
      case 'mpg':
      case 'mpe':
      case 'mpeg':
      case 'rmvb':
      case 'rm':
      case 'asf':
      case 'divx':
      case 'wmv':
      case 'mkv':
      case 'vob':
      case 'flv':
      case 'ogm':
      case 'qt':
        return 'video';
      default:
        return 'other';
    }
  }

  $upload.addClass('state-' + state);
  updateTotalProgress();

  return uploader;
}
