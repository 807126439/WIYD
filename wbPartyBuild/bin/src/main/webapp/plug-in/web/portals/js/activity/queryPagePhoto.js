	var isFirst = true; 
	var workslist = [];
	var path = $("#path").val();
	var activityId=$("#activityId").val();
	
	$(function() {
		loadData(false,true)
	});	
	
	
	function loadData(queryByLove,queryByTime,curr){
		curr = curr ||1	
		 $.ajax({
	        	type:'post',
	        	url:"manuscriptController/getAllPhoto.do",
	        	data:{
	        		activityId:activityId,
	        		status:1,
	        		currentPage:curr ||1,
	        		pageSize:12,
	        		queryByLove:queryByLove,
	        		queryByTime:queryByTime
	        	},
	        	dataType: "json",
	        	success:function(data) {
	        		
	        		 laypage({
	      			   cont: 'page1',
	      			   pages:data.totalPage,
	      			   curr: curr || 1,
	      			   jump:function(obj, first){      				   
		      				 if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
	                       	  loadData(false,true,obj.curr);
	                         }
	      			   	}
	      			  })	        		
	      			resolveData(data.list);	        	
	        	}
	        })		
	}
	
	
	function resolveData(data){
		$("#col1").html("");
		$("#col2").html("");
		$("#col3").html("");
		$.each(data,function(i, item) {			
			var str = "<li>"
					+"<div class='work' id='work-4'>"
					+"<div class='work-pic'>"
					+"<i class='Hui-iconfont noimg active' onclick='loadAgain(this)'></i><img src='"+item.pattern+"' onload='imgLoad(event)'>"
					+"<input class='bigPattern' value='"+item.bigPattern+"' type='hidden'>"
					+"</div>"
					+"<div class='work-tt'>"
					+"<span>"+item.title+"</span>";
			if(item.loveing == true){
				str = str +"<span class='love loving' onclick='loh("+item.id+",event)'>" ;
			}else{
				str = str +"<span class='love' onclick='loh("+item.id+",event)'>" ;
			}
			str = str  +"<i class='Hui-iconfont'></i><span>"+item.love+"</span></span>"
                +"</div>"
                +"<div class='work-ct'>"        	               
                +"<p>描述："+item.description+"</p>"
                +"</div>"
                +"</div>"
                +"</li>";
			
			if(i%3==0){
				 $("#col1").append(str);				        				
			}if(i%3==1){
				$("#col2").append(str);				        				
			}if(i%3==2){
				$("#col3").append(str);				        				
			}
		});		
		
		
	}