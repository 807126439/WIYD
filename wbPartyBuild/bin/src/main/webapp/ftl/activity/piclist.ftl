<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<title>城建局智慧党建</title>
	<link href="${path}/plug-in/web/portals/css/common/reset.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/common/common.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/common/base.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/activity/piclist.css" rel="stylesheet" type="text/css" />
</head>

<body>
<#include "/common/head.ftl"/>

	<div class="container" class="clearfix">
		<div class="mainplaceholder"></div>
			<div id="main" class="clearfix">
			  <div class="mod" style="margin:0">
			    <h2 class="mod-title"> <i class="icon_mod"></i>
			      <div class="mod-loc">
			        <div class="loc clearfix"> <span>当前位置：</span>
			          <ol>
			            <li class="noactive"><a href="${path}/portals.do">首页</a></li>
			            <li class="active"><a href="#">${result.currColumn.title}</a></li>
			          </ol>
			        </div>
			      </div>
			      ${result.currColumn.title}</h2>

    <div class="mod-content">   
      <div class="contentwrapper" id="contentwrapper">
        	 <#list result.contentList as pic>
		           <div class="box">
		              <a href="${path}/article/${pic.id}.htm"><img class="pic-img" src="${pic.pattern!}"></a>
		              <div class="pic-text">${pic.title}</div>
		          </div>           
	          </#list>       
 		</div>      
     </div>
      
     
      <div class="footerbar clearfix">
          
            <div class="paging" id="page">
         		    
            </div> 
          </div>


	</div>
	</div>
	</div>
  <input id="ctTotalPage" value="${result.ctTotalPage}" type="hidden"/>
  <input id="ctCurPage" value="${result.ctCurPage}" type="hidden"/>
  <input id="path" value="${path}" type="hidden"/>
  <input id="colId" value="${result.currColumn.colId}" type="hidden"/>

<#include "/common/footer.ftl"/>
<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${path}/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/queryPagePic.js"></script> 
</body>
</html>