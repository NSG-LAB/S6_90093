import React from 'react';

const StudentForm = ({ formData, onInputChange, onSubmit, isEditing, onCancelEdit }) => {
  return (
    <div className="card">
      <h2>{isEditing ? 'Update Student' : 'Add Student'}</h2>
      <form onSubmit={onSubmit}>
        <div className="form-group">
          <label htmlFor="name">Name</label>
          <input
            id="name"
            type="text"
            name="name"
            value={formData.name}
            onChange={onInputChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input
            id="email"
            type="email"
            name="email"
            value={formData.email}
            onChange={onInputChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="course">Course</label>
          <input
            id="course"
            type="text"
            name="course"
            value={formData.course}
            onChange={onInputChange}
            required
          />
        </div>

        <div className="actions">
          <button type="submit">{isEditing ? 'Update' : 'Add'}</button>
          {isEditing && (
            <button type="button" className="secondary" onClick={onCancelEdit}>
              Cancel
            </button>
          )}
        </div>
      </form>
    </div>
  );
};

export default StudentForm;
