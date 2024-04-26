import React, { useState, useEffect } from "react";
import axios from "axios";
import { TiEdit } from "react-icons/ti";
import { FaFilter } from "react-icons/fa";
import "../components/css/Transaction.css";

import Transactions from "./Transactions";
import EditTransaction from "./EditTransaction";
import { DateRangePicker } from "react-date-range";
import "react-date-range/dist/styles.css";
import "react-date-range/dist/theme/default.css";

import {
  FaDumbbell,
  FaHome,
  FaDog,
  FaLaptop,
  FaPaintBrush,
  FaMoneyBillAlt,
} from "react-icons/fa";
import { BsPlug, BsBook } from "react-icons/bs";
import { GiPerfumeBottle } from "react-icons/gi";
import { PiBowlFoodFill } from "react-icons/pi";
import { GiClothes } from "react-icons/gi";
import { BsCarFrontFill } from "react-icons/bs";
import { BsHouseGear } from "react-icons/bs";
import { GiHeartEarrings } from "react-icons/gi";
import { BsFillGiftFill } from "react-icons/bs";
import { HiOutlineDocumentDuplicate } from "react-icons/hi";
import { BsGrid } from "react-icons/bs";
import { FaMountain } from "react-icons/fa";
import { GiPartyPopper } from "react-icons/gi";
import { MdDelete } from "react-icons/md";
import DownloadTransactions from "./DownloadTransactions";

