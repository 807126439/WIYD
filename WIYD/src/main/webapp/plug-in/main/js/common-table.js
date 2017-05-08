var DATA_TABLES={
        DEFAULT_OPTION:{
            //默认配置参数，请参考DataTable官网
            bStateSave: true, //状态保存
            aLengthMenu: [10, 20, 40, 60], //更改显示记录数选项
            bLengthChange: true, // 每行显示记录数 
            iDisplayLength: 20, //默认显示的记录数
            bInfo: true, //是否显示页脚信息，DataTables插件左下角显示记录数
            bPaginate: true, //是否显示（应用）分页器
            asStripeClasses: ["odd", "even"],//为奇偶行加上样式，兼容不支持CSS伪类的场合
            bFilter: false,   //禁用原生搜索
            bProcessing: false, //DataTables载入数据时，是否显示‘进度’提示 
            oLanguage: { //国际化配置  
              "sProcessing": "正在获取数据，请稍后...",
              "sLengthMenu": "显示 _MENU_ 条",
              "sZeroRecords": "没有您要搜索的内容",
              "sInfo": "显示 _START_ 到  _END_ 条 ，共 _TOTAL_ 条",
              "sInfoEmpty": "记录数为0",
              "sInfoFiltered": "(当前显示记录 _MAX_ 条)",
              "sInfoPostFix": "",
              "sSearch": "搜索",
              "sUrl": "",
              "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
              }
            }
          },
          COLUMN:{
            //复用列
            CHECKBOX: { //第一列的复选框单元格
                    sTitle:"<input type='checkbox' class='table-check-all'>",
                    sClass: "text-c",
                    bSortable  : false, // 不参与排序
                    bSearchable: false,
                    sWidth: "30px",
                    mDataProp:"id",
                    mRender: function (data, type, row, meta) {
                        return "<input type=\"checkbox\" class=\"table-check-row checkbox\" value=\""+data+"\">";
                    }
                }
          }
      };


$('.table_datatable').on( 'draw.dt', function () {

    //DataTable重绘事件监听
    if (typeof $('.table_datatable').attr('fixtable')==="string"&&$('.ui-page-container').length!==0) {
      //固定表格，重新计算高度
      $('.ui-page-container').addClass('ui-page-fixed');
      if($('.ui-fixedtable-container').length===0) {
         var $cont=$('<div class="ui-fixedtable-container"></div>').append($('.ui-page-container').children()).appendTo($('.ui-page-container'));
       }
      var currentHeight=$('.dataTables_scrollBody').height();
      var spanY=$('.ui-page-container').height()-$('.ui-fixedtable-container').height()-37
      $('.dataTables_scrollBody').height(currentHeight+spanY-1);
      if ($('.table_datatable').hasClass('ui-table-border-col')) {
         $('.dataTables_scrollBody').css({"box-sizing":"border-box","border-right":"1px solid #d1d1d1","border-bottom":"1px solid #d1d1d1"})
      }
    }

    iSelect({
      //select皮肤
     "elem":".dataTables_length select",
     "direct":"up",
     "style":{"display":"inline-block","width":"60px"},
     "extendClass":"size-s"
   })
    $('.table_datatable input').iCheck({
      //checkbox皮肤
      checkboxClass: 'icheckbox_flat-green'
    })
    //全选的实现
    $('.table-check-all').on('ifClicked', function(event){
      if ($(this).parent().is('.checked')) {
        if ($('.table-check-row').parent('.checked').length===$('.table-check-row').length) {
          $('.table-check-row').iCheck('uncheck');
        }
      }else{
        $('.table-check-row').iCheck('check');
      }
    });
    $('.table-check-row').on('ifClicked', function(event){
      if ($(this).parent().is('.checked')) {
        $('.table-check-all').iCheck('uncheck');
      }else if ($('.table-check-row').parent('.checked').length+1===$('.table-check-row').length) {
        $('.table-check-all').iCheck('check');
      }
    });
    
  });



