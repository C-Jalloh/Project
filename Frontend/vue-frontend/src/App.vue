<template>
  <nav class="bg-blue-600 p-4 flex w-full fixed top-0 z-50">
    <div class="flex items-center justify-center w-1/3">
        <img src="/src/assets/logo.svg" alt="App Logo" class="h-8 w-8 mr-2">
        <div class="text-white font-bold text-lg">MyMediaApp</div>
    </div>
    <div class="space-x-4 flex items-center justify-center w-2/3">
      <router-link to="/" class="nav-link">Home</router-link>
      <router-link to="/movies" class="nav-link">Movies</router-link>
      <router-link to="/music" class="nav-link">Music</router-link>
      <router-link to="/playlists" class="nav-link">Playlists</router-link>
      <button @click="logout" class="nav-link">Logout</button>
    </div>
  </nav>
  <div :class="{'dark': isDarkMode}" class="mt-20 h-full">
    <router-view />
  </div>
</template>

<script>
export default {
  data() {
    return {
        isDarkMode: false,
    };
},
  methods: {
    logout() {
      localStorage.removeItem("token");
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
.nav-link {
  color: white;
  text-decoration: none;
  font-weight: bold;
}
.nav-link:hover {
  text-decoration: underline;
}
</style>