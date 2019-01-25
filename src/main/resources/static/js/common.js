//打开侧边栏
$("#button-collapse").sideNav();

//退出
$("#logout").click(function () {
    $.ajax({
        url:"/session",
        type:"delete",
        success:function (result) {
            if(result&&result['status']==200) {
                //跳转到首页
                window.location.href = "/";
            }
        },
        error:function () {
            //退出时错误
            alert("退出时错误");
        }
    });
});

//鼠标样式
$(document).ready(function(){
    var a_index = 0;
    $("body").click(function(e){
        var a = new Array("❤");
        var $i = $("<span/>").text(a[a_index]);
        a_index = (a_index + 1) % a.length;
        var x = e.pageX,y = e.pageY;
        $i.css({
            "z-index": 99999,
            "top": y - 20,
            "left": x,
            "position": "absolute",
            "font-weight": "bold",
            "color": "#ff6651"
        });
        $("body").append($i);
        $i.animate({"top": y-180,"opacity": 0},1500,function() {
            $i.remove();
        });
    });
});