import React from 'react';
import * as FaIcons from 'react-icons/fa';
import { Link } from 'react-router-dom';
import { SidebarData } from './SidebarData';
import '../components/css/Navbar.css';
import { IconContext } from 'react-icons';
import { FaRupeeSign } from "react-icons/fa";
import userImage from '../Images/dummy.png';
import logo from '../Images/logoBB.jpg';
import user from '../Images/logoanimated.gif';
// import animatedLogo from '../Images/logofinal.webp'
function Navbar() {
  const userName = localStorage.getItem('firstName');

  return (
    <>
      <IconContext.Provider value={{ color: '#fff' }}>
        <div className='navbar'>
          <Link to='#' className='menu-bars'>
            <FaIcons.FaBars />
          </Link>
        
          <div className='user-info'>
             {/* Dummy user image  */}
            <span className='user-name'>Hi {userName } ! </span>
            <img src={user} alt='User' className='user-image' />
            {/* Display user's name */}
            
          </div>
        </div>
        <nav className='nav-menu active '>
          <ul className='nav-menu-items'>
            <li className='navbar-toggle'>
              {/* <Link to='#' className='menu-bars'> */}
              <img src={logo} alt='User' className='logo-image' />
                
              <h5 className='logo-text'>Budget Buddy</h5>
            </li>
            {SidebarData.map((item, index) => {
              return (
                <li key={index} className={item.cName}>
                  <Link to={item.path}>
                    {item.icon}
                    <span>{item.title}</span>
                  </Link>
                </li>
              );
            })}
          </ul>
        </nav>
      </IconContext.Provider>
    </>
  );
}

export default Navbar;
