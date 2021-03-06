var mtable;
		$(function(){
			mtable = $("#node_table").dataTable({
			    "sAjaxSource": path+"/procNode/pageList.do",
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
				
				  },
				  {"orderable":false,"aTargets":[4]}// 制定列不参与排序
				],
			    "aoColumns": [
			    			   { "mDataProp":"id" ,"bSortable": false,
			    			   	  "mRender": function (data, type, full) {
			    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
			    			   	  
			    			   	  }
			    			   },
                               { "sTitle": "节点标识符", "sClass": "center","mDataProp":"nodeCode" },                     
                               { "sTitle": "节点名称", "sClass": "center","mDataProp":"nodeName" },
                               { "sTitle": "节点类型", "sClass": "center","mDataProp":"nodeType" },              
                               { "sTitle": "描述","sWidth": "30%", "sClass": "center","mDataProp":"description" },                                              
                               {
                                   "sTitle": "操作",
                                   "sClass": "center",  
                                   "mDataProp":"id" ,                              
                                   "bSortable": false,
                                   "mRender": function (data, type, full) {
                              
                              			var html = "";                             		
	                              		if(typeof(edit_node)!= "undefined"){
	                              			 if(edit_node.length>0){
	                              			 	html+=edit_node.format(data);
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
					{ "name": "id", "value": $("#id").val() },
					{ "name": "nodeCode", "value": $("#nodeCode").val() },
					{ "name": "nodeName", "value": $("#nodeName").val() },
					{ "name": "nodeType", "value": $("#nodeType").val() }	
					
					);
					
					}
			});
		});
	
	/*流程定义-修改*/
	function node_edit(title,id,w,h){
	     var url = path+"/procNode/getDetail.do?id="+id;
		layer_full(title,url,w,h);
	}
	
	function goSearch(){
		mtable.fnDraw();
		
	
	}