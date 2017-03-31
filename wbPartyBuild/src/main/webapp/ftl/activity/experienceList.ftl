<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<#assign ctTotalPage=pageResult.getTotalPage()>
<html >
  <head>

  	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link href="${path}/plug-in/web/portals/css/common/reset.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/common/common.css" rel="stylesheet" type="text/css" />
	<link href="${path}/plug-in/web/portals/css/common/base.css" rel="stylesheet" type="text/css" />	
    <link href="${path}/plug-in/web/portals/css/activity/contribute.css" rel="stylesheet" type="text/css" />
    <link href="${path}/plug-in/h-ui/static/h-ui/css/H-ui.css" rel="stylesheet" type="text/css"/>
  </head>
<body>
	<#include "/common/head.ftl"/>
	<div class="container" class="clearfix">
    <div class="mainplaceholder"></div>
    <div id="main" class="clearfix">
    
      
        <#--左菜单-->
	    	<#include "/activity/common/leftMenu.ftl"/>
		      <div class="mod">
	      	   <#--导航条-->
		       <#include "/activity/common/nav.ftl"/>
      
      
      
        <div class="mod-content">
         
           <div style="margin-bottom: 30px;margin-top: 30px;margin-right: 20px;float:right;">
     		<a  class="btn btn-danger radius" href="javascript:;" onclick="feelingRecord_add('添加心得体会','','')""  >添加心得体会</a>
     		</div>
       
          <table class="table-default table1" style="margin-bottom: 30px;">
            <thead>
              <tr>
               	<th style="width:15%;">编号</th>
                <th style="width:45%;">标题</th>
                <th style="width:20%;">创建时间</th>
                <th style="width:20%;">操作</th>
              </tr>
            </thead>
             <tbody>
            	<#list pageResult.getList() as List>
            		<tr 
            		<#if List_index%2=0>
            			class="even"
            		</#if>>
            			<td style="height:60px;width:15%;">${List.sort}</td>
            			<td style="height:60px;width:45%;">${List.title}</td>
                		<td style="height:60px;width:25%;">${List.createTime}</td>
                		<td style="height:60px;width:25%;"><a href="javascript:;" style="color:red;" onclick="feelingRecord_edit('${List.id}')">编辑</a></td>
                	<#else>
                		<td colspan="4" class="contribute-tt">暂无心得体会</td>
                <td>

                </td>
         
              		</tr>
            	</#list>
            </tbody>
          </table>
      <div class="footerbar clearfix">
          
            <div class="paging" id="page">
         		    
            </div> 
        </div>
      <!-- mod -->
    </div>
  </div>
  
</div>	
</div>	
	<#include "/common/footer.ftl"/>	
	
    <input id="ctTotalPage" value="${ctTotalPage!'-1'}" type="hidden"/>
    <input id="ctCurPage" value="${pageResult.currentPage!'-1'}" type="hidden"/>
    <input id="path" value="${path}" type="hidden"/>
    <input id="colId" value="${result.currColumn.colId}" type="hidden"/>


	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script> 
	<script type="text/javascript" src="${path}/plug-in/h-ui/lib/layer/2.1/layer.js"></script> 
	<script type="text/javascript">
	function feelingRecord_add(title,w,h){
		var path=$("#path").val();
		var url = path+"/feelingRecordController.do?add";
		
		layer.open({
				type: 2,
				area: ['900px', '600px'],
				fix: false,
				shade:0.4,
				title: title,
				content: url,
				skin: 'mystyle',
				 end: function () {
                location.reload();
                }
		 });
	}
	
	function feelingRecord_edit(id){
		var path=$("#path").val();
		var url = path+"/feelingRecordController.do?edit&id="+id;
		layer.open({
				type: 2,
				area: ['900px', '600px'],
				fix: false,
				shade:0.4,
				title: "编辑心得体会",
				content: url,
				skin: 'mystyle',
				 end: function () {
                location.reload();
            }
		 });
		
	}
	
	
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
					$("#page").hide();
				}else{
					$("#page").show();
				}
			}
			
			
		}
		
		
		
	});
	
	function queryPage(totalPage){
		
		laypage({
			     cont: 'page', //容器。值支持id名、原生dom对象，jquery对象,
		   		 pages: totalPage, //总页数
		   		 skin: '#AF0000', //加载内置皮肤，也可以直接赋值16进制颜色值，如:#c00
		   		 skip: true, //是否开启跳页
		   		 groups: 3, //连续显示分页数
				 jump: function(obj){
				   
				   if(!isFirst){
				   		$.ajax({
				        	type:'post',
				        	url:path+"/feelingRecord/getExperiencePage.do",
				        	data:{
				        		id:colId,
				        		curPage:obj.curr
				        	},
				        	dataType: "json",
				        	success:function(data) {
				        	
				        	  $("tbody ").empty();
				        	  
				        		$.each(data.list,function(i, item) {
				        			var classs;
				        			if(i%2==0){
				        				classs='class="even"';
				        			}else{
				        				classs='';
				        			}
				        			 $("tbody ").append("<tr "+classs+">"+
				        			 "<td style='height:60px;width:15%;'>"+item.sort+"</td>"+
				        			 "<td style='height:60px;width:45%;'>"+item.title+"</td>"+
				                	 "<td style='height:60px;width:25%;'>"+item.createTime+"</td>"+
				                	 "<td style='height:60px;width:25%;'><a href='javascript:;' style='color:red;' onclick=\"feelingRecord_edit('"+item.id+"')\">编辑</a></td></tr>");

						      });
				        	}
				       });	
					}					
					isFirst = false;	        
			    }
		});
	}
	
	</script>

</body>
</html>