<#assign ctTotalPage=Page.getTotalPage()>
<!DOCTYPE HTML>
<html>
      <link rel="stylesheet" type="text/css" href="${path}/plug-in/h-ui/lib/laypage/1.2/skin/laypage.css">
	<#include "/activity/common/activity-head.ftl"/>

<body>

	<#include "/common/head.ftl"/>
	
	<div class="container clearfix" >
	
	    <div class="mainplaceholder"></div>
	    
	    <div id="main" class="clearfix">
	    
	    	<#include "/activity/common/leftMenu.ftl"/>
	     
	      <div class="mod">
	      
		       <#include "/activity/common/nav.ftl"/>
 				<div class="mod-content">
			          <ul id="announcementlist"> 
			            <#if Page??>
							<#list Page.list as Studydata>
				     			 <li class="announcement" title="${Studydata.dataName!}">
					              <div class="announcementaside">
					                <span class="publicdate">${Studydata.createTime!}</span>
					              </div>
					              <a href="${path}/LearningVideo/${result.currColumn.colId}.htm?studyDataId=${Studydata.sdId}" class="announcementcontent"> 
					             	 ${Studydata.dataName!}
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
  <input id="ctCurPage" value="${result.ctCurPage!'-1'}" type="hidden"/>
  <input id="path" value="${path}" type="hidden"/>
  <input id="colId" value="${result.currColumn.colId}" type="hidden"/>
  
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/menu-change.js"></script>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
   <script type="text/javascript" src="${path}/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/queryLearningVideoList.js"></script>
</body>

</html>

