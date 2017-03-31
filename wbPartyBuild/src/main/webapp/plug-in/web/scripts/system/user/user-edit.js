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
					      	parent.goSearch(false);
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
							      					      
					      },500); 
					
				}
				
			}
		});
	
		
	});


	











