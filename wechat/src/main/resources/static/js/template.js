$(document).ready(function () {


    $.ajax({
        url:"template/templateShow",
        type:"GET",
        success:function (msg) {
            console.log(msg);
            for(let i = 0; i < msg.templateShowDtoList.length; i++){
                $("#displayDiv").append(
                    "<a class='weui-list-theme-item'>" +
                    "   <div class='weui-list-img'>" +
                    "       <img src='"+msg.templateShowDtoList[i].picture1+"' alt='' id='"+msg.templateShowDtoList[i].id+"'>" +
                    "   </div>" +
                    "   <div class='weui-list-title'>" +
                    "       <h3 id='templateTitle'>"+msg.templateShowDtoList[i].name+"</h3>" +
                    "       <div class='weui-flex'>"+
                    "           <div class='weui-flex-box'>" +
                    "               <span id='templateDetail'>点击图片查看详情</span>" +
                    "           </div>" +
                    "       </div>" +
                    "   </div>"+
                    "</a>"
                );


                $("#"+msg.templateShowDtoList[i].id+"").click(function () {
                    window.location.href = "templateDetail.html?id="+msg.templateShowDtoList[i].id;
                });
            }
        }
    });
});