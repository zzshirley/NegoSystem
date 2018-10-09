$(function(){
    $("#btn1").bind('click', function() {
        addStuMsg();
    });


});

function add(data) {
    tpl = '<div><div class="answer"><div class="profile-r"><img src="bootstrap/img/student.png"></div><div class="message-r"><span>{msg}</span></div></div>';
    result = parse(tpl, data);
    $("#chat").append(result);
}

function parse(tpl, data) {
    return tpl.replace('{msg}', data.msg);
}

function addStuMsg() {
    $.ajax({
        url: '/getScore',
        data: {
            btnNum: 1
        },
        dataType: 'json',
        type: 'post',
        success: function(data) {
            console.log(data);
            add(data);
        }
    });
}