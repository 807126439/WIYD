<!-- 
<div class="top">
 
  <div class="topInner">
    <div class="topLeft">
    	<#if isLogined>
    		 你好，${name!}！
    	</#if>	
   	
   	 今天是：${today!}</div>
   	 
 	 
   	 
    <div class="topRight">
    	<#if isLogined>
    		<a href="${path}/j_spring_security_logout"><span>退出系统</span></a>
    	</#if>	    
	    <a href="${path}/index.do"><span>系统管理</span></a>
    </div>
    <form class="topsearch" target="_blank" action="${path}/goSearch.do" method="post">
      <input type="text" class="search-keyword" placeholder="请输入关键字" name="title" id="title">
      <button type="button" onclick="var title=$('#title').val();
      								title = encodeURI(encodeURI(title));
      								 window.location.href='${path}/goSearch.do?title='+title;
      								" 
      								class="search-submit">搜 索</button>
    </form>
     
	</div>


	</div>
</div>-->


<div class="top">
  <div class="topInner">
    <div class="topLeft">
    	<#if isLogined>你好，<strong>${name!}</strong>！</#if>今天是${today!}
    </div>
    <div class="topRight">
      <div class="topmenu">
        <a href="javascript:void(0)">网站导航</a>
        <div class="topmenu-menu">
          <div class="websitenav">
            <!-- jquery load -->
          </div>
        </div>
      </div>|
      <a href="${path}/index.do" class="top-btn-1"><span>系统管理</span></a>
      <a href="${path}/j_spring_security_logout"><span>退出系统</span></a>
    </div>
    <form class="topsearch" target="_blank" action="${path}/goSearch.do" accept-charset="utf-8" method="post">
      <div class="search-classification-wrap">
        <select id="searchType" name="searchType" class="search-classification my-select">
          <option value="column">栏目</option>
          <option value="article">文章</option>
        </select>
      </div>
      <input type="text" name="title" class="search-keyword" placeholder="请输入关键字">
      <button type="submit" class="search-submit"><img src="${path}/plug-in/web/portals/image/icon_search.png"></button>
    </form>
  </div>
</div>



<!-- top -->

<div class="header">
  <div class="headerInner">
  		 <!--ads(1)-->
  		 <#if (ads?size>=1)>
  			<img src="${ads[0].pattern!}" width="1000" height="187" />
  	     </#if>
  
  </div>
</div>
<!-- header -->
<div class="navBar">
  <div class="navBarInner">
    <ul class="navLeft">
      <li><a href="${path}/portals.do">首页<span>|</span></a></li>
      <#list head as k> 
         <#if (k_index<11)>
    					
			<#if k.linkUrl??>
			    <li><a href="${k.linkUrl}">${k.title}<span>|</span></a></li>	    		  
			<#else>
			    <li><a href="${path}/subject/${k.colId}.htm">${k.title}<span>|</span></a></li> 
			</#if>  
      	 </#if>      	       
      </#list>
    </ul>
  </div>
</div>
<script type="text/javascript" src="${path}/plug-in/common/js/header.js"></script>



