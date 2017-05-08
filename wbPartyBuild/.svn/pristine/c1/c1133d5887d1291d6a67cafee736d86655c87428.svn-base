var mf = null ;
var uploader = null;


$(function(){
	
			thumbnailWidth = 64,
			thumbnailHeight = 64,
			$list = $("#fileList"),
			$btn = $("#btn-star"),
			state = "pending";			
		
			 uploader = WebUploader.create({
				auto: false,
				swf: path+'/plug-in/h-ui/lib/webuploader/0.1.5/Uploader.swf',
			
				// 文件接收服务端。
				server: $("#form-ads").attr("action"),
			
				// 选择文件的按钮。可选。
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.
				pick: '#filePicker',
			    formData:{},
			
				// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
				resize: false,
				fileNumLimit:1,
				fileSizeLimit:2*1024*1024,//限制大小2m
				fileSingleSizeLimit:2*1024*1024,
				compress:false,
				/*compress :{
				    width: 320,
				    height: 320,
				
				    // 图片质量，只有type为`image/jpeg`的时候才有效。
				    quality: 90,
				
				    // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
				    allowMagnify: false,
				
				    // 是否允许裁剪。
				    crop: false,
				
				    // 是否保留头部meta信息。
				    preserveHeaders: true,
				
				    // 如果发现压缩后文件大小比原来还大，则使用原来图片
				    // 此属性可能会影响图片自动纠正功能
				    noCompressIfLarger: false,
				
				    // 单位字节，如果图片大小小于此值，不会采用压缩。
				    compressSize: 0
				},*/
				// 只允许选择图片文件。
				accept: {
					title: 'Images',
					extensions: 'gif,jpg,jpeg,bmp,png',
					mimeTypes: 'image/*'
				}
			});
			
			uploader.on( 'fileQueued', function( file ) {
				$list.html("");//清空图片
				mf = file;
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
					$percent = $li.find('.progress-box .sr-only');
			
				// 避免重复创建
				if ( !$percent.length ) {
					$percent = $('<div class="progress-box"><span class="progress-bar radius"><span class="sr-only" style="width:0%"></span></span></div>').appendTo( $li ).find('.sr-only');
				}
				$li.find(".state").text("上传中");
				$percent.css( 'width', percentage * 100 + '%' );
			});
			
			// 文件上传成功，给item添加成功class, 用样式标记上传成功。
			uploader.on( 'uploadSuccess', function( file ) {
				$( '#'+file.id ).addClass('upload-state-success').find(".state").text("已上传");
			});
			
			// 文件上传失败，显示上传出错。
			uploader.on( 'uploadError', function( file ) {
				$( '#'+file.id ).addClass('upload-state-error').find(".state").text("上传出错");
			});
			
			// 完成上传完了，成功或者失败，先删除进度条。
			uploader.on( 'uploadComplete', function( file ) {
				$( '#'+file.id ).find('.progress-box').fadeOut();
			
				 setTimeout(function(){
					parent.goSearch();
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);	      
								      					      
			      },1000);				      					      
							
			});
			
			
			uploader.on( 'uploadAccept', function( file, response ) {
					if(response.status == "y" || response.status == "Y"){	
						layer.msg(response.info,{icon: 1,time:1000}); 
						 return true; 					
					}else{
					   layer.msg(response.info,{icon: 2,time:2000}); 
					    return false;
					}
			   
			      
			    
			});
			
			uploader.on('all', function (type) {
		        if (type === 'startUpload') {
		            state = 'uploading';
		        } else if (type === 'stopUpload') {
		            state = 'paused';
		        } else if (type === 'uploadFinished') {
		            state = 'done';
		        }
		
		        if (state === 'uploading') {
		            $btn.text('暂停上传');
		        } else {
		            $btn.text('开始上传');
		        }
		    });
		
		
				
			$("#btn-cancel").on('click', function () {
				if(mf!=null){
					 removeFile(mf);  
				}
		              
		
		    });
			
			 function removeFile( file ) {
			 		uploader.removeFile(file,true);
		            var $li = $('#'+file.id);
		
		            $li.off().find('.file-panel').off().end().remove();
		            mf =null;
		      }	
			 
			
});			 
			 
				