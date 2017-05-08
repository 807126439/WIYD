<!DOCTYPE HTML>
<html>

<head>
  <meta charset="UTF-8" />
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
      <a href="javascript:history.go(-1)">活动主页</a>
      <a href="javascript:void(0)" class="showrules">详细规则</a>
    </div>
    
 	
     <section class="sect_prize">
      <div class="sect-hd">
        <i class="icon-camera"></i>
        <div class="sect-tt">获奖作品</div>
        <div class="sect-subtt">Winning entries</div>
      </div>    
      <div class="sect-bd">
      
      
         <#list result.awardList as award>
         	
         <#if award.amount == 1 >
         	<div class="prize one_prize">
         <#elseif award.amount == 2>
          	<div class="prize two_prize">      	
         <#else>
         	<div class="prize more_prize">
         </#if>
         	<div class="prize-hd clearfix">
	            <span class="ttgroup">
	              <span class="sect-tt">${award.awardName}</span><br/>
	              <span class="sect-subtt"></span>
	              <span class="left-line"></span>
	              <span class="right-line"></span>
	            </span>
         	 </div>
         	 <ul class="prize-works clearfix">
          <#list award.manuscriptList as msList> 
          	 <li>
              <div class="work clearfix" id="work-0">
                <div class="work-pic">
                  <i class="Hui-iconfont noimg active" onclick="loadAgain(this)"></i>
                  <img src="${msList.pattern}">
                </div>
                <div class="work-tt">
                  <span>${msList.title}</span>
                </div>
                <div class="work-ct">
                  <div>作者：${msList.author}</div>
                  <p>描述：${msList.description}</p>
                </div>
                <div class="work-remark">
                  <div>点评：</div>
                  <p>${msList.comment}</p>
                </div>
              </div>
            </li>          
          </#list>
         </ul>
        </div>       
       </#list>
      
        <div class="prize explain_prize">
          <div class="prize-hd clearfix">
            <span class="ttgroup">
              <span class="sect-tt">奖励说明</span><br/>
              <span class="sect-subtt">Reward Description</span>
              <span class="left-line"></span>
              <span class="right-line"></span>
            </span>
          </div>
          <ul class="explain_list">
            <li>奖项均由中共东莞市城建工程管理局机关委员会秉承公平公正的原则评选而出。</li>
            <li>我们将在20个工作日之内联系获奖作者发放奖励。</li>
            <li>获奖作品将会在党建先锋网站中进行展示。</li>
          </ul>
        </div>
		</div>
    </section>
    
    <div id="showRules">
    <div class="tt">参赛须知</div>
    <div class="subtt">INFORMATION FOR PARTICIPANTS</div>
    <div class="ct">
		 <#if result.ruleList?size gt 0 >   		
			<#list result.ruleList as ruleList>
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
        </div>
    </div>
  </div>
  
  
  <input id="path" value="${path}" type="hidden">
  <input id="activityId" value="${activityId}"  type="hidden">
  
   <script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
   <script type="text/javascript" src="${path}/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script>
   <script type="text/javascript" src="${path}/plug-in/h-ui/lib/layer/2.1/layer.js"></script>
   <script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/awardMessage.js"></script>
</body>

</html>
