<template>
    <div class="container mx-auto p-6 animate-card-slide-up" style="animation-delay: 0.2s;">
        <h2 class="text-3xl font-semibold mb-4 dark:text-gray-300 animate-card-slide-up" style="animation-delay: 0.2s;">
            Create New Playlist</h2>
        <form @submit.prevent="createPlaylist"
            class="bg-gray-800 p-6 rounded-lg shadow-md dark:bg-gray-800 dark:text-gray-300 w-1/2">
            <div class="mb-4  animate-content-slide-in" style="animation-delay: 0.4s;">
                <label class="block text-gray-700 text-sm font-bold mb-2 dark:text-gray-300">Playlist Name:</label>
                <input v-model="newPlaylistName" placeholder="Playlist Name" required
                    class="border p-2 rounded mb-4 dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300 w-2/3" />
            </div>
            <div class="flex justify-center  animate-card-slide-up" style="animation-delay: 0.6s;">
                <button type="submit"
                    class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded-full transition-colors duration-300 dark:bg-purple-700 dark:hover:bg-purple-800">Create
                    Playlist</button>
            </div>
        </form>
    </div>
</template>

<script>
    import apiClient from "../api/axios";

    export default {
        data() {
            return { newPlaylistName: "" };
        },
        methods: {
            async createPlaylist() {
                try {
                    await apiClient.post("/playlists", null, { params: { name: this.newPlaylistName } });
                    this.$router.push('/playlists'); // Redirect after creation
                } catch (error) {
                    console.error("Failed to create playlist", error);
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

    .animate-card-slide-up {
        animation: cardSlideUp 1.2s ease-out forwards;
    }

    .animate-content-slide-in {
        animation: contentSlideIn 1s cubic-bezier(0.19, 1, 0.22, 1) forwards;
    }

    .card-content>* {
        opacity: 0;
    }
</style>