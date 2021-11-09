import axios from 'axios';
import React, { useEffect, useState } from 'react'

import a from "./Ad.module.css"
import q from '../question/Question.module.css'
import { NavLink } from 'react-router-dom';
export default function AdList() {
    const[adList,setAdList] = useState([]);
    const[search,setSearch] = useState('');
    
    useEffect(()=>{
        axios.get('http://localhost:8080/post/get-list-ad')
        .then((response) => {
            console.log(response.data);
        setAdList(response.data);
      });
        
    },[])


    return (
        <>
        
  
       <div className="container">
   
      
            <div className={q.headWrapper}>
            <h1 className={a.header}>Advertisements</h1>
            <input placeholder="Search for..." type="text" className={q.searchbar} onChange={(e)=>setSearch(e.target.value)} />
        
        
        </div>
     
      
       </div>

<div className="container">
{adList.filter((add)=>{
       if(add.title.toLowerCase().includes(search.toLocaleLowerCase())){
      return add;
      }
  }).map(ad=>(
      
    <NavLink to={`/specific-ad/${ad.id}`}>
                <div className={a.adContainer} key={ad.id}>
                  
                      <h3 className={a.title}>{ad.title}</h3>  
                     <div className={a.iconUser}>
                     <i className="fa-solid fa-user"></i>
                        <h5>{ad.user.username}</h5>
                     </div>
                    </div>
                    </NavLink>
                   
            ))}
</div>
           


        </>
       
    )
}
