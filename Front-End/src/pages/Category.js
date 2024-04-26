import React from 'react';
import ProductList from '../components/ProductList';
import AddCategory from '../components/AddCategory';
import Navbar from '../components/Navbar';
const Category = () => {

  console.log("Categories called");
  return (
    <div className=''>
      <Navbar/>
      <ProductList/>
      <AddCategory/>
    </div>
  )
}

export default Category;
