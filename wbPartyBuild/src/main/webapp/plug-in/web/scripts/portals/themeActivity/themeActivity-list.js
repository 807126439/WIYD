 var mtable;
		 $(function(){
			
			 mtable = $("#themeActivity_table").dataTable({
				    "sAjaxSource": path+"/themeActivityController/pageList.do",
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
				    			   { "mDataProp":"id" ,"bSortable": false,
				    			   	  "mRender": function (data, type, full) {
				    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
				    			   	  
				    			   	  }
				    			   },					    				
				    			   { 
				    				   "sTitle": "状态", 
				    				   "sClass": "center",
				    				   "mDataProp":"status", 
				    				   "mRender": function (data, type, full) {				    					   
				    					   if(data == "1"){
		                             		  	return " <span class=\"label label-success radius\">活跃</span>";
		                             	   }else{
		                             			return " <span class=\"label label-defaunt radius\">失效</span>";
		                             	   }			    					   
				    				   }
				    			   }, 
				    			   { "sTitle": "活动类型", "sClass": "center","mDataProp":"activityTypeName" }, 
	                               { "sTitle": "活动名称", "sClass": "center","mDataProp":"activityName" }, 
	                               { "sTitle": "开始时间", "sClass": "center","mDataProp":"startDate"},  	                     
	                               { "sTitle": "结束时间", "sClass": "center","mDataProp":"endDate"},	                               
	                               {
	                                   "sTitle": "操作",
	                                   "sClass": "center",  
	                                   "mDataProp":"adsId" ,                              
	                                   "bSortable": false,
	                                   "mRender": function (data, type, full) {
	                                	   var html = "<div class=\"t-btn-container\">";  
	                              			
	                              			
	                              			if(full.status == 0){		 
		                              			//激活
		                              			if(typeof(manage_activate)!= "undefined"){
			                              			 if(manage_activate.length>0){
			                              			 	html+=manage_activate.format(full.id);
			                              			 }	                              			
			                              		}
	                              			}
	                              			
	                              			
	                              			//稿件管理
	                              			if(typeof(manage_manuscript)!= "undefined"){
		                              			 if(manage_manuscript.length>0){
		                              			 	html+=manage_manuscript.format(full.id,full.activityName,full.activityType);
		                              			 }                            			
		                              		}
	                              			
	                              			if(full.activityType == true){

		                              			//活动规则
		                              			if(typeof(manage_activityRule)!= "undefined"){
			                              			 if(manage_activityRule.length>0){
			                              			 	html+=manage_activityRule.format(full.id);
			                              			 }                            			
			                              		}
		                              			
		                              			if(full.isReward == true){	
		                              				
		                              				//设置奖项
		                              				if(typeof(setting_awards)!= "undefined"){
				                              			 if(setting_awards.length>0){
				                              			 	html+=setting_awards.format(full.id);
				                              			 }                            			
				                              		} 
		                              				
		                              				//评奖
		                              				if(typeof(winningWorks_award)!= "undefined"){
				                              			 if(winningWorks_award.length>0){
				                              			 	html+=winningWorks_award.format(full.id);
				                              			 }                            			
		                              				}
		                              			}
	                              			}	

	                              			//编辑活动
		                              		if(typeof(edit_themeActivity)!= "undefined"){
		                              			 if(edit_themeActivity.length>0){
		                              			 	html+=edit_themeActivity.format(full.id);
		                              			 }                            			
		                              		}
		                              		
		                              		
		                              		if(full.status == 0){		                              			
		                              		//删除活动
			                              		if(typeof(del_themeActivity)!= "undefined"){
			                              			 if(del_themeActivity.length>0){
			                              			 	html+=del_themeActivity.format(full.id);
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
							{ "name": "activityType", "value":$("#activityType").val()},
							{ "name": "activityName", "value":$("#activityName").val()}
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
		 function themeActivity_active(obj,id){
			 layer.confirm('确认要激活吗？',function(index){
				   activate(id);
				});
		 }
		 
		 function manuscript_manage(id,activityName,activityType,w,h){
			 

				var url = path+"/manuscriptController.do?viewPage&activityId="+id+"&activityType="+activityType;
				var title =activityName +" --  稿件管理";
				var w= "";
				var h= "";
				layer_full(title,url,w,h,true);
		 }
		 


			 
		 function activityRule_manage(id,w,h){
				var url = path+"/activityRuleController.do?viewPage&activityId="+id;
				var w="1000";
				layer_show("活动规则",url,w,h,true);
		 }
		 
		 
		 function award_winningWorks(id,w,h){
				var url = path+"/awardWinningWorksController.do?viewPage&activityId="+id;
				layer_show("作品评奖",url,w,h,true);
		 }
		 
			
		 function awards_setting(id,w,h){
				var url = path+"/awardsSettingController.do?viewPage&activityId="+id;
				layer_show("设定奖项",url,w,h,true);
		 }
		 		 
		 function themeActivity_add(title,w,h){
			var url = path+"/themeActivityController.do?add";
			var w="";
			var h="";
			layer_full(title,url,w,h,true);
		 }
			
		 function themeActivity_edit(id,w,h){
		     var url = path+"/themeActivityController.do?edit&id="+id;	
		     var w="";
			 var h="";
		     layer_full("编辑主题活动",url,w,h,true);
		 }
		
		
		
		
		function themeActivity_del(obj,id){
			
			layer.confirm('删除该条记录，会删除该活动下所有稿件及相关设置，确定删除吗?',{
				  title: '警告' //按钮
			},function(index){
			   sumbitDel(id);
			});
			
		}
		
		function datadel(){
		   var l = new Array();
		         
	       $("#themeActivity_table .checkbox:checked").each(function() {		
			  l.push($(this).val());		
		   });
		   	 
		   if(l.length > 0){
	
			   	
			   	layer.confirm("删除这些记录，会删除活动下所有稿件及相关设置，确认要删除 "+l.length+" 条记录吗？",
			   		{
			   		title: '警告'
				},function(index){
				   sumbitDel(id);
				});
			   	
			   	
		   }else{
		     layer.msg('无选中的项!',{icon: 0,time:1000});
		   }
		}
		
		function sumbitDel(ids){
			$.ajax({
				    url: path+"/themeActivityController/delThemeActivity.do",
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
		function activate(id){
			$.ajax({
				    url: path+"/themeActivityController/activateThemeActivity.do",
				    traditional:true,
				    dataType:'json',  
				    data:{
				    	"id":id
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
		