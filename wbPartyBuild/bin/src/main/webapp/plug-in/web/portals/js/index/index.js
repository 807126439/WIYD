var tempName = window.document.location.pathname;
var path = tempName.substring(0,tempName.substr(1).indexOf('/')+1);

$(function(){
	$.Huitab("#tab_demo .tabBar span","#tab_demo .tabCon","current","mousemove ","1");//选项卡
	FloatAd("#floadAD");//调用浮动窗口	
	/*
	SuperSlide组合注意：
	1、内外层mainCell、targetCell、prevCell、nextCell等对象不能相同，除非特殊应用；
	2、注意书写顺序，通常先写内层js调用，再写外层js调用
	*/

	jQuery(".display .Columns1-inner").slide({mainCell:"ul",prevCell:".Left-arrow",nextCell:".Right-arrow",autoPage:true,effect:"leftMarquee",interTime:50,autoPlay:true,vis:4});
	/* 外层tab切换 */
	jQuery(".display").slide({titCell:".display-button .Columns",mainCell:".display-inner"});
	
	
	
	jQuery(".focusBox").slide({ titCell:".num li", mainCell:".pic",effect:"fold", autoPlay:true,trigger:"click",
		//下面startFun代码用于控制文字上下切换
		startFun:function(i){
			 jQuery(".focusBox .txt li").eq(i).animate({"bottom":0}).siblings().animate({"bottom":-36});
		}
	});
	
	
	
	 //实现党旗党徽显示
    $('.pic-button1,.pic-button2,.pic-button3,.pic-button4').click(function(event) {
      var pos_x,pos_y,w,h,i,thas=this;
      var imgSrc = $(this).attr("img-src");
      var data = $(this).attr("data-text");
      
      if($(this).hasClass('pic-button1')){
        {w=360;h=270;i=imgSrc;}
      }
      else if($(this).hasClass('pic-button2')){
        {w=300;h=283;i=imgSrc;}
      }
      else if($(this).hasClass('pic-button3')){
    	  var url = $(this).attr("data-url");
    	  window.open(url);
    	  return;
      }
      else if($(this).hasClass('pic-button4'))
        {
          layer.open({
            type: 1,
            title: "入党誓词",
            closeBtn: 0,
            skin: 'mystyle', //没有背景
            shadeClose: true,
            content: '<p class="xuanshi">'+data+'<p>' 
          });
          return;
        }
      else 
        return;
      var recalculation=function(){
        pos_x=$(thas).offset().left;
        pos_y=$(thas).offset().top;
        s_top=$('body').scrollTop()>0?$('body').scrollTop():$('html').scrollTop();
      };
      recalculation();
      var layer1=layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        area: [w+'px',h+'px'],
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: true,fix: false,
        // scrollbar: false,
        content:'<img src="'+i+'" style="width:'+w+'px;">'  
      });
      
      $(window).resize(function() {
        recalculation();
          layer.style(layer1, {
          top:pos_y-h-7,
          left:pos_x
        }); 
      });
      
    });

    //弹出反馈框
    $('.opinion>a,.opinion-close').click(function() {
      $('.feedbackbox').toggle(200);
    });
    
    
    
    

});

	if(!window.slider) {
		var slider={};
	}

	//调用轮播插件
	slider.data= [
		{
		"id":"slide-img-1", // 与slide-runner中的img标签id对应
		"client":"标题1",
		"desc":"这里修改描述 这里修改描述 这里修改描述" //这里修改描述
		},
		{
		"id":"slide-img-2",
		"client":"标题2",
		"desc":"这里修改描述 这里修改描述 这里修改描述" //这里修改描述
		},
		{
		"id":"slide-img-3",
		"client":"标题3",
		"desc":"这里修改描述 这里修改描述 这里修改描述" //这里修改描述
		}
	
	];

//广告漂浮窗口  
function FloatAd(selector) {  

    var obj = $(selector);  
    if (obj.find(".item").length == 0) return;//如果没有内容，不执行  
    var windowHeight = $(window).height();//浏览器高度  
    var windowWidth = $(window).width();//浏览器宽度  
    var dirX = -1.5;//每次水平漂浮方向及距离(单位：px)，正数向右，负数向左，如果越大的话就会看起来越不流畅，但在某些需求下你可能会需要这种效果  
    var dirY = -1;//每次垂直漂浮方向及距离(单位：px)，正数向下，负数向上，如果越大的话就会看起来越不流畅，但在某些需求下你可能会需要这种效果  
                  
    var delay = 30;//定期执行的时间间隔，单位毫秒  
    obj.css({ left: windowWidth / 2 - obj.width() / 2 + "px", top: windowHeight / 2 - obj.height() / 2 + "px" });//把元素设置成在页面中间  
    obj.show();//元素默认是隐藏的，避免上一句代码改变位置视觉突兀，改变位置后再显示出来  
    var handler = setInterval(move, delay);//定期执行，返回一个值，这个值可以用来取消定期执行  
                  
    obj.hover(function() {//鼠标经过时暂停，离开时继续  
        clearInterval(handler);//取消定期执行  
    }, function() {  
        handler = setInterval(move, delay);  
    });  
  
    obj.find(".close").click(function() {//绑定关闭按钮事件  
        close();  
    });  
    $(window).resize(function() {//当改变窗口大小时，重新获取浏览器大小，以保证不会过界（飘出浏览器可视范围）或漂的范围小于新的大小  
        windowHeight = $(window).height();//浏览器高度  
        windowWidth = $(window).width();//浏览器宽度  
    });  
    function move() {//定期执行的函数，使元素移动  
        var currentPos = obj.position();//获取当前位置，这是JQuery的函数，具体见：http://hemin.cn/jq/position.html  
        var nextPosX = currentPos.left + dirX;//下一个水平位置  
        var nextPosY = currentPos.top + dirY;//下一个垂直位置  
                      
//        if (nextPosX >= windowWidth - obj.width()) {//这一段是本站特有的需求，当漂浮到右边时关闭漂浮窗口，如不需要可删除  
//            close();  
//        }  
//  
        if (nextPosX <= 0 || nextPosX >= windowWidth - obj.width()) {//如果达到左边，或者达到右边，则改变为相反方向  
            dirX = dirX * -1;//改变方向  
            nextPosX = currentPos.left + dirX;//为了不过界，重新获取下一个位置  
        }  
        if (nextPosY <= 0 || nextPosY >= windowHeight - obj.height() - 5) {//如果达到上边，或者达到下边，则改变为相反方向。              
            dirY = dirY * -1;//改变方向  
            nextPosY = currentPos.top + dirY;//为了不过界，重新获取下一个位置  
        }  
        obj.css({ left: nextPosX + "px", top: nextPosY + "px" });//移动到下一个位置  
    }  
  
    function close() {//停止漂浮，并销毁漂浮窗口  
        clearInterval(handler);  
        obj.remove();  
    }  
}  


function showBigPhoto(path){
	layer.open({
	    
	    type: 1,
	    title: false,
	    closeBtn: 0,
	    area: ['800px', '600px'],
	    skin: 'layui-layer-nobg', //没有背景色
	    shadeClose: true,
	    content: "<img height='600' width='800' src="+path+">"
	    
	});	
}


$('.photo-activity,.piao,.QR-code').on('click','.close',function(e){
    $(this).closest('.photo-activity,.piao,.QR-code').hide();
    e.preventDefault();
  })