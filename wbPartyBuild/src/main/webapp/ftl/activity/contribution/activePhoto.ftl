<!DOCTYPE HTML>
<html>

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <meta http-equiv="X-UA-Compatible" content="IE=8">
  <link rel="stylesheet" type="text/css" href="${path}/plug-in/h-ui/static/h-ui/css/H-ui.reset.css">
  <link rel="stylesheet" type="text/css" href="${path}/plug-in/h-ui/lib/laypage/1.2/skin/laypage.css">
  <link rel="stylesheet" type="text/css" href="${path}/plug-in/h-ui/lib/Hui-iconfont/1.0.7/iconfont.css">
  <link rel="stylesheet" type="text/css" href="${path}/plug-in/web/portals/css/activity/photography.css">
  <link rel="stylesheet" type="text/css" href="${path}/plug-in/h-ui/lib/viewer/css/viewer.css">
  



  
  <style type="text/css">
  .linnerbg{position:absolute;z-index:-2;top:0;left:0;width:100%;height:1320px;
    FILTER:progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#bbefdd,endColorStr=#f7f2cf);
    background: -webkit-linear-gradient(#bbefdd, #f7f2cf);
    background: -o-linear-gradient(#bbefdd, #f7f2cf);
    background: linear-gradient(#bbefdd, #f7f2cf);  /*Opera 11.10+*/}
  </style>
  <!--[if lt IE 9]>
  <script type="text/javascript" src="${path}/plug-in/h-ui/lib/html5.js"></script>
  <![endif]-->
</head>

<body>
  <header class="header">
    <div class="topbar">
      <span>"月月精彩"摄影比赛</span><a href="${path}/portals.do" class="backtoroot">返回到党建先锋首页</a>
    </div>
  </header>
  <div class="linnerbg"></div>
  <div class="photowall-wr">
    <div class="photowall">
    </div>
  </div>
  <div class="maincontainer">
    <div class="titlecont"><img src="${path}/plug-in/web/portals/image/activePhotoActivity/title.png"></div>
    

    <div class="navlist">
      <a href="${path}/activePhotoActivity.do">活动主页</a>
      <a href="javascript:void(0)" class="showrules">详细规则</a>
      <a href="javascript:void(0)"  onclick="contribution()"><i class="icon-up"></i>上传作品</a>
      
       
      <#if (result.isAwarded > 0)>
     	 <a href="${path}/adwardMessage.do?activityId=${result.activity.id}">获奖作品</a>
      <#else>  
        <a href="javascript:void(0)" onclick="message()">获奖作品</a>
       </#if>
     
   
      
    </div>
    <div class="twobox clearfix">
      <section class="box-l sect_description">
        <div class="sect-hd">
          <i class="icon-camera"></i>
          <div class="sect-tt">${result.activity.activityName}</div>
          <div class="sect-subtt">Activity Description</div>
        </div>
        <div class="sect-bd">
          <p>${result.activity.content}</p>
          <div>投稿时间：${result.activity.startDate} - ${result.activity.endDate}</div>
        </div>
         <div class="prev"></div>
        <div class="next"></div>
      </section>
      
      <section class="box-r sect_review">
        <div class="sect-hd">
          <i class="icon-camera"></i>
          <div class="sect-tt">作<br/>品<br/>回<br/>顾</div>
          <div class="sect-subtt">
            <div>Works</div>
            <div>Review</div>
          </div>
        </div>
        
        <#if result.lastActivity??>
	        <a href="${path}/lastPhotoActivity.do?nowId=${result.activity.id}" class="sect-bd miscpic-cont" >
	        <div class="tt">${result.lastActivity.activityName}作品回顾</div>
	        
	        <#if result.lastActivity.manuscripts??>
	        	<#list result.lastActivity.manuscripts as lastPhotos>        	
	        		<div id="miscpic${lastPhotos_index+1}">
	        			<img src="${lastPhotos.pattern}">	        		
	        		</div>
        		</#list>        
	        </#if>
	        
	        <input id="lastActivityId" value="${result.lastActivity.id}" type="hidden"> 
        <#else>
        	<a href="javascript:void(0)" class="sect-bd miscpic-cont" >
         	<div class="tt" style="bottom: 148px; right: 136px;">暂无作品回顾</div>
        </#if>
         </a>
      </section>
      
      
      
      <section class="box-l sect_rules">
        <div class="sect-hd">
          <i class="icon-camera"></i>
          <div class="sect-tt">活动规则</div>
          <div class="sect-subtt">Activity Rules</div>
        </div>
        <ul class="sect-bd">
         <#if result.activityRuleList?size gt 0 >   		
			<#list result.activityRuleList as ruleList>
				<#if ruleList.isMain = true>
				 <li>${ruleList.content}</li>
				 </#if>
			</#list>
		  </#if>
        </ul>
        <div class="prev"></div>
        <div class="next"></div>
      </section>
      <section class="box-r sect_award">
        <div class="sect-hd">
          <i class="icon-camera"></i>
          <div class="sect-tt">活动奖励</div>
          <div class="sect-subtt">Activity Award</div>
        </div>
        <#if result.awardsSettingList?size lt 5>
        <div class="sect-bd">
        <#else>
        <div class="sect-bd gt5">
        </#if>
         <#list result.awardsSettingList as settingList>
          <div class="award">
          <#if settingList.pattern ??>
            <div class="award-pic">
            	<img src="${settingList.pattern}">
            </div>
            <#else>	
          	 <div class="award-pic"></div>
           </#if>   
            <div class="award-num">${settingList.awardsName}(${settingList.amount}位)</div>
            <div>${settingList.prize}</div>
          </div>
		 </#list>
        </div>
      </section>
    </div>
    <section class="sect_works" id="works">
      <div class="sect-hd">
        <i class="icon-camera"></i>
        <div class="sect-tt">作品欣赏</div>
        <div class="sect-subtt">Appreciate the works</div>
      </div>
      <div class="sect-bd">
        <div class="sortbar">
          <span class="sort sort-new active" ><i class="Hui-iconfont">&#xe68f;</i><span onclick="queryPage(false,true)">最新</span></span>
          <span class="sort sort-hot"><i class="Hui-iconfont">&#xe648;</i><span onclick="queryPage(true,false)">热门</span></span>
        </div>

        
         <div id="picContainer" class="piccontainer clearfix">
            

			
          <ul class="col" id="col1">
          <#if photoList??>
          <#list photoList as photo>  
           <#if photo_index % 3 = 0 >
            <li>
              <div class="work" id="work-${photo.id}">
                <div class="work-pic">
                  <i class="Hui-iconfont noimg active" onclick="loadAgain(this)"></i><img src="${photo.pattern}">
                  <input class="bigPattern" value="${photo.bigPattern}" type="hidden">
                </div>
                <div class="work-tt">
                  <span>${photo.title}</span>                  
                 	<#if photo.loveing?string("true","false") = "true">
     					<span class="love loving" onclick="loh('${photo.id}',event)">
		     		<#else>
		                <span class="love" onclick="loh('${photo.id}',event)">
		            </#if>           
                  	<i class="Hui-iconfont"></i>
                  	<#if photo.love ??>
                  	<span>${photo.love}</span>
                  	<#else>
                  	<span>0</span>
                  	</#if>
                  </span>
                </div>
                <div class="work-ct">
      
                  <p>描述：${photo.description}</p>
                </div>
              </div>
            </li>
            </#if>
            </#list>
            </#if>
          </ul>
          
		

          
          <ul class="col" id="col2">
           <#if photoList??>
          <#list photoList as photo> 
            <#if photo_index % 3=1 >
            <li>
              <div class="work" id="work-${photo.id}">
                <div class="work-pic">
                  <i class="Hui-iconfont noimg active" onclick="loadAgain(this)"></i><img src="${photo.pattern}">
                  <input class="bigPattern" value="${photo.bigPattern}" type="hidden">
                </div>
                <div class="work-tt">
                  <span>${photo.title}</span>
                   <#if photo.loveing?string("true","false") = "true">
     					<span class="love loving" onclick="loh('${photo.id}',event)">
		     		<#else>
		                <span class="love" onclick="loh('${photo.id}',event)">
		            </#if>
                 	<i class="Hui-iconfont"></i>
                  	<#if photo.love ??>
                  	<span>${photo.love}</span>
                  	<#else>
                  	<span>0</span>
                  	</#if>                 
                  </span>
                </div>
                <div class="work-ct">
               
                  <p>描述：${photo.description}</p>
                </div>
              </div>
            </li>
            </#if>
            </#list>
              </#if>
          </ul>
          <ul class="col" id="col3">
          <#if photoList??>
           <#list photoList as photo>
           <#if photo_index % 3= 2  >
            <li>
              <div class="work" id="work-${photo.id}">
                <div class="work-pic">
                  <i class="Hui-iconfont noimg active" onclick="loadAgain(this)"></i><img src="${photo.pattern}">
                  <input class="bigPattern" value="${photo.bigPattern}" type="hidden">
                </div>
                <div class="work-tt">
                  <span>${photo.title}</span>
  	     			<#if photo.loveing?string("true","false") = "true">
     					<span class="love loving" onclick="loh('${photo.id}',event)">
		     		<#else>
		                <span class="love" onclick="loh('${photo.id}',event)">
		            </#if>
	                <i class="Hui-iconfont"></i>
                  	<#if photo.love ??>
                  	<span>${photo.love}</span>
                  	<#else>
                  	<span>0</span>
                  	</#if>                
                  </span>
                </div>
                <div class="work-ct">
                 
                  <p>描述：${photo.description}</p>
                </div>
              </div>
            </li>
            </#if>
            </#list>
              </#if>
          </ul>
       
        </div>
        
        
        
        
         <div class="page1" id="page1"></div>
      </div>
    </section>
  </div>
  <div id="showRules">
    <div class="tt">参赛须知</div>
    <div class="subtt">INFORMATION FOR PARTICIPANTS</div>
    <div class="ct">
    	 <#if result.activityRuleList?size gt 0 >   		
			<#list result.activityRuleList as ruleList>
				<#if ruleList.isMain = false>
				 ${ruleList.content}
				 </#if>
			</#list>
		  </#if>
    </div>
  </div>
  
  <div id="showWork" class="clearfix">
    <div class="tb">
      <div class="cell"></div>
      <div class="cell">
        <div class="work-tt">
          <span></span>
        </div>
        <div class="work-ct">
          <div></div>
          <p></p>
        </div>
          <a href="javascript:void(0)" class="openimg"><i class="Hui-iconfont"></i><span>查看原图</span></a>
        
      </div>
    </div>
  </div>
  
  <input id="activityId" value="${result.activity.id}"  type="hidden">
  <input id="activityTitle" value="${result.activity.activityName}" type="hidden"> 
  <input id="path" value="${path}" type="hidden">
  <#if totalPage??><input id="totalPage" value="${totalPage}" type="hidden"></#if>
  
  <script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript" src="${path}/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script>
  <script type="text/javascript" src="${path}/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
  <script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/queryPagePhoto.js"></script>
  <script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/photography.js"></script>

</body>

</html>
