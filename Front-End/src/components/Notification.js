import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../components/css/notification.css';
 
const Notification = ({isIntervalNeeded, url, messageToVerify}) => {
  const [showNotification, setShowNotification] = useState(false);
  const [message, setMessage] = useState("");
  const token = localStorage.getItem("auth_token");
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };
 
  useEffect(() => {
    async function fetchData() {
      try {
        const response = await axios.get(url, {headers: headers});
        const message = response.data;
       
        setMessage(message);
       
        if(message === messageToVerify){
          setShowNotification(false);
        } else {
          setShowNotification(true);
        }
       
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    }
    fetchData();
 
    if(isIntervalNeeded) {
      const intervalId = setInterval(fetchData, 1 * 60 * 1000);
      return () => clearInterval(intervalId);
    }
  }, []);
 
  const handleCloseNotification = () => {
    setShowNotification(false);
  };
 
  return (
    <div>
      {showNotification && (
        <div className="notification">
          {message}
          <button className="close-button" onClick={handleCloseNotification}>
            Close
          </button>
        </div>
      )}
    </div>
  );
};

export default Notification;