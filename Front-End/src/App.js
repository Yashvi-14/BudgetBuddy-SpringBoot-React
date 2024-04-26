import React, { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import LoginPage from './pages/LoginPage';
import Transactions from './pages/Transactions';
import Reports from './pages/Reports';
import Category from './pages/Category';
import Logout from './components/Logout';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const token = JSON.parse(localStorage.getItem("token"));
    if (token) {
      setIsLoggedIn(true);
    }
    setIsLoading(false); // Update loading state after checking authentication
  }, []);

  // Show loading indicator while checking authentication status
  if (isLoading) {
    return <div>Loading...</div>;
  }

  return (
    <BrowserRouter>
      {isLoggedIn && <Navbar />}
      <Routes>
        <Route path='/' element={<LoginPage />} />
        <Route path='/home' element={<Home />} />
        {/* <Route path='/transactions' element={<Transactions />} /> */}
        <Route path='/reports' element={<Reports />} /> 
        <Route path='/category' element={<Category />} />
        <Route path='/logout' element={<Logout />} />
        {!isLoggedIn && <Route element={<Navigate to="/" replace />} />}
      </Routes>
    </BrowserRouter>
  );
}

export default App;
