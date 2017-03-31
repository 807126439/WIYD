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
    <link href="${path}/plug-in/web/portals/css/activity/communication.css" rel="stylesheet" type="text/css" />
  </head>
<body>

<input type="hidden" id="path" value="${path}">
	<#include "/common/head.ftl"/>
 	<div class="container" class="clearfix">
     <div class="mainplaceholder"></div>
    <div id="main" class="clearfix">
    
    
      <div class="wr_communication">
        <div class="title">在线议题</div>
        <div class="content">
          <div class="part1 clearfix">
          
          <#if result ??>
          <input type="hidden" id="comId" value="${result.id}">
          	 <div class="issue">
              <div class="issue-pic">
                <p class="issue-tt">本期议题：</p>
                <p class="issue-title">${result.title}</p>
                <div class="issue-info">
                  <p class="issue-tiem">议题时间：${result.startDate?string('yyyy-MM-dd')}</p>
               	 <p class="issue-originator">议题发起人：${result.sponsor}</p>
                </div>
              </div>
              <div class="issue-footer clearfix">
                <span class="issue-remaintime">剩余时间: ${result.leftDays}天</span>
                <span class="issue-participants" id="participants"></span>
                <span class="issue-footer-r">
               
                  <a href="${path}/issue/${result.id}.htm" class="btn_enterissue">我要评论</a>
                </span>
              </div>
            </div>
            
          
          
          <#else>
          
          	<div class="issue">
              <div class="issue-pic">
                <p class="issue-tt">本期议题：</p>
                <p class="issue-title"><span style="margin-left:120px">暂无议题</span></p>
              </div>      
            </div>
          
          </#if>
          
          
            
      <div class="communicationmod">
             <div class="communicationmod-title">
              <span>往期议题</span>
              <span class="communicationmod-title-r"><a href="#">+MORE</a></span>
            </div>
             <div class="mod-content">
               <ul id="announcementlist">
               
               <#if currentList??>
               
	               <#list currentList as current>
	               		<#if current.id != result.id>
		              <li class="announcement">
	                   <span class="announcement-l"><i class="icon_mod"></i></span>
	                   <div class="announcementaside">
	                     <span class="publicdate">${current.startDate?string('yyyy-MM-dd')}</span>
	                   </div>
	                   <a href="${path}/communication/1/${current.id}.htm" class="announcementcontent">${current.title}</a>
	                   <p class="originator">发起人:${current.sponsor}</p>
	                 </li>
	                 </#if>
	               </#list>
               
               </#if> 
               
              </ul>
             </div>
          </div>  
          
        </div>
        
       
       
      </div>
    </div>
  </div>
  </div>
 	
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript">
	$(function(){
		var comId=$("#comId").val();
		if(comId){		
			$.ajax({
	             type: "post",
	             url: "commentController/countComment.do",
	             data: {comId:comId,type:1},
	             success: function(data){
	             	$("#participants").html("");
					$("#participants").append("参与人数:"+data+"人");
	            }
	         });
		}
				
	})
	</script>
	
	
</body>
</html>