/**
 * @name iselect插件 （改编至layui表单模块）
 * @author 张子操
 * @namespace iSelect
 * @requires jquery
 * @vesion 1.1
 * @demo 初始化配置  iSelect(option) option见下方
 * @demo 单个配置 不渲染  <select lay-ignore></select>
 * @demo 单个配置 开启搜索  <select lay-search></select>
 * @demo 单个配置 向上菜单  <select lay-up></select>
 * @demo 单个配置 向下菜单  <select lay-down></select>
 * @event iChange  select.on('iChange',fun)自定义change事件（原生change事件需配置才禁用）
 */
(function(root,factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as anonymous module.
    define(['jquery'], factory);
  } else if (typeof exports === 'object') {
    // Node/CommonJS.
    module.exports = factory(require('jquery'));
  } else {
    // Browser globals.
    root.iSelect = factory(root.jQuery);
  }
})(this,function(jquery){
  var $ = jquery;
  var iSelect = function(option){
    //默认配置
    var option=$.extend({},{
      "elem":"ui-form", //要渲染的select元素的选择器
      "tips":'请选择', //没有默认选项时的placeholder
      "class":'ui-form-select', //新选择框容器类名
      "title":'ui-select-title', 
      "noneClass":'ui-select-none', 
      "disabledClass":'ui-disabled', 
      "activeClass":'ui-this', //当前选项类名
      "hideClass":'ui-hide',
      "direct":"down", //菜单方向
      "style":null, //object 新select样式
      "extendClass":null, //新选择框容器额外添加类名
      "searchabled":false, //是否可以搜索
      "nativeChange":true //是否触发原生change事件
    },option);

    var ELEM = option.elem, TIPS = option.tips, CLASS =option["class"], TITLE = option.title
    ,NONE =  option.noneClass, THIS =  option.activeClass, HIDE =  option.hideClass, DISABLED = option.disabledClass, initValue = '', thatInput
    
    ,selects = $(ELEM).is("select")?$(ELEM):$(ELEM).find('select'), hide = function(e, clear){
      if(!$(e.target).parent().hasClass(TITLE) || clear){
        $('.'+CLASS).removeClass(CLASS+'ed');
        thatInput && initValue && thatInput.val(initValue);
      }
      thatInput = null;
    }
    
    ,events = function(reElem, disabled, isSearch){
      var select = $(this)
      ,title = reElem.find('.' + TITLE)
      ,input = title.find('input')
      ,dl = reElem.find('dl')
      ,dds = dl.children('dd')
      
      
      if(disabled) return;
      
      //展开下拉
      var showDown = function(){
        reElem.addClass(CLASS+'ed');
        dds.removeClass(HIDE);
      }, hideDown = function(){
        reElem.removeClass(CLASS+'ed');
        input.blur();
        
        notOption(input.val(), function(none){
          if(none){
            initValue = dl.find('.'+THIS).html();
            input && input.val(initValue);
          }
        });
      };
      
      //点击标题区域
      title.on('click', function(e){
        reElem.hasClass(CLASS+'ed') ? (
          hideDown()
        ) : (
          hide(e, true), 
          showDown()
        );
        dl.find('.'+NONE).remove();
      }); 
      
      //点击箭头获取焦点
      title.find('.ui-edge').on('click', function(){
        input.focus();
      });
      
      //键盘事件
      input.on('keyup', function(e){
        var keyCode = e.keyCode;
        //Tab键
        if(keyCode === 9){
          showDown();
        }
      }).on('keydown', function(e){
        var keyCode = e.keyCode;
        //Tab键
        if(keyCode === 9){
          hideDown();
        } else if(keyCode === 13){ //回车键
          e.preventDefault();
        }
      });
      
      //检测值是否不属于select项
      var notOption = function(value, callback, origin){
        var num = 0;
        $.each(dds, function(){
          var othis = $(this)
          ,text = othis.text()
          ,not = text.indexOf(value) === -1;
          if(value === '' || (origin === 'blur') ? value !== text : not) num++;
          origin === 'keyup' && othis[not ? 'addClass' : 'removeClass'](HIDE);
        });
        var none = num === dds.length;
        return callback(none), none;
      };
      
      //搜索匹配
      var search = function(e){
        var value = $(this).val(), keyCode = e.keyCode;
        
        if(keyCode === 9 || keyCode === 13 
          || keyCode === 37 || keyCode === 38 
          || keyCode === 39 || keyCode === 40
        ){
          return false;
        }
        
        notOption(value, function(none){
          if(none){
            dl.find('.'+NONE)[0] || dl.append('<p class="'+ NONE +'" style="text-align:center">无匹配项</p>');
          } else {
            dl.find('.'+NONE).remove();
          }
        }, 'keyup');
        
        if(value === ''){
          dl.find('.'+NONE).remove();
        }
      };
      if(isSearch){
        input.on('keyup', search).on('blur', function(e){
          thatInput = input;
          initValue = dl.find('.'+THIS).html();
          setTimeout(function(){
            notOption(input.val(), function(none){
              if(none && !initValue){
                input.val('');
              }
            }, 'blur');
          }, 200);
        });
      }

      //选择
      dds.on('click', function(){
        var othis = $(this), value = othis.data('value'),oldVal=select.val();

        if(othis.hasClass(DISABLED)) return false;
        select.val(value).removeClass('ui-form-danger'), input.val(othis.text());
        othis.addClass(THIS).siblings().removeClass(THIS);
        hideDown();

        if (oldVal+''!==value+''){
          setTimeout(function(){
            if (option.nativeChange) 
              select.trigger('change');//原生change
            select.trigger('iChange');
          },0)
        } 
        
        return false;
      });
      
      reElem.find('dl>dt').on('click', function(e){
        return false;
      });
      
      //关闭下拉
      $(document).off('click', hide).on('click', hide);
    }
    
    selects.each(function(index, select){
      var othis = $(this), hasRender = othis.next('.'+CLASS), disabled = this.disabled;
      var value = $(select).val(), selected = $(select.options[select.selectedIndex]); //获取当前选中项

      if (typeof othis.attr('lay-ignore') === 'string') { return; }
      var isSearch = typeof othis.attr('lay-search') === 'string'||option.searchabled;
      var direct = typeof othis.attr('lay-up') === 'string'?"up":(typeof othis.attr('lay-down') === 'string'?"down":option.direct);

      //替代元素
      var reElem = $(['<div class="ui-unselect '+ CLASS + (disabled ? ' ui-select-disabled' : '') +(option.extendClass?' '+option.extendClass:'')+'">'
        ,'<div class="'+ TITLE +'"><input type="text" placeholder="'+ (select.options[0].innerHTML ? select.options[0].innerHTML : TIPS) +'" value="'+ (value ? selected.html() : '') +'" '+ (isSearch ? '' : 'readonly') +' class="ui-unselect ui-input'+ (disabled ? (' '+DISABLED) : '') +'">'
        ,'<i class="ui-edge"></i></div>'
        ,'<dl class="ui-anim '+(direct==='up'?'ui-options-up ui-anim-downbit':'ui-anim-upbit') + (othis.find('optgroup')[0] ? ' ui-select-group' : '') +'">'+ function(options){
          var arr = [];
          $.each(options, function(index, item){
            if(index === 0 && !$(item).val()) return;
            if(item.tagName.toLowerCase() === 'optgroup'){
              arr.push('<dt>'+ item.label +'</dt>'); 
            } else {
              arr.push('<dd data-value="'+ $(item).val() +'" class="'+ (value === $(item).val() ?  THIS : '') + (item.disabled ? (' '+DISABLED) : '') +'">'+ item.innerHTML +'</dd>');
            }
          });
          return arr.join('');
        }(othis.find('*')) +'</dl>'
      ,'</div>'].join(''));
      
      hasRender[0] && hasRender.remove(); //如果已经渲染，则Rerender
      othis.after(reElem);
      events.call(this, reElem, disabled, isSearch);
      othis.addClass('ui-hide')
      option.style&&reElem.css(option.style)
    });
  };
  return iSelect;
});

 
