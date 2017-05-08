<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/page/common/tag/mytags.jsp" %>

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
  <title>万维博通知识库</title>
 
  <%@include file="/page/common/script/main-css.jsp" %>
  
</head>

<body>
  <%@include file="/page/common/nav/navLine.jsp" %>
  
  <div class="ui-page-container">
    <div class="ui-panel">

      <div class="ui-panel-body">
        <div class="ui-table-actions">
          <div class="btns ui-btn-group">
            <m:if_own_auth authCode="skipAddAuth">
            	<a href="javascript:void(0)" class="ui-btn ui-btn-normal" onclick="auth_add('添加权限节点','<%=path%>/authController/skipAddAuth.do','850','510')">添加</a>
            </m:if_own_auth>         
            <a href="javascript:location.replace(location.href);" class="ui-btn ui-btn-secondary">刷新</a>
            <m:if_own_auth authCode="delAuth">
           	    <a href="javascript:void(0)" class="ui-btn ui-btn-danger" onclick="datadel()">删除</a>
            </m:if_own_auth> 
          </div>
          
          <div class="inputs">
            <input style="width:180px" name="search" type="text" class="ui-input" placeholder="权限名称" id="ser_name">
            <input type="text"  class="ui-input" style="width:180px" placeholder="权限码" id="ser_code" >
            <input type="text"  class="ui-input" style="width:180px" placeholder="父节点名称" id="ser_par_name" >
            <a href="javascript:void(0)" class="ui-btn" onclick="goSearch()"><i class="icon_search" ></i>查询</a>
          </div>
         
        </div>
        
        <table id="auth_table" class="ui-table ui-table-border-row ui-table-border-col ui-table-hover table_datatable" fixtable>         
        </table>
        
      </div>
    </div>
  </div>
  
  
  <m:ljs_button operCode="getAuthDetail"  funMethod="auth_edit('编辑','{0}','850','510')" context="编辑" varName="edit_auth" />
  <m:ljs_button operCode="delAuth" funMethod="auth_del(this,'{0}')" context="删除" varName="del_auth"/>	
       

  <%@include file="/page/common/script/main-js.jsp" %>
   
  <script type="text/javascript" src="<%=path%>/plug-in/web/system/auth/auth-list.js"></script>

</body>

</html>
