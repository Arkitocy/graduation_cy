$(document).ready(function () {

    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }

    var token = getUrlParam("token");
    if (token == null || token == "") {

    } else {
        localStorage.setItem("token", token);
    }
    $.ajax({
        url: "index/get",
        type: "GET",
        contentType: 'application/json',
        success: function (msg) {
            console.log(msg)
            if (msg.code == 200) {
                var data = msg.data;
                var pic = data.pic;
                var piclist = pic.split("|");
                var video = data.video.substr("1");
                // $("#pic1").attr("src", piclist[1]);
                // $("#pic2").attr("src", piclist[2]);
                for(var i = 1;i<piclist.length;i++){
                    $("#swip").append("<div class='swiper-slide'><img src='"+piclist[i]+"'/></div>")
                }
                $("#video").append("<video :src='" + video + "' :poster='" + video + "?vframe/jpg/offset/1' preload='auto' controls='controls' webkit-playsinline width='100%' height='100%'></video>")
                $(".swiper-container").swiper({
                    // loop: true,
                    autoplay: 3000
                });
                $(".t_news").swiper({
                    loop: true,
                    autoplay: 5000
                });
            } else {
                alert(msg.msg);
            }
        }
    })



})
