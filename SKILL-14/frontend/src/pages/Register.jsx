import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { registerUser } from '../services/api.js'

const initialState = {
  username: '',
  password: '',
  fullName: '',
  email: '',
}

const Register = () => {
  const navigate = useNavigate()
  const [form, setForm] = useState(initialState)
  const [isSubmitting, setIsSubmitting] = useState(false)
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')

  const handleChange = (event) => {
    const { name, value } = event.target
    setForm((prev) => ({ ...prev, [name]: value }))
  }

  const handleSubmit = async (event) => {
    event.preventDefault()
    setIsSubmitting(true)
    setMessage('')
    setError('')
    try {
      await registerUser(form)
      setMessage('Registration successful. You can log in now.')
      setTimeout(() => navigate('/login'), 1200)
    } catch (err) {
      const detail = err.response?.data?.message || 'Unable to register with the supplied data.'
      setError(detail)
    } finally {
      setIsSubmitting(false)
    }
  }

  return (
    <section className="form-wrapper">
      <div className="form-card">
        <p className="eyebrow">Get started</p>
        <h2>Create an account</h2>
        <p className="muted">
          Complete the form below and we will store your credentials in the Spring Boot backend.
        </p>
        {message && <div className="banner success">{message}</div>}
        {error && <div className="banner error">{error}</div>}
        <form className="stack" onSubmit={handleSubmit}>
          <div className="grid-2">
            <label>
              Full name
              <input name="fullName" value={form.fullName} onChange={handleChange} required />
            </label>
            <label>
              Username
              <input name="username" value={form.username} onChange={handleChange} required />
            </label>
          </div>
          <label>
            Email
            <input
              name="email"
              type="email"
              value={form.email}
              onChange={handleChange}
              required
            />
          </label>
          <label>
            Password
            <input
              name="password"
              type="password"
              value={form.password}
              onChange={handleChange}
              autoComplete="new-password"
              required
            />
          </label>
          <button className="primary-btn" type="submit" disabled={isSubmitting}>
            {isSubmitting ? 'Creating account...' : 'Register'}
          </button>
        </form>
        <p className="muted small">
          Already registered? <Link to="/login">Back to login</Link>
        </p>
      </div>
    </section>
  )
}

export default Register
