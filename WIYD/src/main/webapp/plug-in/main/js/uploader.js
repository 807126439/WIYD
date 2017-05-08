//返回一个uploader对象(webuploader)。
//参数一：可选，uploader容器的选择器或jq对象，缺省值为#uploader。
//参数二:可选，创建webuploader的扩展参数
//组件状态state:pending、paused、uploading、done，存储在uploader容器的data('state')里
function createUploader(params,elem,option) {
		
  var $wrap = $('#uploader');
  if (typeof elem === "string") {
    $wrap=$(elem);
  }else if (elem instanceof jQuery) {
    $wrap=elem;
  }if (typeof elem==='object'&&!option) {
    option=elem;
  }

  var $list = $wrap.find('.thelist'),
    $btn = $wrap.find('.ctlBtn'),
    $picker = $wrap.find('.picker'),
    $attachment=$wrap.find('.attachmentlist'),
    state = 'pending',
    uploader,
    defaultOption={
      resize: false, // 不压缩image
      swf: path+'/plug-in/h-ui/lib/webuploader/0.1.5/Uploader.swf',
      server: path+'/file/upload.do',
      formData:params,

      // 选择文件的按钮。可选。
      // 内部根据当前运行是创建，可能是input元素，也可能是flash.
      pick: $picker
    };

  uploader = WebUploader.create($.extend({},defaultOption,option));

  // 当有文件添加进来的时候
  uploader.on('beforeFileQueued', function(file) {
    var rs = true;
    $attachment.find('span:first-child').each(function() {
      if (file.name === $(this).text()) {
        rs = false;
        layer.msg('已有同名的上传文件');
        return;
      }
    });
    return rs;
  });

  // 当有文件添加进来的时候
  uploader.on('fileQueued', function(file) {
    var $remove=$('<a href="javascript:void(0)" class="remove">撤消</a>');
    $('<div class="'+ file.id+'" item">' +
      '<span class="info">' + file.name + '</span>' +
      '(<span class="state">等待上传...</span>)' +
      '</div>').append($remove).appendTo($list);
    $remove.click(function() {
      removeFile(file);
    });
  });

  // 文件上传过程中创建进度条实时显示。
  uploader.on('uploadProgress', function(file, percentage) {
    var $li = $wrap.find('.' + file.id),
      $percent = $li.find('.progress .progress-bar');

    // 避免重复创建
    if (!$percent.length) {
      $percent = $('<div class="progress progress-striped active">' +
        '<div class="progress-bar" role="progressbar" style="width: 0%">' +
        '</div>' +
        '</div>').appendTo($li).find('.progress-bar');
    }

    $li.find('.state').text('上传中');

    $percent.css('width', percentage * 100 + '%');
  });

  uploader.on('uploadSuccess', function(file) {
    $wrap.find('.' + file.id).find('.state').text('已上传');
    $wrap.find('.' + file.id).find('.remove').remove();
  });

  uploader.on('uploadError', function(file) {
    $wrap.find('.' + file.id).find('.state').text('上传出错');
    if ($wrap.find('.' + file.id).find('.retry').length === 0&&$btn.length!==0) {
      var $retry = $('<a href="javascript:void(0)" class="retry">重新上传</a>');
      $wrap.find('.' + file.id).find('.state').after($retry);
      //重新上传失败的文件
      $retry.on('click', function() {
        $(this).unbind().remove();
        uploader.retry(file);
      })
    }
  });

  uploader.on('uploadComplete', function(file) {
    $wrap.find('.' + file.id).find('.progress').fadeOut();
  });

  uploader.on('all', function(type) {
    if (type === 'startUpload') {
      state = 'uploading';
    } else if (type === 'stopUpload') {
      state = 'paused';
    } else if (type === 'uploadFinished') {
      state = 'done';
    }
    $wrap.data('state',state);

    if (state === 'uploading') {
      $btn.text('暂停上传');
    } else {
      $btn.text('开始上传');
    }
  });

  $btn.on('click', function() {
    if (state === 'uploading') {
      uploader.stop();
    } else {
      uploader.upload();
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

      default:
        txt = code;
    }
    layer.msg(txt);
  };

  //已经成功上传文件的处理
  uploader.on('uploadSuccess', function(file,response) {
	
	  
    if (response.backVal) {
        $wrap.find('.'+file.id).remove();
        $attachment.append($('<span data-id="'+response.backVal+'" class="attachment">\
                                        <span>'+file.name+'</span>\
                                        <span class="ctrls">\
                                        <a href="javascript:void(0)" class="del">删除</a>\
                                        </span>\
                                      </span>'));
    }
  });

  //下载已经上传的文件
  $attachment.on('click', '.download', function() {
    var fileId = $(this).parent().data('id');

  })

  //删除已经上传的文件
  $attachment.on('click', '.del', function() {
    var $file = $(this).parent().parent();
    $.ajax({
      type: "POST",
      url: path+"/file/delete.do",
      data: {
    	  ucode: $("#ucode").val(),
    	  kw: $file.data('id')
      },
      async: false,
      error: function(request) {
        layer.msg("删除失败");
      },
      success: function(data) {
        if (data.status == "y" || data.status == "Y") {
          $file.remove();
        }
      }
    })
  })

  function removeFile(file) {
    uploader.removeFile(file);
    $wrap.find('.'+file.id).remove();
  }
  return uploader;
}
