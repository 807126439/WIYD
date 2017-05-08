<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

	<#include "/activity/common/activity-head.ftl"/>
	<link href="${path}/plug-in/web/portals/css/study/studyTask.css" rel="stylesheet" type="text/css" />
<body>
	<#include "/common/head.ftl"/>
  <div class="container" class="clearfix">
    <div class="mainplaceholder"></div>
    <div id="main" class="clearfix">
    
	        <#--左菜单-->
	    	<#include "/activity/common/leftMenu.ftl"/>
	    	
      <div class="mod">
      
	      	   <#--导航条-->
		       <#include "/activity/common/nav.ftl"/>
		       
        <div class="mod-content">
          <div class="learningtask">
            <h1>学习任务</h1>
            <p><span class="red">任务名称：</span>${StudyTask.taskName}</p>
            <p><span class="red">任务编号：</span>${StudyTask.taskNum}</p>
            <p><span class="red">开始时间：</span>${StudyTask.startTime?date}</p>
            <p><span class="red">结束时间：</span>${StudyTask.endTime?date}</p>
            <p><span class="red">任务说明：</span>${StudyTask.taskMemo}</p>
            <p><span class="red">资料列表：</span></p>
            <ul class="datalist">
            
              	<#if StudyTask.studyData??>
              		<#list StudyTask.studyData as studyData>
              			<li><a href="${path}/LearningVideo/53.htm?studyDataId=${studyData.id}">${studyData.dataName}</a></li>
              		</#list>
            	</#if>
              													
            </ul>
            <p><span class="red">测试试卷：</span><a href="${path}/onlineTest/${columnId}.htm?testPaperId=${StudyTask.stId}">${StudyTask.paperName!}</a></p>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--container-->
  <#include "/common/footer.ftl"/>
  <!-- footer-p -->
  <script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript">
    $(function(){
      $('.sidemenu-tt .icon_fold').click(toggleMenu);
      $('.sidemenu-item').click(addActive);
    });
    //伸缩菜单
    function toggleMenu(){
      $(this).closest('.sidemenu-menu').delay().toggleClass('active');
    }
    function addActive(){
      $('.sidemenu-item').removeClass('active');
      $(this).addClass('active');
    }
  </script>
</body>

</html>
