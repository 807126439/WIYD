var userAgent = window.navigator.userAgent.toLowerCase();
$.browser.msie8 = $.browser.msie && /msie 8\.0/i.test(userAgent);
$.browser.msie7 = $.browser.msie && /msie 7\.0/i.test(userAgent);
$.browser.msie6 = !$.browser.msie8 && !$.browser.msie7 && $.browser.msie
		&& /msie 6\.0/i.test(userAgent);
function load() {
	var roleId = $("#roleId").val();
	var sendUrl = "authController/getTreeByRole.do";
	if(null != roleId && "" != roleId){
		sendUrl += "?rid=" + roleId;
	}
	
	var o = {
		url : sendUrl,
		
	};
	$("#tree").treeview(o);
}
if ($.browser.msie6) {
	load();
} else {
	$(document).ready(load);
}



function getSelNodes(){
	return $("#tree").getCheckedNodes();

}