import React, { useState } from "react";
// import { SiAddthis } from "react-icons/si";
import axios from "axios";

export default function Footer(props) {
  const [categoryName, setCategoryName] = useState("");
  const [budgetLimit, setBudgetLimit] = useState("");
  const [type, setType] = useState("Expense");
  const token = localStorage.getItem("auth_token");
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const userId = localStorage.getItem("user_id");
      const response = await axios.post(
        `http://localhost:8777/category/addCategory/${userId}`,
        {
          name: categoryName,
          budgetLimit: parseInt(budgetLimit),
          type: type,
        },
        {
          headers: headers, // Pass headers as a separate object
        }
      );
      console.log(response.data);
      // Reset form fields after successful submission
      setCategoryName("");
      setBudgetLimit("");
      setType("Expense");
    } catch (error) {
      console.error("Error submitting form:", error);
    }
  };

  return (
    <div data-bs-backdrop="false">
      <div className="footer">
        <button
          type="button"
          className="footer fixed-bottom bg-dark text-white text-left"
          data-bs-toggle="modal"
          data-bs-target="#exampleModal"
          data-bs-whatever="@mdo"
        >
          {/* <SiAddthis />  */}
          Add Category
        </button>
      </div>
      <div className="modal" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-4" id="exampleModalLabel">
                <b>Add New Category</b>
              </h1>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label htmlFor="recipient-name" className="col-form-label">
                    Category-Name
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="recipient-name"
                    value={categoryName}
                    onChange={(e) => setCategoryName(e.target.value)}
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="message-text" className="col-form-label">
                    Limit
                  </label>
                  <input
                    type="number"
                    className="form-control"
                    id="message-text"
                    value={budgetLimit}
                    onChange={(e) => setBudgetLimit(e.target.value)}
                  />
                </div>
                <div className="btn-group" role="group" aria-label="Basic radio toggle button group">
                  <input
                    type="radio"
                    className="btn-check"
                    name="type"
                    id="expense"
                    value="Expense"
                    checked={type === "Expense"}
                    onChange={(e) => setType(e.target.value)}
                  />
                  <label className="btn btn-outline-primary" htmlFor="expense">
                    Expense
                  </label>
                  <input
                    type="radio"
                    className="btn-check"
                    name="type"
                    id="income"
                    value="Income"
                    checked={type === "Income"}
                    onChange={(e) => setType(e.target.value)}
                  />
                  <label className="btn btn-outline-primary" htmlFor="income">
                    Income
                  </label>
                </div>
                <div className="modal-footer">
                  <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">
                    Close
                  </button>
                  <button type="submit" className="btn btn-primary">
                    Submit
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
