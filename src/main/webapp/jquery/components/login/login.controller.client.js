(function() {
    $(init);

    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();

    function init() {
        $usernameFld = $("#username");
        $passwordFld = $("#password");
        $loginBtn = $(".btn");

        $loginBtn.click(login);
    }

    function login() {
        userService.login($usernameFld.val(), $passwordFld.val());
        console.log("Logged in.")
    }

})();