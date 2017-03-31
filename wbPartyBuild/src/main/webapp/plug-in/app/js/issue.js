var comId = $("#comId").val();
var path = $("#path").val();
var totalPage = $("#totalPage").val();
$(function() {
  var scrollTop = 0; //记录滚动位置
  var myUE = UE.getEditor('editor',{toolbars:[['emotion', 'drafts']],
    autoHeightEnabled: false,
    autoFloatEnabled: true,
    elementPathEnabled: false,
    saveInterval: 5000,
    enableContextMenu: false,
    pasteplain: true
  });
  
  conutComment(1);
  conutVote();
  queryPage(true,false);

  //排序方式
  $('.sort_wrap li').click(sortQuery)

  // 点赞与取消点赞
  $('.maincontainer').on('click', '.issue-digg,.comment-digg,.reply-digg', comDigg);

  //展开回复列表
  $('.commentlist').on('click', '.comment-ft .reply-call', showReply);
  //收起回复列表
  $('.comment-btns').on('click','.back', hideReply);

  //打开评论窗口
  $('.currentissue .reply-call,.comment-btns .reply-call,.issue-commentnum').on('click', showEditor);
  $('.commentlist').on('click', '.reply-ft .reply-call', showEditor);
  //关闭评论窗口
  $('.hide_editor').on('click', hideEditor);

  //排序方式
  function sortQuery(){
    var type = $(this).attr("id");
    if(type == 1){
      queryPage(false,true);
    }if(type == 2){
     queryPage(true,false);
    }
    $(this).addClass('active').siblings().removeClass('active');
  }

  //点赞与取消点赞
  function comDigg() {
   var data = {},
      url = '';
    if ($(this).hasClass('issue-digg')) {
    	url = path+"/communController/editCommun.do";  	
    	if($(this).hasClass('haddigg')) {
    	  //互动议题取消点赞
    	  data = {id:comId,type:4};    
	    }else{
	      //互动议题点赞
    	  data = {id:comId,type:3};
	    }
    }else if ($(this).hasClass('comment-digg')) {
    	url = path+"/commentController/editComment.do";
    	var commentId = $(this).closest('.comment').data('itemid');   	
	    if($(this).hasClass('haddigg')) {	    	  
	   	   //评论取消点赞
	   	     data = {id:commentId,type:3};  
		    }else {
		     //评论议题点赞
		     data = {id:commentId,type:2};
	 	    }
    }else if ($(this).hasClass('reply-digg')) {
    	var commentId = $(this).closest('.reply').data('itemid');   
    	url = path+"/commentController/editComment.do";
	    if($(this).hasClass('haddigg')) {	    	  
	   	   //评论取消点赞
	   	     data = {id:commentId,type:3};  
		    }else {
		      //评论议题点赞
		     data = {id:commentId,type:2};
	 	    }
    }       
    var el = this;
    $.ajax({
      type: 'post',
      url: url,
      data: data,
      success: function() {
        $(el).toggleClass('haddigg');
        if ($(el).hasClass('haddigg'))
          $(el).find('.digg-num').text(parseInt($(el).find('.digg-num').text()) + 1);
        else $(el).find('.digg-num').text(parseInt($(el).find('.digg-num').text()) - 1);
      },
      error: function(e) {
        if (e.status === 404 || e.status === '404') {
          layer.msg("网络异常");
        } else {
          layer.msg("服务器异常");
        }

      }
    });
  }

  //展开回复列表
  function showReply() {
    var $comment = $(this).closest('.comment');
    var offsetTop = $comment.offset().top;
    scrollTop = $('body').scrollTop() > 0 ? $('body').scrollTop() : $('html').scrollTop();

    $comment.css({
      "-webkit-transform": "translateY(" + (offsetTop - scrollTop) + "px)",
      "-ms-transform": "translateY(" + (offsetTop - scrollTop) + "px)",
      "-o-transform": "translateY(" + (offsetTop - scrollTop) + "px)",
      "transform": "translateY(" + (offsetTop - scrollTop) + "px)"
    }).addClass('opened').scrollTop(0);
    setTimeout(function() {
      $comment.css({
        "-webkit-transition": "-webkit-transform .3s linear",
        "-o-transition": "-webkit-transform .3s linear",
        " transition": "-webkit-transform .3s linear",
        "-webkit-transition": "transform .3s linear",
        "-o-transition": "transform .3s linear",
        "transition": "transform .3s linear",
        "-webkit-transform": "translateY(0)",
        "-ms-transform": "translateY(0)",
        "-o-transform": "translateY(0)",
        "transform": "translateY(0)"
      });
    }, 100);
    $('.comment-btns').show();
    $('body').css('overflow', 'hidden');
    //滚动封顶
    $comment[0].addEventListener('touchstart', dropcontroller.dropStart, false);
    $comment[0].addEventListener('touchmove', dropcontroller.dropStop, false);

    //初始化子评论列表
    if ($('.comment.opened .replies').length===0){
      $('<div class="replies">').appendTo($('.comment.opened')).append($('<ul class="reply-list"></ul>')).append($("<div class='appendReply loadmore'>加载更多</div>"));
      queryReply();
    }
  }

  //收起回复列表
  function hideReply() {
    var $comment = $('.comment.opened');
    var $clonecomment = $comment.clone().appendTo($('#commentlist'));
    $comment.removeClass('opened').css({
      "-webkit-transition": "all 0s linear",
      "-o-transition": "all 0s linear",
      "transition": "all 0s linear",
      "-webkit-transform": "translateY(0)",
      "-ms-transform": "translateY(0)",
      "-o-transform": "translateY(0)",
      "transform": "translateY(0)"
    });
    setTimeout(function() {
      $clonecomment.css({
        "-webkit-transform": "translateY(100%)",
        "-ms-transform": "translateY(100%)",
        "-o-transform": "translateY(100%)",
        "transform": "translateY(100%)"
      });
    }, 100);
    setTimeout(function() {
      $clonecomment.remove();
      $('.comment-btns').hide();
    }, 500);

    $('body').css('overflow', 'auto');
    $('body').scrollTop(scrollTop); //位置回滚
    $comment[0].removeEventListener('touchstart', dropcontroller.dropStart, false);
    $comment[0].removeEventListener('touchmove', dropcontroller.dropStop, false);
  }

  //展开回复列表
  function showEditor() {
    var newParentId, newUserId;
    //表单变更
    if ($(this).closest('.currentissue').length !== 0 || $(this).closest('.evaluate').length !== 0) {
      newParentId = '';
      newUserId = '';
    } else if ($(this).closest('.comment-btns').length !== 0) {
      newParentId = $('.comment.opened').data('itemid');
      newUserId = '';
    } else if ($(this).closest('.reply-ft').length !== 0) {
      newParentId = $('.comment.opened').data('itemid');
      newUserId = $(this).closest('.reply').data('userid');
    }
    if (newParentId + '' + newUserId !== $("#parentId").val() + $("#userId").val()) {
      myUE.setContent('');
    }
    $("#parentId").val(newParentId);
    $("#userId").val(newUserId);

    $('.ui-editor').addClass('active');
    $('body').css('overflow', 'hidden');
    myUE.focus();
  }

  

  var dropcontroller = dropController();
  //闭包-滚动封顶
  function dropController() {
    var startY = 0;
    var startX = 0;
    //开始滑动
    function dropStart(e) {
      startY = e.touches[0].clientY;
      startX = e.touches[0].clientX;
    }
    //滑动中，判断是否到底
    function dropStop(e) {
      // 高位表示向上滚动
      // 底位表示向下滚动
      // 1容许 0禁止
      var status = '11';
      var ele = this;
      var currentY = e.touches[0].clientY;
      var currentX = e.touches[0].clientX;
      if (ele.scrollTop === 0) {
        // 如果内容小于容器则同时禁止上下滚动
        status = ele.offsetHeight >= ele.scrollHeight ? '00' : '01';
      } else if (ele.scrollTop + ele.offsetHeight >= ele.scrollHeight) {
        // 已经滚到底部了只能向上滚动
        status = '10';
      }
      if (status != '11') {
        // 判断当前的滚动方向
        var direction = currentY - startY > 0 ? '10' : '01';
        // 操作方向和当前允许状态求与运算，运算结果为0，就说明不允许该方向滚动，则禁止默认事件，阻止滚动
        if (!(parseInt(status, 2) & parseInt(direction, 2))) {
          e.preventDefault();
        }
      }
      if (Math.abs(currentX - startX) < Math.abs(currentY - startY)) {
        e.stopPropagation();
      }
    }
    return {
      dropStart: dropStart,
      dropStop: dropStop
    }
  }
});

