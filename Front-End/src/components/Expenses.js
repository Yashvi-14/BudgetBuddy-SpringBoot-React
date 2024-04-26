import React, { useEffect, useState } from 'react'
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import CurrencyRupeeIcon from '@mui/icons-material/CurrencyRupee';
import "../components/css/expenses.css"
import {  ResponsiveContainer } from 'recharts';
import { BsFillArchiveFill, BsFillGrid3X3GapFill, BsPeopleFill, BsFillBellFill} from 'react-icons/bs';
import Chart from 'react-apexcharts';
import axios from 'axios';
import { BarChart } from '@mui/x-charts/BarChart';
import 'react-date-range/dist/styles.css';
import 'react-date-range/dist/theme/default.css';
import { DateRangePicker } from 'react-date-range';
import { FaFilter } from "react-icons/fa";
 
 
 
const Expenses = () => {
  //states
  const[transactions,setTransactions] = useState([]);
  const[selectedTransactions,setSelectedTransactions] = useState([]);
  const[category,setCategory] = useState([]);
  const[expense,setExpense] = useState([]);
  const[income,setIncome] = useState([]);
  const[budgetLimit , setBudgetLimit] = useState([]);
  const salary = expense+income;
  const categoryNames = category.map(category => category.category);
  const percentages = category.map(category => category.totalAmount);
  const balance = 0;
 
  //jwt
  const userId = localStorage.getItem("user_id");
  const token = localStorage.getItem("auth_token");
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };
 
  //date-filter on reports
  const[startDate , setStartDate] = useState(new Date());
  const[endDate , setEndDate] = useState(new Date());
  const [showDateRangePicker, setShowDateRangePicker] = useState(false);
 
 
  // percentage method
  const calculatePercentage = (amount) => {
    return ((amount / salary) * 100).toFixed(2);
  };
 
  // calculate transaction total
  const calculateTotals = (selectedTransactions) => {
    let expenseTotal = 0;
    let incomeTotal = 0;
 
    selectedTransactions.forEach(transaction => {
      if(transaction && transaction.type){
        const transactionType = transaction.type.toLowerCase();
        const comparisionString = "Expense".toLowerCase();
        const incomeString = "Income".toLowerCase();
        if (transactionType === comparisionString ) {
          expenseTotal += transaction.amount;
        }
        else if(transactionType === incomeString) {
          incomeTotal += transaction.amount;
        }
      }
    });
    setExpense(expenseTotal);
    setIncome(incomeTotal);
  };
 
  //calculate total by category
  const calculateTotalAmountByCategory = (categoryName) => {
    return selectedTransactions.reduce((total, transaction) => {
      if (transaction.categoryName === categoryName) {
        return total + transaction.amount;
      }
      return total;
    }, 0);
  };
 
  useEffect(() => {
 
    calculateTotals(selectedTransactions);
    const categories = [...new Set(selectedTransactions.map(transaction => transaction.categoryName))]; // Get unique categories
    const categoriesWithTotalAndPercentage = categories.map(category => {
      const totalAmount = calculateTotalAmountByCategory(category);
      const percentage = calculatePercentage(totalAmount);
      return {
        category: category,
        totalAmount: totalAmount,
        percentage: percentage
      };
    });
    setCategory(categoriesWithTotalAndPercentage);
  }, [selectedTransactions  ]);
 
  //api for transactions
  useEffect(() => {
    axios.get(`http://localhost:8777/expenses/view/${userId}`,{
      headers: headers,
    }).then((response) => {
      setTransactions(response.data);
      setSelectedTransactions(response.data);
     
    });
 
    fetchBudegetLimit();
  }, []);
 
 
  const getDistinctData = (data) => {
    const uniqueData = [];
    const uniqueNames = new Set();
    data.forEach((item) => {
      if (!uniqueNames.has(item.name)) {
        uniqueNames.add(item.name);
        uniqueData.push(item);
      }
    });
    return uniqueData;
  };
 
  const fetchBudegetLimit = async () => {
    try {
      const userId = localStorage.getItem("user_id");
      const response = await axios.get(`http://localhost:8777/category/getAll/${userId}`,{
        headers: headers,
      });
      const distinctData = getDistinctData(response.data);
      setBudgetLimit(distinctData);
     
     
    } catch (error) {
      console.error("Error fetching data:", error);
     
    }
  };
 
  //styling for list
  const itemClasses = [
    "list-group-item list-group-item-action list-group-item-primary",
    "list-group-item list-group-item-action list-group-item-secondary",
    "list-group-item list-group-item-action list-group-item-success",
    "list-group-item list-group-item-action list-group-item-danger",
    "list-group-item list-group-item-action list-group-item-warning",
    "list-group-item list-group-item-action list-group-item-info",
  ];
 
  //handle date range on transactions
  const handleSelect = (date) =>{
    setStartDate(date.selection.startDate)
    setEndDate(date.selection.endDate)
    let filtered = transactions.filter((transaction)=>{
      let transactionDate = new Date(transaction.tran_Date);
      return (
        transactionDate >= date.selection.startDate &&
        transactionDate <= date.selection.endDate
      )
    })
    console.log(startDate)
    console.log(endDate)
    setSelectedTransactions(filtered)
  }
 
 
  //filtering all budegetLimits per categoryName from category service
  const budgetLimitsByCategory = budgetLimit.reduce((acc, obj) => {
    if (acc[obj.categoryName] === undefined) {
      acc[obj.name] = [];
    }
    acc[obj.name].push(obj.budgetLimit);
    return acc;
  }, {});
 
  //filtering only on selected transaction
  const distinctCategoriesWithBudget = [];
  selectedTransactions.forEach(transaction => {
    const categoryName = getCategoryName(transaction.categoryName); // Assuming you have a function to extract category name from notes
    if (budgetLimitsByCategory.hasOwnProperty(categoryName) && !distinctCategoriesWithBudget.some(item => item.categoryName === categoryName)) {
      distinctCategoriesWithBudget.push({
        categoryName: categoryName,
        budgetLimit: budgetLimitsByCategory[categoryName][0]
      });
    }
  });
 
  //categoryName
  function getCategoryName(categoryName) {
    return categoryName;
  }
 
  //creating array of budgetLimit on selected transaction from object
  const budgetLimitsArray = distinctCategoriesWithBudget.map(obj => obj.budgetLimit);
 
  //setting date ranges
  const selectionRange = {
    startDate: startDate,
    endDate: endDate,
    key: 'selection',
  }
 
  //handling button on click
  const toggleDateRangePicker = () => {
    setShowDateRangePicker(!showDateRangePicker);
  };
 
 
 
