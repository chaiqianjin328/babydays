/**
 * 引入popup组件
 */
function mPopup(){
  $(".m-popup .close,.m-popup .cover").click(function(){
      $(this).parents(".m-popup").hide();
      //自动暂停
	  var myPlayer = videojs('my-video');
	  myPlayer.pause();
  })
}
function mPopupTip(){
  $(".m-popup-tip .close,.m-popup-tip .cover").click(function(){
      $(this).parents(".m-popup-tip").hide();
  })
}
function mPopupImg(){
  $(".m-popup-img .cover").click(function(){
      $(this).parents(".m-popup-img").hide();
  })
}
function selectAll(){
  $(".m-table thead .t-checkbox input").change(function(){
    if($(this).is(':checked')){
      $(this).parents(".m-table").find("td").find(".t-checkbox").find("input").prop("checked",true);
    }else {
      $(this).parents(".m-table").find("td").find(".t-checkbox").find("input").prop("checked",false);
    }
  })

  $(".m-table tbody .t-checkbox input").change(function(){
    $(this).parents(".m-table").find("th").find(".t-checkbox").find("input").prop("checked",false);
  })

}

function switchBtn(){
  $(".m-switch-btn a").click(function(){
    $(this).parents(".m-switch-btn").children("a").removeClass("active");
    $(this).addClass("active");
  })
}

function scrollShow(){
  $(".m-scroll-show .prev").click(function(){
    
  })
}

function tab(){
  $(".m-tab .tab-hd >a,.m-tab-2 .tab-hd >a,.m-tab-3 .tab-hd >a").click(function(){
    var index = $(this).index();
    $(this).parents(".tab-hd").children("a").removeClass("active");
    $(this).addClass("active");
    $(this).parents(".m-tab").next(".tab-bd").children("div").removeClass("active");
    $(this).parents(".m-tab").next(".tab-bd").children("div").eq(index).addClass("active");

  })
}

function folder(){
  $(".m-folder .hd .right a").click(function(){
    if($(this).parents(".m-folder").hasClass("zhedie")){
      $(this).parents(".m-folder").children(".bd").css("height","auto");
      $(this).parents(".m-folder").removeClass("zhedie");
    }else {
      $(this).parents(".m-folder").children(".bd").css("height","0px");
      $(this).parents(".m-folder").addClass("zhedie");
    }

  })
}

function scrollShow(){
  $(".m-scroll-show .next").click(function(){
    var index = $(".m-scroll-show .active").index();
    //最后一个
    if(index ==$(this).parents(".content").children(".box").children(".item").length-1){
      return;
    }
    $(".m-scroll-show .active").removeClass("active");
    $(".m-scroll-show .item").eq(index+1).addClass("active");
    var ml = -index*125;
    $(".m-scroll-show .box").css("margin-left",ml+'px')
  })
  $(".m-scroll-show .prev").click(function(){
    var index = $(".m-scroll-show .active").index();
    //第一个
    if(index ==0){
      return;
    }
    $(".m-scroll-show .active").removeClass("active");
    $(".m-scroll-show .item").eq(index-1).addClass("active");
    var ml = (-index+1)*125;
    $(".m-scroll-show .box").css("margin-left",ml+'px')
  })

  $(".m-scroll-show .item").not(".active").click(function(){
    var index = $(this).index();
    $(".m-scroll-show .active").removeClass("active");
    $(this).addClass("active");
    var ml = (-index+1)*125;
    $(".m-scroll-show .box").css("margin-left",ml+'px')
  })

}