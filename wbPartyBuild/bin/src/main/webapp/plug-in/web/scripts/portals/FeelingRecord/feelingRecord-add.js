$(function(){
	
	$("#form-commun-add").Validform({
		tiptype:2,
		ajaxPost:true,//ajax方式提交表单数据
		callback:function(data){

			
			if(data.status == "y" || data.status == "Y"){			
				      setTimeout(function(){
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);  					      
				      },500); 
				
			}
		}
	});
	
	
	  
	var ue = UE.getEditor('editor', {
		   toolbars: [
		            ['undo','redo','bold','indent','italic','underline','subscript','superscript','horizontal','removeformat','time', 'date','inserttitle','fontfamily', 'fontsize','paragraph','link', 'justifyleft','justifyright','justifycenter','justifyjustify','forecolor'],
				],
		    autoHeightEnabled: true,
		    autoFloatEnabled: true
		});
			
			
	});
