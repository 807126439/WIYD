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
    <link href="${path}/plug-in/web/portals/css/activity/issue.css" rel="stylesheet" type="text/css" />
    
  </head>
<body>
	<#include "/common/head.ftl"/>	
  <div class="container" class="clearfix">
    <div class="mainplaceholder"></div>
    <div id="main" class="clearfix">
      
      
      <div class="wr_issue">
      <#-- 标题导航 -->
        <div class="title">
          <div class="loc clearfix">
            <span>当前位置：</span>
            <ol>
              <li class="noactive"><a href="${path}/communication.do">互动交流</a></li>
              <li class="active">本期议题</li>
            </ol>
          </div>
        </div>
        
 
        
        <div class="content">
            <#-- 主题相关 -->
          <div class="part1 clearfix">
          
          
            <div class="lef">
              <div class="lef-title">
                <span class="issue-tt">本期议题：</span>
                <span class="issue-title">${result.title}</span>
                <div class="issue-info">
                  <span class="issue-time">时间：${result.startDate?string('yyyy-MM-dd')}</span>
                  <span class="issue-originator">发起人：${result.sponsor}</span>
                </div>
              </div>
              <div class="lef-content">
                <p>${result.content}</p>           
              </div>
              <div class="clearfix">
              
                <span class="lef-evaluate">  
                
               
                
       
                    <span class="digg">
                  	<span class="midd">${result.love!}</span>
                  	<span class="midd digg-tip">赞</span>
                  	<span class="digg-tip2">+1</span>
                  	<span class="midd iconwr">
                  		<i class="icon_evaluate"></i>
                  	</span>
                  </span>	
                  <span class="comcout"><span class="midd" id="pNum">0</span><span class="midd iconwr"><i class="icon_evaluate"></i></span></span>
                </span>  
                         
              </div>
            </div>
            
        	<div class="comment_container" id="commentContainer">
              <div class="redtitle">评论列表
              	<ul class="sort_wrap">
                  <li id="1">最热</li>
                  <li id="2" class="active">最新</li>
                </ul>
              </div>
              <ul class="commentlist" id="commentlist"></ul>        
            </div> 
            
            
           <div class="page" id="page"></div>      
              
            <div class="lef-editor">
              <div class="lef-editor-t"><span>我要评论</span></div>
              <div class="lef-editor-ipt">
           		<form id="commmentForm"  method="post">
                 	<input id="comId"  name="comId" value="${result.id}" type="hidden">
                 	<input id="parentId" name="parentId" type="hidden">
                 	<input id="userId" name="userId" type="hidden">
                  	<script id="editor" type="text/plain" style="width:100%;height:150px;"></script>         	                             
                </form> 
              </div>
              <div class="lef-editor-f">
                <a href="javascript:void(0);" onclick="submitComment()" class="btn_publish">发表</a>
              </div>
            </div>
             
            
          </div>
        </div>
      </div>
    </div>
	</div>	
	

	
 	<input id="path" value="${path}" type="hidden">
 	<input id="totalPage" value="${totalPage}" type="hidden">
 	<input id="isLove" value="${result.isLove}" type="hidden">
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.config.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/ueditor/1.4.3.3/ueditor.all.js"></script>
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/issue.js"></script> 
	
	

		
</body>
</html>