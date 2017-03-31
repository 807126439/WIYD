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
    
    <title>	板块列表</title>
    
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
		    	<div class="panel-header">板块导航</div>
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
			<div class="page-container">
								
				<div class="t_ctrlbar">	
				 <span class="btns">	
				  <m:a_top context="新建板块"  operCode="skipAddBanChunk"  funMethod="dep_add('新建板块','','')"  className="btn btn-primary radius depart-btn"/>			  
				  <m:a_top context="删除" operCode="delBanChunk"  funMethod="datadel()" className="btn btn-danger radius depart-btn" />
				 </span>
				
				 <m:a_top context="获取活动所有稿件" operCode="getAllManuscriptByActivity"  funMethod="getAllManuscriptByActivity()" className="btn btn-primary radius depart-btn" />
				
				</div>
				

				<table id="depart_table" class="table table-border table-bordered table-bg">
				</table>
			
				
				
			</div>
				
	 </div>
	 
	 
	 </div>	
	 	
	
		<input type="hidden" id="treeParId" value="0">
		<input type="hidden" id="parName" value="">
		<input type="hidden" id="node_Level" value="0">
		<input type="hidden" id="activityId" value="0">
	
		<%@include file="/page/common/script/mybottom.jsp" %>
		<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 	 	
	
		
		<m:a_button operCode="skipEditBanChunk"  funMethod="dep_edit('编辑','{0}','','')" context="编辑" varName="edit_dep" />
		<m:a_button operCode="delBanChunk"  funMethod="dep_del(this,'{0}')" context="删除" varName="del_dep" className="t-btn btn size-MINI btn-danger-outline"/>
		<m:a_button operCode="manuscriptManage"  funMethod="manuscript_manage('{0}','{1}','','')" context="稿件管理" varName="manage_manuscript" />	
			
			
			
		<script type="text/javascript" >
			   var mtable;


				var setting = {
						async: {
							enable: true,
							url:path+"/banChunkController/getBanChunkTree.do",
							autoParam:["id=treeParId", "level=level","activityId=activityId"],
							otherParam:{"model":1}
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
								rootPId: null
							},
							key: {
								name:"name"
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
			    $("#treeParId").val(treeNode.id);
				$("#parName").val(treeNode.name);
				$("#activityId").val(treeNode.activityId);
				navTemp="";
				cyclyParentNode(treeNode);
				$("#nav-line").html(navTemp);
				goSearch();
		
		}
	
		/*添加树节点*/
		function addNodeItem(id,name){
			var le = $("#node_Level").val();
			var par = $("#treeParId").val();
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var parNode = null;
			var isParent = false;
			var activityId = null;
			if(par){
				parNode = treeObj.getNodeByParam("id", par, null);
				activityId = parNode.activityId;
			}else{
				par = null;
				isParent = true;
			}
							
			var newNode = {"id":id,"isParent":isParent,"level":le,"name":name,"pid":par,"activityId":activityId};
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
			    "sAjaxSource": path+"/banChunkController/pageList.do",
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
				//{"visible": false, "aTargets": [1]} 
				 
				],
			    "aoColumns": [
			    			   { "mDataProp":"id" ,"bSortable": false,"sWidth":"18px",
			    			   	  "mRender": function (data, type, full) {
			    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
			    			   	  
			    			   	  }
			    			   },   
			    			   {"mDataProp":"sortNum","sTitle": "序号","sWidth":"48px"},                        
                               { "sTitle": "板块名称","sWidth":"120px","mDataProp":"chunkName" },
                               { 
                               "sTitle": "是否公开投稿",
                               "sWidth":"100px",
                               "mDataProp":"status",
                               "mRender": function (data, type, full) {				    					   
				    					   if(data == "0"){
		                             		  	return " <span class=\"label label-defaunt radius\">否</span>";
		                             	   }else{
		                             			return " <span class=\"label label-success radius\">是</span>";
		                             	   }			    					   
				    				   }
                                },
                               { "sTitle": "板块备注","mDataProp":"chunkMemo" },
                               { "sTitle": "上次操作时间","sWidth":"150px","mDataProp":"lastOperatorTime"},                         
                               {
                                   "sTitle": "操作",
                                   "mDataProp":"id" , 
                                   "sWidth":"130px",                             
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
	                              		if(typeof(manage_manuscript)!= "undefined"){
		                              			 if(manage_manuscript.length>0){
		                              			 	html+=manage_manuscript.format(full.id,full.chunkName);
		                              			 }                            			
		                              		}
                              	                             	                             	
                                     return html+"</div>";
                                   }
                               }
               ],
               "fnServerParams" : function (aoData) {
					aoData.push(
					 { "name": "treeParId", "value": $("#treeParId").val() },
					 { "name": "level", "value": $("#node_Level").val() },
					 { "name": "model", "value": 1 }
						
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
		
	
		
		/*板块-添加*/
		function dep_add(title,w,h){ 
			if($("#treeParId").val()=="0"){
				layer.alert("请先选择活动或父板块");
			}else{
				var url = path+"/banChunkController.do?add";
				layer_show(title,url,w,h);
			}
		}
		
		/*板块-修改*/
		function dep_edit(title,id,w,h){
		     var url = path+"/banChunkController.do?edit&banChunkId="+id;
			layer_show(title,url,w,h);
		}
		
		
		/*板块-删除*/
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
			var activityId=$("#activityId").val();
			$.ajax({
				    url: path+"/banChunkController/delBanChunk.do",
				    traditional:true,
				    dataType:'json',  
				    data:{
				     "Ids":ids,
				     "activityId":activityId
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
		
		 function manuscript_manage(id,chunkName,w,h){
		 		var activityId=$("#activityId").val();
				var url = path+"/manuscriptController.do?viewPage&banChunkId="+id+"&activityId="+activityId;
				var title =chunkName +" --  稿件管理";
				var w= "";
				var h= "";
				layer_full(title,url,w,h,true);
		 }
		
		function getAllManuscriptByActivity(){
			var activityId=$("#activityId").val();
			if(activityId==0){
				layer.alert("请先选择活动或其子版块");
			}else{
				var url = path+"/manuscriptController.do?viewPage&activityId="+activityId+"&type=1";
				var title ="稿件管理";
				var w= "";
				var h= "";
				layer_full(title,url,w,h,true);
			}
		}
		
		
		
		

			
		
		
		
		
	
		



		
		
		
		
		
		</script>
		
</body>
  
  
  
</html>
