<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

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

<link href="<%=path%>/plug-in/h-ui/static/h-ui/css/H-ui.min.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/plug-in/main/css/common.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/plug-in/main/css/index.css">


<!--[if lt IE 9]>
	<script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/html5.js"></script>
	<script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/respond.min.js"></script>
	<script type="text/javascript" src="<%=path%>/plug-in/h-ui/lib/PIE_IE678.js"></script>
<![endif]-->

</head>




<body>
	<div class="header">
		<img class="header-title"
			src="<%=path%>/plug-in/main/images/header-title.png">
		<div class="header-buttons">
			<input type="hidden" value="" id="loginScore">
			<input type="hidden" value="" id="loginTimes">
			<a href="javascript:;" class="header-buttons-user"
				onclick="viewMyDetail()"><i class="icon-avatar-small">
				<img src="${ pageContext.request.contextPath.concat('/plug-in/main/images/demo/avatar-sm.png')}"
					height="100%"></i>用户名</a> 
		     <a href="<%=path%>/index.do" target="_top"><i class="icon-home"></i>首页</a>
			<a href="javascript:;" onclick="logout()">退出</a>
		</div>
	</div>
	

	
	
	<div class="sidebar">
		<div class="sidebar-header-wrapper">工务闪念</div>
		<div class="sidebar-menu-wrapper">
			<ul class="sidebar-menu">
				<%
					String menu = (String) session.getAttribute("menu");
					out.print(menu);
				%>
			</ul>
		</div>
	</div>
	<div class="main-container">
		<iframe src="<%=path%>/homePage.do" name="mainframe" width="100%"
			height="100%" frameborder="0"></iframe>
	</div>
	<script type="text/javascript"
		src="<%=path%>/plug-in/main/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/plug-in/h-ui/lib/layer/3.0.3/layer.js"></script>
	<script type="text/javascript"
		src="<%=path%>/plug-in/main/libs/jquery.nicescroll.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/plug-in/web/common/js/public.js"></script>
	<script type="text/javascript">
		$(function() {
			//登录成功积分变动提示
			/*
			var loginTimes = $("#loginTimes").val();
			if (loginTimes != null && loginTimes != "") {
				layer
						.alert(
								"<div class='text-c'>恭喜你连续登录 <span class='color-red'>"
										+ loginTimes
										+ "</span> 天，获得 <span class='color-red'>"
										+ $("#loginScore").val()
										+ "</span> 个积分!</div>",
								{
									shade : 0.3,
									shadeClose : true,
									title : "登录成功",
									skin : "layer-skin-kb layer-skin-kb-dialog layer-skin-kb-bodered"
								});
			}
			*/

			$(".sidebar-menu,.sidebar-menu-innermenu").on("click", "li>a",
					function() {
						preMenu(this);
					});

			//初始化
			niceScrollFactory('.sidebar-menu-wrapper'); //滚动条

			$('.sidebar-menu-item>a:last-child').closest('.sidebar-menu-item')
					.addClass('sidebar-menu-item-end'); //菜单栏的叶子节点添加类名
			$('.sidebar-menu-item>a').on('click', openSubmenu); //菜单栏折叠事件绑定

			//菜单展开
			function openSubmenu(event) { //开启子菜单(sidebar-menu-innermenu的子菜单不可展开)
				if ($(this).nextAll('ul').eq(0).hasClass(
						'sidebar-menu-innermenu')) {
					//要展开的菜单是独立式菜单时
					if ($(this).closest('.sidebar-menu-item')
							.hasClass('active')) {
						//菜单早已开启时
						if (!$(this).closest('.sidebar-menu-item').parent()
								.hasClass('opened')) {
							//从外菜单重新返回子菜单(不触发链接)
							$(this).closest('.sidebar-menu-item').parent()
									.addClass('opened');
							if (event.preventDefault)
								event.preventDefault();
							event.returnValue = false;
						} else {
							//从子菜单重新返回外菜单(触发链接)
							$(this).closest('.sidebar-menu-item').parent()
									.removeClass('opened');
						}
					} else {
						//子菜单还未开启时
						$('.sidebar-menu-item').removeClass('active');
						$(this).parents('.sidebar-menu-item')
								.addClass('active');
						$(this).closest('.sidebar-menu-item').parent()
								.addClass('opened');
						// $(this).nextAll('ul').eq(0).children('li:eq(0)').children('a').trigger('click');
					}
				} else {
					//要展开的菜单是普通菜单时
					if ($(this).nextAll('ul').eq(0).length === 0) {
						//终节点的场合
						var $activeItems = $(this)
								.parents('.sidebar-menu-item');
						$activeItems.addClass('active');
						$('.sidebar-menu-item>a:last-child').parent().not(
								$activeItems).removeClass('active');
						$('.sidebar-menu-item>.sidebar-menu-innermenu')
								.parent().not($activeItems).removeClass(
										'active');
						$('.sidebar-menu-item:hidden').not($activeItems)
								.removeClass('active');
					} else {
						//菜单项
						$(this).closest('.sidebar-menu-item').toggleClass(
								'active');
					}
				}
				$(".sidebar .sidebar-menu").getNiceScroll().resize();
			}
		});

		//更新子菜单位置。elem为要展开的menu的元素或选择器
		function updateMenu(elem) {
			$('.sidebar-menu,.sidebar-menu ul').removeClass('opened').find(
					'.sidebar-menu-item').removeClass('active');
			if (typeof elem === "undefined" || !elem) {
				return;
			}
			$(elem).closest('.sidebar-menu-item').addClass('active');
			if ($(elem).parents('.sidebar-menu-item').length == 0) {
				return;
			}
			$(elem).parents('.sidebar-menu-item').addClass('active');
			$(elem).parent().find('li:first')
					.parents('.sidebar-menu-innermenu').parent().closest(
							'.sidebar-menu,.sidebar-menu ul')
					.addClass('opened');
			$(".sidebar .sidebar-menu").getNiceScroll().resize();
		}

		//展开或收起侧边栏
		function toggleSidebar() {
			$('.page_fold').toggleClass('active');
			$('.sidebar').toggleClass('closed');
			$('.header,.main-container').toggleClass('opened');
		}

		//滚动条生成，elem为要容器的元素或选择器
		function niceScrollFactory(elem, option) {
			$(elem).each(function() {
				$(this).niceScroll($.extend({}, {
					cursorcolor : "#212127", //光标颜色 
					cursoropacitymax : 1, //改变处于活动状态光标的不透明度（scrollabar“可见”状态），范围从1到0 
					touchbehavior : false, //使光标拖动滚动像在台式电脑触摸设备 
					cursorwidth : "10px", //像素光标的宽度 
					cursorborder : "0", //     游标边框css定义 
					cursorborderradius : "0", //以像素为光标边界半径 
					autohidemode : false
				//是否隐藏滚动条 
				}, option));
			});
		}

		function preMenu(obj) {
			if ($(obj).attr('_href')) {
				var le = $(obj).attr('le');
				var _href = $(obj).attr('_href');
				var _titleName = $(obj).attr("title-val");

				if (_href && le == 1) {
					if (_href.indexOf("?") >= 0) {
						_href += "&title=" + encodeURI(encodeURI(_titleName));
					} else {
						_href += "?title=" + encodeURI(encodeURI(_titleName));
					}
				} else {
					var topTitle = $(obj).parents(".sidebar-menu-item").find(
							"a").attr("title-val");
					if (_href.indexOf("?") >= 0) {
						_href += "&title=" + encodeURI(encodeURI(_titleName))
								+ "&top=" + encodeURI(encodeURI(topTitle));
					} else {
						_href += "?title=" + encodeURI(encodeURI(_titleName))
								+ "&top=" + encodeURI(encodeURI(topTitle));
					}

				}

				window.top.frames['mainframe'].location.href = _href;
			}
		}

		function viewMyDetail() {
			var url = path + "/userController/loadMyDetail.do";
			layer_show("个人信息", url, "420", "520", false);
		}
	</script>
</body>

</html>