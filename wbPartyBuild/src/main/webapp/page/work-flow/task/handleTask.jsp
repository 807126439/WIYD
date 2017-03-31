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
    
    <title>办理任务</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	
	<style type="text/css">
		.red-back{color: #FF4040}
	
	</style>
  </head>
  
  
  <body>
  
  
  <div class="pd-20">
  	<table class="table table-border table-bordered table-striped">
			<tr>
				<th>流程名称</th>
				<td>${taskItem.processName}</td>
				<th>流程开始时间</th>
				<td><fmt:formatDate value="${taskItem.beginTime}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<th>当前环节</th>
				<td >${taskItem.currNode}</td>
				<th>申请人</th>
				<td>${taskItem.owner}</td>
			</tr>
			<tr>
				<th>任务名称</th>
				<td width="30%">${taskItem.name}</td>
				<th>任务说明</th>
				<td width="40%">${taskItem.taskExplain}</td>
			</tr>	
			<tr>
			   <td colspan="4" class="text-c">
			      <strong> 申请人已上传的附件列表 <span class="c-warning">（红色行表示当前任务上传附件）</span></strong>
			  </td>			
			</tr>
			<tr>
			   <td colspan="4">
			      <table id="apply-fj-table" class="table table-border table-bordered table-hover"></table>
			      <input type="hidden" id="ownerTaskIds" value="${taskItem.ownerTaskIds}">
			  </td>	
			  		
			</tr>
			
		
		
	</table>
   
  </div>
  
  <div class="line"></div>
   <form action="<%=path %>/task/handleTask.do" method="post" class="form form-horizontal" id="form-handle-task">
	 
 	 <div>
  	
	     <div><label class="col-xs-12 col-sm-12"><span class="c-red">*</span>处理意见：</label> </div>
	  	 <div class="row cl">	   
		      <div class="formControls col-xs-12 col-sm-12">
		      	<textarea name="content" class="textarea" id="description"  onKeyUp="textarealength(this,500)" datatype="*5-500" nullmsg="请填写处理意见" ></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/500</p>
		      </div>
		
		    
		  </div>
    
  	</div>
  
  
	<div class="page-container">
	  
  		<div class="t_ctrlbar">	
			<span class="btns">		
			  <m:a_top context="上传文件" operCode="delDict" icon="&#xe600;" funMethod="uploadFile()" className="btn btn-primary radius" />		  
		    </span>
		
		</div>
	  
	  	
  		  <table id="fj-table" class="table table-border table-bordered table-hover">
   		  </table>

	  
	</div>
		
  	<div class="line"></div>
   
    <div class="row cl pb-20">
    
      <div class="col-xs-12 col-sm-12 next-step">
         <label class="col-xs-2 col-sm-2">下一步：</label>
         <div class="col-xs-10 col-sm-10">
           <c:choose>
           	<c:when test="${!empty nodes}">
           		<c:forEach items="${nodes}" var="nd" varStatus="vs">	
	           	  <input class="btn btn-danger radius nextBtn" type="button" value="${nd.nodeName}" data-val="${nd.id}" >
	           </c:forEach>
           	</c:when>
           	<c:otherwise>
           		<input class="btn btn-danger radius nextBtn endProcess" type="button" value="确定流程结束" >
           	</c:otherwise>
           </c:choose>
                   
           
        </div>
     
      </div>
    
    </div>
     
    <input type="hidden"  id="taskId" name="taskId" value="${taskItem.id}">
    <input type="hidden"  id="procInstId" value="${taskItem.procInstId}">
    <input type="hidden" id="choiceNodeId"  name="choiceNodeId"  value="">
   
    </form>
    
    

	
	<%@include file="/page/common/script/allbuttom.jsp" %>

	<m:a_button operCode="downAttachment"  funMethod="downFile('{0}')" context="下载" varName="file_down" />
						

 	<script type="text/javascript">
 		var mtable;
		$(function(){
		
			
		
			$("#apply-fj-table").dataTable({
			    "sAjaxSource": path+"/attachment/getPageData.do",
			    "sServerMethod":"post",
			    "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
	            "bServerSide" : true, //是否启动服务器端数据导入  
	            "bStateSave" : true, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  	           
	            "aLengthMenu" : [10, 20, 40,60], //更改显示记录数选项  
	            "bLengthChange" : false,// 每行显示记录数 
	            "iDisplayLength" : 10, //默认显示的记录数  
	            "bAutoWidth" : false, //是否自适应宽度  			
	            "bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变  
	            "bPaginate" : true, //是否显示（应用）分页器  
	            "bInfo" : false, //是否显示页脚信息，DataTables插件左下角显示记录数  
	            "sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页  
	            "bSort" : true, //是否启动各个字段的排序功能  
	            "aaSorting" : [[1, "asc"]], //默认的排序方式，第2列，升序排列  
            	"bFilter" : false, //是否启动过滤、搜索功能
				"aoColumnDefs": [
				{
				 sDefaultContent: '',
				 aTargets: [ '_all' ]
				  }
				],
			    "aoColumns": [
			    			
                               { "sTitle": "附件名称", "sClass": "center","mDataProp":"name", "bSortable": false,
                                 "mRender": function (data, type, full) {
                              				
                              			if(full.isThisTask){                             			
                              				 return "<span class=\"red-back\">"+data+"</span>";
                              			}else{
                              				 return data;
                              			}	
                              		                	                             	
                                  }     
                                },                     
                               { "sTitle": "上传时间", "width": "150px","mDataProp":"createTime" ,"bSortable": false,
                               	 "mRender": function (data, type, full) {
                              				
                              			if(full.isThisTask){                             			
                              				 return "<span class=\"red-back\">"+data+"</span>";
                              			}else{
                              				 return data;
                              			}	
                              		                	                             	
                                  }  
                                   },
  
                               {
                                   "sTitle": "操作",
                                   "width": "30px",  
                                   "mDataProp":"id" ,                              
                                   "bSortable": false,
                                   "mRender": function (data, type, full) {
                              
                              			var html = "<div class=\"t-btn-container\">";                             		
	                              		if(typeof(file_down)!= "undefined"){
	                              			 if(file_down.length>0){
	                              			 	html+=file_down.format(data);
	                              			 }                            			
	                              		}	                              		
                             	                             	                             	
                                        return html+"</div>";
                                   }
                               }
               ],
              "oLanguage": { //国际化配置  
                "sProcessing" : "正在获取数据，请稍后...",    
                "sLengthMenu" : "显示 _MENU_ 条",    
                "sZeroRecords" : "没有数据记录",    
                "sInfo" : "显示 _START_ 到  _END_ 条 ，共 _TOTAL_ 条",    
                "sInfoEmpty" : "记录数为0",    
                "sInfoFiltered" : "",    
                "sInfoPostFix" : "",    
                "sSearch" : "搜索",    
                "sUrl" : "",    
                "oPaginate": {    
                    "sFirst" : "",    
                    "sPrevious" : "上一页",    
                    "sNext" : "下一页",    
                    "sLast" : ""    
                }  
            },
            "fnServerParams" : function (aoData) {
					aoData.push(
					{ "name": "procInstId", "value": $("#procInstId").val()},
					{ "name": "kind", "value": "apply"},
					{ "name": "taskIds", "value": $("#ownerTaskIds").val()}
					);
					
				
					}
		
		});
		
		
			mtable = $("#fj-table").dataTable({
			    "sAjaxSource": path+"/attachment/getPageData.do",
			    "sServerMethod":"post",
			    "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
	            "bServerSide" : true, //是否启动服务器端数据导入  
	            "bStateSave" : true, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  	           
	            "aLengthMenu" : [10, 20, 40,60], //更改显示记录数选项  
	            "bLengthChange" : false,// 每行显示记录数 
	            "iDisplayLength" : 10, //默认显示的记录数  
	            "bAutoWidth" : false, //是否自适应宽度  			
	            "bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变  
	            "bPaginate" : true, //是否显示（应用）分页器  
	            "bInfo" : false, //是否显示页脚信息，DataTables插件左下角显示记录数  
	            "sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页  
	            "bSort" : true, //是否启动各个字段的排序功能  
	            "aaSorting" : [[1, "asc"]], //默认的排序方式，第2列，升序排列  
            	"bFilter" : false, //是否启动过滤、搜索功能
				"aoColumnDefs": [
				{
				 sDefaultContent: '',
				 aTargets: [ '_all' ]
				  }
				],
			    "aoColumns": [
			    			
                               { "sTitle": "附件名称", "sClass": "center","mDataProp":"name" },                     
                               { "sTitle": "上传时间", "width": "150px","mDataProp":"createTime" },
  
                               {
                                   "sTitle": "操作",
                                   "width": "30px",  
                                   "mDataProp":"id" ,                              
                                   "bSortable": false,
                                   "mRender": function (data, type, full) {
                              
                              			var html = "<div class=\"t-btn-container\">";                             		
	                              		if(typeof(file_down)!= "undefined"){
	                              			 if(file_down.length>0){
	                              			 	html+=file_down.format(data);
	                              			 }                            			
	                              		}	                              		
                             	                             	                             	
                                         return html+"</div>";
                                   }
                               }
               ],
              "oLanguage": { //国际化配置  
                "sProcessing" : "正在获取数据，请稍后...",    
                "sLengthMenu" : "显示 _MENU_ 条",    
                "sZeroRecords" : "没有数据记录",    
                "sInfo" : "显示 _START_ 到  _END_ 条 ，共 _TOTAL_ 条",    
                "sInfoEmpty" : "记录数为0",    
                "sInfoFiltered" : "",    
                "sInfoPostFix" : "",    
                "sSearch" : "搜索",    
                "sUrl" : "",    
                "oPaginate": {    
                    "sFirst" : "",    
                    "sPrevious" : "上一页",    
                    "sNext" : "下一页",    
                    "sLast" : ""    
                }  
            },
            "fnServerParams" : function (aoData) {
					aoData.push(
					{ "name": "taskId", "value": $("#taskId").val() }
					);
					
				
					}
		
		});
		
		
		
		
		$(".next-step .nextBtn").click(function(){
			 $(this).addClass('selected').siblings().removeClass('selected');
			 if($(this).hasClass("endProcess")){
			 	$("#form-handle-task").attr("action",path+"/task/finishProcess.do");
			 }else{
			 	$("#form-handle-task").attr("action",path+"/task/handleTask.do");
			 }
			 
			 $("#form-handle-task").submit(); 
			 
			 
		});
		
		
		$("#form-handle-task").Validform({
			tiptype:3,
			ajaxPost:true,//ajax方式提交表单数据
			beforeSubmit:function(curform){
			    var $curBtn = $(".next-step .selected");
				
			
				if(confirm("确认要进入下一步（"+$curBtn.val()+"）吗？")){
					
 					$("#choiceNodeId").val($curBtn.attr("data-val"));	
				}else{
				  return false;
 				}
			
					
 	      	    
				
						  	 			  	
			},
		
			callback:function(data){				
				if(data.status == "y" || data.status == "Y"){			
					      setTimeout(function(){
					      	parent.goSearch();
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
							      					      
					      },1200); 
					
				}				
			}
		});
		
		
		
			
	});
 	
 	
 	function downFile(id){
 	  if(id){
 	    window.location.href = path+"/attachment/downAttachment.do?aid="+id;	  
 	  }
 	}
 	
 	
 	
 	
 	function goSearch(){	
		mtable.fnDraw();
	}
 	
 	function uploadFile(){
 	  
 	    var url = path+"/attachment/goUploadAttachment.do?taskId="+$("#taskId").val();
			layer.open({
			type: 2,
			area: ['600px', '450px'],
			fix: false, //不固定
			maxmin: false,
			shade:0.4,
			title: "上传附件",
			content: url,
			end:function(){
				goSearch();
			}
		});
 	  
 	}
 	
 	
 
 	
 	
 	
 	</script>
 
 
 
 
 
   
		
</body>
  
  
  
</html>
