import React, { useContext } from 'react'
import { Link } from 'react-router-dom'
import AuthContext from '../AuthContext'
import NavbarTwo from '../navbar/NavbarTwo';
import Navbar from '../navbar/Navbar'
import m from './Main.module.css'
export default function main() {


    
    return (
        <>
        
        <NavbarTwo />
      <div className="container">
          <div className={m.mainWrapper}>
          <Link to="/questionList">
         <div className={m.questionItem}>
             <h1 className={m.hItem}>Question</h1>
         </div>
         </Link>

         <Link to="/adList">
         <div className={m.ioio}>
            <h1 className={m.hItem}>Advertisements</h1>
        </div>
        </Link>
        </div>
 
      </div>
      </>
    
    
    )
}
