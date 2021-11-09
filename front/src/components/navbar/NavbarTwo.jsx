import React from 'react'
import { Link } from 'react-router-dom';
import n from './Navbar.module.css';
export default function NavbarTwo() {
    return (
                  <div className={n.navbar}>
      
      <div className={n.linkWrapper}>
      <Link to="/"> <h1 className={n.logo}>JavaForum</h1></Link>
        </div>
        </div>
    )
}
