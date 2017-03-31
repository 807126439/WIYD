<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp" %>
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
  <title>摄影投稿</title>
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/h-ui/static/h-ui/css/H-ui.reset.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/app/css/uploader.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/app/css/common.css"> 
</head>

<body>
  <%@include file="/page/app/common/header.jsp" %>
  <div class="maincontainer">
    <section class="panelcont">
      <h3 class="tt-1">“月月精彩”摄影比赛作品上传投稿</h3>
      <form class="form_photography" id="form-photography-add" >
        <input id="activityId" name="activityId" value="${activityId}" type="hidden">
       <div class="ipts">
          <div>
            <span>标题</span>
            <span><input type="text" class="input-1" id="title" name="title" datatype="*1-30"></span>
          </div>
          <div>
            <span>作者</span>
            <span><input type="text" class="input-1" id="author" name="author" datatype="*1-20"></span>
          </div>
          <div>
            <span>描述</span>
            <span>
              <span class="textarea-hascount-wrap"><textarea placeholder="说点什么...最少输入10字符" class="textarea-1" id="description" datatype="*10-800"></textarea><span class="textarea-num"><span>0</span>/<span>800</span></span></span>
            </span>
          </div>
        </div>
        
        
        
       <div class="iptgroup-1 iptgroup_photo">
          <em class="need">*</em>
          <label>照片：</label>
          <div id="uploader">
            <div class="queueList">
              <ul class="filelist">
                <li id="fileDelete"><div><div class="wr"></div></div></li>
                <li id="filePicker"><div><div class="wr"></div></div></li>
              </ul>
            </div>
            <div id="filePicker2"></div>
          </div>
        </div>
        <div class="btns">
          <a href="javascript:void(0)" class="btn-1" id="submitData">提交</a>
          
        </div>
      </form>	
    </section>
  </div>
   <%@include file="/page/app/common/footer.jsp" %>
  
   
  <input type="hidden" value="${path}" id="path">
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/Validform/5.3.2/Validform.min.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.js"></script> 
  <script type="text/javascript" src="<%=path %>/plug-in/app/js/uploadimg.js"></script> 

  <script type="text/javascript">
    var path = $("#path").val();

  </script>
</body>

</html>
