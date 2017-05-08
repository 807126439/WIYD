var setting = {
			check: {
				enable: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: zTreeOnClick
			}
		};

		

	
	function expandNode(obj,type){
		var zTree = $.fn.zTree.getZTreeObj("tree");	
		if (type == "expandAll") {
			zTree.expandAll(true);
		} else if (type == "collapseAll") {
			zTree.expandAll(false);
		}
	}
	
	
	function zTreeOnClick(event, treeId, treeNode) {
		   
		    $("#parId").val(treeNode.id);
			$("#parName").val(treeNode.name);	
	}
	
	function resetCurrForm(){
		$("#auth-name").val("");
		$("#auth-code").val("");
		$("#auth-url").val("");
		$("#auth-icon").val("");
		$("#auth-order").val("");
		
	
	   $('input[name="flag"]:checked').iCheck('uncheck');
	   $('input[name="authType"]:checked').iCheck('uncheck');
	
	   $(".Validform_checktip").removeClass("Validform_wrong Validform_right Validform_loading");
	}
	
	
	
	
	/*添加树节点*/
	function addNodeItem(id,name){
		var le = 0;
		var par = $("#parId").val();
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var parNode = null;
		var isParent = false;
		if(par){
			parNode = treeObj.getNodeByParam("id", par, null);
			le = parNode.level + 1;			
		}else{
			par = null;
			isParent = true;
		}
						
		var newNode = {"id":id,"isParent":isParent,"level":le,"name":name,"pid":par};
	    treeObj.addNodes(parNode, newNode);
		
	}
	
	function setParNull(){
		$("#parId").val("");
		$("#parName").val("");
	}
	
	
	$(document).ready(function(){
		$.fn.zTree.init($("#tree"), setting, zNodes);	
	});