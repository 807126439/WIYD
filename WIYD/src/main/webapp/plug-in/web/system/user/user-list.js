$(function(){
	
	
	   $('#user_table').DataTable($.extend({},DATA_TABLES.DEFAULT_OPTION,{
		   "sAjaxSource": path+"/userController/getPageData.do",
	       "sServerMethod": "post",
	       "bServerSide": true, //是否启动服务器端数据导入        
	       aaSorting: [
	         //默认第几个排序
	         [1, "desc"]
	       ], 
	       sScrollY:typeof $('.table_datatable').attr('fixtable')==="string"?'300':"auto", //固定表单头部和高度    
	       "aoColumns": [
	                     DATA_TABLES.COLUMN.CHECKBOX,		   					
	                    { "sTitle": "用户名", "width": "150px","mDataProp":"userName" }, 
	                    { "sTitle": "中文名", "width": "150px","mDataProp":"chineseName" },  
	                    { "sTitle": "所属部门", "width": "120px","mDataProp":"departName"},
	                    { "sTitle": "手机号码", "width": "180px","mDataProp":"phone"},  
	                    { "sTitle": "创建时间", "width": "150px","mDataProp":"gmtCreate" },                           
	                    { "sTitle": "是否可用","width":"80px","mDataProp":"enabled",
	                         "mRender": function (data, type, full) {
	                  		  if(data == true){
	                  		  	return " <span class=\"label label-success radius\">是</span>";
	                  		  }else{
	                  		  	return " <span class=\"label label-defaunt radius\">否</span>";
	                  		  }	
	
	                       }
	                    
	                    },                          
	                    {
	                        "sTitle": "操作",
	                        "mDataProp":"id" ,                              
	                        "bSortable": false,
	                        "width":"170px",
	                        "mRender": function (data, type, full) {
	                   
	                     	    var html = "<div class=\"t-btn-container\">";                            		
	                       		if(typeof(user_edit)!= "undefined"){
	                       			 if(user_edit.length>0){
	                       			 	html+=user_edit.format(data);
	                       			 }                            			
	                       		}
	                       		if(typeof(score_add)!= "undefined"){
	                       			 if(score_add.length>0){
	                       			 	html+=score_add.format(data,full.userName);
	                       			 }                            			
	                       		}                      	                       		
	                       		if(typeof(user_change_pwd)!= "undefined"){
	                       			 if(user_change_pwd.length>0){
	                       			 	html+=user_change_pwd.format(data,full.userName);
	                       			 }	                              			
	                       		}
	                       		if(typeof(del_user)!= "undefined"){
	                       			 if(del_user.length>0){
	                       			 	html+=del_user.format(data,full.userName);
	                       			 }	                              			
	                       		}
	                   	          
	                       		
	                       		return html+"</div>";
	                        }
	                    }
	           ],
	         
	          "fnServerParams" : function (aoData) {
				   aoData.push(
						{ "name": "userName", "value": $("#user_name").val() },
						{ "name": "chineseName", "value": $("#cn_name").val() },
						{ "name": "dataType", "value": $("#dataType").val() }	
				    );
				
				
				
				}
	     }));

	  });

	

		
		
		/*用户-添加*/
		function member_add(title,w,h){
			var url = path + "/userController/skipAddUser.do";
			layer_show(title,url,w,h,true);
		}
		
		/*用户-修改*/
		function member_edit(title,id,w,h){
			var url = path + "/userController/getDetail.do?uid="+id;
			layer_show(title,url,w,h,true);
		}
		
		
		
		/*密码-修改*/
		function change_password(id, name) {
			$("#changPwd-id").val(id);		
			bindForm("form-change-password",path+ "/userController/changePassword.do",2);
			
			layer.open({
				type : 1,
				shift : 2,
				shadeClose : true, //开启遮罩关闭
				title : "修改 [" + name + "] 密码",
				area : [ '650px' ],
				content : $('#changge-pwd-div'),
				end : function(index, layero) {
					$("#form-change-password .Validform_checktip").remove();
					$("#form-change-password").Validform().resetForm();

				}
			});

			

		}
		
		
		/*用户-删除*/
		function user_del(obj,id){
			layer.confirm('确认要删除吗？',function(index){
			   sumbitDel(id);
			});
		}
		
		
		
		
		
		function sumbitDel(ids){
			$.ajax({
				    url: path+"/userController/deleteUser.do",
				    traditional:true,
				    dataType:'json',  
				    data:{
				    	"ids":ids
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
			$("#user_table").DataTable().draw(signal);					
		}
		
	  
		