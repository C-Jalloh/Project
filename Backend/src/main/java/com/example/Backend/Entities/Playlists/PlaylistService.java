package com.example.Backend.Entities.Playlists;


import com.example.Backend.Entities.Music.MusicModel;
import com.example.Backend.Entities.Music.MusicRepository;
import com.example.Backend.Entities.Users.UserModel;
import com.example.Backend.Entities.Users.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final MusicRepository musicRepository;

    public PlaylistService(PlaylistRepository playlistRepository, UserRepository userRepository, MusicRepository musicRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.musicRepository = musicRepository;
    }

    public List<PlaylistModel> getUserPlaylists(String username) {
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<PlaylistModel> playlists = playlistRepository.findByUser(user);
        return playlists.stream().map(playlist -> {
            playlist.setSongs(playlist.getSongs());
            return playlist;
        }).collect(java.util.stream.Collectors.toList());
    }

    public PlaylistModel createPlaylist(String username, String playlistName) {
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PlaylistModel playlist = new PlaylistModel();
        playlist.setUser(user);
        playlist.setName(playlistName);
        return playlistRepository.save(playlist);
    }

    public PlaylistModel addMusicToPlaylist(Long playlistId, Long musicId) {
        PlaylistModel playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        MusicModel music = musicRepository.findById(musicId)
                .orElseThrow(() -> new RuntimeException("Music not found"));

        playlist.getSongs().add(music);
        return playlistRepository.save(playlist);
    }

    public void removeMusicFromPlaylist(Long playlistId, Long musicId) {
        PlaylistModel playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        MusicModel music = musicRepository.findById(musicId)
                .orElseThrow(() -> new RuntimeException("Music not found"));

        playlist.getSongs().remove(music);
        playlistRepository.save(playlist);
    }

    public void deletePlaylist(Long playlistId) {
        playlistRepository.deleteById(playlistId);
    }
}