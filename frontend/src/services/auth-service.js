import axios from "axios";


class AuthService {
    login(username, password) {
        return axios.post("http://localhost:25580/login", {
            username,
            password
        }).then(res => {
            console.log(res);
        });
    }
}