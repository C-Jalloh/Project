<template>
    <div :class="{'dark': isDarkMode}" class="relative overflow-hidden dark:bg-gray-950 min-h-full h-full">
        <div class="container mx-auto p-6">
                <!-- Background Images -->
            <img src="/src/assets/music1.png" alt="Music Background 1" class="absolute top-0 left-1/4 w-[54rem] opacity-20 animate-spin-in"/>
            <img src="/src/assets/music2.png" alt="Music Background 2" class="absolute bottom-1/4 right-1/4 w-[54rem] opacity-20 animate-fade-in-bottom-left"/>
            <img src="/src/assets/movie1.png" alt="Movie Background 1" class="absolute top-1/4 right-1/4 w-[54rem] opacity-20 animate-slide-in-middle-right"/>

            <!-- Dark Mode Toggle -->
            <div class="flex justify-end mb-4 z-10 relative">
                <label class="inline-flex items-center cursor-pointer">
                    <span class="mr-2 text-gray-700 dark:text-gray-300">Dark Mode</span>
                    <input type="checkbox" v-model="isDarkMode" class="sr-only">
                    <div class="relative">
                        <div class="w-10 h-4 bg-gray-400 rounded-full shadow-inner dark:bg-gray-700"></div>
                        <div class="absolute left-1 top-1 w-2 h-2 bg-white rounded-full transition-transform duration-300 transform" :class="{'translate-x-5': isDarkMode}"></div>
                    </div>
                </label>
            </div>

            <!-- Hero Section -->
            <section class="text-center mb-12 py-10 bg-gray-100 rounded-lg shadow-md animate-fade-in dark:bg-gray-800 dark:text-gray-100 z-10 relative">
                <h2 class="text-4xl font-bold mb-4">Welcome to My Media App</h2>
                <p class="text-gray-700 text-lg dark:text-gray-300">
                    Your one-stop destination for movies, music, and playlists. Explore and enjoy!
                </p>
                    <!-- Added Login and Register Buttons -->
                 <div class="mt-4 flex justify-center space-x-4">
                    <router-link to="/login" class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded-full transition-colors duration-300 dark:bg-purple-700 dark:hover:bg-purple-800">Login</router-link>
                     <router-link to="/register" class="bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-4 rounded-full transition-colors duration-300 dark:bg-green-700 dark:hover:bg-green-800">Register</router-link>
                 </div>
            </section>

            <!-- Feature Sections -->
            <section class="grid grid-cols-1 md:grid-cols-3 gap-6 z-10 relative card-content">
                <!-- Movies Feature -->
                <div class="bg-gray-800 p-6 rounded-lg shadow-md hover:shadow-xl transition-shadow duration-300 flex flex-col animate-card-slide-up dark:bg-gray-900 dark:text-gray-300" style="animation-delay: 0.5s;">
                    <h3 class="text-2xl font-semibold mb-2 dark:text-gray-300">Movies</h3>
                    <p class="text-gray-600 dark:text-gray-400 flex-1">
                        Browse, discover, and review your favorite movies.
                    </p>
                    <div class="flex justify-center">
                        <router-link to="/movies" class="mt-4 inline-block bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded-full transition-colors duration-300 dark:bg-purple-700 dark:hover:bg-purple-800">
                            Explore Movies
                        </router-link>
                    </div>
                </div>

                <!-- Music Feature -->
                <div class="bg-gray-800 p-6 rounded-lg shadow-md hover:shadow-xl transition-shadow duration-300 flex flex-col animate-card-slide-up dark:bg-gray-900 dark:text-gray-300"  style="animation-delay: 0.8s;">
                    <h3 class="text-2xl font-semibold mb-2 dark:text-gray-300">Music</h3>
                    <p class="text-gray-600 dark:text-gray-400 flex-1">
                        Listen to your favorite songs, explore new artists, and create your playlists.
                    </p>
                    <div class="flex justify-center">
                    <router-link to="/music" class="mt-4 inline-block bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-4 rounded-full transition-colors duration-300 dark:bg-purple-700 dark:hover:bg-purple-800">
                            Explore Music
                        </router-link>
                    </div>
                </div>

                <!-- Playlists Feature -->
                <div class="bg-gray-800 p-6 rounded-lg shadow-md hover:shadow-xl transition-shadow duration-300 flex flex-col animate-card-slide-up dark:bg-gray-900 dark:text-gray-300"  style="animation-delay: 1.1s;">
                    <h3 class="text-2xl font-semibold mb-2 dark:text-gray-300">Playlists</h3>
                    <p class="text-gray-600 dark:text-gray-400 flex-1">
                        Create, manage, and enjoy your custom music playlists.
                    </p>
                    <div class="flex justify-center">
                    <router-link to="/playlists" class="mt-4 inline-block bg-purple-500 hover:bg-purple-600 text-white font-bold py-2 px-4 rounded-full transition-colors duration-300 dark:bg-purple-700 dark:hover:bg-purple-800">
                            Manage Playlists
                        </router-link>
                    </div>
                </div>
            </section>
        </div>
    </div>
</template>

<script>
export default {
    name: "HomeView",
    data() {
        return {
            isDarkMode: false,
        };
    },
};
</script>

<style scoped>
/* Define animation keyframes */
@keyframes fadeIn {
    from {
        opacity: 0;
        transform: translateY(-20px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

@keyframes slideUp {
    from {
        opacity: 0;
        transform: translateY(40px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

@keyframes spinIn {
    from {
        opacity: 0;
        transform: translateY(-50px) rotate(-360deg);
    }
    to {
         opacity: 0.2;
        transform: translateY(0) rotate(0deg);
    }
}

@keyframes fadeInBottomLeft {
    from {
        opacity: 0;
        transform: translate(-50px, 50px);
    }
    to {
        opacity: 0.2;
        transform: translate(0,0);
    }
}

@keyframes slideInMiddleRight {
    from {
        opacity: 0;
        transform: translateX(100px);
    }
    to {
        opacity: 0.2;
        transform: translateX(0);
    }
}
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

/* Apply animations to tailwind classes */
.animate-fade-in {
    animation: fadeIn 1.5s ease-out forwards;
}


.animate-spin-in{
    animation: spinIn 2s ease-out forwards;
}
.animate-fade-in-bottom-left{
    animation: fadeInBottomLeft 2s ease-out forwards;
}
.animate-slide-in-middle-right{
    animation: slideInMiddleRight 2s ease-out forwards;
}
.animate-card-slide-up{
    animation: cardSlideUp 1s ease-out forwards;
}

.card-content > * {
    opacity: 0;
}
</style>