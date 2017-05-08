var mtable;
$(function() {

	mtable = $("#studyCount_table").dataTable({
		"sAjaxSource" : path + "/examScoreController/countList.do",
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
		"bSort" : false, // 是否启动各个字段的排序功能
		"aaSorting" : [ [ 1, "asc" ] ], // 默认的排序方式，第2列，升序排列
		"bFilter" : false, // 是否启动过滤、搜索功能
		"aoColumnDefs" : [ {
			sDefaultContent : '',
			aTargets : [ '_all' ]
		}
		// {"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		// {"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
		],
		"aoColumns" : [ {
			"sTitle" : "序号",
			"sClass" : "center",
			"sWidth" : "10px",
			"mDataProp" : "sort"
		}, {
			"sTitle" : "用户名",
			"sClass" : "center",
			"sWidth" : "50px",
			"mDataProp" : "username"
		}, {
			"sTitle" : "测试次数",
			"sWidth" : "100px",
			"sClass" : "center",
			"mDataProp" : "num"
		}, {
			"sTitle" : "最近一次参加测试时间",
			"sWidth" : "100px",
			"sClass" : "center",
			"mDataProp" : "lastOperatorTime"
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
				"name" : "userName",
				"value" : $("#userName").val()
			}, {
				"name" : "beginTime",
				"value" : $("#beginTime").val()
			}, {
				"name" : "endTime",
				"value" : $("#endTime").val()
			}, {
				"name" : "departmentIds",
				"value" : $("#departmentIds").val()
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

// TODO
// 我的申请-导出所选
// function exportSelected() {
// alert("exportSelected");
// TODO 新建数组记录选中记录的id？
// var array = new Array();
// $("#normApply-table input[type='checkbox']:checked").each(function() {
// array.push($(this).val());
// });
// if (array.length > 0) {
// sumbitForm("export-form");
// } else {
// layer.msg('无选中的项!', {
// icon : 0,
// time : 1000
// });
// }
// }

// 我的申请-导出全部
function exportAll() {
	window.location.href = path
			+ "/examScoreController/exportAllExaminationCount.do";
}

function goSearch() {
	mtable.fnDraw();
};
