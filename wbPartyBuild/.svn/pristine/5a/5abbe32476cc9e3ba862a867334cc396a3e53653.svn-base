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
  </head>
<body>
	<#include "/common/head.ftl"/>
 	<#assign x=1/>
 	<#-- 用于统计出现的人数 -->
 	<#assign all=0/>
 	<#-- 用于统计在册的人数 -->
 	<#assign enrolled=0/>
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
          <h1 class="contenttitle">${result.currColumn.title}</h1>
          <ul class="contentlist">
          <#list department.children?sort_by('sortNum') as cdepartment>
          	<#-- 初始化出现的人数 -->
 			<#assign all=0/>
 			<#-- 初始化在册的人数 -->
 			<#assign enrolled=0/>
 			
            <li class="d_${x}">
            <#assign x=x+1>
              <div class="list-t">
                <div class="list-t-wr">
                  <p class="Belong">${cdepartment.departName}</p>
                  <p class="persons">（24人，在册20人）</p>
                </div>
                <div class="simwrapper"><i class="icon_sim"></i></div>
              </div>
              <div class="list-c">
              	<#list cdepartment.children?sort_by('sortNum') as ccdepartment>
                <div class="linewr">
                  <div class="line-left">${ccdepartment.departName}：</div>
                  <div class="line-right">
                  <#list ccdepartment.relations?sort_by('sortNum') as relation>
                  	<#-- 出现的人数自增1 -->
 					<#assign all=all+1/>
                  	<#if relation.status=0>
                  		<span class="undl">${relation.userName}</span>
                  	<#else>${relation.userName}
          				<#-- 在册的人数自增1 -->
                  		<#assign enrolled=enrolled+1/>
                  	</#if>
                  	<#if relation_has_next>、</#if>
                  </#list>
                  	</div>
                </div>
                </#list>
              </div>
              <#-- 用于保存人数 -->
              <div class="record" all=${all!0} enrolled=${enrolled!0}>
            </li>
            </#list>
          </ul>
        </div>
      </div>
    </div>
    <!-- mod -->
    </div>
  </div>
 	
 	
 	
 	
 	
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript">
	$(function(){
		$(".contentlist li").each(function(){
			var all=$(this).children(".record").attr("all");
			var enrolled=$(this).children(".record").attr("enrolled");
			$(this).find(".persons").html("（"+all+"人，在册"+enrolled+"人）");
		});
	});
	</script>
	
</body>
</html>