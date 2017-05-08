var mtable;
		$(function(){
		
			mtable = $("#file_table").dataTable({
			    "sAjaxSource": path+"/attachment/getPageData.do",
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
				               	{ "mDataProp":"id" ,"bSortable": false,"sWidth":"32px","sTitle": "<input type=\"checkbox\" name=\"\" value=\"\" >",
				    			   	  "mRender": function (data, type, full) {
				    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
				    			   	  
				    			   	  }
				    			   },
	                               { "sTitle": "附件名称", "sClass": "center","mDataProp":"name" },                     
	                               { "sTitle": "上传时间", "sClass": "center","mDataProp":"createTime" },
	                               { "sTitle": "类型", "sClass": "td-status","mDataProp":"type",
	                                   "mRender": function (data, type, full) {
	                              		  if(data == 0){
	                              		  	return " <span class=\"label label-secondary radius\">处理方</span>";
	                              		  }else if(data == 1){
	                              		  	return " <span class=\"label label-primary radius\">申请方</span>";
	                              		  }	else{
	                              			  
	                              			  return "";
	                              		  }	
	           
	                                   }
	                                 }, 
	                               { "sTitle": "状态", "sClass": "td-status","mDataProp":"status",
	                                   "mRender": function (data, type, full) {
	                              		  if(data == 0){
	                              		  	return " <span class=\"label label-warning radius\">隐藏</span>";
	                              		  }else if(data == 1){
	                              		  	return " <span class=\"label label-success radius\">公开</span>";
	                              		  }	else{
	                              			  
	                              			  return "";
	                              		  }	
	           
	                                   }
	                                 }, 
	  
	                               {
	                                   "sTitle": "操作",
	                                   "sClass": "center",  
	                                   "mDataProp":"id" ,  
	                                   "width":"60px",
	                                   "bSortable": false,
	                                   "mRender": function (data, type, full) {
	                              
	                              			var html = "";                             		
		                              		if(typeof(file_down)!= "undefined"){
		                              			 if(file_down.length>0){
		                              			 	html+=file_down.format(data);
		                              			 }                            			
		                              		}	                              		
	                             	                             	                             	
	                                        return html;
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
					{ "name": "procInstId", "value": $("#procInstId").val() },
					{ "name": "attaName", "value": $("#attaName").val() }
					
					);
					
					
					
					}
              
			
			});
			
		
			/*全选*/
			$("table thead th input:checkbox").on("click" , function(){
				$(this).closest("table").find("tr > td:first-child input:checkbox").prop("checked",$("table thead th input:checkbox").prop("checked"));
		    });
		
			
		});
	


	function downFile(id){
	 	  if(id){
	 	    window.location.href = path+"/attachment/downAttachment.do?aid="+id;	  
	 	  }
	 }
		 	
	
	function status_set(obj,id,ty){

		sumbitSet(id,ty);

	}
	
	function dataSet(ty){
	   var l = new Array();
	         
       $("#file_table .checkbox:checked").each(function() {		
		  l.push($(this).val());		
	   });
	   	 
	   if(l.length > 0){
		   	layer.confirm("确认要设置"+l.length+" 条记录吗？",function(index){
		   		sumbitSet(l,ty);
			});
	   }else{
	     layer.msg('无选中的项!',{icon: 0,time:1000});
	   }
	}
	
	function sumbitSet(ids,ty){
		$.ajax({
			    url: path+"/attachment/setAttaStatus.do",
			    traditional:true,
			    dataType:'json',  
			    data:{
			    "aids":ids,
			    "dataType":ty
			    
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