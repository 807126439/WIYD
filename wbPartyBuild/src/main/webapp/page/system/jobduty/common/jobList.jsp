<%@ page language="java" pageEncoding="utf-8"%>


	<div id="hide-job-area" style="display: none;width: 720px;">
	
		<div class="text-l mt-5 ml-20">																								
			<input type="text" class="input-text" style="width:200px" placeholder="职务名" id="job_name" >&nbsp;		
			<button type="button" class="btn btn-default radius" id="" name="" onclick="goSearch('job_table')"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
		
			<input class="btn btn-primary radius r mr-20" type="button" value="确定" onclick="subChoiceJob('job-name','job-id')">
		</div>
	
				
		<div class="pd-20">
			<table id="job_table" class="table table-border table-bordered table-hover table-bg table-sort">			
			</table>
		
		</div>
	</div>