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
  <title>视频列表</title>
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/h-ui/static/h-ui/css/H-ui.reset.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/app/css/common.css">
</head>

<body>
  <%@include file="/page/app/common/header.jsp" %> 
  
  <div class="maincontainer">
    <div class="mainbg-1"></div>
    <ul class="articlelist"></ul>   
    <div class="loadmore">加载更多</div>
  </div>
 
  <%@include file="/page/app/common/footer.jsp" %>
  <input id="path" value="${path}" type="hidden">
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script> 
  <script type="text/javascript" src="<%=path %>/plug-in/app/js/jquery.dotdotdot.min.js"></script>
  <script type="text/javascript">
  var path = $("#path").val();
    $(function() {
    	loadData();    	
    	
      //多行省略
      $('.articlelist .bd').dotdotdot({
        height:44,
        ellipsis  : '... ',
        watch: "window"
      });
      
    });
    
    function loadData(curr){    	
    	curr = curr ||1	
	    $.ajax({
	    	type:"post",
	    	url:path+"/appController/getVideoPage.do",
	    	data: {
	        	 "currentPage":curr ||1,	            	  
	        	 "pageSize":4,
	        	 "columId":38,
	        },
	        dataType: "json",
	        success:function(data){	        	
        	  laypage({
        	        cont: $('.loadmore'), //容器。值支持id名、原生dom对象，jquery对象,
        	        pages: data.totalPage, //总页数
        	        groups: 0, //连续分数数0
        	        prev: false, //不显示上一页
        	        next: '查看更多',
        	        skin: 'flow', //设置信息流模式的样式
        	        jump: function(obj){
        	          if(obj.curr === obj.pages-1){
        	              this.next = '没有更多了';
        	          }else{     
        	        	  loadData(obj.curr);
        	          }
        	        }
        	   });  
        	  resolveData(data.aaData);
	        }
	     })	       
    }   
    
    
    function resolveData(data){   	
    	if (data!= null){
    		 $(".articlelist").html("");
    		
    		$(data).each(function(index){  			
    			var item = data[index];
    			var html = null;
			    	html = "<li>"
			    	html += "<a href="+path+"/appController/moviePage.do?contentId="+item.ctId+">"
			    	html += "<div class='imgwr'>"
			    	html += "<img src="+item.pattern+"/>"
			    	html += "</div>"
			    	html += "<div class='cont'>"
			    	html += "<div class='hd'>"+item.title==null?"":item.title+"</div>"
			    	html += "<div class='bd'>"+(item.content==null)?"":item.content+"</div>"
			    	html += "</div>"
			    	html += "</a>"
			    	html += "</li>"
			    	
    		
    		    $(".articlelist").append(html);
    		})    	
    	}
    }
    
  </script>
</body>

</html>
