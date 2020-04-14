$(document).ready(function () {



    let page = 0;

    // dropload
    $('.content').dropload({
        scrollArea: window,
        loadDownFn: function (me) {
            page++;
            // 拼接HTML
            var result = '';
            $.ajax({
                type: 'GET',
                url: "/xszadmin/GoldOrder1/findAllByBuyerIdAndAndState/"+1+"/"+page,
                headers:{"token":localStorage.getItem("token")},
                success: function (data) {
                    console.log(data);
                    var content=data.data.content;
                    var arrLen = data.data.content.length;
                    if (arrLen > 0) {
                        for(var i=0;i<content.length;i++  ) {
                            var contentdata = content[i];

                            var id = contentdata.id;
                            var buyerName = contentdata.buyerName;
                            var createTime = contentdata.createTime;
                            var type1 = contentdata.type;
                            var type;
                            switch(type1){
                                case 0:
                                    type="标准";
                                    break;
                                case 1:
                                    type="定制";
                                    break;

                                default:
                                    type="其他";
                                    break;
                            }

                            var state1 = contentdata.state;
                            var state;
                            var label;
                            switch(state1){
                                case 0:
                                    state="待付款";
                                    label="去支付";
                                    break;
                                case 1:
                                    state="待发货";
                                    label="申请退款";
                                    break;
                                case 2:
                                    state="待收货";
                                    label="确认收货";
                                    break;
                                case 3:
                                    state="已完成";
                                    label="查看详情";
                                    break;
                                default:
                                    state="其他";
                                    break;
                            }
                            var totalPrice = contentdata.totalPrice;
                            var orderDetails = contentdata.orderDetails;

                            //
                            // var productName=orderDetails[0].productName;
                            //    var productQuantity=orderDetails[0].productQuantity;

                            console.log(orderDetails);
                            if ( orderDetails.length>1) {
                                $("#tab3").append(
                                    " <div class='weui-panel weui-panel_access'>" +
                                    '<div class="weui-panel__hd"><img src="demo/images/store_icon.png" style="float:left;margin-right:5px;width:22px" alt="">订单类型：' + type + '</div>' +
                                    '<div class="weui-panel__bd">'


                                );
                                for (var j = 0; j < orderDetails.length; j++) {
                                    var productName = orderDetails[j].productName;
                                    var productQuantity = orderDetails[j].productQuantity;
                                    var productPicture = orderDetails[j].productPicture.substr(1);
                                    console.log(productName);
                                    $("#tab3").append(
                                        '<a href="order-detail.html?id='+id+'" class="weui-media-box weui-media-box_appmsg">' +
                                        '<div class="weui-media-box__hd" >' +
                                        '<img class="weui-media-box__thumb" src="' + productPicture + '" alt="">' +
                                        "</div>"+
                                        '<div class="weui-media-box__bd"  >'+
                                        '<h4 class="weui-media-box__title"><span class="weui-cell__ft">x' + productQuantity + '</span>' + productName + '</h4>'+
                                        "</div>"+
                                        "</a>"
                                    );
                                }
                                // '<h4 class="weui-media-box__title"><span class="weui-cell__ft">x' + productQuantity + '</span>' + productName + '</h4>'+
                                $("#tab3").append(
                                    '<p class="weui-media-box__desc">&emsp; &emsp;购买人：' + buyerName + '<br>&emsp; &emsp;时间：' + createTime + '</p>' +
                                    '<p style="font-size:14px;color:#30af08">&emsp; &emsp;订单状态：' +state+ '</p>' +
                                    "</div>" +
                                    "</a>" +
                                    ' <div class="weui-cells weui-cells-tabbar weui-cells-tabbar_tab">'+
                                    ' &emsp; &emsp; &emsp; &emsp;<span name="label" id="label'+id+'">'+label+'</span><span  style="display:none" id = "del_label'+id+'" name="del_label">删除订单</span>'+
                                    " </div>" +
                                    '<div class="weui-cell__bd">总价：<span>' + totalPrice + '</span></div>' +
                                    "</div>"
                                );
                                // console.log("111type1"+type1+"i"+i);
                                // if(type1==0||type1==3){
                                //
                                //     $("#del_label"+i).attr("style","block")
                                // }
                            }else{
                                var productName=orderDetails[0].productName;
                                var productQuantity=orderDetails[0].productQuantity;
                                var productPicture = orderDetails[0].productPicture.substr(1);
                                $("#tab3").append(
                                    " <div class='weui-panel weui-panel_access'>" +
                                    '<div class="weui-panel__hd"><img src="demo/images/store_icon.png" style="float:left;margin-right:5px;width:22px" alt="">订单类型：' + type + '</div>' +
                                    '<a class="weui-panel__bd">' +
                                    '<a href="order-detail.html?id='+id+'" class="weui-media-box weui-media-box_appmsg">' +
                                    '<div class="weui-media-box__hd" >' +
                                    '<img class="weui-media-box__thumb" src="' + productPicture + '" alt="">' +
                                    "</div>" +
                                    '<div class="weui-media-box__bd"  >'+
                                    '<h4 class="weui-media-box__title"><span class="weui-cell__ft">x' + productQuantity + '</span>' + productName + '</h4>'+
                                    '<p class="weui-media-box__desc">购买人：' + buyerName + '<br>时间：' + createTime + '</p>' +
                                    '<p style="font-size:14px;color:#30af08">订单状态：' +state+ '</p>' +
                                    "</div>" +
                                    "</a>" +
                                    ' <div class="weui-cells weui-cells-tabbar weui-cells-tabbar_tab">'+
                                    ' &emsp; &emsp; &emsp; &emsp;<span name="label" id="label'+id+'">'+label+'</span><span  style="display:none" id = "del_label'+id+'" name="del_label">删除订单</span>'+

                                    " </div>" +
                                    '<div class="weui-cell__bd">总价：<span>' + totalPrice + '</span></div>' +
                                    "</div>"
                                );
                            }
                            console.log("type1"+type1+"i"+i);
                            if(state1==0||state1==3){

                                // $("#del_label"+i).attr("style","block")
                                $("#del_label"+id).show();
                            }
                        }
                        // $("span[name='label']").click(function(){
                        //     var id=this.id;
                        //     //从最后面开始，截取一位
                        //     var i = id.substr(5);
                        //     if(content[i].state==0||content[i].state==2){
                        //         var state=content[i].state+1;
                        //         $.ajax({
                        //             type: "POST",
                        //             url: "/xszadmin/GoldOrderX/updatestate/"+ content[i].id+"/"+state,
                        //             success: function(json){
                        //                 location.reload();
                        //                 if(state==1){alert("付款成功");}else if(state==3){alert("已确认收货");}
                        //
                        //
                        //             }
                        //         });
                        //     }else if(content[i].type==1){
                        //         $.ajax({
                        //             type: "DELETE",
                        //             url: "/xszadmin/GoldOrderX/delete/"+ content[i].id,
                        //             success: function(json){
                        //                 console.log(json);
                        //                 if(json.code==200){
                        //                     location.reload();
                        //                     alert("退款成功");
                        //                 }else{
                        //                     alert("下单超过俩个小时，退款失败");
                        //                 }
                        //             }
                        //         });
                        //     }else if(content[i].type==3){
                        //         window.location.href="order-detail.html";
                        //     }
                        // });
                        //
                        //
                        // $("span[name='del_label']").click(function(){
                        //     var id=this.id;
                        //     //从最后面开始，截取一位
                        //     var i = id.substr(9);
                        //
                        //     $.ajax({
                        //         type: "DELETE",
                        //         url: "/xszadmin/GoldOrder1/masterDelete/"+ content[i].id,
                        //         success: function(json){
                        //             location.reload();
                        //             alert("删除成功");
                        //
                        //         }
                        //     });
                        // });
                        // 如果没有数据
                    } else {
                        // 锁定
                        me.lock();
                        // 无数据
                        me.noData();
                    }
                    // 为了测试，延迟1秒加载
                    setTimeout(function () {
                        // 插入数据到页面，放到最后面
                        /*$('#displayDiv').append(result);*/
                        // 每次数据插入，必须重置
                        me.resetload();
                    }, 1000);
                },
                error: function (xhr, type) {
                    alert('Ajax error!');
                    // 即使加载出错，也得重置
                    me.resetload();
                }
            });
        }
    });
});