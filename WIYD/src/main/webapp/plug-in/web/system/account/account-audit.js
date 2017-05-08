  $(function(){
					
		$("#account-audit-form").Validform({
			tiptype:8,
			ajaxPost:true,
			beforeSubmit:function(curform){					
			  
				return true;
			},
		
			callback:function(data){	
				if(data.status == "y" || data.status == "Y"){	
					 setTimeout(function(){
				      	$("#Validform_msg").hide();				      
				     },1000); 					
				}
			}
		});
		
	

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
	        viewFile:{
		          doc:previewFile,
		          xls:previewFile,
		          ppt:previewFile,
		          txt:previewFile,
		          pdf:previewFile
		      },
	        deleteBtn:false,
	        search:true,
	        sortMenu:[true,false,false],
	        display:1
	      }
	      );
		
		
	  
		
	});	
	
	
  	function previewFile(flieData) {
      
      open_window(path+"/userFileController/previewUserFile.do?dataId="+flieData.id);
     }
  
	
	function sumbitAudit(type){
		$("#auditType").val(type);
		sumbitForm("account-audit-form");
	}
	
	