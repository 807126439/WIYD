$(function() {
	if (typeof localStorage!=='undefined'&&typeof localStorage.lasttest!=='undefined') {
		if (!$('.btn-pause').hasClass('pausing')) {
			$('.btn-pause').trigger('click');
		}
		var index = layer.open({
	    type: 1,
	    closeBtn: 0,
	    shift: 0,
	    shadeClose: false,
	    title: false,
    	area: ['320px','auto'],
	    content: $("#popup3")
	  });
	  $('#popup3 .mylayer-close').off('click').click(function() {
	  	clearTest();
	    layer.close(index);
			$('.btn-pause').trigger('click');
	  });
	  $('.continuelast').off('click').on('click',loadLastTest);
	}
});

//通过storage加载上次的测试
function loadLastTest() {
	$('#onlinetest').html(localStorage.lasttest);
	if (typeof localStorage.usesecond!=='undefined') {
		usesecond=localStorage.usesecond;
	}
	if (typeof localStorage.currenttime!=='undefined') {
		currenttime=localStorage.currenttime;
	}
	$(this).next('.mylayer-close').trigger('click');

	//重新初始化、绑定事件  

	//生成一个倒计时
  countDown({
    elem: '.onlinetest-controll strong', //时间容器
    connector: '：', //输出结果连接符
    start:currenttime,//倒计时开始时间
    pause: '.btn-pause', //触发暂停的元素
    timeFollow: saveTestTime, //每秒触发一次
    timeEnd: testFinish //时间结束时触发
  });

	  //选择选项
  $('.questionlist').on('click', '.question-options li', selectAnswer);

  //提交本题答案
  $('.questionlist').on('click', '.btn-submit', sendAnswer);

  //交卷
  $('.btn-their').on('click', testFinish);

  //答案解析
  $('.questionlist').on('click', '.question-result a', analysis);

  //问题导航跳转
  $('.onlinetest-navlist li,.onlinetest-prev,.onlinetest-next').click(goToQuestion);

  saveTest();
}
//保存测试
function saveTest() {
	localStorage.setItem('lasttest',$('#onlinetest').html());
}
//保存测试时间
function saveTestTime() {
	if (typeof localStorage!=='undefined') {
		localStorage.setItem('usesecond',usesecond);
		localStorage.setItem('currenttime',currenttime);
	}
}
//删除测试存储
function clearTest() {
	localStorage.clear();
}