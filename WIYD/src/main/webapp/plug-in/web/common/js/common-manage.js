
 function initTableCheck(){
   
   	   //checkbox美化
        $('.skin-minimal input').iCheck({
          checkboxClass: 'icheckbox-blue',
          radioClass: 'iradio-blue',
          increaseArea: '20%'
        });
     
        //table里批量选择的check方法       
      $("table thead th input:checkbox").on("click" , function(){
      	 if ($(this).is(':checked')){
          $(this).closest("table").find("tr > td:first-child input:checkbox").iCheck('check');
        }else{
          $(this).closest("table").find("tr > td:first-child input:checkbox").iCheck('uncheck');
        }
		
     });
      
   }
 
 
 
    //换页时清除全选
	function clearAllCheck(tableId){
		 if($("#"+tableId+" thead th input:checkbox").is(':checked')){//清除全选
	        $("#"+tableId+" thead th input:checkbox").iCheck('uncheck');
	     }
	}
	
	
	
	
	 /*绑定表单*/
    function bindForm(formId,action){
       $("#"+formId).attr("action",action);
    	
       $("#"+formId).Validform({
			tiptype:1,
			ajaxPost:true,//ajax方式提交表单数据		
			callback:function(data){
				if(data.status == "y" || data.status == "Y"){				
				      setTimeout(function(){
					    $("#Validform_msg").hide();
						layer.closeAll('page');
						     					      
				      },500); 					
				}
				
			}
		}); 
    
    }
	
	
	
	