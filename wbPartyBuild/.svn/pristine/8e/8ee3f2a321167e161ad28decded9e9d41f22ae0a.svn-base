 var mtable;
 var idsArray = $("#choosentopic").val().split(",")
 
  var topicScoreArray = $("#topicScore").val().split(",")
  var topicTextArray = $("#topicText").val().split(",")
  var topicTypeNameArray = $("#topicTypeName").val().split(",")
  var topicCNameArray = $("#topicCName").val().split(",")

 
for(var i=0;i<idsArray.length;i++){				
	var str = "<li title='"+"题目类别："+topicCNameArray[i]+"&#10;题目类型："+topicTypeNameArray[i]+"&#10;题目分值："+topicScoreArray[i]+"分&#10;题目文本："+topicTextArray[i]+"' id='"+idsArray[i]+"' dataid='"+idsArray[i]+"'>"+idsArray[i]+"   "+topicTypeNameArray[i]+"-"+topicScoreArray[i]+"分<i class='Hui-iconfont Hui-iconfont-close2'></i></li>";
	$(".choosenlist").append(str);	
}
$(function(){
	$("#form-examPaperC-add").Validform({
		tiptype:1,
		ajaxPost:true,//ajax方式提交表单数据
		beforeSubmit:function(curform){	 
			var l = new Array();    
		    $(".choosenlist li").each(function() {		
				l.push($(this).attr("id"));				  
			});	
		    if(l.length>0){
		    	 $("#choosentopic").val("");	
		    	 $("#choosentopic").val(l);	
		    	 return true;
		    }else{
		    	layer.msg("请选择题目");
		    	return false;
		    }
		},		
		callback:function(data){	
				if(data.status == "y" || data.status == "Y"){	
					 setTimeout(function(){
					      	parent.goSearch();
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
							      					      
					      },800); 
					
				}
			}
		});
	
	
	mtable = $("#topic_table").dataTable({
			    "sAjaxSource": path+"/topicController/pageList.do",
			    "sServerMethod":"post",
			    "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
		        "bServerSide" : true, //是否启动服务器端数据导入  
		        "bStateSave" : false, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态  	           
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
				  },				
				  //{"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
				  
				],					
			    "aoColumns": [  
			    			   { "mDataProp":"topicId" ,
			    				   "bSortable": false,"sWidth": "20px",
			    				
			    			   	  "mRender": function (data, type, full) {	
			    			   		  
			    			   		var flag = 0;			    			   		
			    			   		for(var i=0;i<idsArray.length;i++){
			    			   			if(data == idsArray[i]){
			    			   				flag = 1;
			    			   			}
			    			   		}
			    			   		
			    			   		  
	    			   		  		if(flag == 1 ){
	    			   		  			return "<input type=\"checkbox\" data-cN=\""+full.categoryName+"\" data-tY=\""+full.topicTypeName+"\" data-score=\""+full.score+"\" data-text=\""+full.questionText+"\" data-id=\""+data+"\" checked='checked' class=\"checkbox\" value=\""+data+"\">";				    			   		  			
	    			   		  		}else{
	    			   		  			return "<input type=\"checkbox\" data-cN=\""+full.categoryName+"\" data-tY=\""+full.topicTypeName+"\" data-score=\""+full.score+"\" data-text=\""+full.questionText+"\"  class=\"checkbox\" value=\""+data+"\">";
	    			   		  		}
			    			   	  }
			    			   },	   
			    			   { "sTitle": "标识号", "sWidth": "50px","mDataProp":"topicId"}, 
			    			   { "sTitle": "题目类别", "sWidth": "100px","mDataProp":"categoryName"}, 
			    			   { "sTitle": "题目类型", "sWidth": "80px","mDataProp":"topicTypeName"}, 
			    			   { "sTitle": "题目分值", "sWidth": "80px","mDataProp":"score"}, 
			    			   { "sTitle": "题目文本", "sClass": "center","mDataProp":"questionText"}, 
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
							{ "name": "questionText", "value":$("#questionText").val()},
							{ "name": "topicType", "value":$("#topicType").val()},
							{ "name": "categoryId", "value":$("#categoryId").val()},
							{ "name":"choosentopic","value":$("#choosentopic").val()}
						);				  					
					}  
			});
	
	
	$('.choosenlist').on('click','i',function(){		
		var id = $(this).parent().text();
		$(this).parent().remove();
		$('input[data-id="'+id+'"]').attr("checked",false);
	})
	
	
	$("#topic_table").on('click','.checkbox',function(){		
		var topicId =  $(this).val();	
		var categoryName = $(this).attr("data-cN");
		var topicTypeName = $(this).attr("data-tY");
		var score = $(this).attr("data-score");
		var questionText = $(this).attr("data-text");
		
		if($('#'+topicId).length===0){			
			$(this).attr("data-id",topicId);
			var str = "<li title='"+"题目类别："+categoryName+"&#10;题目类型："+topicTypeName+"&#10;题目分值："+score+"分&#10;题目文本："+questionText+"' id='"+topicId+"' dataid='"+topicId+"'>"+topicId+"   "+topicTypeName+"-"+score+"分<i class='Hui-iconfont Hui-iconfont-close2'></i></li>";
			$(".choosenlist").append(str);				
		}else{
			$('#'+topicId).remove();

		}
	})

	
	
	
	

		
});


function goSearch(){		
	mtable.fnDraw();					
}


