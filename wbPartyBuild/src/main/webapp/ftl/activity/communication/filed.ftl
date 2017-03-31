<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	 <meta http-equiv="X-UA-Compatible" content="IE=8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link href="${path}/plug-in/web/portals/css/common/reset.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/common/base.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/common/common.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/article/article.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${path}/plug-in/h-ui/lib/laypage/1.2/skin/laypage.css">
	
	<title>城建先锋</title>
	 <style type="text/css">
	    .textcontainer{font-family: 'Microsoft YaHei';line-height:1.8;font-size:16px;}
	    .textcontainer p{margin:11px 0;letter-spacing:2px;padding-top:10px;padding-left:3em;text-indent:-3em;}
	    .textcontainer .sub{padding-top:0px;padding-left:5em;}
  	</style>
		
  </head>

<body>
<#include "/common/head.ftl"/>
	 <div class="maincontainer">
    <div class="mainplaceholder"></div>
    <div id="main" class="clearfix">
      <div class="articlecontainer">
        <div class="informationbar">
          <div class="loc clearfix">
            <span>当前位置：</span>
            <ol>		 
              <li class="noactive"><a href="${path}/portals.do">首页</a></li>          
              	<li class="noactive"><a href="#">互动交流</a></li>
             <li class="active"><a href="#">${filed.title}</a></li> 
            </ol>
          </div>
        </div> 
        <div class="article">
          <div class="article-header">
            <h1 class="article-title">${filed.title}</h1>
            <span class="article-info">
              发布时间:<span class="article-pdate">${filed.startDate?string('yyyy-MM-dd')}</span>
              发起人:<span class="article-pauthor">${filed.sponsor}</span>
            </span>
          </div>
          <div class="article-content">
          	${filed.content}
          	
          	<div class="textcontainer" id="comment">
          	
          		
          	</div>
          	
          <div class="page" id="page"></div>  
          </div>
          <div class="article-footer">		  
		
	    	    
	       <#if filed.pre??>
		 		<p class="article-prev"><a href="${path}/communication/2/${filed.pre.id}.htm">上一篇：${filed.pre.title}</a></p>
			<#else>
	            <p class="article-prev"><a href="#">
	            
	            上一篇：无</a></p>
			</#if>
			<#if filed.next??>
		 		<p class="article-prev"><a href="${path}/communication/2/${filed.next.id}.htm">下一篇：${filed.next.title}</a></p>
			<#else>
	            <p class="article-prev"><a href="#">下一篇：无</a></p>
			</#if>
		
          </div>
        </div>
      </div>
    </div>
  </div>

	<input id="totalPage" value="${filed.totalPage}" type="hidden">   
	<input id="comId" value="${filed.id}" type="hidden">   
	<input id="path" value="${path}" type="hidden">   
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/queryFiled.js"></script> 
</body>
</html>

