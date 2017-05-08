<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>栏目列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	  <style type="text/css">
	    #lanmu_table tr td:first-child{text-align: left;padding-right:0;}
			.grid-contrl{display: inline-block;height: 22px;width:0;vertical-align: top;text-align: center;position: relative;cursor: pointer;}
			.grid-contrl:before{content:'-';display: inline-block;height: 12px;width:12px;line-height:10px;font-size:12px;background:#333;color:#FFF;float: left;font-style:normal;text-indent:0px;vertical-align: top;margin-top:5px;position:absolute; right:5px;}
			.grid-contrl.closed:before{content:'+';}
			.level-1 td:first-child{text-indent:12px;}
			.level-2 td:first-child{text-indent:21px;}
			.level-3 td:first-child{text-indent:30px;}
			.level-4 td:first-child{text-indent:39px;}
		 #lanmu_table tbody tr{display: none;}
		 /*#lanmu_table thead th{background-color: #FFE4E1;}*/
   	     #lanmu_table .level-1{display: table-row;}	
   	     
   	     .touchTd{cursor: pointer;}
	  </style>
  </head>
  
  
  <body>
	<%@include file="/page/common/nav/breadcrumb.jsp" %>

	 <div class="Hui-article" >
		<div class="page-container">
								
				<div class="t_ctrlbar"> 
				 <span class="btns">			 
				   	 <m:a_top context="添加栏目"  operCode="skipAddColumn"  funMethod="column_add('添加栏目','','')"  className="btn btn-primary radius"/>				 					     							    
				 </span> 
				 
			
				</div>
				
				
				
			
				<table id="lanmu_table" class="table table-border table-hover table-bg table-sort">
					<thead>
						<tr class="text-c">
							<!--  <th width="25"><input type="checkbox" id="checkAll"></th>-->
							<th>序号</th>		
							<th class="text-l">标题</th>
							<th>级别</th>
							<th>栏目类型</th>
							<th>导航项</th>
							<th>置于首页</th>
							<th>首页排序</th>
							<th>显示类型</th>
							<th>操作</th>							
						</tr>	
										
					</thead>
					<tbody>
						<c:forEach items="${lanMuList}" var="l" varStatus="vs"> 
							<tr class="text-c level-${l.level }" data-level="${l.level }">
								<!-- <td>
									<input type="checkbox" class="columnBox" value="${l.id}"  name="roleIds"  >
								</td>-->
								<td width="60"><c:if test="${l.level<lanMuList[vs.index+1].level}"><i class="grid-contrl closed"></i></c:if>${l.sortNum}</td>
								<td class="text-l touchTd">
									<c:choose>
										<c:when test="${!empty l.linkUrl}">
											<a href="${l.linkUrl}" target="_blank" class="${l.level>1 ? 'c-warning':'c-error'}">
												${l.title2}
											</a>
										</c:when>
										<c:otherwise>
											<a href="<%=path %>/subject/${l.id}.htm" target="_blank"  class="${l.level>1 ? 'c-warning':'c-error'}">
												${l.title2}
											</a>
										</c:otherwise>
									</c:choose>								
									
									
								</td>
								<td width="60">${l.level}级</td>
								<td width="100">
									${l.typeText}
								</td>
								<td width="60">
									<c:choose>
										<c:when test="${l.isIndexNav eq true}">
											<span class="label label-secondary radius">是</span>
										</c:when>
										<c:otherwise>	
											<span class="label label-defaunt radius">否</span>										
										</c:otherwise>
									
									</c:choose>
							
								</td>
								<td width="60">
									<c:choose>
										<c:when test="${l.isIndex eq true}">
											<span class="label label-success radius">是</span>
										</c:when>
										<c:otherwise>	
											<span class="label label-defaunt radius">否</span>										
										</c:otherwise>
									
									</c:choose>
							
								</td>
															
								<td width="60">${l.indexNum}</td>									
								<td width="70">${l.showTypeText}</td>
								
								<td class="td-manage" width="340">
								 	<div class="t-btn-container">
										 <c:if test="${l.isAllowEdit}">
											<m:a_mini_button context="内容管理" operCode="contentManage" funMethod="view_content('${l.id}','	${l.typeId}','${l.title}')"  />
											
																			
											<c:if test="${l.level<=3}">
												<m:a_mini_button context="添加子栏目" operCode="skipAddColumn" funMethod="column_add_child('','','${l.id}')" />
												
											</c:if>
											
											<m:a_mini_button context="修改" operCode="columnMuDetail" funMethod="column_edit('','','${l.id}')" />
											<m:a_mini_button context="删除" operCode="columnMuDetail" funMethod="column_del(this,'${l.id}')" className="t-btn btn size-MINI btn-danger-outline"/>
											
										</c:if>
										<m:a_mini_button context="设置权限" operCode="skipAddColumnMuEditer" funMethod="column_addEditer('','','${l.id}')" />
									</div>
								
								</td>						
							</tr>					
						</c:forEach>
					
					
					
					</tbody>
				</table>
				
			</div>
			
		</div>
			
		<%@include file="/page/common/script/mybottom.jsp" %>
			

	 	<script type="text/javascript" >
	 		$(function(){
	 		
					$('#lanmu_table').on('click','.grid-contrl',function() {
				  		$(this).toggleClass('closed');
				  		updataRowVis($(this).closest('tr'));
				  	});
					
					
					$('#lanmu_table tbody tr').on('click','.touchTd',function() { 
						$(this).parent().find('.grid-contrl').toggleClass('closed');
				  		updataRowVis($(this).closest('tr'));
				  	});
					/*								
					  $('#lanmu_table').dataTable({
					    "aaSorting": false, //默认第几个排序
					    "bStateSave": true, //状态保存
					    "bPaginate" : false,
					    "bSort":false
					  });*/
								
					
			});
			
			function column_add(title,w,h){		
				var url = path+"/columnMuController.do?add";
				layer_show(title,url,w,h,true);	
			
			}
			
			
			function column_add_child(w,h,id){		
				var url = path+"/columnMuController.do?add&par="+id;
				layer_show("添加子栏目",url,w,h,true);	
			
			}
			
			
			function column_edit(w,h,id){		
				var url = path+"/columnMuController.do?detail&id="+id;
				layer_show("修改",url,w,h,true);	
			
			}
			//跳转添加编辑者
			function column_addEditer(w,h,id){		
				var url = path+"/columnMuController.do?skipAddEditer&id="+id;
				layer_show("设置栏目权限",url,w,h,true);	
			
			}
			
			
			function view_content(id,typeId,title){		
				var url = path+"/contentController.do?viewPage&columId="+id;	
				
				if(typeId == 1){
					window.top.layer_full("栏目["+title+"]单页面内容",url,'','',false);
				}else{
					layer_full("栏目["+title+"]内容管理",url,'','',true);	
				}
		
			}
			
			
			function column_del(obj,id){
				layer.confirm('确认要删除吗？',function(index){
				   sumbitDel(id);
				});
			}
			
			function datadel(){
			   var l = new Array();
			         
		       $("#lanmu_table .columnBox:checked").each(function() {		
				  l.push($(this).val());		
			   });
			   	 
			   if(l.length > 0){
				   	layer.confirm("确认要删除 "+l.length+" 条记录吗？",function(index){
				   		
				   		sumbitDel(l);
					});
			   }else{
			     layer.msg('无选中的项!',{icon: 0,time:1000});
			   }
			}
			
			function sumbitDel(ids){
				$.ajax({
					    url: path+"/columnMuController/delColumnMu.do",
					    traditional:true,
					    dataType:'json',  
					    data:{
					    	"ids":ids
					    },
					    type:"post",
					    cache : false,  
					    async : false,  
					    success:function(data){	
					    		if(data.status == "y" || data.status == "Y"){
					    			layer.msg(data.info,{icon: 1,time:1000});
					          		location.replace(location.href);	
					    		}else{
					    			layer.msg(data.info,{icon: 2,time:2500});
					    		}	    					         				           
					                		           
					       },  
					     error : function(error) {  
					            alert(error); 		            
					       } 
				  });
				
			
			}
			
			//更新表格某行的子行可视性
			function updataRowVis($start) {
				var level=$start.data('level'),
				    vis=$start.find('.grid-contrl.closed').length===0?'table-row':'none',
				    $row=$start.next(),
				    parentRow=null;
				while ($row.length!==0&&$row.data('level')>level) {
					if (vis==='none'||$row.data('level')===level+1) {
						$row.css('display',vis);
					}else{
						parentRow=getParentRow($row);
						if (parentRow.find('.grid-contrl').hasClass('closed')) {
							$row.css('display','none');
						}else{
							$row.css('display','table-row');
						}
						parentRow=null;
					}
					$row=$row.next();
				}
			}
			
			//换取表格某行的父行元素
			function getParentRow($row){
				var level=$row.data('level'),
				    result=$row.prev();
				while(result.length!==0){
					if (result.data('level')===level-1) {
						return result;
					}
				  result=result.prev();
				}
			}
			
			
	 	
	 	</script> 
		
</body>
  
  
  
</html>
