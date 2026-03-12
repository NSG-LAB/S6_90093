import { createContext, useContext, useEffect, useMemo, useState } from 'react'

const STORAGE_KEY = 'demo_auth_user'

const AuthContext = createContext(null)

const parseIdentity = (raw) => {
  try {
    return raw ? JSON.parse(raw) : null
  } catch (error) {
    console.error('Failed to parse auth storage', error)
    return null
  }
}

const getIdentityFromStorage = () => {
  if (typeof window === 'undefined') {
    return null
  }
  const raw = window.localStorage.getItem(STORAGE_KEY)
  return parseIdentity(raw)
}

export const AuthProvider = ({ children }) => {
  const [identity, setIdentity] = useState(() => getIdentityFromStorage())

  useEffect(() => {
    const handleStorage = (event) => {
      if (event.key === STORAGE_KEY) {
        setIdentity(parseIdentity(event.newValue))
      }
    }

    window.addEventListener('storage', handleStorage)
    return () => window.removeEventListener('storage', handleStorage)
  }, [])

  const login = (profile) => {
    const payload = { id: profile.id, username: profile.username }
    window.localStorage.setItem(STORAGE_KEY, JSON.stringify(payload))
    setIdentity(payload)
  }

  const logout = () => {
    window.localStorage.removeItem(STORAGE_KEY)
    setIdentity(null)
  }

  const value = useMemo(
    () => ({
      identity,
      isAuthenticated: Boolean(identity),
      login,
      logout,
    }),
    [identity],
  )

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export const useAuth = () => {
  const context = useContext(AuthContext)
  if (!context) {
    throw new Error('useAuth must be used within AuthProvider')
  }
  return context
}
