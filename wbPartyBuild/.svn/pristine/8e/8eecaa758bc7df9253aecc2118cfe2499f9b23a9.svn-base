var dataLoaded = false;

// ==========选择资料==========
function chooseData() {
	// 初始化页面
	if (!dataLoaded) {
		loadData();
	} else {
		// 设置缩略图选项列表
		// refreshData();
	}
	// 设置弹层显示
	layer.open({
		type : 1,
		title : "选择学习资料",
		maxmin : true, // 开启最大化最小化按钮
		area : [ '950px', '600px' ],
		shadeClose : true,
		// shade : 0.2,
		content : $('#hide-StudyData-area'),
		scrollbar : false
	});
}

// ==========初始化资料缩略图选择列表==========
function loadData(curr) {
	curr = curr || 1;
	var pageSize = 10;
	$.post(path + "/studyDataController/pageList.do", {
		"iDisplayStart" : ((curr - 1) * pageSize),
		"pageSize" : pageSize
	},
			function(data, status) {
				console.log('loadding');
				res = jQuery.parseJSON(data);
				var sumPage = Math.floor(res.iTotalDisplayRecords
						/ res.iTotalRecords) + 1;
				resolveData(res.aaData);

				// 显示分页
				laypage({
					cont : 'item-page', // 容器
					pages : sumPage, // 设置总页数
					skin : 'molv',
					curr : curr || 1, // 当前页
					first : false, // 隐藏首页
					jump : function(obj, first) { // 触发分页后的回调
						if (!first) { // 点击跳页触发函数自身，并传递当前页：obj.curr
							loadData(obj.curr);
						}
					}
				});

				// 鼠标悬浮在图片上的响应
				$(".item-area li").hover(function() {
					$(this).addClass("hover");
				}, function() {
					$(this).removeClass("hover");
				});

				// 绑定图片选中事件
				$(".item-area .item").click(function() {
					checks($(this).closest('.item'));
				});
			});
	dataLoaded = true;
}

// ==========数据解析==========
function resolveData(data) {
	$("#show-list").html("");
	if (data != null) {
		$(data)
				.each(
						function(index) {
							var item = data[index];
							var html = "<li class=\"item\" title=\""
									+ item.dataName
									+ "\">"
									+ "<div class=\"itembox\">"
									+ "<input type=\"checkbox\" class=\"checkbox\" name=\"picture\" "
									+ "data-name=\""
									+ item.dataName
									+ "\" value=\""
									+ item.sdId
									+ "\">"
									+ "<span class=\"itembox-checkbox\"><i class=\"Hui-iconfont\">&#xe6a7;</i></span>"
									+ "<div class=\"picbox\">"
									+ "<img src=\""
									+ item.pattern
									// + path
									// +
									// "/plug-in/web/scripts/study/studyTask/images/bg_form.png"
									+ "\">"
									+ "</div>"
									+ "<div class=\"textbox\" data-val=\""
									+ item.dataName
									+ "\" data-id=\""
									+ item.sdId
									+ "\" >"
									+ item.dataName
									+ "</div>"
									+ "<div class=\"operbox\"></div></div></li>";
							$("#show-list").append(html);
						});
	}
}

// ==========刷新缩略图选择列表==========
// function refreshData() {
// // 如果不存在已选学习资料
// if ($(".divclass").length == 0) {
// $('.item-area .item').each(function() {
// var checkbox = $(this).find('.checkbox');
// var cbox = $(this).find('.itembox-checkbox');
// checkbox.prop("checked", false);
// $(this).removeClass('checked');
// cbox.removeClass('checked');
// });
// } else {
// $('.item-area .item').each(function() {
// $a = $(this);
// var checkbox = $(this).find('.checkbox');
// var cbox = $(this).find('.itembox-checkbox');
// $(".taskStuids").each(function() {
// var id = $(this).val();
// // 如果addStudyTask.jsp中存在当前学习资料，则设置为选中
// if (id == checkbox.val()) {
// checkbox.prop("checked", true);
// $a.addClass('checked');
// cbox.addClass('checked');
// return false;
// } else {
// // 否则设置为未选中
// checkbox.prop("checked", false);
// $a.removeClass('checked');
// cbox.removeClass('checked');
// }
// });
// });
// }
// }

// 选中事件
function checks($ele) {
	$ele.each(function() {
		var checkbox = $(this).find('.checkbox');
		var cbox = $(this).find('.itembox-checkbox');
		if (checkbox.is(':checked')) {
			checkbox.prop("checked", false);
			$(this).removeClass('checked');
			cbox.removeClass('checked');
		} else {
			checkbox.prop("checked", true);
			$(this).addClass('checked');
			cbox.addClass('checked');
		}
	});
}

// 全选
function selectAll() {
	checks($('.item-area .item').not('.checked'));
}

// 全不选
function cancelAll() {
	checks($('.item-area .item.checked'));
}

// ==========确认选中资料==========
function subStudyDataChoice() {
	// 清空div
	// $("#xuexi").empty();
	// 把选中的item加入
	$("#show-list li .itembox .checkbox:checked").each(function(index) {
		$a = $(this);
		// 如果存在已选学习资料
		if ($(".divclass").length != 0) {
			// flag标志当前学习资料是否需要添加到addStudyTask.jsp，默认为需要添加true
			var flag = true;
			$(".taskStuids").each(function(index) {
				var id = $(this).val();
				if (id == $a.val()) {
					// 检测到当前学习资料已添加到addStudyTask.jsp，则flag设置为false
					flag = false;
				}
			});
			if (flag) {
				stuAppend();
			}
		} else {
			// 如果不存在已选学习资料
			stuAppend();
		}
	});
	// 关闭当前页
	layer.closeAll('page');
}

// 添加选中学习资料到addStudyTask.jsp
// TODO 第二个input的name和id怎样映射到controller
function stuAppend() {
	$("#xuexi").append(
			"<div class='divclass'>"
					+ "<input readonly='readonly' type='text' value="
					+ $a.attr('data-name') + " />"
					// ==========去掉span的style='float: right;'==========
					// + "<span onclick='checkSpan(this)' style='float:
					// right;'>"
					+ "<span onclick='checkSpan(this)'>"
					+ "<i class='Hui-iconfont Hui-iconfont-close2'></i>"
					+ "</span>"
					+ "<input class='taskStuids' type=\"hidden\" value="
					+ $a.val() + " name='taskStu' id=sdid_" + $a.val() + " />"
					+ " </div>");
}
