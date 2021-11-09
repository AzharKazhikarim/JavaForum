import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Redirect, useHistory } from 'react-router'
import UserService from '../components/service/user.service';

export default function UserProfile() {
const [user,setUser] = useState({});
const history = useHistory();


const deleteAccount = ()=>{
        UserService.deleteUser();
        history.push('/register')
}
    useEffect(()=>{
        
        axios.get('http://localhost:8080/user/get-profile',
        
        {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        })
        .then(response => {
            setUser(response.data)
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
                axios.get('http://localhost:8080/user/get-profile',
        
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${localStorage.getItem('token')}`
                    }
                })
                
                .then(response => {
                console.log(response.data)
                setUser(response.data);
            })
            .catch(error=>{
               
                <Redirect to='/login'/>
            })
               
            })
        }
    })
   },[])



    return (
        <div>
            {user.username}
            <button onClick={deleteAccount}>Delete Account</button>
        </div>
    )
}
