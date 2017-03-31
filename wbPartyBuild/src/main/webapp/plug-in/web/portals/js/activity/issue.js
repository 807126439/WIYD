var path = $("#path").val();
var comId = $("#comId").val();
var totalPage = $("#totalPage").val();	
var hasDigg;//用户是否点过赞
if($("#isLove").val()==0){
	hasDigg = true;
}else{
	hasDigg = false;
}

	
	$(function(){			
		 conutComment(2);		
		 var ue = UE.getEditor('editor', {toolbars: [['emotion','drafts']],autoHeightEnabled: true, autoFloatEnabled: true	});
		 $('.sort_wrap li').click(function(){
			 var type = $(this).attr("id");
			 if(type == 1){
				 queryPage(false,true);
			 }if(type == 2){
				 queryPage(true,false);
			 }		 
		      $(this).addClass('active').siblings().removeClass('active');		     
		  })		 
		 queryPage(true,false);
		 if(totalPage<2){
			$("#page").hide();
		 }else{
			$("#page").show();
		 }
		 
		 
		 //点赞相关实现	    
	      if (hasDigg)
	      $('.digg').addClass('hasdigg');	      
	      $('.digg').mouseenter(function(){
	        var txt=(hasDigg===true)?"取消赞":"赞";
	        $('.digg .midd').eq(0).hide();
	        $('.digg .digg-tip').text(txt).show();
	      });
	      $('.digg').mouseleave(function(){
	        $('.digg .midd').eq(0).show();
	        $('.digg .digg-tip').hide();
	      });
	      
	      $('.digg').click(function(){
	    	
	        var i=(hasDigg===true)?-1:1;

	  
	        if(hasDigg == true){
	        	 $.ajax({	  
		        	    type:'post',
		           	    url:path+"/communController/editCommun.do",
		           	    data:{
		           	    	id:comId,
		           	    	type:4
		           	    }	  
	       	  	 });
	        }else{
	        
	        	 $.ajax({	  
		        	    type:'post',
		           	    url:path+"/communController/editCommun.do",
		           	    data:{
		           	    	id:comId,
		           	    	type:3
		           	    }	  
	       	  		 });
	        }
	        
	        $('.digg .digg-tip2').text(i==1?"+1":i).show();
	        $('.digg .digg-tip2').animate({top:'-20px',opacity:'0.6'},function(){
	          $('.digg .digg-tip2').css({top:'0px',opacity:'1'}).hide();
	        });
	        $('.digg .midd').eq(0).text(parseInt($('.digg .midd').eq(0).text())+i).show();
	        $('.digg .digg-tip').hide();
	        hasDigg=!hasDigg;
	        $('.digg').toggleClass('hasdigg');
	      });
	  
	      //评论点赞
	      $('.comment-digg,.reply-digg').click(function(){
	    	   var commentId_1 = $(this).closest('.comment').data('itemid');   //父评论
	      	   var commentId_2= $(this).closest('.reply').data('itemid');      //子评论
	        if ($(this).hasClass('comment-haddigg')){
	        	if(commentId_1!=null && commentId_2 !=null){
		        	 $.ajax({	  
		        	    type:'post',
		           	    url:path+"/commentController/editComment.do",
		           	    data:{
		           	    	id:commentId_2,
		           	    	type:3  
		           	    }	  
	       	  		 });
	        	}else{	
			        $.ajax({	  
		        	    type:'post',
		           	    url:path+"/commentController/editComment.do",
		           	    data:{
		           	    	id:commentId_1,
		           	    	type:3  	           		
		           	    }	  
		       	  	});
	        	}	        	
	        	$(this).find('span').text(parseInt($(this).find('span').text())-1);
	        }	        
	        else {
	        	if(commentId_1!=null && commentId_2 !=null){
		        	 $.ajax({	 
		        	    type:'post',
		           	    url:path+"/commentController/editComment.do",
		           	    data:{
		           	    	id:commentId_2,
		           	    	type:2   
		           	    }	  
	       	  		 });
	        	}else{
			        $.ajax({	  
		        	    type:'post',
		           	    url:path+"/commentController/editComment.do",
		           	    data:{
		           		id:commentId_1,
		           		type:2           		
		           	    }	  
		       	  	});
	        	}
	        	$(this).find('span').text(parseInt($(this).find('span').text())+1);
	        }
	        $(this).toggleClass('comment-haddigg')
	      });
	      intireplybar();//回复列表初始化
	      $('.commentlist').on('click','.replies-toggle',togglereplybar);//回复列表展开收起
	      $('.commentlist').on('click','.reply-call',fixingIpt);
				
	 });	
		 	
	function conutComment(type){
		$.ajax({
			 type: "post",
		        url:path+"/commentController/countComment.do",
		        data:{
		        	"type":type,
		        	"comId":comId		        	
		        },
		        success: function(data) {
		        	$("#pNum").html("");
		        	$("#pNum").append(data)
		        }			
		})	
	}
	
	function queryPage(byTime,byLove){				 
		 laypage({
			   cont: 'page',
			   pages: totalPage,
			   skip: true, //是否开启跳页
			   skin:'#yahei',	
			   groups: 5, //连续显示分页数
			   jump:function(obj, first){
				   $.ajax({
				        type: "post",
				        url:path+"/commentController/getAllComment.do",
				        data:{
				        	"comId":comId,
				        	"type":2,
				        	"curPage":obj.curr,
				        	"parentId":null,
				        	"pageSize":6,
				        	"byTime":byTime,
				        	"byLove":byLove
				         },
				        async: false,
				        error: function(request) {
				            alert("Connection error");
				        },
				        success: function(data) {      	
				        	$("#commentlist").html("");
				        	data = jQuery.parseJSON(data);
				
							$("#pNum").html(data.count);
						    
						     
				        	$.each(data.list,function(i,item) {	
				        		if(item.isHaveChild == true){
				        			var str ="<li class='comment clearfix' data-itemid='"+item.id+"'>"
			    	        		+"<div class='comment-hd'>"
			    	        		+"<span class='author'>"+item.username+"</span>"
			    	        		+"<span class='pdate'>发表于："+item.commentTimeStr+"</span>"
			    	        		+"<span class='udate'>更新于："+item.updateTimeStr+"</span>"
			    	        		+"</div>"
			    	        		+"<div class='comment-bd'>"
			    	        		+"<div class='txtwp'>"+item.content+"</div>"
			    	        		+"</div>"
			    	        		+"<div class='comment-ft'>";
				        			
				        			if(item.loving == true){
				        				str = str+"<a class='comment-digg comment-haddigg' href='javascript:void(0)'>赞(<span>"+item.love+"</span>)</a>";
				        			}else{
				        				str = str+"<a class='comment-digg' href='javascript:void(0)'>赞(<span>"+item.love+"</span>)</a>";
				        			}
			    	        		str= str+"<a class='reply-call' href='javascript:void(0)'>回复</a>"	
			    	        				+"<a href='javascript:void(0)' class='replies-toggle replies-open'>回复列表(<span class='count'>1</span>)</a>"
			    	        				+"</div>"
			    	        				+"<div class='replies'>"
			    	        				+"<ul class='reply-list'>";
			    	        
			    	        		
				        			
				        			$.each(item.childComment,function(j,child) {	
				        				var author;
				        				if(child.targetId != null){
				        					author = child.username +" 对   "+child.targetUsername+" 说："
				        				}else{
				        					author = child.username+"：";
				        				}
				        				str = str+"<li class='reply clearfix' data-itemid='"+child.id+"' data-userId='"+child.userId+"'>"
				    	        		+"<div class='reply-hd clearfix'>"
				    	        		+"<span class='author'>"+author+"</span>"
				    	        		+"</div>"
				    	        		+"<div class='reply-bd'>"
				    	        		+"<div class='txtwp'>"+child.content+"</div>"
				    	        		+"</div>"
				    	        		+"<div class='reply-ft'>"
				    	        		+"<span class='pdate'>发表于："+child.commentTimeStr+"</span>";
				        				if(child.loving == true){
					        				str = str+"<a class='reply-digg comment-haddigg' href='javascript:void(0)'>赞(<span>"+child.love+"</span>)</a>";
					        			}else{
					        				str = str+"<a class='reply-digg' href='javascript:void(0)'>赞(<span>"+child.love+"</span>)</a>";
					        			}
				    	        		str=str+"<a class='reply-call' href='javascript:void(0)'>回复</a>"
				    	        		+"</div>"
				    	        		+"</li>"
				        			});		
				        			
				        			str=str+"</ul>"+"</div>"+"</li>"
				        			$("#commentlist").append(str);       			
				        		}else{
				        			
				        			var str ="<li class='comment clearfix' data-itemid='"+item.id+"'>"
							        			+"<div class='comment-hd'>"
						    	        		+"<span class='author'>"+item.username+"</span>"
						    	        		+"<span class='pdate'>发表于："+item.commentTimeStr+"</span>"
						    	        		+"<span class='udate'>更新于："+item.updateTimeStr+"</span>"
						    	        		+"</div>"
						    	        		+"<div class='comment-bd'>"
						    	        		+"<div class='txtwp'>"+item.content+"</div>"
						    	        		+"</div>"
						    	        		+"<div class='comment-ft'>";
				        			
				        			if(item.loving == true){
				        				str = 	str + "<a class='comment-digg comment-haddigg' href='javascript:void(0)'>赞(<span>"+item.love+"</span>)</a>";				        				
				        			}else{
				        				str = 	str + "<a class='comment-digg' href='javascript:void(0)'>赞(<span>"+item.love+"</span>)</a>";
				        			}
				        			
				        			str = str +"<a class='reply-call' href='javascript:void(0)'>回复</a>"	
					    	        		+"</div>"					    	        
					    	        		+"</li>" 
				        			
				        			$("#commentlist").append(str);
				        		}	
				        	});       	
				        }
					 });
			   }
		 });				
	}
	
	
	
	
	
		function submitComment(){		
				$.ajax({
	                type: "post",
	                url:path+"/commentController/addComment.do",
	                data:$("#commmentForm").serialize(),
	                async: false,
	                error: function(request) {
	                    alert("Connection error");
	                },
	                beforeSend: function(){
	                	if(!UE.getEditor('editor').hasContents()){
							layer.msg("请填写评论内容！",{icon: 0,time:1500}); 						
							return false;
						} 
	                },
	                success: function(data) {	               	                	
                		data = jQuery.parseJSON(data);          
                		if(data.status == "l"){                			
                			layer.confirm('您还未登录，是否登录?',function(index){
                				window.location.href = path + "/login.jsp";
                			});               			
                		}else if(data.status == "y"){   
                			
                			$("#parentId").val(null);
                			$("#userId").val(null);
                			UE.getEditor('editor').setContent("");
                			unfixingIpt();
    	            		layer.msg(data.info,{icon:1,time:1000});	    	            		 	            		
    	            		queryPage(true,false); 	            		
                		}else if(data.status == "n"){	                			
                			layer.msg(data.info,{icon:2,time:2000});
                		}	                		            		
	                }
         	   });					
		}
		
		 
	    function fixingIpt(){
			$('.lef-editor').addClass('fixing');
	        if($('.lef-editor-f .unfixing').length!==0) return;
	    	$('.lef-editor-f').prepend('<a class="unfixing" href="javascript:unfixingIpt()">隐藏</a>');	 	
	    	$("#parentId").val($(this).closest('.comment').data('itemid'));
	    	$("#userId").val($(this).closest('.reply').data('userid'));
	    
	    }
		
	    function unfixingIpt(){
	    	$('.lef-editor').removeClass('fixing');
	    	$('.lef-editor-f .unfixing').remove();
	    }
	    //收起回复栏
	    function togglereplybar() {	    	
	      if ($(this).find('span').text()=='0')return;
	      $(this).closest('.comment-ft').next().slideToggle();
	      if ($(this).hasClass('replies-open'))
	        $(this).removeClass('replies-open');
	      else $(this).addClass('replies-open');   
	    }
	    
	    // 初始化，根据高度判断是否收起回复栏,做reply-list溢出的滚动处理
	    function intireplybar() {
	      for (i = 0; i < $('.reply-list').length; i++){
	        var elem1=$('.reply-list').eq(i).parent().prev().find('.replies-toggle');
	        if ($('.reply-list').eq(i).height() >720 || $('.reply-list').eq(i).height() == 0) {
	          $('.reply-list').eq(i).parent().css("display", "none");
	          elem1.removeClass('replies-open');
	        }
	        elem1.parent().find('.count').text($('.reply-list').eq(i).find('.reply').length);
	      }
	    }	