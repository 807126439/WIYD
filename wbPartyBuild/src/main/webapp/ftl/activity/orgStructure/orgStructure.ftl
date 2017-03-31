<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	 <meta http-equiv="X-UA-Compatible" content="IE=8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link href="${path}/plug-in/web/portals/css/common/reset.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/common/base.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/activity/mod-osd.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/common/common.css" rel="stylesheet" type="text/css" />
  </head>
<body>
	<#include "/common/head.ftl"/>
	<#assign x=1/>
	 <div class="container" class="clearfix">
    <div class="mainplaceholder"></div>
    <div id="main" class="clearfix">
      <#--左菜单-->
		<#include "/activity/common/leftMenu.ftl"/>
      <!-- sidemenu -->
      <div class="mod">
        <#--导航条-->
      <#include "/activity/common/nav.ftl"/>
        <div class="mod-content">
        <#list department.children?sort_by('sortNum') as cdepartment>
        <#if cdepartment.children?size gt 0>
        
          <div class="contentwrapper">
            <div class="level_1">
              <div class="nodewrapper node_wr_${x}">
              <#assign x=x+1>
                <div class="node">
                ${cdepartment.departName}
                  <ul class="node-info">
                  <#list cdepartment.relations?sort_by('sortNum') as relation>
                    <li>${relation.jobName}：${relation.userName}</li>
                  </#list>
                  </ul>
                </div>
                <div class="branch branch4"></div>
              </div>
            </div>
            <!-- level_1 -->
            <div class="level_2">
            <#list cdepartment.children?sort_by('sortNum') as ccdepartment>
            
            	<div class="nodewrapper node_wr_${x}">
            	<#assign x=x+1>
                <div class="node">${ccdepartment.departName}
                  <ul class="node-info">
                   <#list ccdepartment.relations?sort_by('sortNum') as relation>
                    <li>${relation.jobName}：${relation.userName}</li>
                  </#list>
                  </ul>
                </div>
                <div class="branch parenthesis2"></div>
              </div>
            </#list>
            
            </div>
            <!-- level_2 -->
            <div class="level_3">
            <#list cdepartment.children?sort_by('sortNum') as ccdepartment>
              <div class="nodewrapper">
              <#if ccdepartment.children?size gt 0>
              	<#list ccdepartment.children?sort_by('sortNum') as cccdepartment>
                <div class="nodewrapper node_wr_${x}">
                	<#assign x=x+1>
                  <div class="node">${cccdepartment.departName}
                    <ul class="node-info">
                        <#list cccdepartment.relations?sort_by('sortNum') as relation>
                        	<#if relation_index=0>
                   		 		<li>${relation.jobName}：${relation.userName}</li>
                   		 	</#if>
                  		</#list>
                    </ul>
                  </div>
                </div>
                </#list>
               </#if> 
              </div>
              </#list>
              </div>
            </div>
            <!-- level_3 -->
          </div>
          <#else>
          <div class="tree-1-3">
              <div class="level_1">
                <div class="nodewrapper">
                  <div class="node" style="width:184px;">${cdepartment.departName}</div>
                  <div class="branch parenthesis3"></div>
                </div>
              </div>
              <div class="level_2">
              	<#list cdepartment.relations?sort_by('sortNum') as relation>
                  <div class="nodewrapper">
                    <div class="node">${relation.jobName}：${relation.userName}</div>
                  </div>
                </#list>  
              </div>
            </div>
          </#if>
          </#list>
            
        </div>
      </div>
      <!-- mod -->
    </div>

<#include "/common/footer.ftl"/>
<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/osd.js"></script> 

</body>
</html>