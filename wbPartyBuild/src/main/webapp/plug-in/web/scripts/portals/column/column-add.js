$(function(){
		$('.skin-minimal input').iCheck({
			checkboxClass: 'icheckbox-blue',
			radioClass: 'iradio-blue',
			increaseArea: '20%'
		});
		
		$("#form-column-add").Validform({
			tiptype:2,
			ajaxPost:true,//ajax方式提交表单数据
			beforeSubmit:function(curform){
				
				if($("#checkbox-is-index").prop("checked")){
					if($("#index-num").val().length<1){
						 layer.msg('请填写首页排序号!',{icon: 0,time:1500});
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
	
		
		/*$("#select-typeId").click(function(){
			if(parseInt($(this).val()) == 4){
				
				if($("#input-linkUrl").is(":hidden")){
					$("#input-linkUrl").show();
				}
				
			}else{
				if(!$("#input-linkUrl").is(":hidden")){
					$("#input-linkUrl").hide();
				}
			}
			
			
		});*/
		
		
	});


	











