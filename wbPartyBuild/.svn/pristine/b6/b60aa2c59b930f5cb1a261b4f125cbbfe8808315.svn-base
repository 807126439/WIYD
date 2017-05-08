var mtable;
$(function() {

	mtable = $("#studyTask_table")
			.dataTable(
					{
						"sAjaxSource" : path
								+ "/studyTaskController/pageList.do",
						"sServerMethod" : "post",
						"bProcessing" : true, // DataTables载入数据时，是否显示‘进度’提示
						"bServerSide" : true, // 是否启动服务器端数据导入
						"bStateSave" : true, // 是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态
						"aLengthMenu" : [ 10, 20, 40, 60 ], // 更改显示记录数选项
						"bLengthChange" : true,// 每行显示记录数
						"iDisplayLength" : 10, // 默认显示的记录数
						"bAutoWidth" : false, // 是否自适应宽度
						// "sScrollY": "500px",
						"bScrollCollapse" : true, // 是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
						"bPaginate" : true, // 是否显示（应用）分页器
						"bInfo" : true, // 是否显示页脚信息，DataTables插件左下角显示记录数
						"sPaginationType" : "full_numbers", // 详细分页组，可以支持直接跳转到某页
						"bSort" : true, // 是否启动各个字段的排序功能
						"aaSorting" : [ [ 1, "asc" ] ], // 默认的排序方式，第2列，升序排列
						"bFilter" : false, // 是否启动过滤、搜索功能
						"aoColumnDefs" : [ {
							sDefaultContent : '',
							aTargets : [ '_all' ]
						}
						// {"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
						// {"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
						],
						"aoColumns" : [
								{
									"mDataProp" : "stId",
									"sWidth" : "20px",
									"bSortable" : false,
									"mRender" : function(data, type, full) {
										return "<input type=\"checkbox\" class=\"checkbox\" value=\""
												+ data + "\">";
									}
								},
								{
									"sTitle" : "状态",
									"sClass" : "center",
									"sWidth" : "50px",
									"mDataProp" : "status",
									"mRender" : function(data, type, full) {
										if (data == "1") {
											return " <span class=\"label label-success radius\">正常</span>";
										} else {
											return " <span class=\"label label-defaunt radius\">失效</span>";
										}
									}
								},
								{
									"sTitle" : "开始时间",
									"sWidth" : "100px",
									"sClass" : "center",
									"mDataProp" : "startTime"
								},
								{
									"sTitle" : "任务编号",
									"sClass" : "center",
									"mDataProp" : "taskNum"
								},
								{
									"sTitle" : "任务名称",
									"sClass" : "center",
									"mDataProp" : "taskName"
								},
								{
									"sTitle" : "试卷名称",
									"sClass" : "center",
									"mDataProp" : "paperName"
								},
								{
									"sTitle" : "结束时间",
									"sWidth" : "100px",
									"sClass" : "center",
									"mDataProp" : "endTime"
								},
								{
									"sTitle" : "操作",
									"sWidth" : "80px",
									"sClass" : "center",
									"mDataProp" : "stId",
									"bSortable" : false,
									"mRender" : function(data, type, full) {
										var html = "<div class=\"t-btn-container\">";
										if (typeof (update_StudyTask) != "undefined") {
											if (update_StudyTask.length > 0) {
												html += update_StudyTask
														.format(full.stId);
											}
										}
										if (typeof (delete_StudyTask) != "undefined") {
											if (delete_StudyTask.length > 0) {
												html += delete_StudyTask
														.format(full.stId);
											}
										}
										return html + "</div>";
									}
								} ],
						"oLanguage" : { // 国际化配置
							"sProcessing" : "正在获取数据，请稍后...",
							"sLengthMenu" : "显示 _MENU_ 条",
							"sZeroRecords" : "没有您要搜索的内容",
							"sInfo" : "显示 _START_ 到  _END_ 条 ，共 _TOTAL_ 条",
							"sInfoEmpty" : "记录数为0",
							"sInfoFiltered" : "(当前显示记录 _MAX_ 条)",
							"sInfoPostFix" : "",
							"sSearch" : "搜索",
							"sUrl" : "",
							"oPaginate" : {
								"sFirst" : "第一页",
								"sPrevious" : "上一页",
								"sNext" : "下一页",
								"sLast" : "最后一页"
							}
						},
						"fnServerParams" : function(aoData) {
							aoData.push({
								"name" : "taskNum",
								"value" : $("#taskNum").val()
							}, {
								"name" : "taskName",
								"value" : $("#taskName").val()
							});
						}
					});

	$('.table-sort tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
});

/* 添加 */
function studyTask_add(title, w, h) {
	var url = path + "/studyTaskController.do?add";
	layer_full(title, url, w, h, false);
}

/* 修改 */
function studyTask_edit(stId) {
	var url = path + "/studyTaskController.do?edit&stId=" + stId;
	layer_full("修改学习任务", url, '400', '300', false);
}

/* 删除 */
function studyTask_del(obj, sdId) {
	layer.confirm('确认要删除吗？', function(index) {
		sumbitDel(sdId);
	});
}
/* 批量删除 */
function datadel() {
	var l = new Array();
	$("#studyTask_table .checkbox:checked").each(function() {
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
/* 删除共用的方法 */
function sumbitDel(stId) {
	$.ajax({
		url : path + "/studyTaskController/studyTaskDel.do",
		traditional : true,
		dataType : 'json',
		data : {
			"stId" : stId
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
				goSearch();
			} else {
				layer.alert(data.info, {
					icon : 2
				});
				goSearch();
			}
		},
		error : function(error) {
			alert(error);
		}
	});
}

function goSearch() {
	mtable.fnDraw();
};
