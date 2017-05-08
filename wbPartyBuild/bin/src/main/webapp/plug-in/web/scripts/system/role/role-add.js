$(function(){	
	
		$('.skin-minimal input').iCheck({
			checkboxClass: 'icheckbox-blue',
			radioClass: 'iradio-blue',
			increaseArea: '20%'
		});
		
		
		$("#form-role-add").Validform({
			tiptype:1,
			ajaxPost:true,//ajax方式提交表单数据
			beforeSubmit:function(curform){//切换name属性
				initCheckAuths();
	
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
				
					
				$("#check_req").html("");
				
			}
		});
	});
