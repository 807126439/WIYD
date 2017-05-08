var isLoadJob = false;
	    function choiceJob(){
	    
	    	if(!isLoadJob){
	    		initJobList();
	    	}
	    	
	    	w=($(window).width() - 30);
	    	h=($(window).height() - 20);
	    	layer.open({
			  type: 1,
			  title: "选择职务",
			  area: [w+'px', h+'px'],
			  shade: 0.2,
			  content: $('#hide-job-area')
			});
	    
	    }	
	
	
	
	   function initJobList(){
	   		
	   		 $("#job_table").dataTable({
			    "sAjaxSource": path+"/jobDutyController/pageList.do",
			    "sServerMethod":"post",
			    "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
	            "bServerSide" : true, //是否启动服务器端数据导入  
	            "bStateSave" : false, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  	           
	            "aLengthMenu" : [10, 20, 40,60], //更改显示记录数选项  
	            "bLengthChange" : false,// 每行显示记录数 
	            "iDisplayLength" : 10, //默认显示的记录数  
	            "bAutoWidth" : false, //是否自适应宽度  
	            "sScrollY": "700px",
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
			
				],
			    "aoColumns": [
			    			   { "mDataProp":"id" ,"bSortable": false,"sWidth":"32px",
			    			   	  "mRender": function (data, type, full) {
			    			   	    return "<input type=\"radio\" name=\"jtid\" data-name=\""+full.jobName+"\" class=\"checkbox jobbox\" value=\""+data+"\">";
			    			   	  
			    			   	  }
			    			   },                                           
                               { "sTitle": "职务名称", "sWidth":"100px","mDataProp":"jobName" },
                               { "sTitle": "职务编码", "sWidth":"100px","mDataProp":"jobCode" }
                               
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
					{ "name": "jobName", "value": $("#job_name").val()}	
					
					);
					
					
					
					}
              
			
			});
			
	   
	   		isLoadJob = true;
	   }
	
	
	
		function subChoiceJob(nameId,idId){
			var curRadio = $("#job_table tbody .jobbox:checked");
			if(curRadio.size()>0){
			    $("#"+nameId).val(curRadio.attr("data-name"));
			    $("#"+idId).val(curRadio.val());
			    
			    layer.closeAll('page');
			
			}else{
				 layer.msg('请选择职务!',{icon: 0,time:1000});
			}	
		
		}
	
	