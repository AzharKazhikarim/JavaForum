import axios from 'axios'
import QueryString from 'qs';


class AuthService{
    login(username,password){
        return  axios.post('http://localhost:8080/login',
        QueryString.stringify({
        username: username,
        password: password
      }), {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      }
    }).then(function (response) {
     console.log(response.data)
            localStorage.setItem('token', response.data.access_token)
            localStorage.setItem('refresh_token', response.data.refresh_token)
      
    return response.data;
    });
    }


    logout() {
        localStorage.removeItem('token');
        localStorage.removeItem('refresh_token');
      }

    refresh_token(){
        axios.get('http://localhost:8080/user/token/refresh',
        {
          headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${localStorage.getItem('refresh_token')}`
          }
      }).then((response)=>{
        console.log(response.data)
               localStorage.setItem('token', response.data.access_token)
               localStorage.setItem('refresh_token', response.data.refresh_token)
               return true;
       });
       return false;
      }





      
      

      register(username, password) {
      axios.post('http://localhost:8080/user/register', {
      username: username,
      password: password
   })
    .then((response) => {
        const user = response.data;
      console.log(user);
     
    });
      }
    
      getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
      }
       
}

export default new AuthService();