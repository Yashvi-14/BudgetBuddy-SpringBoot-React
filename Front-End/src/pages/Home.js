import React from 'react'
import Transactions from '../components/Transactions'
import TransactionDashboard from '../components/TransactionDashboard'
import Navbar from '../components/Navbar'
import Notification from '../components/Notification'
// import DownloadTransactions from '../components/DownloadTransactions'



const Home = () => {
    
  const userId = localStorage.getItem("user_id");
  return (
    <div>
      {/* Home */}
      <Navbar/>
      <TransactionDashboard/>
      <Transactions/>
      
      <Notification isIntervalNeeded={true}  messageToVerify={"Expense submitted for today."} url={`http://localhost:8777/notification/checkDailyTransactions/${userId}`}/>
     
    </div>

  )
}

export default Home
