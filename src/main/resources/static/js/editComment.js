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
    }
};