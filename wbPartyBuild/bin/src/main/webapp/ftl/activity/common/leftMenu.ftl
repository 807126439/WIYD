 
<#macro colMenu data curr>
 
<li class="sidemenu-menu <#if data.isOpen>active<#elseif data.colId = curr>active</#if>"> 
    <span class="sidemenu-tt">            
        <i class="icon_fold"></i>
        <#if data.linkUrl??>
        <a href="${data.linkUrl}">							            	
        <#else>
        <a href="${path}/subject/${data.colId}.htm">
        </#if>
        ${data.title}</a>
    </span>
    <ul class="sidemenu-list">
    	<#list data.childList as child>
    			       			       	       			         	       	              			       		       				       			
       			<#if child.isHasChildren >     			
       				 <@colMenu data=child curr=curr />  
       			<#else>
   				   <li class="sidemenu-item <#if child.colId = curr>active</#if>" >				        				       		
			        	<#if child.linkUrl??>
			            	<a href="${child.linkUrl}">							            	
			            <#else>
			            	 <a href="${path}/subject/${child.colId}.htm">
			            </#if>		        
			        	${child.title}</a>
				    </li>   
       				 
       			</#if>
 	       			
 	       		
		</#list>
    </ul>
 
 </li>
 
 
 
 
</#macro>
 
 
 
 
 <div class="sidemenu">
	     
		        <div class="sidemenu-header clearfix">
		    	<a href="${path}/subject/${result.topColumn.colId}.htm"> 
		            <div class="sidemenu-uppercase">${result.topColumn.upperFirst!}</div>
			            <div class="sidemenu-headertitle">
			              <p class="sm-ht_zh">${result.topColumn.title!}</p>
			              <p class="sm-ht_en" >${result.topColumn.alias!}</p>
			            </div>
		            </a>
		        </div>
	        
	                
	        
		        <ul class="sidemenu-list">
		      
			         <#list result.childColums as child>  
			               	
			         	<#if child.isHasChildren >
			         	     <@colMenu data=child curr=result.currColumn.colId />   
			     	       		
					     <#else>     
					        <li class="sidemenu-item <#if child.colId = result.currColumn.colId>active</#if>" >
					        				       		
					        	<#if child.linkUrl??>
					            	<a href="${child.linkUrl}">							            	
					            <#else>
					            	 <a href="${path}/subject/${child.colId}.htm">
					            </#if>
					        
					        	${child.title}</a>
					        </li>        		
			         	</#if>
			         	
			         </#list>
		          
		        </ul>
	
	      </div><#--sidemenu-->