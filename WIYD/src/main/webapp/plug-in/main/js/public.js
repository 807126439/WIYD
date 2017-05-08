var tempName = window.document.location.pathname;
var path = tempName.substring(0,tempName.substr(1).indexOf('/')+1);








//标签页切换（类名切换）
//s1为tag父容器，active1当前tag的类名，s2为面板父容器(可选)，active2当前面板的类名
function tabPage(s1,active1,s2,active2,callback){
if ($(s1).length===0) {
  return;
}
var $tabcontainer=$(s1);
var $pagecontainer=$(s2);
$tabcontainer.not(function(){ return $(this).children('.'+active1).length!==0 }).children(":first-child").addClass(active1);
$pagecontainer.not(function(){ return $(this).children('.'+active2).length!==0 }).children(":first-child").addClass(active2);
$tabcontainer.children().click(function(){
  $(this).addClass(active1).siblings().removeClass(active1);
  $pagecontainer.children(":nth-child("+($(this).index()+1)+")").addClass(active2).siblings().removeClass(active2);
  if(typeof(eval(callback)) == "function"){
	  callback();
  }
});
}




//禁用Enter键表单自动提交
document.onkeydown = function(event) {
    var target, code, tag;
    if (!event) {
        event = window.event; //针对ie浏览器
        target = event.srcElement;
        code = event.keyCode;
        if (code == 13) {
            tag = target.tagName;
            if (tag == "TEXTAREA") { return true; }
            else { return false; }
        }
    }
    else {
        target = event.target; //针对遵循w3c标准的浏览器，如Firefox
        code = event.keyCode;
        if (code == 13) {
            tag = target.tagName;
            if (tag == "INPUT") { return false; }
            else { return true; }
        }
    }
};


function logout(){	   
	
	layer.confirm("您确定要退出系统吗?",function(index){
		window.parent.location.href = path+"/j_spring_security_logout";	
	});
}
  
  

/*绑定表单*/
function bindForm(formId,action,t,end){
   if(action){
	 $("#"+formId).attr("action",action);
   }
   
	
   $("#"+formId).Validform({
		tiptype:t,
		ajaxPost:true,//ajax方式提交表单数据		
		callback:function(data){
			if(data.status == "y" || data.status == "Y"){				
			      setTimeout(function(){
				    $("#Validform_msg").hide();			 
					layer.closeAll('page');
					end();     					      
			      },1000); 					
			}
			
		}
	}); 

}


function sumbitForm(formId){
	$("#"+formId).submit();
}






/*弹出层*/
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
function layer_show(title,url,w,h,maxmin){
	if (title == null || title == '') {
		title=false;
	};
	if (url == null || url == '') {
		url="404.html";
	};
	if (w == null || w == '') {
		w=($(window).width() - 50);
	};
	if (h == null || h == '') {
		h=($(window).height() - 50);
	};
	layer.open({
		type: 2,
		area: [w+'px', h +'px'],
		fix: false, //不固定
		maxmin: maxmin,
		shade:0.4,
		title: title,
		content: url
	});
}

function layer_full(title,url,w,h,maxmin){
	if (title == null || title == '') {
		title=false;
	};
	if (url == null || url == '') {
		url="404.html";
	};
	if (w == null || w == '') {
		w=800;
	};
	if (h == null || h == '') {
		h=($(window).height() - 50);
	};
	 
	var index = layer.open({
		type: 2,
		area: [w+'px', h +'px'],
		fix: false, //不固定
		maxmin: maxmin,
		shade:0.4,
		title: title,
		content: url
	});
	layer.full(index);
	
}


function layer_full_end(title,url,w,h,maxmin,endCallBack){
	if (title == null || title == '') {
		title=false;
	};
	if (url == null || url == '') {
		url="404.html";
	};
	if (w == null || w == '') {
		w=800;
	};
	if (h == null || h == '') {
		h=($(window).height() - 50);
	};
	 
	var index = layer.open({
		type: 2,
		area: [w+'px', h +'px'],
		fix: false, //不固定
		maxmin: maxmin,
		shade:0.4,
		title: title,
		content: url,
 		end: function(){ 
 			endCallBack();
		}
	});
	layer.full(index);
	
}


function layer_show_end(title,url,w,h,maxmin,endCallBack){
 	if (title == null || title == '') {
 		title=false;
 	};
 	if (url == null || url == '') {
 		url="404.html";
 	};
 	if (w == null || w == '') {
 		 w = ($(window).width() - 50);
 	};
 	if (h == null || h == '') {
 		h=($(window).height() - 50);
 	};
 	 
 	layer.open({
 		type: 2,
 		area: [w+'px', h +'px'],
 		fix: false, //不固定
 		maxmin: false,
 		shade:0.4,
 		title: title,
 		content: url,
 		end: function(){ 
 			endCallBack();
		}
 	});
 
 	
 }



/*关闭弹出框口*/
function layer_close(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}


function open_window(url){
	var h = $(window).height() - 50, w=$(window).width() - 100;
	window.open(url,"_blank","height:"+h+"px,width:"+w+"px,scrollbars=yes");
	
}

