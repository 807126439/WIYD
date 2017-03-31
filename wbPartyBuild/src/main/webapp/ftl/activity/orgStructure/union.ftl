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
    <link href="${path}/plug-in/web/portals/css/activity/mod-osd.css" rel="stylesheet" type="text/css" />
    <link href="${path}/plug-in/web/portals/css/activity/mod-osd-ex2.css" rel="stylesheet" type="text/css" />

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
          <h1 class="content-tt">东莞市城建工程管理局工会委员会组织架构</h1>
          <div class="contentwrapper">
          
          <#list department.children?sort_by('sortNum') as cdepartment>
            <div class="part${x}">
            <#assign x=x+1/>
              <div class="level_1">
                <div class="nodewrapper">
                  <div class="node">${cdepartment.departName}</div>
                  <div class="branch parenthesis3"></div>
                </div>
              </div>
              <div class="level_2">
              
              	<#list cdepartment.relations as relation>
              		<#if relation_index lt 1>
              			<div class="nodewrapper">
                  			<div class="node">
                 			 ${relation.jobName}：${relation.userName}
                 	<#elseif relation.jobName=cdepartment.relations[relation_index-1].jobName>
                 		、${relation.userName}
                 	<#else>
                 		</div></div>
                 		<div class="nodewrapper">
                  			<div class="node">
                 			 ${relation.jobName}：${relation.userName}
              		</#if>
              		
              	</#list>
              	</div></div>
              
              	
                </div>
                </div>
            </#list>
            <!-- part1 -->
          </div>
        </div>
      </div>
      <!-- mod -->
    </div>
  </div>
 
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>	
</body>
</html>