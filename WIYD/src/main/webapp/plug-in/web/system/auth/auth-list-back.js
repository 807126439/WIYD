
		$(function(){
		
			$("#auth_table").dataTable({
			    "sAjaxSource": path+"/authController/getPageData.do",
			    "sServerMethod":"post",
			    "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
	            "bServerSide" : true, //是否启动服务器端数据导入  
	            "bStateSave" : true, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  	           
	            "aLengthMenu" : [10, 20, 40,60], //更改显示记录数选项  
	            "bLengthChange" : true,// 每行显示记录数 
	            "iDisplayLength" : 10, //默认显示的记录数  
	            "bAutoWidth" : false, //是否自适应宽度  
	            //"bScrollInfinite" : false, //是否启动初始化滚动条  
	            "bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变  
	            "bPaginate" : true, //是否显示（应用）分页器  
	            "bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
	            "sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页  
	            "bSort" : true, //是否启动各个字段的排序功能  
	            "aaSorting" : [[1, "desc"]], //默认的排序方式，第2列，升序排列  
            	"bFilter" : false, //是否启动过滤、搜索功能
				"aoColumnDefs": [
				{
				 sDefaultContent: '',
				 aTargets: [ '_all' ]
				  }				
				],
			    "aoColumns": [
			    			   { "mDataProp":"id" ,"bSortable": false,"width": "18px","sTitle": "<input type=\"checkbox\" name=\"\" value=\"\" >",
			    			   	  "mRender": function (data, type, full) {
			    			   	    return "<input type=\"checkbox\" class=\"checkbox\" value=\""+data+"\">";
			    			   	  
			    			   	  }
			    			   },
			    			   { "sTitle": "序号","mDataProp":"sort","width":"32px" }, 
                               { "sTitle": "权限名", "width": "150px","mDataProp":"authText" },                     
                               { "sTitle": "权限码", "width": "100px","mDataProp":"authCode" },
                               { "sTitle": "资源地址", "sClass": "center","mDataProp":"resourecesUrl" },
                               { "sTitle": "开启控制",
                                "width": "80px",
                                "mDataProp":"flag",
                                "mRender": function (data, type, full) {
                            		   if(data == 0){
                             		  	  return "<span class=\"label label-defaunty radius\">否</span>";
                             		   }else if(data == 1){
                             		      return "<span class=\"label label-primary radius\">是</span>";                            		                              		   
                             		   }else{
                             		    return "";
                             		  }	
                                 }
                               },
                               { "sTitle": "权限类型 ",
                            	  "width": "80px",
                                 "mDataProp":"authType",
                                  "mRender": function (data, type, full) {                                                              	                             	                             	
                                       if(data == 0){
                             		  	   return "<span class=\"label label-success radius\">菜单</span>";   
                             		   }else if(data == 1){
                             		      return "<span class=\"label label-defaunty radius\">访问</span>";                            		                              		   
                             		   }else if(data == 2){
                             		      return "<span class=\"label label-primary radius\">按钮</span>";
                             		  }else{
                             		    return "";
                             		  }		
                                   }
                                 },                               
                           
                               { "sTitle": "排序", "width": "60px","mDataProp":"authOrder"},                         
                               { "sTitle": "图标", "width": "45px","mDataProp":"icon", "bSortable": false,
                               	    "mRender": function (data, type, full) {
                            		 	
                                      return "<i class=\"Hui-iconfont\">"+data+"</i>";
                                   }
                               
                                },  
                               { "sTitle": "父节点","width": "150px","mDataProp":"parentName" },                         
                               {
                                   "sTitle": "操作",
                                   "sClass": "center",  
                                   "width":"100px",
                                   "mDataProp":"id" ,                              
                                   "bSortable": false,
                                   "mRender": function (data, type, full) {
                                	   var html = "<div class=\"t-btn-container\">";                              		
	                              		if(typeof(edit_auth)!= "undefined"){
	                              			 if(edit_auth.length>0){
	                              			 	html+=edit_auth.format(data);
	                              			 }                            			
	                              		}
	                              		if(typeof(del_auth)!= "undefined"){
	                              			 if(del_auth.length>0){
	                              			 	html+=del_auth.format(data);
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
					{ "name": "authName", "value": $("#ser_name").val() },					
					{ "name": "authCode", "value": $("#ser_code").val() },
					{ "name": "parAuthName", "value": $("#ser_par_name").val() },	
					{ "name": "authType", "value": $("#auth-type").val() },
					{ "name": "flag", "value": $("#auth-flag").val() }
					);
					
					
					
					}
              
			
			});
			
		
			
			
			
		
		
			
		});
	
	
	/*权限-添加*/
	function auth_add(title,url,w,h){
		layer_show_end(title,url,w,h,goSearch);
	}
	
	/*权限-修改*/
	function auth_edit(title,id,w,h){
	     var url = path+"/authController/getDetail.do?aid="+id;
	     layer_show_end(title,url,w,h,goSearch);
	}
	
	
	/*权限-删除*/
	function auth_del(obj,id){
		layer.confirm('确认要删除吗？',function(index){
		      sumbitDel(id);
		});
	}
	
	function datadel(){
	   var l = new Array();
	         
       $("#auth_table .checkbox:checked").each(function() {		
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
			    url: path+"/authController/delAuth.do",
			    traditional:true,
			    dataType:'json',  
			    data:{
			    "aids":ids
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
	

	
	function goSearch(signal){			   
		$("#auth_table").DataTable().draw(signal);					
	}
