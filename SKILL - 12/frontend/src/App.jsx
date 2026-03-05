import React, { useEffect, useState } from 'react';
import axios from 'axios';
import StudentForm from './components/StudentForm';
import StudentList from './components/StudentList';

const API_URL = 'http://localhost:8080/students';

const initialFormState = {
  name: '',
  email: '',
  course: ''
};

const App = () => {
  const [students, setStudents] = useState([]);
  const [formData, setFormData] = useState(initialFormState);
  const [editingId, setEditingId] = useState(null);

  const fetchStudents = async () => {
    const response = await axios.get(API_URL);
    setStudents(response.data);
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const resetForm = () => {
    setFormData(initialFormState);
    setEditingId(null);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (editingId === null) {
      await axios.post(API_URL, formData);
    } else {
      await axios.put(`${API_URL}/${editingId}`, formData);
    }

    resetForm();
    await fetchStudents();
  };

  const handleEdit = (student) => {
    setEditingId(student.id);
    setFormData({
      name: student.name,
      email: student.email,
      course: student.course
    });
  };

  const handleDelete = async (id) => {
    await axios.delete(`${API_URL}/${id}`);
    setStudents((prev) => prev.filter((student) => student.id !== id));

    if (editingId === id) {
      resetForm();
    }
  };

  return (
    <div className="container">
      <h1>Student Management System</h1>
      <StudentForm
        formData={formData}
        onInputChange={handleInputChange}
        onSubmit={handleSubmit}
        isEditing={editingId !== null}
        onCancelEdit={resetForm}
      />
      <StudentList students={students} onEdit={handleEdit} onDelete={handleDelete} />
    </div>
  );
};

export default App;
