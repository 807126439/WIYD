<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>城建局智慧党建</title>
  <link href="${path}/plug-in/web/portals/css/common/reset.css" rel="stylesheet" type="text/css" />
  <link href="${path}/plug-in/h-ui/lib/laypage/1.2/skin/laypage.css" rel="stylesheet" type="text/css" />
  <link href="${path}/plug-in/web/portals/css/common/common.css" rel="stylesheet" type="text/css" />
  <link href="${path}/plug-in/web/portals/css/common/base.css" rel="stylesheet" type="text/css" />
  <link href="${path}/plug-in/web/portals/css/search/searchpage.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <#include "/common/head.ftl"/>
    <input type="hidden" id="path" name="path" value="${path}">
	<input type="hidden" id="totalRecord" name="totalRecord" value="${result.recTotal!0}">
  <!-- navBer -->
  <div class="container" class="clearfix">
    <div class="mainplaceholder"></div>
    <div id="main" class="clearfix">
      <div class="searchpage clearfix">
        <div class="s_bar clearfix">
          <input type="text" id="searchTitle" class="s_ipt" placeholder="请输入搜索内容" value="${title!''}" >
          <input type="hidden" id="lockTitle" name="lockTitle" value="">
          <input type="hidden" id="days" name="days" value=0>
          <input type="hidden" id="curPage" name="curPage" value=1>
          <a href="javascript:void(0)" class="s_btn"></a>
        </div>
        <div class="s_rslist" style="float:left">
          <div class="tt"><em>搜</em>索结果<span class="bc">${result.recTotal!0}</span></div>
          <ul class="rslist">
          <#if result.list?size gt 0>
          <#list result.list as content>
          	<li class="rs">
              <div class="rs-c">
                <a href="${content.linkUrl!'http://localhost:8080/wbPartyBuild/subject/'+content.colId+'.htm'}">${content.title}</a>
              </div>
            </li class="rs">
          </#list>
          <#else>
          	<div class='tt'>没有搜索到相关内容</div>
          </#if>
          </ul>
          <div id="page1" class="page1" <#if !(result.list?size gt 0)>style="display: none;"</#if>></div>
        </div>
      </div>
    </div>
  </div>
  <!--container-->
  		<#include "/common/footer.ftl"/>
  <!-- footer-p -->
  <script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript" src="${path}/plug-in/h-ui/lib/laypage/1.2/laypage.js"></script>
  <script type="text/javascript">
  	var path=$("#path").val();
	$(function() {
		$("#lockTitle").val($("#searchTitle").val());
		sortPage();
	});
	function sortPage(){
	if($("#totalRecord").val()>0){
		$("#page1").show();
	}
	  
      var pages=Math.ceil($("#totalRecord").val()/20);
      var curpage=$("#curPage").val();
      laypage({
         cont: 'page1',
         pages: pages,
         groups: 10, //连续显示分页数
         curr: curpage,//当前页
         first:false,
         last:false,
         prev:"<上一页",
         next:"下一页>",
         skin:'#b40100',
         jump: function(obj, first){ //触发分页后的回调
                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                	$("#curPage").val(obj.curr);
                    search();
                }
                }
      })
      }
      $('.s_time .sortway li').on('click',function() {//时间排序
        $('.s_time .sortway li').removeClass('active');
        $(this).addClass('active');
        $("#days").val($(this).val());
        //将当前页面切换回第一页
      	$("#curPage").val(1);
        search();
      });
      
      $(".s_btn").on('click',function(){
      	//锁定搜索内容，防止换页页数与输入框内容不符
      	$("#lockTitle").val($("#searchTitle").val());
      	//将当前页面切换回第一页
      	$("#curPage").val(1);
      	search();
      })
      
      
      function search(){
      	var title=$("#lockTitle").val();
      	var days=$("#days").val();
      	var curPage=$("#curPage").val();
      	$.ajax({
      		 type: "POST",
      		 url: path+"/searchColumn.do",
      		 data: {"title":title,"curPage":curPage},
      		 dataType: "json",
      		 cache : false,  
			async : false,  
			success:function(data){	
				    		if(data.status == "y" || data.status == "Y"){
				    			clearTable();//清空以往内容
				    			printTable(data.backVal);//加载数据内容			    			
				    		}else{
				    			layer.msg(data.info,{icon: 2,time:2500});
				    		}	    					         				           
				                		           
				       },  
			 error : function(error) {  
				            alert(error); 		            
				       }
      	});
      }
      function clearTable(){
      	$(".rslist").empty();
      }
      
      
      function printTable(page){
      
      $("#totalRecord").val(page.recTotal);
      $(".bc").html(page.recTotal);
      if(page.list.length>0){
      	$ul=$(".rslist");
      	var uri
      	for(var i=0;i<page.list.length;i++){
      		 var li="<li class='rs'>";
             li+="<div class='rs-c'>";
             if(page.list[i].linkUrl==null||page.list[i].linkUrl==""){
             	uri='http://localhost:8080/wbPartyBuild/subject/'+page.list[i].colId+'.htm';
             }else{
             	uri=page.list[i].linkUrl;
             }
             li+="<a href="+uri+">"+page.list[i].title+"</a>";
             li+="</div>";
             li+="</li class='rs'>";
             $li=$(li);
             $ul.append($li);
      	}
      	sortPage();
      	}else{
      		$ul=$(".rslist");
      		var li="<div class='tt'>没有搜索到相关内容</div>";
      		$li=$(li);
      		$ul.append($li);
      		$("#page1").hide();
      	}
      }
  </script>
</body>

</html>
