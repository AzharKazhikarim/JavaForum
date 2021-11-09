import React, { useState } from 'react'
import UserService from '../service/user.service';
import a from "./Ad.module.css"
export default function PostAd() {
  const[title,setTitle] = useState('');
  const[par,setPar] = useState('');

  const handleClick=(e)=>{
      e.preventDefault()
      const ad={title,par}
      UserService.postAd(ad);  
      setTitle('')
      setPar('')  
  }



    return (
      <>
      <div className="container">
    
    
    <h3 className={a.header}>Post An Adsertisement</h3>
  
     <form action=""  className={a.blogPost}>
       <div className={a.blogBox}>

       <textarea className={a.blogTitle} cols="30" rows="10" placeholder="Advertisement Title" value={title} onChange={(e) => setTitle(e.target.value)} />
            <textarea
              placeholder="Write your content..."
              className={a.blogText}
              cols="30" rows="10"
              value={par}
              onChange={(e) => setPar(e.target.value)}
            />
         <button type="submit" className="btn btn-success" onClick={handleClick}>Post</button>
       </div>
     </form>
</div>
      
      </>
    )
}
