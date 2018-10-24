score0=null;
selfeva0=null;
var abt;
var ab= $("#ab").text();
var times=$("#times").text();
console.log(ab,times)
$(function(){
    $("#opt1").bind('click', function() {
        console.log(ab,times)
        addstu1();
        addStuMsg(ab,times);
    });
    $("#opt2").bind('click',function () {
        stuexplain();
        teacherask();
    });
    $("#opt3").bind('click',function () {
        sturetest();
        reTest(ab,times);
    });
    $("#opt4").bind('click',function(){
        stureeva();
        reselfeva(ab);
    })
    $("#opt5").bind('click',function(){
        answerstu();
        addStuAsk(ab,times)
    })
    $("#opt6").bind('click',function () {
        quesstu()
        stuQues()
    })
    $("#opt7").bind('click',function () {
        decision(score0,selfeva0);
    })

});
//1.学生请求查询成绩
function addstu1(){
    tpl='<div class ="row"><div class="answer"><div class="profile-r"><img src="bootstrap/img/student.JPG"></div><div class="message-r"><span>我想要查询成绩</span></div></div>';
    $("#chat").append(tpl);
}
//1.返回成绩列表
function add(data,ab) {
    console.log(data);
    tpl = '<div class ="row"><div class="question"><div class="profile-l"><img src="bootstrap/img/teacher.JPG"></div><div class="message-l"><table class="table table-hover" style="width: 300px "><thead><tr><th>文章/题目</th><th>成绩/选项</th></tr></thead><tbody>{msg}</tbody></table></div></div></div>';
    str="<tr>"+"<td>"+data.date +"</td>"+"<td>"+data.score+"</td>"+"</tr>";
    str2='';
    if(times=="2018-10-10 SRL"){
        str2="<tr>"+"<td>"+data.ques1 +"</td>"+"<tr>"+"<tr>"+"<td>"+data.ques2 +"</td>"+"<tr>"+"<tr>"+"<td>"+data.ques3 +"</td>"+"<tr>"+"<tr>"+"<td>"+data.ques4 +"</td>"+"<tr>"+"<tr>"+"<td>"+data.ques5 +"</td>"+"<tr>";
    }else{
        str2="<tr>"+"<td>"+data.ques1 +"</td>"+"<td>"+data.score1+"</td>"+"</tr>"+"<tr>"+"<td>"+data.ques2 +"</td>"+"<td>"+data.score2+"</td>"+"</tr>"+"<tr>"+"<td>"+data.ques3 +"</td>"+"<td>"+data.score3+"</td>"+"</tr>"+"<tr>"+"<td>"+data.ques4 +"</td>"+"<td>"+data.score4+"</td>"+"</tr>"+"<tr>"+"<td>"+data.ques5 +"</td>"+"<td>"+data.score5+"</td>"+"</tr>"
    }
    str=str+str2;
    result = parse(tpl,str);
    $("#chat").append(result);
    var div = document.getElementById("negotiate");
    div.scrollTop = div.scrollHeight;
}
//2学生选择解释测评结果
function stuexplain(){
    tpl='<div class ="row"><div class="answer"><div class="profile-r"><img src="bootstrap/img/student.JPG"></div><div class="message-r"><span>我想解释测评的结果</span></div></div>'
    $("#chat").append(tpl);
}
//2.系统生成表单，请学生填写原因
function teacherask() {
     tpl='<div class ="row"><div class="question"><div class="profile-l"><img src="bootstrap/img/teacher.JPG"></div>' +
         '<div class="message-l"><div class="form-group" ><div class="list-group-item"><span>你觉得问题出在哪个方面</span>' +
         '<select id="selfexplain" class="form-control" name="selfexplain">\n' +
         '<option>我的能力</option>\n' +
         '<option>我的努力程度</option>\n' +
         '<option>题目难度</option>\n' +
         '<option>运气</option>\n' +
         '<option>其他原因</option>\n' +
         '</select>  ' +
         '<input type="text" name="stuexplain" class="form-control"  placeholder="请说出你的理由" required data-bv-notempty-message="解释原因不为空">' +
         '<center><input type="submit" class="btn btn-default" id="expbtn" onclick="selfexp() "></center>'+
         '<div></div></div></div></div>'
     $("#chat").append(tpl);
    var div = document.getElementById("negotiate");
    div.scrollTop = div.scrollHeight;
 }
