$(function(){
				
	
		$('.skin-minimal input').iCheck({
			checkboxClass: 'icheckbox-blue',
			radioClass: 'iradio-blue',
			increaseArea: '20%'
		});
		
		$("#form-column-edit").Validform({
			tiptype:2,
			ajaxPost:true,//ajax方式提交表单数据
			beforeSubmit:function(curform){
								
				
				if($("#checkbox-is-index").prop("checked")){
					if($("#index-num").val().length<1){
						 layer.msg('请填写首页排序号!',{icon: 0,time:1500});
						 return false;
					}
				}
				
				if(parseInt($("#select-show-typeId").val())>0){
					
					if(!$("#checkbox-is-index").prop("checked")){
						 layer.msg('请勾选置于首页！',{icon: 2,time:2000});
						 return false;
					}
				}
				
			     return true;
			   	
			},
			callback:function(data){
				if(data.status == "y" || data.status == "Y"){				
					   setTimeout(function(){
						    var isRefresh = $("#checkbox-refresh-parent").prop("checked");
						    if(isRefresh){
						    	parent.location.replace(parent.location.href);
						  
						    }else{						    	
								     
								var index = parent.layer.getFrameIndex(window.name);
								parent.layer.close(index);										      					      
								     
						    }
							
							
							      					      
					      },500); 
					
				}
				
			}
			
			
			
		});
	
	
			
		$("#form-column-move").Validform({
			tiptype:2,
			ajaxPost:true,//ajax方式提交表单数据
			beforeSubmit:function(curform){			
				
			     return true;
			   	
			},
			callback:function(data){
				if(data.status == "y" || data.status == "Y"){				
					   setTimeout(function(){						 
				
						    	parent.location.replace(parent.location.href);
				      },500); 
					
				}
				
			}
			
			
			
		});
		
	});


	











