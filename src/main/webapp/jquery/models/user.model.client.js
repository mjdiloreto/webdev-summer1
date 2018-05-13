function User(username, password, firstName, lastName, role, phone, email, dob) {

    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.phone = phone;
    this.email = email;
    this.dob = dob;

    this.setUsername = setUsername;
    this.getUsername = getUsername;


    function setUsername(username) {
        this.username = username;
    }

    function getUsername() {
        return this.username;
    }

    function setPassword(password) {
        this.password = password;
    }

    function getPassword() {
        return this.password;
    }

    function setFirstName(firstName) {
        this.firstName = firstName;
    }

    function getFirstName() {
        return this.firstName;
    }

    function setLastName(lastName) {
        this.lastName = lastName;
    }

    function getLastName() {
        return this.lastName;
    }

    function setRole(role) {
        this.role = role;
    }

    function getRole() {
        return this.role;
    }

    function setPhone(phone) {
        this.phone = phone;
    }

    function getPhone() {
        return this.phone;
    }

    function setEmail(email) {
        this.email = email;
    }

    function getEmail() {
        return this.email;
    }

    function setDob(dob) {
        this.dob = dob;
    }

    function getDob() {
        return this.dob;
    }

}
