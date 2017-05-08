$(function(){
			
				$('.skin-minimal input').iCheck({
					checkboxClass: 'icheckbox-blue',
					radioClass: 'iradio-blue',
					increaseArea: '20%'
				});
					
				$("#form-content").Validform({
					tiptype:2,
					ajaxPost:true,//ajax方式提交表单数据
					beforeSubmit:function(curform){	
										
						if(!UE.getEditor('editor').hasContents()){
							layer.msg("请编辑文章内容！",{icon: 1,time:1500}); 						
							return false;
						} 	
								
						if(typeof(mf)!= "undefined" && mf!=null){
						
						   var ct_id =  $("#ct-id").val();	
						   var titlePrefix = $("#ct-title-prefix").val();
						   var title = $("#ct-title").val();
						   var sortNum = $("#ct-sortNum").val();
						   var author = $("#ct-author").val();
						   var source =  $("#ct-source").val();
						   var addDate =  $("#add-date").val();
						   var actionWay =  $("#action-way").val();
						   var indexFlag = 0;
						   if($("#indexFlag").prop("checked")){
							   indexFlag =  $("#indexFlag").val();
						   }
						   var content = UE.getEditor('editor').getContent();
															
							if (state === 'uploading') {
				           		 uploader.stop();
				        	 } else {
				   	  
				        	    uploader.option('formData', {
				        	    	"ctId":ct_id,
				        	    	"titlePrefix":titlePrefix,
				  					"title":title,
				  					"sortNum":sortNum,
				  					"author":author,
				  					"source":source,
				  					"indexFlag":indexFlag,
				  					"createTime":addDate,
				  					"action":actionWay,
				  					"content":content
				  					
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
							      	
									var index = parent.layer.getFrameIndex(window.name);
									parent.layer.close(index);
									      					      
							      },500); 
							
						}
					}
				});
			
			/*富文本编辑器*/
		    var ue = UE.getEditor('editor');
	});

	function choiceOrg(){
		var id=$("#ct-id").val();
		var url = path+"/contentController.do?seeOrg&contentId="+id;
		layer_show("选择可查看部门，不选则所有部门可查看",url,400,600);
	}











