$(function() {  
	var activityId = $("#activityId").val();
	$.ajax({	  
	  type:'post',
  	  url:"manuscriptController/getPhotoWall.do",
  	  data:{
  		activityId:activityId,
  	  },
  	  dataType: "json",
  	  success:function(data) {
  		$.each(data,function(i, item) {
  			if(i<35){
  				$('.photowall').append("<div id='pic"+i+"'><img src='/public/"+item.pattern+"' onload='imgLoad(event)'></div>");   	  
  			}    			  		
  		});
   	}		  
	  });

  //图片加载事件
  $('.work img,.award-pic img').each(function(){
     if(this.complete)
       { setImgHeight(this);}
     else {
      $(this).on('load',imgLoad);
      $(this).attr('src',$(this).attr('src'));//ie9的bug
     }
  });
  $('.sort').click(sorting);
  $('.showrules').click(function(){mylayer({elem:'#showRules'});});//弹窗
  $('.work-pic img').click(function(){
    mylayer({elem:'#showWork',scrollable:true});//图片弹窗
    addData(this);//数据填充
    $('#showWork img').each(function(){
      if(this.complete){
          filler('#showWork img',window,400,80);
      }
      else {
          $(this).unbind('load').bind('load',function(){filler('#showWork img',window,400,80);});
          $(this).attr('src',$(this).attr('src'));
      }
    });
  });

//以某个元素宽高为标准的填充
function filler(content,standard,rest_w,rest_h){
  if($(content).height()>$(standard).height()-rest_h)//高度调整
    $(content).css({height:($(standard).height()-rest_h)+"px",width:'auto'});
  if($(content).width()>$(standard).width()-rest_w)//高度调整
    $(content).css({width:($(standard).width()-rest_w)+"px",height:'auto'});
}



  //返回顶部
  var $backToTopEle=$('<a href="javascript:void(0)" class="Hui-iconfont toTop" title="返回顶部" alt="返回顶部" style="display:none">&#xe684;</a>').appendTo($("body")).click(function(){
    $("html, body").animate({ scrollTop: 0 }, 120);
  });
  var $backToTopFun = function() {
    var st = $(document).scrollTop(), winh = $(window).height();
    (st > 0)? $backToTopEle.show(): $backToTopEle.hide();
  };
  $(window).on("scroll",$backToTopFun);$backToTopFun();
  if ($(window).width()>1324) 
    $('.toTop').css('right',$(window).width()/2-665+'px')
});
//img加载成功调用的函数
function imgLoad(event) {
  var event = event || window.event;
  var targ = event.srcElement ? event.srcElement : event.target;
  setImgHeight(targ);
}
function setImgHeight(targ) {
  $(targ).show();
  $(targ).prev('.noimg').hide();
  if ($(targ).closest('.photowall,.miscpic-cont').length!==0) {
    centerImg(targ);//图片墙宽高调整为居中
  }
  if ($(targ).closest('.photowall').length!==0) {
     $(targ).parent().css('box-shadow','5px 5px 5px rgba(0,0,0,0.3)');
  }
  if ($(targ).closest('.work-pic,.prize-works,.award-pic').length!==0) {
    $(targ).css({width:'100%',height:'auto',position:'relative'});
    if ($(targ).height()<$(targ).parent().height())
        {
          $(targ).css({top:($(targ).parent().height()-$(targ).height())/2+'px'});
        }
      else if($(targ).height()>$(targ).parent().height()){
        $(targ).css({height:'100%',width:'auto'});
        $(targ).css({left:($(targ).parent().width()-$(targ).width())/2+'px'});
      }
  }
}

function centerImg(targ){
  $(targ).css({width:'100%',height:'auto',position:'relative'});
  if ($(targ).height()<$(targ).parent().height())
      {
        $(targ).css({height:'100%',width:'auto'});
        $(targ).css({left:-($(targ).width()-$(targ).parent().width())/2+'px'});
      }
    else if($(targ).height()>$(targ).parent().height()){
      $(targ).css({top:-($(targ).height()-$(targ).parent().height())/2+'px'});
    }
}

//排序方式
function sorting() {
  $('.sort').removeClass('active');
  $(this).addClass('active');
}



//重新加载图片
function loadAgain(obj) {
  $(obj).toggleClass('active');
  $(obj).next('img').attr("src", $(obj).next('img').attr("src")+"?"+Math.random());
}

//弹窗的实现
function mylayer(obj){
  var elem=obj.elem; //捕获层
  var scrollable=obj.scrollable||false;//是否固定
  var closebtn=obj.closebtn||true;//是否有关闭按钮
  var title=obj.title||false;//标题
  var addClass=obj.addClass||false;//是否给.mylayer添加自定义class
  var newlayer=$('<div class="mylayer"><div class="mylayer-overlay"><div class="mylayer-main"><div class="mylayer-hd"></div><span class="mylayer-close">&times;</span><div class="mylayer-bd"></div></div></div></div>');
  $(elem).remove().appendTo(newlayer.find('.mylayer-bd'));
  $('body').append(newlayer);
  $(elem).show();

  //标题添加
  if(title!==""||title!==false) {
    $('.mylayer-hd').append(title);
  }

  //class添加
  if(addClass!==""||addClass!==false) {
    $('.mylayer').addClass(addClass);
  }

  //不固定定位，可滚动的实现
  if (scrollable) {
  $(elem).closest('.mylayer').css('position','absolute');
  var y=0;
  var bh=$('.mylayer').height();
  var wh=$(window).height();
  var s_top=$('body').scrollTop()>0?$('body').scrollTop():$('html').scrollTop();
  y=s_top+wh/2-bh/2;
  $(elem).closest('.mylayer-main').css('top',y+'px');
} 
  $('.mylayer-overlay').click(closemylayer);
  //关闭按钮
  if (closebtn) 
   $('.mylayer-close').click(closemylayer);
 else $('.mylayer-close').hide();
}
function closemylayer(event){
  var targets = event.srcElement ? event.srcElement : event.target;
  if(targets!==this)return;
  $(this).closest('.mylayer').hide();
}

function addData(that){//图片弹窗的数据填充
  var elem=$(that).closest('.work');
  $('#showWork .tb').attr('id','s_'+elem.attr('id'));
  $('#showWork').find('.cell').eq(0).html('<img src='+elem.find('.work-pic img').eq(0).attr("src")+'>');
  $('#showWork').find('.work-tt span').eq(0).text(elem.find('.work-tt span').eq(0).text());
  $('#showWork').find('.work-tt span').eq(0).text(elem.find('.work-tt span').eq(0).text());
  $('#showWork').find('.work-ct div').eq(0).text(elem.find('.work-ct div').eq(0).text());
  $('#showWork').find('.work-ct p').eq(0).text(elem.find('.work-ct p').eq(0).text());
  $('#showWork').find('.work-remark div').eq(0).text(elem.find('.work-remark div').eq(0).text());
  $('#showWork').find('.work-remark p').eq(0).text(elem.find('.work-remark p').eq(0).text());

  if (elem.find('.love').hasClass('loving'))
    $('#showWork').find('.love').addClass('loving');
  else  $('#showWork').find('.love').removeClass('loving');
  $('#showWork').find('.love span').text(elem.find('.love span').text());
  $('#showWork').find('.work-ct p').eq(0).text(elem.find('.work-ct p').eq(0).text());
}