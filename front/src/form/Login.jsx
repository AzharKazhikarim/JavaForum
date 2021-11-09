
import {  useState } from "react";
import {Link,useHistory} from 'react-router-dom';
import AuthService from "../components/service/auth.service.js";
import './login.css'
export default function Login() {

    const[username,setUsername] = useState('');
    const[password,setPassword] = useState('');
    const history = useHistory();
 

    const handleLogin=(e)=>{
        e.preventDefault()
    
       AuthService.login(username,password);
      
       history.push("/");
    }

    return (
        <>
<div className="login">
           <div className="loginWrapper">
               <div className="loginleft">
                    <h2 className="loginheader">Welcome back!</h2>
            <p className="loginparagraph">Lorem ipsum tek avue ref tau baro. </p>
               </div>

               <div className="loginright">
                <form className="loginbox" >
              
                <input 
                type="username" 
                placeholder="Username"
                className="input" 
                value = {username}
                onChange={(e) => setUsername(e.target.value)}
                  />
                 <input type="password" 
                 placeholder="Password"
                 className="input" 
                 value={password} 
                 onChange={(e) => setPassword(e.target.value)}
                  />
                    <div className="link">
                        
                    <Link to="/register">
                    <span className="hasAcc"><p className="textLink">Doesn't not have account?</p></span>
                    </Link>
                    </div>
                    <button type ="submit" className="loginbutton" onClick={handleLogin}>Sign In</button>
                </form>

               </div>
           </div>
       </div>
       </>
    )
}
