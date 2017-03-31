<%@ page language="java" pageEncoding="utf-8"%>

 <div id="hide-user-area" style="width: 720px;display: none;">

   		<div class="text-l mt-5 ml-20">																								
			<input type="text" class="input-text" style="width:200px" placeholder="用户名" id="user_name" >&nbsp;		
			<button type="button" class="btn btn-default radius" id="" name="" onclick="goSearch('user_table')"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
		
			<input class="btn btn-primary radius r mr-20" type="button" value="确定" onclick="subChoiceUser('user-name','user-id')">
		</div>
   		
   			
   	
   
		<div class="pd-20">
			<table id="user_table" class="table table-border table-bordered table-hover table-bg table-sort">			
			</table>
		
		</div>
		
		
		
	</div>
	
