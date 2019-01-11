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