/*tree*/   
	   var isLoadTree = false;
       
       var setting = {
			
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		
	   var storeIds = null;
		
       function content_move(ids){
		   storeIds = ids;
		
		   var curNum = 1;
		   if($.isArray(ids)){
		      curNum = storeIds.length;
		   }
			
		   if(!isLoadTree){
		  	 initColumTree();
		  	 isLoadTree = true;		  			  
		   }
			
		   layer.open({
			  type: 1,
			  title: "选择栏目（已选择"+curNum+"篇文章）",
			  area: ['350px', '450px'],
			  content: $("#hide-tree")
			});	

		 	
		}
	
    	/*初始化栏目树*/
    	function initColumTree(){
    	         	
	    	 $.ajax({
				    url: path+"/columnMuController/getColumTreeNode.do",
				    dataType:'json',  
				    data:{
				    },
				    type:"post",
				    cache : false,  
				    async : false,  
				    success:function(data){	
				    	if(data!=null && data!=undefined){
				    		var zNodes = new Array();
				    		
				    		for(var i=0;i<data.length;i++){
				    		
				    			zNodes.push({id:data[i].id, pId:data[i].pid, name:data[i].name, isParent:data[i].isParent,open:data[i].open});
				    		
				    		}
				    	
				    		$.fn.zTree.init($("#tree"), setting, zNodes);
				    	
				    	}		
				    			    					         				           
				                		           
				    },  
				    error : function(error) {  
				          alert(error); 		            
				    } 
			  });
	
    	}
    	
    		    	
    	
	function content_move_one(id){	   			
		   content_move(id);			
	}
	
	
	function dataMove(){
	   var l = new Array();
	         
       $("#content_table .checkbox:checked").each(function() {		
		  l.push($(this).val());		
	   });
	   	 
	   if(l.length > 0){
		  
		   	content_move(l);
			
	   }else{
	     layer.msg('无选中的项!',{icon: 0,time:1000});
	   }
	}
	
	
	
	function sumbitMove(){

		var colId = getCurrentVal();
		if(colId == -1){
			layer.msg('请选择栏目，然后确定移动!',{icon: 0,time:1500});
			return;
		}
	
		$.ajax({
			    url: path+"/contentController/changeColumn.do",
			    traditional:true,
			    dataType:'json',  
			    data:{
			    	"ids":storeIds,
			    	"colId":colId
			    },
			    type:"post",
			    cache : false,  
			    async : false,  
			    success:function(data){
			    	
			    		if(data.status == "y" || data.status == "Y"){
			    			layer.msg(data.info,{icon: 1,time:1000});
			          		goSearch();	
		          			layer.closeAll('page');
			    		}else{
			    			layer.msg(data.info,{icon: 2,time:2500});
			    		}	    					         				           
			                		           
			       },  
			     error : function(error) {  
			            alert(error); 		            
			       } 
		  });
		
	
	}
    
     /*获取栏目树当前选中的节点*/
     function getCurrentVal(){
	  	var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getSelectedNodes();
		if(nodes.length>0){
			if(nodes.length == 1){
			  return nodes[0].id;
			}else{
			 alert("eror");	
			 return -1;
			}
		
		}else{
		   return -1;
		}
	
	  }	   