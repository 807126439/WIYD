

   
   
/**
 * 初始化上传插件
 * @param id     (控件id)
 * @param params (参数)
 * return mf
 */   
function initUploader(id,serverUrl,params){
	 if ($("#"+id).length===0) {
	        return;
	  }


        var $list = $("#"+id).parents(".uploader-div").find(".thelist"),	//上传列表
        $btn = $("#"+id).siblings(".ctlBtn"),				//开始上传按钮
        $cancel_btn = $("#"+id).siblings(".btn-cancel"),	//取消按钮	       
        state = 'pending';
        $("#"+id).attr("state",'pending'),
		thumbnailWidth = 96,
		thumbnailHeight = 96,mf =null;
	   	
        var uploader = WebUploader.create({
	   	    auto: false,
	   		swf: path+'/plug-in/h-ui/lib/webuploader/0.1.5/Uploader.swf',	
	
	   		// 文件接收服务端。
	   		server: serverUrl,		
	   		// 选择文件的按钮。可选
	   		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		   	 pick: {
	             id: '#'+id,
	             multiple : false
	        },
	   		multiple:false,
	   		formData:params,
	   		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	   		resize: false,
	   		fileNumLimit:1,
			fileSizeLimit:30*1024*1024,//限制大小500m
			fileSingleSizeLimit:30*1024*1024,
	   		compress:false,
	   		accept: {
				title: 'Images',
				extensions: 'gif,jpg,jpeg,bmp,png',
				mimeTypes: 'image/*'
			}
	    });
		

	    // 当有文件添加进来的时候
	    uploader.on( 'fileQueued', function( file ) {	    	        
	        
	        $list.html("");//清空图片
			mf = file;
			$btn.show();
		 	$cancel_btn.show();
			var $li = $(
				'<div id="' + file.id + '" class="item">' +
					'<div class="pic-box"><img></div>'+
					'<div class="info">' + file.name + '</div>' +
					'<p class="state">等待上传...</p>'+
				'</div>'
			),
			$img = $li.find('img');
			$list.append( $li );
	
			// 创建缩略图
			// 如果为非图片文件，可以不用调用此方法。
			// thumbnailWidth x thumbnailHeight 为 100 x 100
			uploader.makeThumb( file, function( error, src ) {
				if ( error ) {
					$img.replaceWith('<span>不能预览</span>');
					return;
				}
		
				$img.attr( 'src', src );
			}, thumbnailWidth, thumbnailHeight );
	        
	        
	    });
	    




	    // 文件上传过程中创建进度条实时显示。
	    uploader.on( 'uploadProgress', function( file, percentage ) {
	        var $li = $( '#'+file.id ),
	            $percent = $li.find('.progress .progress-bar');

	        // 避免重复创建
	        if ( !$percent.length ) {
	            $percent = $('<div class="progress progress-striped active">' +
	              '<div class="progress-bar" role="progressbar" style="width: 0%">' +
	              '</div>' +
	            '</div>').appendTo( $li ).find('.progress-bar');
	        }

	        $li.find('p.state').text('上传中');

	        $percent.css( 'width', percentage * 100 + '%' );
	    });

	    uploader.on( 'uploadSuccess', function( file ) {
	      
	 
	        $( '#'+file.id ).find('p.state').text('已上传');
	       
	    });

	    uploader.on( 'uploadError', function( file ) {
	        $( '#'+file.id ).find('p.state').text('上传出错');
	    });

	    uploader.on( 'uploadComplete', function( file ) {
	        $( '#'+file.id ).find('.progress').fadeOut();
	    });

	    
		uploader.on( 'uploadAccept', function( file, response ) {
			if(response.status == "y" || response.status == "Y"){
			    $btn.hide();  
			    $cancel_btn.hide();
			    $("#"+id).attr("state",'success');
				layer.msg(response.info,{icon: 1,time:1000}); 
				return true; 					
			}else{
			   layer.msg(response.info,{icon: 2,time:3000}); 
			    return false;
			}    
	 });
	    
	    
	    uploader.on( 'all', function( type ) {
	        if ( type === 'startUpload' ) {
	            state = 'uploading';
	        } else if ( type === 'stopUpload' ) {
	            state = 'paused';
	        } else if ( type === 'uploadFinished' ) {
	            state = 'done';
	        }

	        if ( state === 'uploading' ) {
	            $btn.text('暂停上传');
	        } else {
	            $btn.text('开始上传');
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
	    
	    $cancel_btn.on('click', function () {
		   	if(mf!=null){
		   	    uploader.removeFile(mf,true);
		        var $li = $('#'+mf.id);		               
		        $li.off().find('.file-panel').off().end().remove();
		        mf =null;
		   	}
		   	
		   	 $btn.hide();
		     $cancel_btn.hide();
	    });
	   	
	   

	    $btn.on( 'click', function() {	    	
	        if ( state === 'uploading' ) {
	            uploader.stop();
	        } else {
	            uploader.upload();
	        }
	    });
	    
	    return mf;
	 
	 }	