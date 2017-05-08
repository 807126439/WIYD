<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


String processData = (String )request.getAttribute("processData");
if(processData!=null){
	out.print("<script type='text/javascript'> var processData = "+processData+";</script>");
}


%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">
  <head>
    <base href="<%=basePath%>" />
    
    <!--[if lt IE 9]>
		<?import namespace="v" implementation="#default#VML" ?>
	<![endif]-->
	
	<link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/work-flow/design/libs/gooFlow/codebase/GooFlow2.css" />
		
	<style>
	.myForm{display:block;margin:0px;padding:0px;line-height:1.5;border:#ccc 1px solid;font: 12px Arial, Helvetica, sans-serif;margin:5px 5px 0px 0px;border-radius:4px;}
	.myForm .form_title{background:#428bca;padding:4px;color:#fff;border-radius:3px 3px 0px 0px;}
	.myForm .form_content{padding:4px;background:#fff;}
	.myForm .form_content table{border:0px}
	.myForm .form_content table td{border:0px}
	.myForm .form_content table td input{width: 100%}
	.myForm .form_content table .th{text-align:right;font-weight:bold}
	.myForm .form_btn_div{text-align:center;border-top:#ccc 1px solid;background:#f5f5f5;padding:4px;border-radius:0px 0px 3px 3px;} 
	#propertyForm{float:left;width:300px}
	</style>
	
	<script type="text/javascript" src="<%=path %>/plug-in/work-flow/design/libs/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/plug-in/work-flow/design/libs/GooFunc.js"></script>
	<script type="text/javascript" src="<%=path %>/plug-in/work-flow/design/libs/json2.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=path %>/plug-in/work-flow/design/libs/default.css"/>
	
	<script type="text/javascript" src="<%=path %>/plug-in/work-flow/design/libs/gooFlow/codebase/GooFlow.js"></script>
	<script type="text/javascript" src="<%=path %>/plug-in/work-flow/design/libs/gooFlow/codebase/GooFlow_color.js"></script>
	<script type="text/javascript" src="<%=path %>/plug-in/h-ui/lib/layer/2.4/layer.js"></script> 
	
	<script type="text/javascript">
	var property={
		width:$(window).width()-320 ,
		height:$(window).height(),
		toolBtns:["start","end","task","node","chat","state","plug","join","fork","complex mix"],
		haveHead:true,
		headBtns:["save","undo","redo","reload"],//如果haveHead=true，则定义HEAD区的按钮
		haveTool:true,
		haveGroup:true,
		useOperStack:true
	};
	var remark={
		cursor:"选择指针",
		direct:"结点连线",
		start:"入口结点",
		end:"结束结点",
		task:"任务结点",
		node:"自动结点",
		chat:"申请者任务",
		state:"状态结点",
		plug:"附加结点",
		fork:"分支结点",
		join:"联合结点",
		complex:"复合结点",
		group:"组织划分框编辑开关"
	};
	var demo;
	$(document).ready(function(){
	
		demo=$.createGooFlow($("#demo"),property);
		demo.setNodeRemarks(remark);
		demo.onItemDel=function(id,type){
		
			layer.confirm('确定要删除该单元吗?',function(index){
				  this.blurItem();
			});		
		
		};
		
		if(typeof(processData)!= "undefined"){
			if(processData){
				demo.loadData(processData);
			}
		}
		
	
		demo.onItemFocus=function(id,model){ 
	      var obj;
	      $("#ele_model").val(model);
	      $("#ele_id").val(id);
	     
	      if(model=="line"){
	        $(".form_content .node-item").hide();
	        obj=this.$lineData[id];
	        $("#ele_name").val(obj.name);
	        $("#ele_type").val(obj.type);
	
	        
	      }else if(model=="node"){
	        $(".form_content .node-item").show();
	        obj=this.$nodeData[id];
	        
	        $("#ele_name").val(obj.name);
	        $("#ele_type").val(obj.type);
	        $("#ele_des").val(obj.des);
	        $("#ele_apply").val(obj.applyNeed);
	        $("#ele_handle").val(obj.handleNeed);
	        $("#ele_duty_person").val(obj.dutyPerson?obj.dutyPerson.join(","):"");
	        $("#ele_duty_role").val(obj.dutyRole?obj.dutyRole.join(","):"");
	        
	      }
	   
	      return true;
		};
		demo.onItemBlur=function(id,model){
	    document.getElementById("propertyForm").reset();
	    return true;
		};
		
		demo.onBtnSaveClick=function(){
		
			layer.confirm('确认要保存吗？',function(index){
				saveData();
			});
		
		
		};
	
	});

	function saveData(){
	
	    var data = JSON.stringify(demo.exportData());
	    /*document.getElementById("result").value= data;*/
	    $.ajax({
			    url: "<%=path%>/procNode/addProcess.do",
			    dataType:'json',  
			    data:{
			    "procDefinId":$("#procDefinIdId").val(),
			    "data":data
			    },
			    type:"post",
			    cache : false,  
			    async : false,  
			    success:function(data){	
			    		if(data.status == "y" || data.status == "Y"){
			    			layer.msg(data.info,{icon: 1,time:1000});
			    
			    		}else{
			    			layer.msg(data.info,{icon: 2,time:2500});
			    		}	    					         				           
			                		           
			       },  
			     error : function(error) {  
			            alert(error); 		            
			       } 
		  });
	    
	}
	
	

	
	function updateNode(){
		var id = $("#ele_id").val();
		var model = $("#ele_model").val();
		
		if(model=="line"){
		  var obj = demo.$lineData[id];
		  var ja = {
		  	 from:obj.from,
		  	 to:obj.to,
		  	 name:$("#ele_name").val(),			 			 
			 type:$("#ele_type").val(),
			 };
		 
		  demo.setName(id,$("#ele_name").val(),"line");
		  demo.$lineData[id]=ja;
		  
		}else if(model=="node"){
		  var obj = demo.$nodeData[id];
		
		  var dutyPersons = $("#ele_duty_person").val(); 
		  var dutyRoles =$("#ele_duty_role").val();

		  var pa = [];
		  if(pa){
		  	pa = dutyPersons.split(",");
		  }
		 var ra =[];
		 if(ra){
		  	ra = dutyRoles.split(",");
		  }


		  var ja = {
		  	 name:$("#ele_name").val(),			 			 
			 type:$("#ele_type").val(),
			 des:$("#ele_des").val(),
			 left:obj.left,
			 top:obj.top,
			 width:obj.width,
			 height:obj.height,
			 dutyPerson:pa,
			 dutyRole :ra
			 };
		 
		 
		
			 demo.setName(id,$("#ele_name").val(),"node");
		 
			 	 demo.$nodeData[id]=ja;
		 
		 
		}

		 
	}
	
	

	
	</script>
	</head>
	<body style="background:#EEEEEE">
	<div id="demo" style="margin:5px;float:left"></div>
	
	<form class="myForm" id="propertyForm">
		<div class="form_title">属性设置</div>
		<div class="form_content">
		  <table>
		    <tr><td class="th">节点id：</td><td><input type="text"  id="ele_id"/></td></tr>
		    <tr><td class="th">节点名称：</td><td><input type="text"  id="ele_name"/></td></tr>
		    <tr><td class="th">节点类型：</td><td><input type="text"  id="ele_type" /></td></tr>  
		    <tr><td class="th">组件类型：</td><td><input type="text"  id="ele_model" /></td></tr> 
		    <tr class="node-item"><td class="th">描述：</td><td><textarea id="ele_des" style="height: 80px;"></textarea></td></tr>		 
		    <tr class="node-item"><td class="th">责任人：</td><td><input type="text"  id="ele_duty_person"/></td></tr>
		    <tr class="node-item"><td class="th">责任角色：</td><td><input type="text"  id="ele_duty_role"/></td></tr>
		  </table>
		
		</div>
		<div class="form_btn_div">
		  <input type="reset" value="重置"/>
		  <input type="button" value="确定" onclick="updateNode()"/>
		</div>
	</form>
	
	

	
	<input type="hidden" id="procDefinIdId" value="${procDefinIdId}"/>
	
	
	
	</body>
	</html>