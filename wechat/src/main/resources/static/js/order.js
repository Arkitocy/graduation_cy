$(document).ready(function () {
    /**
     * 标识符 0获取默认地址  1选择地址
     * @type {string}
     */
    let flag = getUrlParam("flag");
    let name = localStorage.getItem("name");
    let price = localStorage.getItem("price");
    let num = localStorage.getItem("num");
    let orderId = getUrlParam("orderId");
    let pictureUrl = localStorage.getItem("pictureUrl");
    console.log(orderId);
    $("#goodsImg").attr("src", pictureUrl);
    $("#goodsTitle").text(name);
    $("#goodsPrice").text("￥" + parseInt(price) * parseInt(num));
    $("#sumPrice").text("消费金额：￥" + parseInt(price) * parseInt(num));
    $("#goodsNum").attr("value", num);

    if ("0" === flag) {
        /**
         * 获取默认地址
         */
        $.ajax({
            url: "address/addressDefaultVal",
            type: "GET",
            headers:{"token":localStorage.getItem("token")},
            success: function (msg) {
                let detailAddr = msg.data.addressName + msg.data.addressNameKid + msg.data.addressNameKid2 + msg.data.area;
                $("#consignee").text(msg.data.name);
                $("#ConsigneeTel").text(msg.data.mobile);
                $("#ConsigneeAddr").text(detailAddr);
            }
        });
    } else if ("1" === flag) {
        $("#consignee").text(localStorage.getItem("addressPerson"));
        $("#ConsigneeTel").text(localStorage.getItem("addressMobile"));
        $("#ConsigneeAddr").text(
            localStorage.getItem("addressProvince") +
            localStorage.getItem("addressCity") +
            localStorage.getItem("addressDistrict") +
            localStorage.getItem("addressArea")
        );
    }


    $("#decrease").click(function () {
        if (parseInt($("#goodsNum").val()) == 1) {
            alert("商品数不能小于1");
        } else {
            $("#goodsNum").val(parseInt($("#goodsNum").val()) - 1);
            $("#goodsPrice").text("￥" + parseInt(price) * parseInt($("#goodsNum").val()));
            $("#sumPrice").text("消费金额：￥" + parseInt(price) * parseInt($("#goodsNum").val()));
        }
    });
    $("#increase").click(function () {
        $("#goodsNum").val(parseInt($("#goodsNum").val()) + 1);
        $("#goodsPrice").text("￥" + parseInt(price) * parseInt($("#goodsNum").val()));
        $("#sumPrice").text("消费金额：￥" + parseInt(price) * parseInt($("#goodsNum").val()));
    });
    $("#consigneeContactAddress").click(function () {
        localStorage.removeItem("addressPerson");
        localStorage.removeItem("addressMobile");
        localStorage.removeItem("addressProvince");
        localStorage.removeItem("addressDistrict");
        window.location.href = "addressIndex.html?midupdate=0";
    });

    $("#backIcon").click(function () {
        if (null == orderId || "" == orderId) {
            window.location.href = "goodsDetail.html?id=" + localStorage.getItem("id");
        } else {
            window.location.href = "custom.html?orderId=" + orderId;
        }
    });

    $("#submit").click(function () {
        let data1 = {
            "orderId": orderId,
            "buyerName": localStorage.getItem("addressPerson"),
            "buyerPhone": localStorage.getItem("addressMobile"),
            "buyerAddress": localStorage.getItem("addressProvince") +
                localStorage.getItem("addressCity") +
                localStorage.getItem("addressDistrict") +
                localStorage.getItem("addressArea"),
            "productId": localStorage.getItem("id"),
            "totalPrice": parseInt(price) * parseInt($("#goodsNum").val()),
            "productName": localStorage.getItem("name"),
            "productPrice": localStorage.getItem("price"),
            "productQuantity": localStorage.getItem("num"),
            "productPicture": localStorage.getItem("pictureUrl"),
            "fb": localStorage.getItem("fb"),
            "templateF": localStorage.getItem("templateF"),
            "templateB": localStorage.getItem("templateB")
        };
        $.ajax({
            url: "GoldOrder1/saveOrder",
            type: "POST",
            contentType: 'application/json',
            headers:{"token":localStorage.getItem("token")},
            data: JSON.stringify(data1),
            success: function (msg) {
                if (msg.code == 200) {
                    localStorage.removeItem("name");
                    localStorage.removeItem("price");
                    localStorage.removeItem("num");
                    localStorage.removeItem("pictureUrl");
                    localStorage.removeItem("id");
                    localStorage.removeItem("fb");
                    localStorage.removeItem("templateF");
                    localStorage.removeItem("templateB");
                    window.location.href = "successmsg.html"
                } else {
                    alert(msg.msg);
                }
            }
        });


    });


});

//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}
