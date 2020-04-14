$(document).ready(function(){
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}

    var token = getUrlParam("token");
    if (token==null||token==""){

    } else {
        localStorage.setItem("token",token);
    }

    $.getJSON("/xszadmin/user/url4?token="+localStorage.getItem("token"),function(json) {
          $("#loginuname").text(json.nickname);
        $("#loginuheadimgurl").attr("src",json.headimgurl);

    });
});