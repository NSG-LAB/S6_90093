import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext.jsx'
import { fetchProfileById } from '../services/api.js'

const Profile = () => {
  const { identity } = useAuth()
  const navigate = useNavigate()
  const [profile, setProfile] = useState(null)
  const [status, setStatus] = useState({ loading: true, error: '' })

  useEffect(() => {
    if (!identity?.id) {
      navigate('/login')
      return
    }

    const loadProfile = async () => {
      setStatus({ loading: true, error: '' })
      try {
        const { data } = await fetchProfileById(identity.id)
        setProfile(data)
        setStatus({ loading: false, error: '' })
      } catch (err) {
        const detail = err.response?.data?.message || 'Unable to load profile.'
        setStatus({ loading: false, error: detail })
      }
    }

    loadProfile()
  }, [identity, navigate])

  if (status.loading) {
    return (
      <section className="form-wrapper">
        <div className="form-card">
          <p>Loading your data…</p>
        </div>
      </section>
    )
  }

  if (status.error) {
    return (
      <section className="form-wrapper">
        <div className="form-card">
          <div className="banner error">{status.error}</div>
        </div>
      </section>
    )
  }

  return (
    <section className="profile-card">
      <p className="eyebrow">Profile</p>
      <h2>{profile.fullName}</h2>
      <dl>
        <div>
          <dt>Username</dt>
          <dd>{profile.username}</dd>
        </div>
        <div>
          <dt>Email</dt>
          <dd>{profile.email}</dd>
        </div>
        <div>
          <dt>Account created</dt>
          <dd>{new Date(profile.createdAt).toLocaleString()}</dd>
        </div>
        <div>
          <dt>User ID</dt>
          <dd>{profile.id}</dd>
        </div>
      </dl>
    </section>
  )
}

export default Profile
