/** 表格公共方法 */	
	$(function(){
			
		/*全选*/
		$("table thead th input:checkbox").on("click" , function(){
			$(this).closest("table").find("tr > td:first-child input:checkbox").prop("checked",$("table thead th input:checkbox").prop("checked"));
	    });
		
		 $('.table tbody').on( 'click', 'tr', function () {			
				if ( $(this).hasClass('selected') ) {
					$(this).removeClass('selected');
				}else {
					$('.table tbody tr.selected').removeClass('selected');
					$(this).addClass('selected');
			
				}
		});
		 
		 
		 //搜索框收缩
		 $('.sect-mini').click(function () {
		      $(this).closest('.sect').addClass('mini');
		      $(this).hide();
		      $('.sect-restore').show();
		    });
		    $('.sect-restore').click(function () {
		      $(this).closest('.sect').removeClass('mini');
		      $(this).hide();
		      $('.sect-mini').show();
		    });
		 
	});
	
	
	