$(function(){
	
		$('.skin-minimal input').iCheck({
			checkboxClass: 'icheckbox-blue',
			radioClass: 'iradio-blue',
			increaseArea: '20%'
		});
		
		
		$("#form-auth-add").Validform({
			tiptype:2,
			ajaxPost:true,//ajax方式提交表单数据
			beforeSubmit:function(curform){//切换name属性
			 		  	 
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
				

				
			}
		});
	});
	
	
	function showDir(t){
		if($("#iframe_dir").is(":hidden")){
			  var url= path+"/page/system/auth/authZtree.jsp?callmethod=setParNodeVal";
	  		  $("#iframe_dir").html("<iframe name=\"dir_box\" id=\"dir_box\" src=\""+url+"\"  width=\"100%\" height=\"100%\" frameborder=\"NO\" scrolling=\"no\" />");	
			  $(t).text("收起目录");	
		      $("#iframe_dir").show();    
		}else{
			  $(t).text("展开目录");
			  $("#iframe_dir").html("");	
		      $("#iframe_dir").hide();     
		}

	
	}
	
	
	function setParNodeVal(id,name){
		$("#auth-par").val(id);
		$("#par-name").val(name);
	}
	