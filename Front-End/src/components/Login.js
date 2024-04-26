import React, { useState } from 'react';
import '../components/css/Login.css';
import axios from 'axios';
import { jwtDecode } from "jwt-decode"
 
const Login = () => {
    const [activeForm, setActiveForm] = useState('login');
    const [loginData, setLoginData] = useState({ email: '', password: '' });
    const [signupData, setSignupData] = useState({ firstName : '', lastName : '', email: '', password: '', confirmPassword: '' });
    const [loading, setLoading] = useState(false);
    const [errorForLogin, setErrorForLogin] = useState('');
    const [errorForSignUp, setErrorForSignUp] = useState('');
 
    const switchForm = (formType) => {
        setActiveForm(formType);
    };
 
    const handleLoginChange = (e) => {
        setLoginData({ ...loginData, [e.target.name]: e.target.value });
    };
 
    const handleSignupChange = (e) => {
        setSignupData({ ...signupData, [e.target.name]: e.target.value });
    };
 
    const handleLoginSubmit = async (e) => {
        e.preventDefault();
        setErrorForLogin('');
        setLoading(true);
 
        try {
            // Make API call for login using Axios
            const response = await axios.post('http://localhost:8777/user-service/auth/login', loginData);
            // console.log(response.data); // Log the response
            const token = response.data;
            window.localStorage.setItem("auth_token", token);
            const decodedToken = jwtDecode(token);
            console.log(decodedToken);
            const user_id = decodedToken.user.id;
            const firstName = decodedToken.user.firstName;
            // console.log(firstName,"firstName");
            console.log(user_id);
            window.localStorage.setItem("user_id", user_id);
            window.localStorage.setItem("firstName", firstName);
            window.location.href = '/home';
        } catch (error) {
            // Handle login error
            console.log("heloooo")
            setErrorForLogin('Invalid email or password.');
        }
        finally {
            setLoading(false); // Set loading to false regardless of success or failure
        }
    };
 
    const handleSignupSubmit = async (e) => {
        e.preventDefault();
        setErrorForSignUp('');
        setLoading(true);
 
        try {
            // Make API call for signup using Axios
            const signUpDetails = {
                "firstName" : signupData.firstName,
                "lastName" : signupData.lastName,
                "email" : signupData.email,
                "password" : signupData.password
            }
 
            const response = await axios.post('http://localhost:8777/user-service/auth/signUp', signUpDetails);
            if(response.data === "User Already Present"){
                setErrorForSignUp('User Already Present');
            } else {
                alert('SignUp Successfull.. Please Login')
                setActiveForm('login')
                setSignupData(
                    { firstName : '', lastName : '', email: '', password: '', confirmPassword: '' }
                )
            }
            console.log(response.data); // Log the response
        } catch (error) {
            // Handle signup error
            setErrorForSignUp('Error occurred during registration.');
        }
 
        setLoading(false);
    };
 
    return (
        <section className="forms-section">
            <h1 className="section-title">Expense Tracker</h1>
            <div className="forms">
                <div className={`form-wrapper ${activeForm === 'login' ? 'is-active' : ''}`}>
                <button type="button" className="switcher switcher-login" onClick={() => switchForm('login')}>
                        Login
                        <span className="underline"></span>
                    </button>
                    <form className="form form-login" onSubmit={handleLoginSubmit}>
                        <fieldset>
                            <legend>Please, enter your email and password for login.</legend>
                            <div className="input-block">
                                <label htmlFor="login-email">E-mail</label>
                                <input id="login-email" type="email" name="email" value={loginData.email} onChange={handleLoginChange} required />
                            </div>
                            <div className="input-block">
                                <label htmlFor="login-password">Password</label>
                                <input id="login-password" type="password" name="password" value={loginData.password} onChange={handleLoginChange} required />
                            </div>
                        </fieldset>
                        {errorForLogin && <p className="error-message">{errorForLogin}</p>}
                        <button type="submit" className="btn-login" disabled={loading}>
                            {loading ? 'Logging in...' : 'Login'}
                        </button>
                    </form>
                </div>
                <div className={`form-wrapper ${activeForm === 'signup' ? 'is-active' : ''}`}>
                <button type="button" className="switcher switcher-signup" onClick={() => switchForm('signup')}>
                        Sign Up
                        <span className="underline"></span>
                    </button>
                    <form className="form form-signup" onSubmit={handleSignupSubmit}>
                        <fieldset>
                            <legend>Please, enter your email, password and password confirmation for sign up.</legend>
                            <div className="input-block">
                                <label htmlFor="signup-firstName">First Name</label>
                                <input id="signup-firstName" type="firstName" name="firstName" value={signupData.firstName} onChange={handleSignupChange} required />
                            </div>
                            <div className="input-block">
                                <label htmlFor="signup-lastName">Last Name</label>
                                <input id="signup-lastName" type="lastName" name="lastName" value={signupData.lastName} onChange={handleSignupChange} required />
                            </div>
                            <div className="input-block">
                                <label htmlFor="signup-email">E-mail</label>
                                <input id="signup-email" type="email" name="email" value={signupData.email} onChange={handleSignupChange} required />
                            </div>
                            <div className="input-block">
                                <label htmlFor="signup-password">Password</label>
                                <input id="signup-password" type="password" name="password" value={signupData.password} onChange={handleSignupChange} required />
                            </div>
                            <div className="input-block">
                                <label htmlFor="signup-password-confirm">Confirm password</label>
                                <input id="signup-password-confirm" type="password" name="confirmPassword" value={signupData.confirmPassword} onChange={handleSignupChange} required />
                            </div>
                        </fieldset>
                        {errorForSignUp && <p className="error-message">{errorForSignUp}</p>}
                        <button type="submit" className="btn-signup" disabled={loading}>
                            {loading ? 'Signing up...' : 'Sign Up'}
                        </button>
                    </form>
                </div>
            </div>
        </section>
    );
};
 
export default Login;
 