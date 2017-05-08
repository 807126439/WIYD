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
			          <ul id="announcementlist">
		
			          </ul>
			          
			          <div class="footerbar clearfix">         
			            <div class="paging" id="page"></div> 
			            
			          </div>
			          
			          
		        </div>
		      
		      </#if> 
	        
	      </div><#-- mod end -->
	      
	      
	    </div><#--clearfix end-->
    
  </div><#--container end-->


  <input id="totalPage" value="${totalPage}" type="hidden"/>
  <input id="path" value="${path}" type="hidden"/>
  <input id="columnId" value="${columnId}" type="hidden"/>


  
  
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script> 

	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/menu-change.js"></script>
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/queryPageThemeEassay.js"></script>




</html>

