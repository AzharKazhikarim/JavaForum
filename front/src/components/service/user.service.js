import axios from "axios";
import { Redirect } from "react-router";


class UserService{




    getUser(username){
            axios.get(`http://localhost:8080/user/get-user/${username}`)
            .then(response=>{
                console.log(response.data)
                return response.data;
            })
    }
    deleteUser(){
        axios.delete('http://localhost:8080/user/delete-user',
        
        {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        })
        .then(response => {
        console.log(response.data)

    })
    .catch(error=>{
        console.log(error.response)
        console.log(localStorage.getItem('refresh_token'))
        console.log(error.response.data.error_message)
        if(error.response.data.error_message.includes('The Token has expired on')){
            axios.get('http://localhost:8080/user/token/refresh',{
                headers:{
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('refresh_token')}`
                }
            }).then(response=>{
                console.log(response.data)
                localStorage.setItem('token', response.data.access_token);
                localStorage.setItem('refresh_token', response.data.refresh_token);
                   axios.delete('http://localhost:8080/user/delete-user',
        
        {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        })
        .then(response => {
        console.log(response.data)
        
    })
            .catch(error=>{
                <Redirect to='/login'/>
            })
               
            })
        }
    })    
    }

    updateUser(){

    }



   postQuestion(question){
       
        axios.post('http://localhost:8080/post/save-que',
        { title:question.title, par:question.par }
        ,
        {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        })
        .then(response => {
        console.log(response.data)
    })
    .catch(error=>{
        console.log(error.response)
        console.log(localStorage.getItem('refresh_token'))
        console.log(error.response.data.error_message)
        if(error.response.data.error_message.includes('The Token has expired on')){
            axios.get('http://localhost:8080/user/token/refresh',{
                headers:{
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('refresh_token')}`
                }
            }).then(response=>{
                console.log(response.data)
                localStorage.setItem('token', response.data.access_token);
                localStorage.setItem('refresh_token', response.data.refresh_token);
                axios.post('http://localhost:8080/post/save-que',
                { title:question.title, par:question.par }
                ,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.getItem('token')}`
                    }
                })
                .then(response => {
                console.log(response.data)
            })
            .catch(error=>{
                <Redirect to='/login'/>
            })
               
            })
        }
    })
}
       


    postAd(ad){
        axios.post('http://localhost:8080/post/save-ad',
                { title:ad.title, par:ad.par }
                ,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.getItem('token')}`
                    }
                })
                .then(response => {
                console.log(response)
            })    
            .catch(error=>{
                console.log(error.response)
                console.log(localStorage.getItem('refresh_token'))
                console.log(error.response.data.error_message)
                if(error.response.data.error_message.includes('The Token has expired on')){
                    axios.get('http://localhost:8080/user/token/refresh',{
                        headers:{
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${localStorage.getItem('refresh_token')}`
                        }
                    }).then(response=>{
                        console.log(response.data)
                        localStorage.setItem('token', response.data.access_token);
                        localStorage.setItem('refresh_token', response.data.refresh_token);
                        axios.post('http://localhost:8080/post/save-ad',
                        { title:ad.title, par:ad.par }
                        ,
                        {
                            headers: {
                                'Content-Type': 'application/json',
                                'Authorization': `Bearer ${localStorage.getItem('token')}`
                            }
                        })
                        .then(response => {
                        console.log(response.data)
                    })
                    .catch(error=>{
                        <Redirect to='/login'/>
                    })
                       
                    })
                }
            })
    
        }

        postAnswer(answer,id){
            axios.post(`http://localhost:8080/post/answer?queId=${id}`,
            { answer:answer }
            ,
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                }
            })
            .then(response => {
            console.log(response.data)
        }) 
        .catch((error)=>{
            console.log(error.response)
            console.log(localStorage.getItem('refresh_token'))
            console.log(error.response.data.error_message)
            if(error.response.data.error_message.includes('The Token has expired on')){
                axios.get('http://localhost:8080/user/token/refresh',{
                    headers:{
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.getItem('refresh_token')}`
                    }
                }).then(response=>{
                    console.log(response.data)
                    localStorage.setItem('token', response.data.access_token);
                    localStorage.setItem('refresh_token', response.data.refresh_token);
                    axios.post(`http://localhost:8080/post/answer?queId=${id}`,
                    { answer:answer }
                    ,
                    {
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${localStorage.getItem('token')}`
                        }
                    })
                    .then(response => {
                    console.log(response.data)
                })
                .catch(error=>{
                    
                    <Redirect to='/login'/>
                })
                   
                })
            }
        })
        }



        deleteQuestion(id){
            axios.delete(`http://localhost:8080/post/delete-que?id=${id}`,
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                }
            }).then(response => {
                console.log(response.data)
            })
            .catch((error)=>{
                console.log(error.response)
                console.log(localStorage.getItem('refresh_token'))
                console.log(error.response.data.error_message)
                if(error.response.data.error_message.includes('The Token has expired on')){
                    axios.get('http://localhost:8080/user/token/refresh',{
                        headers:{
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${localStorage.getItem('refresh_token')}`
                        }
                    }).then(response=>{
                        console.log(response.data)
                        localStorage.setItem('token', response.data.access_token);
                        localStorage.setItem('refresh_token', response.data.refresh_token);
                        axios.delete(`http://localhost:8080/post/delete-que?id=${id}`,
                        {
                            headers: {
                                'Content-Type': 'application/json',
                                'Authorization': `Bearer ${localStorage.getItem('token')}`
                            }
                        }).then(response => {
                            console.log(response.data)
                        })
                    .catch(error=>{
                        
                        <Redirect to='/login'/>
                    })
                       
                    })
                }
            })
        }

        deleteAd(id){
            axios.delete(`http://localhost:8080/post/delete-ad?id=${id}`,
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                }
            }).then(response => {
                console.log(response.data)
            })
            .catch((error)=>{
                console.log(error.response)
                console.log(localStorage.getItem('refresh_token'))
                console.log(error.response.data.error_message)
                if(error.response.data.error_message.includes('The Token has expired on')){
                    axios.get('http://localhost:8080/user/token/refresh',{
                        headers:{
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${localStorage.getItem('refresh_token')}`
                        }
                    }).then(response=>{
                        console.log(response.data)
                        localStorage.setItem('token', response.data.access_token);
                        localStorage.setItem('refresh_token', response.data.refresh_token);
                        axios.delete(`http://localhost:8080/post/delete-ad?id=${id}`,
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                }
            }).then(response => {
                console.log(response.data)
            })
                    .catch(error=>{
                        
                        <Redirect to='/login'/>
                    })
                       
                    })
                }
            })
        }

      getLoggedIn(){
          const token = localStorage.getItem('token');
          if(token){
              return true;
          }
          return false;
      }


}
export default new UserService();