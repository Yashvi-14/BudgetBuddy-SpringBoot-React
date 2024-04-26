import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { FaPlus } from "react-icons/fa6";
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import Notification from '../components/Notification'
 
const Transactions = ({ closeModal, onSubmit }) => {
  const [selectedDate, setSelectedDate] = useState(null);
  const [amount, setAmount] = useState('');
  const [note, setNote] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('');
  const [categories, setCategories] = useState([]);
  const [showAlert, setShowAlert] = useState(false);
  const token = localStorage.getItem("auth_token");
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };
  const userId = localStorage.getItem("user_id");
  useEffect(() => {
    fetchData();
  }, []);
 
  const fetchData = async () => {
    try {
      // const response = await axios.get('http://localhost:7001/getAll');
      
      const response = await axios.get(`http://localhost:8777/category/getAll/${userId}`,{
        headers: headers,
      });
      setCategories(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };
 
 
  const handleSaveTransaction = async () => {
    try {
      const formattedDate = selectedDate.toISOString();
      const data = {
        tran_Date: formattedDate,
        amount: parseFloat(amount),
        notes: note,
        userId: localStorage.getItem('user_id'), 
        categoryName: selectedCategory,
      };
      await onSubmit(data);
      setShowAlert(true); 
 
      setTimeout(() => {
        setShowAlert(false); 
        // closeModal();
      }, 5000);
 
      // Clear fields
      setSelectedDate(null);
      setAmount('');
      setNote('');
      setSelectedCategory('');
 
    } catch (error) {
      console.log('Error saving transaction:', error);
      setShowAlert(true);
    }
  };
 
 
  const handleCategoryChange = (event) => {
    setSelectedCategory(event.target.value);
  };
 
  return (
    <div>
      <button
        type="button"
        className="btn btn-dark"
        data-bs-toggle="modal"
        data-bs-target="#exampleModal"
        data-bs-whatever="@mdo"
        style={{
          position: 'fixed',
          bottom: '20px',
          right: '20px',
          // zIndex: '1000',
          display: 'flex', // Add flex display
          alignItems: 'center', // Align items vertically
          // width: '200px' // Set width as needed
        }}
      >
        <FaPlus style={{ marginLeft: '5px' }} />
        {/* {"   "}  */}
        Add Transaction 
  
        
      </button>
      <div
        className="modal fade"
        id="exampleModal"
        tabIndex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="exampleModalLabel">
                New Transaction
              </h1>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              <form>
                <div className="mb-3">
                  <label htmlFor="date" className="form-label">
                    Date:
                  </label>
                  <br />
                  <DatePicker
                    selected={selectedDate}
                    onChange={(date) => setSelectedDate(date)}
                    className="form-control"
                    dateFormat="MM/dd/yyyy"
                    placeholderText="Select date"
                    showMonthDropdown
                    showYearDropdown
                    dropdownMode="select"
                    isClearable
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="category" className="form-label">
                    Category:
                  </label>
                  <br />
                  <select
                    className="form-select"
                    value={selectedCategory}
                    onChange={handleCategoryChange}
                  >
                    <option value="">Select category</option>
                    {categories.map((category) => (
                      <option key={category.cat_Id} value={category.name}>
                        {category.name}
                      </option>
                    ))}
                  </select>
                </div>
                <div className="mb-3">
                  <label htmlFor="amount" className="form-label">
                    Amount:
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="amount"
                    value={amount}
                    onChange={(e) => setAmount(e.target.value)}
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="note" className="form-label">
                    Note:
                  </label>
                  <textarea
                    className="form-control"
                    id="note"
                    value={note}
                    onChange={(e) => setNote(e.target.value)}
                  />
                </div>
              </form>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
              <button
                type="button"
                className="btn btn-primary"
                onClick={handleSaveTransaction}
              >
                Save
              </button>
            </div>
          </div>
        </div>
      </div>
      {showAlert && (
        <div
          className="alert alert-success alert-dismissible fade show"
          role="alert"
          style={{
            position: 'fixed',
            top: '20px',
            right: '20px',
            zIndex: '1000',
          }}
        >
          Transaction added successfully!
          <button
            type="button"
            className="btn-close"
            onClick={() => setShowAlert(false)}
          ></button>
        </div>
      )}
      {showAlert &&
      <Notification isIntervalNeeded={false}  messageToVerify={"No Limit exceeded"} url={`http://localhost:8777/notification/checkLimit/${userId}`}/>

      }
    </div>
  );
};
 
export default Transactions;