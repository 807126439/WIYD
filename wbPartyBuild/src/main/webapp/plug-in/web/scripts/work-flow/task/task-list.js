var mtable;
		$(function(){
		
			mtable = $("#task_table").dataTable({
			    "sAjaxSource": path+"/task/getMyTaskData.do",
			    "sServerMethod":"post",
			    "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
	            "bServerSide" : true, //是否启动服务器端数据导入  
	            "bStateSave" : true, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  	           
	            "aLengthMenu" : [10, 20, 40,60], //更改显示记录数选项  
	            "bLengthChange" : true,// 每行显示记录数 
	            "iDisplayLength" : 10, //默认显示的记录数  
	            "bAutoWidth" : false, //是否自适应宽度  			
	            "bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变  
	            "bPaginate" : true, //是否显示（应用）分页器  
	            "bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
	            "sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页  
	            "bSort" : false, //是否启动各个字段的排序功能   
            	"bFilter" : false, //是否启动过滤、搜索功能
				"aoColumnDefs": [
				{
				 sDefaultContent: '',
				 aTargets: [ '_all' ]
				  }
				],
			    "aoColumns": [			    			  
                               { "sTitle": "流程名称","mDataProp":"processName"},  
                               { "sTitle": "流程开始时间", "mDataProp":"beginTime" },
                               { "sTitle": "任务名称","mDataProp":"name" },
                               { "sTitle": "任务说明","mDataProp":"taskExplain","width":"320px" },
                               { "sTitle": "任务开始时间", "mDataProp":"createTime" },
                               { "sTitle": "申请人", "mDataProp":"owner" },  
                               { "sTitle": "任务办理人", "mDataProp":"handler" },                               
                               { "sTitle": "状态","mDataProp":"status",
                                  "mRender": function (data, type, full) {
                             		  if(data == 1){
                             		  	return " <span class=\"label label-success radius\">已处理</span>";
                             		  }else{
                             		  	return " <span class=\"label label-defaunt radius\">未处理</span>";
                             		  }	
          
                                  }
                                }, 
                               { "sTitle": "当前环节", "mDataProp":"currNode" },
                               {
                                   "sTitle": "操作",
                                   "sClass": "center",  
                                   "mDataProp":"id" ,                              
                                   "bSortable": false,
                                   "width":"180px",
                                   "mRender": function (data, type, full) {
                              
                                	   var html = "<div class=\"t-btn-container\">";                             		
	                              		if(typeof(handle_task)!= "undefined"){
	                              			 if(handle_task.length>0){
	                              			 	html+=handle_task.format(data);
	                              			 }                            			
	                              		}
	                              		if(typeof(view_process)!= "undefined"){
	                              			 if(view_process.length>0){
	                              			 	html+=view_process.format(full.procInstId);
	                              			 }                            			
	                              		}
	                              		if(typeof(roll_task)!= "undefined"){
	                              			 if(roll_task.length>0){
	                              			 	html+=roll_task.format(data);
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
					{ "name": "taskName", "value": $("#taskName").val() }
					
					);
					
					
					
					}
              
			
			});
			
		

		
			
		});
	


	function task_handle(title,id,w,h){
	     var url = path+"/task/getDetail.do?tid="+id;
		 layer_show(title,url,w,h);
	}
	
	function process_view(instId){
		var url = path+"/procNode/getCurrProcess.do?procInstId="+instId;
		
		layer_full("流程图",url,"","",false);
		
	}

	function task_roll(id){
		if(id){
			layer.confirm("确认要回到上一步吗？",function(index){
				$.ajax({
				    url: path+"/task/rollBackTask.do",
				    dataType:'json',  
				    data:{
				    "taskId":id
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
			});
			
		}
	   
	}
	
	
	function goSearch(){
	
		mtable.fnDraw();
		
	
	}