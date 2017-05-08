 var mtable;
		 $(function(){			
			 mtable = $("#commun_table").dataTable({
				    "sAjaxSource": path+"/communController/pageList.do",
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
		            "aaSorting" : [[0, "asc"]], //默认的排序方式，第2列，升序排列  
	            	"bFilter" : false, //是否启动过滤、搜索功能
					"aoColumnDefs": [
					 {
					    sDefaultContent: '',
					 	aTargets: [ '_all' ]
					  }				
					  //{"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
					  
					],					
				    "aoColumns": [
				    			   { "mDataProp":"id" ,"bSortable": false,
				    			   	  "mRender": function (data, type, full) {
				    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
				    			   	  
				    			   	  }
				    			   },		                               
	                               { 
	                            	   "sTitle": "状态", 
	                            	   "sClass": "center",
	                            	   "mDataProp":"status",
	                            	   "bSortable": false,
		                               "mRender": function (data, type, full) {
	                             		  if(data == 1){
	                             		  	return " <span class=\"label label-success radius\">活跃</span>";
	                             		  }else{
	                             		  	return " <span class=\"label label-defaunt radius\">归档</span>";
	                             		  }	      
	                                  }                               
	                               }, 
	                               { "sTitle": "议题名称", "sClass": "center","mDataProp":"title","bSortable": false}, 
	                               { "sTitle": "发起人", "sClass": "center","mDataProp":"sponsor","bSortable": false}, 
	                               { "sTitle": "开始日期", "sClass": "center","mDataProp":"startDate","bSortable": false},  	                     
	                               { "sTitle": "结束日期", "sClass": "center","mDataProp":"endDate","bSortable": false},	                               
	                               {
	                                   "sTitle": "操作",
	                                   "sClass": "center",  
	                                   "mDataProp":"adsId" ,                              
	                                   "bSortable": false,
	                                   "mRender": function (data, type, full) {
	                                	   var html = "<div class=\"t-btn-container\">";  
	                              			
	                              			if(typeof(manage_comment)!= "undefined"){
		                              			 if(manage_comment.length>0){
		                              			 	html+=manage_comment.format(full.id,full.title);
		                              			 }                            			
		                              		}
	                              			
	                              			//归档
	                              			if( full.status==1){
		                              			if(typeof(filed_commun)!= "undefined"){
			                              			 if(filed_commun.length>0){
			                              			 	html+=filed_commun.format(full.id);
			                              			 }                            			
			                              		}
	                              			}
	                              			
	                              			
		                              		if(typeof(edit_commun)!= "undefined"){
		                              			 if(edit_commun.length>0){
		                              			 	html+=edit_commun.format(full.id);
		                              			 }                            			
		                              		}
		                              				                              			                              				                              		
		                              		if(typeof(del_commun)!= "undefined"){
		                              			 if(del_commun.length>0){
		                              			 	html+=del_commun.format(full.id);
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
							{ "name": "title", "value":$("#title").val()}		
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
			});
		
		 function comment_manage(id,comunTitle){
				var url = path+"/commentController.do?viewPage&comId="+id;
				var title = "互动议题："+comunTitle
				var w= "";
				var h= "";
				layer_full(title,url,w,h,true);
			}
		 
		 
		 function commun_filed(obj,id){
			 layer.confirm('是否确定归档，归档之后将无法进行评论',function(index){			   
				 $.ajax({
					    url: path+"/communController/editCommun.do",
					    traditional:true,
					    data:{
					    	id:id,
					    	status:2,
					    	type:1
					    },
					    dataType:'json',  
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
		 
	
		function commun_add(title,w,h){
			var url = path+"/communController.do?add";
			layer_full(title,url,w,h,true);
			
		}
		
	
		function commun_edit(id,w,h){
		     var url = path+"/communController.do?edit&comId="+id;	     
		     layer_full("编辑交流议题",url,w,h,true);
		}
		
		
		
		
		function commun_del(obj,id){
			layer.confirm('确认要删除吗？',function(index){
			   sumbitDel(id);
			});
		}
		
		function datadel(){
		   var l = new Array();
		         
	       $("#commun_table .checkbox:checked").each(function() {		
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
				    url: path+"/communController/delCommun.do",
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