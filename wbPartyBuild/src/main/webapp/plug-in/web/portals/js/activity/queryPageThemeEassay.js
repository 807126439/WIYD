	var path = $("#path").val();
	var totalPage= $("#totalPage").val();
	var columnId  = $("#columnId").val();
	
	$(function() {
		queryPage();		
		if(totalPage<2){
			$("#page").hide();
		}else{
			$("#page").show();
		}
	});	
	

function queryPage(){
		 laypage({
			   cont: 'page',
			   pages: totalPage,
			   prev:"上一页",
			   next:"下一页",
			   skip: true, //是否开启跳页
			   skin:'#b40100',
			   jump:function(obj, first){ //触发分页后的回调
			       //设置分页样式和总页数
			      $('#page .laypage_total').append("&emsp;共"+obj.pages+"页");
			     
			    	  $.ajax({
				    	 	async: false,
				        	type:'post',
				        	dataType:'json',
				        	url:path+"/themeActivityController/getPage.do",
				        	data:{
				        		curPage:obj.curr	
				        	},
				        	success:function(data) {
				        		
				        		 $("#announcementlist").html("");

				        		$.each(data.list,function(i, item) {
				        			
				        			str = path+"/themeEassay/"+columnId+".htm?eassayId="+item.id;				        			
				        			$("#announcementlist").append(
				        					"<li class='announcement'>"
				        					+"<div class='announcementaside'>"
				        					+"<span class='publicdate'>"+(new Date(Date.parse(item.startDate))).toLocaleDateString()+"</span>"
				        					+" </div>"
				        					+"<a href='"+str+"' class='announcementcontent'>"+ item.activityName+"</a>"
				        					+ "</li>"
				        			
				        			);
	
				        			
				        		});
				        			
				        			        			
				        	}
				       });			    	  
			
			    }		 		
			  });
	}
	