function selfexp() {
    var opt=$('#selfexplain option:selected').val();
    var stutext=$('input').val();
    postexplain(stutext,opt,ab,times);
    stureturn();
}
//3.学生请求重新测试
function sturetest(){
    tpl='<div class ="row"><div class="answer"><div class="profile-r"><img src="bootstrap/img/student.JPG"></div><div class="message-r"><span>我想要重新进行测试</span></div></div>';
    $("#chat").append(tpl);
}
//3.系统生成测试列表&&学生提交测试
function tearetest(data) {
    tpl='<div class ="row"><div class="question"><div class="profile-l"><img src="bootstrap/img/teacher.JPG"></div><div class="message-l"><table class="table table-hover" style="width: 300px"><div class="form-group"><a class="list-group-item" ><h5>{msg}</h5><span>{msg1}<center><input type="submit" class="btn btn-default" id="retestbtn" onclick="retest0()"></center></span></a></div></div></div>';
    str2="";
    for(i=0;i<5;i++){
        var name0=data[i].id
        str= "<p>"+(i+1)+"."+data[i].options+
            '<div class="radio-inline">' + "<input type=radio value=a name="+name0+">"+"正确"+"<br/>"+
            '\t\t\t\t\t\t\t\t\t\t\t</div>\n' +
            '<div class="radio-inline">\n' + "<input type=radio value=b name="+name0+">"+"错误"+"<br/>" +
            '\t\t\t\t\t\t\t\t\t\t\t</div>\n' +
            '<div class="radio-inline">\n' + "<input type=radio value=c name="+name0+">"+"不知道"+"<br/>" +
            '</div>\n' +
            '</p>'
        str2=str2+str;
    }
    result0=parse(tpl,data[5].options);
    result = parse0(result0,str2);
    $("#chat").append(result);
    var div = document.getElementById("negotiate");
    div.scrollTop = div.scrollHeight;

}
function  retest0() {
    var map=new Array();
    $(".form-group").find("input[type='radio']").each(function(){
        if($(this).is(":checked")){
            choicCheckOption=$(this).val();
            map.push(choicCheckOption);
        }
    });
    retest(map,ab,times);
}
//3.返回测试成绩
function teacherselect(data){
    tpl='<div class="question">\n' +
        '<div class="profile-l"><img src="bootstrap/img/teacher.JPG"></div>\n' +
        '<div class="message-l">\n' +
        '<div class="form-group" ><span>你的成绩是</span><span>{msg}</span></div></div></div>'

    result = parse(tpl,data);
    $("#chat").append(result);
    score0=data;
    console.log("分数是："+score0);
    var div = document.getElementById("negotiate");

    div.scrollTop = div.scrollHeight;
}

