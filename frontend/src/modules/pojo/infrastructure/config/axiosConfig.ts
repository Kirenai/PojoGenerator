import axios from 'axios'

const baseURL = import.meta.env.BACKEND_URL || 'http://localhost:8080/api'

const instance = axios.create({
  baseURL: baseURL,
  timeout: 1500,
  headers: {
    'Content-Type': 'text/plain',
    Accept: 'application/octet-stream',
  },
  responseType: 'blob',
})

export default instance
