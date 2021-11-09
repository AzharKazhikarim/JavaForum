import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router';
import q from "./Question.module.css"
import UserService from '../service/user.service';
export default function SpecificQuestion() {

     const [question,setQuestion] = useState({});
     const [user,setUser] = useState({});
     const [answers,setAnswers] = useState([])
     const[ans,setAns] = useState('');
     const {id} = useParams();

   
    useEffect(()=>{
      
        axios.get(`http://localhost:8080/post/get-que?id=${id}`)
        .then((response) => {
        console.log(response)
        setQuestion(response.data);
        setAnswers(response.data.answers);
        console.log(response.data.answers);
        setUser(response.data.user);
      });
    },[id])

    const postAnswer=()=>{

        UserService.postAnswer(ans,id);
        setAns('');
    }



    return (
<>
    

<div className="container">


     <div className={q.questionContainer}>
        <h2> {question.title} </h2>

           <p className={q.par}>{question.par}</p>
           <i className="fa-solid fa-user"></i>
     <h6>{user.username} </h6>
    </div>
</div>
 <div className="container">
     <div className={q.ioiop}>
     <h5 className={q.ans}>{answers.length} answers</h5>
     </div>
 {answers.map(ans=>

         <div key = {ans.id}className={q.answer}>
         <i className="fa-solid fa-user"></i> {ans.user.username}
             <p className="answerText">{ans.answer}</p>
         </div>
  )} 
</div> 

<div className="container">
<div className={q.postAnswerContainer}>
   <textarea placeholder="Left an Answer" value = {ans} onChange={(e)=>{setAns(e.target.value)}} className={q.answerText}></textarea>
   <button onClick={postAnswer}>Post</button>
</div>
</div>

</>

    //     <>

    //    <div className="container">
    //     <div className={q.questionContainer}>
    //         <h2>{question.title}</h2>
        
    //         <p className={q.par}>{question.par}</p>
    //         <h5>{question.user.username}</h5>
    //     </div>
    //    </div>
    //    <div className="container">
        
    //    <h3> answers</h3>
    //    <div className={q.answerWrapper}>
    //  {question.answers.map(ans=>

    //         <div key={ans.id} className={q.answer}>
    //         <i className="fa-solid fa-user">{ans.user.username}</i>
    //             <p className="answerText">{ans.answer}</p>
    //         </div>
    //  )}
    //     </div>
    //    </div>
    //    </>
    )
}
