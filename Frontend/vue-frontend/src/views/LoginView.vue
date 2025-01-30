<template>
    <div class="max-w-md mx-auto p-6 bg-gray-800 rounded-lg shadow-md ">
      <h2 class="text-2xl font-semibold text-center animate-content-slide-in" style="animation-delay: 0.2s;" >Login</h2>
      <form @submit.prevent="login" class="mt-4 card-content">
        <input v-model="username" placeholder="Username" class="input-field animate-content-slide-in" style="animation-delay: 0.4s;" required />
        <input v-model="password" type="password" placeholder="Password" class="input-field animate-content-slide-in" style="animation-delay: 0.4s;" required />
        <button type="submit" class="btn animate-content-slide-in" style="animation-delay: 0.6s;">Login</button>
      </form>
      <p v-if="errorMessage" class="text-red-500 text-center mt-2">{{ errorMessage }}</p>
    </div>
  </template>
  
  <script>
  import apiClient from "../api/axios";
  
  export default {
    data() {
      return { username: "", password: "", errorMessage: "" };
    },
    methods: {
      async login() {
        try {
          const response = await apiClient.post("/auth/login", { username: this.username, password: this.password });
          localStorage.setItem("token", response.data.token);
          this.$router.push("/movies");
        } catch (error) {
          this.errorMessage = "Invalid credentials!";
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .input-field {
    width: 100%;
    padding: 10px;
    margin-top: 8px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }
  .btn {
    width: 100%;
    background-color: #4f46e5;
    color: white;
    padding: 10px;
    margin-top: 10px;
    border-radius: 4px;
    font-weight: bold;
  }
  @keyframes cardSlideUp {
    from {
        opacity: 0;
        transform: translateY(-20px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}
@keyframes contentSlideIn {
  from {
        opacity: 0;
        transform: translateX(-100%);
    }
    to {
        opacity: 1;
        transform: translateX(0);
    }
}
.animate-card-slide-up{
    animation: cardSlideUp 1.2s ease-out forwards;
}
.animate-content-slide-in{
    animation: contentSlideIn 1s cubic-bezier(0.19, 1, 0.22, 1) forwards;
}
.card-content > * {
    opacity: 0;
}
  </style>
  