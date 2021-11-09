import React from 'react'
import { Link,useHistory } from 'react-router-dom'
import n from './Navbar.module.css';
import AuthService from "../service/auth.service.js"

export default function Navbar() {

  const history = useHistory();
  



 
  const logout=(e)=>{
    e.preventDefault()
  AuthService.logout();
history.push('login')
  }
    return (
        <>
       

     
      
       <div className={n.navbar}>
      
          <div className={n.linkWrapper}>
          <Link to="/"> <h1 className={n.logo}>JavaForum</h1></Link>
       <Link to="/user-que-list">
      <span className={n.links}>My Questions</span>
      </Link>
     <Link to="/user-ad-list">
     <span className={n.links}>My Adverisements</span>
       </Link>
    <Link to='/post-que'>
    <span className={n.links}>Post Question</span>
      </Link>
      <Link to='/post-ad'>
    <span className={n.links}>Post Advertisements</span>
      </Link>
      <Link to='/post-ad'>
    <span className={n.links}>Post Advertisements</span>
      </Link>
      <Link to='/user'>
    <span className={n.links}>My Profile</span>
      </Link>
     <button className={n.logout} onClick={logout}>Log out</button>
       </div>
       


      
      
      
       </div>
       </>
    )
}
