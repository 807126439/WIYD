<#assign ctTotalPage=Page.getTotalPage()>
<!DOCTYPE HTML>
<html>
  	
	<#include "/activity/common/activity-head.ftl"/>

<body>

	<#include "/common/head.ftl"/>
	
	<div class="container clearfix" >
	
	    <div class="mainplaceholder"></div>
	    
	    <div id="main" class="clearfix">
	    
	        <#--左菜单-->
	    	<#include "/activity/common/leftMenu.ftl"/>
	      
	       
	      
	      <div class="mod">
	      	   <#--导航条-->
		       <#include "/activity/common/nav.ftl"/>
	        
		       
		      	  <div class="mod-content">
			          <ul id="announcementlist"> 
			            <#if Page??>
							<#list Page.list as StudyTaskDTO>
				     			 <li class="announcement" title="${StudyTaskDTO.taskName!}">
					              <div class="announcementaside">
					                <span class="publicdate">${StudyTaskDTO.startTime!}</span>
					              </div>
					              <a href="${path}/studyTask/${result.currColumn.colId}.htm?taskId=${StudyTaskDTO.stId}" class="announcementcontent"> 
					              	 <#if StudyTaskDTO.finish>
					              	 <span class="span1" style="color:red">
					              	 	【已完成】
					              	 <#else>
					              	 <span class="span1" style="color:blue">
					              	 	【未完成】
					              	 </#if>
					              </span>
					              ${StudyTaskDTO.taskName!}
					              </a>
					            </li>
				      		</#list>
				      	</#if>	
			          </ul>
			          
			          <div class="footerbar clearfix">         
			            <div class="paging" id="page"></div> 
			            
			          </div>
			          
			          
		        </div>
		      
	        
	      </div><#-- mod end -->
	      
	      
	    </div><#--clearfix end-->
    
  </div><#--container end-->

   
  <input id="ctTotalPage" value="${ctTotalPage!'-1'}" type="hidden"/>
  <input id="ctCurPage" value="${Page.currentPage!'-1'}" type="hidden"/>
  <input id="path" value="${path}" type="hidden"/>
  <input id="colId" value="${result.currColumn.colId}" type="hidden"/>

  
  
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/queryPageStudyTask.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/menu-change.js"></script>


</body>

</html>