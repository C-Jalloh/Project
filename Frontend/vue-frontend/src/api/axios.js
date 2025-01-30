import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080/api", // Adjust if backend runs on a different port
  headers: {
    "Content-Type": "application/json",
  },
});

// Automatically add token to requests if available
apiClient.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default apiClient;
