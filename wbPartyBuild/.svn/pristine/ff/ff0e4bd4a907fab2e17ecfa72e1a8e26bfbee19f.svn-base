//var isLoadJob = false;
var dataLoaded = false;

/**
 * ==========选择资料==========
 */
function chooseData() {
	// alert("=======chooseData========");
	// 初始化页面
	if (!dataLoaded) {
		loadData();
	} else {
		// 设置缩略图选项列表
		refreshData();
	}
	// 设置弹层显示
	w = $(window).width();
	h = $(window).height();
	layer.open({
		type : 1,
		title : "",
		area : [ w + 'px', h + 'px' ],
		shade : 0.2,
		content : $('#hide-StudyData-area')
	});
}

// ==========初始化资料缩略图选择列表==========
function loadData(curr) {
	// alert("initExamPaperList");
	curr = curr || 1;
	// dataIds = new Array();
	$.post(path + "/studyDataController/pageList.do", {
	// TODO 设置页数cur
	// "iDisplayStart" : ((curr - 1) * 20),
	// "pageSize" : 20,
	// "parId" : $("#parId").val()
	},
			function(data, status) {
				console.log('loadding');
				res = jQuery.parseJSON(data);
				var sumPage = Math.floor(res.iTotalDisplayRecords
						/ res.iTotalRecords) + 1;
				// TODO
				// alert(sumPage + "===" + res.aaData[1].sdId + "==="
				// + res.aaData[1].dataName + "==="
				// + res.aaData[1].pattern + "===" + res.aaData[1].dataNum
				// + "===\n" + data);
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

				// 触发重命名动作
				// $("#show-list li .itembox .itembox-rename").click(
				// function() {
				// $(this).closest('.itembox').find('.textbox')
				// .trigger('click');
				// });

				// 重命名动作响应
				// $("#show-list li .itembox .textbox").click(
				// function() {
				// $(this).addClass("curedit");// 设置正在编辑状态
				// var pic_name = $(this).attr("data-val");
				// var pic_id = $(this).attr("data-id");
				// var p_li = $(this).parents("li");
				// p_li.removeClass("hover").addClass('editing');// 移除悬浮效果，隐藏操作栏
				// var offset = p_li.offset();
				// var X = offset.top;
				// var Y = offset.left;
				// $("#edit-pic-input").val(pic_name).focus();
				// $("#edit-pic-id").val(pic_id);
				// $("#edit-pic-box").attr(
				// "style",
				// "top: " + X + "px; left: " + (Y - 8)
				// + "px;display: block;");// 显示修改input框
				// $(".Hui-article").css({
				// "overflow" : "hidden"
				// });// 禁止滚动
				// });

				// 重命名操作绑定回车提交事件
				// $("#edit-pic-input").bind('keypress', function(event) {
				// if (event.keyCode == "13") // 按回车触发提交
				// {
				// subEditName();
				// }
				// });

				// 绑定复选框选中事件
				// $('.itembox-checkbox').click(function() {
				// alert("itembox");
				// checks($(this).closest('.item'));
				// });

				// 点击缩略图查看大图
				// $('.item-area .picbox a').click(function(event) {
				// alert("点击缩略图看大图");
				// if (event.preventDefault) {
				// event.preventDefault();
				// } else {
				// event.returnValue = false;
				// }
				// showPhoto($(this).closest('li').index());
				// });
			});
	dataLoaded = true;
}

