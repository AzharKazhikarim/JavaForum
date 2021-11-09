import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { NavLink } from 'react-router-dom';
import UserService from '../service/user.service';
import q from "./Question.module.css"
export default function UserQuestionList() {

    const [queUserList,setQueUserList] = useState([]);


 
    useEffect(()=>{
     
        axios.get('http://localhost:8080/user/get-list-que',
        {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        })
           .then((response) => {
               console.log(response.data);
           setQueUserList(response.data);
         });
           

   },[])


    return (
        <>
             <div className="container">
             <h1 className= {q.header}>My Questions</h1>    
            {queUserList.map(question=>(
                <>
                      <NavLink to={`/specific-que/${question.id}`}>

                    <div className={q.questionContainer}>
                      <h3 key={question.id}>{question.title}</h3>
                        <p className={q.par}>{question.par}</p>
                        
                    </div>
                    </NavLink>
                   
                    </>
            ))}
            </div>
             
        </>
    )
    
}
