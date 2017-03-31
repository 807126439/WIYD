<#--合理化建议征集-->

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
	        
	           <#if result.singalContent??>
	           		
	           		<div class="article">
			           <div class="article-header">
			             <h1 class="article-title">${result.singalContent.title!}</h1>
			
			           </div>
		           
			      		<div class="article-content">
				           ${result.singalContent.content!}
				         </div>
				         
				         
				        
			         </div>		
			         
			         
			         <div>
			         	<center>
			         		<a href="javascript:void(0)" onclick="rationalizations()"><img src="${path}/plug-in/web/portals/image/addAdvise.jpg"></a>
			         		</center>
			         
			         </div>    
		      
		      </#if> 
	        
	      </div><#-- mod end -->
	      
	      
	    </div><#--clearfix end-->
    
  </div><#--container end-->
  
   

  <input id="path" value="${path}" type="hidden"/>
  <input id="colId" value="${result.currColumn.colId}" type="hidden"/>
  <input id="name" type="hidden"value="${name!}" />
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/menu-change.js"></script>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
	<script type="text/javascript">
	function rationalizations(){
		 var url = "${path}/OpinionFeedbackConteroller.do?add";
		 var w=700;
		 var h=700;
		 layer.open({
				type: 2,
				area: [w+'px', h +'px'],
				fix: false,
				shade:0.4,
				skin: 'mystyle',
				title: "合理化意见征集",
				content: url,
		 });
	}
	
	</script>
</body>

</html>

