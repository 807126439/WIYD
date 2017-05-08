 $(function() {
  

	 	if($("#fileManager").length !== 0){
			 var fileManager = createFileManager('#fileManager',
		      {
		        uploader:false,
		        ajaxUrl:path+"/userFileController/getUserFileData.do", //文件数据请求url
		        ajaxData:{"userId":$("#uid").val()},
		        base:null, //请求一页显示的文件数量，默认null为不分页，加载全部
		        renameBtn:false, 
		        downloadBtn:{ //设为false移除下载按钮
		          url:path+"/userFileController/downUserFile.do?dataId=" 
		        }, 
		        deleteBtn:false,
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
		      }
		      );
	 	}
	
	    
	  });
	
	
	 function previewFile(flieData) {
       
       open_window(path+"/userFileController/previewUserFile.do?dataId="+flieData.id);
      }