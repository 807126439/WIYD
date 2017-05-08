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
  <title>文章阅读</title>
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/h-ui/static/h-ui/css/H-ui.reset.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/app/css/common.css">
</head>

<body>
  <%@include file="/page/app/common/header.jsp" %>
  <div class="maincontainer">
    <div class="mainbg-1"></div>
    <ul class="articlelist">
    </ul>
    <div class="loadmore">加载更多</div>
  </div>
  <!-- footerbar's btns -->
  <%@include file="/page/app/common/footer.jsp" %>
  
  <input type="hidden" value="${pages}" id="pages">
  <input type="hidden" value="${path}" id="path">
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/layer/2.1/layer.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/jquery.dotdotdot/jquery.dotdotdot.min.js"></script>
  <script type="text/javascript">
  	var path=$("#path").val();
    $(function() {
      laypage({
        cont: $('.loadmore'), //容器。值支持id名、原生dom对象，jquery对象,
        pages: $('#pages').val(), //总页数
        groups: 0, //连续分数数0
        prev: false, //不显示上一页
        next: '查看更多',
        skin: 'flow', //设置信息流模式的样式
        jump: function(obj){
        	if(obj.curr === obj.pages-1){
	            this.next = '没有更多了';
	          }else{
	          	getContentData(obj.curr);
	          }
        }
      });

      //多行省略
      $('.articlelist .textwrap').dotdotdot({
        height:44,
        ellipsis  : '...',
        watch: "window"
      });
    });
    
    //获取显示数据
      function getContentData(currPage){
	      	$.ajax({
					    url: path+"/appController/nextContentList.do",
					    traditional:true,
					    dataType:'json',  
					    data:{
					    	"currPage":currPage
					    },
					    type:"post",
					    cache : false,  
					    async : false,  
					    success:function(data){	
					    		if(data.status == "y" || data.status == "Y"){
					          		printList(data.backVal);
					    		}else{
					    			layer.msg(data.info,{icon: 2,time:2500});
					    		}	    					         				           
					                		           
					       },  
					     error : function(error) {  
					            alert(error); 		            
					       } 
				  });
      }
      
      function printList(list){
      	var str='';
      	for(var i=0;i<list.length;i++){
      		str+='<li>\
                    <a href="javascript:void(0)" onclick="checkContent(this)" data-val='+list[i].ctId+' >'+
                    (!list[i].pattern?'':('<div class="pic" style="background-image: url('+list[i].pattern+');"></div>'))+
                    '<div class="cont">\
                      <div class="hd">'+list[i].title+'</div>\
                      <div class="ft">'+list[i].createTime+'</div>\
                    </div>\
                  </a>\
                </li>';
      	}
      	$('.articlelist').append($(str));
      	
      }
      function checkContent(a){
      		var ctId=$(a).attr("data-val");
      		var path=$("#path").val();
      		window.location.href=path+"/appController/contentDetil.do?ctId="+ctId;
      		/*
      		$.ajax({
					    url: path+"/appController/contentDetil.do",
					    traditional:true,
					    dataType:'json',  
					    data:{
					    	"ctId":ctId
					    },
					    type:"post",
					    cache : false,  
					    async : false,  
					    success:function(data){	
					    		if(data.status == "y" || data.status == "Y"){
					    			window.loaction.href=path+data.backVal;
					    		}else{
					    			layer.msg(data.info,{icon: 2,time:2500});
					    		}	    					         				           
					                		           
					       },  
					     error : function(error) {  
					            alert(error); 		            
					       } 
				  });
				  */
      }
      
  </script>
</body>

</html>
