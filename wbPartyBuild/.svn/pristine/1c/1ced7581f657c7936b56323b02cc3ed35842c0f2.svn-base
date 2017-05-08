var mtable;
		$(function(){
		
			mtable = $("#def_table").dataTable({
			    "sAjaxSource": path+"/procDefinition/getPageData.do",
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
                               { "sTitle": "流程标识符", "sClass": "center","mDataProp":"processCode" },                     
                               { "sTitle": "流程名", "sClass": "center","mDataProp":"processName" },
                               { "sTitle": "描述", "sClass": "center","mDataProp":"description" },
                               { "sTitle": "版本号", "sClass": "center","mDataProp":"version" },
                               { "sTitle": "是否有效", "sClass": "td-status","mDataProp":"status",
                                  "mRender": function (data, type, full) {
                             		  if(data == 1){
                             		  	return " <span class=\"label label-success radius\">是</span>";
                             		  }else{
                             		  	return " <span class=\"label label-defaunt radius\">否</span>";
                             		  }	
          
                                  }
                                },                                                        
                               {
                                   "sTitle": "操作",
                                   "width": "240px",  
                                   "mDataProp":"id" ,                              
                                   "bSortable": false,
                                   "mRender": function (data, type, full) {
                              
                                		var html = "<div class=\"t-btn-container\">";                             		
	                              		if(typeof(edit_procDef)!= "undefined"){
	                              			 if(edit_procDef.length>0){
	                              			 	html+=edit_procDef.format(data);
	                              			 }                            			
	                              		}
	                              		if(typeof(design_proc)!= "undefined"){
	                              			 if(design_proc.length>0){
	                              			 	html+=design_proc.format(data);
	                              			 }                            			
	                              		}
	                              		if(typeof(del_procDef)!= "undefined"){
	                              			 if(del_procDef.length>0){
	                              			 	html+=del_procDef.format(data);
	                              			 }	                              			
	                              		}
	                              		if(typeof(manage_proc)!= "undefined"){
	                              			 if(manage_proc.length>0){
	                              			 	html+=manage_proc.format(data);
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
					{ "name": "processCode", "value": $("#ser_code").val() },
					{ "name": "processName", "value": $("#ser_name").val() }	
					
					);
					
					
					
					}
              
			
			});
			
		

		
			
		});
	
	
	/*流程定义-添加*/
	function procDef_add(title,url,w,h){
		layer_show(title,url,w,h);
	}
	
	
	function proc_design(title,id){
		var url = path + "/procNode/designProcess.do?definId="+id;
		layer_full(title,url,"","",false);
	}
	
	/*流程定义-修改*/
	function procDef_edit(title,id,w,h){
	     var url = path+"/procDefinition/getDetail.do?id="+id;
		layer_show(title,url,w,h);
	}
	/*流程定义-管理*/
	function proc_manage(title,id,w,h){
	     var url = path+"/procNode/viewPage.do?id="+id;
		layer_full(title,url,w,h);
	}
	
	/*流程定义-删除*/
	function procDef_del(obj,id){
		layer.confirm('确认要删除吗？',function(index){
		   sumbitDel(id);
		});
	}
	
	

	
	function sumbitDel(ids){
		$.ajax({
			    url: path+"/procDefinition/delProcessDefinition.do",
			    traditional:true,
			    dataType:'json',  
			    data:{
			    	"id":ids
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
	
	
	
	
	function goSearch(){
	
		mtable.fnDraw();
		
	
	}