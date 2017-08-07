$(document).ready(function () {
    $("#btn_add").click(function () {
        var text = $("#btn_add").html();
        if (text != "发送消息") {
            return;
        }
        if ($("#txt_name").val() == "" || $("#txt_name").val() == "姓名：") {
            alert("请填写姓名！");
            return;
        }
        if ($("#txt_email").val() == "" || $("#txt_email").val() == "邮箱：") {
            alert("请填写邮箱！");
            return;
        }
        if ($("#txt_company").val() == "" || $("#txt_company").val() == "公司：") {
            alert("请填写公司信息！");
            return;
        }
        if ($("#txt_website").val() == "" || $("#txt_website").val() == "网站：") {
            alert("请填写网站信息！");
            return;
        }
        if ($("#txt_message").val() == "") {
            alert("请填写留言信息！");
            return;
        }
        if ($("#txt_message").val().length >500) {
            alert("请填写正确的留言信息！");
            return;
        }
        var parms = {
            action: "message_post",
            name: $("#txt_name").val(),
            email: $("#txt_email").val(),
            company: $("#txt_company").val(),
            website: $("#txt_website").val(),
            postmsg: $("#txt_message").val(),
            _type: $("#hidden_type").val(),
            dt: new Date()
        };
        var isfav = false;
        $.ajax({
            type: "post",
            url: "/ashx/ServiceFormPost.ashx",
            data: parms,
            dataType: "json",
            beforeSend: function () {
                $("#btn_add").html("操作中...");
            },
            success: function (data) {
                if (data != undefined) {
                    if (data.result == 1) {
                        alert('您的留言已提交，谢谢参与！');
                        $("#txt_name").val("");
                        $("#txt_email").val("");
                        $("#txt_company").val("");

                        $("#txt_website").val("");
                        $("#txt_message").val("");
                        $("#hidden_type").val("");
                    }
                    else {
                        alert('留言失败，请刷新页面后再试！');
                        return false;
                    }
                } else {
                    alert('留言失败，请刷新页面后再试！');
                    return false;
                }
            },
            error: function (xhr, errorType, error) {
                alert('留言失败，请刷新页面后再试！');
                return false;
            },
            complete: function () {
                $("#btn_add").html("发送消息");
            }
        });
    });

});