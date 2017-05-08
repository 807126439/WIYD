 $(function() {
  

	 	if($("#fileManager").length !== 0){
			 var fileManager = createFileManager('#fileManager',
		      {
		        uploader:false,
		        ajaxUrl:path+"/userFileController/getMyFileData.do", //文件数据请求url
		        ajaxData:{},
		        base:null, //请求一页显示的文件数量，默认null为不分页，加载全部
		        renameBtn:false, 
		        downloadBtn:{ //设为false移除下载按钮
		          url:path+"/userFileController/downMyUserFile.do?dataId=" 
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
       
       open_window(path+"/userFileController/previewMyUserFile.do?dataId="+flieData.id);
      }