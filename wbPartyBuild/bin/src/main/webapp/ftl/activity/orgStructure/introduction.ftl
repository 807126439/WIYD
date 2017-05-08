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
	<link href="${path}/plug-in/web/portals/css/activity/mod-dymc.css" rel="stylesheet" type="text/css" />
    <link href="${path}/plug-in/web/portals/css/activity/memberlist.css" rel="stylesheet" type="text/css" />
    <!-- 切换标签CSS -->
  	  <style type="text/css">
	    .navtag-container{margin-top:25px;}
	    .navtag { border-bottom: 1px solid #7d0600;}   
	    .navtag li{    
	      float: left;
	      line-height: 24px;
	      font-size: 14px;
	      padding: 4px 15px;
	      background: #DDD;
	      cursor: pointer;
	    }
	    .navtag li+li{
	      border-left:2px solid #EEE;
	    }
	    .navtag li.active {
	      background: #a91710;
	      color: #FFF;
	    }
	    .tabpanel{display: none;}
	    .tabpanel.current{display: block;}
  </style>
  </head>
<body>
	<#include "/common/head.ftl"/>
 		
	<div class="container" class="clearfix">
    <div class="mainplaceholder"></div>
    <div id="main" class="clearfix">
      <#--左菜单-->
		<#include "/activity/common/leftMenu.ftl"/>
      <!-- sidemenu -->
      <div class="mod">
        <#--导航条-->
      <#include "/activity/common/nav.ftl"/>
        <div class="mod-content">
          <div class="contentwrapper" style="line-height:40px">
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市城建局机关党委于2012年4月换届升格成立，下设4个党支部，现有党员94名，其中在册的正式党员81名，流动党员13名。一直以来，在市委组织部、市直工委的正确领导下，市城建局机关党委紧密围绕工程建设中心，围绕一条主线（即提高党建科学化水平），突出两个建设（即先进性建设和能力建设），实现三个目标（即创建服务型党组织、争当优秀共产党员、打造党建特色品牌），大胆创新，积极开展创先争优活动，不断探索新形势下党建工作的新思路和新举措，充分发挥机关党委和各党支部的战斗堡垒作用和共产党员的先锋模范作用，积极开展思想建设、组织建设、作风建设、制度建设和党风廉政建设，党建工作取得了丰硕成果，为工程建设提供了坚强有力的组织保障。局机关党委2005年以来连续十一年被评为市直机关党建工作量化考评先进单位，打造出了工地临时党支部党建百佳品牌、"我来讲党课"党员学习品牌、"五字三问"工作法组织生活品牌等机关党建品牌，我局的党建工作得到了省委组织部调研组、市委组织部和市直工委领导的充分肯定。</p>
          </div>
          <div class="navtag-container">
		    <ul class="navtag clearfix">
		      <li class="active"><span>党员男女比例</span></li>
		      <li><span>党员年龄比例</span></li>
		      <li><span>党员在册情况</span></li>
		      <li><span>党员受教育程度</span></li>
		    </ul>
		    <div class="tabpanels">
		      <div class="tabpanel current"><div id="sexCharts" style="width: 600px;height:400px;"></div></div>
		      <div class="tabpanel"><div id="ageCharts" style="width: 600px;height:400px;"></div></div>
		      <div class="tabpanel"><div id="partyCharts" style="width: 600px;height:400px;"></div></div>
		      <div class="tabpanel"><div id="degreeCharts" style="width: 600px;height:400px;"></div></div>
		    </div>
		  </div>
        </div>
      </div>
      <!-- mod -->
    </div>
  </div>	
 	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/utils/echarts/echarts.js"></script> 
	<script type="text/javascript">
	
		window.console = window.console || (function(){ 
		var c = {}; c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile = c.clear = c.exception = c.trace = c.assert = function(){}; 
		return c; 
		})();
	
		$(function(){
			tabPage('.navtag','active','.tabpanels','current');
			
			// 基于准备好的dom，初始化echarts实例
	        var sexCharts = echarts.init(document.getElementById('sexCharts'));
	        var ageCharts = echarts.init(document.getElementById('ageCharts'));
	        var partyCharts = echarts.init(document.getElementById('partyCharts'));
	        var degreeCharts = echarts.init(document.getElementById('degreeCharts'));
	        
			$.ajax({
			    url: "${path}/userController/getTotalData.do",
			    traditional:true,
			    dataType:'json',  
			    data:{},
			    type:"post",
			    cache : false,  
			    async : false,  
			    success:function(data){	
			    		var resultList=data.backVal;
			    		//设置性别数据
			    		var sexData=new Array();
			    		for(var i=0; i<resultList.totalSex.length; i++){
			    				sexData[i]=resultList.totalSex[i]['name'];
               			 }
               			 sexCharts.setOption({
               			 				    title : {
										        text: '党员性别比例',
										        x:'center'
										    },
										    tooltip : {
										        trigger: 'item',
										        formatter: "{a} <br/>{b} : {c} ({d}%)"
										    },
										    legend: {
										        orient: 'vertical',
										        left: 'left',
										        data: sexData
										    },
										    series : [
										        {
										            name: '访问来源',
										            type: 'pie',
										            radius : '55%',
										            center: ['50%', '60%'],
										            data:resultList.totalSex,
										            itemStyle: {
										                emphasis: {
										                    shadowBlur: 10,
										                    shadowOffsetX: 0,
										                    shadowColor: 'rgba(0, 0, 0, 0.5)'
										                }
										            }
										        }
										    ]
										
               			 })
               			 //设置年龄数据
               			 var ageData=new Array();
               			 var ageVal=new Array();
               			 var j=0;
               			 for(var i in resultList.totalAge[0]){
			    				ageData[j]=i;
			    				ageVal[j]=resultList.totalAge[0][i];
			    				j++;
               			 }
               			 
               			 ageCharts.setOption({
               			 	title: {
				                text: '党员年龄组成'
				            },
				            legend: {
				                data:['年龄']
				            },
				            xAxis: {
				                data: ["30以下","31-40","41-50","50以上"]
				            },
				            yAxis : {
					            type : 'value',
					            name:'人次',
					            min:'0',
					            minInterval: 1
					            //splitNumber:3
					            
					        },
				            series: [{
				                name: '年龄',
				                type: 'bar',
				                barWidth: '50%',
				                data: ageVal
				            }]
               			 })
               			 //设置在册情况统计信息
			    		var partyData=new Array();
			    		for(var i=0; i<resultList.totalParty.length; i++){
			    				partyData[i]=resultList.totalParty[i]['name'];
               			 }
               			 partyCharts.setOption({
               			 				    title : {
										        text: '党员在册情况比例',
										        x:'center'
										    },
										    tooltip : {
										        trigger: 'item',
										        formatter: "{a} <br/>{b} : {c} ({d}%)"
										    },
										    legend: {
										        orient: 'vertical',
										        left: 'left',
										        data: partyData
										    },
										    series : [
										        {
										            name: '访问来源',
										            type: 'pie',
										            radius : '55%',
										            center: ['50%', '60%'],
										            data:resultList.totalParty,
										            itemStyle: {
										                emphasis: {
										                    shadowBlur: 10,
										                    shadowOffsetX: 0,
										                    shadowColor: 'rgba(0, 0, 0, 0.5)'
										                }
										            }
										        }
										    ]
										    
										
               			 })
               			 //设置学历数据
               			 var degreeData=new Array();
               			 var degreeVal=new Array();
               			 for(var i=0; i<resultList.totalDegree.length; i++){
               			 		for(var j in resultList.totalDegree[i]){
				    				degreeData[i]=resultList.totalDegree[i]['name'];
				    				degreeVal[i]=resultList.totalDegree[i]['value'];
               					 }
               			 }
               			 
               			 degreeCharts.setOption({
               			 	title: {
				                text: '党员学历组成'
				            },
				            legend: {
				            },
				            xAxis: {
				                data: degreeData
				            },
				            yAxis: {},
				            series: [{
				                name: '学历',
				                type: 'bar',
				                barWidth: '50%',
				                data: degreeVal
				            }]
               			 })
			       },  
			     error : function(error) {  
			       } 
		  	});
		});

		  
		    //标签页
		    function tabPage(s1,active1,s2,active2){
		      if ($(s1).length===0||$(s2).length===0) {
		        return;
		      }
		      var $tabcontainer=$(s1);
		      var $pagecontainer=$(s2);
		      $tabcontainer.find('li').click(function(){
		        $(this).addClass(active1).siblings().removeClass(active1);
		        $pagecontainer.children().eq($(this).index()).addClass(active2).siblings().removeClass(active2);
		      });
		    }
		

	</script>	
</body>
</html>