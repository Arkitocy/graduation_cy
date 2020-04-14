$(document).ready(function () {
    // var storage=window.localStorage;
    // var a=storage.getItem("addressPerson");
    // console.log("storage-------"+a);
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }
    var aid=getUrlParam("aid");
    var midupdate=getUrlParam("midupdate");
    console.log("aid-----"+aid);
    if(aid!=="false"){
        $("#aidid").val(aid);
        $("#deleteBtn").show();
        getAddress(aid);
    }else if("false"===aid){
        $("#deleteBtn").hide();
        addAddress();
    }


    function getAddress(aid) {
        $.ajax({
            type: "GET",
            url: "address/findAddressByid/" +aid ,
            headers:{"token":localStorage.getItem("token")},
            contentType: 'application/json',
            success: function (json) {
                console.log(json.data);
                var addressvar = json.data;
                $("#ondiv").attr("aria-disabled", true);
                $("#ondiv2").attr("aria-disabled", true);
                $('#distpicker').distpicker({
                    province: addressvar.addressName,
                    city: addressvar.addressNameKid,
                    district: addressvar.addressNameKid2
                });
                $("#defaultValid").val(addressvar.defaultVal);
                $("#addressmobileName").val(addressvar.name);
                $("#addressmobile").val(addressvar.mobile);
                $("#addressarea").val(addressvar.area);

                if (false === addressvar.defaultVal) {
                    // $("#offdiv").show();
                    // $("#ondiv").hide();
                    $("#ondiv").addClass("off");
                    $("#ondiv2").attr("data-state", "off");
                } else if (true === addressvar.defaultVal) {
                    // $("#ondiv").show();
                    // $("#offdiv").hide();
                    $("#ondiv").addClass("on");
                    $("#ondiv2").attr("data-state", "on");
                }
                $("#ondiv").click(function () {
                    toogle(this);
                });
            }
        }, 5);
    }

    function addAddress() {
        $("#ondiv").attr("aria-disabled", true);
        $("#ondiv2").attr("aria-disabled", true);
        $("#defaultValid").val(false);
        $("#ondiv").addClass("off");
        $("#ondiv2").attr("data-state", "off");
        $("#ondiv").click(function () {
            toogle(this);
        });
        $('#distpicker').distpicker({
            province: "---- 所在省 ----",
            city: "---- 所在市 ----",
            district: "---- 所在区 ----"
        });
    }

    /**
     * 修改保存地址
     */
    $("#saveBtn").click(function () {
        console.log($("#addressmobileName").val()==="");
     if($("#addressmobileName").val()==="" || $("#addressmobile").val()==="" || $("#addressarea").val()===""){
         alert("请将信息填写完整");
     }else{
         if(aid!=="false"){
             let data1 = {
                 "aid": $("#aidid").val(),
                 "person": $("#addressmobileName").val(),
                 "mobile": $("#addressmobile").val(),
                 "addressName": $("#province").val(),
                 "addressNameKid": $("#city").val(),
                 "addressNameKid2": $("#district").val(),
                 "area": $("#addressarea").val(),
                 "defaultVal": $("#defaultValid").val()
             };
             $.ajax({
                 type: "POST",
                 url: "address/updateAddress",
                 contentType: 'application/json',
                 headers:{"token":localStorage.getItem("token")},
                 data: JSON.stringify(data1),
                 success: function (json) {
                     console.log(json);
                     if ("修改成功" === json.msg) {
                         alert("修改成功")
                         window.location.href="addressIndex.html?midupdate="+midupdate;
                     } else {
                         alert("修改失败")
                     }
                 }
             }, 5);
         }else if("false"===aid){
             console.log($("#defaultValid").val());
             let data2 = {
                 "person": $("#addressmobileName").val(),
                 "mobile": $("#addressmobile").val(),
                 "addressName": $("#province").val(),
                 "addressNameKid": $("#city").val(),
                 "addressNameKid2": $("#district").val(),
                 "area": $("#addressarea").val(),
                 "defaultVal": $("#defaultValid").val()
             };
             $.ajax({
                 type: "POST",
                 url: "address/addAddress",
                 headers:{"token":localStorage.getItem("token")},
                 contentType: 'application/json',
                 data: JSON.stringify(data2),
                 success: function (json) {
                     console.log(json);
                     if ("添加成功" === json.msg) {
                         alert("添加成功");
                         window.location.href="addressIndex.html?midupdate="+midupdate;
                     } else {
                         alert("添加失败")
                     }
                 }
             }, 5);
         }
     }




    });

    /**
     * 地址删除按钮
     */
    $("#deleteBtn").click(function () {
        let data = {
            "aid": aid
        };
        $.ajax({
            type: "POST",
            url: "address/deleteAddress",
            contentType: 'application/json',
            headers:{"token":localStorage.getItem("token")},
            data: JSON.stringify(data),
            success: function (json) {
                console.log(json);
                if ("删除成功" === json.msg) {
                    alert("删除成功");
                    window.location.href="addressIndex.html";
                } else {
                    alert("删除失败")
                }
            }
        }, 5);
    });


});


function toogle(th) {
    var ele = $(th).children(".move");
    if (ele.attr("data-state") == "on") {
        ele.animate({left: "0"}, 300, function () {
            ele.attr("data-state", "off");
            $("#defaultValid").val(false);
        });
        $(th).removeClass("on").addClass("off");
    } else if (ele.attr("data-state") == "off") {
        ele.animate({left: '60px'}, 300, function () {
            $(this).attr("data-state", "on");
            $("#defaultValid").val(true);
        });
        $(th).removeClass("off").addClass("on");
    }
}