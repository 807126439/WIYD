<#macro showData data>
	<#list data as col>
  		 <#if col.childs?? && (col.childs?size>0) >	
  		 	<li class="sidemenu-menu"><span class="sidemenu-tt"><i class="icon_fold"></i>
  		 	<#if col.linkUrl??>
            	<a href="${col.linkUrl}">							            	
            <#else>
            	 <a href="${request.contextPath}/subject/${col.id}.htm">
            </#if>	
  		 	${col.title!}
  		 	</a>
  		 	</span> 		 	
 		 	<ul class="sidemenu-list">
 		 	    <@showData data=col.childs />   		 	
 		 	</ul>
 		 	</li>		 	
  		 <#else>
  		   <li class="sidemenu-item">
  		 	<#if col.linkUrl??><a href="${col.linkUrl}"><#else><a href="${request.contextPath}/subject/${col.id}.htm"></#if>	
  		 	${col.title!}</a>
  		 	</li>
  	     </#if>
  	
  	</#list>

</#macro>



<!DOCTYPE html>
<html>

<head>

  <title></title>
</head>

<body>
  <ul class="sidemenu-list websitenav-menu" id="websitenav-menu">   
  	  <@showData data=data />     
  </ul>
</body>

</html>
