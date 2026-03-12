import { Link, useLocation, useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext.jsx'

const NavBar = () => {
  const { identity, isAuthenticated, logout } = useAuth()
  const navigate = useNavigate()
  const location = useLocation()

  if (!isAuthenticated) {
    return null
  }

  const handleLogout = () => {
    logout()
    navigate('/login')
  }

  const isActive = (path) => location.pathname === path

  return (
    <header className="site-nav">
      <div className="brand">PulseID</div>
      <nav className="nav-links">
        <Link className={isActive('/home') ? 'active' : ''} to="/home">
          Home
        </Link>
        <Link className={isActive('/profile') ? 'active' : ''} to="/profile">
          Profile
        </Link>
      </nav>
      <div className="nav-right">
        <span className="user-chip">{identity?.username}</span>
        <button className="ghost-btn" type="button" onClick={handleLogout}>
          Logout
        </button>
      </div>
    </header>
  )
}

export default NavBar
