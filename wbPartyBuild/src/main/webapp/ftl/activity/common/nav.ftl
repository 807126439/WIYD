 <h2 class="mod-title">
		          <i class="icon_mod"></i>
		          <div class="mod-loc">
		            <div class="loc clearfix">
		              <span>当前位置：</span>
		              <ol>
		                <li class="noactive"><a href="${path}/portals.do">首页</a></li>               
		                 <#list result.navColums as nav>                	
			               
					         <#if nav_has_next>
					         	<li class="noactive"><a href="${path}/subject/${nav.colId}.htm">${nav.title}</a></li> 
					         <#else>
					        	<li class="active"><a href="${path}/subject/${nav.colId}.htm">${nav.title}</a></li> 
					         </#if>                  
			                
		                </#list>  
		             </ol>
		            </div>
		          </div>
		  		${result.currColumn.title}   
	</h2>