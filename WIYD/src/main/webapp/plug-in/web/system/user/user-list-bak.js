
			$(function(){
			   $("#user_table").dataTable({
				    "sAjaxSource": path+"/userController/getPageData.do",
				    "sServerMethod":"post",
				    "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
		            "bServerSide" : true, //是否启动服务器端数据导入  
		            "bStateSave" : true, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  	           
		            "aLengthMenu" : [10, 20, 40,60], //更改显示记录数选项  
		            "bLengthChange" : true,// 每行显示记录数 
		            "iDisplayLength" : 10, //默认显示的记录数  
		            "bAutoWidth" : false, //是否自适应宽度  
		           // "sScrollY": "500px",
		            "bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变  
		            "bPaginate" : true, //是否显示（应用）分页器  
		            "bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
		            "sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页  
		            "bSort" : true, //是否启动各个字段的排序功能  
		            "aaSorting" : [[1, "asc"]], //默认的排序方式，第2列，升序排列  
	            	"bFilter" : false, //是否启动过滤、搜索功能
					"aoColumnDefs": [
					{
					 sDefaultContent: '',
					 aTargets: [ '_all' ]
					  }
					  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
					  //{"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
					],
				    "aoColumns": [
				    			   {"mDataProp":"id" ,"width":"32px","bSortable": false,"sTitle": "<input type=\"checkbox\" name=\"\" value=\"\" >",
				    			   	  "mRender": function (data, type, full) {
				    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
				    			   	  
				    			   	  }
				    			   },	
	                               { "sTitle": "用户名", "width": "150px","mDataProp":"userName" }, 
	                               { "sTitle": "中文名", "width": "150px","mDataProp":"chineseName" },  
	                               { "sTitle": "邮箱", "width": "200px","mDataProp":"email"},
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
	                                   "width": "100px",  
	                                   "mDataProp":"id" ,                              
	                                   "bSortable": false,
	                                   "width":"150px",
	                                   "mRender": function (data, type, full) {
	                              
	                                	    var html = "<div class=\"t-btn-container\">";                            		
		                              		if(typeof(user_edit)!= "undefined"){
		                              			 if(user_edit.length>0){
		                              			 	html+=user_edit.format(data);
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
	              "oLanguage": { //国际化配置  
	                "sProcessing" : "正在获取数据，请稍后...",    
	                "sLengthMenu" : "显示 _MENU_ 条",    
	                "sZeroRecords" : "没有您要搜索的内容",    
	                "sInfo" : "显示 _START_ 到  _END_ 条 ，共 _TOTAL_ 条",    
	                "sInfoEmpty" : "记录数为0",    
	                "sInfoFiltered" : "(当前显示记录 _MAX_ 条)",    
	                "sInfoPostFix" : "",    
	                "sSearch" : "搜索",    
	                "sUrl" : "",    
	                "oPaginate": {    
	                    "sFirst" : "第一页",    
	                    "sPrevious" : "上一页",    
	                    "sNext" : "下一页",    
	                    "sLast" : "最后一页"    
	                }  
	            },
	              "fnServerParams" : function (aoData) {
						aoData.push(
						{ "name": "userName", "value": $("#user_name").val() },
						{ "name": "chineseName", "value": $("#cn_name").val() },
						{ "name": "dataType", "value": $("#dataType").val() }	
						);
						
						
						
						}  
				
				});
				
			
				
		
			
				
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
		
	  
		