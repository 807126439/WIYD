<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

<head>
  <base href="<%=basePath%>">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <meta name="renderer" content="webkit|ie-stand">
  <title>万维博通知识库</title>
  <link href="<%=path%>/plug-in/h-ui/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
  <link href="<%=path %>/plug-in/main/libs/icheck/icheck.css" rel="stylesheet" type="text/css"/>
  <link rel="stylesheet" type="text/css" href="<%=path%>/plug-in/main/css/common.css">
  
  <!--[if lt IE 9]>
  <script type="text/javascript" src="libs/h-ui/2.2.1/lib/html5.js"></script>
  <script type="text/javascript" src="libs/h-ui/2.2.1/lib/respond.min.js"></script>
  <![endif]-->
</head>

<body>
  <div class="ui-topnav">
    <span class="ui-topnav-title">添砖加瓦</span>
    <span class="ui-location"><span class="ui-location-pre">当前位置：</span><ol class="ui-location-path"><li>首页</li><li>添砖加瓦</li></ol></span>
  </div>
  
  <div class="ui-page-container">
    <div class="ui-panel">
      <div class="ui-panel-body cl">
        <div class="ui-form-item">
          <div class="ui-form-item-title">类别设定：</div>
          
          
          <div class="radio_classify-wrapper">
          	<c:forEach items="${categorys}" var="category" varStatus="vs">
          		<input type="radio" name="categoryId" class="radio_classify" data-form="form-${category.typeId}" data-score="${category.downNeedScore}" value="${category.id}">
            	<label>${category.categoryName}</label>
          	</c:forEach>
          </div>
        </div>
      </div>
    </div>
    <div class="ui-panel">
      <div class="ui-panel-body cl">
      
      <form action="<%=path %>/documentFileController/addDocumentFile.do" method="post" id="form-documentFile-add" >
      	<input type="hidden" id="ucode1" name="ucode" />
      	<input type="hidden" id="categoryId1" name="categoryId" />
        <div class="ui-form form_classify form-1">
	      <div class="ui-form-item">
	            <div class="ui-form-item-title">请输入编号：</div>
	            <div class="ui-input-block"><input type="text" class="ui-input" id="documentName" placeholder="忽略则默认为文件名称"></div>
	            <a href="javascript:void(0)" onclick="addInvestment()" class="ui-btn">新增</a>
	      </div>
	      
	      
          
          
          
          
          
          <div class="ui-form-item ui-form-btns">
            <a href="javascript:void(0)" onclick="addFile(1)"  class="ui-btn ui-btn-secondary">确认提交</a>
          </div>
        </div><!-- form-1 -->
       </form>
        
       </form>
        
      </div>
    </div>
    <div class="ui-panel">
      <div class="tip-title">温馨提醒</div>
      <div class="tip-item">1. 所有用户均可上传文件，成功上传文件后，可获得一定的积分；</div>
      <div class="tip-item">2. 用户可以针对所上传的文件，设定其下载所需积分（必须上传的文件不能设定下载积分），但所设定的积分不能超过规定；</div>
      <div class="tip-item">3.更多关于积分的介绍，请点击查看知识库积分说明。</div>
    </div>
  </div>
  
  <script type="text/javascript" src="<%=path%>/plug-in/main/libs/jquery/1.11.3/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/Validform/5.3.2/Validform.min.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/main/libs/icheck/icheck.js"></script>
  <script type="text/javascript" src="<%=path%>/plug-in/main/js/public.js"></script>
  <script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/layer/2.4/layer.js"></script>
  <script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.min.js"></script>
  <script type="text/javascript" src="<%=path%>/plug-in/filemanager/1.0.0/uploader.js"></script>
  <script type="text/javascript" src="<%=path%>/plug-in/web/Investment/Investment/Investment-add.js"></script>
</body>

</html>