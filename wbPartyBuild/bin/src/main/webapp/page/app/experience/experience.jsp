<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <meta name="renderer" content="webkit|ie-stand">
  <meta name="viewport" content="initial-scale=1.0,maximum-scale=1,user-scalable=no">
  <meta content="telephone=no,email=no" name="format-detection">
  <title>心得体会</title>
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/h-ui/static/h-ui/css/H-ui.reset.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/app/css/common.css">
</head>

<body>
  <%@include file="/page/app/common/header.jsp" %>
  <div class="maincontainer">
    <section class="panelcont">
      <h3 class="tt-1">上传我的心得体会</h3>
      <form class="form_Experience" action="<%=path%>/appController/addExperience.do" method="post">
       <div class="ipts">
          <div>
            <span>标题：</span>
            <span><input type="text" class="input-1" datatype="*1-30" name="title" id="title"></span>
          </div>
          <div>
            <span>内容：</span>
            <span>
              <span class="textarea-hascount-wrap"><textarea placeholder="说点什么...最少输入10字符" class="textarea-1" name="content" id="content" datatype="*10-800"></textarea><span class="textarea-num"><span>0</span>/<span>800</span></span></span>
            </span>
          </div>
        </div>
       <div class="btns">
        	<a href="javascript:void(0)" class="btn-1" id="submitData">提交</a>
        </div>
      </form>
    </section>
  </div>
  <%@include file="/page/app/common/footer.jsp" %>
 <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/Validform/5.3.2/Validform.min.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
  <script type="text/javascript">
    $(function() {
      	$(".form_Experience").Validform({
			tiptype:4,
			ajaxPost:true,//ajax方式提交表单数据
			btnSubmit: "#submitData",
			beforeSubmit:function(curform){
				return true;			  	 			  	
			},
			callback:function(data){				
				if(data.status == "y" || data.status == "Y"){			
					    layer.msg("提交成功",{icon:1,time:1800});
					    setTimeout(function(){
						      window.history.go(-1); 	    
						  },800);	
					
				}				
			}
		});

      $("#content").keyup(function() {
        var curnum=$(this).val().length;
        var limitnum=$("#content").next('.textarea-num').find('span:first-child+span').text();
        if (curnum>limitnum) {
          $(this).next('.textarea-num').addClass('beyond');
        }else{
          $(this).next('.textarea-num').removeClass('beyond');
        }
        $(this).next('.textarea-num').find('span:first-child').text(curnum);
      });
    });
  </script>
</body>

</html>
