$(function(){
						
			$("#checkAll").click(function(){
				 var singal = $(this).prop("checked");			
			     var checkVal = false;
			     if(singal){
			     	checkVal = true;			     	
			     }
			     
			     $("#selected-roles").val("");//清空内容
			     var currs = "";
				 $(".roleBox").each(function(index){
				 	if(checkVal){
				 		if(index == 0){
				 			currs+=$(this).attr("data-name");
				 		}else{
				 			currs+=","+$(this).attr("data-name");
				 		}
							
				 	}
					$(this).prop("checked",checkVal);
				 });
				
				  if(checkVal){
			     	$("#selected-roles").val(currs);			     	
			     }
				 
			
			});
			
			$("#role_table tbody .roleBox").click(function(){
					
				var currs = "";
						
				$("#role_table tbody .roleBox:checked").each(function(index){
			
					if(index == 0){
			 			currs+=$(this).attr("data-name");
			 		}else{
			 			currs+=","+$(this).attr("data-name");
			 		}
				});	
				
				$("#selected-roles").val(currs);	
			
			});
			
			$('#roletable').dataTable({
				"bPaginate" : false,
				"bFilter" : false,
				"bLengthChange" : false,
				"bInfo" : false,
				"sScrollY": "330px",
				"aaSorting": [[ 1, "desc" ]],
				"aoColumnDefs": [
				 
				  {"orderable":false,"aTargets":[0,0]}// 制定列不参与排序
				]
			});
			
		
			
	});
	
	
		function choiceRoles(){
			layer.open({
			  type: 1,
			  title: "请选择责任角色",
			  area: ['560px', '300px'],
			  shade: 0.2,
			  content: $('#hide_role')
			});
		}
	