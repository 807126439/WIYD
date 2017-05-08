$(function() {
	tabPage(".ui-btntag", "active");
	$("#recordView_table")
			.DataTable(
					$
							.extend(
									{},
									DATA_TABLES.DEFAULT_OPTION,
									{
										"sAjaxSource" : path
												+ "/recordViewController/morePageData.do",
										"sServerMethod" : "post",
										"bServerSide" : true, // 是否启动服务器端数据导入
										aaSorting : [
										// 默认第几个排序
										/* [ 0, "desc" ] */],
										sScrollY : typeof $('.table_datatable')
												.attr('fixtable') === "string" ? '300'
												: "auto", // 固定表单头部和高度
										"aoColumns" : [
												// DATA_TABLES.COLUMN.CHECKBOX,
												{
													"sTitle" : "序号",
													"width" : "",
													"bSortable" : false,
													"mDataProp" : "sort"
												},
												{
													"sTitle" : "知识分类",
													"width" : "",
													"bSortable" : false,
													"mDataProp" : "categoryName"
												},
												{
													"sTitle" : "名称",
													"width" : "",
													"bSortable" : false,
													"mDataProp" : "documentName",
													"mRender" : function(data,
															type, full) {
														return (data == null || data == "") ? "无"
																: data;
													}
												},
												{
													"sTitle" : "查看次数",
													"width" : "",
													"bSortable" : false,
													"mDataProp" : "viewTime"
												},
												{
													"sTitle" : "创建人",
													"width" : "",
													"bSortable" : false,
													"mDataProp" : "uploderName"
												},
												{
													"sTitle" : "查看时间",
													"width" : "180px",
													"bSortable" : false,
													"mDataProp" : "gmtModified"
												},
												{
													"sTitle" : "操作",
													"mDataProp" : "id",
													"width" : "120px",
													"bSortable" : false,
													"mRender" : function(data,
															type, full) {
														var html = "<div class=\"ui-btn-group\">";
														if (typeof (model_check) != "undefined"
																&& full.categoryName == "有问有答") {
															if (model_checkQuestion.length > 0) {
																html += model_checkQuestion
																		.format(data);
															}
														} else if (typeof (model_checkQuestion) != "undefined") {
															if (model_check.length > 0) {
																html += model_check
																		.format(data);
															}
														}
														return html + "</div>";
													}
												} ],
										"fnServerParams" : function(aoData) {
											aoData
													.push(
															{
																"name" : "categoryId",
																"value" : $(
																		".ui-btntag li[class='active']")
																		.children(
																				"input")
																		.val()
															},
															{
																"name" : "sidx",
																"value" : $(
																		".ui-btntag_sort li[class='active']")
																		.children(
																				"input")
																		.val()
															},
															{
																"name" : "field_1",
																"value" : $(
																		"#field_1")
																		.val()
															});
										}
									}));

	// 点击触发分类显示
	$("div.filters ul.ui-btntag").click(function() {
		goSearch(false);
	});
});

// 查看
function check_model(title, id, w, h) {
	var url = path + "/documentFileController/skipCheckDocumentFile.do?id="
			+ id;
	layer_full(title, url, w, h);
}

// 查看问题
function checkQuestion_model(title, id, w, h) {
	var url = path + "/documentFileController/skipCheckDocumentFile.do?id="
			+ id;
	layer_full(title, url, w, h);
}

function model_add(title, w, h) {
	var url = path + "/recordViewController/skipAddRecordView.do";
	layer_show(title, url, w, h);
}

function edit_model(title, id, w, h) {
	var url = path + "/recordViewController/getDetail.do?id=" + id;
	layer_show(title, url, w, h);
}

function del_model(obj, id) {
	layer.confirm('确认要删除吗？', function(index) {
		sumbitDel(id);
	});
}

function datadel() {
	var l = new Array();

	$("#recordView_table .checkbox:checked").each(function() {
		l.push($(this).val());
	});

	if (l.length > 0) {
		layer.confirm("确认要删除 " + l.length + " 条记录吗？", function(index) {

			sumbitDel(l);
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
		url : path + "/recordViewController/deleteRecordView.do",
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
				goSearch(false);
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

function goSearch(signal) {
	$("#recordView_table").DataTable().draw(signal);
}