// 数据解析
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
									//
									// + "<div class=\"itembox-top\"><span
									// class=\"f-r\">"
									// + "<a href=\"javascript:showPhoto("
									// + index
									// + ");\" class=\"itembox-del\"
									// title=\"大图\"><i
									// class=\"Hui-iconfont\">&#xe685;</i></a>"
									// + "<a href=\"javascript:"
									// + ";\" class=\"itembox-rename\"
									// title=\"重命名\"><i
									// class=\"Hui-iconfont\">&#xe647;</i></a>"
									// + "<a href=\""
									// + item.pattern
									// + "\" title=\"下载\"><i
									// class=\"Hui-iconfont\">&#xe640;</i></a>"
									// + "<a href=\"javascript:sumbitDel('"
									// + item.sdId
									// + "');\" class=\"itembox-del\"
									// title=\"删除\"><i
									// class=\"Hui-iconfont\">&#xe609;</i></a>"
									// + "</span></div>"
									//
									+ "<div class=\"picbox\">"
									// + "<a href=\""
									// + item.pattern
									// + "\" item-title=\""
									// + item.dataName
									// + "\" >"
									+ "<img src=\""
									+ item.pattern
									+ "\">"
									// + "</a>"
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
function refreshData() {
	// 如果不存在已选学习资料
	if ($(".divclass").length == 0) {
		// alert("no selected");
		$('.item-area .item').each(function() {
			// alert($(this).find('.checkbox').val());
			var checkbox = $(this).find('.checkbox');
			var cbox = $(this).find('.itembox-checkbox');
			checkbox.prop("checked", false);
			$(this).removeClass('checked');
			cbox.removeClass('checked');
		});
	} else {
		// alert($(".taskStuids").length + "selected");
		$('.item-area .item').each(function() {
			$a = $(this);
			var checkbox = $(this).find('.checkbox');
			var cbox = $(this).find('.itembox-checkbox');
			$(".taskStuids").each(function() {
				var id = $(this).val();
				// alert(checkbox.val() + "===" + id);
				// 如果addStudyTask.jsp中存在当前学习资料，则设置为选中
				if (id == checkbox.val()) {
					// alert(checkbox.val() + "==" + id + "true");
					checkbox.prop("checked", true);
					$a.addClass('checked');
					cbox.addClass('checked');
					return false;
				} else {
					// alert(checkbox.val() + "!=" + id + "false");
					// 否则设置为未选中
					checkbox.prop("checked", false);
					$a.removeClass('checked');
					cbox.removeClass('checked');
				}
			});
		});
	}
}

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
	$("#xuexi").empty();
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
// TODO 第二个input的name和id映射到controller
function stuAppend() {
	$("#xuexi")
			.append(
					"<div class='divclass'  style=\"width:180px;margin: 10px;float: left;border: 1px solid #AFD1E3;\">"
							+ "<input  style='height: 25px;width: 156px;' readonly='readonly' type='text' value="
							+ $a.attr('data-name')
							+ " />"
							+ "<input class='taskStuids' type=\"hidden\"  value="
							+ $a.val()
							+ " name='taskStu' id=sdid_"
							+ $a.val()
							+ ">"
							+ "<span onclick='checkSpan(this)' style='float:right;'><img src='/wbPartyBuild/plug-in/web/portals/image/cancel.png' width='24px' height='25px'/></span>"
							+ " </div>");
}

// 大图展示 TODO 没有反应？
// function showPhoto(index) {
// var data = [];
// $('.item-area .picbox a').each(
// function() {
// data.push({
// "alt" : ($(this).attr('item-title') === "") ? $(this).attr(
// 'title') : $(this).attr('item-title'),
// "src" : $(this).attr('href')
// });
// });
// var json = {
// "title" : "",
// "start" : index,
// "data" : data
// };
// // TODO layer.photos阻塞
// // alert(index + "==" + data.length);
// layer.photos({
// photos : json,
// skin : "layer-center",
// shift : 5,
// tab : function(pic, layero) {
// console.log('new photo tab');
// var $img = $(layero).find('img');
// $(layero).css({
// 'height' : $img.height() + 'px',
// 'width' : $img.width() + 'px'
// }).css({
// 'top' : ($(window).height() - $(layero).height()) / 2 + "px",
// 'left' : ($(window).width() - $(layero).width()) / 2 + "px"
// });
// }
// });
// // TODO
// // alert(index + "==");
// }

// 提交编辑照片名称请求 TODO
// function subEditName() {
// var name = $("#edit-pic-input").val();
// var key = $("#edit-pic-id").val();
// if (name && name.length < 1) {
// layer.msg("名称不能为空", {
// icon : 0,
// time : 1000
// });
// return;
// }
// if (isNaN(name)) {
// layer.msg("请填写数字", {
// icon : 0,
// time : 1000
// });
// return;
// }
// if (key) {
// $.ajax({
// url : path + "/contentController/editContent.do",
// traditional : true,
// dataType : 'json',
// data : {
// "ctId" : key,
// "sortNum" : name,
// "type" : "num"
// },
// type : "post",
// cache : false,
// async : false,
// success : function(data) {
// if (data.status == "y" || data.status == "Y") {
// layer.msg(data.info, {
// icon : 1,
// time : 1000
// });
// $("#show-list li .itembox div.curedit").text(name);
// $("#show-list li .itembox div.curedit").attr("data-val",
// name);
// $("#show-list li .itembox div.curedit").removeClass(
// "curedit");// 删除编辑状态
// $("#edit-pic-input").val("");
// $("#edit-pic-id").val("");
// $("#edit-pic-box").attr("style", "display: none;");// 隐藏修改input框
// $('.item-area .item.editing').removeClass('editing');
// $(".Hui-article").css({
// "overflow" : "auto"
// });// 启动滚动
// } else {
// layer.msg(data.info, {
// icon : 2,
// time : 2500
// });
// }
// },
// error : function(error) {
// alert(error);
// }
// });
// }
// }

// 取消编辑照片名 TODO
// function cancelEditName() {
// $("#edit-pic-input").val("");
// $("#edit-pic-id").val("");
// $("#edit-pic-box").attr("style", "display: none;");// 隐藏修改input框
// $(".Hui-article").css({
// "overflow" : "auto"
// });// 启动滚动
// $('.item-area .item.editing').removeClass('editing');
// }

