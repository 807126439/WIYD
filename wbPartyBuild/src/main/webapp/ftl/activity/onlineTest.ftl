<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link href="${path}/plug-in/web/portals/css/common/reset.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/common/common.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/common/base.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/h-ui/lib/layer/2.4/skin/layer.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/study/onlinetest.css" rel="stylesheet" type="text/css" />
	
	<title>城建先锋</title>
	
<body>
	
	<#include "/common/head.ftl"/>

  <!-- navBer -->
  <div class="container" class="clearfix">
    <div class="mainplaceholder"></div>
    <div id="main" class="clearfix">
      <div class="mod" style="margin:auto;">
        	<#--导航条-->
		    <#include "/activity/common/nav.ftl"/>
        
        
        <div id="onlinetest">
           <input id="testTime" value="${testDTO.testTime}" type="hidden">
 		   <input id="path" value="${path}" type="hidden">
  		   <input id="taskId" value="${taskId}" type="hidden">
  		   
          <h2 class="onlinetest-tt">${testDTO.paperName}</h2>
          <div class="onlinetest-subtt">共<strong>${testDTO.topicSize}</strong>题&emsp;&emsp;总分：<strong>${testDTO.totalScore}</strong>分&emsp;&emsp;合格分数线：<strong>${testDTO.passScore}</strong>分&emsp;&emsp;考试时间：<strong>${testDTO.testTime}</strong>
          </div>

          <div class="onlinetest-controll">
            		考试剩余时间：<strong></strong>
            <div class="btngroup">
              <a class="btn-white btn-their" href="javascript:void(0)">交卷</a><a class="btn-white btn-pause" href="javascript:void(0)"><span>暂停</span><span>开始</span></a>
            </div>
          </div>
                  
          <div class="onlinetest-ct">
            <div class="questionlist">
           <#list testDTO.topicList as topic> 
              
              <#if topic.sortNum == 1>
              	
              	<#if topic.topicType ==1>
              		 <div class="question singlechoice current" data-qid="${topic.topicId}" data-score="${topic.score}">
              	<#elseif topic.topicType ==2>
              		<div class="question morechoice current" data-qid="${topic.topicId}" data-score="${topic.score}">
              	<#elseif topic.topicType ==3>
              		<div class="question  trueorfalse current" data-qid="${topic.topicId}" data-score="${topic.score}">            		
              	<#elseif topic.topicType ==4>
              		 <div class="question gapfill current" data-qid="${topic.topicId}" data-score="${topic.score}">
              	</#if>
              
              
              <#else>
              	 <#if topic.topicType ==1>
              		 <div class="question singlechoice" data-qid="${topic.topicId}" data-score="${topic.score}">
              	 <#elseif topic.topicType ==2>
              		<div class="question morechoice" data-qid="${topic.topicId}" data-score="${topic.score}">
              	<#elseif topic.topicType ==3>
              		<div class="question trueorfalse" data-qid="${topic.topicId}" data-score="${topic.score}">            		
              	<#elseif topic.topicType ==4>
              		 <div class="question gapfill" data-qid="${topic.topicId}" data-score="${topic.score}">
              	</#if>
              </#if>
              
               <p class="question-title">${topic.sortNum}.【${topic.topicTypeName}】${topic.questionText}</p>
             
              
                  <#if topic.topicType == 4> 
                  	 <ol class="anwsergroup">
                 	 <li><input type="text" autocomplete="off"></li>                  
                	</ol>
                	 <a class="btn-white btn-submit" href="javascript:void(0)">提交答案</a>   
		           <#else>
		           	 	<ol class="question-options">
		                 <#if topic.optionA??><li>${topic.optionA!}</li></#if> 
		                 <#if topic.optionB??><li>${topic.optionB!}</li></#if> 
		                 <#if topic.optionC??><li>${topic.optionC!}</li></#if> 
		                 <#if topic.optionD??><li>${topic.optionD!}</li></#if> 
		                 <#if topic.optionE??><li>${topic.optionE!}</li></#if>          
		                 <#if topic.optionG??><li>${topic.optionG!}</li></#if>                
		                </ol> 
		                <a class="btn-white btn-submit" href="javascript:void(0)">提交答案</a>
                   </#if> 
                         
              </div>    
              </#list> 
              
                 
           </div>
           
           <div class="onlinetest-ft">
              <p class="onlinetest-guide">
              	<a href="javascript:void(0)" class="onlinetest-prev btn-white">上一题</a>
              	<a href="javascript:void(0)" class="onlinetest-next btn-white">下一题</a>
              </p>
              <ol class="onlinetest-navlist clearfix">                 
            	<#list testDTO.topicList as topic> 
	            	<#if topic.sortNum == 1>
	            		<li class="current"><a href="javascript:void(0)">${topic.sortNum}</a></li>
	            	<#else>
	            		<li><a href="javascript:void(0)">${topic.sortNum}</a></li>	            		
	            	</#if>
               	 	
                </#list>              
              </ol>
              <p class="onlinetest-statistics">共${testDTO.topicSize}题&emsp;&ensp;答对：<em>0</em> 题&emsp;&ensp;答错：<strong>0</strong> 题&emsp;&ensp;首正率：<span class="correct">0</span>%&emsp;&ensp;进度：<span class="progress">0</span>%</p>
            </div>
               
          </div>
        </div>
        
        
        <!-- onlinetest -->
      </div>
    </div>
  </div>
  <!--container-->
  	<#include "/common/footer.ftl"/>
  	
  	
  	
  	
  <!-- footer-p -->
 <div id="popup1" class="mylayer-main" style="display: none;">
    <div class="mylayer-hd"></div><span class="mylayer-close">×</span>
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
    <div class="mylayer-hd"></div><span class="mylayer-close">×</span>
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
      <a href="javascript:history.go(-1)">退出考试</a>
    </div>
  </div>
  <div id="popup3" class="mylayer-main" style="display: none;">
    <div class="mylayer-hd"></div><span class="mylayer-close">×</span>
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
  

  <script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript" src="${path}/plug-in/h-ui/lib/layer/2.4/layer.js"></script>
  <script type="text/javascript" src="${path}/plug-in/web/scripts/study/onlineTest/onlinetest.js"></script>
  <script type="text/javascript" src="${path}/plug-in/web/scripts/study/onlineTest/savetest.js"></script>
</body>

</html>