function conutComment(type) {
  $.ajax({
    type: "post",
    url: path + "/commentController/countComment.do",
    data: {
      "type": type,
      "comId": comId
    },
    success: function(data) {
      $("#pNum").html("");
      $("#pNum").append(data)
    }
  })
}

function conutVote() {
  $.ajax({
    type: "post",
    url: path + "/commentController/conutVote.do",
    data: {
      "comId": comId
    },
    success: function(data) {
      $(".evaluate .digg-num").html(data);
    }
  })
}


function queryPage(byTime,byLove) {
  var curr=1;
  $('#commentlist').html('');
  appendPage();
  $('.appendPage').off('click',appendPage).on('click',appendPage);
  
  function appendPage(){
      $('.appendPage').addClass('loading');
 	    if (curr===parseInt(totalPage)){
		  $('.appendPage').off('click',appendPage).hide();
	    }else{
	      $('.appendPage').show();
	    }
      
      $.ajax({
        type: "post",
        url: path + "/commentController/getAllParentCommentApp.do",
        data: {
           curPage:curr,
          "comId": comId,
          "parentId": null,
          "pageSize": 4, //一页连续显示的数量
          "byTime": byTime,
          "byLove": byLove
        },
        async: true,
        dataType: "json",
        error: function(request) {
          layer.msg("Connection error");
        },success: function(data){

		      curr++;
		      $.each(data.list, function(i, item) {
		        var str = "<li class='comment clearfix' data-itemid='" + item.id + "'>" + "<div class='comment-hd'>" + "<span class='author'>" + item.username + "</span>" + "<span class='pdate'>" + item.commentTimeStr + "</span>" + "</div>" + "<div class='comment-bd'>" + "<div class='txtwp'>" + item.content + "</div>" + "</div>" + "<div class='comment-ft'>";
		
		        if (item.loving == true) {
		          str = str + "<a class='comment-digg haddigg' href='javascript:void(0)'><i class='icon_digg'></i><span class='digg-notdigg'>赞</span><span class='digg-num'>" + item.love + "</span></a>";
		        } else {
		          str = str + "<a class='comment-digg' href='javascript:void(0)'><i class='icon_digg'></i><span class='digg-notdigg'>赞</span><span class='digg-num'>" + item.love + "</span></a>";
		        }
		        str = str + "<a class='reply-call' href='javascript:void(0)'><i class='icon_reply'></i><span class='reply-num'>" + item.childCommentNums + "</span></a>" + "</div>";
		
		        str = str + "</li>"
		        $("#commentlist").append(str);
		      });      	
          
        }
      });
    }
}
//请求子评论列表
function queryReply() {
  parentId=$('.comment.opened').data('itemid'),
  $cont=$('.comment.opened .reply-list'),
  $appendReply=$('.comment.opened .appendReply');
  $cont.html('');
  appendReply();
    
  $appendReply.off('click').on('click',appendReply);
  function appendReply() {
      $appendReply.addClass('loading');
      $.ajax({
        type: "post",
        url: path + "/commentController/getAllChildCommentApp.do",
        data: {
          "parentId": parentId,
        },
        async: true,
        dataType: "json",
        error: function(request) {
          layer.msg("Connection error");
        },success:function(data){
        	$cont.html("");
        	  $.each(data, function(j, child) {
                  var author,str='';
                  if (child.targetId != null) {
                    author = child.username + " 对   " + child.targetUsername + " 说："
                  } else {
                    author = child.username + "：";
                  }
                  str = str + "<li class='reply clearfix' data-itemid='" + child.id + "' data-userId='" + child.userId + "'>" + "<div class='reply-hd clearfix'>" + "<span class='author'>" + author + "</span>"+ "<span class='pdate'>" + child.commentTimeStr + "</span>" + "</div>" + "<div class='reply-bd'>" + "<div class='txtwp'>" + child.content + "</div>" + "</div>" + "<div class='reply-ft'>" ;                
                  if (child.loving == true) {
                    str = str + "<a class='reply-digg haddigg' href='javascript:void(0)'><i class='icon_digg'></i><span class='digg-notdigg'>赞</span><span class='digg-num'>" + child.love + "</span></a>";
                  } else {
                    str = str + "<a class='reply-digg' href='javascript:void(0)'><i class='icon_digg'></i><span class='digg-notdigg'>赞</span><span class='digg-num'>" + child.love + "</span></a>";
                  }
                  str = str + "<a class='reply-call' href='javascript:void(0)'><i class='icon_reply'></i></a>" + "</div>";
                  str = str + "</li>";
                  $cont.append(str);
                });
        	
        }
      });
    }
}

