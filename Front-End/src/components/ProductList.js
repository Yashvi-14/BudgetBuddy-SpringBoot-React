import { PiBowlFoodFill } from "react-icons/pi";
import { GiClothes } from "react-icons/gi";
import { BsCarFrontFill, BsChatRight } from "react-icons/bs";
import { BsHouseGear } from "react-icons/bs";
import { GiHeartEarrings } from "react-icons/gi";
import { BsFillGiftFill } from "react-icons/bs";
import { HiOutlineDocumentDuplicate } from "react-icons/hi";
import { BsGrid } from "react-icons/bs";
import { FaMountain } from "react-icons/fa";
import { GiPartyPopper } from "react-icons/gi";
import '../components/css/category.css';
 
 
import { FaDumbbell, FaHome, FaDog, FaLaptop, FaPaintBrush, FaMoneyBillAlt } from 'react-icons/fa';
import { BsPlug, BsBook } from 'react-icons/bs';
import { GiPerfumeBottle } from 'react-icons/gi';
import { IoIosRemoveCircle } from "react-icons/io";
import { TbEdit } from "react-icons/tb";
 
import React, { useState, useEffect } from "react";
import axios from "axios";
import Modal from 'react-bootstrap/Modal';
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS
import bootstrap from 'bootstrap'; // Import Bootstrap JavaScript
 
 
function ProductList() {
  const [data, setData] = useState([]);
  // const [loading, setLoading] = useState(true);
  // const [budgetLimit, setBudgetLimit] = useState("");
  const [budgetLimit, setBudgetLimit] = useState("");
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [showEditModal, setShowEditModal] = useState(false);
  // const [selectedCategory, setSelectedCategory] = useState(null);
  const token = localStorage.getItem("auth_token");
  const headers = {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json", 
  };
  const itemClasses = [
    "list-group-item list-group-item-action list-group-item-primary",
    "list-group-item list-group-item-action list-group-item-secondary",
    "list-group-item list-group-item-action list-group-item-success",
    "list-group-item list-group-item-action list-group-item-danger",
    "list-group-item list-group-item-action list-group-item-warning",
    "list-group-item list-group-item-action list-group-item-info",
  ];
 
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
 
  useEffect(() => {
    fetchData();
  }, [selectedCategory]);
 
  const handleEditClick = (categoryId) => {
    setSelectedCategory(categoryId);
    setShowEditModal(true);
  };
 
 
  const removeCategory = async (cat_Id) => {
    try {
      const userId = localStorage.getItem("user_id");
      await axios.delete(`http://localhost:8777/category/deleteCategory/${cat_Id}/${userId}`,{
        headers: headers,
      });
      setData(data.filter((item) => item.cat_Id !== cat_Id));
      console.log(`Category with ID ${cat_Id} deleted successfully.`);
    } catch (error) {
      console.log("Error removing category", error);
    }
  };
 
  // const updateCategory = async (categoryId) => {
  //   try {
  //     const userId = localStorage.getItem("user_id");
  //     await axios.put(`http://localhost:8777/category/updateCategory/${categoryId}/${budgetLimit}/${userId}`, {
  //       budgetLimit: parseInt(budgetLimit),
  //       headers: headers,
  //     });
  //     fetchData();
  //     console.log(`Category with ID ${categoryId} updated successfully.`);
  //     setBudgetLimit("");
  //     setShowEditModal(false); // Close modal after updating
  //   } catch (error) {
  //     console.error("Error updating category", error);
  //   }
  // };
  const updateCategory = async (categoryId) => {
    try {
      const userId = localStorage.getItem("user_id");
      await axios.put(
        `http://localhost:8777/category/updateCategory/${categoryId}/${budgetLimit}/${userId}`,
        {
          budgetLimit: parseInt(budgetLimit),
        },
        {
          headers: headers, 
        }
      );
      fetchData();
      console.log(`Category with ID ${categoryId} updated successfully.`);
      setBudgetLimit("");
      setShowEditModal(false); // Close modal after updating
    } catch (error) {
      console.error("Error updating category", error);
    }
  };
  
  const fetchData = async () => {
    try {
      const userId = localStorage.getItem("user_id");
      const response = await axios.get(`http://localhost:8777/category/getAll/${userId}`,{
        headers: headers,
      });
      const distinctData = getDistinctData(response.data);
      setData(distinctData);
      // setLoading(false);
    } catch (error) {
      console.error("Error fetching data:", error);
      // setLoading(false);
    }
  };
 
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
 
  return (
    <>
      <div className="container">
        <div className="list-group">
          <a href="#" style={{height:"70px"}} className="list-group-item list-group-item-action">
            <b>Category-List</b>
          </a>
 
          {data.map((item, index) => (
            <a
              style={{height:"70px"}}
              key={item.id}
              href="#"
              className={`list-group-item ${
                itemClasses[index % itemClasses.length]
              }`}
            >
              {renderIcon(item.name)}
              {item.name}
              <span className="budget-limit-circle">{item.budgetLimit}</span>
              <span
                 style={{ marginRight: '10px', float: 'right', padding:'2px'}}
                className="edit-icon"
                onClick={() => handleEditClick(item.cat_Id)}
              ><TbEdit />
               
              </span>
              <span
                 style={{ float: 'right' ,padding:'2px'}}
                className="delete-icon"
                onClick={() => removeCategory(item.cat_Id)}
              ><IoIosRemoveCircle />
               
              </span>
            </a>
          ))}
        </div>
      </div>
 
      <Modal
        show={showEditModal}
        onHide={() => setShowEditModal(false)}
        aria-labelledby="editModalLabel"
        centered
      >
        <Modal.Header closeButton>
          <Modal.Title id="editModalLabel">
            <b>Edit Category</b>
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <form
            onSubmit={(e) => {
              e.preventDefault();
              updateCategory(selectedCategory);
            }}
          >
            <div className="mb-3">
              <label htmlFor="budgetLimit" className="col-form-label">
                Budget Limit
              </label>
              <input
                type="number"
                className="form-control"
                id="budgetLimit"
                value={budgetLimit}
                onChange={(e) => setBudgetLimit(e.target.value)}
              />
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                onClick={() => setShowEditModal(false)}
              >
                Close
              </button>
              <button type="submit" className="btn btn-primary">
                Save changes
              </button>
            </div>
          </form>
        </Modal.Body>
      </Modal>
    </>
  );
}
 
export default ProductList;