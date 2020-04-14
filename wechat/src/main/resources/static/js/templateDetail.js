$(document).ready(function () {
    var id = getUrlParam("id");
    var url=location.href.split('#')[0];
    var link = "http://arkitocy.ngrok2.xiaomiqiu.cn/xszadmin/templateDetail.html?id="+id;
    var imgURL="http://arkitocy.ngrok2.xiaomiqiu.cn/xszadmin/demo/images/a-03.png";

    $.ajax({
        url:"template/templateDetailShow/"+id,
        type:"GET",
        success:function (data) {
            console.log(data);
            $("#templateName").text(data.name);
            $("#picture1").attr('src', data.picture1);
            $("#picture2").attr('src', data.picture2);
            for(let i = 0; i < data.attributeList.length; i++){
                $("#fb").append(
                    "<option name='"+i+"' value="+data.attributeList[i].price+">"+data.attributeList[i].name+"</option>"
                );
            }

            $("#decrease").click(function () {
                if(parseInt($("#num").val()) == 0){
                    alert("输入错误");
                }
                else{
                    $("#num").val(parseInt($("#num").val())-1);
                }
            });
            $("#increase").click(function () {
                console.log(parseInt($("#num").val())+1);
                $("#num").val(parseInt($("#num").val())+1);
            });


            $("#introduce").html(data.introduce);
            $("#goodsMaterial").text(data.material);
            $("#goodsSize").text(data.size);
            $("#goodsWeight").text(data.weight);

            $("#pictureUpload").click(function () {
                localStorage.setItem("price", $("#fb").val());
                localStorage.setItem("fb", $("#fb").find("option:selected").attr("name"));
                localStorage.setItem("id", id);
                localStorage.setItem("name", data.name);
                localStorage.setItem("num", $("#num").val());
                localStorage.setItem("templateF", data.picture1);
                localStorage.setItem("templateB", data.picture2);
                window.location.href = "custom.html";
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
        var desc= '向您分享'+ $("#templateName").text();
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: 'wx16c4ea9bf1f2a099', // 必填，公众号的唯一标识
            timestamp: timestamp, // 必填，生成签名的时间戳
            nonceStr: nonceStr, // 必填，生成签名的随机串
            signature: signature,// 必填，签名
            jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ'] // 必填，需要使用的JS接口列表
        });
        wx.ready(function(){
            //分享到朋友圈
            wx.onMenuShareTimeline({
                title: '向您分享'+ $("#templateName").text(), // 分享标题
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
                title: title, // 分享标题
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

});

//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}