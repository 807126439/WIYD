<#--对智慧版块建议-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
			             <h1 class="article-title">8888888</h1>
			
			           </div>
		           
			      		<div class="article-content">
				           kkkkkkk
				         </div>
			         </div>		    
		      
		      </#if> 
	        <div style="width: 700px; height: 700px; border-color: red;margin: 0px auto;margin-top: 20px">
				<form action="${path}/OpinionFeedbackConteroller/addOpinion.do" id="advise-form" method="post" class="form form-horizontal" name="form-Opinionfeedback">	  
					<div style="font-size:24px; text-align: center;"><h3 class="title">市城建局工会反馈建议征集表</h3></div>
						<div>
				        	<table border="1" style="text-align: center;margin-top: 20px;width: 700px">
					        	<tr style="height:50px;">
					        		<td style="width:25%;font-weight: 600;font-size:14px"">申报时间</td>
					  				<td style="width:25%;" ><input type="text" class="input-text text" style="font-size: 14px;border-style:none;height:50px;width:99%;line-height:50px;" id="date"  readonly="readonly" /></td>
					        		<td style="width:25%;font-weight: 600;font-size:14px"">申报主题</td>
					  				<td style="width:25%;"><input type="text" class="input-text text" style="font-size: 14px;border-style:none;height:50px;width:99%;line-height:50px;" id="theme"  name="theme"  /></td>
					        	</tr>
					        	<tr style="width: 500px;height: 180px;">
					  				<td style="font-weight: 600;font-size:14px">反馈意见</td>
					  				<td colspan="3"><textarea class="input-text text" style="font-size: 16px;border-style:none;resize:none;width: 99%;height: 180px;" placeholder="说点什么..."  name="content" id="content" cols="30" rows="10"></textarea></td>
					  			</tr>
					  			<tr style="">
					  				<td colspan="4"><div  style="margin-top: 10px; margin-bottom: 10px;"><input style="border:none;background-color: #CE2C1D;border-radius:3px;width: 86px;height: 38px;font-size: 15px; color:#FFFFFF" type="button" value="提交" onclick="checkVal()" ></input></div>	</td>
					  			</tr>
				     	   	</table>
		      			</div>
					
					<input type="hidden" value="2" name="typeId" />
				</form>
	        </div>
	      </div><#-- mod end -->
	     
			
	      
	    </div><#--clearfix end-->
    
  </div><#--container end-->

  
   
  <input id="path" value="${path}" type="hidden"/>
  <input id="colId" value="${result.currColumn.colId}" type="hidden"/>
 
	<#include "/common/footer.ftl"/>
	<script type="text/javascript" src="${path}/plug-in/h-ui/static/h-ui/js/H-ui.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/menu-change.js"></script>
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript" src="${path}/plug-in/web/scripts/portals/opinionFeedback/addOpinionFeedback.js"></script>


</body>

</html>

