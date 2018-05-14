(function() {
    $(init);

    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserServiceClient();

    function init() {
        $usernameFld = $("#username");
        $passwordFld = $("#password");
        $verifyPasswordFld = $("#verify-password");
        $registerBtn = $(".btn");

        $registerBtn.click(register);
    }

    function register() {

        // verify that the passwords match
        if(!verifyMatchingPasswords()) {
            alert("Passwords must match to register.");
            return;
        }

        // verify that the username isn't taken
        usernameExists().then(
            function(responseJson) {
                var maybeUser = responseJson.length;

                if(maybeUser) {
                    alert("Username is already taken.");
                    return;
                } else {
                    uname = $usernameFld.val();
                    pword = $passwordFld.val();
                    userService.createUser(new User(uname, pword));
                }
            }
        );
    }

    function verifyMatchingPasswords() {
        return $passwordFld.val() === $verifyPasswordFld.val();
    }

    // Returns the array of Users that match the given username
    function usernameExists() {
        var username = $usernameFld.val();
        return userService.findUserByUsername(username);
    }


})();