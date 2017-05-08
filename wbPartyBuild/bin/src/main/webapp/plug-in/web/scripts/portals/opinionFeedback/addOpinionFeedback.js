var date = new Date();
var str=date.getFullYear()+"-"+((date.getMonth()+1)<10?"0":"")+(date.getMonth()+1)+"-"+(date.getDate()<10?"0":"")+date.getDate();			
$("#date").val(str);
$(function(){
	
	
	
	
	$("#advise-form").Validform({
		tiptype:2,
		ajaxPost:true,//ajax方式提交表单数据
		beforeSubmit:function(curform){
			 
		     return true;
		    
		},
		callback:function(data){			
				if(data.status == "y" || data.status == "Y"){	
				    
					  setTimeout(function(){
						    
						  $("#advise-form")[0].reset();
						  $("#date").val(str);
						  $("#Validform_msg").hide();
						  
					  },1000); 
					
				}
			}
		});
	
});


function checkVal(){
	  var theme =  $("#theme").val();
	  if(theme == "undefined" || theme == null || theme == ""){
	  	alert("请输入申报主题！");
	  	return false;
	  }
	 var content =  $("#content").val();
	  if(content == "undefined" || content == null || content == ""){
	  	alert("请输入反馈内容！");
	  	return false;
	  }
	  $("#advise-form").submit();
	}
			