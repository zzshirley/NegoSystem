var list=new Array();
list=["#list1","#list2","#list3","#list4","#list5","#list6","#list7","#list8","#list9","#list10","#list11","#list12","#list13","#list14","#list15"]

$(function() {
    $('.navbar-collapse a').click(function() {
        $('.navbar-collapse').collapse('hide');
    });
})
$(function() {
    for(var i=1;i<list.length;i++){
        $(list[i]).hide();
    }
    $("li").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
    })
    $(':radio').change(function () {
        var radiovalue=$(this).val();
        var radioid=$(this).attr('name');
        radiolog(radioid,radiovalue);

    })
})


$(function(){
    $("#btn1").click(function(){
        $("#list1").show();
        for(var i=1;i<list.length;i++){
            $(list[i]).hide();
        }
    })
})
$(function(){
    $("#btn2").click(function(){
        $("#list2").show();

        for(var i=0;i<list.length;i++){
            if(list[i]!="#list2"){
                $(list[i]).hide();
            }
        }
    })
})
$(function(){
    $("#btn3").click(function(){
        $("#list3").show();

        for(var i=0;i<list.length;i++){
            if(list[i]!="#list3"){
                $(list[i]).hide();
            }
        }
    })
})
$(function(){
    $("#btn4").click(function(){
        $("#list4").show();

        for(var i=0;i<list.length;i++){
            if(list[i]!="#list4"){
                $(list[i]).hide();
            }
        }
    })
    $("#btn5").click(function(){
        $("#list5").show();

        for(var i=0;i<list.length;i++){
            if(list[i]!="#list5"){
                $(list[i]).hide();
            }
        }
    })
    $("#btn6").click(function(){
        $("#list6").show();

        for(var i=0;i<list.length;i++){
            if(list[i]!="#list6"){
                $(list[i]).hide();
            }
        }
    })
    $("#btn7").click(function(){
        $("#list7").show();

        for(var i=0;i<list.length;i++){
            if(list[i]!="#list7"){
                $(list[i]).hide();
            }
        }
    })
    $("#btn8").click(function(){
        $("#list8").show();

        for(var i=0;i<list.length;i++){
            if(list[i]!="#list8"){
                $(list[i]).hide();
            }
        }
    })
    $("#btn9").click(function(){
        $("#list9").show();

        for(var i=0;i<list.length;i++){
            if(list[i]!="#list9"){
                $(list[i]).hide();
            }
        }
    })
    $("#btn10").click(function(){
        $("#list10").show();

        for(var i=0;i<list.length;i++){
            if(list[i]!="#list10"){
                $(list[i]).hide();
            }
        }
    })
    $("#btn11").click(function(){
        $("#list11").show();

        for(var i=0;i<list.length;i++){
            if(list[i]!="#list11"){
                $(list[i]).hide();
            }
        }
    })
    $("#btn12").click(function(){
        $("#list12").show();

        for(var i=0;i<list.length;i++){
            if(list[i]!="#list12"){
                $(list[i]).hide();
            }
        }
    })
    $("#btn13").click(function(){
        $("#list13").show();

        for(var i=0;i<list.length;i++){
            if(list[i]!="#list13"){
                $(list[i]).hide();
            }
        }
    })
    $("#btn14").click(function(){
        $("#list14").show();

        for(var i=0;i<list.length;i++){
            if(list[i]!="#list14"){
                $(list[i]).hide();
            }
        }
    })
    $("#btn15").click(function(){
        $("#list15").show();

        for(var i=0;i<list.length-1;i++){
            $(list[i]).hide();
        }
    })
})

function checkradio(){
    var item = $(":radio:checked");
    var len=item.length;
    if(len<15){
        alert("全部选择之后才可以提交哦～请重新检查");
        return false;
    }else {
        alert("提交成功！✌耶！️")
    }
    return true;
}

function radiolog(id,value) {
    $.ajax({
        url: '/postradio',
        data: {
            btnNum: id,
            btnvalue:value
        },
        dataType: 'json',
        type: 'post',
        success: function(data) {
            console.log(data);
        }
    });
}