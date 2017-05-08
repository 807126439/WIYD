//返回一个fileManager对象
//参数一：必选，fileManager容器的选择器、jq或Dom对象
//参数二:可选，fileManager组件的配置
//参数三:可选，webuploader的创建参数
function createFileManager(elem, option, uploaderOption) {
  if (!elem) return;
  var $wrap = $(elem),
    $toolbar=$('<div class="filemanager-toolbar">\
                  <a href="javascript:void(0)" class="filemanager-upload">上传</a>\
                  <span class="filemanager-btns disabled"></span>\
                  <span class="filemanager-btns filemanager-my-btns"></span>\
                  <span class="filemanager-filter">\
                    <input type="text" class="filemanager-search"/>\
                    <span class="filemanager-search-btn view-btn"></span>\
                    <span class="filemanager-sort-btn filemanager-menu-wrap">\
                      <i class="icon-sort view-btn"></i>\
                      <input type="hidden" class="filemanager-sort" value="sort"/>\
    				  <input type="hidden" class="filemanager-sort-methor" value="desc"/>\
                      <ul class="filemanager-sort-menu filemanager-menu">\
                      </ul>\
                    </span>\
                    <span class="filemanager-display view-btn filemanager-display-grid"></span>\
                  </span>\
                </div>').appendTo($wrap),
    $list=$('<div class="filemanager-list">\
              <div class="filemanager-list-header">\
                <span class="check-all"><i></i><span class="check-all-text">全选</span></span>\
                <span class="check-count-tips"></span>\
	    		 <div class="filedata-all">\
		            <span data-name="filename">文件名</span>\
		            <span data-name="addtime">上传时间</span>\
		         </div>\
              </div>\
              <div class="filemanager-list-view"><div class="loading"></div></div>\
              <div class="filemanager-laypage"></div>\
            </div>').appendTo($wrap),
    $uploader=$wrap.find('.uploader'),
    $uploaderContainer=$('<div class="uploader-container element-invisible">\
                            <div class="uploader-bg"></div>\
                            <div class="uploader-wrap">\
                              <div class="uploader-popup">\
                                <div class="uploader-hd">文件上传<span class="uploader-close"></span></div>\
                                <div class="uploader-bd"></div>\
                              </div>\
                            </div>\
                          </div>').appendTo($wrap).find('.uploader-bd').append($uploader).end(),
    $uploaderPopup=$uploaderContainer.find('.uploader-popup'),
    $uploaderClose=$uploaderContainer.find('.uploader-close'),
    $upload=$wrap.find('.filemanager-upload'),
    $btns=$wrap.find('.filemanager-btns').eq(0),
    $myBtns=$wrap.find('.filemanager-my-btns'),
    $rename=$('<a href="javascript:void(0)" class="filemanager-rename">重命名</a>'),
    $download=$('<a href="javascript:void(0)" class="filemanager-download">下载</a>'),
    $delete=$('<a href="javascript:void(0)" class="filemanager-delete">删除</a>'),
    $header=$wrap.find('.filemanager-list-header'),
    $view=$wrap.find('.filemanager-list-view'),
    $checkAll=$wrap.find('.check-all'),
    $checkCountTips=$wrap.find('.check-count-tips'),
    $filedataAll=$wrap.find('.filedata-all'),
    $laypage=$wrap.find('.filemanager-laypage'),
    $editBox=$('<div class="edit-box" style="display: none;">\
                  <div class="new-dir-item">\
                    <input class="edit-name" value="" type="text">\
                    <input class="edit-id" type="hidden" value="">\
                    <span class="edit-sure"></span>\
                    <span class="edit-cancel"></span>\
                  </div>\
                </div>').appendTo($('body')),
    $sortBtn=$wrap.find('.icon-sort'),
    $sortMethor=$wrap.find('.filemanager-sort-methor'),
    $sortMenu=$wrap.find('.filemanager-sort-menu'),
    $sortInput=$wrap.find('.filemanager-sort'),
    $searchInput=$wrap.find('.filemanager-search'),
    $searchBtn=$wrap.find('.filemanager-search-btn'),
    $display=$wrap.find('.filemanager-display'),
    fileManager={};

  //默认配置信息
  var option=$.extend({},{
    height:'auto', //容器高度，默认'auto'
    uploader:true, //是否要上传
    uploaderArea:['650px','420px'], //上传弹窗的宽和高
    ajaxUrl:'', //文件数据请求url
    ajaxData:{}, //请求时添加的额外参数
    base:null, //请求一页显示的文件数量，默认不分页
    renameBtn:{ //设为false移除重命名按钮
      url:'',  //配置ajax操作的url
      data:{}  //可额外添加data参数
    }, 
    downloadBtn:{ //设为false移除下载按钮
      url:''  //下载的url参数的主要部分
      	  
    }, 
    deleteBtn:{//设为false移除删除按钮
      url:'' , //配置ajax操作的url
      data:{}  //可额外添加data参数
    },
    btns:[],//更多按钮及其触发函数 btns:[ ['按钮1','按钮2'],function(){},function() {} ]
    viewFile:{//点击不同类型文件调用的自定义方法，会覆盖默认操作，附带一个fileData参数,function(fileData){},该参数从后台获取的该文件的数据对象
      img:null,
      doc:null,
      ppt:null,
      xls:null,
      pdf:null,
      txt:null,
      video:null,
      rar:null,
      other:null
    },
    search:true,  //是否要搜索功能
    sortMenu:[true,true,true],  //排序分别为添加时间、文件名、大小，设为false移除对应的排序方式
    display:1 //默认的文件展示方式，1为图文网格，2为列表
  },option)

  //初始化
  if(option.renameBtn) $rename.appendTo($btns).on('click',fileRename);
  if(option.downloadBtn) $download.appendTo($btns).on('click',fileDownload);
  if(option.deleteBtn) $delete.appendTo($btns).on('click',fileDelete);
  if(option.height!=='auto') $view.height(parseInt(option.height));
  if(option.uploaderArea&&option.uploaderArea[0]) $uploaderPopup.width(parseInt(option.uploaderArea[0]));
  if(option.uploaderArea&&option.uploaderArea[1]) $uploaderPopup.height(parseInt(option.uploaderArea[1]));
  if(option.sortMenu&&option.sortMenu[0])
    $('<li data-val="sort" class="sort-sort">上传时间</li>').appendTo($sortMenu);
  if(option.sortMenu&&option.sortMenu[1])
    $('<li data-val="fileName" class="sort-filename">文件名</li>').appendTo($sortMenu);
  if(option.sortMenu&&option.sortMenu[2])
    $('<li data-val="size" class="sort-size">大小</li>').appendTo($sortMenu);
  if ($sortMenu.children().length===0)
    $wrap.find('.filemanager-sort-btn').hide();

  if(option.btns&&option.btns[0]){ //更多按钮
    $(option.btns[0]).each(function(i,item) {
      var disabledClass='',txt=item,min=0,max=1000;
      if (typeof item==='object') {
        if (item.text) txt=item.text;
        if (item.min) min=item.min;
        if (item.max) max=item.max;
        if (min>0) {
          disabledClass='disabled';
        }
      }
      var $btn=$('<a href="javascript:void(0)" class="'+disabledClass+'" data-min="'+min+'" data-max="'+max+'"></a>').text(txt).appendTo($myBtns);
      if (typeof option.btns[i+1]==='function')
        $btn.click(function(){
          if ($(this).hasClass('disabled')||$(this).parent().hasClass('disabled')) {
            return;
          }
          option.btns[i+1]();
        });
    });
  }

  //文件上传
  $upload.on('click',openUploader);
  $view.on('click','.upload2',function() {
    $upload.trigger('click');
  });
  $uploaderClose.on('click',closeUploader);

  //修改名称
  $editBox.find('.edit-sure').click(submitRename);
  $editBox.find('.edit-cancel').click(cancelRename);

  //文件查看
  $view.on('click','.view-item-name,.view-item-icon',viewFile);

  //文件排序
  $sortBtn.on('click',function(){ 
    $(this).parent().find('.filemanager-sort-menu li.current').trigger('click');
  });
  //文件排序
  $sortMenu.on('click','li',fileSort);
  $sortMenu.children().eq(0).addClass('current');
  $sortInput.val($sortMenu.children('.current').attr('data-val'));
  $filedataAll.on('click','span',function(){
	    $sortMenu.find('.sort-'+$(this).attr('name')).trigger('click');
	  });
  
  
  //文件检索
  if (option.search) {
    $searchBtn.on('click',function() { queryPage(1,$searchInput.val()); });
  }else{
    $searchBtn.hide();$searchInput.hide();
  }
  //文件展示模式切换
  $display.on('click',fileDisplay);
  if(option.display===2)
    $display.trigger('click');


  queryPage();
  if (option.uploader) {
    var uploader=createUploader($uploader,$.extend({},uploaderOption,{dnd:$wrap})); //上传组件
    uploader.on('uploadFinished',function(file) {
      queryPage();
    });
    uploader.on('fileQueued',function(file) {
      if ($uploaderContainer.hasClass('element-invisible')) {
        openUploader();
      }
    });
    fileManager.uploader=uploader;
  }else{
    $upload.hide();
    fileManager.uploader=null;
  }

  //文件列表加载
  function queryPage(curr,keyword){
    $btns.addClass('disabled');
    $.ajax({
      type: "post",
      url: option.ajaxUrl,
      data: $.extend({},option.ajaxData,{
    	currentPage:curr||1,//当前页
        base:option.base,//若设为数字表示一页显示的数量，默认为null，不分页
        sidx:$sortInput.val(),
        sord:$sortMethor.val(),
        fileName:keyword
      }),
      async: true,
      dataType: "json",
      error: function(request) {
        layer.msg("连接超时");
      },
      success: function(data) {
        //显示分页
        if (data.pages!==1&&data.pages!=='1') {
          $laypage.show();
          laypage({
            cont: $laypage,
            curr: curr||1,
            pages: data.pages,
            groups: 6, 
            prev: '<',
            next: '>',
            skin: '#5fb8ff', //设置信息流模式的样式
            jump: function(obj,first) {
              if (!first) {
                queryPage(obj.curr,keyword);
              }
            }
          });
        }else{
          $laypage.hide();
        }
        var str='';
        $.each(data.list, function(i, item) {
          var ext=item.fileName.match(/\.(\w+)$/)[1];
          var type=matchType(ext);
          var iconClass="fileicon-"+type;
          var bgstyle='';
          if (item.pattern) {
            bgstyle="style='background-image:url("+item.pattern+");background-size:cover;'";
          }
          str += "<div class='view-item' data-itemid='"+item.id+" 'data-type='"+type+"' data-src='"+(typeof(item.bigPattern)!=='undefined'?item.bigPattern:"") + "'>\
                    <div class='view-item-icon "+iconClass+"' "+bgstyle+"></div>\
                    <div class='view-item-name'>"+item.fileName+"</div>\
                    <div class='view-item-addtime'>"+(item.createTime||'')+"</div>\
                    <div class='view-item-checkbox'></div>\
                  </div>";
        });
        $view.html(str);
        if (!data.list||data.list.length===0) {
          //$view.append('<div class="view-no-item">您还没上传过文件哦，点击<a href="javascript:void(0)" class="upload2">上传</a>按钮,或将文件拖到这里</div>');
          $view.append('<div class="view-no-item">无数据记录..</div>');
        }
        fileManager.fileDataList=data.list;
      }
    });
  }

  //文件查看
  function viewFile() {
    var $item=$(this).closest('.view-item');
    var type=$item.data('type');
    if (typeof option.viewFile[type]==='function') {
      option.viewFile[type](fileManager.fileDataList[$item.index()]);
    }else{ 
      switch (type) {
        case 'img':
          var index=$list.find('.view-item[data-type="img"] .view-item-icon').index($item.find('.view-item-icon'));
          showPhoto(index);
          break;
        case 'doc':
        case 'ppt':
        case 'xls':
        case 'pdf':
        case 'txt':
          break;
        case 'video':
          break;
        case 'rar':
        default: return;
      }        
    }
  }
  
  //文件下载
  function fileDownload() {
    if ($(this).closest('.disabled').length!==0) return;
    var l = new Array();
    $view.find('.view-item.item-checked').each(function() {
      l.push($(this).data('itemid'));
    });
    
    window.location.href = option.downloadBtn.url+l;
    
  }

  //批量删除警告
  function fileDelete() {
    if ($(this).closest('.disabled').length!==0) return;
    var l = new Array();
    $view.find('.view-item.item-checked').each(function() {
      l.push($(this).data('itemid'));
    });
    if (l.length > 0) {
      layer.confirm("确认要删除这" + l.length + " 个文件吗？", function(index) {
        submitDel(l);
        layer.close(index);
      });
    } else {
      layer.msg('先选择文件!', {
        icon: 0,
        time: 1000
      });
    }
  }
  //提交删除操作
  function submitDel(ids) {
    $.ajax({
      url: option.deleteBtn.url,
      traditional: true,
      dataType: 'json',
      data: $.extend({},option.deleteBtn.data,{
    	  "ids": ids
      }),
      type: "post",
      cache: false,
      async: false,
      success: function(data) {
        queryPage();
        if (data.status == "y" || data.status == "Y") {
          layer.msg(data.info, {
            icon: 1,
            time: 1000
          });
        } else {
          layer.msg(data.info, {
            icon: 2,
            time: 2500
          });
        }
      },
      error: function(xhr, error) {
        alert(error);
      }
    });
  }

  //开启编辑修改名称
  function fileRename() {
    if ($(this).closest('.disabled').length!==0) return;
    var $currItem=$view.find('.view-item.item-checked');
    $currItem.addClass("editing"); //设置正在编辑状态
    var offset = $currItem.offset();
    var X = offset.top;
    var Y = offset.left;
    var pathName=$currItem.find('.view-item-name').text().match(/^(.+)(\.\w+)$/)[1];
    var ext=$currItem.find('.view-item-name').text().match(/^(.+)(\.\w+)$/)[2];
    $currItem.find('.view-item-name').css('visibility','hidden');
    $currItem.data('ext',ext);
    if ($view.hasClass('display-list')) {
      $editBox.addClass('display-list');
    }else{
      $editBox.removeClass('display-list');
    }
    $editBox.find(".edit-name").val(pathName).focus();
    $editBox.find(".edit-id").val($currItem.data('itemid'));
    $editBox.css({
      top: X + "px",
      left: (Y - 8) + "px",
      display:"block"
    }); //显示修改input框
  }
  //提交名称的修改
  function submitRename() {
    var name = $editBox.find(".edit-name").val()+$view.find('.view-item.editing').data('ext');
    var id = $editBox.find(".edit-id").val();
    if (name && name.length < 1) {
      return;
    }
    if (name.match(/[\/\\\:\*\"\?\|\<\>]/)) {
      layer.msg("文件名不能包含以下字符：\/\\\:\*\"\?\|\<\>", {
        icon: 0,
        time: 1500
      });
      return;
    }
    if (id) {
      $.ajax({
        url: option.renameBtn.url,
        traditional: true,
        dataType: 'json',
        data: $.extend({},option.renameBtn.data,{
          "id": id,
          "name": name
        }),
        type: "post",
        cache: false,
        async: false,
        success: function(data) {
          if (data.status == "y" || data.status == "Y") {
            layer.msg(data.info||"修改成功！", {
              icon: 1,
              time: 1000
            });
            $view.find('.view-item.editing .view-item-name').text(name);
            cancelRename();
          } else {
            layer.msg(data.info||"修改失败！", {
              icon: 2,
              time: 2500
            });
          }
        },
        error: function(error) {
          alert('服务异常');
        }
      });
    }
  }
  /*取消编辑照片名*/
  function cancelRename() {
    $editBox.find(".edit-name").val("");
    $editBox.find(".edit-id").val("");
    $editBox.css("display","none"); //隐藏修改input框
   $view.find('.view-item.editing .view-item-name').css('visibility','visible');
    $view.find('.view-item.editing').removeClass('editing');
  }

  //大图展示图片
  function showPhoto(index) {
    var data = [];
    $list.find('.view-item[data-type="img"]').each(function() {
      data.push({
        "alt": $(this).find('.view-item-name').text(),
        "src": $(this).data('src')
      });
    });
    var json = {
      "title": "",
      "start": index,
      "data": data
    };
    layer.photos({
      photos: json,
      skin: "layer-center",
      shift: 5,
      tab: function(pic, layero) {
        var $img = $(layero).find('img');
        $img.css({
          'width': $img.width() + 'px'
        });
        $(layero).css({
          'height': $img.height() + 'px',
          'width': $img.width() + 'px'
        }).css({
          'top': ($(window).height() - $(layero).height()) / 2 + "px",
          'left': ($(window).width() - $(layero).width()) / 2 + "px"
        });
      }
    });
  }

  //打开上传组件
  function openUploader() {
    $uploaderContainer.removeClass('element-invisible');
    $('body').css('overflow','hidden');
  }  
  //关闭上传组件
  function closeUploader() {
    $uploaderContainer.addClass('element-invisible');
    $('body').css('overflow','auto');
  }

  //文件选择
  $view.on('click','.view-item-checkbox',function() {
    var $item=$(this).closest('.view-item');
    $item.toggleClass('item-checked');
    afterCheck();
  });
  $checkAll.on('click',function() {
    $(this).toggleClass('checked');
    if ($(this).hasClass('checked')) {
      $view.find('.view-item').addClass('item-checked');
    }else{
      $view.find('.view-item').removeClass('item-checked');
    }
    afterCheck();
  });
  
 //文件选择之后调用，计算选择情况
  function afterCheck() {
	    var count=$view.find('.view-item.item-checked').length;
	    $checkCountTips.text("已选中"+count+"个文件");
	    if (count===0) {
	      //没有选择的文件
	      $checkCountTips.hide();
	      $checkAll.find('.check-all-text').show();
	      $btns.addClass('disabled');
	    }else{
	      $checkCountTips.show();
	      $checkAll.find('.check-all-text').hide();
	      if (count===$view.find('.view-item').length) {
	        $checkAll.addClass('checked');//全选
	      }else{
	        $checkAll.removeClass('checked');//未全选
	      }
	      $btns.removeClass('disabled');
	      if (count!==1) {
	        $rename.addClass('disabled');
	        $download.addClass('disabled');
	      }else{
	        $rename.removeClass('disabled');
	        $download.removeClass('disabled');
	      }
    }
  
    //自定义按钮
    $myBtns.find('a').each(function(i,item) {
      if (count<$(item).data('min')||count>$(item).data('max')) {
        $(item).addClass('disabled');
      }else{
        $(item).removeClass('disabled');
      }
    });
  }
	    
	    
	    
	    

  //文件排序
  function fileSort() {
    if (!$(this).hasClass('current')) {
      $(this).closest('.filemanager-sort-btn').removeClass('sort-asc');
      $(this).addClass('current').siblings().removeClass('current');
      $sortInput.val($(this).attr('data-val'));
    }else{
      $(this).closest('.filemanager-sort-btn').toggleClass('sort-asc');
    }
    if ($(this).closest('.filemanager-sort-btn').hasClass('sort-asc')) {
      $sortMethor.val('asc');
    }else{
      $sortMethor.val('desc');
    }
    queryPage();
  }  
  
  
  //文件展示模式切换
  function fileDisplay(){
    if ($display.hasClass('filemanager-display-grid')){
      $display.removeClass('filemanager-display-grid').addClass('filemanager-display-list');
      $view.addClass('display-list');
      $filedataAll.show();
    }
    else {
      $display.removeClass('filemanager-display-list').addClass('filemanager-display-grid');
      $view.removeClass('display-list');
      $filedataAll.hide();
    }
  } 
 

  //根据后缀名匹配文件图标
  function matchType(ext) {
    switch (ext) {
      case 'png':
      case 'gif':
      case 'jpg':
      case 'jpeg':
      case 'bmp':
        return 'img';
      case 'doc':
      case 'docx':
      case 'wps':
        return 'doc';
      case 'ppt':
      case 'pptx':
      case 'dps':
        return 'ppt';
      case 'xls':
      case 'xlsx':
      case 'et':
        return 'xls';
      case 'pdf':
        return 'pdf';
      case 'txt':
        return 'txt';
      case 'rar':
      case 'zip':
        return 'rar';
      case '3gp':
      case 'mp4':
      case 'avi':
      case 'mpv2':
      case 'mpg':
      case 'mpe':
      case 'mpeg':
      case 'rmvb':
      case 'rm':
      case 'asf':
      case 'divx':
      case 'wmv':
      case 'mkv':
      case 'vob':
      case 'flv':
      case 'ogm':
      case 'qt':
        return 'video';
      default:
        return 'other';
    }
  }
  fileManager.queryPage=queryPage;
  fileManager.openUploader=openUploader;
  fileManager.closeUploader=closeUploader;
  fileManager.fileDisplay=fileDisplay;
  fileManager.getSelectFileData=function(){
    var result=[];
    $view.find('.view-item.item-checked').each(function(){
      var index=$(this).index();
      result.push(fileManager.fileDataList[index]);
    });
    return result;
  };
  return fileManager;
}