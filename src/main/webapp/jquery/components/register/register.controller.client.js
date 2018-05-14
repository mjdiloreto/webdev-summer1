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
        if(usernameExists()) {
            alert("Username is already taken.");
            return;
        }

        uname = $usernameFld.val();
        pword = $passwordFld.val();
        userService.createUser(new User(uname, pword));
    }

    function verifyMatchingPasswords() {
        console.log("verify passwords " + ($passwordFld.val() === $verifyPasswordFld.val()))
        return $passwordFld.val() === $verifyPasswordFld.val();
    }

    // Returns the length of the array of Users that match the given username
    // (0 === false)
    function usernameExists() {
        return userService.findUserByUsername($usernameFld.val()).length;
    }


})();