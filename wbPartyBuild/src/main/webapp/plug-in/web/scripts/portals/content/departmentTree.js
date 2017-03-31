 var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		

	function initCheckAuths(){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getCheckedNodes(true);
		
		var html="";
		for ( var i = 0; i < nodes.length; i++) {
					
			html+="<input type='hidden' name='authIds' value='"
							+ nodes[i].id + "'/>";
		}

		$("#check_req").html(html);
		
	}
	
	function setCheck() {
		var zTree = $.fn.zTree.getZTreeObj("tree"),
		py = $("#py").prop("checked")? "p":"",
		sy = $("#sy").prop("checked")? "s":"",
		pn = $("#pn").prop("checked")? "p":"",
		sn = $("#sn").prop("checked")? "s":"",
				
		type = { "Y":py + sy, "N":pn + sn};
		
		zTree.setting.check.chkboxType = type;
		
	}
	
	
	function showCheckAttr(){
		layer.open({
			  type: 1,
			  title: "选择勾选属性",
			  area: ['300px', '150px'],
			  content: $("#checkAttrs")
			});
	}
	
	function checkAllNodes(obj){
		   var sg = $(obj).attr("singal");
		   var zTree = $.fn.zTree.getZTreeObj("tree");	
		   if(sg == 0){
		   		$(obj).attr("singal","1");
		   		$(obj).text("反选");
		   		zTree.checkAllNodes(true);
		   }else{
		   		$(obj).attr("singal","0");	
		   		$(obj).text("全选");
		   		zTree.checkAllNodes(false);
		   }	
		 
		
			
		}
	
	$(document).ready(function(){
		$.fn.zTree.init($("#tree"), setting, zNodes);
		setCheck();
		$("#py").bind("change", setCheck);
		$("#sy").bind("change", setCheck);
		$("#pn").bind("change", setCheck);
		$("#sn").bind("change", setCheck);
	});
	
	function setSeeOrg(){
		var seeOrgNames="";
		var seeOrgIds="";
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getCheckedNodes(true);
		for(var i=0;i<nodes.length;i++){
			seeOrgNames+=(nodes[i].name+",");
			seeOrgIds+=(nodes[i].id+",");
		}
		seeOrgNames=seeOrgNames.substring(0, seeOrgNames.length-1);
		seeOrgIds=seeOrgIds.substring(0, seeOrgIds.length-1);
		parent.$("#seeOrgName").val(seeOrgNames);
		parent.$("#seeOrgId").val(seeOrgIds);
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
	
	$(function(){ 
		
		$('.skin-minimal input').iCheck({
			checkboxClass: 'icheckbox-blue',
			radioClass: 'iradio-blue',
			increaseArea: '20%'
		});
	
		
		$("#form-role-edit").Validform({
			tiptype:2,
			ajaxPost:true,//ajax方式提交表单数据
			beforeSubmit:function(curform){//切换name属性
				initCheckAuths();
					
			  	return true; 			  	
			},
		
			callback:function(data){				
				if(data.status == "y" || data.status == "Y"){			
					      setTimeout(function(){
					      	parent.goSearch();
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
							      					      
					      },500); 
					
				}
				
				

				$("#check_req").html("");
				
			}
		});
	});