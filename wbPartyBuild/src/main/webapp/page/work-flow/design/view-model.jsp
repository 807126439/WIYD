<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


String processData = (String )request.getAttribute("processData");
String nodeCode = (String )request.getAttribute("nodeCode");
if(processData!=null){
	out.print("<script type='text/javascript'> var processData = "+processData+",nodeCode='"+nodeCode+"';</script>");
}


%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">
  <head>
    <base href="<%=basePath%>" />
    
    <!--[if lt IE 9]>
		<?import namespace="v" implementation="#default#VML" ?>
	<![endif]-->
	
	<link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/work-flow/design/libs/gooFlow/codebase/GooFlow2.css" />
		
	<style>
	.myForm{display:block;margin:0px;padding:0px;line-height:1.5;border:#ccc 1px solid;font: 12px Arial, Helvetica, sans-serif;margin:5px 5px 0px 0px;border-radius:4px;}
	.myForm .form_title{background:#428bca;padding:4px;color:#fff;border-radius:3px 3px 0px 0px;}
	.myForm .form_content{padding:4px;background:#fff;}
	.myForm .form_content table{border:0px}
	.myForm .form_content table td{border:0px}
	.myForm .form_content table td input{width: 100%}
	.myForm .form_content table .th{text-align:right;font-weight:bold}
	.myForm .form_btn_div{text-align:center;border-top:#ccc 1px solid;background:#f5f5f5;padding:4px;border-radius:0px 0px 3px 3px;} 
	#propertyForm{float:left;width:300px}
	</style>
	
	<script type="text/javascript" src="<%=path %>/plug-in/work-flow/design/libs/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/plug-in/work-flow/design/libs/GooFunc.js"></script>
	<script type="text/javascript" src="<%=path %>/plug-in/work-flow/design/libs/json2.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/work-flow/design/libs/default.css"/>
	
	<script type="text/javascript" src="<%=path %>/plug-in/work-flow/design/libs/gooFlow/codebase/GooFlow.js"></script>
	<script type="text/javascript" src="<%=path %>/plug-in/work-flow/design/libs/gooFlow/codebase/GooFlow_color.js"></script>
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/layer/2.4/layer.js"></script> 
	
	<script type="text/javascript">
	var property={
		width:$(window).width() ,
		height:$(window).height(),
		haveTool: false,
     	haveHead: true,
      headBtns: [],
      haveGroup: false,
      useOperStack: true
	};

	var demo;
	$(document).ready(function(){
	
		demo=$.createGooFlow($("#demo"),property);
	
		
		if(typeof(processData)!= "undefined"){
			if(processData){
				demo.loadData(processData);
			}
		}
		
		if(typeof(nodeCode)!= "undefined"){
			if(nodeCode){
			demo.markItem(nodeCode,"node",true);
			}	
		}
		

		

	});


	</script>
	</head>
	
	
	<body style="background:#EEEEEE">
		<div id="demo" style="margin:5px;float:left"></div>
	
	</body>
	
	
	
	</html>