function checkSpan(thisSpan) {
	$(thisSpan).parent().remove();
}

function goSearch(attrId) {
	$("#" + attrId).dataTable().fnDraw();
}

$(function() {
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
});
