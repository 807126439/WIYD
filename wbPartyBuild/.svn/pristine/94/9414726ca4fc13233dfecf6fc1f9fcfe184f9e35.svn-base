var isFirst = true; 

	var path = $("#path").val();
	var colId=$("#colId").val();
	

	$(function() {
		var ctTotalPage= $("#ctTotalPage").val();
		if(ctTotalPage){
			ctTotalPage = parseInt(ctTotalPage);
			
			if(ctTotalPage !=-1){
				
				queryPage(parseInt(ctTotalPage));
				
				if(ctTotalPage<2){
					$("#page").hide();
				}else{
					$("#page").show();
				}
			}
			
			
		}
		
		
		
	});
	
	function queryPage(totalPage){
		laypage({
			     cont: 'page', //容器。值支持id名、原生dom对象，jquery对象,
		   		 pages: totalPage, //总页数
		   		 skin: '#AF0000', //加载内置皮肤，也可以直接赋值16进制颜色值，如:#c00
		   		 skip: true, //是否开启跳页
		   		 groups: 3, //连续显示分页数
				 jump: function(obj){
				   
				   if(!isFirst){
				   		$.ajax({
				        	type:'post',
				        	url:path+"/studyTask/getStudyTaskPage.do",
				        	data:{
				        		curPage:obj.curr
				        	},
				        	dataType: "json",
				        	success:function(data) {
				        	
				        	  $("#announcementlist").html("");
				        	  
				        		$.each(data.list,function(i, item) {
						            $("#announcementlist").append(		                    
						               "<li class='announcement'>"
							         +"<div class='announcementaside'>"
							         +"<span class='publicdate'>"
						             +item.startTime
						             +"</span></div>"
							         +"<a href='"+path+"/studyTask/"+colId+".htm?taskId="+item.stId+"' class='announcementcontent'>"+item.taskName+(item.finish?'<span>已完成</span>':'<span>未完成</span>')+"</a>"
							         +"</li>"			                  
						            );
						      });
				        	}
				       });	
					}					
					isFirst = false;	        
			    }
		});
	}
	