return (  
  <>
    <div className='transaction-container'>
      <div className="filter-icon-container">
        <div className="filter-container">
          <button
          className="btn btn-dark"
          onClick={toggleDateRangePicker}
          style={{
            position: 'fixed',
            top: '100px',
            right: '20px',
            display: 'flex',
          alignItems: 'center',
          }}
          >
          <FaFilter style={{ marginLeft: '5px' }}/> Add Filter
          </button>
        </div>
      </div>
      {showDateRangePicker && (
          <div className="date-filter-container ">
        <DateRangePicker
          ranges={[selectionRange]}
          onChange={handleSelect}
          className="date-picker-sidebar bg-white bg-opacity-75"
          color='black'
         
        />
        </div>
      )}
    </div>
    <main className='main-container'>  
      <div className='main-title'>
        <h3>DASHBOARD</h3>
      </div>
      <div className='main-cards'>
        <div className='card'>
          <div className='card-inner'>
            <h3>EXPENSE</h3>
            <BsFillArchiveFill className='card_icon'/>
          </div>
          <h1><CurrencyRupeeIcon/>{expense}</h1>
        </div>
          <div className='card'>
              <div className='card-inner'>
                  <h3>INCOME</h3>
                  <BsFillGrid3X3GapFill className='card_icon'/>
              </div>
              <h1><CurrencyRupeeIcon/>{income}</h1>
          </div>
          <div className='card'>
              <div className='card-inner'>
                  <h3>CARRY</h3>
                  <BsPeopleFill className='card_icon'/>
              </div>
              <h1>{balance + income - expense }</h1>
          </div>
          <div className='card'>
              <div className='card-inner'>
                  <h3>BALANCE</h3>
                  <BsFillBellFill className='card_icon'/>
              </div>
              <h1><CurrencyRupeeIcon/>{income-expense}</h1>
          </div>
      </div>
 
      <div className='charts'>
        <BarChart
          width={500}
          height={300}
          series={[
          { data: percentages, label: 'Expense', id: 'pvId', stack: 'total',color:"#00bdae" ,barWidth:20},
          { data: budgetLimitsArray, label: 'Budget', id: 'uvId', stack: 'total'  ,color:"#404041",barWidth:20},
          ]}
          xAxis={[{ data: categoryNames, scaleType: 'band' }]}
          colorScheme={['#1976D2', '#F57C00']}
        />
        <ResponsiveContainer width="100%" height="100%">  
          <Chart
            type='donut'
            width={1349}
            height={550}
            series={percentages}
            options={{
              labels:categoryNames,
            }}
          >
          </Chart>
        </ResponsiveContainer>
      </div>
    </main>
    <div className="reports-container">
      <TableContainer
        component={Paper}
        style={{ boxShadow: "0px 13px 20px 0px #80808029" }}
      >
        <Table sx={{ minWidth: 450 }} aria-label="simple table">
          <TableHead className="tableHead">
            <TableRow>
              <TableCell className="tableHead-Cell">Category</TableCell>
              <TableCell align="left" className="tableHead-Cell">Total</TableCell>
            </TableRow>
          </TableHead>
          <TableBody style={{ color: "white" }}>
            {category.map((category , index) => (
              <TableRow
                key={category.category}
                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                className={`list-group-item ${
                  itemClasses[index % itemClasses.length]
                }`}
              >
                <TableCell component="th" scope="row">
                  <span className='percentage-btn'>
                  {((expense + income) !== 0 ? ((category.totalAmount / (expense + income)) * 100).toFixed(1) : 0)}%
                  </span>
                  <span></span>
                  {category.category}
                </TableCell>
                <TableCell align="" component="th" scope="row"><CurrencyRupeeIcon/>{category.totalAmount}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  </>
  );
};
 
export default Expenses;
