import axios from 'axios'

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api'

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
})

export const registerUser = (payload) => api.post('/auth/register', payload)
export const loginUser = (payload) => api.post('/auth/login', payload)
export const fetchProfileById = (id) => api.get(`/users/${id}`)
export const fetchProfileByUsername = (username) => api.get(`/users/by-username/${username}`)

export default api
