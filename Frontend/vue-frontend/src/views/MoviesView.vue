<template>
  <div class="container mx-auto p-6">
    <div class="relative flex items-center justify-between card-content">
        <div class="flex items-center animate-content-slide-in" style="animation-delay: 0.2s;">
        <img src="/src/assets/movie.svg" alt="Movie Header" class="w-16 h-16 mr-4">
         <h2 class="text-4xl font-bold">Movies</h2>
       </div>
          <!-- Create Movie Button -->
          <router-link to="/create-movie" class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded-full transition-colors duration-300 dark:bg-purple-700 dark:hover:bg-purple-800 animate-content-slide-in" style="animation-delay: 0.2s;">Create Movie</router-link>
    </div>
    
      
    <div v-if="loading" class="text-center">Loading...</div>
    <div v-else-if="error" class="text-red-500">{{ error }}</div>
    <ul class="grid grid-cols-1 md:grid-cols-3 gap-6">
      <li v-for="movie in movies" :key="movie.id" class="p-4 shadow-md rounded-lg transition-shadow duration-300 animate-card-slide-up dark:bg-gray-900 dark:text-gray-300 dark:hover:border-purple-600 dark:border dark:border-gray-800 dark:hover:shadow-lg card-content">
        <h3 class="text-xl font-bold dark:text-purple-400 animate-content-slide-in"  style="animation-delay: 0.2s;">{{ movie.title }}</h3>
        <p class="text-gray-600 dark:text-gray-400 animate-content-slide-in" style="animation-delay: 0.4s;">{{ movie.genre }} | {{ movie.releaseYear }}</p>
         <div class="animate-content-slide-in" style="animation-delay: 0.6s;">
            <Reviews :contentId="movie.id" contentType="MOVIE" @review-updated="fetchMovies"/>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import apiClient from "../api/axios";
import Reviews from "../components/Reviews.vue";

export default {
  components: { Reviews },
  data() {
    return { movies: [], loading: true, error: "" };
  },
  async mounted() {
   await this.fetchMovies();
  },
  methods:{
      async fetchMovies() {
        try {
        const response = await apiClient.get("/movies");
        this.movies = response.data;
        } catch (error) {
        this.error = "Failed to fetch movies";
       } finally {
          this.loading = false;
       }
     }
  }
};
</script>

<style scoped>
@keyframes cardSlideUp {
    from {
        opacity: 0;
        transform: translateY(40px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}
@keyframes contentSlideIn {
  from {
        opacity: 0;
        transform: translateX(100%);
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
    animation: contentSlideIn 2s cubic-bezier(0.19, 1, 0.22, 1) forwards;
}
.card-content > * {
    opacity: 0;
}
</style>