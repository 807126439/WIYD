var totalPage = $("#totalPage").val();
var comId = $("#comId").val();
var path = $("#path").val();
		
$(function(){	
		 if(totalPage<2){
			$("#page").hide();
		 }else{
			$("#page").show();
		 }
		 
		 queryPage(true,false);
	
	});
	
	function queryPage(byTime,byLove){				 
		 laypage({
			   cont: "page",
			   pages: totalPage,
			   skip: true, //是否开启跳页
			   skin:'#AF0000',
			   groups: 5, //连续显示分页数
			   jump:function(obj){
					   $.ajax({
			             type: "post",
			             url: path+"/commentController/getAllComment.do",
			             dataType:'json',  
			             data: {
			            	    comId:comId,		
					        	curPage:obj.curr,
					        	pageSize:6,
					        	byTime:byTime,
					        	byLove:byLove
			            	 },
			            	 success:function(data){
			            		 $("#comment").html("");	 
					        	$.each(data.list,function(i,item) {		
					        		 var str = "<p>"+item.username+" : "+item.content+"</p>";	
					        		 if(item.isHaveChild == true){
					        			 
					        				$.each(item.childComment,function(j,child) {	
					        					
					        					var author;
						        				if(child.targetId != null){
						        					author = child.username +" 对   "+child.targetUsername+" 说："
						        				}else{
						        					author = child.username+"：";
						        				}
						        								
					        					 str = str + "<p class='sub'>"+author+" "+child.content+"</p>";
					        					
					        				})
					        			 
					        			
					        			 
					        		 }
					        		 
					        		 $("#comment").append(str);	 
					        	})
			              }
			         });
			  }
		 });				
	}