// 批量删除警告 TODO 没有单项删除？
// function datadel() {
// var l = new Array();
// $(".item-area input.checkbox:checked").each(function() {
// l.push($(this).val());
// });
// if (l.length > 0) {
// layer.confirm("确认要删除" + l.length + " 张照片吗？", function(index) {
//
// sumbitDel(l);
// layer.close(index);
// }, false);
// } else {
// layer.msg('无选中的项!', {
// icon : 0,
// time : 1000
// });
// }
// }

// 提交删除操作 TODO 修改提交controller？
// function sumbitDel(ids) {
// $.ajax({
// url : path + "/contentController/delContent.do",
// traditional : true,
// dataType : 'json',
// data : {
// "ids" : ids
// },
// type : "post",
// cache : false,
// async : false,
// success : function(data) {
// loadData();
// if (data.status == "y" || data.status == "Y") {
// layer.msg(data.info, {
// icon : 1,
// time : 1000
// });
// } else {
// layer.msg(data.info, {
// icon : 2,
// time : 2500
// });
// }
// },
// error : function(xhr, error) {
// alert(error);
// }
// });
// }

// function choiceData() {
// if (!isLoadJob) {
// initStudyDataList();
// }
// w = ($(window).width() - 600);
// h = ($(window).height() - 100);
// layer.open({
// type : 1,
// title : "",
// area : [ w + 'px', h + 'px' ],
// shade : 0.2,
// content : $('#hide-StudyData-area')
// });
// }
//
// function initStudyDataList() {
// $("#studyData_table")
// .dataTable(
// {
// "sAjaxSource" : path
// + "/studyDataController/pageList.do",
// "sServerMethod" : "post",
// "bProcessing" : true, // DataTables载入数据时，是否显示‘进度’提示
// "bServerSide" : false, // 是否启动服务器端数据导入
// "bStateSave" : false, // 是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态
// "aLengthMenu" : [ 10, 20, 40, 60 ], // 更改显示记录数选项
// "bLengthChange" : false,// 每行显示记录数
// "iDisplayLength" : 2, // 默认显示的记录数
// "bAutoWidth" : false, // 是否自适应宽度
// "sScrollY" : "700px",
// "bScrollCollapse" : true, //
// 是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
// "bPaginate" : true, // 是否显示（应用）分页器
// "bInfo" : true, // 是否显示页脚信息，DataTables插件左下角显示记录数
// "sPaginationType" : "full_numbers", // 详细分页组，可以支持直接跳转到某页
// "bSort" : true, // 是否启动各个字段的排序功能
// "aaSorting" : [ [ 1, "asc" ] ], // 默认的排序方式，第1列，升序排列
// "bFilter" : false, // 是否启动过滤、搜索功能
// "aoColumnDefs" : [ {
// sDefaultContent : '',
// aTargets : [ '_all' ]
// } ],
// "aoColumns" : [
// {
// "mDataProp" : "sdId",
// "bSortable" : false,
// "sWidth" : "32px",
// "mRender" : function(data, type, full) {
// if (full.check == true) {
// return "<input type=\"checkbox\" checked=\"checked\" data-name=\""
// + full.dataName
// + "\" class=\"checkbox jobbox\" value=\""
// + data + "\">";
// } else {
// return "<input type=\"checkbox\" data-name=\""
// + full.dataName
// + "\" class=\"checkbox jobbox\" value=\""
// + data + "\">";
// }
// }
// }, {
// "sTitle" : "资料编号",
// "sClass" : "center",
// "mDataProp" : "dataNum"
// }, {
// "sTitle" : "资料名称",
// "sClass" : "center",
// "mDataProp" : "dataName"
// }, {
// "sTitle" : "资料备注",
// "sClass" : "center",
// "mDataProp" : "dataMemo"
// }, ],
// "oLanguage" : { // 国际化配置
// "sProcessing" : "正在获取数据，请稍后...",
// "sLengthMenu" : "显示 _MENU_ 条",
// "sZeroRecords" : "没有您要搜索的内容",
// "sInfo" : "显示 _START_ 到 _END_ 条 ，共 _TOTAL_ 条",
// "sInfoEmpty" : "记录数为0",
// "sInfoFiltered" : "",
// "sInfoPostFix" : "",
// "sSearch" : "搜索",
// "sUrl" : "",
// "oPaginate" : {
// "sFirst" : "",
// "sPrevious" : "上一页",
// "sNext" : "下一页",
// "sLast" : ""
// }
// },
// "fnServerParams" : function(aoData) {
// aoData.push({
// "name" : "dataName",
// "value" : $("#dataName").val()
// }, {
// "name" : "stuIds",
// "value" : $("#stuId").val()
// });
// }
// });
// isLoadJob = true;
// }
//
// function subChoiceStudyData(nameId, sdId) {
// $("#studyData_table tbody .jobbox:checked").each(function(index) {
// $a = $(this);
// if ($(".divclass").length != 0) {
// var flag = true;
// $(".taskStuids").each(function(index) {
// var id = $(this).val();
// if (id == $a.val()) {
// flag = false;
// }
// });
// if (flag) {
// stuAppend();
// }
// } else {
// stuAppend();
// }
// });
// layer.closeAll('page');
// }
