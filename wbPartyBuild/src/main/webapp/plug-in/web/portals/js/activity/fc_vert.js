$(function() {
	$('.fc_dpm-node').click(toggleinfo);
});

//显示,隐藏职位列表
function toggleinfo() {
	$(this).parent().find('.fc_dpm-node-info').slideToggle();
	$(this).parent('.fc_dpm-nodewrapper').toggleClass('active');
}