<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	        
	           <#if result.singalContent??>
	           		
	           		<div class="article">
			           <div class="article-header">
			             <h1 class="article-title">${result.singalContent.title!}</h1>
			
			           </div>
		           
			      		<div class="article-content">
				            ${result.singalContent.content!}
				         </div>
			         </div>
		       <#else> 
		       
		      	  <div class="mod-content">
			         <br> 无相关的记录。
			          
			        			          			          
		          </div>
		      
		      </#if> 
	        
	      </div><#-- mod end -->
	      
	      
	    </div><#--clearfix end-->
    
  </div><#--container end-->

   
  <input id="ctTotalPage" value="${result.ctTotalPage!'-1'}" type="hidden"/>
  <input id="ctCurPage" value="${result.ctCurPage!'-1'}" type="hidden"/>
  <input id="path" value="${path}" type="hidden"/>
  <input id="colId" value="${result.currColumn.colId}" type="hidden"/>

  
  
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/queryPage.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/menu-change.js"></script>


</body>

</html>

