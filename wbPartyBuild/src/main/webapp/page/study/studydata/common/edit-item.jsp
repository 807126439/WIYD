 <%@ page language="java"  pageEncoding="utf-8"%>
	<div id="hide-StudyData-area" style="display: none;width:720px;margin:0 auto">
	<div style="">
	   		<div class="text-l mt-5 ml-20" >																					
				资料名称:	<input type="text" class="input-text" style="width:200px" placeholder="资料名称" id="dataName" >&nbsp;		
				<button type="button" class="btn btn-default radius" onclick="goSearch('studyData_table')"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
				<input class="btn btn-primary radius " type="button" value="确定" onclick="subChoiceStudyData('data-name','data-id')">
			</div>
			<div class="pd-20">
				<table id="studyData_table" class="table table-border table-bordered table-hover table-bg table-sort">	
				</table>
			</div>
	</div>
 