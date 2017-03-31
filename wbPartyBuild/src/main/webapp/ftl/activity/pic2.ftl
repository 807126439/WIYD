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
		
		
		<link href="${path}/plug-in/web/portals/css/activity/bigimagelist.css" rel="stylesheet" type="text/css" />

	  </head>
	<body>
		<#include "/common/head.ftl"/>
		 <div class="mod" style="margin:0 auto;width:1200px;padding:270px 12px 12px" >
				    <h2 class="mod-title"> <i class="icon_mod"></i>
				      <div class="mod-loc">
				        <div class="loc clearfix"> <span>当前位置：</span>
				          <ol>
				            <li class="noactive"><a href="${path}/portals.do">首页</a></li>
				            <li class="noactive"><a href="${path}/subject/${result.columnId}.htm">${result.columnTitle}</a></li>
				            <li class="active"><a href="#">${result.title}</a></li>
				          </ol>
				        </div>
				      </div>
				      ${result.title}</h2>
				     </div>
	
	
	
	   <div class="maincontainer clearfix">
	
      <div class="module type-2" id="module_news">
	  		
		<div class="picshow">
		
          <div class="bigimg-tt" id="bigimgtt1"></div>
          
          <div class="picshowimg">
            <a href="javascript:void(0)"><img src="" alt="" id="bigimg" curindex="0"></a>
            <a class="prearrow" href="javascript:void(0)" title="上一张"><span id="preArrow_A"></span></a>
            <a class="nextarrow" href="javascript:void(0)" title="下一张"><span id="nextArrow_A"></span></a>
          </div>
          
          <div class="picshowtxt">
            <div class="picshow-count"><span></span>/<i></i></div>
            <div class="bigimg-tt" id="bigimgtt2"></div>
            <a href="javascript:void(0)" class="btn btn-primary size-MINI" id="slideBack">定位当前</a>
          </div>
          
          <div class="picshowlist">      
            <a href="javascript:void(0)" id="leftSlide"></a>
            <div class="picmidmid">         
             <ul>
          	  <#list result.imgList as img> 
	           	  <#if result.id != img.id>            	
	            	 <li>
                	  <a href="javascript:void(0);"><img src="${img.viewPath}" alt="" data-src="${img.viewPath}" title="${img.title}"></a>
               		 </li>         
	              </#if>
	           </#list>  
	         </ul>  
            </div>
            <a href="javascript:void(0)" id="rightSlide"></a>           
          </div>
          
        </div>
        
	 </div>
	</div>

		     <!-- module_news -->
   
		  
		<#include "/common/footer.ftl"/>
		<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
		<script type="text/javascript" src="${path}/plug-in/h-ui/lib/layer/2.4/layer.js"></script>
	    <script type="text/javascript" src="${path}/plug-in/h-ui/lib/imageGallery/1.0/imageGallery.min.js"></script>
	    <script type="text/javascript">
	      var contrl;
	      $(function() {
	        imageGallery({
			  first:0,
	          target:'#bigimg',
	          list:'.picmidmid ul',
	          title:'.bigimg-tt',
	          prev:'.prearrow',
	          next:'.nextarrow',
	          count:'.picshow-count span',
	          total:'.picshow-count i',
	          leftSlide:'#leftSlide',
	          rightSlide:'#rightSlide',
	          slideBack:'#slideBack',
	        });
	      });
	    </script>
	</body>
	</html>