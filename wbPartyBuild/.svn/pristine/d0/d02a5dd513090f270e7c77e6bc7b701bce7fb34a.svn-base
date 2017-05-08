<#--个人入党信息查询-->


<#macro getLiCode x zt>
 
	<#switch x>   
		<#case 0>    
		  <#if zt=0>  
		  	<li class="step-start">   
		  <#elseif zt=1>	
		    <li class="step-start step-active">
		   <#else> 
		   <li class="step-start step-done">    
		   </#if>		  
		<#break>  
		<#case 4>      
			<#if zt=0>  
		  	<li class="step-turn">   
		   <#elseif zt=1>	
		    <li class="step-turn step-active">
		   <#else> 
		   <li class="step-turn step-done">    
		   </#if>	   
		<#break>   
		<#case 5>    
		 	<#if zt=0>  
		  	<li class="step-turn step-reverse">   
		   <#elseif zt=1>	
		    <li class="step-turn step-reverse  step-active">
		   <#else> 
		   <li class="step-turn step-reverse  step-done">    
		   </#if>    
		 <#break> 
		 <#case 6>    
		 	<#if zt=0>  
		  	<li class="step-reverse">   
		   <#elseif zt=1>	
		    <li class="step-reverse  step-active">
		   <#else> 
		   <li class="step-reverse  step-done">    
		   </#if>    
			 <#break> 
		  <#case 7>    
		 	<#if zt=0>  
		  	<li class="step-reverse">   
		   <#elseif zt=1>	
		    <li class="step-reverse  step-active">
		   <#else> 
		   <li class="step-reverse  step-done">    
		   </#if>    
			 <#break>  
		  <#case 8>    
		 	<#if zt=0>  
		  	<li class="step-end step-reverse">   
		   <#elseif zt=1>	
		    <li class="step-end step-reverse step-active">
		   <#else> 
		   <li class="step-end step-reverse step-done">    
		   </#if>    
			 <#break>    
		 <#default>      
		  <#if zt=0>  
		  	<li class="">   
		   <#elseif zt=1>	
		    <li class="step-active">
		   <#else> 
		   <li class="step-done">    
		   </#if> 
	</#switch>	
		
</#macro>

<!DOCTYPE HTML>
<html>
  
	<#include "/activity/common/activity-head.ftl"/>

<body>

	<#include "/common/head.ftl"/>
	
	<div class="container clearfix" >
	
	    <div class="mainplaceholder"></div>
	    
	    <div id="main" class="clearfix">
	    
	    	<#include "/activity/common/leftMenu.ftl"/>
	     
	      <div class="mod">
	      
		       <#include "/activity/common/nav.ftl"/>
	        

	        
	     
	        
	     
	        <#if info??>
	          <input id="currExeId" value="${info.processData.currExeId}" type="hidden"/>
	        <div class="mod-content">
	          <div class="progresswrapper">
	            <div class="personalinfo">
	              <div class="personalinfo-tt">个人信息核实</div>
	              <div class="personalinfo-ct"><span>姓名：${info.applyUserName!}</span><span>部门：${info.department!}</span></div>
	            </div>
	            <ol class="ui-step ui-step-5">
	            
	             <#list info.processData.nodes as nd>
	               
	               <@getLiCode x=nd_index zt=nd.status />
	               
	             	 
		                <div class="ui-step-line"></div>
		                <div class="ui-step-cont">
		                  <span class="ui-step-cont-number">${nd_index + 1}</span>
		                  <span class="ui-step-cont-text">${nd.nodeName}</span>
		                  <#if nd.ownerNode??>
		                  	  <span class="ui-step-tip" data-val="${nd.ownerNode.id}">${nd.ownerNode.nodeName}</span>
		                  </#if>
		                
		                </div>
		              </li>
	             
	             </#list>
             
	            
	            </ol>
	            <span class="tip">提示：红色标注表示正处于的步骤或者已提交的资料</span>
	          </div>
	          <!-- progresswrapper -->
	        </div>
	        
	        <#else> 
	      	 <div class="mod-content"><br>无法相关的记录！</div>
	      
	       </#if>
	        
	      </div><#-- mod end -->
	    
	      
	    </div><#--clearfix end-->
    
  </div><#--container end-->
  
   

  <input id="path" value="${path}" type="hidden"/>
  <input id="colId" value="${result.currColumn.colId}" type="hidden"/>

  
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/menu-change.js"></script>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
	
	<script type="text/javascript">
		var path = $("#path").val();
	
	    $(function() {
	        //$toStep(9);// //跳至第9步
	      	 $('.ui-step li.step-active .ui-step-tip').bind("click",function(){
	            
	             var $span = $(this).prev();
	                     
	        	 var url = path+"/task/getCurrTask.do?procExeId="+$("#currExeId").val()+"&nodeId="+$(this).attr("data-val");
			      layer.open({
						type: 2,
						area: ['800px', '600px'],
						fix: false, //不固定
						maxmin: false,
						shade:0.4,
						title: $span.text(),
						content: url
					});
			    });
 	
	    });
	
	    //跳至第n步
	    function $toStep(n){
	      if (!n) {n=1;}
	      $('.ui-step li').removeClass('step-done').removeClass('step-active');
	      for (var i=1; i <= n; i++)
	        if (i!==n) 
	          $('.ui-step li').eq(i-1).addClass('step-done');
	        else $('.ui-step li').eq(i-1).addClass('step-active');
	    }
	    
	    
	  
     </script>
</body>

</html>

