$(function(){
		$('.skin-minimal input').iCheck({
			checkboxClass: 'icheckbox-blue',
			radioClass: 'iradio-blue',
			increaseArea: '20%'
		});
		
		$("#form-user-edit").Validform({
			tiptype:2,
			ajaxPost:true,//ajax方式提交表单数据
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
	
		
	});


	











