<#macro greet result>

<#if k1.top.indexNum = 4>
	<#list k1.innner as k2>				 	 
		 <li><div class="biaoti"><a href="article/${k2.id}.htm"><span class="span3"></span>${k2.ctTitle}</a></div><div class="date">06-12</div></li>
	     <div class="line"></div>     
	</#list>
</#if>	



</#macro>

<#-- 栏目标题-->    
<#macro columTitle result curr>
 
	<#if (result?size>=curr) >
		 ${result[curr-1].top.title}
	</#if>	
		
</#macro>

<#-- 栏目more链接-->    
<#macro columTitleLink result curr>
 
	<#if (result?size>=curr) >
		<a href="subject/${result[curr-1].top.colId!''}.htm">+MORE</a>

	<#else>	
	 	<a href="javascript:;">+MORE</a>
	</#if>		
	
	
</#macro>


<#-- 普通栏目文章列表 -->  
<#macro contentList result curr>
		<#if (result?size>=curr) >

			 <#if result[curr-1].isInner == false>
				
				 <#if result[curr-1].top.typeId == 2 >
			
         			 <ul class="text-inner">
	
				 	 <#list result[curr-1].innner as l>	
				 	 	 <#if l.isNew >		     				     		
			    	 	 	<li class="new" title="${l.ctTitle}">
			    	 	 <#else>
			    	 	 	<li  title="${l.ctTitle}">
			    	 	 </#if>	     		
			     	
			     		<div class="biaoti"><a href="article/${l.id}.htm" target="_blank"><span class="span3"></span>${l.ctTitle}</a></div><div class="date">${l.changgTime}</div>
			     		</li>
			    	 	
			    	 	 <#if l_has_next>
	           			 	 <div class="line"></div>
	           			 </#if>
			    	 	
			    	 </#list>
			    	 
			    	 </ul>
       			 </#if>
		    	 				 
			 <#else>
			 
			 	<#if result[curr-1].top.typeId == 2 >
 		  			 <div id="tab_demo" class="HuiTab">
			 
					   	<div class="tabBar cl">
					 		<#list result[curr-1].innner as l>	
					 	     	<span class="buttonFont">${l.top.title}</span>
					 	    </#list>          	            	
					    </div>
			 	     
					 	     
					 	<#list result[curr-1].innner as t>	
					 	      <div class="tabCon">
							    <div class="Box2-inner">
						          	<ul class="text-inner">
						          		<#list t.innner as it>	
					          			   <#if it.isNew >		     				     		
							    	 	 	 <li class="new" title="${it.ctTitle}">
							    	 	   <#else>
							    	 	 	<li  title="${it.ctTitle}">
							    	 	   </#if>			          		
							          	
							          		
							          		<div class="biaoti"><a href="article/${it.id}.htm" target="_blank"><span class="span3"></span>${it.ctTitle}</a></div><div class="date">${it.changgTime}</div>
							          		</li>
							            	 
							            	 <#if it_has_next>
						           			 	 <div class="line"></div>
						           			 </#if>
							            	
							            	
						          		</#list> 
						           	</ul>
							    </div> 
							  </div>  		
						          		
					 	</#list>       
			 	     
			 	     	</div>
					
		   		 </#if>
			 	     
		 
			 </#if>	
		
		
		</#if>	
		
	   		

</#macro>


<#-- 第一个栏目文章列表 -->  
<#macro contentFirst result curr>
		<#if (result?size>=curr) >
		
			 <#if result[curr-1].innner.class.simpleName = "ArrayList">
		 
			 	 <#list result[curr-1].innner as l>
			 	 	 <#if l.isNew >		     				     		
		    	 	 	<li class="new" title="${l.ctTitle}">
		    	 	 <#else>
		    	 	 	<li  title="${l.ctTitle}">
		    	 	 </#if>
		    	 	 <a href="article/${l.id}.htm" target="_blank"><span class="span1">【${l.clTitle!}】</span><span class="span0">${l.ctTitle}</span></a>
		    	 	 
		    	 	 </li>
           			 
           			 <#if l_has_next>
           			 	 <div class="line"></div>
           			 </#if>
           			
		    	 </#list>
		 	     
		 
			  </#if>	
		
		
		</#if>	
		
	   		

