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
			            <#if Page.list??>
							<#list Page.list as filedCommunication>
				     			 <li class="announcement" title="${filedCommunication.title!}">
					              <div class="announcementaside">
					                <span class="publicdate">${filedCommunication.startDate?date}</span>
					              </div>
					             
					              
					              <#if filedCommunication.status == 1>
					              	<a href="${path}/communication/1/${filedCommunication.id}.htm" class="announcementcontent">
					              <#else>
					              	<a href="${path}/communication/2/${filedCommunication.id}.htm" class="announcementcontent">
					              </#if>
					              	 
					              	 <#if filedCommunication.status == 1>
					              	 <span class="span1" style="color:red">
					              	 	【活跃】
					              	 <#else>
					              	  <span class="span1" style="color:blue">
					              	 	【归档】
					              	 </#if>
					              	 </span>
					              ${filedCommunication.title!}</a>
					            </li>
				      		</#list>
			          </ul>
			          
			          <div class="footerbar clearfix">         
			            <div class="paging" id="page"></div> 
			            
			          </div>
			          
			          
		        </div>
		      
		      </#if> 
	        
	      </div><#-- mod end -->
	      
	      
	    </div><#--clearfix end-->
    
  </div><#--container end-->

   
  <input id="ctTotalPage" value="${ctTotalPage!'-1'}" type="hidden"/>
  <input id="ctCurPage" value="${result.ctCurPage!'-1'}" type="hidden"/>
  <input id="path" value="${path}" type="hidden"/>
  <input id="colId" value="${result.currColumn.colId}" type="hidden"/>

  
  
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/queryPageCommunication.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/menu-change.js"></script>


</body>

</html>

