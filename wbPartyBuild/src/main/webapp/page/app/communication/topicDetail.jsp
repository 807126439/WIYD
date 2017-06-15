<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp"%>
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
  <title>互动交流</title>
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/h-ui/static/h-ui/css/H-ui.reset.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/app/css/common.css">
</head>

<body>
  <%@include file="/page/app/common/header.jsp" %>
  <div class="maincontainer">
    <div class="currentissue">
      <div class="issue-hd">
        <div class="issue-tt">${result.title}</div>
        <div class="author">${result.sponsor}</div>
        <div class="pdate">  <fmt:formatDate value="${result.startDate}" type="date"/></div>
        <div class="reply-call"><i class="icon_reply"></i></div>
      </div>
      <div class="issue-bd">
        <div class="issue-ct">
          <div class="textwrap">${result.content}</div>
        </div>
      </div>
      <div class="video-wrapper">
   	 	<div id="a1"></div>
    </div>
    </div>
    <div class="comment_container" id="commentContainer">
      <div class="redtitle">
        <ul class="sort_wrap">
          <li id="1">最热</li>
          <li id="2" class="active">最新</li>
        </ul>
      </div>
      <ul class="commentlist" id="commentlist">
      </ul>
      <div class="loadmore appendPage">加载更多</div>
    </div>

    <div class="ui-editor" id="ueditor-1">
      <div class="ui-editor-ipt">
        <form id="commmentForm"  method="post">
          <input id="path"  name="path" value="${path}" type="hidden">
          <input id="comId" name="comId" value="${result.id}" type="hidden">
          <input id="parentId" name="parentId" type="hidden">
          <input id="userId" name="userId" type="hidden">
          <script id="editor" type="text/plain" style="width:100%;height:150px;"></script>
        </form> 
      </div>
      <div class="ui-editor-f">
        <a href="javascript:void(0);" class="hide_editor">取消</a>
        <a href="javascript:void(0);" onclick="submitComment()" class="btn_publish">发表</a>
      </div>
    </div>

    <div class="comment-btns">
      <a class="back" href="javascript:void(0)">返回</a>
      <a class="reply-call" href="javascript:void(0)">回复</a>
    </div>

    <div class="evaluate">
	    <!-- 当前用户未点赞 -->
	    <c:if test="${result.isLove == 1}">
	    	 <a class="issue-digg" href="javascript:void(0)">
	    </c:if>
	     <!-- 当前用户已点赞 -->
	    <c:if test="${result.isLove == 0}">
	    	 <a class="issue-digg haddigg" href="javascript:void(0)">
	    </c:if>
        <i class="icon_digg"></i>
        <span class="digg-notdigg">赞</span>
        <span class="digg-num">${result.love}</span>
      </a>
      <a class="issue-commentnum" href="javascript:void(0)">
        <i class="icon_comment"></i>
        <span id="pNum">0</span>
      </a>
    </div>
    <div class="placeholder-1"></div>
  </div>

  <%@include file="/page/app/common/footer.jsp" %>
  <input id="totalPage" value="${totalPage}" type="hidden">
   <input type="hidden" value="${result.videoPath}" id="mPath">
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script> 
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.config.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.all.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/app/js/issue.js"></script>
   <script type="text/javascript" src="${path}/plug-in/h-ui/lib/ckplayer/ckplayer.js" charset="utf-8"></script>
   <script type="text/javascript">	
   
   	   var mPath = $("#mPath").val();   
	   if(mPath!=null && mPath!=""){
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
	   }			
    </script>
</body>
</html>