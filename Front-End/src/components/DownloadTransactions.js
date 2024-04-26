import React from "react";
import { saveAs } from "file-saver";
import * as XLSX from "xlsx";
import { FaDownload } from "react-icons/fa";
import { LiaFileDownloadSolid } from "react-icons/lia";
import "../components/css/download.css";

const DownloadTransactions = ({ transactions }) => {
  const downloadTransactions = () => {
    const data = transactions.map((transaction) => ({
      Tran_Date: transaction.tran_Date,
      Category: transaction.categoryName,
      Amount: transaction.amount,
      // Type :transaction.type
      // notes: transaction.notes,
    }));

    //  data to Excel workbook
    const workbook = XLSX.utils.book_new();
    const worksheet = XLSX.utils.json_to_sheet(data); // TO JSON
    XLSX.utils.book_append_sheet(workbook, worksheet, "Transactions"); //APPEND WORKSHEET TO WORKBOOK

    // Generate Excel file
    const excelBuffer = XLSX.write(workbook, { bookType: "xlsx", type: "array" });

    // Save Excel file
    const blob = new Blob([excelBuffer], { type: "application/octet-stream" });
    saveAs(blob, "transactions.xlsx");
  };

  return (
    <div>
      
      <button  className="download-button" onClick={downloadTransactions}><LiaFileDownloadSolid className="LiaFileDownloadSolid" /></button>
      
    </div>
  );
};

export default DownloadTransactions;
