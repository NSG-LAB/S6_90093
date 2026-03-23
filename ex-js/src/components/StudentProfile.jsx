import React from 'react';
import './StudentProfile.css';

const StudentProfile = ({ name = 'SIva Ganesh', studentId = '2400090093', age = 20 }) => {
  return (
    <article className="profile-card" role="region" aria-label="Student profile">
      <header className="profile-card__header">
        <p className="profile-card__label">Student Spotlight</p>
        <h3 className="profile-card__name">{name}</h3>
        <p className="profile-card__id">ID: {studentId}</p>
      </header>
      <section className="profile-card__details">
        <p>
          <span className="profile-card__details-label">Age</span>
          <span className="profile-card__details-value">{age}</span>
        </p>
      </section>
      <div className="profile-card__glow" aria-hidden="true" />
    </article>
  );
};

export default StudentProfile;