//4.重新自评
function stureeva() {
    tpl='<div class ="row"><div class="answer"><div class="profile-r"><img src="bootstrap/img/student.JPG"></div><div class="message-r"><span>我想要重新自我评价</span></div></div>';
    $("#chat").append(tpl);
}
//4.系统生成自我评价表格，学生提交自我评价
function reselfeva(ab) {
    tpl = '<div class ="row"><div class="question"><div class="profile-l"><img src="bootstrap/img/teacher.JPG"></div><div class="message-l"><div class="form-group" >\n' +
        '                                <a class="list-group-item" ><p>{msg}</p>\n' +
        '                                    <select id="selfeva1" class="form-control" name="abt1" >\n' +
        '                                        <option>1</option>\n' +
        '                                        <option>2</option>\n' +
        '                                        <option>3</option>\n' +
        '                                        <option>4</option>\n' +
        '                                        <option>5</option>\n' +
        '                                        <option>6</option>\n' +
        '                                        <option>7</option>\n' +
        '                                        <option>8</option>\n' +
        '                                        <option>9</option>\n' +
        '                                        <option>10</option>\n' +
        '                                    </select></a>\n' +
        '                        <center><input type="submit" class="btn btn-default" id="selfbtn" onclick="selfeva()"></center>\n' +
        '                    </div></div></div></div>';
    result2 =parse2(tpl,ab)
    $("#chat").append(result2);
    var ab= $("#ab").text();
    var times=$("#times").text();
    var div = document.getElementById("negotiate");
    div.scrollTop = div.scrollHeight;
}
function selfeva() {
    var selfoption=$('#selfeva1 option:selected').val();
    selfeva0=selfoption;
    console.log("重新自评结果是"+selfeva0);
    postselfeva(selfoption,ab,times);
    stureturn()
}
//5.回答学生的问题
function answerstu(){
    tpl='<div class ="row"><div class="answer"><div class="profile-r"><img src="bootstrap/img/student.JPG"></div><div class="message-r"><span>我想要回答同学的问题</span></div></div>';
    $("#chat").append(tpl);
}
//5.回答学生的问题
function readingsopt(data){
    tpl = '<div class ="row"><div class="question"><div class="profile-l"><img src="bootstrap/img/teacher.JPG"></div>' +
        '<div class="message-l"><div class="form-group" ><div class="list-group-item" ><p>{msg}</p>' +
        '<input type="text" name="stuask" id="stuask"class="form-control"  placeholder="你的回答" required data-bv-notempty-message="解释原因不为空"><center><input type="submit" class="btn btn-default" id="stuaskbtn" onclick="stuawr()"></center>' +
        '</div></div></div></div></div>';
    result = parse(tpl,data[0].stuques);
    $("#chat").append(result);
    var div = document.getElementById("negotiate");
    div.scrollTop = div.scrollHeight;
}
function stuawr() {
    var stuawr=$("input").val();
    poststuawr(stuawr,ab,times)
    stureturn()
}
//6.提问
function quesstu(){
    tpl='<div class ="row"><div class="answer"><div class="profile-r"><img src="bootstrap/img/student.JPG"></div><div class="message-r"><span>我还有问题想问</span></div></div>';
    $("#chat").append(tpl);
    var div = document.getElementById("negotiate");
    div.scrollTop = div.scrollHeight;
}
function stuQues(){
    tpl = '<div class ="row"><div class="question"><div class="profile-l"><img src="bootstrap/img/teacher.JPG"></div>' +
        '<div class="message-l"><div class="form-group" ><div class="list-group-item" ><p>请说</p>' +
        '<input type="text" name="stuques" id="stuques" class="form-control"  placeholder="你的问题" required data-bv-notempty-message="解释原因不为空">' +
        '<center><input type="submit" class="btn btn-default" id="stuquesbtn" onclick="stuques()"></center>' +
        '</div></div></div></div></div>';
    $("#chat").append(tpl);
    var div = document.getElementById("negotiate");
    div.scrollTop = div.scrollHeight;
}
//提问
function stuques() {
    var stuques=$("#stuques").val();
    poststuques(stuques,ab,times);
    stureturn();
}
//7.做决定
function decision(score0,selfeva0) {
    tpl = '<div class ="row"><div class="question"><div class="profile-l"><img src="bootstrap/img/teacher.JPG"></div><div class="message-l"><div class="form-group" ><div class="list-group-item" ><span>现在系统对你的评价是**{msg}**,</span><span>你的自我评价是**{msg1}**</span><span>你是否同意这个结果</span><p><li><a id="agree" onclick="agree0()">我同意测评结果是正确的。</a></li><li><a id="disagree" onclick="disagree0()">我不同意测评结果</a></li><li><a id="abc">我想要提议折衷。</a></li></p></div></div></div></div>';
    if(score0!=null&&selfeva0==null){
        selfeva0=$("#selfevaold").text();
        result = parse(tpl,score0);
        result0= parse0(result,selfeva0);
        $("#chat").append(result0);
    }else if(score0!=null&&selfeva0!=null){
        result = parse(tpl,score0);
        result0= parse0(result,selfeva0);
        $("#chat").append(result0);
    }else if(score0==null&&selfeva0!=null){
        score0=$("#sysold").text();
        result = parse(tpl,selfeva0);
        result0= parse0(result,selfeva0);
        $("#chat").append(result0);
    }else{
        score0=$("#sysold").text();
        selfeva0=$("#selfevaold").text();
        result = parse(tpl,abtold);
        result0= parse0(result,selfold);
        $("#chat").append(result0);
    }
    var div = document.getElementById("negotiate");
    div.scrollTop = div.scrollHeight;
}
function agree0() {
    agree(ab,times);
    endnego();
}
function disagree0() {
    disagree(ab,times);
    endnego();
}
//折衷
function com() {
    comp(ab,times,score0,selfeva0);
}
//1.查询成绩
function addStuMsg(ab,times) {
    $.ajax({
        url: '/getScore',
        data: {
            ability:ab,
            paperid:times
        },
        dataType: 'json',
        type: 'post',
        success: function(data) {
            console.log(data);
            add(data,ab);
        }
    });
}
//2.解释测评结果
function postexplain(text,option,ab,times) {
    $.ajax({
        url: '/postExp',
        data: {
            btntext: text,
            btnoption:option,
            paperid:times,
            ab:ab
        },
        dataType: 'json',
        type: 'post',
        success: function(data) {
            console.log(data);
            stureturn();
        }
    });
}
//3.重新测评
function reTest(ab,times){
    $.ajax({
        url: '/getAbPaper',
        data: {
            ability:ab,
            paperid:times
        },
        dataType: 'json',
        type: 'post',
        success: function(data) {
            console.log(data);
            tearetest(data);
        }
    });
}
//3.提交重新测评
function retest(map,ab,times){
    $.ajax({
        url: '/retest',
        data: {
            btntext: map.toString(),
            ability:ab,
            paperid:times
        },
        dataType: 'json',
        type: 'post',
        success: function(data) {
            console.log(data);
            teacherselect(data)
        }
    });
}

