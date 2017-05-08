<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String callMethod = request.getParameter("callmethod");

out.print("<script type='text/javascript'> var callMethod= '"+callMethod+"';</script>");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<base href="<%=basePath%>">
	<title>权限树</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/base-css.jsp" %>
	<link href="<%=path %>/plug-in/h-ui/lib/zTree/v3/css/zTreeStyle/zzTree.css" type="text/css" rel="stylesheet">



	</head>
	<body>
	
	
	<div>
	   <div class="prettyprint linenums" style="width:378px;height: 322px;overflow:scroll;">
		  <ul id="tree" class="ztree"></ul>
		</div>
	</div>

	
	<%@include file="/page/common/script/base-js.jsp" %>
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 	 	
	
	
	<script type="text/javascript">
	var path = "<%=path %>";

	var setting = {
	
		async: {
				enable: true,
				url:path+"/authController/getZtree.do",
				autoParam:["id=pid", "level=level"],
				otherParam: {}
				},
			
		view: {
			dblClickExpand: false,
			showLine: false,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id"
			
			}
		},
		callback: {
		  onClick: zTreeOnClick
		}
	
	};

  function zTreeOnClick(event, treeId, treeNode) {
      parent.window[callMethod](treeNode.id,treeNode.name);
 };
	


	
  $(document).ready(function(){ 
  	 $.fn.zTree.init($("#tree"), setting);
  	
  });
		
	

  function getCurrentVal(){
  	var treeObj = $.fn.zTree.getZTreeObj("tree");
	var nodes = treeObj.getSelectedNodes();
	if(nodes.length>0){
		if(nodes.length == 1){
		  return nodes[0].id;
		}else{
		 alert("eror");	
		 return -1;
		}
	
	}else{
	   return -1;
	}

  }



   

     


	

	
		
		
	

	
	</script> 	
	
		
</body>
</html>
