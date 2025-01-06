import axios from 'axios'

const baseURL = import.meta.env.VITE_BACKEND_URL || 'http://localhost:8080/api'

const instance = axios.create({
  baseURL: baseURL,
  timeout: 1500,
  headers: {
    'Content-Type': 'application/json',
    Accept: 'application/octet-stream',
  },
  responseType: 'blob',
})

export default instance