</#macro>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link href="${path}/plug-in/web/portals/css/common/common.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/index/style.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/h-ui/lib/layer/2.4/skin/layer.css" rel="stylesheet" type="text/css" />
	
	<title>城建先锋</title>
	
  </head>

<body>
<#include "/common/head.ftl"/>

<div class="container">
  <div class="left">
    <!-- 党群动态  -->
    <div class="News">
      <div class="titleBar">
        <div class="titleLeft"><span class="title1"></span>
        	<@columTitle result=result curr=1 />
        </div>
        <div class="titleRight">
        	<@columTitleLink result=result curr=1 />
        </div>
      </div>   
      <div class="News-inner">
        <!-- 轮播 -->
        <div class="img">	
			<div class="focusBox" style="margin:0 auto">
				<ul class="pic">
					 <!--ads(2)-->
					 <#if (ads?size>=2)>
					 
					 	<#if ads[1].class.simpleName == "ArrayList">
					 		<#list ads[1] as a>
					 			<li><a href="javascript:showBigPhoto('${a.bigPattern!}')"><img src="${a.pattern!}"/></a></li>
					 		</#list>
					 
					    </#if>
					 
					 </#if>											
			
				</ul>
				<div class="txt-bg"></div>
				<div class="txt">
					<ul> <!--ads(2)-->
						<#if (ads?size>=2)>
					 
						 	<#if ads[1].class.simpleName == "ArrayList">
						 		<#list ads[1] as a>
						 			<li><a href="${a.linkUrl!"javascript:;"}">${a.title!}</a></li>
						 		</#list>
						 
						    </#if>
						 
						 </#if>	
					
					</ul>
				</div>
				<ul class="num">
					<#if (ads?size>=2)>				 
						 	<#if ads[1].class.simpleName == "ArrayList">
						 		<#list ads[1] as a>
						 			<li><a>${a_index+1}</a><span></span></li>
						 		</#list>
						 
						    </#if>
						 
						 </#if>
			
		
				</ul>
			</div>				       
        </div>
       <div class="News-text">
          <ul class="text-inner1">
          
          <@contentFirst result=result curr=1 />  
          	
          
        
            
          </ul>
        </div>
      </div>
    </div>
        
    <div class="banner">
    	<!--ads(3)-->
    	<#if (ads?size>=3)>	
    		 <div class="banner1">
		      	<a href="${ads[2].linkUrl!"javascript:;"}"><img src="${ads[2].pattern}" width="323" height="68" /></a>
		     </div>
    		
    	</#if>
    	<!--ads(4)-->
    	<#if (ads?size>=4)>	
    		 <div class="banner2">
		      	<a href="${ads[3].linkUrl!"javascript:;"}"><img src="${ads[3].pattern}" width="323" height="68" /></a>
		     </div>
    		
    	</#if>
   </div>
    
    
     <div class="part3">
      <!-- 党风廉政 -->
      <div class="Box1">
        <div class="titleBar">
          <div class="titleLeft"><span class="title3"></span>
        		<@columTitle result=result curr=2 />
          </div>
          <div class="titleRight">
              	 <@columTitleLink result=result curr=2 />
          </div>
        </div>
        
        <div class="inner">
       
        		<@contentList	result=result curr=2 />  
    
        </div>
      </div>

      <!-- 标杆引路 -->
      <div class="Box2">
        <div class="titleBar">
          <div class="titleLeft"><span class="title4"></span>
        		<@columTitle result=result curr=3 />
          </div>
          <div class="titleRight">
         	    <@columTitleLink result=result curr=3 />
          </div>
        </div>
        <!-- titleBar -->
        <div class="inner2">
 		  
			  
			  <@contentList	result=result curr=3 />  
					  
		
			
        </div>
      </div>
    </div>
  </div>


  
  <div class="right">
    <div class="Note"> 
    	   
		      <div class="titleBar"> <#-- 通知公告-->    
		        <div class="titleLeft"><span class="title2"></span>
		        	
		        	<@columTitle result=result curr=4 />
			        
						       	 
		        </div>
		        <div class="titleRight">
		        	
		        	 <@columTitleLink result=result curr=4 />
		        	
		        </div>
		      </div>
		      
		      <div class="Note-inner">
		         			
					  	  
					  <@contentList	result=result curr=4 />     	    
		            	        
		      </div>  
          
    </div>
    </div>
   
    
    
    <!-- 党务公开  -->
    <div class="Rightbox">
      <div class="titleBar">
        <div class="titleLeft"><span class="title6"></span>
        	<@columTitle result=result curr=5 />
        
        </div>
        <div class="titleRight">
        	 <@columTitleLink result=result curr=5 />
        </div>
      </div>
      <div class="Right-inner">
         
            	<@contentList	result=result curr=5 />  
             
      </div>
    </div>
      
    <!--专题讨论 -->
    <div class="Rightbox">
      <div class="titleBar">
        <div class="titleLeft"><span class="title5"></span>
        	<@columTitle result=result curr=6 />
        </div>
        <div class="titleRight">
       		<@columTitleLink result=result curr=6 />
        </div>
      </div>
      <div class="Right-inner">
               
           	<@contentList	result=result curr=6 />  
           
      </div>
    </div>  
 
  

  
  <!-- 快速通道 -->
  <div class="Passage">
    <div class="Passage-title"></div>
    <a href="${path}/personJoinPartyInfo/178.htm"><div class="Passage1"></div></a>
    <a href="${path}/developPartyMember/108.htm"><div class="Passage2"></div>
    <a href="${path}/subject/33.htm"><div class="Passage3"></div></a>
  </div>
  
   <#--定义最后一行取集合数据的坐标 -->
    <#assign last=7 >
    <div class="display">
    <div class="display-button">

    <#list 7..10 as n>
    		<#if (result?size>=n) >
    		   		  	 	
    		  	 <#if (result[n-1].top.typeId == 3 ) || (result[n-1].top.typeId == 5) || (result[n-1].top.typeId == 6)>
    		  		    <div class="Columns"><a href="javascript:;">${result[n-1].top.title}</a></div>
    		  		     <#assign last=n+1>
    		  	  </#if>		
    		</#if>	    		
    		
    </#list>
    
     

    </div>
    <!--display-button-->
    <div class="display-inner">
    
		 <#list 7..10 as n>
	    		<#if (result?size>=n) >
	    		   		  	 	
	    		  	 <#if (result[n-1].top.typeId == 3 ) || (result[n-1].top.typeId == 5) || (result[n-1].top.typeId == 6)>
	    		  		  
	    		  		   <div class="Columns1-inner">
						      <div class="Left-arrow"></div>
						      <div class="pic-inner">
							      <ul>
							      	  <#list result[n-1].innner as l>	
							        
									      <li class="pic-inner1">
									      <div class="pic-img">
									      	<a href="${path}/article/${l.id}.htm">
									      	<img src="${l.pattern!}" width="210" height="132" />
									      	</a>
									      </div>
									      <div class="pic-text">${l.ctTitle}</div>
									      </li>		    
									      		   
							      	  </#list>
							      </ul>
						      </div>
						      <a href="${path}/subject/${result[n-1].top.colId}.htm"><div class="more"></div></a>
						      <div class="Right-arrow"></div>
					      </div>
	    		  		  
	    		  		  

	    		  	  </#if>		
	    		</#if>	    		
	    		
	    </#list>

    
   
     
     
    </div>
    <!--display-inner--> 
  </div>
  
 

 
  <div class="Last-part"> 
    <!-- 百宝箱 -->
    <div class="Last-part1">
      <div class="titleBar">
	      <div class="titleLeft"><span class="title7"></span>
	      		 <@columTitle result=result curr=last />
	      </div>
	      <div class="titleRight">
	      		 <@columTitleLink result=result curr=last />
	      </div>
      </div>
      <div class="Last-inner1">
	      <div class="pic-button">
	      <div class="pic-button1" img-src="${path}/plug-in/web/portals/image/partyFlag.jpg"></div>
	      <div class="pic-button2" img-src="${path}/plug-in/web/portals/image/partyLogo.jpg"></div>
	      <!--ads(6)-->
	       <#if (ads?size>=6)>
	     	 <div class="pic-button3" data-url="${ads[5].linkUrl!'javascript:;'}" ></div>
	       </#if>
	      <div class="pic-button4" data-text="&emsp;&emsp;我志愿加入中国共产党，拥护党的纲领，遵守党的章程，履行党员义务，执行党的决定，严守党的纪律，保守党的秘密，对党忠诚，积极工作，为共产主义奋斗终身，随时准备为党和人民牺牲一切，永不叛党。"></div>
	      </div>
	      <div class="inner1-Last">
	         
	           <@contentList  result=result curr=last />             
	        
	      </div>
     </div>
    </div>
    
    <!-- 党建搭台 业务唱戏 -->  
    <div class="Last-part2">
      <div class="titleBar">
        <div class="titleLeft"><span class="title8"></span>
        	<@columTitle result=result curr=last+1 />
        </div>
        <div class="titleRight">
        	 <@columTitleLink result=result curr=last+1 />
        </div>
      </div>
      <!-- titleBar -->
      <div class="Last-inner2">
         
            <@contentList	result=result curr=last+1 />  
             
      </div>
    </div>     
     
    <!--党务超市--> 
    <div class="Last-part3">
      <div class="titleBar">
        <div class="titleLeft"><span class="title8"></span>党务超市</div>
        <div class="titleRight"><a src="#">+MORE</a></div>
      </div>
      <div class="Last-inner3">
        <ul class="inner-box">
           <!--ads(7)-->
	       <#if (ads?size>=7)>
          	<li><div class="life"><a href="${ads[6].linkUrl!"javascript:;"}"><span class="icon1"></span>${ads[6].title!}<span class="go"></span></a></div></li>
           </#if>
           <!--ads(8)-->
	       <#if (ads?size>=8)>
         	 <li><div class="volunteer"><a href="${ads[7].linkUrl!"javascript:;"}"><span class="icon2"></span>${ads[7].title!}<span class="go"></span></a></div></li>
           </#if>
           
           <!--ads(9)-->
	       <#if (ads?size>=9)>
          	<li><div class="Contribute"><a href="${ads[8].linkUrl!"javascript:;"}"><span class="icon3"></span>${ads[8].title!}<span class="go"></span></a></div></li>
           </#if>
           
           <!--ads(10)-->
	       <#if (ads?size>=10)>
           <li><div class="opinion"><a href="${ads[9].linkUrl!"javascript:;"}"><span class="icon4"></span>${ads[9].title!}<span class="go"></span></a></li>
          </#if>
        </ul>
      </div>
    </div> 
  
     
  </div>
  
</div>

<!--ads(5)-->
<#if (ads?size>=5)>	
    	
	<div id="floadAD" class="floadAd piao"> 
		<div class="close"></div>
    	 <a class="item" title="${ads[4].title!}" href="${ads[4].linkUrl!"javascript:;"}" target="_blank"> 
	    	 <img src="${ads[4].pattern!}" />
	 	     <div class="editable-text">${ads[4].title!}</div>	
	    </a>
 
	 </div>   
 	
 
 		
 </#if>

  
</div> 

<#include "/common/footer.ftl"/>

<div class="QR-code">
<div class="close"></div>
</div>

<a href="${path}/activePhotoActivity.do">
<div class="photo-activity">
<div class="close"></div>
</div>
</a>
<#include "/common/js.ftl"/>
<script type="text/javascript" src="${path}/plug-in/web/portals/js/index/jquery.SuperSlide.2.1.1.js"></script> 
<script type="text/javascript" src="${path}/plug-in/h-ui/lib/layer/2.4/layer.js"></script> 
<script type="text/javascript" src="${path}/plug-in/web/portals/js/index/index.js"></script> 


</body>
</html>

