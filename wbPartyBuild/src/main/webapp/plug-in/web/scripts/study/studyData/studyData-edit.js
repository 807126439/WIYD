var mf = null;
$(function() {
	$list = $("#fileList");
	$btn = $("#btn-star");
	state = "pending";

	$("#form-StydyData").Validform({
		tiptype : 2,
		ajaxPost : true,// ajax方式提交表单数据
		beforeSubmit : function(curform) {
			var sdId = $("#sdId").val();
			var cateName = $("#cateName").val();
			var dataNum = $("#dataNum").val();
			var dataName = $("#dataName").val();
			var dataMemo = $("#dataMemo").val();
			var cateId = $("[name='cateId']").val();
			if (mf != null) {
				// 新上传资料文件的处理
				if (state === 'uploading') {
					uploader.stop();
				} else {
					uploader.option('formData', {
						"sdId" : sdId,
						"cateName" : cateName,
						"dataNum" : dataNum,
						"dataName" : dataName,
						"dataMemo" : dataMemo,
						"cateId" : cateId,
					});
					uploader.upload();
				}
				return false;
			} else if ($("#fileList").children().length > 0) {
				// 原本有资料文件的处理
				if (state === 'uploading') {
					uploader.stop();
				} else {
					uploader.option('formData', {
						"sdId" : sdId,
						"cateName" : cateName,
						"dataNum" : dataNum,
						"dataName" : dataName,
						"dataMemo" : dataMemo,
						"cateId" : cateId,
					});
					uploader.upload();
				}
			} else {
				layer.alert("请选择资料文件！");
				return false;
			}
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

	var uploader = WebUploader.create({
		auto : false,
		swf : path + '/plug-in/h-ui/lib/webuploader/0.1.5/Uploader.swf',
		// 文件接收服务端。
		server : path + '/studyDataController/editStudyData.do',
		// 选择文件的按钮。可选
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick : '#filePicker',
		formData : {},
		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		resize : false,
		fileNumLimit : 1,
		fileSizeLimit : 2 * 1000 * 1024 * 1024,// 限制大小2000m
		fileSingleSizeLimit : 2 * 1000 * 1024 * 1024,
		compress : false,
	/*
	 * accept: { title: 'videos', extensions: 'flv,mp4', mimeTypes: 'video/*' }
	 */
	});

	// 选择上传文件更新文件显示列表div
	uploader.on('fileQueued', function(file) {
		mf = file;
		// 删除被选元素的子元素
		$list.empty();
		$list.append('<div id="' + file.id + '" class="item">'
				+ '<h4 class="info">' + file.name + '</h4>'
				+ '<p class="state">等待上传...</p>' + '</div>');
	});

	// 文件上传过程中创建进度条实时显示。
	uploader
			.on(
					'uploadProgress',
					function(file, percentage) {
						var $li = $('#' + file.id), $percent = $li
								.find('.progress-box .sr-only');
						// 避免重复创建
						if (!$percent.length) {
							$percent = $(
									'<div class="progress-box"><span class="progress-bar radius"><span class="sr-only" style="width:0%"></span></span></div>')
									.appendTo($li).find('.sr-only');
						}
						$li.find(".state").text("上传中");
						$percent.css('width', percentage * 100 + '%');
					});

	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on('uploadSuccess', function(file) {
		$('#' + file.id).addClass('upload-state-success').find(".state").text(
				"已上传");
	});

	// 文件上传失败，显示上传出错。
	uploader.on('uploadError', function(file) {
		$('#' + file.id).addClass('upload-state-error').find(".state").text(
				"上传出错");
	});

	// 完成上传完了，成功或者失败，先删除进度条。
	uploader.on('uploadComplete', function(file) {
		$('#' + file.id).find('.progress-box').fadeOut();
		setTimeout(function() {
			parent.goSearch();
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}, 1000);
	});

	uploader.on('uploadAccept', function(file, response) {
		if (response.status == "y" || response.status == "Y") {
			layer.msg(response.info, {
				icon : 1,
				time : 1000
			});
			return true;
		} else {
			layer.msg(response.info, {
				icon : 2,
				time : 2000
			});
			return false;
		}
	});

	uploader.on('all', function(type) {
		if (type === 'startUpload') {
			state = 'uploading';
		} else if (type === 'stopUpload') {
			state = 'paused';
		} else if (type === 'uploadFinished') {
			state = 'done';
		}
		if (state === 'uploading') {
			$btn.text('暂停上传');
		} else {
			$btn.text('开始上传');
		}
	});

	// 撤销
	$("#btn-cancel").on('click', function() {
		if (mf != null) {
			removeFile(mf);
		} else {
			// 撤销原本上传文件
			$list.empty();
		}
	});

	function removeFile(file) {
		uploader.removeFile(file, true);
		var $li = $('#' + file.id);
		$li.off().find('.file-panel').off().end().remove();
		mf = null;
	}

});