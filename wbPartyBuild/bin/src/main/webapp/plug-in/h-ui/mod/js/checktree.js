$(function(){
  if (!Array.prototype.indexOf)
  {
    Array.prototype.indexOf = function(elt /*, from*/)
    {
      var len = this.length >>> 0;
      var from = Number(arguments[1]) || 0;
      from = (from < 0)
           ? Math.ceil(from)
           : Math.floor(from);
      if (from < 0)
        from += len;
      for (; from < len; from++)
      {
        if (from in this &&
            this[from] === elt)
          return from;
      }
      return -1;
    };
  }
  $('.menutree-2 .menutree-item').each(function() {
    $(this).addClass('u-'+$(this).data('id'));
  });
  $('.menutree-2 .icon_checkbox').click(function(event) {
    //全(不)选
    if ($(this).closest('.menutree-tt').length!==0) {
      $(this).removeClass('hascheck');
      if ($(this).hasClass('checked')) {
        $(this).removeClass('checked');
        $(this).closest('.menutree-menu').find('.menutree-list .icon_checkbox').each(function() {
          $('.u-'+$(this).closest('.menutree-item').data('id')).find('i').removeClass('checked');
        });
      }else{
        $(this).closest('.menutree-menu').find('.icon_checkbox').addClass('checked');
      }
    }
    //单选
    if ($(this).closest('.menutree-item').length!==0) {
      if ($(this).hasClass('checked')) {
        $('.u-'+$(this).closest('.menutree-item').data('id')).find('i').removeClass('checked');
      }else{
        $(this).addClass('checked');
      }
    }
    updateRecipient();
    updateCheckStatus();
    if (event.stopPropagation) {
      event.stopPropagation();
    }
    else event.cancelBubble=true;
  });

  // 收件人处取消选择
  $('.recipientslist').on('click','li',function(){
    $('.u-'+$(this).data('id').substring(5)).find('i').removeClass('checked');
    $('.u-'+$(this).data('id').substring(5)).find('i').removeClass('linking');
    $(this).remove();
    updateCheckStatus();
  });

  // 搜索
  $('.ipt_check').keyup(function(){
    $('.menutree-2 .menutree-menu').removeClass('active');
    if ($(this).val()==='') {
      $('.menutree-2 .menutree-item').removeClass('hide');
    }else{
      $('.menutree-2 .menutree-item').addClass('hide');
      $('.menutree-2 .menutree-item').each(function(){
        if($(this).text().indexOf($('.ipt_check').val())!==-1){
          $(this).removeClass('hide');
          $(this).parents('.menutree-menu').addClass('active');
        }
      });
    }
  });
});
//更新选中，全选和不全选的状态以及上传用的id拼接字符串
function updateCheckStatus() {
  $('.menutree-tt .icon_checkbox').each(function() {
    var num=0,$checkeboxs=$(this).closest('.menutree-menu').find('.menutree-item .icon_checkbox');
    for (var i=0; i<$checkeboxs.length; i++) {
      if ($checkeboxs.eq(i).hasClass('checked')) {
        num++;
      }
    }
    $(this).removeClass('hascheck');
    $(this).removeClass('checked');
    if (num===$checkeboxs.length&&num!==0) {
      $(this).addClass('checked');
    }else if (num!==0) {
      $(this).addClass('hascheck');
    }
  });
  var idarr=[];
  $('.menutree-2 .menutree-item .icon_checkbox.checked').each(function() {
    if (idarr.indexOf($(this).closest('.menutree-item').data('id'))===-1) {
      idarr.push($(this).closest('.menutree-item').data('id'));
    }
  });
  $('.ipt_recipients').val(idarr.join('|'));
}

//刷新收件人列表
function updateRecipient(){
  $('.menutree-2 .menutree-item .checked,.menutree-2 .menutree-item .linking').each(function() {
    var $recipients=$('.recipientslist li');
    var uid=$(this).parent().data('id');
    var name=$(this).parent().text();
    if ($(this).hasClass('checked')&&!$(this).hasClass('linking')) {
      if ($('.l-'+$(this).closest('.menutree-item').data('id')).length===0) {
        $('.recipientslist').append($('<li data-id="link-'+uid+'" class="l-'+uid+'">'+name+'<i class="Hui-iconfont Hui-iconfont-close2"></i></li>'));
      }
      $(this).addClass('linking');
    }else if(!$(this).hasClass('checked')&&$(this).hasClass('linking')){
      $('.l-'+uid).remove();
      $(this).removeClass('linking');
    }
  });
}
