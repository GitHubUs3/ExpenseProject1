import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../components/component.css'; 

export const NavBar = ({children}) => {
  return (
  <div>
      <nav className="navbar">
      {children}
      </nav>
  </div>
    
  )
} 
