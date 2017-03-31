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
    
    <title>任务详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	
  </head>
  
  
  <body>
  
  
  <div class="pd-20">
  	<table class="table table-border table-bordered table-striped">
					
			<tr>
				<th>任务名称</th>
				<td>${info.name}</td>

			</tr>
			<tr>
				<th width="20%">任务说明</th>
				<td width="80%">${info.taskExplain}</td>

			</tr>
		
		
	</table>
    <input type="hidden"  id="taskId" value="${info.id}">
  </div>
  
  
  
  
	<div class="pd-20">
	  
  		<div class="cl pd-5 bg-1 bk-gray mt-20">		
		  <span class="l">
			<m:a_top context="上传文件" operCode="delDict" icon="&#xe600;" funMethod="uploadFile()" className="btn btn-primary radius" />
		  
		  </span>
		
		</div>
	  
	  	
  		  <table id="fj-table" class="table table-border table-bordered table-hover">
   		  </table>

	  
	</div>
		

	
	
	<%@include file="/page/common/script/allbuttom.jsp" %>
	<m:a_button operCode="downAttachment"  funMethod="downFile('{0}')" context="下载" varName="file_down" />
						

				

 	<script type="text/javascript">
 		var mtable;
		$(function(){
		
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
	            "aaSorting" : [[2, "asc"]], //默认的排序方式，第2列，升序排列  
            	"bFilter" : false, //是否启动过滤、搜索功能
				"aoColumnDefs": [
				{
				 sDefaultContent: '',
				 aTargets: [ '_all' ]
				  }
				],
			    "aoColumns": [
			    			
                               { "sTitle": "附件名称", "sClass": "center","mDataProp":"name" },                     
                               { "sTitle": "上传时间", "sClass": "center","mDataProp":"createTime" },
  
                               {
                                   "sTitle": "操作",
                                   "sClass": "center",  
                                   "mDataProp":"id" ,                              
                                   "bSortable": false,
                                   "mRender": function (data, type, full) {
                              
                              			var html = "";                             		
	                              		if(typeof(file_down)!= "undefined"){
	                              			 if(file_down.length>0){
	                              			 	html+=file_down.format(data);
	                              			 }                            			
	                              		}	                              		
                             	                             	                             	
                                        return html;
                                   }
                               }
               ],
              "oLanguage": { //国际化配置  
                "sProcessing" : "正在获取数据，请稍后...",    
                "sLengthMenu" : "显示 _MENU_ 条",    
                "sZeroRecords" : "没有您要搜索的内容",    
                "sInfo" : "显示 _START_ 到  _END_ 条 ，共 _TOTAL_ 条",    
                "sInfoEmpty" : "记录数为0",    
                "sInfoFiltered" : "(当前显示记录 _MAX_ 条)",    
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
		
		
			
	});
 	
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
 	
 		
 	function downFile(id){
 	  if(id){
 	    window.location.href = path+"/attachment/downAttachment.do?aid="+id;	  
 	  }
 	}
 	
 	</script>
 
 
 
 
 
   
		
</body>
  
  
  
</html>
