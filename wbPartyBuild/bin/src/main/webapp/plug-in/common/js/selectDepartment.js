		
 //初始化部门下拉选择框
  //使用:1.CSS:导入zzTree.css文件
   //2.容器:创建input容器(ID可变,初始化传入,其他组件ID暂时固定)放选择的部门名称,隐藏input,id=departmentIds存放部门Ids,任意位置创建menuContent容器放部门列表树
   //3.js:导入selectDepartment.js和以下内容.
   //4.查询条件根据情况调整,参考ContentStatDaoImpl
   $(document).ready(function(){
	   initDepartmentSelect("#department");
});


var $wrap;
		function initDepartmentSelect(elem){
			
		
			  if (!elem) return;
			  $wrap = $(elem);
			var setting = {
					
					check: {
						enable: true,
						chkStyle: "checkbox",
						radioType: "all"
					},
					view: {
						dblClickExpand: false
					},
					data: {
						simpleData: {      
							enable: true
						}
					},
					callback: {
						onClick: onClick,
						onCheck: onCheck
					}
				};
			
			var zNodes={};
			//获取节点内容
			$.ajax({
			    url: path+"/departmentController/getMainDepTree.do",
			    traditional:true,
			    dataType:'json',  
			    data:{},
			    type:"post",
			    cache : false,  
			    async : false,  
			    success:function(data){	
			    		if(data.status == "y" || data.status == "Y"){
			    			zNodes=eval(data.backVal);
			    		}else{
			    			alert(data.info);
			    		}	    					         				           
			       },  
			     error : function(error) {  
			            alert(error); 		            
			       } 
		  });
			
		$.fn.zTree.init($("#departmentTree"), setting, zNodes);
			
		}



		

		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("departmentTree");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}

		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("departmentTree"),
			nodes = zTree.getCheckedNodes(true),
			v = "";
			ids="";
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
				ids+=nodes[i].id + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			if (ids.length > 0 ) ids = ids.substring(0, ids.length-1);
			var cityObj = $wrap;
			cityObj.attr("value", v);
			$("#departmentIds").attr("value",ids);
		}

		function showMenu() {
			var cityObj = $wrap;
			var cityOffset = $wrap.offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top  + "px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target == $wrap || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}

