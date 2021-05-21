function handleLogin() {
    if(document.loginForm.username.value != "" || document.loginForm.password.value != ""){
        return false;
    }
    else
        return true;
}