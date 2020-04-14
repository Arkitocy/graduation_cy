$(document).ready(function () {
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }

    var orderId = getUrlParam("orderId");

    var templateSrc = localStorage.getItem("templateB");
    $("#img").attr('src', templateSrc);
    // 修改自官方demo的js
    var initCropper = function (img, input) {
        var $image = img;
        var options = {
            aspectRatio: 1, // 纵横比
            viewMode: 2,
            preview: '.img-preview' // 预览图的class名
        };
        $image.cropper(options);
        var $inputImage = input;
        var uploadedImageURL;
        if (URL) {
            // 给input添加监听
            $inputImage.change(function () {
                var files = this.files;
                var file;
                if (!$image.data('cropper')) {
                    return;
                }
                if (files && files.length) {
                    file = files[0];
                    // 判断是否是图像文件
                    if (/^image\/\w+$/.test(file.type)) {
                        // 如果URL已存在就先释放
                        if (uploadedImageURL) {
                            URL.revokeObjectURL(uploadedImageURL);
                        }
                        uploadedImageURL = URL.createObjectURL(file);
                        // 销毁cropper后更改src属性再重新创建cropper
                        $image.cropper('destroy').attr('src', uploadedImageURL).cropper(options);
                        $inputImage.val('');
                    } else {
                        window.alert('请选择一个图像文件！');
                    }
                }
            });
        } else {
            $inputImage.prop('disabled', true).addClass('disabled');
        }
    }


    $(function () {
        initCropper($('#photo'), $('#input'));
    });


    $("#clickbtn").click(function () {
        var $image = $('#photo');
        var $target = $('#result');
        $image.cropper('getCroppedCanvas', {
            width: 300, // 裁剪后的长宽
            height: 300
        }).toBlob(function (blob) {
            //上传数据库
            var fd = new FormData();
            fd.append("file", blob);
            fd.append("orderId", orderId);
            fd.append("templateSrc", templateSrc);
            $.ajax({
                url: "custom/uploadImgBack",
                dataType: 'json',
                type: "POST",
                data: fd,
                processData: false,//必须加
                contentType: false,//必须加
                success: function (data) {
                    console.log(data);
                    console.log(data);
                    if (data.code == 500) {
                        alert("裁剪失败");
                    } else {
                        orderId = data.data.templateId;
                        $target.attr('src', data.data.templateBlob);
                    }
                },
                error: function () {

                }
            });
        });
    })


    $("#backbtn").click(function () {
        window.location.href = "custom.html?orderId=" + orderId;
    })
})