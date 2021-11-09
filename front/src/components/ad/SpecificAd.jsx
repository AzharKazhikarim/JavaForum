import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router';
import q from "../question/Question.module.css"
export default function SpecificAd() {
    const[ad,setAd] = useState({});
    const {id} = useParams();
    const [user,setUser] = useState({});
    
    useEffect(()=>{
        axios.get(`http://localhost:8080/post/get-ad?id=${id}`)
        .then((response) => {
            console.log(response.data);
        setAd(response.data);
        setUser(response.data.user)

        });

    },[id])

    return (
        <div className="container">
             <div className={q.questionContainer}>
        <h2> {ad.title} </h2>
        <p className={q.par}>{ad.par}</p>
           <i className="fa-solid fa-user"></i>
     <h6>{user.username} </h6>
</div>
        </div>
    )
}
