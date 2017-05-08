$(function(){
			/*全选*/
			$("table thead th input:checkbox").on("click" , function(){
				$(this).closest("table").find("tr > td:first-child input:checkbox").prop("checked",$("table thead th input:checkbox").prop("checked"));
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
				  title: "选择用户角色",
				  area: ['560px', '300px'],
				  shade: 0.2,
				  content: $('#hide_role'),
				  end: function(index, layero){
				  	   handleCheckRole("role_table","selected-roles");
				  	   $("#selected-roles").trigger("blur");
				  }
			  
			});
			
		}


		 function handleCheckRole(tableId,bindInputId){
			
		      var currs = "";
		      $("#"+tableId+" tbody .checkBox:checked").each(function(i){
		      	
		      	    if(i == 0){
			 			currs+=$(this).attr("data-name");
			 		}else{
			 			currs+=","+$(this).attr("data-name");
			 		}
		         
		      });
		
		      $("#"+bindInputId).val(currs);

		
		}
		
		
		
		
		
		
		
	