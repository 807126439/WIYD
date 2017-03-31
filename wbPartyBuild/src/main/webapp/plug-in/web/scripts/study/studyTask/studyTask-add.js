function checkSpan(thisSpan) {
	$(thisSpan).parent().remove();
}

function goSearch(attrId) {
	$("#" + attrId).dataTable().fnDraw();
}

$(function() {
	// 设置学习任务“状态”单选框响应动作
	$('.skin-minimal input').iCheck({
		checkboxClass : 'icheckbox-blue',
		radioClass : 'iradio-blue',
		increaseArea : '20%'
	});

	$("#form-StydyTask").Validform({
		tiptype : 2,
		ajaxPost : true,// ajax方式提交表单数据
		beforeSubmit : function(curform) {
			return true;
		},
		callback : function(data) {
			if (data.status == "y" || data.status == "Y") {
				setTimeout(function() {
					parent.goSearch();
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}, 800);
			}
		}
	});

	$("div.divclass input").mouseover(function() {
		layer.tips($(this).val(), $(this), {
			tips : [ 1, '#000000' ]
		// 1配置上面显示，还可配置颜色
		});
	});
});
