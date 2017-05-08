// WebUploader实例
jQuery(function() {
  var $ = jQuery;
  $(".form_photography").Validform({
    tiptype: 4,
    tipSweep: true,
    ajaxPost: true,
    btnSubmit: '#submitData',
    beforeSubmit: function() {
      if (mf != null) {
        var activityId = $("#activityId").val();
        var author = $("#author").val();
        var description = $("#description").val();
        var title = $("#title").val();
        if (state === 'uploading') {
          uploader.stop();
        } else {
          uploader.option('formData', {
            "activityId": activityId,
            "author": author,
            "description": description,
            "title": title
          });
          uploader.upload();
        }
      } else {
        layer.msg("请选择图片！");
      }
      return false;
    }
  });

  var uploader,
    $wrap = $('#uploader'),
    $queue = $('.filelist').length === 0 ? $('<ul class="filelist"></ul>')
    .appendTo($wrap.find('.queueList')) : $('.filelist'), // 图片容器
    $fileDelete = $wrap.find('#fileDelete'), // 触发删除的按钮
    $fileAdd = $wrap.find('#filePicker'), // 触发添加的按钮

    ratio = window.devicePixelRatio || 1, // 优化retina, 在retina下这个值是2
    // 缩略图大小
    thumbnailWidth = 100 * ratio,
    thumbnailHeight = 100 * ratio,

    // 可能有pedding, ready, uploading, confirm, done.
    state = 'pedding',
    mf = null //当前文件;

  if (!WebUploader.Uploader.support()) {
    alert('Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
    throw new Error('WebUploader does not support the browser you are using.');
  }

  // 实例化
  uploader = WebUploader.create({
    multiple: false,
    pick: { id: "#filePicker2" },
    accept: {
      title: 'Images',
      extensions: 'gif,jpg,jpeg,bmp,png',
      mimeTypes: 'image/*'
    },
    server: path+'/manuscriptController/addManuscript.do',
    swf: path+'/plug-in/h-ui/lib/webuploader/0.1.5/Uploader.swf',
    
    disableGlobalDnd: true, //禁用拖拽
    sendAsBinary: false, //是否已二进制的流的方式发送文件
    chunked: true, //分片处理大文件上传
    compress: { //设为null则不压缩
      quality: 100 // 图片质量，只有type为`image/jpeg`的时候才有效。
    }
  });

  $fileAdd.on('click', function() {
    $('#filePicker2 label').trigger('click');
  });
  $fileDelete.on('click', function() {
    removeFile(mf);
  });

  uploader.onFileQueued = function(file) {
    mf = file;
    addFile(file);
  };
  uploader.on('all', function(type) {
    if (type === 'startUpload') {
      state = 'uploading';
    } else if (type === 'stopUpload') {
      state = 'paused';
    } else if (type === 'uploadFinished') {
      state = 'done';
    }

  });
  uploader.on( 'uploadProgress', function( file, percentage ) {
      var $li = $( '#'+file.id ),
      $percent = $li.find('.progress .span');
      $percent.css( 'width', percentage * 100 + '%' );
    });

  // 当有文件添加进来时执行，负责view的创建
  function addFile(file) {
    if ($queue.children().length > 2) {
      $queue.children().eq(0).remove();
    }
    var $li = $('<li id="' + file.id + '"><div><div class="wr">' +
        '<p class="title">' + file.name + '</p>' +
        '<p class="imgWrap"></p>' + 
        '<p class="progress"><span></span></p>' +
        '</div></div></li>'),
    $progress = $li.find('p.progress span'),
    $wrap = $li.find('p.imgWrap');
    $li.prependTo($queue);

    var $info = $('<p class="error"></p>');

    var showError = function(code) {
      switch (code) {
        case 'exceed_size':
          text = '文件大小超出';
          break;

        case 'interrupt':
          text = '上传暂停';
          break;

        default:
          text = '上传失败';
          break;
      }

      $info.text(text).appendTo($li.find('.wr'));
    };

    if (file.getStatus() === 'invalid') {
      showError(file.statusText);
    } else {
      $wrap.text('预览中');
      uploader.makeThumb(file, function(error, src) {
        if (error) {
          $wrap.text('不能预览');
          return;
        }
        var img = $('<img src="' + src + '">');
        $wrap.empty().append(img);
      }, thumbnailWidth, thumbnailHeight);
    }

    file.on('statuschange', function(cur, prev) {
       if ( prev === 'progress' ) {
                $progress.hide().width(0);
            } else if ( prev === 'queued' ) {
                $li.off();
            }
      if (cur === 'error' || cur === 'invalid') {
        showError(file.statusText);
      } else if (cur === 'interrupt') {
        showError('interrupt');
      } else if (cur === 'queued') {
      } else if (cur === 'progress') {
        $info.remove();
        $progress.css('display', 'block');
      } else if (cur === 'complete') {
      }
      $li.removeClass('state-' + prev).addClass('state-' + cur);
    });
  }
  
	// 完成上传完了，成功或者失败，先删除进度条。
	uploader.on( 'uploadComplete', function( file ) {
		  layer.msg('已上传')
		  setTimeout(function(){
		      window.history.go(-1); 	    
		  },1000);					      					      
	});
  

  function removeFile(file) {
    uploader.removeFile(file, true);
    var $li = $('#' + file.id);

    $li.off().find('.file-panel').off().end().remove();
    mf = null;
  }
  
});
