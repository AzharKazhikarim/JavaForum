import React, { useContext, useEffect } from 'react'
import { BrowserRouter,Switch,Route } from "react-router-dom";
import Main from "./components/main/Main";
import QuestionList from './components/question/QuestionList'
import AdList from '../src/components/ad/AdList'

import Navbar from "./components/navbar/Navbar"
import PostQuestion from "./components/question/PostQuestion";
import PostAd from "./components/ad/PostAd";
import UserQuestionList from "./components/question/UserQuestionList";
import UserAdList from "./components/ad/UserAdList";
import Login from './form/Login';
import Register from './form/Register';
import SpecificQuestion from './components/question/SpecificQuestion';
import SpecificAd from './components/ad/SpecificAd'
import UserProfile from './user/UserProfile';

export default function Router() {

    

    return (
           
        <BrowserRouter>
     
        <Switch>
            
         
        
        <Route path="/login" exact component={Login}/>
        <Route path="/register" exact component={Register}/>
         
        
               


        
               
            <Route path ='/' exact component={Main} />
            <Route path ='/user' exact component={UserProfile} />
            
           <Route path ='/questionList' exact component={QuestionList} />
           <Route path='/specific-que/:id' exact component={SpecificQuestion}/>
           <Route path='/specific-ad/:id' exact component={SpecificAd}/>
           <Route path='/adList' exact component={AdList}/>

          <Route path='/post-que' exact component={PostQuestion}/>
           <Route path='/post-ad' exact component={PostAd}/>
           <Route path='/user-que-list' exact component={UserQuestionList}/>
           <Route path='/user-ad-list' exact component={UserAdList}/>
      
         
                   
        </Switch>
        </BrowserRouter>
    )
}
