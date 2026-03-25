import React, { useState } from "react";
import axios from "axios";

const FileUpload = () => {
  const [file, setFile] = useState(null);
  const [message, setMessage] = useState("");
  const [isError, setIsError] = useState(false);
  const [uploading, setUploading] = useState(false);

  // Handle file selection
  const handleFileChange = (e) => {
    const selectedFile = e.target.files[0];

    if (!selectedFile) {
      setMessage("No file selected.");
      setIsError(true);
      return;
    }

    // Validation: limit file size to 5MB
    if (selectedFile.size > 5 * 1024 * 1024) {
      setMessage("File size exceeds 5MB limit.");
      setIsError(true);
      return;
    }

    setFile(selectedFile);
    setMessage("");
    setIsError(false);
  };

  // Handle file upload
  const handleUpload = async () => {
    if (!file) {
      setMessage("Please select a file first.");
      setIsError(true);
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      setUploading(true);
      setMessage("");
      setIsError(false);

      const response = await axios.post("http://localhost:5000/upload", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });

      setMessage(`Upload successful: ${response.data.fileName || file.name}`);
      setIsError(false);
    } catch (error) {
      console.error(error);
      setMessage("File upload failed. Please try again.");
      setIsError(true);
    } finally {
      setUploading(false);
    }
  };

  return (
    <div className="upload-card">
      <h2>File Uploader</h2>
      <p className="subtitle">Securely upload your files to our server</p>
      
      <div className="file-input-wrapper">
        <input type="file" onChange={handleFileChange} />
      </div>

      <button 
        className="upload-button" 
        onClick={handleUpload} 
        disabled={uploading}
      >
        {uploading && <div className="spinner"></div>}
        {uploading ? "Uploading..." : "Upload File"}
      </button>

      {message && (
        <div className={`message ${isError ? 'error' : 'success'}`}>
          {message}
        </div>
      )}
    </div>
  );
};

export default FileUpload;
