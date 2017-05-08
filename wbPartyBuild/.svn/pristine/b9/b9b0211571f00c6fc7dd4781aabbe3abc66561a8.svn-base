	
	window.onload=function(){
		var tempName = window.document.location.pathname;
		var path = tempName.substring(0,tempName.substr(1).indexOf('/')+1);
		
		  //网站导航初始化
	    $('.websitenav').load(path+'/webSiteNav.do #websitenav-menu',function() {
	      $('.websitenav .sidemenu-menu').hover(function() {
	        updateNav(this);
	      });
	      updateNav($('.websitenav-menu>.sidemenu-menu:first'));
	      function updateNav(el) {
	        var $curr=$(el);
	        $curr.addClass('opened').siblings().removeClass('opened');
	        $curr.find('.sidemenu-menu').removeClass('opened');
	        $curr.find('.sidemenu-list').find('.sidemenu-menu:first').addClass('opened');
	      }
	    });

	    //搜索条select初始化
	    $('.my-select').each(function() {
	      var $box=$('<div class="my-select-box"><span class="my-select-box-select"></span><ul class="my-select-box-list"></ul></div>');
	      $(this).after($box);
	      $box.append($(this));
	      $(this).find('option').each(function(i,item) {
	        $box.find('.my-select-box-list').append($('<li></li>').text($(item).text()));
	      });
	      $box.find('.my-select-box-select').text($(this).find('option:selected').text());
	      $box.find('.my-select-box-list li').eq($(this).find('option:selected').index()).addClass('selected');
	      $box.find('.my-select-box-list').on('click','li',function () {
	        $(this).addClass('selected').siblings().removeClass('selected');
	        $box.find('.my-select-box-select').text($(this).text());
	        $box.find('select').val( $box.find('option').eq($(this).index()).val());
	      });
	    });
	};
