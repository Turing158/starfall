function display_me(){
    var a = document.getElementById("i_square");
    var b = document.getElementById("information");
    var c = document.getElementById("p_square");
    var d = document.getElementById("password");
    var e = document.getElementById("me_square");
    var f = document.getElementById("me");
    a.style.display = "none";
    b.style.display = "none";
    c.style.display = "none";
    d.style.display = "none";
    e.style.display = "block";
    f.style.display = "block";
}
function display_information(){
    var a = document.getElementById("i_square");
    var b = document.getElementById("information");
    var c = document.getElementById("p_square");
    var d = document.getElementById("password");
    var e = document.getElementById("me_square");
    var f = document.getElementById("me");
    a.style.display = "block";
    b.style.display = "block";
    c.style.display = "none";
    d.style.display = "none";
    e.style.display = "none";
    f.style.display = "none";
}
function display_password(){
    var a = document.getElementById("i_square");
    var b = document.getElementById("information");
    var c = document.getElementById("p_square");
    var d = document.getElementById("password");
    var e = document.getElementById("me_square");
    var f = document.getElementById("me");
    a.style.display = "none";
    b.style.display = "none";
    c.style.display = "block";
    d.style.display = "block";
    e.style.display = "none";
    f.style.display = "none";
}
function check_password_length(){
    var new_p = document.getElementById("new_password").value;
    var tips = document.getElementById("tips_password");
    if(new_p.length < 6){
        tips.innerHTML = new_p.length+" 密码不得小于6个字符";
        tips.style.color = "rgb(255, 125, 125)";
    }
    else{
        tips.innerHTML = new_p.length+" ✔";
        tips.style.color = "rgb(120, 255, 138)";
    }
}
function check_password_equal(){
    var new_p = document.getElementById("new_password").value;
    var again_p = document.getElementById("again_password").value;
    var tips = document.getElementById("tips_again_password");
    var submit = document.getElementById("confirm_password");
    if(again_p === new_p && again_p != ""){
        tips.innerHTML = "✔";
        tips.style.color = "rgb(120, 255, 138)";
        submit.type = "submit";
    }
    else{
        tips.innerHTML = "× 两次密码不一样";
        tips.style.color = "rgb(255, 125, 125)";
        submit.type = "button";
    }
}
function seti_Change(){
    var img = document.getElementById("seti_img_code")
    //设置时间戳
    var date = new Date().getTime();
    img.src="jpegCode?"+date;
}
function setp_Change(){
    var img = document.getElementById("setp_img_code")
    //设置时间戳
    var date = new Date().getTime();
    img.src="jpegCode?"+date;
}