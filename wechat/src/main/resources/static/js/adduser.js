$(document).ready(function(){
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}


    var token = getUrlParam("token");
    localStorage.setItem("token",token);
    $.getJSON("/xszadmin/user/url4?token="+token,function(json) {
        $("#loginuname").text(json.nickname);
        $("#loginuheadimgurl").attr("src",json.headimgurl);
    });
    $("#usubmit").click(function(){
        let data1 = {
            "name": $("#uname").val(),
            "sex": $("#usex").val(),
            "age": $("#uage").val(),
            "phone": $("#umobile").val()

        };
        $.ajax({
            url: "/xszadmin/user/addMember",
            type: "POST",
            contentType: 'application/json',
            headers:{"token":token},
            data: JSON.stringify(data1),
            success: function (msg) {
               alert("恭喜注册成功！");
                window.location.href ="myinfo.html";
            }
        });
    });

});