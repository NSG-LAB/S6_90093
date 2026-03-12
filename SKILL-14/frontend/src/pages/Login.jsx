import { useState } from 'react'
import { Link, useLocation, useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext.jsx'
import { loginUser } from '../services/api.js'

const Login = () => {
  const navigate = useNavigate()
  const location = useLocation()
  const { login } = useAuth()
  const [form, setForm] = useState({ username: '', password: '' })
  const [isLoading, setIsLoading] = useState(false)
  const [error, setError] = useState('')

  const handleChange = (event) => {
    const { name, value } = event.target
    setForm((prev) => ({ ...prev, [name]: value }))
  }

  const handleSubmit = async (event) => {
    event.preventDefault()
    setIsLoading(true)
    setError('')
    try {
      const { data } = await loginUser(form)
      login(data)
      const redirectTo = location.state?.from || '/home'
      navigate(redirectTo, { replace: true })
    } catch (err) {
      const message = err.response?.data?.message || 'Unable to sign in with those credentials.'
      setError(message)
    } finally {
      setIsLoading(false)
    }
  }

  return (
    <section className="form-wrapper">
      <div className="form-card">
        <p className="eyebrow">Welcome</p>
        <h2>Log in to continue</h2>
        <p className="muted">Use the credentials you registered with to access your dashboard.</p>
        {error && <div className="banner error">{error}</div>}
        <form className="stack" onSubmit={handleSubmit}>
          <label>
            Username
            <input
              name="username"
              value={form.username}
              onChange={handleChange}
              autoComplete="username"
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
              autoComplete="current-password"
              required
            />
          </label>
          <button className="primary-btn" type="submit" disabled={isLoading}>
            {isLoading ? 'Signing in...' : 'Sign In'}
          </button>
        </form>
        <p className="muted small">
          Need an account? <Link to="/register">Register here</Link>
        </p>
      </div>
    </section>
  )
}

export default Login
