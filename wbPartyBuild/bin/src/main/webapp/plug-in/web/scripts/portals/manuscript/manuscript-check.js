var pageType = $("#pageType").val();
$(function(){		
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
					
	$("#form-manuscript-check").Validform({
		tiptype:2,
		ajaxPost:true,//ajax方式提交表单数据
		callback:function(data){				
			if(data.status == "y" || data.status == "Y"){			
				      setTimeout(function(){
				    	  if(pageType == 1){
				    		  parent.goSearch();
				    	  }if(pageType == 2){
				    		  parent.loadData();
				    	  }				    	
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
						      					      
				      },500); 
				
			}
		}
	});
			
			
	});
