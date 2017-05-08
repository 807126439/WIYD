<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/page/common/tag/mytags.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<base href="<%=basePath%>">

<title>杂志内页图片列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<%@include file="/page/common/script/mytop.jsp"%>
<link href="<%=path%>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/plug-in/web/portals/css/content/showFile.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/plug-in/h-ui/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet">



</head>


<body>

	<!-- 板块树形图 -->
		<input type="hidden" id="treeParId" value="0">
		<input type="hidden" id="parName" value="">
		<input type="hidden" id="node_Level" value="0">
		<input type="hidden" id="activityId" value="${activityId}">
		<input type="hidden" id="linkContentId" value="0">
		<input type="hidden" id="SelectedContentId" value="0">
		<input type="hidden" id="path" value="0">
	
	<div class="pos-a"
		style="width:250px;left:0;top:0; bottom:0; height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5">
		<div class="panel panel-default"
			style="border-bottom:none; border-left: none;border-right: none;overflow: hidden;">
			<div class="panel-header">板块导航</div>
			<div class="panel-body" style="padding: 5px;">
				<ul id="tree" class="ztree"></ul>
			</div>
		</div>
	</div>
	<!-- 板块树形图 -->
	
	<div class="cl pd-5 bg-1 bk-gray" style="margin-left: 250px;">
		<div style="height: 48px;line-height: 48px;overflow: hidden;">
			<div class="f-l pl-20">
	
				<span class="l">
					<m:a_top context="上传照片" operCode="skipAddContent"  funMethod="img_upload()" className="btn btn-secondary radius" /> 
					<m:a_top context="删除图片" operCode="delContent"  funMethod="datadel()" className="btn btn-danger radius" />
					<m:a_top context="新增子元素" operCode="saveContent"  funMethod="contentsave()" className="btn btn-secondary radius" />
					<m:a_top context="删除子元素" operCode="cutOffContent"  funMethod="contentCut()" className="btn btn-danger radius" />
				</span>
			</div>
			
				
				<span class="select-box r mr-10 mt-10" style="width: 120px">
						<select id="change-table" class="select">
							<option value="0" selected="selected">所有图片</option>
							<option value="1">每板块下图片</option>
						
						</select>
				</span>

		</div>
	</div>
	
	<div class="Hui-article" style="top: 48px;z-index: 1;margin-left: 250px">
		<div class="pd-20">
			<div class="item-content">
				<ul class="cl item-area" id="show-list">

				</ul>
			</div>
			<div id="item-page" class="pt-20 pr-10 r"></div>
		</div>
		<input type="hidden" value="${parId}" id="parId">
	</div>


	<div id="edit-pic-box" style="display: none;"
		class="module-edit-name module-edit-name-grid">
		<div class="new-dir-item">
			<input id="edit-pic-input" class="box" value="" type="text">
			<input id="edit-pic-id" type="hidden" value=""> <span
				class="sure" onclick="subEditName()"></span> <span class="cancel"
				onclick="cancelEditName()"></span>

		</div>
	</div>


	<div style="display: none;width: 660px;margin: 0 auto;" id="upload-img-area">
		
				
				<div class="uploader-list-container">
					<div class="queueList">
						<div id="dndArea" class="placeholder" style="min-height: 160px;">
							<div id="filePicker-2"></div>
							<p>或将照片拖到这里，单次最多可选300张</p>
						</div>
					</div>
					<div class="statusBar" style="display:none;">
						<div class="progress">
							<span class="text">0%</span> <span class="percentage"></span>
						</div>
						<div class="info"></div>
						<div class="btns">
							<div id="filePicker2"></div>
							<div class="uploadBtn">开始上传</div>
						</div>
					</div>
				</div>

	</div>



	<m:a_button operCode="delContent" context="下载"
		funMethod="downloadPic('{0}')" varName="down_photo" />


	<%@include file="/page/common/script/operbuttom.jsp"%>
	<script type="text/javascript"
		src="<%=path%>/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript"
		src="<%=path%>/plug-in/h-ui/lib/webuploader/0.1.5/webuploader.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/plug-in/web/scripts/portals/content/multi-img-upload.js"></script>
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>

	<script type="text/javascript">
		$(function() {
			//初始化树形图
			$.fn.zTree.init($("#tree"), setting);
			loadData();
			
		});
		/*树形图JS*/
				var activityId=$("#activityId").val();
			 	var mtable;
				var setting = {
						async: {
							enable: true,
							url:path+"/banChunkController/getBanChunkTree.do",
							autoParam:["id=treeParId", "level=level"],
							otherParam:{"activityId":activityId},
						},
						view: {
							dblClickExpand: false,
							showLine: true,
							selectedMulti: false
						},
						data: {
							keep: {
								parent:true
							},
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
		   		var linkContentId=treeNode.linkContentId;
			 	$("#node_Level").val(le+1);
			    $("#treeParId").val(treeNode.id);
				$("#parName").val(treeNode.name);
				$("#activityId").val(treeNode.activityId)
				$("#linkContentId").val(treeNode.linkContentId)
				
				navTemp= "";
				cyclyParentNode(treeNode);
				$("#nav-line").html(navTemp);
				if(linkContentId==null){
					$("input").prop("checked", false);
				}else{
					$("input[value="+linkContentId+"]").prop("checked", true);
				}
				
				hidePicture();
				//goSearch();
				
				
			
		}
	
		/*添加树节点*/
		function addNodeItem(id,name){
			var le = $("#node_Level").val();
			var par = $("#treeParId").val();
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var parNode = null;
			var isParent = false;
			var activityId = $("#activityId").val();
			var linkContentId = $("input:radio:checked").val();
			if(par){
				parNode = treeObj.getNodeByParam("id", par, null);
			}else{
				par = null;
				isParent = true;
			}
							
			var newNode = {"id":id,"isParent":isParent,"level":le,"name":name,"pid":par,"activityId":activityId,"linkContentId":linkContentId};
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

    	
    	/*更新所选节点父节点*/
    	function refreshParentNode(contentId) {  
        	var zTree = $.fn.zTree.getZTreeObj("tree"),  
        	type = "refresh",  
        	silent = false,  
        	nodes = zTree.getSelectedNodes();
        	nodes[0].linkContentId=contentId;
        	
        	/*根据 zTree 的唯一标识 tId 快速获取节点 JSON 数据对象  
        	var parentNode = zTree.getNodeByTId(nodes[0].parentTId);  
        	选中指定节点  
        	zTree.selectNode(parentNode);  
        	zTree.reAsyncChildNodes(parentNode, type, silent);
        	
        	$("#node_Level").val(parentNode.level);
			$("#treeParId").val(parentNode.id);
			$("#parName").val(parentNode.name);
			$("#activityId").val(parentNode.activityId)
			$("#linkContentId").val(parentNode.linkContentId)
        	 */
    }

		/*向上遍历获取父节点们的名称*/
		function cyclyParentNode(treeNode){
			 navTemp = navHtml.format(treeNode.name) + navTemp;
			 
			if(treeNode.getParentNode()!=" undefined" && treeNode.getParentNode()!=null){			
				cyclyParentNode(treeNode.getParentNode(),navTemp);
			}
			
		
		}
		/*树形图JS结束*/
		
		/*提交编辑照片名称请求*/
		function subEditName() {
			var name = $("#edit-pic-input").val();
			var key = $("#edit-pic-id").val();

			if (name && name.length < 1) {
				layer.msg("名称不能为空", {
					icon : 0,
					time : 1000
				});
				return;
			}

			if (isNaN(name)) {
				layer.msg("请填写数字", {
					icon : 0,
					time : 1000
				});
				return;
			}

			if (key) {
				$.ajax({
					url : path + "/contentController/editContent.do",
					traditional : true,
					dataType : 'json',
					data : {
						"ctId" : key,
						"sortNum" : name,
						"type" : "num"
					},
					type : "post",
					cache : false,
					async : false,
					success : function(data) {
						if (data.status == "y" || data.status == "Y") {
							layer.msg(data.info, {
								icon : 1,
								time : 1000
							});
							$("#show-list li .itembox div.curedit").text(name);
							$("#show-list li .itembox div.curedit").attr(
									"data-val", name);
							$("#show-list li .itembox div.curedit")
									.removeClass("curedit");//删除编辑状态
							$("#edit-pic-input").val("");
							$("#edit-pic-id").val("");
							$("#edit-pic-box").attr("style", "display: none;");//隐藏修改input框

							$(".Hui-article").css({
								"overflow" : "auto"
							});//启动滚动

						} else {
							layer.msg(data.info, {
								icon : 2,
								time : 2500
							});
						}

					},
					error : function(error) {
						alert(error);
					}
				});

			}

		}

		/*取消编辑照片名*/
		function cancelEditName() {
			$("#edit-pic-input").val("");
			$("#edit-pic-id").val("");
			$("#edit-pic-box").attr("style", "display: none;");//隐藏修改input框

			$(".Hui-article").css({
				"overflow" : "auto"
			});//启动滚动
		}

		function loadData(curr) {
			curr = curr || 1;
			dataIds = new Array();
			$.post(path + "/contentController/pageList.do", {
				"iDisplayStart" : ((curr - 1) * 20),
				"pageSize" : 20,
				"parId" : $("#parId").val()

			}, function(res) {
				res = jQuery.parseJSON(res);
				resolveData(res.aaData);

				//显示分页
				laypage({
					cont : 'item-page', //容器
					pages : res.totalPage, //通过后台拿到的总页数
					skin : 'molv',
					curr : curr || 1, //当前页			    
					first : false, //隐藏首页
					jump : function(obj, first) { //触发分页后的回调
						if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
							loadData(obj.curr);
						}
					}
				});

				$(".item-area li").hover(function() {
					$(this).addClass("hover");
				}, function() {
					$(this).removeClass("hover");
				});

				$("#show-list li .itembox .textbox").click(
						function() {
							$(this).addClass("curedit");//设置正在编辑状态
							var pic_name = $(this).attr("data-val");
							var pic_id = $(this).attr("data-id");
							var p_li = $(this).parents("li");
							p_li.removeClass("hover");//隐藏操作栏
							var offset = p_li.offset();
							var X = offset.top;
							var Y = offset.left;

							$("#edit-pic-input").val(pic_name).focus();
							$("#edit-pic-id").val(pic_id);
							$("#edit-pic-box").attr(
									"style",
									"top: " + X + "px; left: " + (Y - 8)
											+ "px;display: block;");//显示修改input框

							$(".Hui-article").css({
								"overflow" : "hidden"
							});//禁止滚动
						});

				//绑定回车事件	
				$("#edit-pic-input").bind('keypress', function(event) {
					if (event.keyCode == "13") //按回车触发提交 
					{
						subEditName();
					}
				});

			});
		}

		function resolveData(data) {
			$("#show-list").html("");

			if (data != null) {

				$(data)
						.each(
								function(index) {
									var item = data[index];

									var html = "<li class=\"item\" title=\"{0}\">"
											.format(item.sortNum);
									html += "<div class=\"itembox\">"
											+ "<input class=\"radiobox\"  type=\"radio\" name=\"picture\" value=\""+item.ctId+"\">"
											+ "<div class=\"picbox\">";

									html += "<a href=\"javascript:;\" item-title=\"{0}\" ><img src=\"{1}\"></a>"
											.format(item.sortNum, item.pattern,
													item.ctId);

									html += "</div>";
									html += "<div class=\"textbox\" data-val=\"{0}\" data-id=\"{1}\" >{0}</div>"
											.format(item.sortNum, item.ctId);
									html += "<div class=\"operbox\">";//操作栏

									html += "</div>";
									html + "</div>";
									html += "</li>";

									$("#show-list").append(html);

								});

			}

		}

		function goSearch() {

			loadData();

		}

		function datadel() {
			var l = new Array();

			$("input:radio:checked").each(function() {
				l.push($(this).val());
			});

			if (l.length > 0) {
				layer.confirm("确认要删除" + l.length + " 张照片吗？", function(index) {

					sumbitDel(l);
					loadData();
				});
			} else {
				layer.msg('无选中的项!', {
					icon : 0,
					time : 1000
				});
			}

		}

		function sumbitDel(ids) {
			$.ajax({
				url : path + "/contentController/delContent.do",
				traditional : true,
				dataType : 'json',
				data : {
					"ids" : ids
				},
				type : "post",
				cache : false,
				async : false,
				success : function(data) {
					if (data.status == "y" || data.status == "Y") {
						layer.msg(data.info, {
							icon : 1,
							time : 1000
						});
					} else {
						layer.msg(data.info, {
							icon : 2,
							time : 2500
						});
					}

				},
				error : function(error) {
					alert(error);
				}
			});

		}

		var isInitUpload = false;

		function img_upload() {
			if (!isInitUpload) {
				initUploadComnpent();
				isInitUpload = true;
			}

			layer.open({
				type : 1,
				title : "上传图片",
				area : [ '680px', '500px' ],
				shade : 0.2,
				content : $('#upload-img-area'),
				end : function(index) {
					loadData();
					return true;
				}
			});

		}
		
		//切换显示所有图片
		$("#change-table").on("change",function(e){
				//显示所有图片
			  if($(this).val() == 0){
			  	//把所有的没选中图片显示
			   	 $("#depart-area").show();
			   	 
			   	 //显示当前板块选用图片
			  }else if($(this).val() == 1){
			  	//把没有选中的图片都进行隐藏
			  	
			  }
		
		});
		
		//保存板块与内容关系
		function contentsave(){
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var sNodes = treeObj.getSelectedNodes();
			var treeNode = sNodes[0];
			
			var node_Level=$("#node_Level").val();
			if(node_Level<=0){
				layer.alert("请先选择板块");
			}else if(!(treeNode.isParent)){
				layer.alert("请先选择板块");
			}else if($("input:radio:checked").size()<1){
				layer.alert("请先选择图片");
			}else{
			
			$("#SelectedContentId").val($("input:radio:checked").val());
			$("#path").val($("input:radio:checked").parent().find("img")[0].src);
			var url = path+"/banChunkController.do?addLeaf";
			layer_show("新增子元素",url,"","",true);
			
			}

		}
		//删除子元素节点
		function contentCut(){
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var sNodes = treeObj.getSelectedNodes();
			var treeNode = sNodes[0];
				if(treeNode.level<=0){
					layer.alert("请先选择板块");
				}else if(treeNode.isParent){
				layer.alert("请选择子节点");
				}else{
			var treeParId=$("#treeParId").val();
			var activityId=$("#activityId").val();
			$.ajax({
				url : path + "/banChunkController/delBanChunk.do",
				traditional : true,
				dataType : 'json',
				data : {
					"Ids" : treeParId,
					"activityId":activityId
				},
				type : "post",
				cache : false,
				async : false,
				success : function(data) {
					if (data.status == "y" || data.status == "Y") {
						layer.msg(data.info, {
							icon : 1,
							time : 1000
						});
					} else {
						layer.msg(data.info, {
							icon : 2,
							time : 2500
						});
					}
					treeObj.removeNode(treeNode);
					$("input").prop("checked", false);
				},
				error : function(error) {
					alert(error);
				}
			});
			}
		}
		//切换所有图片显示与相关图片显示
		$("#change-table").on("change",function(e){
			hidePicture();
		});
		function hidePicture(){
			if($("#change-table").val() == 0){
			   	 $("#show-list li").show(); 
			  }else if($("#change-table").val() == 1){
			  	$("#show-list li").hide();
			  	$("input:radio:checked").parents("li").show();
			  }
		}
	</script>



</body>



</html>
