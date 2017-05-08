$(function(){
    setClickMenu({
    elem:'.Hui-tabNav ul', //点击触发的目标选择器
    menu:'<li onclick="closethistab(event)">关闭标签页</li><li onclick="closeothertab(event)">关闭其他标签页</li><li onclick="closerighttab(event)">关闭右侧标签页</li>' //菜单对象
    });//
});
//关闭当前标签页
function closethistab(event){
  var event = event || window.event;
  var targ = event.srcElement ? event.srcElement : event.target;
  var tarindex=parseInt($(targ).closest('.tabmenu').data('tag'));
  $('.Hui-tabNav ul li').eq(tarindex).find('i').click();
  hideClickMenu();
}//关闭当前标签页
function closeothertab(event){
  var event = event || window.event;
  var targ = event.srcElement ? event.srcElement : event.target;
  var tarindex=parseInt($(targ).closest('.tabmenu').data('tag'));
  $('.Hui-tabNav ul li').not($('.Hui-tabNav ul li').eq(0)).not($('.Hui-tabNav ul li').eq(tarindex)).find('i').click();
  hideClickMenu();
}//关闭当前标签页
function closerighttab(event){
  var event = event || window.event;
  var targ = event.srcElement ? event.srcElement : event.target;
  var tarindex=parseInt($(targ).closest('.tabmenu').data('tag'));
  $('.Hui-tabNav ul li').not(function(index){return index>tarindex?false:true;}).find('i').click();
  hideClickMenu();
}
//菜单控件
function setClickMenu(obj){
  var elem=obj.elem;
  var menu=obj.menu;
  //右键菜单显示
  $(elem).mouseup(function(event){
    if (event.which!==3) return;
    var targ=event.target;
    var $this=$(targ).closest('li');
    if ($this.length===0) return;
    $(document).bind("contextmenu",function(){return false;});
    if($('.tabmenu').length===0)
      $('<ul class="tabmenu" data-tag="'+$this.index()+'">'+menu+'</ul>').appendTo($('body')); 
    $('.tabmenu').css({top:event.clientY,left:event.clientX});
    if ($this.closest('li').find('i').length===0) {//首页不可关
      $('.tabmenu li').eq(0).addClass('notused');
    }
    $('body').off('mousedown',hideClickMenu).on('mousedown',hideClickMenu);
    if (event.stopPropagation)
        event.stopPropagation();
    event.cancelBubble = true;
   if (event.preventDefault)
        event.preventDefault();
    event.returnValue = false;
  });
}
//右键菜单隐藏
function hideClickMenu(e){
    if (e&&$(e.target).closest('.tabmenu').length!==0) {return;}
    $('.tabmenu').remove();
    $(document).unbind("contextmenu");
    $('body').off('mousedown',hideClickMenu);
}