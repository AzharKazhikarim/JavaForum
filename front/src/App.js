
import React from 'react';
import { AuthContextProvider } from './components/AuthContext';

import Router from "./Router"

function App() {




  return (
    
<AuthContextProvider>
 <Router/>
 </AuthContextProvider>
   


  );
}


export default App;
