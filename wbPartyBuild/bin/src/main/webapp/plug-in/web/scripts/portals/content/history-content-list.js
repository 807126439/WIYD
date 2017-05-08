 var mtable;
		 $(function(){
			
			 mtable = $("#content_table").dataTable({
				    "sAjaxSource": path+"/contentController/historyPageList.do",
				    "sServerMethod":"post",
				    "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
		            "bServerSide" : true, //是否启动服务器端数据导入  
		            "bStateSave" : false, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  	           
		            "aLengthMenu" : [10, 20, 40,60], //更改显示记录数选项  
		            "bLengthChange" : true,// 每行显示记录数 
		            "iDisplayLength" : 10, //默认显示的记录数  
		            "bAutoWidth" : false, //是否自适应宽度  
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
				
					  
					],					
				    "aoColumns": [
				    			   { "mDataProp":"id" ,"bSortable": false,"sTitle": "<input type=\"checkbox\" name=\"\" value=\"\" >",
				    			   	  "mRender": function (data, type, full) {
				    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
				    			   	  
				    			   	  }
				    			   },	
	                               { "sTitle": "序号", "sClass": "center","mDataProp":"sortNum" }, 
	                               { "sTitle": "标题", "sClass": "center","mDataProp":"title",
                            	     "mRender": function (data, type, full) {
	                             		 
	                             		 return "<a href=\""+path+"/article/"+full.ctId+".htm\" target=\"_blank\" >"+data+"</a>";
	                             		  
	          
	                                  }   
	                            	   
	                               },  
	                               { "sTitle": "缩略图", "sClass": "center","mDataProp":"pattern",
	                            	   "mRender": function (data, type, full) {
		                             		  if(data !=null && data!=""){
		                             		  	return " <img src=\""+data+"\" width=\"48\" heigth\"48\" />";
		                             		  }else{
		                             		  	return "";
		                             		  }	
		          
		                                  }                                  
	                               
	                               },
	                               { "sTitle": "历史栏目名称", "sClass": "center","mDataProp":"historyColumn"},  
	                               { "sTitle": "来源", "sClass": "center","mDataProp":"source"},  
	                               { "sTitle": "作者", "sClass": "center","mDataProp":"author"},  
	                               { "sTitle": "创建时间", "sClass": "center","mDataProp":"createTime" },                                                    
	                              	                                              
	                               {
	                                   "sTitle": "操作",
	                                   "sClass": "center",  
	                                   "mDataProp":"id" ,                              
	                                   "bSortable": false,
	                                   "mRender": function (data, type, full) {
	                              
	                                	   var html = "<div class=\"t-btn-container\">";                            		
		                              		if(typeof(recover_content)!= "undefined"){
		                              			 if(recover_content.length>0){
		                              			 	html+=recover_content.format(data);
		                              			 }                            			
		                              		}
		                              				                              			                              		
		                              		
		                              		if(typeof(del_content)!= "undefined"){
		                              			 if(del_content.length>0){
		                              			 	html+=del_content.format(data);
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
						{ "name": "title", "value": $("#ct-title").val() },
						{ "name": "author", "value": $("#ct-source").val() },
						{ "name": "source", "value": $("#ct-auhtor").val() }
						);
						
						
						
						}  
				
				});
				
			
				
				
				
				$('.table-sort tbody').on( 'click', 'tr', function () {
					if ( $(this).hasClass('selected') ) {
						$(this).removeClass('selected');
					}
					else {
						table.$('tr.selected').removeClass('selected');
						$(this).addClass('selected');
					}
				});
			
				/*全选*/
				$("table thead th input:checkbox").on("click" , function(){
					$(this).closest("table").find("tr > td:first-child input:checkbox").prop("checked",$("table thead th input:checkbox").prop("checked"));
			    });
				
			});
		
	
		
		
		
	
		
		
		
		function content_del(obj,id){
			layer.confirm('确认要删除吗？',function(index){
			   sumbitDel(id);
			});
		}
		
		function datadel(){
		   var l = new Array();
		         
	       $("#content_table .checkbox:checked").each(function() {		
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
				    url: path+"/contentController/shiftDelContent.do",
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
		
		
		
	   function goSearch(){		
			mtable.fnDraw();					
	   }
	   
	   
	   var isLoadTree = false;
       
       var setting = {
			
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		
	   var storeIds = null;
		
       function content_recover(ids){
		   storeIds = ids;
		
		   var curNum = 1;
		   if($.isArray(ids)){
		      curNum = storeIds.length;
		   }
			
		   if(!isLoadTree){
		  	 initColumTree();
		  	 isLoadTree = true;		  			  
		   }
			
		   layer.open({
			  type: 1,
			  title: "选择栏目（已选择"+curNum+"篇文章）",
			  area: ['350px', '450px'],
			  content: $("#hide-tree")
			});	

		 	
		}
	
    	/*初始化栏目树*/
    	function initColumTree(){
    	         	
	    	 $.ajax({
				    url: path+"/columnMuController/getColumTreeNode.do",
				    dataType:'json',  
				    data:{
				    },
				    type:"post",
				    cache : false,  
				    async : false,  
				    success:function(data){	
				    	if(data!=null && data!=undefined){
				    		var zNodes = new Array();
				    		
				    		for(var i=0;i<data.length;i++){
				    		
				    			zNodes.push({id:data[i].id, pId:data[i].pid, name:data[i].name, isParent:data[i].isParent,open:data[i].open});
				    		
				    		}
				    	
				    		$.fn.zTree.init($("#tree"), setting, zNodes);
				    	
				    	}		
				    			    					         				           
				                		           
				    },  
				    error : function(error) {  
				          alert(error); 		            
				    } 
			  });
	
    	}
    	
    		    	
    	
	function content_one_recover(id){	   			
		   content_recover(id);			
	}
	
	
	function dataRecover(){
	   var l = new Array();
	         
       $("#content_table .checkbox:checked").each(function() {		
		  l.push($(this).val());		
	   });
	   	 
	   if(l.length > 0){
		  
		   	content_recover(l);
			
	   }else{
	     layer.msg('无选中的项!',{icon: 0,time:1000});
	   }
	}
	
	
	
	function sumbitRecover(){

		var colId = getCurrentVal();
		if(colId == -1){
			layer.msg('请选择栏目，然后确定恢复!',{icon: 0,time:1500});
			return;
		}
	
		$.ajax({
			    url: path+"/contentController/recoverContent.do",
			    traditional:true,
			    dataType:'json',  
			    data:{
			    	"ids":storeIds,
			    	"columId":colId
			    },
			    type:"post",
			    cache : false,  
			    async : false,  
			    success:function(data){
			    	
			    		if(data.status == "y" || data.status == "Y"){
			    			layer.msg(data.info,{icon: 1,time:1000});
			          		goSearch();	
		          			layer.closeAll('page');
			    		}else{
			    			layer.msg(data.info,{icon: 2,time:2500});
			    		}	    					         				           
			                		           
			       },  
			     error : function(error) {  
			            alert(error); 		            
			       } 
		  });
		
	
	}
    
     /*获取栏目树当前选中的节点*/
     function getCurrentVal(){
	  	var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getSelectedNodes();
		if(nodes.length>0){
			if(nodes.length == 1){
			  return nodes[0].id;
			}else{
			 alert("eror");	
			 return -1;
			}
		
		}else{
		   return -1;
		}
	
	  }	   
	   
	   
	   
		