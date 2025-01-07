function sessionuser(user){

    const login_li = document.getElementById('login');
    const join_li = document.getElementById('join');
    const logout_li = document.getElementById('logout');
    console.log(logout_li);
    if(user!=null){
        login_li.style.display="none";
        join_li.style.display="none";
        logout_li.style.display="block";

    }else{
        login_li.style.display="block";
        join_li.style.display="block";
        logout_li.style.display="none";
    }


}