/*
* @author Xiaotong
* @Date 12/09/18
* */

$('input:radio').click(function(){
    //alert(this.checked);
    //

    var domName = $(this).attr('name');

    var $radio = $(this);
    // if this was previously checked
    if ($radio.data('waschecked') == true){
        $radio.prop('checked', false);
        $("input:radio[name='" + domName + "']").data('waschecked',false);
        //$radio.data('waschecked', false);
    } else {
        $radio.prop('checked', true);
        $("input:radio[name='" + domName + "']").data('waschecked',false);
        $radio.data('waschecked', true);
    }
});