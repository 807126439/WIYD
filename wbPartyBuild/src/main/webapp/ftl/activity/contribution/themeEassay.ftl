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
      
    
        <div class="mod-content">
          <div class="contentwrapper" style="line-height:40px">
          <center><span style="font-size:20px;color:red">${eassayActivity.activityName}</span></center>
          <p> ${eassayActivity.content}</p>

			<p style="font-size:18px;color:red">【活动时间】:</p>
			${eassayActivity.startDate} - ${eassayActivity.endDate}
		
			
         
         
         <center><a href="javascript:void(0)" onclick="contribution()"><img src="${path}/plug-in/web/portals/image/contribution.jpg"></a></center>
         
        </div>
      </div>
      <input id="activityId" value="${eassayActivity.id}"  type="hidden"> 
 	  <input id="activityTitle" value="${eassayActivity.activityName}" type="hidden"> 
 	<input id="path" value="${path}" type="hidden"> 
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
		 var url = path+"/manuscriptController.do?add&activityId="+activityId+"&type="+3;
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