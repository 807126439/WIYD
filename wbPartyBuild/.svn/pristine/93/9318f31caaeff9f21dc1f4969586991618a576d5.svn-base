
	$(function(){
			 mtable = $("#OpinionFeedback_table").dataTable({
				    "sAjaxSource": path+"/OpinionFeedbackConteroller/pageList.do",
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
					  //{"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
					  
					],
				    "aoColumns": [{ "mDataProp":"id" ,"bSortable": false,"sWidth": "3%",
				    			   	  "mRender": function (data, type, full) {
				    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
				    			   	  }},	
				    			  
	                               { "sTitle": "反馈时间","sWidth": "7%", "sClass": "center","mDataProp":"feedbackTime" },
	                               { "sTitle": "用户名","sWidth": "6%", "sClass": "center","mDataProp":"feedbackUser"},
	                               { "sTitle": "姓名","sWidth": "6%", "sClass": "center","mDataProp":"chineseName"},
	                               { "sTitle": "科室", "sWidth": "6%","sClass": "center","mDataProp":"department"},
	                               { "sTitle": "岗位", "sWidth": "6%","sClass": "center","mDataProp":"post"},
	                               { "sTitle": "申报主题", "sWidth": "8%","sClass": "center","mDataProp":"theme"},
	                               { "sTitle": "内容","sWidth": "26%", "sClass": "center","mDataProp":"content"},
	                               { "sTitle": "效益预测","sWidth": "20%", "sClass": "center","mDataProp":"forecast"},
	                               {"sTitle": "操作","sWidth": "7%","sClass": "center", "mDataProp":"id","bSortable": false,
	                            	   
	                            	"mRender": function (data, type, full) {
	                          
	                              			var html = "<div class=\"t-btn-container\">";                             		
	                              	 		if(typeof(detail_opinion)!= "undefined"){
		                              			 if(detail_opinion.length>0){
		                              			 	html+=detail_opinion.format(full.id);
		                              			 }                            			
		                              		}
	                              			
		                              		if(typeof(delete_opinion)!= "undefined"){
		                              			 if(delete_opinion.length>0){
		                              			 	html+=delete_opinion.format(full.id);
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
							{ "name": "beginTime", "value": $("#beginTimeDate").val()},
							{ "name": "endTime", "value": $("#endTimeDate").val()},
							{ "name": "typeid", "value": $("#change-table").val()}
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
		//隐藏
		function hiedden() {
			var typeid=$("#change-table").val();
			var table =  $("#OpinionFeedback_table").DataTable();
			goSearch();	
			if (typeid==2) {
				table.column(2).visible(false);
				table.column(3).visible(false);
				table.column(4).visible(false);//隐藏列  
				table.column(5).visible(false);
				table.column(8).visible(false);
				
			}else {
				table.column(2).visible(true);
				table.column(3).visible(true);
				table.column(4).visible(true);//显示
				table.column(5).visible(true);
				table.column(8).visible(true);
			}
		}
		//跳转详细查询的页面
		function query_opinion(id,w,h){
		     var url = path+"/OpinionFeedbackConteroller.do?edit&id="+id;
		     layer_show('查看',url,'900','600',true);
		}
		
		//单条删除
		function opinion_del(obj,id){
			layer.confirm('确认要删除吗？',function(index){
			   sumbitDel(id);
			});
		}
		//意见反馈-批量删除
		function datadel(){
		   var l = new Array();
	       $("#OpinionFeedback_table .checkbox:checked").each(function() {		
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
		
	   
	   //反馈删除的共用
		function sumbitDel(ids){
			$.ajax({
				    url: path+"/OpinionFeedbackConteroller/opinionDel.do",
				    traditional:true,
				    dataType:'json',  
				    data:{"dtoId":ids},
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
	   
		