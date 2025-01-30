<template>
  <div :class="{ 'dark': isDarkMode }" class="container mx-auto p-6 dark:bg-gray-950 min-h-full h-full">
      <div class="relative flex items-center justify-between card-content">
          <div class="flex items-center animate-content-slide-in" style="animation-delay: 0.2s;">
              <img src="/src/assets/playlist.svg" alt="Playlist Header" class="w-16 h-16 mr-4">
              <h2 class="text-4xl font-bold dark:text-gray-300">My Playlists</h2>
          </div>
              <router-link to="/create-playlist" class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded-full transition-colors duration-300 dark:bg-purple-700 dark:hover:bg-purple-800 animate-content-slide-in" style="animation-delay: 0.2s;">Create Playlist</router-link>
      </div>
       
      <div class="mt-8 ">
      <ul class="grid grid-cols-1 md:grid-cols-3 gap-6 ">
          <li v-for="playlist in playlists" :key="playlist.id"
              class="bg-gray-800 p-4 shadow-md  rounded-lg mb-4 dark:bg-gray-900 dark:text-gray-300 transition-shadow duration-300 animate-card-slide-up dark:hover:border-purple-600 dark:border dark:border-gray-800 dark:hover:shadow-lg">
               <PlaylistForm :playlist="playlist" :all-songs="allSongs" @playlist-updated="fetchPlaylists"/>
          </li>
      </ul>
   </div>
  </div>
</template>

<script>
import apiClient from "../api/axios";
import PlaylistForm from "../components/PlaylistForm.vue"

export default {
  components:{PlaylistForm},
  data() {
      return {
          playlists: [],
          newPlaylistName: "",
          allSongs: [],
         isDarkMode: false
      };
  },
  async mounted() {
      await this.fetchPlaylists();
      await this.fetchAllSongs();
  },
  methods: {
     async fetchPlaylists() {
          try {
              const response = await apiClient.get("/playlists");
              this.playlists = response.data;
          } catch (error) {
              console.error("Failed to fetch playlists");
          }
      },
    async fetchAllSongs() {
          try {
              const response = await apiClient.get("/music");
              this.allSongs = response.data;
          } catch (error) {
              console.error("Failed to fetch songs");
          }
      },
     async createPlaylist() {
          try {
              await apiClient.post("/playlists", null, {params: {name: this.newPlaylistName}});
              this.newPlaylistName = "";
            await this.fetchPlaylists();
               await this.fetchAllSongs();
          } catch (error) {
              console.error("Failed to create playlist");
          }
      },
      
  },
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
.animate-card-slide-up{
  animation: cardSlideUp 1.2s ease-out forwards;
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
.animate-content-slide-in{
  animation: contentSlideIn 2s cubic-bezier(0.19, 1, 0.22, 1) forwards;
}
.card-content>* {
        opacity: 0;
    }
</style>