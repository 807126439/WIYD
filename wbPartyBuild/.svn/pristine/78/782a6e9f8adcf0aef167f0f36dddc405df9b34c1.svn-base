<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <meta http-equiv="X-UA-Compatible" content="IE=8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link href="${path}/plug-in/web/portals/css/common/reset.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/common/common.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/common/base.css" rel="stylesheet" type="text/css" />	
	<link href="${path}/plug-in/web/portals/css/activity/mod-dymc.css" rel="stylesheet" type="text/css" />
    <link href="${path}/plug-in/web/portals/css/activity/mod-osd.css" rel="stylesheet" type="text/css" />
    <link href="${path}/plug-in/web/portals/css/activity/memberlist.css" rel="stylesheet" type="text/css" />
  </head>
<body>
	<#include "/common/head.ftl"/>
 	<#assign x=1/>
 	<!-- navBer -->
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
          <div class="contentwrapper">
          
          
            <#list department.children?sort_by('sortNum') as cdepartment>
          <#if cdepartment.children?size = 0>
          	<div class="part${x}">
            <#assign x=x+1/>
              <div class="level_1">
                <div class="nodewrapper">
                  <div class="node">${cdepartment.departName}</div>
                  <div class="branch parenthesis6"></div>
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
            <!-- part1 -->
          </#if>
          </#list>
            
            
            <#list department.children?sort_by('sortNum') as cdepartment>
            <#if cdepartment.children?size gt 0>
            <div class="part${x}">
            <#assign x=x+1/>
              <ul class="contentlist">
                <li class="d_1">
                  <div class="list-t">
                    <div class="list-t-wr">
                      <p class="Belong">${cdepartment.departName}</p>
                    </div>
                    <div class="simwrapper"><i class="icon_sim"></i></div>
                  </div>
                  <div class="list-c">
                  <#list cdepartment.children?sort_by('sortNum') as ccdepartment>
                    <div class="linewr">
                      <div class="line-left">${ccdepartment.departName}：</div>
                      <div class="line-right">
                      	<#list ccdepartment.relations?sort_by('sortNum') as relation>
                     		 ${relation.userName}
                     		 <#if relation_has_next>
                     		 	、
                     		 </#if>
                     	 </#list>
                      </div>
                    </div>
                   </#list>
                  </div>
                </li>
              </ul>
            </div>
            </#if>
            </#list>
            
            
          </div>
        </div>
      </div>
      <!-- mod -->
    </div>
  </div>
 	
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/index/MSClass.js"></script> 

</body>
</html>