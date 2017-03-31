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
  <title>在线测试</title>
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/h-ui/static/h-ui/css/H-ui.reset.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/app/css/common.css">
   <link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/app/css/onlinetest.css">
</head>

<body>
  
  <header class="onlinetest-topbar"><a href="javascript:window.history.go(-1)" class="test-back"></a>${result.paperName}</header>
  <div id="onlinetest">    
    <input id="testTime" value="${result.testTime}" type="hidden">
 	<input id="path" value="${path}" type="hidden">
  	<input id="taskId" value="${taskId}" type="hidden">
    
    <div class="onlinetest-controll clearfix">
      <div class="questionnum">
        <span>1</span>/<span>${result.topicSize}</span>
      </div>
      <strong></strong>
      <div class="btngroup">
        <a class="btn-pause" href="javascript:void(0)"><span>暂停</span><span>开始</span></a>
        <a class="btn-their" href="javascript:void(0)">交卷</a>
      </div>
    </div>
    <div class="onlinetest-ct">
      <div class="questionlist">
      
      <c:forEach items="${result.topicList}" var="topic">
        <!-- 1：单选  2：多选  3：判断  4：填空 --> 
      		<c:choose>
      			<c:when test="${topic.topicType eq 1}">
      				<c:choose>
      					<c:when test="${topic.sortNum eq 1}">
      						<div class="question singlechoice current" data-qid="${topic.topicId}" data-score="${topic.score}">
      					</c:when>
      					<c:otherwise>
      						<div class="question singlechoice" data-qid="${topic.topicId}" data-score="${topic.score}">
      					</c:otherwise>
      				</c:choose>
      			</c:when>
      			<c:when test="${topic.topicType eq 2}">
      				<c:choose>
      					<c:when test="${topic.sortNum eq 1}">
      		     			<div class="question morechoice current" data-qid="${topic.topicId}" data-score="${topic.score}">		
      					</c:when>
      					<c:otherwise>
      						<div class="question morechoice" data-qid="${topic.topicId}" data-score="${topic.score}">
      					</c:otherwise>
      				</c:choose>
      			</c:when>
      			<c:when test="${topic.topicType eq 3}">
      				<c:choose>
      					<c:when test="${topic.sortNum eq 1}">
      						<div class="question trueorfalse current" data-qid="${topic.topicId}" data-score="${topic.score}">
      					</c:when>
      					<c:otherwise>
      						<div class="question trueorfalse" data-qid="${topic.topicId}" data-score="${topic.score}">
      					</c:otherwise>
      				</c:choose>
      			</c:when>
      			<c:when test="${topic.topicType eq 4}">
      				<c:choose>
      					<c:when test="${topic.sortNum eq 1}">
      						<div class="question gapfill current" data-qid="${topic.topicId}" data-score="${topic.score}">
      					</c:when>
      					<c:otherwise>
      						<div class="question gapfill" data-qid="${topic.topicId}" data-score="${topic.score}">
      					</c:otherwise>
      				</c:choose>
      			</c:when>    			
      		</c:choose>
  	      
	          <p class="question-title">${topic.sortNum}.【${topic.topicTypeName}】 ${topic.questionText}</p>
	          
	          <c:choose>
	          	<c:when test="${topic.topicType eq 4}">
	          		 <ol class="anwsergroup">
	          		<c:forEach	begin="1"  end="${topic.fillNums}">
	          		 	<li>
			              <input type="text" autocomplete="off">
			            </li>
	          		 </c:forEach>
			         </ol>
	          	</c:when>
	          	<c:otherwise>
	          	 <ol class="question-options">	
		          	 <c:if test="${!empty topic.optionA}">
		          	 	<li>${topic.optionA}</li>	
		          	 </c:if>
		          	 <c:if test="${!empty topic.optionB}">
		          	 	<li>${topic.optionB}</li>
		          	 </c:if>
		          	 <c:if test="${!empty topic.optionC}">
		          	 	<li>${topic.optionC}</li>
		          	 </c:if>
		          	 <c:if test="${!empty topic.optionD}">
		          	 	<li>${topic.optionD}</li>
		          	 </c:if>
		          	 <c:if test="${!empty topic.optionE}">
		          	 	<li>${topic.optionE}</li>
		          	 </c:if>
		          	 <c:if test="${!empty topic.optionF}">
		          	 	<li>${topic.optionF}</li>
		          	 </c:if>
		          	 <c:if test="${!empty topic.optionG}">
		          	 	<li>${topic.optionG}</li>
		          	 </c:if>
		         </ol> 	
	          	</c:otherwise>
	          </c:choose>
	          <a class="btn-submit" href="javascript:void(0)">提交答案</a>
	       </div>      
      </c:forEach>
      
      
      <div class="onlinetest-ft">
        <p class="onlinetest-guide"><a href="javascript:void(0)" class="onlinetest-prev">上一题</a><a href="javascript:void(0)" class="onlinetest-opennav">选择题号</a><a href="javascript:void(0)" class="onlinetest-next">下一题</a></p>
        <ol class="onlinetest-navlist">
        
           <c:forEach items="${result.topicList}" var="topic">           
           	<c:choose>
           		<c:when test="${topic.sortNum eq  1 }">
           			 <li class="current"><a href="javascript:void(0)">${topic.sortNum}</a></li>
           		</c:when>
           		<c:otherwise>
           			 <li><a href="javascript:void(0)">${topic.sortNum}</a></li>         		
           		</c:otherwise>
           	</c:choose>
          </c:forEach>
          
          
        </ol>
        <p class="onlinetest-statistics">答对：<em>0</em> 题，答错：<strong>0</strong> 题，首正率：<span class="correct">0</span>%，进度：<span class="progress">0</span>%</p>
      </div>
    </div>
  </div>
  <!-- onlinetest -->
  <div id="popup1" class="mylayer-main" style="display: none;">
    <div class="mylayer-hd"></div><span class="mylayer-close"></span>
    <div class="mylayer-bd">
      <div id="mylayer1">
        <div class="tt">答案解析</div>
        <div class="subtt">Answer Analysis</div>
        <div class="ct">
        </div>
      </div>
    </div>
  </div>
  <div id="popup2" class="mylayer-main" style="display: none;">
    <div class="mylayer-hd"></div><span class="mylayer-close"></span>
    <div class="mylayer-bd">
      <div id="mylayer2" class="mylayer-msg">
        <div>考试结束：</div>
        <div>
          <span class="success">恭喜您通过考试，您的分数为 <span class="right">60</span> 分</span>
          <span class="fail">您未通过考试，您的分数为 <span class="wrong">59</span> 分</span>
          ，本次考试的总分为 <span class="totalscore"></span> 分。
        </div>
        <div>
          您的考试用时为 <span class="usetime"></span>，本次考试的总时间为 <span class="totaltime"></span>
        </div>
      </div>
    </div>
    <div class="btns">
      <a href="javascript:void(0)" class="mylayer-close">回顾</a>
      <a href="${path}/appController/index.do">退出考试</a>
    </div>
  </div>
  <div id="popup3" class="mylayer-main" style="display: none;">
    <div class="mylayer-hd"></div><span class="mylayer-close"></span>
    <div class="mylayer-bd">
      <div id="mylayer3" class="mylayer-msg">
        <div>信息：</div>
        <div>
          检测到您有未完成考试，是否继续上次的测试？
        </div>
      </div>
    </div>
    <div class="btns">
      <a href="javascript:void(0)" class="continuelast">继续做题</a>
      <a href="javascript:void(0)" class="mylayer-close">重新考试</a>
    </div>
  </div>
  
  
  
  
  <input id="totalPage" value="${totalPage}" type="hidden">
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
  <script type="text/javascript" src="<%=path %>/plug-in/app/js/onlinetest.js"></script>
  <script type="text/javascript" src="<%=path %>/plug-in/app/js/savetest.js"></script>
  <script type="text/javascript">
    $(function () {
      $('.onlinetest-opennav').click(function() {
        $('.onlinetest-navlist').addClass('active');
        if ($('.onlinetest-navlist').hasClass('active')) {
          $bg=$("<div style='position:fixed;z-index:1000;left:0;right:0;top:0;bottom:0;background:rgba(0,0,0,0.5)'></div>").appendTo($("#onlinetest"));
          $bg.on('click',closeNav);
          $("#onlinetest li").on('click',closeNav);
        }
        function closeNav() {
          console.log(1);
          $('.onlinetest-navlist').removeClass('active');
          $("#onlinetest li").off('click',closeNav);
          $bg.off().remove();
        }
      });
    });
  </script>
</body>
</html>