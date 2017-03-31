$(function(){
	    $("#dep-par").val(parent.document.getElementById('parId').value);
	    var parName = parent.document.getElementById('parName').value;
	    if(parName == null ||parName == ""){
	    	parName = "无";
	    	$("#showIsMain").show();
	    }
		$("#par-name").val(parName);
	
	
	
		$('.skin-minimal input').iCheck({
			checkboxClass: 'icheckbox-blue',
			radioClass: 'iradio-blue',
			increaseArea: '20%'
		});
		
		$("#form-department").Validform({
			tiptype:2,
			ajaxPost:true,//ajax方式提交表单数据
			beforeSubmit:function(curform){
			  
			     return true;
			   	
			},
			callback:function(data){
				if(data.status == "y" || data.status == "Y"){				
					      setTimeout(function(){
				    		var addId = data.backVal;
							parent.addNodeItem(addId,$("#dep-name").val());  
				    	  
					    	  
					      	parent.goSearch();
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
							      					      
					      },500); 
					
				}
				
			}
		});
	
		
	});


	











