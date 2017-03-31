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
    <link href="${path}/plug-in/web/portals/css/activity/contribute.css" rel="stylesheet" type="text/css" />
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
          <h1 class="contribute-tt">${cityBuilder.activity.activityName}</h1>
          <table class="table-default table1">
          <input id="activityId" value="${cityBuilder.activity.id}" type="hidden">
          <input id="activityTitle" value="${cityBuilder.activity.activityName}" type="hidden">
          <input id="path" value="${path}" type="hidden">
            <thead>
              <tr>
                <th>板块</th>
                <th>内容</th>
                <th>投稿</th>
              </tr>
            </thead>
            <tbody>
            	<#list cityBuilder.banChunkList?sort_by('sortNum') as banChunk>
            		<tr <#if banChunk_index%2=0>
            			class="even"
            		</#if>>
            		<#if (banChunk.children?size=0)>
                		<td>${banChunk.chunkName}</td>
                		<td>${banChunk.chunkMemo}</td>
                		<td><a href="javascript:void(0)"  onclick="contribution(${banChunk.id},'${banChunk.chunkName}')">我要投稿</a></td>
                	<#else>
                		<td>
                  <table class="table-default table-insert">
                    <tr>
                      <td rowspan="${banChunk.children?size}">${banChunk.chunkName}</td>
                      <#list banChunk.children?sort_by('sortNum') as cbanChunk>
                      	<#if cbanChunk_index=0>
                      <td>${cbanChunk.chunkName}</td>
                    </tr>
                    	</#if>
                    	<#if (cbanChunk_index>0) >
                    	<tr>
                      		<td>${cbanChunk.chunkName}</td>
                    	</tr>
                    	</#if>
                    </#list>
                  </table>
                </td>
                <td>
                  <table class="table-default table-insert">
                  
                  <#list banChunk.children?sort_by('sortNum') as cbanChunk>
                    <tr>
                      <td>${cbanChunk.chunkMemo}</td>
                    </tr>
                   </#list>
                    
                  </table>
                </td>
                <td>
                  <table class="table-default table-insert">
                  
                  <#list banChunk.children?sort_by('sortNum') as cbanChunk>
                    <tr>
                      <td><a href="javascript:void(0)" onclick="contribution(${cbanChunk.id},'${banChunk.chunkName}-${cbanChunk.chunkName}')">我要投稿</a></td>
                    </tr>
                   </#list>
                    
                  </table>
                </td>
                	</#if>
              		</tr>
            	</#list>
            </tbody>
          </table>
        </div>
      <!-- mod -->
    </div>
  </div>	
 
	<#include "/common/footer.ftl"/>	
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
	<script type="text/javascript">
	function contribution(id,chunkName){
		 var activityId = $("#activityId").val();
		 var banChunkId=id;
		 var path=$("#path").val();
		 var url = path+"/manuscriptController.do?add&activityId="+activityId+"&banChunkId="+id+"&type="+2;
		 var title="投稿版块:"+chunkName;
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