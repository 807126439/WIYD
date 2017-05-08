var tempName = window.document.location.pathname;
var path = tempName.substring(0,tempName.substr(1).indexOf('/')+1);


$(function() {
 
  
  
    
    $("#account-form").Validform({
      tiptype: function(msg, o, cssctl) {
        if (!o.obj.is("form")) { //验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
          var objtip = o.obj.closest('.group').find(".Validform_checktip");
          if (objtip.length === 0) {
            objtip = $('<span class="Validform_checktip">').appendTo(o.obj.closest('.group'));
          }
          cssctl(objtip, o.type);
          objtip.text(msg);
        }
      },
      tipSweep: false, //为true时提示信息将只会在表单提交时触发显示
      ajaxPost: true,
      beforeSubmit:function(curform){
		return true;			  	 			  	
	  },
      callback:function(data){				
		  if(data.status == "y" || data.status == "Y"){			
			$('#account-form').hide();
			$("#pwd-qt").text(data.backVal);
			$("#captcha-div").append("<img src=\""+path+"/Kaptcha.jpg\" id=\"captchaImage2\" class=\"pl-10\" />");
			$("#account2").val($("#find-account-name").val());
						 
		    $("#captchaImage2").click(function() {
				var timestamp = (new Date()).valueOf();
				var imageSrc = $(this).attr("src");
				if (imageSrc.indexOf("?") >= 0) {
					imageSrc = imageSrc.substring(0, imageSrc.indexOf("?"));
				}
				imageSrc = imageSrc + "?timestamp=" + timestamp;
				$(this).attr("src", imageSrc);
     	    }); 
    
			
            $('#find-account-form').show();				
		  }else{
		  	layer.msg(data.info,{icon: 2,time:2500});
		  }				
	  }
    });
  
  
  
  
    $("#find-account-form").Validform({
      tiptype: function(msg, o, cssctl) {
        if (!o.obj.is("form")) { //验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
          var objtip = o.obj.closest('.group').find(".Validform_checktip");
          if (objtip.length === 0) {
            objtip = $('<span class="Validform_checktip">').appendTo(o.obj.closest('.group'));
          }
          cssctl(objtip, o.type);
          objtip.text(msg);
        }
      },
      tipSweep: false, //为true时提示信息将只会在表单提交时触发显示
      ajaxPost: true,
      beforeSubmit:function(curform){
		return true;			  	 			  	
	  },
      callback:function(data){				
			if(data.status == "y" || data.status == "Y"){			
				$('#find-account-form').hide();
         		$('.tipbox').show();				
			}else{
				layer.msg(data.info,{icon: 2,time:2500});
			}				
	  },
      usePlugin: {
        passwordstrength: {
          minLen: 6, //设置密码长度最小值，默认为0;
          maxLen: 16, //设置密码长度最大值，默认为30;
          trigger: function(obj, error) {
            if (error) {
              obj.closest('.group').find(".Validform_checktip").show();
              obj.closest('.group').find(".passwordStrength").hide();
            } else {
              obj.closest('.group').find(".Validform_checktip").hide();
              obj.closest('.group').find(".passwordStrength").show();
            }
          }
        }
      }
    });
    
    
   	var $captchaImage = $("#captchaImage");

		// 刷新验证码
		$captchaImage.click(function() {
			var timestamp = (new Date()).valueOf();
			var imageSrc = $(this).attr("src");
			if (imageSrc.indexOf("?") >= 0) {
				imageSrc = imageSrc.substring(0, imageSrc.indexOf("?"));
			}
			imageSrc = imageSrc + "?timestamp=" + timestamp;
			$(this).attr("src", imageSrc);
      }); 
  
    

    
  });
  
  
  
function sumbitForm(formId){
	$("#"+formId).submit();
}
  








