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
  <title>添加样本</title>
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
      
      <form action="<%=path %>/documentFileController/addDocumentFile.do" method="post" id="form-documentFile-add" >
      	<input type="hidden" id="ucode1" name="ucode" />
      	<input type="hidden" id="categoryId1" name="categoryId" />
        <div class="ui-form form_classify form-1">
	      <div class="ui-form-item">
	            <div class="ui-form-item-title">请输入编号：</div>
	            <div class="ui-input-block"><input type="text" class="ui-input" id="number" placeholder="" style="width: 80%;display: inline;margin-right: 20px" ><a href="javascript:void(0)" onclick="addInvestment()" class="ui-btn">新增</a></div>
	      </div>
          <!-- 
          <div class="ui-form-item ui-form-btns">
            <a href="javascript:void(0)" onclick="addFile(1)"  class="ui-btn ui-btn-secondary">确认提交</a>
          </div>
          -->
        </div><!-- form-1 -->
       </form>
      </div>
    </div>
    
    <div class="ui-panel" style="display: none;" id="zt-panel">
      <div class="ui-panel-body cl">
      		<div id="ztChart" style="width: 600px;height:400px;"></div>
      </div>
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
  <script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/echarts/3/echarts.js"></script>
  <script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/echarts/ecStat.js"></script>
  <script type="text/javascript">
  		
  	
  	
  	
  </script>
</body>

</html>
