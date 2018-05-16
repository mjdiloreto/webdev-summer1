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
                    // create the user on the server and log them in and go to
                    // their profile page
                    uname = $usernameFld.val();
                    pword = $passwordFld.val();
                    userService.createUser(new User(uname, pword))
                        .then(function() {
                            userService.login(uname, pword)
                                .then(function (users) {
                                    console.log(users);
                                })

                        });
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