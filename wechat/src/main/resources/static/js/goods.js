$(document).ready(function () {
    $.ajax({
        url:"goodsController/getAll",
        type:"GET",
        success: function(data){
            console.log(data);
            for(let i = 0; i < data.goodsShowDtoList.length; i++){
                $("#displayDiv").append(
                    "<a class='weui-list-theme-item'>" +
                    "   <div class='weui-list-img'>" +
                    "       <img src='"+data.goodsShowDtoList[i].picture+"' alt='' id='"+data.goodsShowDtoList[i].id+"'>" +
                    "   </div>" +
                    "   <div class='weui-list-title'>" +
                    "       <h3 id='templateTitle'>"+data.goodsShowDtoList[i].name+"</h3>" +
                    "       <div class='weui-flex'>"+
                    "           <div class='weui-flex-box'>" +
                    "               <span id='templateDetail'>点击图片查看详情</span>" +
                    "           </div>" +
                    "       </div>" +
                    "   </div>"+
                    "</a>"
                );

                $("#"+data.goodsShowDtoList[i].id+"").click(function () {
                    window.location.href = "goodsDetail.html?id="+data.goodsShowDtoList[i].id;
                });
            }
        }
    });
});