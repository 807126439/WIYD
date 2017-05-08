$(function(){
	   $("#dep-id").val(parent.document.getElementById('parId').value);
	    var parName = parent.document.getElementById('parName').value;
	    if(parName == null ||parName == ""){
	    	parName = "无";
	    }
		$("#dep-name").val(parName);
	
	
	
		$('.skin-minimal input').iCheck({
			checkboxClass: 'icheckbox-blue',
			radioClass: 'iradio-blue',
			increaseArea: '20%'
		});
		
		$("#form-jdr").Validform({
			tiptype:2,
			ajaxPost:true,//ajax方式提交表单数据
			beforeSubmit:function(curform){
			  
			     return true;
			   	
			},
			callback:function(data){
				if(data.status == "y" || data.status == "Y"){				
					      setTimeout(function(){				    		
				    	  
					    	  
					      	parent.goSearchJdr();
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
							      					      
					      },500); 
					
				}
				
			}
		});
	
		
	});


	











