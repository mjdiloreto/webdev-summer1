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
        userService.login($usernameFld.val(), $passwordFld.val())
            .then(function(users) {
                var id = users[0].id;
                window.location.href = '../profile/profile.template.client.html'
                    + "?id=" + id;
            });
    }

})();