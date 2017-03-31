		var mtable;
		var idList=$(".choosenlist li");
		$(function(){
			$("#form-Editer").Validform({
				tiptype:1,
				ajaxPost:true,//ajax方式提交表单数据
				beforeSubmit:function(curform){
				     return true;
				},
				callback:function(data){	
						if(data.status == "y" || data.status == "Y"){	
							 setTimeout(function(){
								 	parent.window.location.href=path+"/columnMuController/getAllColumnMus.do"
								 	//var index = parent.layer.getFrameIndex(window.name);
								 	//parent.layer.close(index);
												      
							      },800); 
						}
					}
				});
		
			
		mtable = $("#user_table").dataTable({
			    "sAjaxSource": path+"/userController/pageList.do",
			    "sServerMethod":"post",
			    "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
	            "bServerSide" : true, //是否启动服务器端数据导入  
	            "bStateSave" : false, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  	           
	            "aLengthMenu" : [10, 20, 40,60], //更改显示记录数选项  
	            "bLengthChange" : true,// 每行显示记录数 
	            "iDisplayLength" : 10, //默认显示的记录数  
	            "bAutoWidth" : false, //是否自适应宽度  
	            "sScrollY": "500px",
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
			    			   { "mDataProp":"id" ,"bSortable": false,"width": "28px",
			    			   	  "mRender": function (data, type, full) {
			    			   		var flag=0; 
			    			   		idList.each(function(){
			    			   			if($(this).attr("dataid")==data){
			    			   				flag=1;
			    			   			}
			    			   		});
			    			   		if(flag==1){
			    			   			return "<input type=\"checkbox\" name=\"id\" checked=\"checked\" data-name=\""+full.userName+"\" class=\"checkbox jobbox\"  value=\""+data+"\">";
			    			   		}else{
			    			   			return "<input type=\"checkbox\" name=\"id\" data-name=\""+full.userName+"\" class=\"checkbox jobbox\" value=\""+data+"\">";
			    			   		}
			    			   	  }
			    			   },	
                               { "sTitle": "用户名", "width": "80px","mDataProp":"userName" }, 
                               { "sTitle": "中文名", "width": "80px","mDataProp":"chineseName" },  	                                                                                                          
                               { "sTitle": "是否可用", "width": "70px","mDataProp":"enabled",
                                    "mRender": function (data, type, full) {
                             		  if(data == true){
                             		  	return " <span class=\"label label-success radius\">是</span>";
                             		  }else{
                             		  	return " <span class=\"label label-defaunt radius\">否</span>";
                             		  }	
          
                                  }
                               
                                }
               ],
              "oLanguage": { //国际化配置  
                "sProcessing" : "正在获取数据，请稍后...",    
                "sLengthMenu" : "显示 _MENU_ 条",    
                "sZeroRecords" : "没有您要搜索的内容",    
                "sInfo" : "显示 _START_ 到  _END_ 条 ，共 _TOTAL_ 条",    
                "sInfoEmpty" : "记录数为0",    
                "sInfoFiltered" : "",    
                "sInfoPostFix" : "",    
                "sSearch" : "搜索",    
                "sUrl" : "",    
                "oPaginate": {    
                    "sFirst" : "",    
                    "sPrevious" : "上一页",    
                    "sNext" : "下一页",    
                    "sLast" : ""    
                }  
            },
              "fnServerParams" : function (aoData) {
					aoData.push(
					{ "name": "name", "value": $("#user_name").val() }			
					);
					}  
			
			});
			
		$('.choosenlist').on('click','i',function(){		
			var id = $(this).parent().attr("dataid");
			$(this).parent().remove();
			$('input[value="'+id+'"]').attr("checked",false);
		})
		
		
		$("#user_table").on('click','.checkbox',function(){		
			var userId =  $(this).val();
			var userName = $(this).attr("data-name");
			if($('#'+userId).length===0){			
				$(this).attr("data-id",userId);
				var str = "<li id='"+userId+"' dataid='"+userId+"'>"+userName+"<input type='hidden'  value="+userId+" name='userId' >"+"<i class='Hui-iconfont Hui-iconfont-close2'></i></li>";
				$(".choosenlist").append(str);				
			}else{
				$('#'+userId).remove();

			}
		})
		
		});
		
		
		function goSearch(){		
			mtable.fnDraw();					
		}
		
		var isLoadUser = false;
