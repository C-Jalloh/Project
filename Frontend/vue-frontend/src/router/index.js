import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";
import RegisterView from "../views/RegisterView.vue";
import MoviesView from "../views/MoviesView.vue";
import MusicView from "../views/MusicView.vue";
import PlaylistsView from "../views/PlaylistsView.vue";
import CreateMovieView from "../views/CreateMovieView.vue";
import CreateMusicView from "../views/CreateMusicView.vue";
import CreatePlaylistsView from "../views/CreatePlaylistsView.vue";


const routes = [
  { path: "/", component: HomeView },
  { path: "/login", component: LoginView },
  { path: "/register", component: RegisterView },
  { path: "/movies", component: MoviesView },
  { path: "/music", component: MusicView },
  { path: "/playlists", component: PlaylistsView },
    { path: "/create-movie", component: CreateMovieView },
      { path: "/create-music", component: CreateMusicView },
        { path: "/create-playlist", component: CreatePlaylistsView },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;