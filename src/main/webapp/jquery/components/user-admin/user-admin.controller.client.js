//IIFE
(function () {

    jQuery(main);

    var tbody;
    var template;
    var userService = new UserServiceClient()

    var $unameFld;
    var $pwFld;
    var $fnameFld;
    var $lnameFld;
    var $roleFld;

    var $searchUser;
    var $createUser;
    var $saveUser;

    function main() {
        tbody = $('tbody');
        template = $('.template');
        $('#createUser')

        $unameFld = $('#usernameFld');
        $pwFld = $('#passwordFld');
        $fnameFld = $('#firstNameFld');
        $lnameFld = $('#lastNameFld');
        $roleFld = $('#roleFld');

        $searchUser = $('#searchUser');
        $createUser = $('#createUser');
        $saveUser = $('#saveUser');

        $createUser.click(createUser);

        findAllUsers();
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function createUser() {
        var username = $unameFld.val();
        var password = $pwFld.val();
        var firstName = $fnameFld.val();
        var lastName = $lnameFld.val();
        var role = $roleFld.val();

        var user = new User(username, password, firstName, lastName, role);

        userService
            .createUser(user)
            .then(findAllUsers);
    }

    function renderUsers(users) {
        tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = template.clone();

            clone.attr('id', user.id);

            clone.find('.delete').click(deleteUser);
            clone.find('.edit').click(editUser);

            clone.find('.username')
                .html(user.username);
            clone.find('.password')
                .html(user.password);
            clone.find('.firstname')
                .html(user.firstName);
            clone.find('.lastname')
                .html(user.lastName);
            clone.find('.role')
                .html(user.role);

            tbody.append(clone);
        }
    }

    // Given the button Jquery obj of the row, what is the id of the User?
    function getUserId(button) {
        return button.parent().parent().attr('id');
    }

    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = getUserId(deleteBtn);

        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    function editUser(event) {
        var id = getUserId($(event.currentTarget));

        var user = userService.findUserById(id).then(populateForm);
    }

    function populateForm(user) {
        $unameFld.val(user.username);
        $pwFld.val(user.password);
        $fnameFld.val(user.firstName);
        $lnameFld.val(user.lastName);
        $roleFld.val(user.role);
    }
})();