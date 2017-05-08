<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>城建局智慧党建</title>
  <link rel="stylesheet" type="text/css" href="${path}/plug-in/h-ui/lib/Hui-iconfont/1.0.7/iconfont.css">
  <link rel="stylesheet" type="text/css" href="${path}/plug-in/h-ui/lib/slick/1.6.0/slick.css">
  <link rel="stylesheet" type="text/css" href="${path}/plug-in/web/portals/css/common/reset.css">
  <link rel="stylesheet" type="text/css" href="${path}/plug-in/web/portals/css/common/common.css">
  <link rel="stylesheet" type="text/css" href="${path}/plug-in/web/portals/css/common/periodical.css">
</head>

<body>
	<#include "/common/head.ftl"/>

  <!-- navBer -->
  <div class="container" class="clearfix">
    <div class="mainplaceholder"></div>
    <div id="main" class="clearfix">
      <div class="mod">
        <h2 class="mod-title clearfix">
        <div class="mod-loc">
          <div class="loc clearfix">
            <span>当前位置：</span>
            <ol>
              <li class="noactive"><a href="${path}/portals.do">首页</a></li>
              <li class="noactive"><a href="${path}/subject/${result.columnId}.htm">${result.columnTitle}</a></li>
			  <li class="active"><a href="#">${result.title}</a></li>
            </ol>
          </div>
        </div>
      </h2>
        <div class="mod-content clearfix">
          <div id="sideBar">
            <div class="menu-tt">目录CONTENTS</div>
            <div class="menu-up"><i class="icon_up"></i></div>
            <div class="menu">
            <#list result.banChunkList as banChunk>
            	<div class="submenu">
                	<div class="submenu-title"><span class="icon_fold_wr"><i class="icon_fold Hui-iconfont"></i></span>${banChunk.chunkName}</div>
                	<ul class="submenu-list">
                	<#if (banChunk.children?size!=0)>
                		<#list banChunk.children?sort_by('sortNum') as cbanChunk>
                			<#if cbanChunk.children?size=0>
                  				<li class="submenu-item"><i class="icon_item Hui-iconfont"></i><a href="javascript:void(0)" <#if cbanChunk.linkContentId??>onclick="changePageByContentId(${cbanChunk.linkContentId})"</#if>>${cbanChunk.chunkName}</a></li>
                			<#else>
                 				<li class="submenu">
                    				<div class="submenu-title"><i class="icon_jiajian Hui-iconfont"></i>${cbanChunk.chunkName}</div>
                    					<ul class="submenu-list">
                    						<#list cbanChunk.children?sort_by('sortNum') as ccbanChunk>
                      						<li class="submenu-item"><a href="javascript:void(0)" <#if ccbanChunk.linkContentId??>onclick="changePageByContentId(${ccbanChunk.linkContentId})"</#if>>${ccbanChunk.chunkName}</a></li>
                      						</#list>
                    					</ul>
                  				</li>
                			</#if>
                		</#list>
                	</#if>
              		</ul>
              </div>
            </#list>
            </div>
            <div class="menu-down"><i class="icon_down"></i></div>
          </div>
          <div class="periods">${result.title}</div>
          <div class="piccontainer">
            <img id="currentshow" src="${result.imgList[0].viewPath}">
            <div class="prevpic"></div>
            <div class="nextpic"></div>
          </div>
          <div class="picnav clearfix">
            <div class="picnav-tt">
              <span class="currentnum">1</span><span class="piccount">/44</span>${result.title}
            </div>
            <!-- 上图集框 -->
            <#if result.pre??>
            	<a href="${path}/article/${result.pre.id}.htm" class="nextperiodical"><span>下一期</span><img src="${result.pre.viewPath}"></a>
            <#else>
            	<a href="javascript:void(0)" class="prevperiodical ends"><span>上一期</span>无</a>
            </#if>
            
            <!-- 预览框 -->
            <div class="currperiodical" id="slider-1">
              <a class="picnav-left Hui-iconfont" href="javascript:void(0)">&#xe67d;</a>
              <div class="picnav-list">
              	<#list result.imgList as img>
              		<#if img_index=0>
              		<img class="current" src="${img.viewPath}" id="contentId${img.id}">
              		<#else>
              		<img src="${img.viewPath}" id="contentId${img.id}">
              		</#if>
              	</#list>
              </div>
              <a class="picnav-right Hui-iconfont" href="javascript:void(0)">&#xe63d;</a>
            </div>
            
            <!-- 下图集框 -->
            <#if result.next??>
            	<a href="${path}/article/${result.next.id}.htm" class="nextperiodical"><span>下一期</span><img src="${result.next.viewPath}"></a>
            <#else>
            	<a href="javascript:void(0)" class="prevperiodical ends"><span>上一期</span>无</a>
            </#if>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--container-->
		<#include "/common/footer.ftl"/>
  <!-- footer-p -->
  <script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript" src="${path}/plug-in/h-ui/lib/slick/1.6.0/slick.js"></script>
  <script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery.nicescroll/jquery.nicescroll.min.js"></script>
  <script type="text/javascript">
    $(function() {
       //侧边菜单栏展开与收回
      $('.submenu-title').click(togglemenu);
      //侧边菜单栏控制
      $('.menu-up').click(scrollUpMenu);
      $('.menu-down').click(scrollDownMenu);
      $('.menu').niceScroll({cursoropacitymax:0, horizrailenabled: false, autohidemode: true });

      //图片导航列表
       $(".picnav-list").slick({
        dots: false,
        infinite: false,
        centerMode: true,
        variableWidth: true,
        arrows:false,
        focusOnSelect: true,
        slidesToScroll: 3
      });

      //图片导航slider的控制
      var changeif=true;
      var goTo=function(n){
        $('.picnav-list').slick('slickGoTo',n-1);//跳转到第n页方法
      };
      $('.picnav-list').on('beforeChange', function(event, slick, currentSlide, nextSlide){
        if(!changeif) {changeif=true;return;}
        $('#currentshow').attr('src',$(".picnav-list img").eq(nextSlide).attr('src'));
        $(".picnav-list img").eq(nextSlide).addClass('current').siblings().removeClass('current');
        $(".currentnum").text(nextSlide+1);
      });
      $('.picnav-left').click(function(){
        changeif=false;
        goTo($('.picnav-list').slick('slickCurrentSlide')-4);
      });
      $('.picnav-right').click(function(){
        changeif=false;
        goTo($('.picnav-list').slick('slickCurrentSlide')+6);
      });
      $('.prevpic').click(function(){
        goTo($('.picnav-list').slick('slickCurrentSlide'));
      });
      $('.nextpic').click(function(){
        goTo($('.picnav-list').slick('slickCurrentSlide')+2);
      });
      $('.piccount').text('/'+$('.picnav-list img').length);//输出总页数
    });

    function togglemenu(event) {
      $(this).next('.submenu-list').slideToggle(200);
      var ele = $(this).find('.icon_fold,.icon_jiajian');
      ele.toggleClass('opened');
      var elemenu = $(this).parent('.submenu');
      elemenu.toggleClass('active');
      $(".menu").getNiceScroll().resize();
    }
    function scrollUpMenu(event) {
      $(".menu").getNiceScroll(0).doScrollTop(-400, 1000);
    }

    function scrollDownMenu(event) {
      $(".menu").getNiceScroll(0).doScrollTop(400, 1000);
    }
    
    //后台关联页面位置方法
    function changePageByContentId(contentId){
    	$("#contentId"+contentId).click();
    	
    }
  </script>
</body>

</html>