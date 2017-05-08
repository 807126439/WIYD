 $(function() {


   $('#auth_table').DataTable($.extend({},DATA_TABLES.DEFAULT_OPTION,{
       "sAjaxSource": path+"/authController/getPageData.do",
       "sServerMethod": "post",
       "bServerSide": true, //是否启动服务器端数据导入        
       aaSorting: [
         //默认第几个排序
         [1, "desc"]
       ], 
       sScrollY:typeof $('.table_datatable').attr('fixtable')==="string"?'300':"auto", //固定表单头部和高度    
       "aoColumns": [
   			   DATA_TABLES.COLUMN.CHECKBOX,
   			   { "sTitle": "序号","mDataProp":"sort","width":"32px" }, 
               { "sTitle": "权限名", "width": "150px","mDataProp":"authText" },                     
               { "sTitle": "权限码", "width": "100px","mDataProp":"authCode" },
               { "sTitle": "资源地址", "sClass": "center","mDataProp":"resourecesUrl" },
               { "sTitle": "开启控制",
                "width": "80px",
                "mDataProp":"flag",
                "mRender": function (data, type, full) {
            		   if(data == 0){
             		  	  return "<span class=\"label label-defaunty radius\">否</span>";
             		   }else if(data == 1){
             		      return "<span class=\"label label-primary radius\">是</span>";                            		                              		   
             		   }else{
             		    return "";
             		  }	
                 }
               },
               { "sTitle": "权限类型 ",
            	  "width": "80px",
                 "mDataProp":"authType",
                  "mRender": function (data, type, full) {                                                              	                             	                             	
                       if(data == 0){
             		  	   return "<span class=\"label label-success radius\">菜单</span>";   
             		   }else if(data == 1){
             		      return "<span class=\"label label-defaunty radius\">访问</span>";                            		                              		   
             		   }else if(data == 2){
             		      return "<span class=\"label label-primary radius\">按钮</span>";
             		  }else{
             		    return "";
             		  }		
                   }
                 },                               
           
               { "sTitle": "排序", "width": "60px","mDataProp":"authOrder"},                         
               { "sTitle": "图标", "width": "45px","mDataProp":"icon", "bSortable": false,
               	    "mRender": function (data, type, full) {
            		 	
                      return "<i class=\"Hui-iconfont\">"+data+"</i>";
                   }
               
                },  
               { "sTitle": "父节点","width": "150px","mDataProp":"parentName" },                         
               {
                   "sTitle": "操作",
                   "sClass": "center",  
                   "width":"100px",
                   "mDataProp":"id" ,                              
                   "bSortable": false,
                   "mRender": function (data, type, full) {
                	   var html = "<div class=\"ui-btn-group\">";                              		
                		if(typeof(edit_auth)!= "undefined"){
                			 if(edit_auth.length>0){
                			 	html+=edit_auth.format(data);
                			 }                            			
                		}
                		if(typeof(del_auth)!= "undefined"){
                			 if(del_auth.length>0){
                			 	html+=del_auth.format(data);
                			 }	                              			
                		}
              	                             	                             	
               			return html+"</div>";
                   }
               }
           ],
         
          "fnServerParams" : function (aoData) {
			aoData.push(
			{ "name": "authName", "value": $("#ser_name").val() },					
			{ "name": "authCode", "value": $("#ser_code").val() },
			{ "name": "parAuthName", "value": $("#ser_par_name").val() },	
			{ "name": "authType", "value": $("#auth-type").val() },
			{ "name": "flag", "value": $("#auth-flag").val() }
			);
			
			
			
			}
     }));

  });




	function auth_add(title,url,w,h){
		layer_show_end(title,url,w,h,goSearch);
	}
	

	function auth_edit(title,id,w,h){
	     var url = path+"/authController/getDetail.do?aid="+id;
	     layer_show_end(title,url,w,h,goSearch);
	}
	
	

	function auth_del(obj,id){
		layer.confirm('确认要删除吗？',function(index){
		      sumbitDel(id);
		});
	}
	
	function datadel(){
	   var l = new Array();
	         
       $("#auth_table .checkbox:checked").each(function() {		
		  l.push($(this).val());		
	   });
	   	 
	   if(l.length > 0){
		   	layer.confirm("确认要删除 "+l.length+" 条记录吗？",function(index){
			    sumbitDel(l);
			});
	   }else{
	     layer.msg('无选中的项!',{icon: 0,time:1000});
	   }
	}
	
	function sumbitDel(ids){
		$.ajax({
			    url: path+"/authController/delAuth.do",
			    traditional:true,
			    dataType:'json',  
			    data:{
			    "aids":ids
			    },
			    type:"post",
			    cache : false,  
			    async : false,  
			    success:function(data){	
			    		if(data.status == "y" || data.status == "Y"){
			    			layer.msg(data.info,{icon: 1,time:1000});
			          		goSearch();	
			    		}else{
			    			layer.msg(data.info,{icon: 2,time:2500});
			    		}	    					         				           
			                		           
			       },  
			     error : function(error) {  
			            alert(error); 		            
			       } 
		  });
		
	
	}	



  function goSearch(signal){	
	$("#auth_table").DataTable().draw(signal);					
  }
