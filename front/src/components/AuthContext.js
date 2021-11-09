import { createContext, useEffect, useState} from "react";


export const AuthContext = createContext();

function AuthContextProvider(props) {

const[loggedIn, setLoggedIn] = useState(false);

useEffect(() => {
 
  const token = localStorage.getItem('token')
  if(token){
    setLoggedIn(true)
  }
}, [])
    

    return (
      <AuthContext.Provider value={{loggedIn}}>
        {props.children}
      </AuthContext.Provider>
    );
  }
  
  export default AuthContext;
  export { AuthContextProvider };