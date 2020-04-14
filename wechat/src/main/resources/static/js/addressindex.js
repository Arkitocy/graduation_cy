$(document).ready(function () {
    getAddress();
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }
    // var midupdate=getUrlParam("midupdate");
    var midupdate=getUrlParam("midupdate");
    $("#addAddressBtn").click(function () {
        window.location.href="addressSAU.html?aid=false&midupdate="+midupdate;
    });
    function getAddress() {
        $.ajax({
            type: "GET",
            url: "address/AddressAllByMid",
            headers:{"token":localStorage.getItem("token")},
            contentType: 'application/json',
            success: function (json) {
                console.log(json);
                var contentdata=json.data;
                $("#addressdiv").empty();

                if("1"===midupdate){
                    $("#menuDiv").show();
                    for(let i=0;i<contentdata.length;i++){
                        if (contentdata[i].defaultVal === false) {
                            var defaultValtitle = "";
                        } else if (contentdata[i].defaultVal === true) {
                            var defaultValtitle = "默认";
                        }
                        $("#addressdiv").append(
                            '<a class="weui-cell weui-cell_access" id="htmla1'+contentdata[i].id+'" name="addresshtmla1">'
                            +'<div class="weui-cell__hd"><img src="Demo2/images/nav-4.png" alt="" style="width:20px;margin-right:5px;display:block;float:left;margin-top:3px">'
                            +'</div>'
                            +'<div class="weui-cell__bd"><h4 class="weui-media-box__title">'+contentdata[i].name+'&nbsp&nbsp'+contentdata[i].mobile
                            +'<span class="weui-cell__ft"></span></h4>'
                            + '<small class="form-text text-muted ml-3" id="small">' + defaultValtitle
                            + '</small>'
                            +'<p class="weui-media-box__desc">'+contentdata[i].addressName+'&nbsp&nbsp'+contentdata[i].addressNameKid+'&nbsp&nbsp'+contentdata[i].addressNameKid2
                            +'</p>'
                            +'<p class="weui-media-box__desc weui-media-box__desc_second">'+contentdata[i].area
                            +'</p>'
                            +'<p class="weui-media-box__title weui-media-box__title_second">查看详情</p>'
                            +'</div></a>'
                        );
                    }
                    $("a[name='addresshtmla1']").click(function(){
                        var thisId=this.id;
                        var aid=thisId.slice(6);
                        window.location.href="addressSAU.html?aid="+aid+"&midupdate="+midupdate;
                    });
                }else if("0"===midupdate){
                    $("#menuDiv").hide();
                    for(let i=0;i<contentdata.length;i++){
                        if (contentdata[i].defaultVal === false) {
                            var defaultValtitle = "";
                        } else if (contentdata[i].defaultVal === true) {
                            var defaultValtitle = "默认";
                        }
                        $("#addressdiv").append(
                            '<a class="weui-cell weui-cell_access" id="htmla2'+contentdata[i].id+'" name="addresshtmla2">'
                            +'<div class="weui-cell__hd"><img src="Demo2/images/nav-4.png" alt="" style="width:20px;margin-right:5px;display:block;float:left;margin-top:3px">'
                            +'</div>'
                            +'<div class="weui-cell__bd"><h4 class="weui-media-box__title">'+contentdata[i].name+'&nbsp&nbsp'+contentdata[i].mobile
                            +'<span class="weui-cell__ft"></span></h4>'
                            + '<small class="form-text text-muted ml-3" id="small">' + defaultValtitle
                            + '</small>'
                            +'<p class="weui-media-box__desc">'+contentdata[i].addressName+'&nbsp&nbsp'+contentdata[i].addressNameKid+'&nbsp&nbsp'+contentdata[i].addressNameKid2
                            +'</p>'
                            +'<p class="weui-media-box__desc weui-media-box__desc_second">'+contentdata[i].area
                            +'</p>'
                            +'</div></a>'
                        );
                    }
                    $("a[name='addresshtmla2']").click(function(){
                        var thisId=this.id;
                        var aid=thisId.slice(6);
                        $.ajax({
                            type: "GET",
                            url: "address/findAddressByid/" +aid ,
                            headers:localStorage.getItem("token"),
                            contentType: 'application/json',
                            success: function (json) {
                                var addressvar = json.data;
                                var storage=window.localStorage;
                                storage.setItem("addressPerson",addressvar.name);
                                storage.setItem("addressMobile",addressvar.mobile);
                                storage.setItem("addressProvince",addressvar.addressName);
                                storage.setItem("addressCity",addressvar.addressNameKid);
                                storage.setItem("addressDistrict",addressvar.addressNameKid2);
                                storage.setItem("addressArea",addressvar.area);
                                storage.setItem("addressDefaultValid",addressvar.defaultVal);
                                window.location.href="order.html?flag=1";
                            }
                        }, 5);
                    });
                }
            }
        })
    }
})