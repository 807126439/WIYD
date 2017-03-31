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
	
	<title>城建先锋</title>
		
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
              <#if result.parColumnTitle?? >
              	<li class="noactive"><a href="${path}/subject/${result.parColumnId}.htm">${result.parColumnTitle!}</a></li>
              </#if>
              
              <li class="active"><a href="${path}/subject/${result.columnId}.htm">${result.columnTitle!}</a></li>
            </ol>
          </div>
        </div> 
        
        <div class="article">
          <div class="article-header">
            <h1 class="article-title">${result.title!}</h1>
            <span class="article-info">
              发布时间:<span class="article-pdate">${result.createTime!}</span>
              发布组织:<span class="article-pauthor">${result.author!}</span>
            </span>
          </div>
          
          <div class="article-content">
            ${result.content!}
          </div>
        
          <div class="article-footer">		  
		 	 <#if result.pre??>
		 		<p class="article-prev"><a href="${path}/article/${result.pre.id}.htm">上一篇：${result.pre.title!}</a></p>
			<#else>
	            <p class="article-prev"><a href="#">上一篇：无</a></p>
			</#if>
			<#if result.next??>
		 		<p class="article-prev"><a href="${path}/article/${result.next.id}.htm">下一篇：${result.next.title!}</a></p>
			<#else>
	            <p class="article-prev"><a href="#">下一篇：无</a></p>
			</#if>
          </div>
          
        </div>
        
      </div>
    </div>
  </div>


<#include "/common/footer.ftl"/>
<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/index/MSClass.js"></script> 

</body>
</html>

