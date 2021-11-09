import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { NavLink } from 'react-router-dom';
import a from "./Ad.module.css"
export default function UserAdList() {
    const [adUserList,setAdUserList] = useState([]);

  
    useEffect(()=>{
      
     
      axios.get('http://localhost:8080/user/get-list-ad',
      {
          headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${localStorage.getItem('token')}`
          }
      })
         .then((response) => {
             console.log(response.data);
         setAdUserList(response.data);
       });
         

 },[])


    return (
        <>
     


<div className="container">
<h1 className={a.header}>My Advertisements</h1>
{adUserList.map(ad=>(
       

       <NavLink to={`/specific-ad/${ad.id}`}>
              <div className={a.adContainer} >
                <h3 key={ad.id}>{ad.title}</h3>  
                  <p>{ad.par}</p>
              </div>
              </NavLink>
      ))}
</div>
  </>
    )
}
