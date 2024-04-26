import React, { useState, useEffect } from "react";
import axios from "axios";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

const EditTransaction = ({ transaction, closeModal, onSubmit }) => {
  const [selectedDate, setSelectedDate] = useState(null);
  const [amount, setAmount] = useState("");
  const [note, setNote] = useState("");
  const [selectedCategory, setSelectedCategory] = useState("");
  const [categories, setCategories] = useState([]);
  const [showAlert, setShowAlert] = useState(false);
  const userId = localStorage.getItem("user_id");
  const token = localStorage.getItem("auth_token");
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  useEffect(() => {
    fetchData();
    if (transaction) {
      setSelectedDate(new Date(transaction.tran_Date));
      setAmount(transaction.amount);
      setNote(transaction.notes);
      setSelectedCategory(transaction.categoryName);
    }
  }, [transaction]);

  const fetchData = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8777/category/getAll/${userId}`,
        {
          headers: headers,
        }
      );
      setCategories(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const handleSaveTransaction = async () => {
    try {
      const formattedDate = selectedDate.toISOString();
      const data = {
        transaction_Id: transaction.transaction_id,
        tran_Date: formattedDate,
        amount: parseFloat(amount),
        notes: note,
        userId: localStorage.getItem("user_id"), // Get userId from localStorage
        categoryName: selectedCategory,
      };
      await onSubmit(data);
      setShowAlert(true);

      // Don't close the modal immediately, wait for 2 seconds before closing it
      setTimeout(() => {
        setShowAlert(false); // Hide the alert after 2 seconds
        closeModal(); // Close modal after successful update
        clearForm(); // Clear form fields
      }, 5000);
    } catch (error) {
      console.log("Error saving transaction:", error);
    }
  };

  const handleCategoryChange = (event) => {
    setSelectedCategory(event.target.value);
  };

  const clearForm = () => {
    setSelectedDate(null);
    setAmount("");
    setNote("");
    setSelectedCategory("");
  };

  return (
    <div className="container-fluid">
      <div className="row">
        <div className="col-md-8 offset-md-2">
          <div
            className="modal fade"
            id="editModal"
            tabIndex="-1"
            aria-labelledby="editModalLabel"
            aria-hidden="true"
          >
            <div className="modal-dialog">
              <div className="modal-content">
                <div className="modal-header">
                  <h1 className="modal-title fs-5" id="editModalLabel">
                    Edit Transaction
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
                    data-bs-dismiss="modal"
                  >
                    Update
                  </button>
                </div>
              </div>
            </div>
          </div>

          {showAlert && (
            <div
              className="position-fixed top-0 end-0 p-3"
              style={{ zIndex: 999 }}
            >
              <div className="alert alert-warning" role="alert">
                Transaction updated successfully!
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default EditTransaction;
