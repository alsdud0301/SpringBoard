
function goHome() {
    console.log('go home')
    location.href = "/";
}
function msgPrint(){
    if(m!==null){
        alert(m);
    }
}
function loginStatus(){
    if(id!=null){
        $('#m_name').html(id.m_name+"님");
        $('.suc').css('display','block'); //.show()
        $('.bef').css('display','none');

    }else{
        $('.suc').css('display','none'); //.show()
        $('.bef').css('display','block');
    }
}