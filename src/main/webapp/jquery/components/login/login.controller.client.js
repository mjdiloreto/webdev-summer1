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
                if (users.length === 0) {
                    alert("No user with that username.");
                    return;
                }
                var id = users[0].id;
                window.location.href = '../profile/profile.template.client.html'
                    + "?id=" + id;
            });
    }

})();