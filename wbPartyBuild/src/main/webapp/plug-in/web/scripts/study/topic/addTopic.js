$(function(){
	$("#form-topic-add").Validform({
		tiptype:1,
		ajaxPost:true,//ajax方式提交表单数据
		beforeSubmit:function(curform){		
		     return true;
		},
		
		callback:function(data){	
				if(data.status == "y" || data.status == "Y"){	
					 setTimeout(function(){
					      	parent.goSearch();
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
							      					      
					      },800); 
					
				}
			}
		});
	
	
	
});