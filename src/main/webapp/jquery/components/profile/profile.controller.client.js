(function() {
    $(init);

    var urlVars;

    var $staticEmail;
    var $firstName;
    var $lastName;
    var $username;
    var $password;
    var $role;
    var $dob;
    var $phone;

    var $updateBtn;
    var userService = new UserServiceClient();

    function init() {
        $staticEmail = $("#staticEmail");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
        $username = $("#staticUsername");
        $password = $("#inputPassword");
        $role = $("#role");
        $dob = $("#datetimepicker");
        $phone = $("#phone");

        $updateBtn = $("#updateBtn");

        $updateBtn.click(updateUser);
        //$('#datetimepicker1').datepicker();

        urlVars = getUrlVars();
        userService.findUserById(urlVars["id"]).then(renderUser);
    }

    function updateUser() {
        var user = new User();
        user.setFirstName($firstName.val());
        user.setLastName($lastName.val());
        user.setPassword($password.val());
        user.setRole($role.val());
        user.setDob($dob.val());
        user.setPhone($phone.val());

        console.log("updating user " + user.dob);
        userService
            .updateUser(urlVars['id'], user)
            .then(success);
    }

    function success(response) {
        if(response === null) {
            alert('unable to update')
        } else {
            alert('success');
        }
    }

    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }
    
    function renderUser(user) {
        $staticEmail.val(user.email);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);

        $username.val(user.username);
        $password.val(user.password);
        $role.val(user.role);
        $dob.val(user.dob);
        $phone.val(user.phone);
    }

    // Read a page's GET URL variables and return them as an associative array.
    // FROM: https://stackoverflow.com/questions/4656843/
    // jquery-get-querystring-from-url?utm_medium=organic&utm_source
    // =google_rich_qa&utm_campaign=google_rich_qa
    function getUrlVars()
    {
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for(var i = 0; i < hashes.length; i++)
        {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    }
})();