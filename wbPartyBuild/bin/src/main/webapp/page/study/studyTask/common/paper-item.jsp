
<%@ page language="java" pageEncoding="utf-8"%>
<div id="hide-ExamPaper-area"
	style="display: none;width:720px;margin:0 auto">
	<div style="">
		<!-- ==========关键字搜索========== -->
		<!-- ==========div去掉class="mt-5 ml-20"========== -->
		<div class="" style="margin: 20px 0px 0px 0px; text-align: center;">
			试卷名称: <input type="text" class="input-text" style="width:200px"
				placeholder="试卷名称" id="queryByName" name="paperName">&nbsp;
			<button type="button" class="btn btn-default radius"
				onclick="goSearch('examPaper_table')">
				<i class="Hui-iconfont">&#xe665;</i> 查询
			</button>
			<input class="btn btn-primary radius " type="button" value="确定"
				onclick="subExamPaperChoice()">
		</div>
		<!-- ==========end of 关键字搜索========== -->
		<div class="pd-20">
			<table id="examPaper_table"
				class="table table-border table-bordered table-hover table-bg table-sort">
			</table>
		</div>
	</div>
</div>