function submitComment() {
//  alert(JSON.stringify({
//    comId: $('#comId').val(),
//    parentId: $('#parentId').val(),
//    userId: $('#userId').val()
//  }));
  $.ajax({
    type: "post",
    url: path + "/commentController/addComment.do",
    data: $("#commmentForm").serialize(),
    async: true,
    dataType: 'json',
    error: function(request) {
      if (request.status === 404 || request.status === '404') {
        layer.msg("网络异常");
      } else {
        layer.msg("Connection error");
      }
    },
    beforeSend: function() {
      if (!UE.getEditor('editor').hasContents()) {
        layer.msg("请填写评论内容！", { icon: 0, time: 1500 });
        return false;
      }
    },
    success: function(data) {
      if (data.status == "l") {
        layer.confirm('您还未登录，是否登录?', function(index) {
          window.location.href = path + "/login.jsp";
        });
      } else if (data.status == "y") {
    	  
    	  
    	  
        $("#parentId").val('');
        $("#userId").val('');
        UE.getEditor('editor').setContent("");
        hideEditor();
        layer.msg(data.info, { icon: 1, time: 1000 });
        queryPage(true, false);
      } else if (data.status == "n") {
        layer.msg(data.info, { icon: 2, time: 2000 });
      }
    }
  });
}

//收起回复列表
function hideEditor() {
  $('.ui-editor').removeClass('active');
  $('body').css('overflow', 'auto');
}