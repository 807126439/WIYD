<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="renderer" content="webkit|ie-stand">
<title>万维博通知识库</title>

<link href="<%=path%>/plug-in/h-ui/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/plug-in/main/libs/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/plug-in/main/css/common.css">


<!--[if lt IE 9]>
	<script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/html5.js"></script>
	<script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/respond.min.js"></script>
	<script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/PIE_IE678.js"></script>
 <![endif]-->


</head>

<body>
	<div class="infobar">
		<span>当前用户：${userList[0].userName}</span>
		<span>
			当前积分：<em>${userList[0].score}</em>（${userList[0].gradeName}）
		</span>
		<span>
			已连续登陆<em>${userList[0].loginTimes==null?'0':userList[0].loginTimes}</em>天（${userList[0].loginGrade}）
		</span>
		<span title="1级">
			${userList[0].gradeIcon}
			<!-- <i class="icon_star"></i><i class="icon_moon"></i><i
			class="icon_sun"></i> -->
		</span>
		<span class="infobar-right">
			<fmt:formatDate value="${date}" pattern="yyyy年MM月dd日" />
		</span>
	</div>
	<div class="ui-box-contianer">
		<div class="ui-box-left">
			<div class="ui-panel">
				<form action="<%=path%>/documentFileController/goSearch.do" method="post">
					<div class="ui-searchbar-container">
						<div class="ui-searchbar-label" style="width: 110px">
							<select class="ui-select ui-hide" name="searchField">
								<option selected value="1">文件名</option>
								<option value="2">关键字</option>
								<option value="3">按上传用户</option>
							</select>
						</div>
						<div class="ui-searchbar" style="margin-left: 115px">
							<span class="ui-searchbar-btn-wr">
								<button class="ui-searchbar-btn">
									<i class="icon_search"></i>搜索
								</button>
							</span>
							<span class="ui-searchbar-ipt-wr">
								<input name="search" id="search" type="text" class="ui-searchbar-ipt">
							</span>
							<span class="ui-searchbar-ext">
								<span>选择文件格式：</span>
								<input name="classification" type="checkbox" value="all" id="s_ext_all" checked>
								<label for="s_ext_all">全部</label>
								<input name="classification" type="checkbox" value="doc" id="s_ext_doc">
								<label for="s_ext_doc">doc</label>
								<input name="classification" type="checkbox" value="ppt" id="s_ext_ppt">
								<label for="s_ext_ppt">ppt</label>
								<input name="classification" type="checkbox" value="xls" id="s_ext_xls">
								<label for="s_ext_xls">xls</label>
								<input name="classification" type="checkbox" value="pdf" id="s_ext_pdf">
								<label for="s_ext_pdf">pdf</label>
								<input name="classification" type="checkbox" value="pic" id="s_ext_pic">
								<label for="s_ext_pic">图片</label>
							</span>
						</div>
					</div>
				</form>
			</div>
			<div class="ui-panel">
				<div class="ui-panel-header">
					<span class="tt">
						热点推荐<i class="icon_hot"></i>
					</span>
					<a href="javascript:void(0)" class="ui-panel-more" onclick="goSearch(1)">MORE+</a>
				</div>
				<div class="ui-panel-body doublelist-container" style="height: 173px;overflow: auto;">
					<ul class="ui-itemlist">
						<c:forEach var="recommend" varStatus="vs" items="${recommendList}" end="4">
							<li class="itemlist-item">
								<div class="item-title">
									<span class="text-recommend">${recommend.userName }推荐了</span>
									<a href="javascript:void(0)" onclick="check_file('${recommend.documentId }')">${recommend.documentName==null?'空':recommend.documentName }</a>
								</div>
							</li>
						</c:forEach>
					</ul>
					<ul class="ui-itemlist">
						<c:forEach var="recommend" varStatus="vs" items="${recommendList}" begin="5">
							<li class="itemlist-item">
								<div class="item-title">
									<span class="text-recommend">${recommend.userName }推荐了</span>
									<a href="javascript:void(0)" onclick="check_file('${recommend.documentId }')">${recommend.documentName==null?'空':recommend.documentName }</a>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="ui-panel">
				<div class="ui-panel-header">
					<span class="tt">知识查看</span>
					<a href="javascript:void(0)" onclick="goSearch(1)" class="ui-panel-more">MORE+</a>
				</div>
				<div class="ui-panel-body" style="height: 434px;overflow: auto;">
					<ul class="ui-navtag navtag_type">
						<c:forEach var="category" varStatus="vs" items="${categorys}">
							<li class="ui-col-2" data-value="${category.id}">${category.categoryName}</li>
						</c:forEach>
					</ul>
					<ul class="ui-btntag btntag_sort ">
						<li class="sortType" data-value="gmt_modified">按时间</li>
						<li class="sortType" data-value="view_time">按热度</li>
					</ul>
					<div class="ui-tabpanels tabpanels_type">
						<div class="ui-tabpanels tabpanels_sort current">
							<ul class="ui-itemlist border-dashed current">
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="ui-box-right">
			<div class="ui-panel">
				<div class="user-box">
					<i class="icon-avatar-large"><img src="${userList[0].headImgUrl == null? pageContext.request.contextPath.concat('/plug-in/main/images/demo/avatar-lg.png'): userList[0].headImgUrl}" height="100%"></i>
					<div class="user-box-info">
						<span class="user-box-info-name">${userList[0].userName}</span>
						<span class="user-box-info-level">（等级：${userList[0].gradeName}）</span>
					</div>
					<div class="user-box-data">
						<a href="javascript:void(0)" class="user-box-data-item">
							<span class="item-number">${userList[0].score}</span>
							<span class="item-name">积分</span>
						</a>
						<a href="javascript:void(0)" class="user-box-data-item">
							<span class="item-number">${userList[0].rank}</span>
							<span class="item-name">排名</span>
						</a>
						<a href="javascript:void(0)" class="user-box-data-item">
							<span class="item-number">${fileNum}</span>
							<span class="item-name">已上传文件</span>
						</a>
					</div>
				</div>
				<div class="ui-panel-header header-border-top">
					<span class="tt">近期下载</span>
					<a href="javascript:void(0)" onclick="moreRecord('近期下载', '', '', '/klb/recordDownloadController/viewPage.do')" class="ui-panel-more">更多&gt;&gt;</a>
				</div>
				<div class="ui-panel-body">
					<ul class="ui-itemlist border-dashed">
						<c:forEach var="download" varStatus="vs" items="${downloadList}">
							<li class="itemlist-item">
								<div class="item-right">${download.fileSize}</div>
								<div class="item-title">
									<a href="javascript:void(0)" onclick="check_file('${download.documentId }')">${download.documentName} </a>
								</div>
							</li>
						</c:forEach>
					</ul>
					<a class="ui-btn ui-btn-secondary size-l ui-btn-block" href="javascript:void(0)" onclick="model_add()" style="margin-top:6px">
						<i class="icon_upload"></i>上传我的文档
					</a>
				</div>
				<div class="ui-panel-header header-border-top">
					<span class="tt">最热点击</span>
					<a href="javascript:void(0)" onclick="goSearch(1)" class="ui-panel-more">更多&gt;&gt;</a>
				</div>
				<div class="ui-panel-body">
					<ul class="ui-itemlist border-dashed">
						<c:forEach var="view" varStatus="vs" items="${viewList}">
							<li class="itemlist-item">
								<div class="item-right">${view.fileSize}</div>
								<div class="item-title">
									<a href="javascript:void(0)" onclick="check_file('${view.id }')">${vs.index+1}、 ${view.documentName }</a>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<!-- box-right -->
	</div>
	<!-- main -->
	<script type="text/javascript" src="<%=path%>/plug-in/main/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/plug-in/main/libs/icheck/icheck.js"></script>
	<script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="<%=path%>/plug-in/main/js/iselect.js"></script>
	<script type="text/javascript" src="<%=path%>/plug-in/main/js/public.js"></script>
	<script type="text/javascript">
		$(function() {
			iSelect({
				elem : ".ui-select.ui-hide"
			});//select皮肤
			$('input[type="checkbox"]').iCheck({
				//checkbox皮肤
				checkboxClass : 'icheckbox_minimal-blue'
			});
			// $('input[type="radio"]').iCheck({
			//   //radio皮肤
			//   checkboxClass: 'icheckbox_minimal-blue',
			//   radioClass:'iradio_minimal-blue'
			// })
			if (true) {
				tabPage(".navtag_type", "active", '', '', updateList);
				tabPage(".btntag_sort", "active", '', '', updateList);
				updateList();
			}

		});

		//知识查看框内的内容更新
		function updateList() {
			$(".ui-itemlist[class*='current']").empty();
			$
					.ajax({
						type : "POST",
						url : path + "/documentFileController/getFileList.do",
						data : {
							'categoryId' : $(".ui-col-2[class*='active']")
									.data("value"),
							'sortType' : $(".sortType[class*='active']").data(
									"value")
						},
						async : true,
						error : function(request) {
							layer.msg("查询失败");
						},
						success : function(data) {
							if (data.status == "y" || data.status == "Y") {
								var html = "";
								var filedate;
								for ( var i in data.data) {
									//filedate = new Date(Date.parse(
									//		data.data[i].gmtModified));
									html += "<li class='itemlist-item'>";
									html += "     <div class='item-time'>"
									//+ filedate.Format("yyyy-MM-dd")
									+ data.data[i].gmtModified + "</div>";
									html += "     <div class='item-user'>已被查看"
											+ data.data[i].viewTime + "次</div>";
									html += "     <div class='item-user'>"
											+ data.data[i].uploderName
											+ "</div>";
									html += "     <div class='item-title'><a href='javascript:void(0)' onclick=\"check_file('"
											+ data.data[i].id
											+ "')\">"
											+ (data.data[i].documentName == null ? '空'
													: data.data[i].documentName)
											+ "</a></div>";
									html += "</li>";
								}
								$(".ui-itemlist[class*='current']")
										.append(html);
							} else {
								layer.msg(data.info, {
									icon : 2,
									time : 2500
								});
							}
						}
					});
		}

		//查看更多
		function moreRecord(title, w, h, url) {
			layer_full(title, url, w, h);
		}

		//查看文件
		function check_file(id, w, h) {
			var url = path
					+ "/documentFileController/skipCheckDocumentFile.do?id="
					+ id;
			layer_full("查看文件", url, w, h);
		}

		//上传文件
		function model_add() {

			parent.$("#t_uploadDocumentFile").trigger("click");

		}

		function goSearch(type) {
			//按跳转类别重置提交的表单
			switch (type) {
			//热点推荐
			case 1:
				$(".ui-select").val(1);
				$("[type='checkbox']").iCheck('uncheck');
				$("#s_ext_all").iCheck('check');
				break;
			//知识查看
			case 2:
				break;
			default:
			}

			$(".ui-searchbar-btn").trigger("click");
		}

		function showRecomendList() {
			var url = path + "/recordRecommendController/showRecomendList.do";
			layer_full("推荐列表", url);
		}

		//对日期进行格式化
		function FMT(fmt) { //author: meizz
			var o = {
				"M+" : this.getMonth() + 1, //月份
				"d+" : this.getDate(), //日
				"h+" : this.getHours(), //小时
				"m+" : this.getMinutes(), //分
				"s+" : this.getSeconds(), //秒
				"q+" : Math.floor((this.getMonth() + 3) / 3), //季度
				"S" : this.getMilliseconds()
			//毫秒
			};
			if (/(y+)/.test(fmt))
				fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
						.substr(4 - RegExp.$1.length));
			for ( var k in o)
				if (new RegExp("(" + k + ")").test(fmt))
					fmt = fmt.replace(RegExp.$1,
							(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
									.substr(("" + o[k]).length)));
			return fmt;
		};
	</script>
</body>

</html>
