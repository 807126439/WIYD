 $(function() {
  

	    $("#form_audit_reply").Validform({
	      tiptype: 8,
	      tipSweep: false, //为true时提示信息将只会在表单提交时触发显示
	      ajaxPost: true,
	      beforeSubmit:function(curform){
			return true;			  	 			  	
		  },
	      callback:function(data){				
				if(data.status == "y" || data.status == "Y"){	
				 	setTimeout(function(){
						 window.location.replace(window.location.href);								      					      
				    },500); 		
								
				}				
		  }
	    });
  	
	    if(!$("#fileManager").length === 0){
	    	var fileManager = createFileManager('#fileManager',
	    		      {
	    		        uploader:true,
	    		        ajaxUrl:path+"/userFileController/getMyFileData.do", //文件数据请求url
	    		        base:null, //请求一页显示的文件数量，默认null为不分页，加载全部
	    		        renameBtn:false, 
	    		        downloadBtn:{ //设为false移除下载按钮
	    		          url:path+"/userFileController/downMyUserFile.do?dataId=" 
	    		        }, 
	    		        deleteBtn:{
	    		          url:path+"/userFileController/deleteMyFile.do"
	    		        },
	    		        viewFile:{
	    		          doc:previewFile,
	    		          xls:previewFile,
	    		          ppt:previewFile,
	    		          txt:previewFile,
	    		          pdf:previewFile
	    		        },
	    		        search:true,
	    		        sortMenu:[true,false,false],
	    		        display:1
	    		      },
	    		      {
	    		        server:path+"/userFileController/uploadMyFile.do"
	    		     
	    		      }
	    		      );
	    }
		 

    
  });
 
 
 	function previewFile(flieData) {
     
     open_window(path+"/userFileController/previewMyUserFile.do?dataId="+flieData.id);
    }
  