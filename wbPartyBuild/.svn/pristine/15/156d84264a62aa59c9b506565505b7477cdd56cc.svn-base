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
              <li class="noactive"><a href="${path}/portals.do">廉政微电影</a></li>
              <li class="active">${result.title}</li>
            </ol>
          </div>
        </div> 
        <div class="article" >
        
          <div class="article-header">
            <h1 class="article-title"></h1>
          </div>        
          
          <div class="article-content">
             <center><div id="play" ></div></center>           	
         	 ${result.content}         
         	
	          	<div id="a1"></div>
				<div id="nowTime"></div>
	
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

<input type="hidden" value="${mPath}" id="mPath">
<#include "/common/footer.ftl"/>
<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${path}/plug-in/h-ui/lib/ckplayer/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript">	
		  var flashvars={
		    f:$("#mPath").val(),
		    c:0,
		    p:2,
		    b:0,
		    loaded:'loadedHandler'
		  };

		 var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
		CKobject.embedSWF('../plug-in/h-ui/lib/ckplayer/ckplayer.swf','a1','ckplayer_a1','600','400',flashvars,params);	
</script>
</body>
</html>