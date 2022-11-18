import logo from './logo.svg';
import React, {useState, useEffect} from "react";
import './App.css';
import axios from "axios";

//const UserProfiles = () => {
//    const fetchUserProfiles = () => {
//
//        axios.get("http://localhost:25580/api/v1/users").then(res => {
//            console.log(res);
//        });
//    }
//
//    useEffect(() => {
//        fetchUserProfiles();
//    }, []);
//
//    return <h1>Hello</h1>;
//}

//
//const logIn = () => {
//
//    const dosmth = () => {
//        axios.post("http://localhost:25580/login", {
//            admin,
//            admin
//        })
//    }
//
//}

//function App() {
//  return (
//    <div className="App">
//        <UserProfiles/>
//    </div>
//  );
//}

function App() {
    // React States
    const [errorMessages, setErrorMessages] = useState({});
    const [isSubmitted, setIsSubmitted] = useState(false);

    // User Login info


    const errors = {
        uname: "invalid username",
        pass: "invalid password"
    };

    const handleSubmit = (event) => {
        //Prevent page reload
        event.preventDefault();

        let { uname, pass } = document.forms[0];

        let [username, setUsername] = useState("");
        let password = document.getElementById("password");

        const info = {username, password};

        // Find user login info
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ info })
        };
        fetch('http://localhost:25580/login', requestOptions)
        .then(() => {
            console.log("ya tut")
        })
//        .then(response => response.json())
//        .then(data => this.setState({ postId: data.id }));

        // Compare user info
//        if (userData) {
//            if (userData.password !== pass.value) {
//                // Invalid password
//                setErrorMessages({ name: "pass", message: errors.pass });
//            } else {
//                setIsSubmitted(true);
//            }
//        } else {
//            // Username not found
//            setErrorMessages({ name: "uname", message: errors.uname });
//        }
    };

    // Generate JSX code for error message
    const renderErrorMessage = (name) =>
    name === errorMessages.name && (
            <div className="error">{errorMessages.message}</div>
            );

    // JSX code for login form
    const renderForm = (
            <div className="form">
                <form onSubmit={handleSubmit}>
                    <div className="input-container">
                        <label>Username </label>
                        <input id = "username" type="text" name="uname" required />
                        {renderErrorMessage("uname")}
                    </div>
                    <div className="input-container">
                        <label>Password </label>
                        <input id = "password" type="password" name="pass" required />
                        {renderErrorMessage("pass")}
                    </div>
                    <div className="button-container">
                        <input type="submit" />
                    </div>
                </form>
            </div>
            );

    return (
            <div className="app">
                <div className="login-form">
                    <div className="title">Sign In</div>
                    {isSubmitted ? <div>User is successfully logged in</div> : renderForm}
                </div>
            </div>
            );
}

export default App;
