$(document).ready(function(){
    // 在这里写你的代码...
var id=window.location.href.split("?")[1].split("=")[1];
    $.getJSON("/xszadmin/GoldOrder1/findById/"+id, function(json){
        console.log(json);
        var data=json.data;
        var detail=data.orderDetails;
        console.log(detail)
        // if(data.state==0){
        //     $("#pay").show()
        //         $("#enter").hide()
        // }
        // $("#pay").click(function(){
        //     alert("支付出成功");
        // });
        for(var i=0;i<detail.length;i++) {
            $("#productDetail").append(
            "<a href='goodsDetail.html?id="+ detail[i].productId+"' class='weui-media-box weui-media-box_appmsg'>"
             +   "<div class='weui-media-box__hd'>"
               + "<img class='weui-media-box__thumb' src='"+detail[i].productPicture.substr(1)+"' 'alt=''></div>"
                +"<div class='weui-media-box__bd'>"
               + "<h4 class='weui-media-box__title'>"+detail[i].productName+"</h4>"
                +"<p class='weui-media-box__desc'>￥"+detail[i].productPrice+"</p>"
                +"</div>"
                +"</a>"
            );
        }
        $("#user").append(
        "<div class='weui-cell'>"+
        "<div class='weui-cell__bd'>"
          + "<p>用户</p>"
           +"</div>"
        +"<div class='weui-cell__ft'>"+data.buyerName+"</div>"
            +"</div>"
           + "<div class='weui-cell'>"
           + "<div class='weui-cell__bd'>"
            +"<p>地址</p>"
           +"</div>"
            +"<div class='weui-cell__ft'>"+data.buyerAddress+"</div>"
            +"</div>"
            + "<div class='weui-cell'>"
            + "<div class='weui-cell__bd'>"
            +"<p>电话</p>"
            +"</div>"
            +"<div class='weui-cell__ft'>"+data.buyerPhone+"</div>"
            +"</div>"
            + "<div class='weui-cell'>"
            + "<div class='weui-cell__bd'>"
            +"<p>订单创建时间</p>"
            +"</div>"
            +"<div class='weui-cell__ft'>"+data.createTime+"</div>"
            +"</div>"
            +"<div class='weui-cell'>"
            +"<div class='weui-cell__bd'>"
           +"<p>微信支付</p>"
            +"</div>"
            +"<div class='weui-cell__ft' style='color:#f30'>"+data.totalPrice+"</div>"
           +"</div>"
    )
    });

});

