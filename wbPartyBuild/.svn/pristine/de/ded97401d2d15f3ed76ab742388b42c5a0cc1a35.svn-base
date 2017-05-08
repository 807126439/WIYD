<#--入党信息统计-->



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
	        
		       <div class="mod-content">
		          <div class="infowrapper">
		            <div class="joinpartyinfo-tt">
		              <div class="personalinfo1">个人信息</div>
		              <div class="submittedinfo">已提交的资料</div>
		              <div class="uploadedinfo">上传的资料</div>
		            </div>
		          </div>
		            
		          <#if page.list?size gt 0>  
		            <ul class="joinpartyinfo" id="joinpartyinfo-list">
		               <#list page.list as info>
			              <li>
			                <div class="personalinfo1">
			                  <span class="personalinfo1-name">${info.applyUserName!}</span>
			                  <span class="personalinfo1-belong">(${info.department!})</span><br/>
			                  <span class="personalinfo1-state">${info.currNode!}</span>
			                </div>
			                <div class="submittedinfo">
			                  <ul>
			                  	  <#list info.applyFiles as af>
			                    	<li><a href="javascript:;" onClick="downFile('${af.id}')">${af.name!}</a></li>
			                     </#list>
			                  </ul>
			                </div>
			                <div class="uploadedinfo">
			                  <ul>
			                     <#list info.handleFiles as hf>
			                    	<li><a href="javascript:;" onClick="downFile('${hf.id}')">${hf.name!}</a></li>
			                     </#list>
			                  </ul>
			                </div>
			              </li>
		               </#list>
		             
		            </ul>
		            <div id="page1" class="page1 text-r">
		            </div>
		            <#else>
		        	    无相关的记录。
		            </#if>
		         </div>
	          <!-- infowrapper -->
	        </div>
	        
	      </div><#-- mod end -->
	      
	      
	    </div><#--clearfix end-->
    
  </div><#--container end-->
  
   

  <input id="path" value="${path}" type="hidden"/>
  <input id="ctTotalPage" value="${page.totalPage!'-1'}" type="hidden"/>
  <input id="ctCurPage" value="${page.currentPage!'-1'}" type="hidden"/>

  
  
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/menu-change.js"></script>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/queryPartyPage.js"></script>
	
	
	<script type="text/javascript">
		
	    function downFile(id){
	      if(id){
	        window.location.href=path+"/attachment/downAttachment.do?aid="+id;
	      }
	      
	    }
	  
	    
	  
     </script>
</body>

</html>

