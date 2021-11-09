import React, { useState } from 'react'
import UserService from '../service/user.service';
import q from "./Question.module.css"
export default function PostQuestion() {
    const[title,setTitle] = useState('');
    const[par,setPar] = useState('');

    const handleClick=(e)=>{
        e.preventDefault()
        const question={title,par}
        UserService.postQuestion(question);
        setTitle('')
        setPar('')
    }




    return (
    
     <div className="container">
         
    <h3 className={q.header}>Post An Question</h3>
     <form action="" className={q.blogPost}>
       <div className={q.blogBox}>

       <textarea className={q.blogTitle} cols="30" rows="10" placeholder="Question Title" value={title} onChange={(e) => setTitle(e.target.value)} />
            <textarea
              placeholder="Write your content..."
              className={q.blogText}
              cols="30" rows="10"
              value={par}
              onChange={(e) => setPar(e.target.value)}
      
            />
         <button type="submit" className="btn btn-success" onClick={handleClick}>Post</button>
       </div>
     </form>
</div>

)}

