
 $(function(){


		    $("#sysLogs_table").dataTable({
			    "sAjaxSource": path+"/sysLogsController/getPageData.do",
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
		        "aaSorting" : [[1, "asc"]], //默认的排序方式，第1列，升序排列  
		    	"bFilter" : false, //是否启动过滤、搜索功能
				"aoColumnDefs": [
				{
				 sDefaultContent: '',
				 aTargets: [ '_all' ]
				  }
				  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
				],
			    "aoColumns": [
			    			   { "mDataProp":"id" ,"bSortable": true,"width":"32px","sTitle": "<input type=\"checkbox\" name=\"\" value=\"\" >",
			    			   	  "mRender": function (data, type, full) {
			    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
			    			   	  
			    			   	  }
			    			   }, 			    			     
			    			   { "sTitle": "线程名称","width":"100px","mDataProp":"thread" }, 
			    			   { "sTitle": "类名","width":"150px","mDataProp":"className" }, 
			    			   { "sTitle": "类方法","width":"100px","mDataProp":"method" }, 
			    			   { "sTitle": "时间","width":"150px","mDataProp":"createTime" }, 
			    			   { "sTitle": "等级","width":"80px","mDataProp":"loglevel" }, 
			    			   { "sTitle": "信息","width":"","mDataProp":"msg" }, 
		                       {
		                           "sTitle": "操作", 
		                           "mDataProp":"id" , 
		                           "width":"30px",                             
		                           "bSortable": false,
		                           "mRender": function (data, type, full) {
		                           	                                                  		 
		                        	    var html = "<div class=\"t-btn-container\">";                              		
		                          		if(typeof(model_edit)!= "undefined"){
		                          			 if(model_edit.length>0){
		                          			 	html+=model_edit.format(data);
		                          			 }                            			
		                          		}	                              		
		                          		if(typeof(model_del)!= "undefined"){
		                          			 if(model_del.length>0){
		                          			 	html+=model_del.format(data);
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
					{ "name": "startTime", "value": $("#datemin").val() },
					{ "name": "endTime", "value": $("#datemax").val() }	
					
					);				
			 }
		      
			
	   });
			
		
	   $("table thead th input:checkbox").on("click" , function(){
			$(this).closest("table").find("tr > td:first-child input:checkbox").prop("checked",$("table thead th input:checkbox").prop("checked"));
	    });
		
		
		$('.table-sort tbody').on( 'click', 'tr', function () {
			if ( $(this).hasClass('selected') ) {
				$(this).removeClass('selected');					
			}
			else {
				$(this).parent().find('tr.selected').removeClass('selected');						
				$(this).addClass('selected');				
				
			}
		});	
		
		
			
	});
	
	
	
	

	
	function model_add(title,w,h){
		var url = path + "/sysLogsController/skipAddSysLogs.do";
		layer_show(title,url,w,h);
	}

	
	function edit_model(title,id,w,h){
	     var url = path+"/sysLogsController/getDetail.do?id="+id;
		 layer_show(title,url,w,h);
	}
	
	
	function del_model(obj,id){
		layer.confirm('确认要删除吗？',function(index){
		   sumbitDel(id);
		});
	}
	
	function datadel(){
	   var l = new Array();
	         
       $("#sysLogs_table .checkbox:checked").each(function() {		
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
			    url: path+"/sysLogsController/deleteSysLogs.do",
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
			          		goSearch(false);	
			    		}else{
			    			layer.msg(data.info,{icon: 2,time:2500});
			    		}	    					         				           
			                		           
			       },  
			     error : function(error) {  
			            alert(error); 		            
			       } 
		  });
		
	
	}
	
	
	
	
 	function goSearch(signal){			   
		 $("#sysLogs_table").DataTable().draw(signal);					
	 }
	  
