var editArticle = {
    //页面初始化
    init: function (params) {
        var cateName = "";//分类名称

        //文本编辑器
        var E = window.wangEditor;
        var editor = new E('#editor');
        editor.create();
        //下拉列表
        //$('.dropdown-button').dropdown('open');

        //标题输入栏
        $('.chips').material_chip();
        $('.chips-placeholder').material_chip({
            placeholder: 'Enter a title',
            secondaryPlaceholder: '输入文章标题'
        });

        //监听发布按钮
        $(".sendBtn").click(function () {
            //获取分类
            cateName = $(".dropdown-button").text();
            //获取id
            var articleId = $("#articleId").val();
            //获取标题
            var title = $("#title").val();
            //标题不为空时更新
            if(articleId&&articleId!="") {
                //更新操作
                editArticle.updateArticle(editor.txt.text(),editor.txt.html(),title,cateName,articleId);
            }else {
                //新增操作
                editArticle.saveArticle(editor.txt.text(),editor.txt.html(),title,cateName);
            }
        });

        //设置分类按钮名称
        $("#dropdown1").on("click", "li a", function () {
            cateName = $(this).text();
            $(".dropdown-button").text(cateName);
        });
    },
    //发布文章
    saveArticle:function (content,contentHtml,title,cateName) {
        $.ajax({
            url:"/article",
            type:"post",
            data:{
                "content":content,
                "contentHtml":contentHtml,
                "title":title,
                "cateName":cateName
            },
            success:function (result) {
                if(result && result['status']==200) {
                    //发布文章后，跳转到首页
                    window.location.href = "/articles";
                }else {
                    //跳转错误页面
                    alert("err1");
                    window.location.href = "http://www.baidu.com";
                }
            },
            error:function (result) {
                //跳转错误页面
                alert("err2");
                window.location.href = "http://www.baidu.com";
            }
        });

    },
    //修改文章
    updateArticle:function (content,contentHtml,title,category,articleId) {
        $.ajax({
            url:"/article",
            type:"put",
            data:{
                "content":content,
                "contentHtml":contentHtml,
                "title":title,
                //category
                "articleId":articleId
            },
            success:function (result) {
                if(result['status']==200&&result) {
                    //更新成功返回页面
                    window.location.href = "/articles";
                }else {
                    //跳转错误页面
                    alert("err1");
                    window.location.href = "http://www.baidu.com";
                }
            },
            error:function (result) {
                //跳转错误页面
                alert("err2");
                window.location.href = "http://www.baidu.com";
            }
        });
    }
};