$(document).ready(function () {

    localStorage.removeItem("price");
    localStorage.removeItem("num");
    localStorage.removeItem("name");
    localStorage.removeItem("pictureUrl");
    localStorage.removeItem("id");

    let id = getUrlParam("id");

    var url=location.href.split('#')[0];
    var link = "http://arkitocy.ngrok2.xiaomiqiu.cn/xszadmin/goodsDetail.html?id="+id;
    var imgURL="http://arkitocy.ngrok2.xiaomiqiu.cn/xszadmin/demo/images/a-03.png";



    $.ajax({
        url:"goodsController/getById/"+id,
        type:"GET",
        success:function (data) {
            console.log(data);

            $("#goodsName").text(data.name);
            for(let i = 0; i < data.pictureList.length; i++){
                $("#pictureDiv").append(
                    '<div class="swiper-slide"><img src="'+data.pictureList[i].path+'" id="pic'+[i]+'"/></div>'
                );
            }
            $("#introduce").text(data.detail);

            $("#decrease").click(function () {
                if(parseInt($("#num").val()) == 0){
                    alert("商品数不能小于0");
                }
                else{
                    $("#num").val(parseInt($("#num").val())-1);
                }
            });
            $("#increase").click(function () {
                console.log(parseInt($("#num").val())+1);
                $("#num").val(parseInt($("#num").val())+1);
            });

            $("#goodsMaterial").text(data.material);
            $("#goodsWeight").text(data.weight);
            $("#goodsSize").text(data.size);

            $("#order").click(function () {
                localStorage.setItem("price",data.price);
                localStorage.setItem("num",$("#num").val());
                localStorage.setItem("name",data.name);
                localStorage.setItem("pictureUrl",data.pictureList[0].path);
                localStorage.setItem("id",id);
                window.location.href = "order.html?flag=0";
            });
        }
    });

    $.ajax({
        url: "wechat/getEncryptJsapiTicket",
        type: "POST",
        data:{"url":encodeURIComponent(url)},
        dataType: "json",
        success: function(data){
            alert(data)
            console.log(data);
            nonceStr=data.nonceStr;
            signature=data.signature;
            timestamp=data.timestamp;
            loadWx();
        },
        error:function(err){
            console.log('异常');
        }
    });

    function loadWx(){
        var title='分享';
        var desc= '向您分享'+ $("#goodsName").text();
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: 'wx16c4ea9bf1f2a099', // 必填，公众号的唯一标识
            timestamp: timestamp, // 必填，生成签名的时间戳
            nonceStr: nonceStr, // 必填，生成签名的随机串
            signature: signature,// 必填，签名
            jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表
        });
        wx.ready(function(){
            //分享到朋友圈
            wx.onMenuShareTimeline({
                title: desc, // 分享标题
                link: link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                imgUrl: imgURL, // 分享图标
                success: function () {
                    alert("分享成功");
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    alert("分享失败");
                    // 用户取消分享后执行的回调函数
                }
            });
            //分享到朋友
            wx.onMenuShareAppMessage({
                title: desc, // 分享标题
                desc: desc, // 分享描述
                link: link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                imgUrl:imgURL, // 分享图标
                type: 'link', // 分享类型,music、video或link，不填默认为link
                dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                success: function () {
                    alert("分享成功");
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    alert("分享失败");
                    // 用户取消分享后执行的回调函数
                }
            });
            //分享到QQ
            wx.onMenuShareQQ({
                title: title, // 分享标题
                desc: desc, // 分享描述
                link: link, // 分享链接
                imgUrl: imgURL, // 分享图标
                success: function () {
                    alert("分享成功");
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    alert("分享失败");
                    // 用户取消分享后执行的回调函数
                }
            });



        });
        wx.error(function(res){
            alert("验证失败了");
        });

    }

    function isIosOrAndroid() {
        let u = navigator.userAgent;
        let isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1 // android终端
        let isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/) // ios终端
        let isStr = ''
        if (isAndroid) {
            isStr = 'android'
        }
        if (isiOS) {
            isStr = 'ios'
        }
        return isStr

    }





});





//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}