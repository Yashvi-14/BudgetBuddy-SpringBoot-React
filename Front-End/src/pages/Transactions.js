import React from 'react'
import TransactionDashboard from '../components/TransactionDashboard'

const Transactions = () => {
  return (
    <div>
    <TransactionDashboard/>
      <Transactions/>
      
    </div>
  )
}

export default Transactions
