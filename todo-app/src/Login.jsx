import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Input,InputLabel,Typography,Button,Paper } from '@mui/material'



const Login = () => {
    //TODO
    //Direct user to main page if the user is already authenticated
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loginMessage, setLoginMessage] = useState("");
  const nav = useNavigate()

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = (event) => {
     event.preventDefault();
    axios.post("http://localhost:8080/authenticate",{email,password})
    .then(res=> res.data)
    .then(data => {
      localStorage.setItem('current_jwt',data.token)
      localStorage.setItem("current_user",data.currentUser)
      nav("/main")
    }
    )
      .catch(err => alert(err.message))
  };

  const isValidLogin = (valid) =>
    setLoginMessage(
      valid ? (
        <div>Login Successful</div>
      ) : (
        <div className="alert-warning">Invalid Credentials</div>
      )
    );

  return (
  <>
    <Typography variant="h1">Task Manager App</Typography>
    <div style={{"display":"flex"}}>
      
      <form onSubmit={handleSubmit} style={{"margin":"auto","backgroundColor":"beige","padding": 10}}>
        
        <Paper sx={{p:2}} elevation={10}>
          <Typography variant="h3">Login Form</Typography>
          <InputLabel focused={true}>Username</InputLabel>
          <Input 
            variant="contained" 
            type="text" 
            name="email" 
            onChange={handleEmailChange} 
          />
          <InputLabel>Password</InputLabel>
          <Input
            variant="contained"
            type="password"
            name="password"
            onChange={handlePasswordChange}
          />
          <br/>
          <br/>
          <Button type="submit" variant="contained">Login</Button>
        </Paper>
      </form>
    </div>
  </>
  );
};

export default Login;