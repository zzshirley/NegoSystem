$(function() {

})

function abt1(){
    $.ajax({
        url: '/abt1',
        data: {
            abt1:1
        },
        dataType: 'json',
        type: 'post',
        success: function(data) {
            console.log(data);
        }
    });
}