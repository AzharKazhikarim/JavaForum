
import axios from "axios";
import { useEffect, useState } from "react"
import q from "./Question.module.css"
import a from '../ad/Ad.module.css'
import { NavLink } from "react-router-dom";

export default function QuestionList() {
    const[questionList,setQuestionList] = useState([]);
    const[search,setSearch] = useState('');

    useEffect(()=>{
     
         axios.get('http://localhost:8080/post/get-list-que')
            .then((response) => {
                console.log(response.data);
            setQuestionList(response.data);
          });
            

    },[])
    


    return (
        <>
        <div className="container">
            <div className={q.headWrapper}>
            <h1 className={a.header}>Questions</h1>
            <input placeholder="Search for..."  type="text" className={q.searchbar} onChange={(e)=>setSearch(e.target.value)} />
            </div>
           
        </div>


<div className="container">
  {questionList.filter((question)=>{
      if(question.title.toLowerCase().includes(search.toLocaleLowerCase())){return question;}
  }).map(question=>(   
    <NavLink to={`/specific-que/${question.id}`}>
    <div className={q.listWrapper}>         
    
            
           
                   <div  className={q.questionContainer} >
                      <h4  key={question.title}>{question.title}</h4>  
                        <p className = {q.par} key={question.par}>{question.par}</p>
                        <i className="fa-solid fa-user"></i>  <h6>{question.user.username}</h6>
                    </div>
                   
                    </div>
                    </NavLink>
            ))} 
</div>
   </>
       
    )
}
