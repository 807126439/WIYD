function imageGallery(options) {
	var defaults={
				first:0,
        target:'',
        list:'',
        title:'',
        prev:'',
        next:'',
        count:'',
        total:'',
        leftSlide:'',
        rightSlide:'',
        slideBack:'',
        srcattr:'data-src',
        titleattr:'title',
        speed:1,
        startToEnd:true,
        endToStart:true,
        listenKey:true
      };
	var opts = $.extend({},defaults, options);

	//jquery容器
	var $target=$(opts.target),
			$list=$(opts.list),
			$title=$(opts.title),
			$prev=$(opts.prev),
			$next=$(opts.next),
			$count=$(opts.count),
			$total=$(opts.total),
			$leftSlide=$(opts.leftSlide),
			$rightSlide=$(opts.rightSlide),
			$slideBack=$(opts.slideBack);

	//常量
	var counts=$list.find('li').length;

	//初始化
	$total.text(counts);
	$list.css({'width':'10000px','position':'relative'});
	$next.on('click',showNext);
	$prev.on('click',showPrev);
	$list.find('li img').on('click',showThis);
	$leftSlide.on('click',leftSlide);
	$rightSlide.on('click',rightSlide);
	$slideBack.on('click',slideBack);
	if (opts.listenKey) {
		$(document).on('keydown',listenKey);
	}
	showImage(opts.first);


	//方向键控制
	function listenKey(event) {
		var key = event.keyCode;
			if (key == 37) {
				showPrev();
			} else if (key == 39) {
				showNext();
			}
	}	
	function showNext() {
		var currentIndex=$list.find('li.selected').index();
		if (currentIndex+1===counts) {
			if(opts.endToStart){
				endToStart();
				return;
			}
		}else{
			showImage(currentIndex+1);
		}
	}

	function showPrev() {
		var currentIndex=$list.find('li.selected').index();
		if (currentIndex===0) {
			if(opts.startToEnd){
				startToEnd();
				return;
			}
		}else{
			showImage(currentIndex-1);
		}
	}	

	function showThis() {
		var index=$(this).closest('li').index();
		showImage(index);
	}
	//展示第index+1张图片
	function showImage(index) {
		var $img=$list.find('li:eq('+index+') img');
		$count.text(index+1);
		$img.closest('li').addClass('selected').siblings().removeClass('selected');
		if (opts.titleattr.indexOf('data-')!==-1){
			$title.text($img.data(opts.titleattr.substring(5)));
		}else{
			$title.text($img.attr(opts.titleattr));
		}
		if (opts.srcattr.indexOf('data-')!==-1){
			$target.attr('src',$img.data(opts.srcattr.substring(5)));
		}else{
			$target.attr('src',$img.attr(opts.srcattr));
		}

		preload();
		slideToImage(index);
	}
	//提前缓存图片
	function preload(src, callback) {
		var currentIndex=$list.find('li.selected').index();
		setTimeout(function() {
		 if($list.find('li.selected').index()!==currentIndex){
		 	return;
		 }
		 if (currentIndex+1!==counts) {
		 	if (opts.srcattr.indexOf('data-')!==-1){
		 		loadImage($list.find('li').eq(currentIndex+1).data(opts.srcattr.substring(5)));
		 	}else{
		 		loadImage($list.find('li').eq(currentIndex+1).attr(opts.srcattr));
		 	}
		 }
		 if (currentIndex!==0) {
		 	if (opts.srcattr.indexOf('data-')!==-1){
		 		loadImage($list.find('li').eq(currentIndex-1).data(opts.srcattr.substring(5)));
		 	}else{
		 		loadImage($list.find('li').eq(currentIndex-1).attr(opts.srcattr));
		 	}
		 }
		},1200);
	}	
	function loadImage(src, callback){
		var img = $('<img>').on('load', function(){
			callback.call(img);
		});
		
		img.attr('src',src);
	}

	//滚动至第index+1张图片
	function slideToImage(index,slideEnd) {
		var distance=getDistance(index),
				resultLeft;
		if (distance>0) {
			resultLeft=0;
		}else{
			resultLeft=distance;
		}
		$list.animate({'left':resultLeft+'px'},opts.speed*600,slideEnd);

	}
	//换取第index+1张图片需要居中的left值
	function getDistance(index) {
		var $img=$list.find('li:eq('+index+') img'),
				offsetLeft=$img.offset().left,
				containerLeft=$list.offset().left,
				imgWidth=$img.width(),
				containerWidth=$list.parent().width();
				centerMargin=(containerWidth-imgWidth)/2;
		var	distance=containerLeft+centerMargin-offsetLeft;
		return distance;
	}

	function leftSlide() {
		var currentLeft=$list.css('left')==='auto'?0:parseInt($list.css('left')),
				containerWidth=$list.parent().width();
		var distance=currentLeft+containerWidth/2;
		if (distance>0) {
			resultLeft=0;
		}else{
			resultLeft=distance;
		}
		$list.animate({'left':resultLeft+'px'},opts.speed*600);
	}

	function rightSlide() {
		var currentLeft=$list.css('left')==='auto'?0:parseInt($list.css('left')),
				containerWidth=$list.parent().width();
		var distance=currentLeft-containerWidth/2,
				minDistance=getDistance($list.find('li').length-1);
		if(distance<minDistance){
			resultLeft=minDistance;
		}else{
			resultLeft=distance;
		}
		$list.animate({'left':resultLeft+'px'},opts.speed*600);
	}
	function slideBack() {
		var currentIndex=$list.find('li.selected').index();
		slideToImage(currentIndex,function() {
			$list.find('li.selected img').animate({'opacity':0.5},function() {
				$list.find('li.selected img').animate({'opacity':1});
			});
		});
	}
	function startToEnd() {
		showOverlay('startToEnd');
	}
	function endToStart() {
		showOverlay('endToStart');
	}
	function showOverlay(type){
		var $overlay=$('<div style="position:fixed;width:300px;height:100px;color:#FFF;background:#333;font-size:16px;z-index:2000;left:50%;top:50%;margin-left:-150px;margin-top:-50px;text-align:center;line-height:50px;"><div class="ct"></div><div class="ft"></div>'),
				$btn=$('<a href="javascript:void(0)" style="color:#FFF;display:inline-block;width:50%;float:left;text-align:center;background:#444;"></a>'),
				$close=$('<a href="javascript:void(0)" style="color:#FFF;display:inline-block;width:50%;float:left;text-align:center;background:#444;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;border-left:1px solid #333;">返回</a>');
		$overlay.find('.ft').append($btn).append($close);
		$overlay.appendTo($('body'));
		if (type==="startToEnd") {
			$btn.text('前往最后一页');
			$overlay.find('.ct').text('已经到达第一页');
			$btn.on('click',function() {
				showImage(counts-1);
				closeOverlay();
			});
		}else if (type==="endToStart") {
			$btn.text('前往第一页');
			$overlay.find('.ct').text('已经到达最后一页');
			$btn.on('click',function() {
				showImage(0);
				closeOverlay();
			});
		}
		$close.on('click',closeOverlay);
		function closeOverlay() {
			$btn.off();$close.off();
			$overlay.remove();
		}
	}

	return {
		getCounts:function() {return counts;},
		showNext:showNext,
		showPrev:showPrev,
		showImage:showImage,
		slideToImage:slideToImage,
		leftSlide:leftSlide,
		rightSlide:rightSlide,
		slideBack:slideBack,
		destroy:function() {
			$next.off('click',showNext);
			$prev.off('click',showPrev);
			$list.find('li img').off('click',showThis);
			$leftSlide.off('click',leftSlide);
			$rightSlide.off('click',rightSlide);
			$slideBack.off('click',slideBack);
			$(document).off('keydown',listenKey);
			$target=null,
			$list=null,
			$title=null,
			$prev=null,
			$next=null,
			$count=null,
			$total=null,
			$leftSlide=null,
			$rightSlide=null,
			$slideBack=null;

			counts=null;
		}
	};
}