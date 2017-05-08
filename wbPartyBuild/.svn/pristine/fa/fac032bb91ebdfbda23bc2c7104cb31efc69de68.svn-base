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
    <link href="${path}/plug-in/web/portals/css/activity/memberlist.css" rel="stylesheet" type="text/css" />
  </head>
<body>
	<#include "/common/head.ftl"/>
 		
	<div class="container" class="clearfix">
    <div class="mainplaceholder"></div>
    <div id="main" class="clearfix">
 
      
        <#--左菜单-->
	    <#include "/activity/common/leftMenu.ftl"/>
	      
	      <div class="mod">
	      	   <#--导航条-->
		 <#include "/activity/common/nav.ftl"/>
      
      <#if photoActivity??>
        <div class="mod-content">
          <div class="contentwrapper" style="line-height:40px">
          <center><span style="font-size:20px;color:red">${photoActivity.activity.activityName}</span></center>
          <p> ${photoActivity.activity.content}</p>

			<p style="font-size:18px;color:red">【参赛时间】:</p>
			${photoActivity.activity.startDate} - ${photoActivity.activity.endDate}
			
			<#if photoActivity.activityRuleList?size gt 0 >
	   		<p style="font-size:18px;color:red">【活动规则】:</p>
			<#list photoActivity.activityRuleList as ruleList>
				  <div>${ruleList.num}.${ruleList.content}</div>		
			</#list>
			</#if>
			
			<#if photoActivity.awardsSettingList?size gt 0>
	        <p style="font-size:18px;color:red">【奖项设置】:</p>
			<#list photoActivity.awardsSettingList as settingList>
				  <div>${settingList.awardsName}(${settingList.amount}位):${settingList.prize}</div>		
			</#list>
	        </#if>
         
         
         <center><a href="javascript:void(0)" onclick="contribution()"><img src="${path}/plug-in/web/portals/image/contribution.jpg"></a></center>
         
        </div>
      </div>
      <input id="activityId" value="${photoActivity.activity.id}" type="hidden"> 
 	  <input id="activityTitle" value="${photoActivity.activity.activityName}" type="hidden"> 
 	  <input id="path" value="${path}" type="hidden">
      <#else>
      
      	<center><span style="font-size:20px;color:red">暂无活动,敬请期待</span></center>
      </#if>
      <!-- mod -->
    </div>
  </div>	

 	
 	
	<#include "/common/footer.ftl"/>	
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
	<script type="text/javascript">
	function contribution(){
		 var activityId = $("#activityId").val();
		 var path=$("#path").val();
		 var url = path+"/manuscriptController.do?add&activityId="+activityId+"&type="+1;
		 var title=$("#activityTitle").val();
		 var w=820;
		 var h=600;
		 
		layer.open({
				type: 2,
				area: [w+'px', h +'px'],
				fix: false,
				shade:0.4,
				title: title,
				content: url
		 });
	}
	
	</script>
</body>
</html>