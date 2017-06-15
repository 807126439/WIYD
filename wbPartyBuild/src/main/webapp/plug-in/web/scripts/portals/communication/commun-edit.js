$(function(){
	
    var ue = UE.getEditor('editor');
			
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
					
	$("#form-commun-edit").Validform({
		tiptype:2,
		ajaxPost:true,//ajax方式提交表单数据
		beforeSubmit:function(curform){							
			return true;		  	 			  	
		},
		callback:function(data){				
			if(data.status == "y" || data.status == "Y"){			
				      setTimeout(function(){
				    	parent.goSearch();
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
						      					      
				      },500); 
				
			}
		}
	});
	
	
	var videoFile = null;	
	$list = $("#thelist");
	var uploader = WebUploader.create({

    // swf文件路径
	swf: path+'/plug-in/h-ui/lib/webuploader/0.1.5/Uploader.swf',

    // 文件接收服务端。
    server:path+"/communController/uploadVideo.do",

    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick: '#picker',
    
    formData:{	    	
    	ucode:$("#uuid").val()
    },

    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
    resize: false
});

	uploader.on( 'fileQueued', function( file ) {
		$("#phoneVideoName").hide();
		videoFile = file;
		$list.html("");
		$list.append( '<div id="' + file.id + '" class="item">' +
		        '<h4 class="info">' + file.name + '</h4>' +
		        '<p class="state">等待上传...</p>' +
		    '</div>' );
		
		$("#picker").hide();
		$("#ctlBtn").show();
		$("#cencleBtn").show();
		
	});

	//文件上传过程中创建进度条实时显示。
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
	
	
	$("#ctlBtn").on('click', function () {
		uploader.upload();
		$("#ctlBtn").hide();
		$("#cencleBtn").hide();
		$("#picker").hide();	
	});
	
	
	$("#cencleBtn").on('click', function () {
		$("#phoneVideoName").show();
		$("#picker").show();
		$("#ctlBtn").hide();
		$("#cencleBtn").hide();
		removeFile(videoFile)
	});
	
	function removeFile(file) {
			uploader.removeFile(file,true);
	    var $li = $('#'+file.id);		        
	   
	    $li.off().find('.file-panel').off().end().remove();
	    videoFile =null;
	}	
		
		
	
	
});