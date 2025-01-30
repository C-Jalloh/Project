<template>
  <div class="flex mx-auto p-6 rounded-lg shadow-md justify-center bg-gray-950 dark:bg-gray-950">
      <div class="bg-gray-800 p-8 rounded-lg shadow-md w-full max-w-md dark:bg-gray-800 dark:text-gray-300 animate-card-slide-up">
      <h2 class="text-2xl font-semibold text-center mb-6 dark:text-gray-300 animate-content-slide-in" style="animation-delay: 0.2s;">Register</h2>
        <form @submit.prevent="register" class="flex flex-col card-content">
             <input v-model="username" placeholder="Username" required
                class="input-field animate-content-slide-in" style="animation-delay: 0.4s;"/>
            <input v-model="email" type="email" placeholder="Email" required
                class="input-field  animate-content-slide-in" style="animation-delay: 0.4s;"/>
            <input v-model="password" type="password" placeholder="Password" required
                   class="input-field  animate-content-slide-in" style="animation-delay: 0.4s;"/>
            <div class="flex justify-center animate-content-slide-in" style="animation-delay: 0.6s;">
             <button type="submit"
                class="btn" >
                Register
            </button>
            </div>
        </form>
        <p v-if="message" class="text-red-500 text-center mt-2">{{ message }}</p>
      </div>
    </div>
  </template>
  
  <script>
  import apiClient from "../api/axios";
  
  export default {
    data() {
      return {
        username: "",
        email: "",
        password: "",
        message: "",
      };
    },
    methods: {
      async register() {
        try {
          await apiClient.post("/auth/register", {
            username: this.username,
            email: this.email,
            password: this.password,
          });
          this.message = "Registration successful! You can now log in.";
        } catch (error) {
          this.message = "Error: Could not register!";
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
    width: 70%;
    background-color: #4f46e5;
    color: white;
    padding: 10px;
    margin-top: 10px;
    border-radius: 999px;
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