const TransactionDashboard = () => {
  const [data, setData] = useState([]);
  const [startDate, setStartDate] = useState([]);
  const [endDate, setEndDate] = useState([]);
  const [filData, setFilData] = useState([]); //store filtered transactions
  const [showDateFilter, setShowDateFilter] = useState(false); // show date filter

  // const [filteredData, setFilteredData] = useState([]);
  const [selectedTransaction, setSelectedTransaction] = useState(null);
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [showDeleteAlert, setShowDeleteAlert] = useState(false);
  // const [transaction, setTransactions] = useState([]);
  const userId = localStorage.getItem("user_id");
  const token = localStorage.getItem("auth_token");
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  console.log(data);
  console.log(isEditModalOpen)

  const fetchData = async () => {
    try {
      const response = await axios.get(
        `http://www.localhost:8777/transactions/viewTransaction/${userId}`,
        {
          headers: headers,
        }
      );
      setData(response.data.reverse());
      setFilData(response.data.reverse());
    } catch (error) {
      console.log("Error fetching data", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, [selectedTransaction]);

  // const removeTransaction = async (transactionId) => {
  //   try {
  //     await axios.delete(
  //       `http://localhost:8777/transactions/deleteTransaction/${transactionId}`,{
  //         headers: headers,
  //       }
  //     );
  //     setData(data.filter((transaction) => transaction.transaction_id !== transactionId));
  //     console.log(`Transaction with ID ${transactionId} deleted successfully.`);
  //   } catch (error) {
  //     console.log("Error removing transaction", error);
  //   }
  // };
  const removeTransaction = async (transactionId) => {
    try {
      await axios.delete(
        `http://localhost:8777/transactions/deleteTransaction/${transactionId}`,
        {
          headers: headers,
        }
      );
      setData(
        data.filter(
          (transaction) => transaction.transaction_id !== transactionId
        )
      );
      console.log(`Transaction with ID ${transactionId} deleted successfully.`);
      setShowDeleteAlert(true); // Show the delete alert

      // Hide the alert after 2 seconds
      setTimeout(() => {
        setShowDeleteAlert(false);
      }, 2000);
    } catch (error) {
      console.log("Error removing transaction", error);
    }
  };

  const openEditModal = (transaction) => {
    console.log("Opening edit modal with transaction:", transaction);
    setSelectedTransaction(transaction);
    // console.log("dshuydn")
    console.log(selectedTransaction);
    setIsEditModalOpen(true);
  };

  const closeEditModal = () => {
    console.log("Closing edit modal");
    setSelectedTransaction(null);
    setIsEditModalOpen(false);
  };

  const selectionRange = {
    startDate: startDate,
    endDate: endDate,
    key: "selection",
  };

  //filter  data based on selected datee by user
  const handleSelect = (date) => {
    setStartDate(date.selection.startDate);
    setEndDate(date.selection.endDate);
    let filtered = filData.filter((transaction) => {
      let productDate = new Date(transaction.tran_Date);
      return (
        productDate >= date.selection.startDate &&
        productDate <= date.selection.endDate
      );
    });
    console.log(date);
    setData(filtered);
    setShowDateFilter(false);
  };

  const addTransaction = async (newTransaction) => {
    try {
      const response = await axios.post(
        "http://localhost:8777/transactions/addTransaction",
        newTransaction,
        {
          headers: headers,
        }
      );
      console.log(newTransaction);
      setData([response.data, ...data]);
      console.log(data);
      console.log("Transaction added successfully:", response.data);
    } catch (error) {
      console.log("Error adding transaction:", error);
      alert("Error adding transaction. Please try again.");
    }
  };

  const editTransaction = async (newTransaction) => {
    try {
      const response = await axios.put(
        `http://localhost:8777/transactions/updateTransaction/${newTransaction.transaction_Id}`,
        newTransaction,
        {
          headers: headers,
        }
      );
      // const updatedData = data.map((transaction) =>
      //   transaction.transaction_id === transactionId ? updatedTransaction : transaction
      // );
      // setData(updatedData);
      setData([response.data, ...data]);
      console.log(data, "data");
      console.log(
        `Transaction with ID ${newTransaction.transaction_Id} edited successfully.`
      );
      closeEditModal();
    } catch (error) {
      console.log("Error editing transaction", error);
    }
  };
  // Group transactions by date
  const groupedTransactions = data.reduce((grouped, transaction) => {
    const date = new Date(transaction.tran_Date).toLocaleDateString("en-US", {
      day: "2-digit",
      month: "long",
      year: "numeric",
    });
    if (!grouped[date]) {
      grouped[date] = [];
    }
    grouped[date].push(transaction);
    return grouped;
  }, {});

  // Sort the grouped transactions by date
  const sortedGroupedTransactions = Object.entries(groupedTransactions).sort(
    (a, b) => {
      return new Date(b[0]) - new Date(a[0]);
    }
  );

  const renderIcon = (categoryName) => {
    switch (categoryName.toUpperCase()) {
      case "FOOD":
        return <PiBowlFoodFill />;
      case "CLOTH":
        return <GiClothes />;
      case "TRAVEL":
        return <BsCarFrontFill />;
      case "ENTERTAINMENT":
        return <GiPartyPopper />;
      case "RENT":
        return <BsHouseGear />;
      case "ACCESSORIES":
        return <GiHeartEarrings />;
      case "GIFTS":
        return <BsFillGiftFill />;
      case "INSURANCE":
        return <HiOutlineDocumentDuplicate />;
      case "FITNESS":
        return <FaDumbbell />;
      case "HEALTH":
        return <FaDumbbell />;
      case "ELECTRONICS":
        return <BsPlug />;
      case "EDUCATION":
        return <BsBook />;
      case "BEAUTY":
        return <GiPerfumeBottle />;
      case "PERSONAL CARE":
        return <GiPerfumeBottle />;
      case "HOME":
        return <FaHome />;
      case "GARDEN":
        return <FaHome />;
      case "PETS":
        return <FaDog />;
      case "TECHNOLOGY":
        return <FaLaptop />;
      case "ADVENTURE":
        return <FaMountain />;
      case "HOBBIES":
        return <FaPaintBrush />;
      case "CRAFTS":
        return <FaPaintBrush />;
      case "FINANCE":
        return <FaMoneyBillAlt />;
      default:
        return <BsGrid />;
    }
  };

  const itemClasses = [
    "bg-blue-500 text-white hover:bg-blue-700",
    "bg-gray-500 text-white hover:bg-gray-700",
    "bg-green-500 text-white hover:bg-green-700",
    "bg-red-500 text-white hover:bg-red-700",
    "bg-yellow-500 text-white hover:bg-yellow-700",
    "bg-teal-500 text-white hover:bg-teal-700",
  ];

  return (
    <>
      <div className="transaction-container">
        <div className="filter-icon-container">
          <div className="filter-container">
            {/* <div className="filter-text">Filter Data</div> */}
            <button
              className="btn btn-dark"
              onClick={() => setShowDateFilter(!showDateFilter)}
              style={{
                position: "fixed",
                top: "100px",
                right: "20px",
                display: "flex", // Add flex display
                alignItems: "center",
              }}
            >
              <FaFilter style={{ marginLeft: "5px" }} /> Add Filter
              {/* className="filter-icon" */}
            </button>
          </div>
        </div>
        {showDateFilter && (
          <div className="date-filter-container">
            <DateRangePicker
              ranges={[selectionRange]}
              onChange={handleSelect}
              className="date-picker-sidebar"
            />
          </div>
        )}

<p class="text-xl font-bold ">Recent Transactions</p>

        {/* <DownloadTransactions transactions={data} /> */}
        {/* <button style={{ marginLeft: "700px" }}> */}
          <DownloadTransactions transactions={data} />
        {/* </button> */}
        {sortedGroupedTransactions.map(([date, transactions]) => (
          <div key={transactions.transaction_id}>
            <h2>{date}</h2>
            <ul className="transaction-list">
              {transactions.map((transaction, index) => (
                <div
                  key={index}
                  className={`flex justify-between mt-4 gap-4 border rounded-lg mb-10 p-4 shadow-md transition-transform transition-shadow duration-300 h-1/4 ease-in-out hover:-translate-y-1 hover:shadow-lg ${
                    index % 2 === 0 ? "bg-blue-300" : "bg-white"
                  }`}
                >
                  <div className="flex justify-between gap-4">
                    <button
                      type="button"
                      style={{
                        color: transaction.iconColor,
                        backgroundColor: transaction.iconBg,
                      }}
                      className={`text-2xl rounded-lg p-4 hover:drop-shadow-xl ${
                        itemClasses[index % itemClasses.length]
                      }`}
                      // className={`list-group-item `}
                    >
                      {renderIcon(transaction.categoryName)}
                    </button>
                    <div>
                      <p className="text-md font-semibold">
                        {transaction.categoryName}
                      </p>
                      <p
                        className={`text-lg ${
                          transaction.type === "Expense"
                            ? "text-red-500"
                            : "text-green-800"
                        }`}
                      >
                        {transaction.type === "Expense" ? "-" : "+"}
                        {transaction.amount}
                      </p>
                      {/* <p className="text-sm text-gray-400">{transaction.amount}</p>n */}
                    </div>
                  </div>
                  <div className="flex ">
                    {/* <p className={`text-${transaction.pcColor}`}>{transaction.type}</p> */}
                    <div className="icons">
                      <MdDelete
                        onClick={() =>
                          removeTransaction(transaction.transaction_id)
                        }
                        className="delete-icon"
                      />
                      <button
                        type="button"
                        className="btn btn-dark edit-icon"
                        data-bs-toggle="modal"
                        data-bs-target="#editModal"
                        data-bs-whatever="@mdo"
                        onClick={() => openEditModal(transaction)}
                        style={{
                          position: "fixed",
                          bottom: "20px",
                          right: "20px",
                        }}
                      >
                        <TiEdit />
                      </button>
                    </div>
                  </div>
                </div>
              ))}
            </ul>
          </div>
        ))}
        <EditTransaction
          transaction={selectedTransaction}
          closeModal={closeEditModal}
          onSubmit={editTransaction}
        />
        <Transactions onSubmit={addTransaction} />

        {showDeleteAlert && (
          <div
            className="alert alert-danger"
            role="alert"
            style={{
              position: "fixed",
              top: "20px",
              right: "20px",
              zIndex: 9999,
            }}
          >
            Transaction deleted successfully!
          </div>
        )}
      </div>
    </>
  );
};

export default TransactionDashboard;
