var paperLoaded = false;

// ==========选择试卷==========
function choosePaper() {
	if (!paperLoaded) {
		initExamPaperList();
	}
	layer.open({
		type : 1,
		title : "选择测试资料",
		maxmin : true, // 开启最大化最小化按钮
		area : [ '950px', '600px' ],
		shadeClose : true,
		// shade : 0.2,
		content : $('#hide-ExamPaper-area'),
		scrollbar : false
	});
}

// ==========初始化试卷选择列表==========
function initExamPaperList() {
	$("#examPaper_table").dataTable(
			{
				"sAjaxSource" : path + "/examPaperController/pageList.do",
				"sServerMethod" : "post",
				"bProcessing" : true, // DataTables载入数据时，是否显示‘进度’提示
				"bServerSide" : true, // 是否启动服务器端数据导入
				"bStateSave" : false, // 是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态
				"aLengthMenu" : [ 10, 20, 40, 60 ], // 更改显示记录数选项
				"bLengthChange" : true,// 每行显示记录数
				"iDisplayLength" : 10, // 默认显示的记录数
				"bAutoWidth" : true, // 是否自适应宽度
				// "sScrollY" : "700px",
				"bScrollCollapse" : true, // 是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
				"bPaginate" : true, // 是否显示（应用）分页器
				"bInfo" : true, // 是否显示页脚信息，DataTables插件左下角显示记录数
				"sPaginationType" : "full_numbers", // 详细分页组，可以支持直接跳转到某页
				"bSort" : true, // 是否启动各个字段的排序功能
				"aaSorting" : [ [ 1, "asc" ] ], // 默认的排序方式，第1列，升序排列
				"bFilter" : false, // 是否启动过滤、搜索功能
				"aoColumnDefs" : [ {
					sDefaultContent : '',
					aTargets : [ '_all' ]
				} ],
				"aoColumns" : [
						{
							"mDataProp" : "id",
							"bSortable" : false,
							"sWidth" : "32px",
							"mRender" : function(data, type, full) {
								return "<input type=\"radio\" "
										+ "name=\"paper\" "
										+ "class=\"paperId jobbox\" "
										+ "data-name=\"" + full.paperName
										+ "\" value=\"" + full.id + "\">";
							}
						}, {
							"sTitle" : "试卷类型",
							"sClass" : "center",
							"mDataProp" : "paperType",
							"mRender" : function(data, type, full) {
								if (data == 1) {
									return "随机生成";
								} else {
									return "指定生成";
								}
							}
						}, {
							"sTitle" : "试卷名称",
							"sClass" : "center",
							"mDataProp" : "paperName"
						}, {
							"sTitle" : "考试时间",
							"sClass" : "center",
							"mDataProp" : "examMinute"
						}, ],
				"oLanguage" : { // 国际化配置
					"sProcessing" : "正在获取数据，请稍后...",
					"sLengthMenu" : "显示 _MENU_ 条",
					"sZeroRecords" : "没有您要搜索的内容",
					"sInfo" : "显示 _START_ 到  _END_ 条 ，共 _TOTAL_ 条",
					"sInfoEmpty" : "记录数为0",
					"sInfoFiltered" : "",
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
						"name" : "paperName",
						"value" : $("#queryByName").val()
					});
				}
			});

	// 测试试卷，按行选取
	// $('.table-sort tbody').on(
	// 'click',
	// 'tr',
	// function() {
	// $(".table-sort tbody input[type='radio']").attr('checked', '');
	// $(this).children('td').eq(0).children('input').attr('checked',
	// 'checked');
	// });

	paperLoaded = true;
}

// ==========确认选中试卷==========
function subExamPaperChoice() {
	$a = $("#examPaper_table tbody .jobbox:checked");
	if ($a.val() != null) {
		$("#shijuan")
				.html(
						"<div class='divclass'>"
								+ "<input readonly='readonly' type='text' id='paperName' name='paperName' value="
								+ $a.attr('data-name')
								+ " />"
								+ "<span onclick='checkSpan(this)'>"
								// + " /><span onclick='checkSpan(this)'
								// style='float: right;'>"
								// + "<img
								// src='/wbPartyBuild/plug-in/web/portals/image/cancel.png'
								// width='24px' height='25px'/>"
								+ "<i class='Hui-iconfont Hui-iconfont-close2'></i>"
								+ "</span>"
								+ "<input type=\"hidden\" id='paperid' name='paperid' value="
								+ $a.val() + " />" + "</div>");
	}
	layer.closeAll('page');
}
