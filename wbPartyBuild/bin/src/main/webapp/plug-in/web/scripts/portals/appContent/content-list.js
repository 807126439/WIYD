 var mtable;
		 $(function(){
			 var isShowThumb = false;
			 var tv = parseInt($("#typeId").val());
			 if(tv == 3 || tv ==5){//是否显示缩略图列				
				 isShowThumb = true;				
			 }
			 mtable = $("#content_table").dataTable({
				    "sAjaxSource": path+"/contentController/appPageList.do",
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
					  },
					  {"visible": isShowThumb, "aTargets": [ 3 ]} //控制列的隐藏显示
					  //{"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
					  
					],					
				    "aoColumns": [
				    			   { "mDataProp":"ctId" ,"bSortable": false,"sTitle": "<input type=\"checkbox\" name=\"\" value=\"\" >",
				    			   	  "mRender": function (data, type, full) {
				    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
				    			   	  
				    			   	  }
				    			   },	
	                               { "sTitle": "序号", "sClass": "center","mDataProp":"sort" }, 
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
	                               { "sTitle": "是否置顶", "sClass": "center","mDataProp":"appStatus",
	                            	   "mRender": function (data, type, full) {
		                             		  if(data !=null && data==1){
		                             		  	return " <span class='label label-success radius' >是</span>";
		                             		  }else{
		                             		  	return "<span class='label label-fail radius' >否</span>";
		                             		  }	
		          
		                                  }
	                               },
	                               { "sTitle": "来源", "sClass": "center","mDataProp":"source"},  
	                               { "sTitle": "作者", "sClass": "center","mDataProp":"author"},  
	                               { "sTitle": "发布日期", "sClass": "center","mDataProp":"createTime" },                                                    
	                              
	                                              
	                               {
	                                   "sTitle": "操作",
	                                   "sClass": "center",  
	                                   "mDataProp":"ctId" ,                              
	                                   "bSortable": false,
	                                   "mRender": function (data, type, full) {
	                              
	                                	   var html = "<div class=\"t-btn-container\">";                              		
		                              		if(typeof(edit_content)!= "undefined"){
		                              			 if(edit_content.length>0){
		                              			 	html+=edit_content.format(data);
		                              			 }                            			
		                              		}
		                              		
		                              		if(tv == 6){
		                              			if(typeof(edit_inner)!= "undefined"){
			                              			 if(edit_inner.length>0){
			                              			 	html+=edit_inner.format(data,full.title);
			                              			 }                            			
			                              		}		                              			
		                              		}		                              		
		                              		
		                              		if(typeof(del_content)!= "undefined"){
		                              			 if(del_content.length>0){
		                              			 	html+=del_content.format(data);
		                              			 }	                              			
		                              		}
		                              		
		                              		if(tv == 6){
		                              			if(typeof(connect_content)!= "undefined"){
		                              				if(connect_content.length>0){
		                              					html+=connect_content.format(data);
		                              				}	                              			
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
						{ "name": "columId", "value": $("#columId").val() },
						{ "name": "title", "value": $("#ct-title").val() },
						{ "name": "author", "value": $("#ct-auhtor").val() },
						{ "name": "source", "value": $("#ct-source").val() },
						{ "name": "appContent", "value": true }
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
		
		
		/*内容-添加*/
		function content_add(title){
			var url = path+"/contentController.do?add&columId="+$("#columId").val() ;
		    var index = window.top.layer.open({
		 		type: 2,
		 		fix: false, //不固定
		 		maxmin: false,
		 		shade:0.4,
		 		title: "发布内容",
		 		content: url,
		 		end: function(index){ 
		 			goSearch();
					return true; 
				}
		 	});
		   window.top.layer.full(index);
		}
		
		/*内容-修改*/
		function content_edit(id){
		     var url = path+"/contentController.do?detail&cid="+id;
		     var index = window.top.layer.open({
			 		type: 2,
			 		fix: false, //不固定
			 		maxmin: false,
			 		shade:0.4,
			 		title: "修改内容",
			 		content: url,
			 		end: function(index){ 
			 			goSearch();
						return true; 
					}
			 	});
			   window.top.layer.full(index);
		}
		
		/*内页管理*/
		function innnert_edit(id,title){
			var url = path+"/contentController.do?viewPage&parId="+id ;
		    var index = window.top.layer.open({
		 		type: 2,
		 		fix: false, //不固定
		 		maxmin: false,
		 		shade:0.4,
		 		title: title,
		 		content: url
		 	});
		   window.top.layer.full(index);
		}
		
		/*内容-活动关联*/
		function content_connect(id,title){
			var url = path+"/contentController.do?Connect&parId="+id;
			layer_show("关联活动",url,700,200,true);
		}
		
		
		
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
				    url: path+"/contentController/delContent.do",
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
		
		//批量设置置顶
		function content_toTop(){
			   var l = new Array();
			         
		       $("#content_table .checkbox:checked").each(function() {		
				  l.push($(this).val());		
			   });
			   	 
			   if(l.length > 0){
				   	layer.confirm("确认要置顶 "+l.length+" 条记录吗？",function(index){
				   		
				   		sumbitToTop(l);
					});
			   }else{
			     layer.msg('无选中的项!',{icon: 0,time:1000});
			   }
			}
			
			function sumbitToTop(ids){
				$.ajax({
					    url: path+"/contentController/sumbitToTop.do",
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
	   /**
	    * 保存手机端最大文章数量
	    */
	   function saveAppContentNum(){
		   var contentNum=$("#contentNum").val();
		   if(isNaN(contentNum)){
			   layer.alert("请输入数字");
		   }else{
			   $.ajax({
				    url: path+"/contentController/saveAppContentNum.do",
				    traditional:true,
				    dataType:'json',  
				    data:{
				    	"contentNum":contentNum
				    },
				    type:"post",
				    cache : false,  
				    async : false,  
				    success:function(data){	
				    		if(data.status == "y" || data.status == "Y"){
				    			layer.msg(data.info,{icon: 1,time:1000});
				    		}else{
				    			layer.msg(data.info,{icon: 2,time:2500});
				    		}	    					         				           
				                		           
				       },  
				     error : function(error) {  
				            alert(error); 		            
				       } 
			  });
		   }
		   
	   }
	   /**
	    * 取消置顶
	    */
	   function cancle_toTop(){
		   var l = new Array();
	         
	       $("#content_table .checkbox:checked").each(function() {		
			  l.push($(this).val());		
		   });
		   	 
		   if(l.length > 0){
			   	layer.confirm("确认要取消置顶 "+l.length+" 条记录吗？",function(index){
			   		
			   		sumbitCancle(l);
				});
		   }else{
		     layer.msg('无选中的项!',{icon: 0,time:1000});
		   }
	   }
	   function sumbitCancle(ids){
			$.ajax({
				    url: path+"/contentController/cancleToTop.do",
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
