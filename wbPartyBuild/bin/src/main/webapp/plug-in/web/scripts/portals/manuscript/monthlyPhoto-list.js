	var activityId = $("#activityId").val();
	$(function() {
		loadData();
	});
		
	function loadData(curr){
		curr = curr ||1		
		$.ajax({
	             type: "post",
	             url: path+"/manuscriptController/getMonthlyPage.do",
	             data: {
	            	 "currentPage":curr ||1,
	            	 "pageSize":12,
	            	 "activityId":activityId,
	            	 "author":$("#author").val(),
	            	 "title":$("#title").val()
	             },
	             dataType: "json",
	             success: function(data){
	            	 
	            	 laypage({
	                     cont: 'item-page', 
	                     pages:data.totalPage, //通过后台拿到的总页数
	                     curr: curr || 1,
	                     jump: function (obj,first) { //触发分页后的回调
	                          if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
	                        	  loadData(obj.curr);
	                          }
	                     }
	                 });
	            	 
	            	 	resolveData(data.list);
		            	 
		                $(".item-area li").hover(function() {
		        			$(this).addClass("hover");
		        		}, function() {
		        			$(this).removeClass("hover");
		        		});

		        		$("#show-list li .itembox .itembox-rename").click(function() {
		        			$(this).closest('.itembox').find('.textbox').trigger('click');
		        		});
		        		
		        		
		        		$("#show-list li .itembox .textbox").click(
		        				
		        					function() {
		        						if($("#type").val()!='checkInfo'){
	 	 		        					$(this).addClass("curedit");//设置正在编辑状态
	 	 		        					var pic_name = $(this).attr("data-val");
	 	 		        					var pic_id = $(this).attr("data-id");
	 	 		        					var p_li = $(this).parents("li");
	 	 		        					p_li.removeClass("hover").addClass('editing');//隐藏操作栏
	 	 		        					var offset = p_li.offset();
	 	 		        					var X = offset.top;
	 	 		        					var Y = offset.left;
	
	 	 		        					$("#edit-pic-input").val(pic_name).focus();
	 	 		        					$("#edit-pic-id").val(pic_id);
	 	 		        					$("#edit-pic-box").attr(
	 	 		        							"style",
	 	 		        							"top: " + X + "px; left: " + (Y - 8)
	 	 		        									+ "px;display: block;");//显示修改input框
	
	 	 		        					$(".Hui-article").css({
	 	 		        						"overflow" : "hidden"
	 	 		        					});//禁止滚动
		        						}
		        					}
		        		);


		        		//绑定选中事件
		        		$('.itembox-checkbox').click(function() {
		        			checks($(this).closest('.item'));
		        		});

		        		$('.item-area .picbox a').click(function(event) {
		        			if (event.preventDefault) {
		        				event.preventDefault();
		        			}else{
		        				event.returnValue=false;
		        			}
		        			//showPhoto($(this).closest('li').index());
		        			showPhoto($('.item-area .picbox a').index($(this)))
		        		});
	            	 
	            	 
	             	}
	         })
	}
	
	
	//选中事件
	function checks($ele) {
		$ele.each(function() {
			var checkbox=$(this).find('.checkbox');
			var cbox=$(this).find('.itembox-checkbox');
			if (checkbox.is(':checked')) {
				checkbox.prop("checked",false);
				$(this).removeClass('checked');
				cbox.removeClass('checked');
			}else{
				checkbox.prop("checked",true);
				$(this).addClass('checked');
				cbox.addClass('checked');
			}
		});
	}


	//全选
	function selectAll() {
		checks($('.item-area .item').not('.checked'));
	}

	//全不选
	function cancelAll() {
		checks($('.item-area .item.checked'));
	}


	function resolveData(data) {
		$("#show-list").html("");

		if (data != null) {
			$(data).each(
					function(index) {
						var item = data[index];	
						
							var title= "作品："+item.title+"&#10;作者："+item.author+"&#10;描述："+item.description+"&#10;上传时间："+item.uploadTime
							var html = null;
							if(item.status ==1){
								//审核通过
								 html = "<li class=\"item audit-passed\" title=\""+title+"\">"	
							}else{
								 html = "<li class=\"item\" title=\""+title+"\">"	
							}							
							html += "<div class=\"itembox\">"
									+ "<input class=\"checkbox\"  type=\"checkbox\" name=\"picture\" value=\""+item.id+"\">"
									+"<span class=\"itembox-checkbox\"><i class=\"Hui-iconfont\">&#xe6a7;</i></span>"
									+ "<div class=\"itembox-top\"><span class=\"f-r\">"
									
							html += "<a href=\"javascript:manuscript_check("+item.id+");\" class=\"itembox-del\" title=\"审核\"><i class=\"Hui-iconfont\">&#xe695;</i></a>"																				   
							html += "<a href=\"javascript:showPhoto("+index+");\" class=\"itembox-del\" title=\"大图\"><i class=\"Hui-iconfont\">&#xe685;</i></a>"
							html +=	"<a href=\""+item.pattern+"\" download=\"\" title=\"下载\"><i class=\"Hui-iconfont\">&#xe640;</i></a>" 						 
						    html += "<a href=\"javascript:manuscript_del('"+item.id+"');\" class=\"itembox-del\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe609;</i></a>"
						    
							html += "</span></div><div class=\"picbox\">"	  
							html += "<a href=\""+item.bigPattern+"\" item-title=\""+item.title+"\" ><img src=\""+item.pattern+"\"></a>";

							html += "</div>";
							html += "<div class=\"textbox\" data-val=\""+item.title+"\" data-id=\""+item.id+"\" >"+item.title+"</div>";
							html += "<div class=\"authorbox\">作者：" + item.author + "</div>"; //作者栏
							html += "<div class=\"operbox\">";//操作栏

							html += "</div>";
							html += "</div>";
							html += "</li>";
							
							
							
						$("#show-list").append(html);
					});									
		}
	}


		
		function manuscript_del(id){
			layer.confirm('确认要删除吗？',function(index){
			   sumbitDel(id);
			});
		}	
		
		function download(id){
			window.location.href=path+"/manuscriptDocController/downLoadManuscript.do?msId="+id;
		}

		function manuscript_check(id,w,h){
		     var url = path+"/manuscriptController.do?check&id="+id+"&pageType=2";	     
		     layer_show("审核稿件",url,"500","200",true);
		}
				

	//大图展示
	function showPhoto(index){	
		var data=[];
		$('.item-area .picbox a').each(function() {
			data.push({
				"alt":($(this).attr('item-title')==="")?$(this).attr('title'):$(this).attr('item-title'),
				"src":$(this).attr('href')
			});
		});
		var json={
			"title": "",
			"start":index,
			"data":data
		};
		
		layer.photos({
		    photos:json,
		    skin:"layer-center",
		    shift:5,
			  tab: function(pic, layero){
			    console.log('new photo tab');
			    var $img=$(layero).find('img');
			    $(layero).css({
			    	'height':$img.height()+'px',
			    	'width':$img.width()+'px'
			    }).css({
			    	'top':($(window).height()-$(layero).height())/2+"px",
			    	'left':($(window).width()-$(layero).width())/2+"px"
			    });
			     $showbigimage=($(layero).find('.showbigimage').length!==0)?$(layero).find('.showbigimage'):($('<a class="showbigimage" href="" target="_blank" style="color:#FFF;font-size:14px;position:absolute;bottom:0;right:0;background-color: #333;padding: 0 10px;line-height: 32px;background-color: rgba(0,0,0,.8);">查看原图</a>').appendTo($(layero).find('.layui-layer-imgsee')));
		        $showbigimage.attr('href',$img.attr('src'));

			  }
		 
		  });
	}

	//批量删除警告
	function datadel() {
		var l = new Array();
		$(".item-area input.checkbox:checked").each(function() {
			l.push($(this).val());
		});

		if (l.length > 0) {
			layer.confirm("确认要删除" + l.length + " 张照片吗？", function(index) {
				sumbitDel(l);
				layer.close(index);
			});
		} else {
			layer.msg('无选中的项!', {
				icon : 0,
				time : 1000
			});
		}

	}
	
	
	function datacheck(){
		   var l = new Array();	         
		   $(".item-area input.checkbox:checked").each(function() {
				l.push($(this).val());
			});
	       
	       if(l.length > 0){
	    	   layer.confirm("确认审核通过 "+l.length+" 条记录吗？",function(index){
			   		$.ajax({			   			
			   		  url: path+"/manuscriptController/checkManuscript.do",
					    traditional:true,
					    dataType:'json',  
					    data:{
					    	"ids":l
					    },
					    type:"post",
					    cache : false,  
					    async : false,  
					    success:function(data){	
					    		if(data.status == "y" || data.status == "Y"){
					    			layer.msg(data.info,{icon: 1,time:1000});
					    			loadData();	
					    		}else{
					    			layer.msg(data.info,{icon: 2,time:2500});
					    		}	    					         				           
					                		           
					       },  
					     error : function(error) {  
					            alert(error); 		            
					       } 			   			
			   		})
				});
	    	   
		   }else{
		     layer.msg('无选中的项!',{icon: 0,time:1000});
		   }
			
		}
	

	//提交删除操作
	function sumbitDel(ids) {
		
		$.ajax({
			url : path + "/manuscriptController/delManuscript.do",
			traditional : true,
			dataType : 'json',
			data : {
				"ids":ids,
			},
			type : "post",
			cache : false,
			async : false,
			success : function(data) {		
				loadData();			
				if (data.status == "y" || data.status == "Y") {
					layer.msg(data.info, {
						icon : 1,
						time : 1000
					});
				} else {
					layer.msg(data.info, {
						icon : 2,
						time : 2500
					});
				}

			},
			error : function(xhr,error) {
				alert(error);
			}
		});

	}
	
	
	function goSearch(){
		loadData();
	}
