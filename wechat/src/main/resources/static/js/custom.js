$(document).ready(function () {
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }


    function FisHasImg( src ){
        var img = new Image();
        img.src = src;
        img.onload = function(){
            if( img.width > 0 || img.height > 0  ){
                $('#resultFront').attr('src', src);
            }
            else{
                $('#resultFront').attr('src', templateF);
            }
        }

        img.onerror = function(){
            $('#resultFront').attr('src', templateF);
        }
    }
    function BisHasImg( src ){
        var img = new Image();
        img.src = src;
        img.onload = function(){
            if( img.width > 0 || img.height > 0  ){
                $('#resultBack').attr('src', src);
            }
            else{
                $('#resultBack').attr('src', templateB);
            }
        }

        img.onerror = function(){
            $('#resultBack').attr('src', templateB);
        }
    }




    var orderId = getUrlParam("orderId");
    var templateF = localStorage.getItem("templateF");
    var templateB = localStorage.getItem("templateB");
    var type = localStorage.getItem("fb");
    if (type == "1") {
        $("#front").hide();
    }else if (type=="0") {
        $("#back").hide();
    }else {

    }


    FisHasImg("userfiles/customsave/" + orderId + "-result-A.jpg");
    BisHasImg("userfiles/customsave/" + orderId + "-result-B.jpg");


    $("#frontBtn").click(function () {
        window.location.href = "customF1.html?orderId=" + orderId;
    })


    $("#backBtn").click(function () {
        window.location.href = "customB1.html?orderId=" + orderId;
    })

    $("#back1").click(function () {
        window.location.href="templateDetail.html?id="+localStorage.getItem("id");
    })

    $("#next").click(function () {
        window.location.href="order.html?orderId="+orderId+"&flag=0";
    })
})