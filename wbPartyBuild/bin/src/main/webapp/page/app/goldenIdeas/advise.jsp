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
  <title>金点子建议</title>
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/h-ui/static/h-ui/css/H-ui.reset.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/app/css/common.css">
 
  
  
</head>

<body>

  <%@include file="/page/app/common/header.jsp" %>
  
  <div class="maincontainer">
    <section class="panelcont">
      <h3 class="tt-1">对智慧党建版块建议</h3>
      <form class="form_advise" action="<%=path%>/appController/addOpinion.do" method="post">
      	<input type="hidden" value="2" name="typeId" id="typeId"/>
        <div class="ipts">
          <div>
            <span>申报时间</span>
            <span><input type="text" class="input-1" name="feedbackTime" readonly="readonly" id="applyDate"></span>
          </div>
          <div>
            <span>申报主题</span>
            <span><input type="text"  class="input-1" name="theme" datatype="*2-20" id="theme"></span>
          </div>
          <div>
            <span>反馈意见</span>
            <span><textarea placeholder="说点什么..."  name="content" id="content" class="textarea-1" datatype="*2-400"></textarea></span>
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
 $(function(){
		$(".form_advise").Validform({
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
	
      $("#applyDate").val(FormatDate).css("color","#949494");
      function FormatDate (strTime) {
        if (strTime) {
          var date = new Date(strTime);
        }else{
          var date = new Date();
        }
        return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
      }
    });
  </script>
</body>

</html>