//4.提交重新自我测评
function postselfeva(option,ab,times) {
    $.ajax({
        url: '/postreself',
        data: {
            selfeva:option,
            paperid:times,
            ab:ab
        },
        dataType: 'json',
        type: 'post',
        success: function(data) {
            console.log(data);
        }
    });
}
//5.查询本周学生提问
function addStuAsk(ab,times) {
    $.ajax({
        url: '/stuAsk',
        data: {
            ability:ab,
            paperid:times
        },
        dataType: 'json',
        type: 'post',
        success: function(data) {
            console.log(data);
            readingsopt(data)
        }
    });
}
//5.提交回答
function poststuawr(stuawr,ab,times) {
    $.ajax({
        url: '/anwAsk',
        data: {
            stuawr:stuawr,
            paperid:times,
            ab:ab
        },
        dataType: 'json',
        type: 'post',
        success: function(data) {
            console.log(data);
        }
    });
}
//6.提交学生的问题
function poststuques(stuques,ab,times) {
    $.ajax({
        url: '/stuQues',
        data: {
            stuques:stuques,
            paperid:times,
            ab:ab
        },
        dataType: 'json',
        type: 'post',
        success: function(data) {
            console.log(data);
        }
    });
}
//7.做决定（同意）
function agree(ab,times) {
    $.ajax({
        url: '/negoagree',
        data: {
            ability: ab,
            paperid:times,
            isagree:1
        },
        dataType: 'json',
        type: 'post',
        success: function(data) {
            console.log(data);
        }
    });
}
//7.做决定（不同意）
function disagree(ab,times) {
    $.ajax({
        url: '/negodisagree',
        data: {
            ability: ab,
            paperid:times,
            isagree:0
        },
        dataType: 'json',
        type: 'post',
        success: function(data) {
            console.log(data);
        }
    });
}
//7.做决定（折衷）
function comp(ab,times,score0,selfeva0) {
    $.ajax({
        url: '/compromise',
        data: {
            ability: ab,
            paperid:times,
            isagree:-1,
            score:score0,
            self:selfeva0
        },
        dataType: 'json',
        type: 'post',
        success: function(data) {
            console.log(data);
        }
    });
}
//
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

function endnego(){
    tpl='<div class ="row"><div class="answer"><div class="profile-r"><img src="bootstrap/img/student.JPG"></div><div class="message-r"><span><a href="/index">退出</a></span></div></div>';
    $("#chat").append(tpl);
    var div = document.getElementById("negotiate");
    div.scrollTop = div.scrollHeight;
}
//提交成功
function stureturn() {
    tpl='<div class ="row"><div class="answer"><div class="profile-r"><img src="bootstrap/img/student.JPG"></div><div class="message-r"><span>提交成功</span></div></div>';
    $("#chat").append(tpl);
    var div = document.getElementById("negotiate");
    div.scrollTop = div.scrollHeight;
}
//辅助函数
function parse(tpl, data) {
    return tpl.replace('{msg}', data);
}
//辅助函数
function parse0(tpl, data) {
    return tpl.replace('{msg1}', data);
}
function parse2(tpl, ab) {
    if(ab=="理论能力"){
        return tpl.replace('{msg}',"【理论理解】");
    }else if(ab=="应用能力"){
        return tpl.replace('{msg}',"【研究方法】");
    }else {
        return tpl.replace('{msg}',"【结果分析】");
    }
    return tpl.replace('{msg}', ab);
}