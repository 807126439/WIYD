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
					$("#page1").hide();
				}else{
					$("#page1").show();
				}
			}
			
			
		}
		
		
		
	});
	
	function queryPage(totalPage){
		
		laypage({
			     cont: 'page1', //容器。值支持id名、原生dom对象，jquery对象,
		   		 pages: totalPage, //总页数
		   		 prev:false,
		         next:false,
		         skip: true, //是否开启跳页
		         skin:'#b40100',
				 jump: function(obj){
					setpagesnumber(obj);
				   if(!isFirst){
				   		$.ajax({
				        	type:'post',
				        	url:path+"/joinPartyInfoController/getStatsData.do",
				        	data:{
				        		currentPage:obj.curr
				        	},
				        	dataType: "json",
				        	success:function(data) {
				        					        
				        	    var html = "";
				        		$.each(data.aaData,function(i,item) {
						          
				        			html+="<li>"+
				        			      "<div class=\"personalinfo1\">"+
				        			      "<span class=\"personalinfo1-name\">"+item.applyUserName+"</span>"+
				        			      "<span class=\"personalinfo1-belong\">"+item.department+"</span>"+
				        			      "<span class=\"personalinfo1-state\">"+item.currNode+"</span>"+
				        			      "</div>";
				        			
				        			html+="<div class=\"submittedinfo\"><ul>";
				        			 	 
			        			 		   for(var i=0;i<item.applyFiles;i++){
			        			 			  html+="<li><a href=\"javascript:;\"  onClick=\"downFile('"+item[i].id+"')\">"+item[i].name+"</a></li>";	
			        			 		   }					        			 
				        			 	  
			        			    html+="</ul></div>"; 	  
						                    
			        				html+="<div class=\"uploadedinfo\"><ul>";
			        			 	 
		        			 		   for(var i=0;i<item.handleFiles;i++){
		        			 			  html+="<li><a href=\"javascript:;\" onClick=\"downFile('"+item[i].id+"')\">"+item[i].name+"</a></li>";	
		        			 		   }					        			 
			        			 	  
		        			        html+="</ul></div>";      				                 
						               
						      });
				        		
				        	  $("#joinpartyinfo-list").html(html);
				        	}
				       });	
					}					
					isFirst = false;	        
			    }
		});
	}
	
	
	
	  //设置分页样式和总页数
    function setpagesnumber(obj) {
       $('#page1 .laypage_total label').eq(0).text('第');
       $('#page1 .laypage_total label').eq(1).hide();
       $('#page1 .laypage_total .laypage_btn').text("GO");
       $('#page1 .laypage_total').append("共"+obj.pages+"页");
    }
	