<template>
    <div>
      <div class="flex justify-between items-center">
        <h3 class="text-xl font-semibold dark:text-purple-400 animate-content-slide-in" style="animation-delay: 0.2s;">
          {{ playlist.name }}
        </h3>
        <div class="animate-content-slide-in" style="animation-delay: 0.2s;">
          <button
            @click="deletePlaylist(playlist.id)"
            class="bg-red-500 hover:bg-red-600 text-white font-bold py-1 px-2 rounded mr-2 dark:bg-red-700 dark:hover:bg-red-800" style="animation-delay: 0.4s;"
          >
            Delete
          </button>
          <button
            @click="toggleSongs()"
            class="bg-purple-500 hover:bg-purple-600 text-white font-bold py-1 px-2 rounded dark:bg-purple-700 dark:hover:bg-purple-800" style="animation-delay: 0.6s;"
          >
            {{ showSongs ? 'Hide Songs' : 'View Songs' }}
          </button>
        </div>
      </div>
      <!-- Display Songs in Playlist -->
      <div v-if="showSongs" class="mt-4 animate-content-slide-in" style="animation-delay: 0.4s;">
      <ul  class="mt-4">
        <li
            v-for="song in playlistSongs"
            :key="song.id"
            class="flex justify-between items-center p-2 rounded mt-2 dark:bg-gray-700 dark:text-gray-300"
        >
          <span>{{ song.title }} - {{ song.artist }}</span>
          <button
            @click="removeSongFromPlaylist(playlist.id, song.id)"
            class="bg-orange-500 hover:bg-orange-600 text-white font-bold py-1 px-2 rounded transition-colors duration-300 dark:bg-orange-700 dark:hover:bg-orange-800"
          >
            Remove
          </button>
        </li>
      </ul>
  
          <!-- Add Song to Playlist -->
          <div class="mt-4">
              <h4 class="text-lg font-semibold mb-2 dark:text-gray-300">Add Song to Playlist</h4>
              <select v-model="selectedSong"
                      class="border p-2 rounded w-full dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300">
                  <option v-for="song in allSongs" :key="song.id" :value="song.id">
                      {{ song.title }} - {{ song.artist }}
                  </option>
              </select>
              <button @click="addSongToPlaylist(playlist.id)"
                      class="bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-4 rounded-full transition-colors duration-300 mt-2 dark:bg-green-700 dark:hover:bg-green-800">
                  Add
              </button>
          </div>
      </div>
    </div>
  </template>
  <script>
  import apiClient from "../api/axios";
  export default {
    props: {
      playlist: Object,
      allSongs: Array,
    },
    data() {
      return {
        selectedSong: null,
        selectedPlaylistId: null,
        playlistSongs: [],
        showSongs: false,
        };
      },
      methods: {
      toggleSongs() {
                 this.selectedPlaylistId = this.playlist.id
                   if(this.showSongs) {
                         this.showSongs = false;
                   } else {
                        this.fetchPlaylistSongs()
                        this.showSongs = true;
                   }
  
              },
        async deletePlaylist(playlistId) {
          try {
            await apiClient.delete(`/playlists/${playlistId}`);
            this.$emit("playlist-updated");
          } catch (error) {
            console.error("Failed to delete playlist");
          }
        },
        async addSongToPlaylist(playlistId) {
          if (!this.selectedSong) return;
          try {
            await apiClient.post(`/playlists/${playlistId}/add-music/${this.selectedSong}`);
           await this.fetchPlaylistSongs();
          } catch (error) {
            console.error("Failed to add song to playlist");
          }
        },
        async fetchPlaylistSongs() {
            try {
                 const response = await apiClient.get(`/playlists`);
                   const playlist = response.data.find(p => p.id === this.playlist.id);
                  this.playlistSongs = playlist ? playlist.songs : [];
                   this.selectedPlaylistId = this.playlist.id
              } catch (error) {
                  console.error("Failed to fetch playlist songs");
              }
          },
        async removeSongFromPlaylist(playlistId, songId) {
          try {
            await apiClient.delete(`/playlists/${playlistId}/remove-music/${songId}`);
            await this.fetchPlaylistSongs();
          } catch (error) {
            console.error("Failed to remove song from playlist");
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
      animation: contentSlideIn 2s cubic-bezier(0.19, 1, 0.22, 1) forwards;
  }
  </style>