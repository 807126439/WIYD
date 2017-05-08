	
	$(function(){
				
		 $('.table-sort tbody').on( 'click', 'tr', function () {			
				if ( $(this).hasClass('selected') ) {
					$(this).removeClass('selected');
				}else {
					$('.table-sort tbody tr.selected').removeClass('selected');
					$(this).addClass('selected');
			
				}
		});
	});
	
	
	
   function logout(){	   
		if(confirm("您确定要退出系统吗?")){
			window.parent.location.href = path+"/j_spring_security_logout";	
		}					
   }