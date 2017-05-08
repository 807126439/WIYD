<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <meta name="renderer" content="webkit|ie-stand">
  <meta name="viewport" content="initial-scale=1.0,maximum-scale=1,user-scalable=no">
  <meta content="telephone=no,email=no" name="format-detection">
  <title>在线测试列表</title>
   <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/h-ui/static/h-ui/css/H-ui.reset.css">
   <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/app/css/common.css">
</head>

<body>
  <%@include file="/page/app/common/header.jsp" %> 
  <div class="maincontainer">
    <div class="mainbg-1"></div>
    <ul class="testlist">
    </ul>
    <div class="loadmore" id="loadmore">加载更多</div>
  </div>
  
  <input id="totalPage" value="${totalPage}" type="hidden">
  <input id="path" value="${path}" type="hidden">
  <%@include file="/page/app/common/footer.jsp" %>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script> 
  <script type="text/javascript">
  var totalPage = $("#totalPage").val();
  var path = $("#path").val();
  
  $(function() { 	  
	  queryPage();
	  if(totalPage>1){
		  $("#loadmore").show();		  
	  }else{
		  $("#loadmore").hide();
	  }      	
   })
   
  
  function queryPage(){
	  laypage({
	        cont: $('.loadmore'), //容器。值支持id名、原生dom对象，jquery对象,
	        pages: 3, //总页数
	        groups: 0, //连续分数数0
	        prev: false, //不显示上一页
	        next: '查看更多',
	        skin: 'flow', //设置信息流模式的样式
	        jump: function(obj){
	        	
	        	$.ajax({
        			type:'post',
        			dataType:'json',
        			url:path+"/studyTaskController/getAppPage.do",
        			data:{
        				currentPage:obj.curr,
    	          		pageSize:4,       				
       				},       			
        			success:function(data){
        				$.each(data.list,function(i, item) { 
        					
        					var str = "<li>"
				    	            +"<a href='"+path+"/appController/testDetail.do?taskId="+item.stId+"'>"
				  	                +"<div class='hd'>"+item.taskName+"</div>"
				  	                +"<div class='bd'>"
				  	                +"<div>测试时间："+item.startTime+" 至  "+item.endTime+"</div>"
				  	                +"<div>任务说明："+item.taskMemo+"</div>"
				  	                +"<div>资料列表："+item.dataName+"</div>"
				  	                +"<div>测试试卷："+item.paperName+"</div>"
				  	                +"</div>"
				  	                +"</a>"
				  	                +"</li>"
				  	                
				  	          $('.testlist').append(str);       
        					
        				})  	
        			}
         		})
	        }	  
	  })
  }
  


  </script>
</body>

</html>

