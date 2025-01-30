package com.example.Backend.Entities.Playlists;




import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<List<PlaylistModel>> getUserPlaylists(Principal principal) {
        return ResponseEntity.ok(playlistService.getUserPlaylists(principal.getName()));
    }

    @PostMapping
    public ResponseEntity<PlaylistModel> createPlaylist(Principal principal, @RequestParam String name) {
        return ResponseEntity.ok(playlistService.createPlaylist(principal.getName(), name));
    }

    @PostMapping("/{playlistId}/add-music/{musicId}")
    public ResponseEntity<PlaylistModel> addMusicToPlaylist(@PathVariable Long playlistId, @PathVariable Long musicId) {
        return ResponseEntity.ok(playlistService.addMusicToPlaylist(playlistId, musicId));
    }

    @DeleteMapping("/{playlistId}/remove-music/{musicId}")
    public ResponseEntity<String> removeMusicFromPlaylist(@PathVariable Long playlistId, @PathVariable Long musicId) {
        playlistService.removeMusicFromPlaylist(playlistId, musicId);
        return ResponseEntity.ok("Music removed from playlist");
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Long playlistId) {
        playlistService.deletePlaylist(playlistId);
        return ResponseEntity.ok("Playlist deleted successfully");
    }
}

