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
  <title>互动交流</title>
   <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/h-ui/static/h-ui/css/H-ui.reset.css">
   <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/app/css/common.css">
</head>

<body>
  <%@include file="/page/app/common/header.jsp" %>
  <div class="maincontainer">
    <ul class="issuelist" id="issuelist">
    </ul>
    <div class="loadmore" id="loadmore" style="display:none">加载更多</div>
  </div>
  
  <input id="totalPage" value="${totalPage}" type="hidden">
  <input id="path" value="${path}" type="hidden">
  <%@include file="/page/app/common/footer.jsp" %>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script> 
  <script type="text/javascript" src="<%=path %>/plug-in/app/js/jquery.dotdotdot.min.js"></script>

  <script type="text/javascript">
  var path = $("#path").val();
  var totalPage = $("#totalPage").val();
  function queryPage(){	  
	  laypage({
	      cont: $('.loadmore'), //容器。值支持id名、原生dom对象，jquery对象,
	      pages: totalPage, //总页数
	      groups: 0, //连续分数数0
	      prev: false, //不显示上一页
	      next: '查看更多',
	      skin: 'flow', //设置信息流模式的样式
	      jump: function(obj) {
	        if (obj.curr === totalPage - 1) {
	          this.next = '没有更多了';
	        }
	        
	        $.ajax({		  
	    		type:'post',
	          	dataType:'json',
	          	url:path+"/communController/getAppPage.do",
	          	data:{
	          		currentPage:obj.curr,
	          		pageSize:3,
	          		status:1
	          	},
	          	success:function(data){
	          		$("#issuelist").html("");      		
	          		$.each(data.list,function(i, item) {    	          			
	          			var str = "<li data-itemid='"+item.id+"'><a class='issue-bd' href='"+path+"/appController/topicDetail.do?comId="+item.id+"'>"
      	   	            + "<div class='issue-tt'>"+item.title+"</div>"
      	   	            + "<div class='issue-ct'>"
      	   	            + "<div class='textwrap'>"+item.content+"</div>"
      	   	            + "</div>"
      	   	            + "</a>"
      	   	            + "<div class='issue-ft'>"
      	   	            + "<div class='author'>"+item.sponsor+"</div>"
      	   	            + "<div class='pdate'>"+item.startDate+"</div>";     	   	            
      	   	            //判断当前用户是否已经点赞
      	   	            if(item.isLove == 0){
      	   	            	//已经点赞
      	   	            	str = str + "<a class='issue-digg haddigg' href='javascript:void(0)'>"
      	   	            }else{
      	   	            	//未点赞
      	   	          		str = str + "<a class='issue-digg' href='javascript:void(0)'>"
      	   	            }	          			
	          			str = str + "<i class='icon_digg'></i>"
      	   	            + "<span class='digg-notdigg'>赞</span>"
      	   	            + "<span class='digg-num'>"+item.love+"</span>"
      	   	            + "</a>"
      	   	            + "<a class='issue-commentnum' href='javascript:void(0)'>"
      	   	            + "<i class='icon_comment'></i>"
      	   	            + "<span>"+item.commentNums+"</span>"
      	   	            + "</a>"
      	   	            + "</div>"
      	   	            + "</li>" 
      	   	            
	          			$("#issuelist").append(str);
	          	
	    	  })
	    	  
	      		}
	   		});	
 		 }
	  })
	 }
  
  $(function() {
	  
	  if(totalPage > 1){		  
		  $("#loadmore").show();		  
	  }else{
		  $("#loadmore").hide();
	  }	  
	  queryPage();
	  

     // 点赞与取消点赞
    $('.maincontainer').on('click', '.issue-digg', function() {
      var type;
      var el = this;
 

      if ($(el).hasClass('haddigg')) {
    	//取消点赞
    	type = 4;
      } else {
    	//点赞
    	type = 3;
      }
	
      $.ajax({
        type: 'post',
        url: path + "/communController/editCommun.do",
        data: {
          id: $(el).closest('li').data('itemid'),
  		  type:type        
        },
        success: function() {
          $(el).toggleClass('haddigg');
          if ($(el).hasClass('haddigg'))
            $(el).find('.digg-num').text(parseInt($(el).find('.digg-num').text()) + 1);
          else $(el).find('.digg-num').text(parseInt($(el).find('.digg-num').text()) - 1);
        },
        error: function(e) {
          if (e.status === 404 || e.status === '404') {
            layer.msg("网络异常");
          } else {
            layer.msg("服务器异常");
          }

        }
      });
    }); 

    //多行省略
    $('.issuelist .textwrap').dotdotdot({
      height: 96,
      ellipsis: '...',
      watch: "window"
    });
  });
  </script>
</body>

</html>

