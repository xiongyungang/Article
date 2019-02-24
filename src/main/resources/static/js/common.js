//打开侧边栏
$("#button-collapse").sideNav();

//退出
$("#logout").click(function () {
    $.ajax({
        url:"/session",
        type:"post",
        dataType:"json",
        data:{
            _method:"DELETE"
        },
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

var Cookie = {
    //发送验证码时添加cookie
    addCookie: function (name, value, expiresHours) {

        var cookieString = name + "=" + escape(value);
        //判断是否设置过期时间,0代表关闭浏览器时失效
        if (expiresHours > 0) {
            var date = new Date();
            date.setTime(date.getTime() + expiresHours * 1000);
            cookieString = cookieString + ";expires=" + date.toUTCString();
        }
        document.cookie = cookieString;

    },
    //修改cookie的值
    editCookie: function (name, value, expiresHours) {
        var cookieString = name + "=" + escape(value);
        if (expiresHours > 0) {
            var date = new Date();
            date.setTime(date.getTime() + expiresHours * 1000); //单位是毫秒
            cookieString = cookieString + ";expires=" + date.toGMTString();
        }
        document.cookie = cookieString;
    },
    //根据名字获取cookie的值
    getCookieValue: function (name) {
        var strCookie = document.cookie;
        var arrCookie = strCookie.split("; ");
        for (var i = 0; i < arrCookie.length; i++) {
            var arr = arrCookie[i].split("=");
            if (arr[0] == name) {
                return unescape(arr[1]);
                break;
            }
        }
    }
};

$('.to_top').click(function(){$('html,body').animate({scrollTop: '0px'}, 800);});
$('.to_home').click(function(){location.href = "/";});
$('.to_list').click(function(){location.href="/articles"});
$('.to_write').click(function(){location.href="/postArticle"});
