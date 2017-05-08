var path = $("#path").val();
var testTime = $("#testTime").val();
var taskId = $("#taskId").val();
//计时开始时间
var starttime,
  //百分计的合格分数线
    passingrate,
  //当前时间
    currenttime,  
  //用时记录
    usesecond,
  //题目总数
    questions,
  //总分
    totalscore,
  //合格分数
    passingscore;


var timeStr;
if(testTime<60){		
	timeStr = "00:"+testTime+":00";
}if(testTime>60){
	var hour = testTime/60;
	var min = time % 60;
	timeStr=hour+":"+min+":00";
}


$(function() {
	
	

  starttime = '01：00：00';
  passingrate = 60;

  usesecond = 0;
  questions = $('.questionlist .question').length;
  totalscore = getTotalScore();
  passingscore = Math.round(totalscore*passingrate/100);

  $('.onlinetest-subtt strong').eq(0).text(questions);
  $('.onlinetest-subtt strong').eq(1).text(totalscore);
  $('.onlinetest-subtt strong').eq(2).text(passingscore);
  $('.onlinetest-subtt strong').eq(3).text(getMinutes(timeStr));

  //生成一个倒计时
  countDown({
    elem: '.onlinetest-controll strong', //时间容器
    connector: '：', //输出结果连接符
    start:timeStr ,//倒计时开始时间
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

});

//选择答案并检测对错
function selectAnswer() {
  if ($('#onlinetest').hasClass('disabled')) {
    return;
  }
  var $quest = $('.question.current'); //当前问题
  var qindex = $quest.index();
  var $navtarget = $('.onlinetest-navlist li').eq(qindex);
  if ($navtarget.hasClass('wrong') || $navtarget.hasClass('right')) {
    return; //若该题目已答则返回
  }
  $(this).toggleClass('selected');
  if ($quest.hasClass('singlechoice') || $quest.hasClass('trueorfalse')) { //单选和判断
    $quest.find('.btn-submit').trigger('click');
  }
}

//ajax发送请求，之后检查对错并统计数据
function sendAnswer() {
  if ($('#onlinetest').hasClass('disabled')) {
    return;
  }
  var $quest = $('.question.current'); //当前问题
  var qindex = $quest.index(); //问题的索引值,为0-19
  var qid = $quest.data('qid'); //问题的识别ID
  var $navtarget = $('.onlinetest-navlist li').eq(qindex);
  if ($navtarget.hasClass('wrong') || $navtarget.hasClass('right')) {
    return; //若该题目已答则返回
  }
  $.ajax({
	  url:path+"/topicController/getTopicDetail.do",
      type:"post",
      data:{id:qid},   
      dataType:"json",
      async: true,
      success: function(data) { 

     
      //答题结果显示
      showResult(data);

      //答题情况数据统计
      testStatistics();

      //保存当前测试
      saveTest();
      

      //全部题目已答时交卷
      if ($('.onlinetest-navlist li').length === $('.onlinetest-navlist .right').length + $('.onlinetest-navlist .wrong').length) {
        $('.btn-their').trigger('click');
        return;
      }

      //计时再开始
      if ($('.btn-pause').hasClass('pausing')) {
        $('.btn-pause').trigger('click');
      }
    }
  });
}
//答题结果显示
function showResult(data) {
  var rightIndex = analysisList = rightText = [];
  

  
//  rightIndex = (data.rightIndex == null) ?[] : data.rightIndex;  
//  analysisList =(data.analysisList == null) ?[] : data.analysisList.split(","); 
//  rightText=(data.rightText == null) ?[] :  data.rightText.split(","); 
  
  
  rightIndex = !data.rightIndex ?[] : data.rightIndex;  
  analysisList =!data.analysisList ?[] : data.analysisList; 
  rightText=!data.rightText ?[] :  data.rightText; 
  
  
  
  var $quest = $('.question.current');
  var qindex = $quest.index(); //问题的索引值,为0-19
  if (!$quest.hasClass('gapfill')) { //选择题的情况
    $quest.find('.question-options li').each(function() {
      if (rightIndex.toString().indexOf($(this).index().toString()) >= 0) {
        if ($(this).hasClass('selected')) {
          $(this).addClass('right');
        } else {
          $(this).addClass('wrong');
        }
      } else if ($(this).hasClass('selected')) {
        $(this).addClass('wrong');
      }
    });
    if ($quest.find('.question-options .wrong').length !== 0) {
      $('.onlinetest-navlist li').eq(qindex).addClass('wrong');
      $quest.append($('<div class="question-result"> <span>回答错误 :(</span><ul class="analysislist"></ul></div>'));
    } else {
      $('.onlinetest-navlist li').eq(qindex).addClass('right');
      $quest.append($('<div class="question-result"><span>恭喜！回答正确 :)</span><ul class="analysislist"></ul></div>'));
    }
    if (analysisList.length !== 0) {
      $quest.find('.question-result').append('<a href="javascript:void(0)">本题分析</a>');
      for (var i = 0; i < analysisList.length; i++) {
        $quest.find('.analysislist').append('<li>' + (analysisList[i].length === 0 ? "无" : analysisList[i]) + '</li>');
      }
    }
  } else if ($quest.hasClass('gapfill')) { //填空题的情况
    $quest.find('.anwsergroup li').each(function(index) {
      if ($(this).find('input').val() === rightText[index]) {
        $(this).addClass('right');
      } else {
        $(this).addClass('wrong');
      }
      $(this).find('input').attr('disabled', 'disabled');
    });
    if ($quest.find('.anwsergroup .wrong').length !== 0) {
      $('.onlinetest-navlist li').eq(qindex).addClass('wrong');
      $quest.append($('<div class="question-result"><span>正确的答案是：' + rightText.join(' ; ') + '</span><ul class="analysislist"></ul></div>'));
    } else {
      $('.onlinetest-navlist li').eq(qindex).addClass('right');
      $quest.append($('<div class="question-result"><span>恭喜！回答正确 :)</span><ul class="analysislist"></ul></div>'));
    }
    if (analysisList.length !== 0) {
      $quest.find('.question-result').append('<a href="javascript:void(0)">本题分析</a>');
      for (var i = 0; i < analysisList.length; i++) {
        $quest.find('.analysislist').append('<li>' + (analysisList[i].length === 0 ? "无" : analysisList[i]) + '</li>');
      }
    }
  }
}

//答案解析弹窗
function analysis() {
	
	
  $quest = $('.question.current');
  /* 弹窗填充*/
  var analysislist = $quest.find('.analysislist li');
  $('#mylayer1 .ct').html('');
  if (analysislist.length === 1 || $quest.hasClass('trueorfalse') || $quest.hasClass('gapfill')) {
    $('#mylayer1 .ct').append($('<p>' + (analysislist.eq(0).text().length === 0 ? "无" : analysislist.eq(0).text()) + '</p>'));
  } else if ($quest.hasClass('singlechoice') || $quest.hasClass('morechoice')) { //选择题填充
    $quest.find('.question-options li').each(function(index) {
      var letter;
      if (index === 0) {
        letter = 'A';
      } else if (index === 1) {
        letter = 'B';
      } else if (index === 2) {
        letter = 'C';
      } else if (index === 3) {
        letter = 'D';
      } else if (index === 4) {
        letter = 'E';
      } else if (index === 5) {
        letter = 'F';
      } else if (index === 6) {
        letter = 'G';
      } else if (index === 7) {
        letter = 'H';
      }
      $('#mylayer1 .ct').append($('<p class="opt">' + letter + '、' + $(this).text() + '</p>'));
      $('#mylayer1 .ct').append($('<p>' + (analysislist.eq(index).text().length === 0 ? "无" : analysislist.eq(index).text()) + '</p>'));
    });
  }
  /* 弹窗填充*/
  var index1 = layer.open({
    type: 1,
    closeBtn: 0,
    shift: 2,
    shadeClose: true,
    title: false,
    area: ['562'],
    content: $("#popup1")
  });
  $('.mylayer-close').off('click').click(function() {
    layer.close(index1);
  });
}

//时间结束考试成绩公布
function testFinish() {
  if ($(this).hasClass('disabled')) {
    return;
  }
  //获得的分数
  var currentscore = getCurrentScore();
  //考试总时间
  var totaltime = getMinutes(timeStr);
  //花费的时间
  var usetime = getHours(usesecond);
  
  
  //上传分数
  uploadScore(currentscore,usetime);

  //终止计时器，终止考试
  if (!$('.btn-pause').hasClass('pausing')) {
    $('.btn-pause').click();
  }
  $('.btn-pause').addClass('disabled');
  $('.btn-their').addClass('disabled');
  $('#onlinetest').addClass('disabled');

  if (currentscore >= passingscore) { //合格
    $('#popup2 .success .right').text(currentscore);
    $('#popup2 .fail').hide();
  } else { //不合格
    $('#popup2 .fail .wrong').text(currentscore);
    $('#popup2 .success').hide();
  }
  $('#popup2 .totalscore').text(totalscore);
  $('#popup2 .totaltime').text(totaltime);
  $('#popup2 .usetime').text(usetime);

  //清除上次考试记录
  if (typeof localStorage!=='undefined'&&typeof localStorage.lasttest!=='undefined') {
    clearTest();
  }

  var index2 = layer.open({
    type: 1,
    closeBtn: 0,
    shift: 0,
    shadeClose: false,
    title: false,
    content: $("#popup2")
  });
  $('.mylayer-close').off('click').click(function() {
    layer.close(index2);
  });
}

//计算总分
function getTotalScore() {
  var result = 0;
  $('.questionlist .question').each(function() {
    result += $(this).data('score');
  });
  return result;
}
//计算当前得到分数
function getCurrentScore() {
  var result = 0;
  $('.onlinetest-navlist .right').each(function() {
    result += $('.questionlist .question').eq($(this).index()).data('score');
  });
  return result;
}
//计算使用时间
function getHours(time){
  var hour=minute=second=seconds=0;
  if (typeof time==='number') {//xx秒
    seconds=time;
    second=seconds%60,
    minute=parseInt(seconds/60%60),
    hour=parseInt(seconds/3600%24);
    return (hour===0?"":(hour+" 小时 "))+(minute===0?"":(minute+" 分钟 "))+(second===0?"":(second+" 秒"));
  }else if (typeof time==='string') {//xx-xx-xx
    var timearr=dateToIntArr(time);
    var i=timearr.length-1;
    if (i>=0) {
      second=timearr[i--];
    }
    if (i>=0) {
      minute=timearr[i--];
    }
    if (i>=0) {
      hour=timearr[i--];
    }
    return (hour===0?"":(hour+" 小时 "))+(minute===0?"":(minute+" 分钟 "))+(second===0?"":(second+" 秒"))
  }
}
function getMinutes(time) {
  var minute=second=seconds=0;
  if (typeof time==='string') {//xx-xx-xx
    var timearr=dateToIntArr(time);
    var i=timearr.length-1;
    if (i>=0) {
      second=timearr[i--];
    }
    if (i>=0) {
      minute=timearr[i--];
    }
    if (i>=0) {
      hour=timearr[i--];
    }
    return (minute===0&&hour===0?"":((minute+hour*60)+" 分钟 "))+(second===0?"":(second+" 秒"))
  }
}

//问题跳转
function goToQuestion(index1) {
  var tarindex; //跳转目标索引,从0开始
  if (typeof index1 === 'number') {
    tarindex = index1;
  } else if ($(this).closest('.onlinetest-navlist').length !== 0 && $(this).index() !== $('.onlinetest-navlist .current').index()) {
    tarindex = $(this).index();
  } else if ($(this).closest('.onlinetest-prev').length !== 0 && $('.onlinetest-navlist .current').index() > 0) {
    tarindex = $('.onlinetest-navlist .current').index() - 1;
  } else if ($(this).closest('.onlinetest-next').length !== 0 && $('.onlinetest-navlist .current').index() < $('.onlinetest-navlist li').length - 1) {
    tarindex = $('.onlinetest-navlist .current').index() + 1;
  } else {
    return; }
  $('.onlinetest-navlist li').removeClass('current').eq(tarindex).addClass('current');
  $('.question').removeClass('current').eq(tarindex).addClass('current');
}

//测试情况统计
function testStatistics() {
	
  $('.onlinetest-statistics em').text($('.onlinetest-navlist .right').length);
  $('.onlinetest-statistics strong').text($('.onlinetest-navlist .wrong').length);
  var cor = $('.onlinetest-navlist .right').length * 100 / ($('.onlinetest-navlist .wrong').length + $('.onlinetest-navlist .right').length);
  $('.onlinetest-statistics .correct').text(isNaN(cor) ? 0 : Math.round(cor * 100) / 100);
  $('.onlinetest-statistics .progress').text(Math.round(($('.onlinetest-navlist .wrong').length + $('.onlinetest-navlist .right').length) * 100 / $('.onlinetest-navlist li').length * 100) / 100);
}


//生成一个计时器
function countDown(obj) {
  var elem = obj.elem; //时间容器
  var connector = obj.connector || ':'; //输出结果连接符
  var start = obj.start; //倒计时开始时间
  var pause = obj.pause || ''; //触发暂停的元素
  var timeEnd = obj.timeEnd || ''; //时间结束时触发
  var timeFollow = obj.timeFollow || ''; //跟随计数器每段时间触发1次
  var rest = start.replace(/[-:：]/g, connector); //倒计时剩余时间
  $(elem).text(rest);
  if (rest === "00:00:00" || rest === "00-00-00" || rest === "00：00：00") return;
  var interval = setInterval(function() {
    var timearr = dateToIntArr(rest),
      len = timearr.length;
    for (var i = 1;; i++) {
      if (timearr[len - i] - 1 >= 0) { timearr[len - i]--;
        break; } else {
        timearr[len - i] += 59;
      }
    }
    var strarr = [];
    for (var j = 0; j < timearr.length; j++) {
      strarr.push(timearr[j].toString().length === 1 ? (0 + timearr[j].toString()) : timearr[j].toString());
    }
    rest = strarr.join(connector);

    $(elem).text(rest);
    usesecond++; //用时统计
    currenttime=rest;
    timeFollow();
    if (rest === "00:00:00" || rest === "00-00-00" || rest === "00：00：00") {
      clearInterval(interval);
      timeEnd();
    }
  }, 1000);
  var pauseAndStart = function() { //开始与暂停事件
    if (!$(this).hasClass('pausing')) {
      clearInterval(interval);
      $(this).addClass('pausing');
    } else {
      if ($(this).hasClass('disabled')) {
        clearInterval(interval);
        $(this).unbind('click', pauseAndStart);
        return;
      }
      $(this).unbind('click', pauseAndStart); //防止重复绑定
      countDown({ elem: elem, connector: connector, start: rest, pause: pause, timeEnd: timeEnd });
      $(this).removeClass('pausing');
    }
  };
  $(pause).bind('click', pauseAndStart);
}
//将xx-xx或者xx:xx或者xx：xx时间格式转成数字数组，最多至年
function dateToIntArr(datetime) {
  var intarr = [];
  if (datetime.indexOf(':') !== -1 && datetime.indexOf('-') === -1 && datetime.indexOf('：') === -1)
    var arr = datetime.split(':');
  else if (datetime.indexOf('-') !== -1 && datetime.indexOf(':') === -1 && datetime.indexOf('：') === -1)
    var arr = datetime.split('-');
  else if (datetime.indexOf('：') !== -1 && datetime.indexOf(':') === -1 && datetime.indexOf('-') === -1)
    var arr = datetime.split('：')
  for (var i = 0; i < arr.length; i++) {
    intarr.push(parseInt(arr[i]));
  }
  return intarr;
}


function uploadScore(currentscore,usetime){	
	$.ajax({
		type:'post',
		data:{		
			taskId:taskId
		},
		url: path+"/examScoreController/checkIsExist.do",
		success:function(data){			
			if(data == "true"){
			$.ajax({
					type:'post',
					data:{		
						taskId:taskId,
						score:currentscore,
						usetime:usetime
					},
					url: path+"/examScoreController/editExamScore.do"		
				})
			}else{
				$.ajax({
					type:'post',
					data:{		
						taskId:taskId,
						score:currentscore,
						usetime:usetime
					},
					url: path+"/examScoreController/addExamScore.do"		
				})
			}
			
		}
	})
		
	
}

