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
  <title>视频</title>
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/h-ui/static/h	-ui/css/H-ui.reset.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/app/css/common.css">
</head>

<body>
  <%@include file="/page/app/common/header.jsp" %> 
  
  <div class="maincontainer">
    <div class="mainbg-1"></div>
    <div class="video-wrapper">
   	 <div id="a1"></div>
    </div>
    <section class="m-video-info">
      <div class="m-video-title">
          <h1 class="c-title">${result.title}<span></span></h1>
      </div>
    
      <div class="m-video-content" data-node="moreInfo">
          <div class="video-content">
            <div class="video-type">
              <div class="c-director">上传用户：${result.author}</div>
            </div>
            <div class="video-type">
              <div class="c-director">上传时间：${result.createTime}</div>
            </div>
            <div class="video-info">
         	 	${result.content}
            </div>       	
          </div>
      </div>
    </section>
  </div>
   <input type="hidden" value="${result.phoneVideoPath}" id="mPath">
   <%@include file="/page/app/common/footer.jsp" %>
   <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
   <script type="text/javascript" src="${path}/plug-in/h-ui/lib/ckplayer/ckplayer.js" charset="utf-8"></script>
   <script type="text/javascript">	
			  var flashvars={
			    f:$("#mPath").val(),
			    c:0,
			    p:2,
			    b:0,
			    loaded:'loadedHandler'
			  };
		
			  var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
			  var video=[$("#mPath").val()];
			  CKobject.embed('../plug-in/h-ui/lib/ckplayer/ckplayer.swf','a1','ckplayer_a1','100%','100%',false,flashvars,video,params);
			  
			
    </script>

</body>
</html>