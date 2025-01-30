<template>
    <div class="container mx-auto p-6 animate-card-slide-up" style="animation-delay: 0.2s;">
        <h2 class="text-3xl font-semibold mb-4 dark:text-gray-300 animate-content-slide-up" style="animation-delay: 0.2s;">Create New Music</h2>
        <form @submit.prevent="createMusic" class="bg-gray-800 p-6 rounded-lg shadow-md dark:bg-gray-800 dark:text-gray-300 w-1/2">
              <div class="mb-4 animate-content-slide-in" style="animation-delay: 0.4s;" >
            <label class="block text-gray-700 text-sm font-bold mb-2 dark:text-gray-300">Title:</label>
                <input v-model="music.title" type="text" placeholder="Music Title" required class="border p-2 rounded w-full dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300"/>
            </div>
              <div class="mb-4 animate-content-slide-in" style="animation-delay: 0.4s;" >
              <label class="block text-gray-700 text-sm font-bold mb-2 dark:text-gray-300">Artist:</label>
                 <input v-model="music.artist" type="text" placeholder="Artist Name"  class="border p-2 rounded w-full dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300"/>
             </div>
               <div class="mb-4 animate-content-slide-in" style="animation-delay: 0.4s;" >
            <label class="block text-gray-700 text-sm font-bold mb-2 dark:text-gray-300">Album:</label>
                <input v-model="music.album" type="text" placeholder="Album Name" class="border p-2 rounded w-full dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300"/>
            </div>
             <div class="mb-4animate-content-slide-in" style="animation-delay: 0.4s;" >
            <label class="block text-gray-700 text-sm font-bold mb-2 dark:text-gray-300">Genre:</label>
               <input v-model="music.genre" type="text" placeholder="Genre" class="border p-2 rounded w-full dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300"/>
            </div>
              <div class="mb-4 animate-content-slide-in" style="animation-delay: 0.4s;" >
             <label class="block text-gray-700 text-sm font-bold mb-2 dark:text-gray-300">Release Year:</label>
                 <input v-model="music.releaseYear" type="number" placeholder="Release Year" class="border p-2 rounded w-full dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300"/>
             </div>
              <div class="mb-4 animate-content-slide-in" style="animation-delay: 0.4s;" >
           <label class="block text-gray-700 text-sm font-bold mb-2 dark:text-gray-300">Duration:</label>
                  <input v-model="music.duration" type="number" placeholder="Duration in seconds" class="border p-2 rounded w-full dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300"/>
            </div>
               <div class="flex justify-center animate-content-slide-in" style="animation-delay: 0.6s;" >
            <button type="submit" class="bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-4 rounded-full transition-colors duration-300 dark:bg-green-700 dark:hover:bg-green-800">Create Music</button>
            </div>
        </form>
    </div>
</template>

<script>
import apiClient from "../api/axios";

export default {
    data() {
        return {
            music: {
                title: "",
                artist: "",
                album: "",
                genre: "",
                releaseYear: null,
                duration: null,
            }
        }
    },
    methods: {
        async createMusic() {
            try {
                await apiClient.post("/music", this.music);
                  this.$router.push('/music'); // Redirect after creation
            } catch (error) {
                console.error("Failed to create music", error);
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