var activityId = $("#activityId").val(); 
var mtable;
		 $(function(){
			
			 mtable = $("#awardWinningWorks_table").dataTable({
				    "sAjaxSource": path+"/awardWinningWorksController/pageList.do",
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
				    "aoColumns": [
				    			   { "mDataProp":"awwId" ,"bSortable": false,
				    			   	  "mRender": function (data, type, full) {
				    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
				    			   	  
				    			   	  }
				    			   },	
	                               { "sTitle": "获奖作品", "sClass": "center","mDataProp":"manuscriptTitle" },                                
	                               { "sTitle": "作者", "sClass": "center","mDataProp":"manuscriptUsername"}, 
	                               { "sTitle": "奖项", "sClass": "center","mDataProp":"awardsName"}, 
	                                  
	                               {
	                                   "sTitle": "操作",
	                                   "sClass": "center",  
	                                   "mDataProp":"adsId" ,                              
	                                   "bSortable": false,
	                                   "mRender": function (data, type, full) {
	                              
	                                	   	
	                                	   var html = "<div class=\"t-btn-container\">";  
	                              			
		                              		if(typeof(edit_awardWinningWorks)!= "undefined"){
		                              			 if(edit_awardWinningWorks.length>0){
		                              			 	html+=edit_awardWinningWorks.format(full.awwId);
		                              			 }                            			
		                              		}
		                              				                              			                              				                              		
		                              		if(typeof(del_awardWinningWorks)!= "undefined"){
		                              			 if(del_awardWinningWorks.length>0){
		                              			 	html+=del_awardWinningWorks.format(full.awwId);
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
							{ "name": "activityId", "value":activityId}			
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
		
		 		  
		function awardWinningWorks_add(title,w,h){
			var url = path+"/awardWinningWorksController.do?add&activityId="+activityId;
			var w = "640";
			var h="500";
			layer_show(title,url,w,h,true);
		}
		
	
		function awardWinningWorks_edit(awwId,w,h){
		     var url = path+"/awardWinningWorksController.do?edit&awwId="+awwId+"&activityId="+activityId;
		     var w = "640";
			 var h="590";
		     layer_show("编辑奖项设定",url,w,h,true);
		}
				
		function awardWinningWorks_del(obj,awwId){
			layer.confirm('确认要删除吗？',function(index){
			   sumbitDel(awwId);
			});
		}
		
		function datadel(){
		   var l = new Array();
		         
	       $("#awardWinningWorks_table .checkbox:checked").each(function() {		
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
		
		function sumbitDel(awwIds){
			$.ajax({
				    url: path+"/awardWinningWorksController/delAwardWinningWorks.do",
				    traditional:true,
				    dataType:'json',  
				    data:{
				    	"awwIds":awwIds
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
		