var editComment = {
    //页面初始化
    init: function (params) {
        //文本编辑器
        var E = window.wangEditor;
        var editor = new E('#editor');
        editor.create();

        //监听提交评论按钮
        $(".sendCom").click(function () {
            //获取文章id
            var articleId = $("#articleId").val();
            //获取评论内容并判断
            var content = editor.txt.text();
            if(content == "" || $.trim(content).length < 15){
                Materialize.toast('内容不足15字', 4000);
                return false;
            }

            //添加评论
            $.ajax({
                url:"/comment",
                type:"post",
                data:{
                    "content":content,
                    "articleId":articleId
                },
                success:function (result) {
                    if(result && result['status']==200) {
                        //提交评论刷新当前页面
                        window.location.reload();
                    }else {
                        //todo:bug
                        window.location.href = "/login.html";
                    }
                },
                error:function (result) {
                    //跳转错误页面
                    alert("err2");
                }
            });
        });

        //监听删除文章
        //todo：属于文章的js，暂时放这里
        $(".delLink").click(function () {
            var articleId = $("#articleId").val();
            $.ajax({
                url:"/article",
                type:"post",
                dataType:"json",
                data:{
                    _method:"DELETE",
                    "articleId":articleId
                },
                success:function (result) {
                    if(result && result['status']==200) {
                        //更新成功返回页面
                        window.location.href = "/articles";
                    }else {
                        //跳转错误页面
                        alert("err1");
                    }
                },
                error:function (result) {
                    //跳转错误页面
                    alert("err2");
                }
            });
        });

        //触发点赞
        $(".secondary-content").click(function () {
            var botton= $(this).find("i");
            if(botton.attr("class")=="material-icons red-text"){
                //取消
                botton.attr("class","material-icons");
                $.get("/comment/vote/"+$(this).prev().val());
            }else {
                //点赞
                //todo:users vote
                botton.attr("class","material-icons red-text");
                $.get("/comment/vote/"+$(this).prev().val());
            }
        })
    }
};