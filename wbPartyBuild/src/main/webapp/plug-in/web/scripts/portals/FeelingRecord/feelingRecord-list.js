 var mtable;
		 $(function(){	
			
			 mtable = $("#feelingRecord_table").dataTable({
				 	"sAjaxSource": path+"/feelingRecordController/pageList.do",
				    "sServerMethod":"post",
				    "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
		            "bServerSide" : true, //是否启动服务器端数据导入  
		            "bStateSave" : true, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  	           
		            "aLengthMenu" : [10, 20, 40,60], //更改显示记录数选项  
		            "bLengthChange" : true,// 每行显示记录数 
		            "iDisplayLength" : 10, //默认显示的记录数  
		            "bAutoWidth" : true, //是否自适应宽度  
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
				    			   { "mDataProp":"id" ,"sWidth": "2%","bSortable": false,
				    			   	  "mRender": function (data, type, full) {
				    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
				    			   	  }
				    			   },
				    			   { "sTitle": "编号", "sWidth": "32px","mDataProp":"sort" }, 
				    			   { "sTitle": "标题","mDataProp":"title" }, 
	                               { "sTitle": "作者", "sWidth": "100px", "sClass": "center","mDataProp":"ownerId",
				    				   "mRender": function (data, type, full) {
				                         
		                              		
	                                        return full.ownerName;
	                                   } 
	                               }, 
	                               { "sTitle": "创建时间","sWidth": "150px","mDataProp":"createTime" }, 
	                               {
	                                   "sTitle": "操作",
	                                   "sClass": "center", 
	                                   "sWidth": "120px",
	                                   "mDataProp":"id" ,                              
	                                   "bSortable": false,
	                                   "mRender": function (data, type, full) {
	                              
	                              			var html = "<div class=\"t-btn-container\">";  
	                              			
	                              			if(typeof(update_feelingRecord)!= "undefined"){
		                              			 if(update_feelingRecord.length>0){
		                              			 	html+=update_feelingRecord.format(full.id);
		                              			 }                            			
		                              		}		     
	                              			if(typeof(detailed_feelingRecord)!= "undefined"){
		                              			 if(detailed_feelingRecord.length>0){
		                              			 	html+=detailed_feelingRecord.format(full.id);
		                              			 }	                              			
		                              		} 
		                              		if(typeof(delete_feelingRecord)!= "undefined"){
		                              			 if(delete_feelingRecord.length>0){
		                              			 	html+=delete_feelingRecord.format(full.id);
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
						{ "name": "title", "value": $("#title").val() }
					
						
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
		
		 /*添加*/
		function feelingRecord_add(title,w,h){
			var url = path+"/feelingRecordController.do?add";
			layer_full(title,url,w,h,false);
		}
		
		/*修改*/
		function feelingRecord_edit(id){
			var url = path+"/feelingRecordController.do?edit&id="+id;
			layer_full("修改心得体会",url,'400','300',false);
		}
		
		function feelingRecord_detailed(id){
			/*var url = path+"/feelingRecordController.do?detail&id="+id;
			layer_show("修改心得体会",url,'600','400',false);*/

			$.ajax({
	        	type:'post',
	        	url:path+"/feelingRecordController/detailFeelingRecord.do",
	        	data:{
	        		id:id
	        	},
	        	dataType: "json",
	        	success:function(data) {
	        		layer.open({
	        			  type: 1,
	        			  skin: 'layui-layer-rim', //加上边框
	        			  area: ['800px', '500px'], //宽高
	        			  content: "<div style='margin-left: 20px;margin-top: 20px;margin-right: 20px;'><strong style=text-align: center;'>标题：</strong><p>"+data.title+"</p><strong>内容：</strong><br/>"+data.content+"</div>"
	        			});
	        	}
	       });	
		}
		
		
		
		/*删除*/
		function feelingRecord_del(obj,id){
			layer.confirm('确认要删除吗？',function(index){
			   sumbitDel(id);
			});
		}
		/*批量删除*/
		function datadel(){
		   var l = new Array();
		         
	       $("#feelingRecord_table .checkbox:checked").each(function() {		
			  l.push($(this).val());		
		   });
		   	 
		   if(l.length > 0){
			   	layer.confirm("确认要删除 "+l.length+" 条记录吗？",function(index){
			   		sumbitDel(l);
				});
		   }else{
		     layer.msg('无选中的项!',{icon: 0,time:1000});
		   };
		}
		/*删除共用的方法*/
		function sumbitDel(id){
			$.ajax({
				    url: path+"/feelingRecordController/delFeelingRecord.do",
				    traditional:true,
				    dataType:'json',  
				    data:{
				    	"ids":id
				    },
				    type:"post",
				    cache : false,  
				    async : false,  
				    success:function(data){	
				    		if(data.status == "y" || data.status == "Y"){
				    			layer.msg(data.info,{icon: 1,time:1000});
				          		goSearch();	
				    		}else{
				    			layer.alert(data.info,{icon: 2});
				    			goSearch();	
				    		}	    					         				               		           
				       },  
				     error : function(error) {  
				            alert(error); 		            
				       }
			  });
		}
		
		
		
	   function goSearch(){		
			mtable.fnDraw();					
	   };
		