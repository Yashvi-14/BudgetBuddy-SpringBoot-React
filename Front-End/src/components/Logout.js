import React from 'react'

const Logout = () => {
    const handleSubmit = (e) => {
        e.preventDefault();
    }
    localStorage.removeItem("auth_token")
    localStorage.removeItem("user_id")

    window.location.href = "/"; 
  return (
   <>
   <button  type="submit" onClick={handleSubmit}>Logging out...</button>
   </>
  )
}

export default Logout