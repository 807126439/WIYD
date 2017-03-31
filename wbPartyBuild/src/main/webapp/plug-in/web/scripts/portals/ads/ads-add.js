$(function(){
			
				$('.skin-minimal input').iCheck({
					checkboxClass: 'icheckbox-blue',
					radioClass: 'iradio-blue',
					increaseArea: '20%'
				});
					
				$("#form-ads").Validform({
					tiptype:2,
					ajaxPost:true,//ajax方式提交表单数据
					beforeSubmit:function(curform){	
										
														
						if(typeof(mf)!= "undefined" && mf!=null){
											   
						   var title = $("#ct-title").val();
						   var sortNum = $("#ct-sortNum").val();
						   var typeId = $("#select-typeId").val();
						   var linkUrl =  $("#ct-linkUrl").val();
						   var addDate =  $("#add-date").val();
						   
						   var isCompress = 0;
						   if($("#isCompress").prop("checked")){
							   isCompress =  $("#isCompress").val();
						   }							
															
							if (state === 'uploading') {
				           		 uploader.stop();
				        	 } else {
				        		 
				        		 
				        	   uploader.option('formData', {
				  					"title":title,
				  					"sortNum":sortNum,
				  					"typeId":typeId,
				  					"linkUrl":linkUrl,
				  					"createTime":addDate,
				  					"isCompress":isCompress
				  					
								});
				        	    				        	 
				         	   uploader.upload();
				        	}
				        	
						  	 return false;	
						}
						
						 				 
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
			
			
	});

	











