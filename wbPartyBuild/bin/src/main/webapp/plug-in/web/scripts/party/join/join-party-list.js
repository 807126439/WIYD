var mtable;
		$(function(){
		
			mtable = $("#info_table").dataTable({
			    "sAjaxSource": path+"/joinPartyInfoController/getPageData.do",
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
	            "bSort" : true, //是否启动各个字段的排序功能  
	            "aaSorting" : [[2, "asc"]], //默认的排序方式，第2列，升序排列  
            	"bFilter" : false, //是否启动过滤、搜索功能
				"aoColumnDefs": [
				{
				 sDefaultContent: '',
				 aTargets: [ '_all' ]
				  }
				],
			    "aoColumns": [
			    			   { "mDataProp":"id" ,"bSortable": false,
			    			   	  "mRender": function (data, type, full) {
			    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
			    			   	  
			    			   	  }
			    			   },
                               { "sTitle": "申请人", "sClass": "center","mDataProp":"applyUserName" },                     
                               { "sTitle": "部门", "sClass": "center","mDataProp":"department" },
                               { "sTitle": "备注", "sClass": "center","mDataProp":"memo" },
                               { "sTitle": "创建时间", "sClass": "center","mDataProp":"createTime" },
                               { "sTitle": "提交时间", "sClass": "center","mDataProp":"startTime" },
                               { "sTitle": "状态", "sClass": "td-status","mDataProp":"status",
                                  "mRender": function (data, type, full) {
                             		  if(data == 0){
                             		  	return " <span class=\"label label-defaunt radius\">未开始</span>";
                             		  }else if(data == 1){
                             		  	return " <span class=\"label label-primary radius\">运行中</span>";
                             		  }else if(data == 2){
                             		  	return " <span class=\"label label-success radius\">结束</span>";
                             		  }	else{
                             			  
                             			  return "";
                             		  }	
          
                                  }
                                },                                                        
                               {
                                   "sTitle": "操作",
                                   "sClass": "center",  
                                   "mDataProp":"id" ,                              
                                   "bSortable": false,
                                   "mRender": function (data, type, full) {
                              
                                	   var html = "<div class=\"t-btn-container\">";                              		
	                              		if(typeof(edit_info)!= "undefined"){
	                              			 if(edit_info.length>0){
	                              			 	html+=edit_info.format(data);
	                              			 }                            			
	                              		}
	                              		
	                              		if(!full.processInstanceId){
	                              			if(typeof(start_join)!= "undefined"){
		                              			 if(start_join.length>0){
		                              			 	html+=start_join.format(data);
		                              			 }                            			
		                              		}
	                              		}
	                              		
	                              		if(full.processInstanceId){
	                              			if(typeof(view_atta)!= "undefined"){
		                              			 if(view_atta.length>0){
		                              			 	html+=view_atta.format(full.applyUserName,full.processInstanceId);
		                              			 }                            			
		                              		}
	                              		}
	                              		
	                              		
	                              		if(full.processInstanceId){
	                              			if(typeof(view_process)!= "undefined"){
		                              			 if(view_process.length>0){
		                              			 	html+=view_process.format(full.processInstanceId);
		                              			 }                            			
		                              		}
	                              		}
	                              		
	                              		if(typeof(del_info)!= "undefined"){
	                              			 if(del_info.length>0){
	                              			 	html+=del_info.format(data);
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
					{ "name": "applyUserName", "value": $("#applyUser").val() }
					
					);
					
					
					
					}
              
			
			});
			
		

		
			
		});
	
	
	/*入党申请-添加*/
	function info_add(title,w,h){
		var url = path+"/joinPartyInfoController/skipAddJoinPartyInfo.do";
		layer_show(title,url,w,h);
	}
	
	
	
	function atta_view(user,procInstId){
		var url = path+"/attachment/viewAttachment.do?procInstId="+procInstId;
		layer_show(user+"-流程附件",url,'','');
	}
	
	/*提交申请*/
	function join_start(id){
		
		layer.confirm('确认要提交申请吗？',function(index){
			$.ajax({
			    url: path+"/joinPartyInfoController/startJoinPartyApply.do",
			    traditional:true,
			    dataType:'json',  
			    data:{
			    	"jid":id
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
	
	/*入党申请-修改*/
	function info_edit(title,id,w,h){
	     var url = path+"/joinPartyInfoController/getDetail.do?jid="+id;
		layer_show(title,url,w,h);
	}
	
	
	/*入党申请-删除*/
	function info_del(obj,id){
		layer.confirm('确认要删除吗？',function(index){
		   sumbitDel(id);
		});
	}
	

	
	function sumbitDel(ids){
		$.ajax({
			    url: path+"/joinPartyInfoController/delJoinPartyInfo.do",
			    traditional:true,
			    dataType:'json',  
			    data:{
			    	"jids":ids
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
	
	function process_view(instId){
		var url = path+"/procNode/getCurrProcess.do?procInstId="+instId;
		
		layer_full("流程图",url,"","",false);
		
	}
	
	
	function goSearch(){
	
		mtable.fnDraw();
		
	
	}