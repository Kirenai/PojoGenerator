import axios from 'axios'

const instance = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 1500,
  headers: {
    'Content-Type': 'text/plain',
    Accept: 'application/octet-stream',
  },
  responseType: 'blob',
})

export default instance
