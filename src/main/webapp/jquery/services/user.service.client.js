function UserServiceClient() {
    var self = this;

    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.findUserByUsername = findUserByUsername;
    this.login = login;

    this.url =
        '/api/user';
    this.loginUrl =
        '/api/login';

    function login(username, password) {
        return fetch(self.loginUrl, {
            method: 'post',
            body: JSON.stringify({username:username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        }).then(responseToJson);
    }

    function updateUser(userId, user) {
        return fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
        .then(responseToJson);
    }

    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(responseToJson);
    }

    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            method: 'delete'
        })
    }

    function findAllUsers() {
        return fetch(self.url)
            .then(responseToJson);
    }

    function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function findUserByUsername(username) {
        return fetch(self.url + "?username=" + username)
            .then(responseToJson);
    }

    function responseToJson(response) {
        return response.json();
    }
}
