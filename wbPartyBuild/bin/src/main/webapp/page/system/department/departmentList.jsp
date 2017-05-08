<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/page/common/tag/mytags.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>部门列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/page/common/script/mytop.jsp" %>
	<link href="<%=path %>/plug-in/h-ui/lib/zTree/v3/css/zTreeStyle/zzTree.css" type="text/css" rel="stylesheet">

  </head>
  
  
  <body  class="pos-r">


	<div class="pos-a" style="width:250px;left:0;top:0; bottom:0; height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5">
		    <div class="panel panel-default" style="border-bottom:none; border-left: none;border-right: none;overflow: hidden;">
		    	<div class="panel-header">部门导航</div>
		    	<div class="panel-body" style="padding: 5px;">
		    		<ul id="tree" class="ztree" ></ul>
		    	
		    	</div>
		    </div>
		
		
	</div>


   <div style="margin-left:250px;">
		
		<nav class="breadcrumb" >
		 <i class="Hui-iconfont f-16" >&#xe67f;</i>  ${TOP_TITLE} <span class="c-gray en">&gt;</span>
		 ${TITLE} <span id="nav-line"></span>
		</nav>
		
		

	   <div class="Hui-article" style="margin-left:250px;">
			<div class="pd-20">
								
				<div class="t_ctrlbar">
				 <span class="btns">
					  <m:a_top context="新建部门"  operCode="skipAddDepartment" funMethod="dep_add('新建部门','','')"  className="btn btn-primary radius depart-btn"/>			  
					  <m:a_top context="删除" operCode="deleteDepartment"  funMethod="datadel()" className="btn btn-danger radius depart-btn" />
					 
					  <m:a_top context="新建人员关联"  operCode="skipAddJobDepartRelation"  funMethod="jobDepart_add('新建人员关联','','')"  className="btn btn-primary radius jdr-btn hidden"/>			  
					  <m:a_top context="删除" operCode="deleteJobDepartRelation"  funMethod="jdrdatadel()" className="btn btn-danger radius jdr-btn hidden" />					 					 
				 </span>
				 
				 		 
			    <span class="select-box ml-20" style="width: 120px">
					<select id="change-table" class="select">
						<option value="0" selected="selected">部门设置</option>
						<option value="1">职务人员设置</option>
					
					</select>
				</span>
				   
				</div>
				
				
					<div id="depart-area">
						<table id="depart_table" class="table table-border table-bordered table-bg">
						</table>
					</div>
					
					<div id="jdr-area" style="display: none;">
						<table id="job_table" class="table table-border table-bordered table-bg" >
						</table>
					</div>
				
				
			</div>
				
	 </div>
	 
	 
	 </div>	
	 	
	
		<input type="hidden" id="parId" value="">
		<input type="hidden" id="parName" value="">
		<input type="hidden" id="node_Level" value="0">		
	
		<%@include file="/page/common/script/mybottom.jsp" %>
		<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 	 	
	
		
		<m:a_button operCode="departmentDetail"  funMethod="dep_edit('编辑','{0}','','')" context="编辑" varName="edit_dep" className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="deleteDepartment"  funMethod="dep_del(this,'{0}')" context="删除" varName="del_dep" className="t-btn btn size-MINI btn-danger-outline"/>	
			
			
		<m:a_button operCode="jobDepartRelationDetail"  funMethod="jobDepart_edit('编辑','{0}','','')" context="编辑" varName="edit_jdr" className="t-btn btn size-MINI btn-primary-outline"/>
		<m:a_button operCode="deleteJobDepartRelation"  funMethod="jobDepart_del(this,'{0}')" context="删除" varName="del_jdr" className="t-btn btn size-MINI btn-danger-outline"/>	
		<m:a_button operCode="userDetail"  funMethod="member_edit('人员编辑','{0}','','')" context="人员编辑" varName="user_edit" className="t-btn btn size-MINI btn-primary-outline"/>		
			
		<script type="text/javascript" >
			   var mtable;


				var setting = {
						async: {
							enable: true,
							url:path+"/departmentController/getDepTree.do",
							autoParam:["id=parId", "level=level"]
						},
						view: {
							dblClickExpand: false,
							showLine: true,
							selectedMulti: false
						},
						data: {
							simpleData: {
								enable:true,
								idKey: "id",
								pIdKey: "pId",
								rootPId: ""
							}
						},
						callback: {
							onClick: zTreeOnClick
						}
					};
		
		var navHtml = "<span class=\"c-gray en\">&gt;</span>{0}";
		var navTemp = "";
	
		function zTreeOnClick(event, treeId, treeNode) {
			    var le = treeNode.level;
		   			
			 	$("#node_Level").val(le+1);
			    $("#parId").val(treeNode.id);
				$("#parName").val(treeNode.name);
				navTemp= "";
				cyclyParentNode(treeNode);
				$("#nav-line").html(navTemp);
	
				var gw = $("#change-table").val();
				if(gw == 0){
					goSearch();
				}else if(gw == 1){
					goSearchJdr();
				}
				
			
		}
	
		/*添加树节点*/
		function addNodeItem(id,name){
			var le = $("#node_Level").val();
			var par = $("#parId").val();
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var parNode = null;
			var isParent = false;
			if(par){
				parNode = treeObj.getNodeByParam("id", par, null);
			}else{
				par = null;
				isParent = true;
			}
							
			var newNode = {"id":id,"isParent":isParent,"level":le,"name":name,"pid":par};
		    treeObj.addNodes(parNode, newNode);
			
		}
	
		/*删除部门数节点*/
		function deleteNodeItem(dl){
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			for (var i=0;i<dl.length;i++) {
				var node = treeObj.getNodeByParam("id", dl[i], null);
				treeObj.removeNode(node);
			
			}
		}



		/*向上遍历获取父节点们的名称*/
		function cyclyParentNode(treeNode){
			 navTemp = navHtml.format(treeNode.name) + navTemp;
			 
			if(treeNode.getParentNode()!=" undefined" && treeNode.getParentNode()!=null){			
				cyclyParentNode(treeNode.getParentNode(),navTemp);
			}
			
		
		}



		$(function(){
			 $.fn.zTree.init($("#tree"), setting);
			 
			 mtable = $("#depart_table").dataTable({
			    "sAjaxSource": path+"/departmentController/pageList.do",
			    "sServerMethod":"post",
			    "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
	            "bServerSide" : true, //是否启动服务器端数据导入  
	            "bStateSave" : false, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  	           
	            "aLengthMenu" : [10, 20, 40,60], //更改显示记录数选项  
	            "bLengthChange" : true,// 每行显示记录数 
	            "iDisplayLength" : 10, //默认显示的记录数  
	            "bAutoWidth" : false, //是否自适应宽度  
	            "bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变  
	            "bPaginate" : true, //是否显示（应用）分页器  
	            "bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
	            "sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页  
	            "bSort" : true, //是否启动各个字段的排序功能  
	            "aaSorting" : [[1, "asc"]], //默认的排序方式，第1列，升序排列  
            	"bFilter" : false, //是否启动过滤、搜索功能
				"aoColumnDefs": [
				{
				 sDefaultContent: '',
				 aTargets: [ '_all' ]
				  },
				{"visible": false, "aTargets": [1]} 
				 
				],
			    "aoColumns": [
			    			   { "mDataProp":"id" ,"bSortable": false,"sWidth":"18px",
			    			   	  "mRender": function (data, type, full) {
			    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
			    			   	  
			    			   	  }
			    			   },    
			    			   {"mDataProp":"sortNum"},                        
                               { "sTitle": "部门名称","sWidth":"150px","mDataProp":"departName" },                     
                               { "sTitle": "部门机构代码", "sWidth":"100px","mDataProp":"orgCode" },
                               { "sTitle": "描述", "sWidth":"200px","mDataProp":"description" },                            
                               { "sTitle": "上次操作时间","sWidth":"130px","mDataProp":"lastOperatorTime"},                         
                               {
                                   "sTitle": "操作",
                                   "mDataProp":"id" , 
                                   "sWidth":"45px",                             
                                   "bSortable": false,
                                   "mRender": function (data, type, full) {
                                                          		 
                                      	var html = "<div class=\"t-btn-container\">";                            		
	                              		if(typeof(edit_dep)!= "undefined"){
	                              			 if(edit_dep.length>0){
	                              			 	html+=edit_dep.format(data);
	                              			 }                            			
	                              		}	                              		
	                              		if(typeof(del_dep)!= "undefined"){
	                              			 if(del_dep.length>0){
	                              			 	html+=del_dep.format(data);
	                              			 }
	                              			
	                              		}
                              	                             	                             	
                                                          	                             	
                                        return html+"</div>";
                                   }
                               }
               ],
               "fnServerParams" : function (aoData) {
					aoData.push(
					 { "name": "parId", "value": $("#parId").val() }
						
					);
				
				},
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
                    "sFirst" : "第一页",    
                    "sPrevious" : "上一页",    
                    "sNext" : "下一页",    
                    "sLast" : "最后一页"    
                }  
            }
             
			
			});
			
			
					
			$('.table-sort tbody').on( 'click', 'tr', function () {
				if ( $(this).hasClass('selected') ) {
					$(this).removeClass('selected');
				}
				else {
					table.$('tr.selected').removeClass('selected');
					$(this).addClass('selected');
				}
			});	
			
		});
		
	
		
		/*部门-添加*/
		function dep_add(title,w,h){ 
			var url = path+"/departmentController.do?add";
			layer_show(title,url,w,h);
		}
		
		/*部门-修改*/
		function dep_edit(title,id,w,h){
		     var url = path+"/departmentController.do?detail&depId="+id;
			layer_show(title,url,w,h);
		}
		
		
		/*部门-删除*/
		function dep_del(obj,id){
			layer.confirm('确认要删除吗？',function(index){
			      sumbitDel(id);
			});
		}
		
		function datadel(){
		   var l = new Array();
		         
	       $("#depart_table .checkbox:checked").each(function() {		
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
				    url: path+"/departmentController/deleteDepartment.do",
				    traditional:true,
				    dataType:'json',  
				    data:{
				     "depIds":ids
				    },
				    type:"post",
				    cache : false,  
				    async : false,  
				    success:function(data){	
				    		if(data.status == "y" || data.status == "Y"){
				    			deleteNodeItem(data.backVal);			    			
				    						    			
				    			layer.msg(data.info,{icon: 1,time:1000});
				          		goSearch();	
				    		}else{
				    			layer.msg(data.info,{icon: 2,time:2500});
				    		}	    					         				           
				                		           
				       },  
				     error : function(error) {  
				            alert(error); 		            
				       } 
			  });
			
		
		}
		
		
		
		
		function goSearch(){
		
			mtable.fnDraw();
			
		
		}
				
		
		
		$("#change-table").on("change",function(e){
				
			  if($(this).val() == 0){
			   	 $(".depart-btn").show(); 
			   	 $(".jdr-btn").addClass("hidden"); 
			   	 
			   	 
			   	 $("#jdr-area").hide(); 
			   	 $("#depart-area").show();
			   	 
			   	 goSearch();
			  
			  }else if($(this).val() == 1){
			  	 if(!isLoadJtable){
			  	 	 initJobPersonList();
			  	 }else{
			  	 	goSearchJdr();
			  	 }
			  	 
			  	 $(".depart-btn").hide(); 
			  	 $(".jdr-btn").removeClass("hidden"); 
			   	 
			   	 
			   	 $("#jdr-area").show(); 
			   	 $("#depart-area").hide();
			  	
			  }
		
		});
		
		
		
		
		
		
			
		var jtable = null;
		var isLoadJtable = false;
		
		function initJobPersonList(){
		  
		  jtable = $("#job_table").dataTable({
			    "sAjaxSource": path+"/jobDepartRelationController/pageList.do",
			    "sServerMethod":"post",
			    "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
	            "bServerSide" : true, //是否启动服务器端数据导入  
	            "bStateSave" : false, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  	           
	            "aLengthMenu" : [10, 20, 40,60], //更改显示记录数选项  
	            "bLengthChange" : true,// 每行显示记录数 
	            "iDisplayLength" : 10, //默认显示的记录数  
	            "bAutoWidth" : false, //是否自适应宽度  
	            "bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变  
	            "bPaginate" : true, //是否显示（应用）分页器  
	            "bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
	            "sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页  
	            "bSort" : true, //是否启动各个字段的排序功能  
	            "aaSorting" : [[1, "asc"]], //默认的排序方式，第1列，升序排列  
            	"bFilter" : false, //是否启动过滤、搜索功能
				"aoColumnDefs": [
				{
				 sDefaultContent: '',
				 aTargets: [ '_all' ]
				  }
				 
				],
			    "aoColumns": [
			    			   { "mDataProp":"id" ,"bSortable": false,"width":"18px",
			    			   	  "mRender": function (data, type, full) {
			    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
			    			   	  
			    			   	  }
			    			   },                        
                               { "sTitle": "职务","width":"120px","mDataProp":"jobName" },                     
                               { "sTitle": "人员名称", "width":"120px","mDataProp":"userName" },
                               { "sTitle": "备注", "width":"200px","mDataProp":"memo" },                            
                               { "sTitle": "状态","width":"100px","mDataProp":"status",
	                                    "mRender": function (data, type, full) {
	                             		  if(data == 1){
	                             		  	return " <span class=\"label label-success radius\">在册</span>";
	                             		  }else{
	                             		  	return " <span class=\"label label-defaunt radius\">不在册</span>";
	                             		  }	
	          
	                                  }
	                               
	                                },                         
                               {
                                   "sTitle": "操作",
                                   "mDataProp":"id" , 
                                   "width":"70px",                             
                                   "bSortable": false,
                                   "mRender": function (data, type, full) {
                                                          		 
                                      	var html = "<div class=\"t-btn-container\">";                               		
	                              		if(typeof(edit_jdr)!= "undefined"){
	                              			 if(edit_jdr.length>0){
	                              			 	html+=edit_jdr.format(data);
	                              			 }                            			
	                              		}
	                              		if(typeof(user_edit)!= "undefined"){
	                              			 if(user_edit.length>0){
	                              			 	html+=user_edit.format(full.userId);
	                              			 }                            			
		                              	}	                              		
	                              		if(typeof(del_jdr)!= "undefined"){
	                              			 if(del_jdr.length>0){
	                              			 	html+=del_jdr.format(data);
	                              			 }
	                              			
	                              		}
                              	                             	                             	
                                                          	                             	
                                        return html+"</div>";
                                   }
                               }
               ],
               "fnServerParams" : function (aoData) {
					aoData.push(
					 { "name": "depId", "value": $("#parId").val() }
						
					);
				
				},
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
                    "sFirst" : "第一页",    
                    "sPrevious" : "上一页",    
                    "sNext" : "下一页",    
                    "sLast" : "最后一页"    
                }  
            }
		
		   });
		   
		   isLoadJtable = true;
	
		   
		}
		
		function jobDepart_add(title,w,h){ 
			var par = $("#parId").val();
			
			if(par){
				var url = path+"/jobDepartRelationController.do?add";
				layer_show(title,url,w,h);
			}else{
				 layer.msg('没有选择部门不可新建人员关联!',{icon: 2,time:1000});
			}		
			
		}
		
		
		function jobDepart_edit(title,id,w,h){
			var url = path+"/jobDepartRelationController.do?detail&rid="+id;
			layer_show(title,url,w,h);
		
		}
	
		

		function member_edit(title,id,w,h){
		     var url = path+"/userController.do?detail&uid="+id+"&oper=edit";
			layer_show(title,url,w,h,true);
		}


		function jobDepart_del(obj,id){
			layer.confirm('确认要删除吗？',function(index){
			      sumbitJdrDel(id);
			});
		}
		
		function jdrdatadel(){
		   var l = new Array();
		         
	       $("#job_table .checkbox:checked").each(function() {		
			  l.push($(this).val());		
		   });
		   	 
		   if(l.length > 0){
			   	layer.confirm("确认要删除 "+l.length+" 条记录吗？",function(index){
				    sumbitJdrDel(l);
				});
		   }else{
		     layer.msg('无选中的项!',{icon: 0,time:1000});
		   }
		}
		
		function sumbitJdrDel(ids){
			$.ajax({
				    url: path+"/jobDepartRelationController/deleteJobDepartRelation.do",
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
				          		goSearchJdr();
				          			
				    		}else{
				    			layer.msg(data.info,{icon: 2,time:2500});
				    		}	    					         				           
				                		           
				       },  
				     error : function(error) {  
				            alert(error); 		            
				       } 
			  });
			
		
		}
		
		
		function goSearchJdr(){
			jtable.fnDraw();
		}
		
		</script>
		
</body>
  
  
  
</html>
