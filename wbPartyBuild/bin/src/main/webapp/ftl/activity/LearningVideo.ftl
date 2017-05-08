<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<link rel="stylesheet" type="text/css" href="${path}/plug-in/web/portals/css/study/studydata.css">
	<link rel="stylesheet" type="text/css" href="${path}/plug-in/h-ui/static/h-ui/css/H-ui.css">
	<#include "/activity/common/activity-head.ftl"/>
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
		       
  	 <form action="${path}/studyDataController/downloadStudyData.do?id=${sdDTO.sdId}" method="post">
   
        <div class="mod-content">
          <div class="learningtask">
          <input type="hidden" value="${sdDTO.sdId}" />
          <input type="hidden" value="${sdDTO.fileKind}" id="fileKind"/>
            <h1>学习资料</h1>
            <div id="liebiao">
	           		<p><span class='red'>资料编号：</span>${sdDTO.dataNum}</p>
		            <p><span class='red'>所属类别：</span>${sdDTO.cateName}</p>
		            <p><span class='red'>创建时间：</span>${sdDTO.createTime}</p>
		            <p><span class='red'>资料名称：</span>${sdDTO.dataName}</p>
		            <p><span class='red'>资料备注：</span>${sdDTO.dataMemo}</p>
		            <p><span class='red'>文件名称：</span>${sdDTO.filename}</p>
		            
		                  
		                  
		     <div id="video">            
			 
		    </div>          
		                  
		                  
		                  
		                  
             </div>
          </div>
          <div style="margin-top: 30px;">
	          <input type="button" onclick="history.go(-1)"  class="btn btn-primary size-xl radius " value="返回上一页"/>&nbsp;&nbsp;
			  <input type="submit"  class="btn btn-primary size-xl radius " value="下载资料"/>
          </div>
   	</form>
          
        </div>
      </div>
    </div>
  </div>
  <input type="hidden" value="${mPath}" id="mPath"> 
  <!--container-->
  <#include "/common/footer.ftl"/>
  <!-- footer-p -->
  <script type="text/javascript" src="${path}/plug-in/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript" src="${path}/plug-in/h-ui/lib/ckplayer/ckplayer.js" charset="utf-8"></script>
  <script type="text/javascript" src="${path}/plug-in/web/portals/js/activity/LearningVideo.js"></script>
  <script type="text/javascript">
    $(function(){
      $('.sidemenu-tt .icon_fold').click(toggleMenu);
      $('.sidemenu-item').click(addActive);
    });
    //伸缩菜单
    function toggleMenu(){
      $(this).closest('.sidemenu-menu').delay().toggleClass('active');
    }
    function addActive(){
      $('.sidemenu-item').removeClass('active');
      $(this).addClass('active');
    }
    
	var fileKind=$("#fileKind").val();
	if (fileKind==2) {
		   $("#video").append(		                    
                "<div class='article-content'> "+
	            "<center><div id='play' ></div></center>"+
	          	"<div id='a1'></div> "+
				"<div id='nowTime'></div> "+
	          	"</div>"  
           );
	}else{
		
	}
	
	
			var flashvars={
			    f:$("#mPath").val(),
			    c:0,
			    p:2,
			    b:0,
			    loaded:'loadedHandler'
			};

			var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
			CKobject.embedSWF('../plug-in/h-ui/lib/ckplayer/ckplayer.swf','a1','ckplayer_a1','600','400',flashvars,params);	
  </script>
</body>

</html>
