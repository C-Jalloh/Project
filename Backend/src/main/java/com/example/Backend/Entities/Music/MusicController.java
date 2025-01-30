package com.example.Backend.Entities.Music;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping
    public ResponseEntity<List<MusicModel>> getAllMusic() {
        return ResponseEntity.ok(musicService.getAllMusic());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MusicModel>> getMusicById(@PathVariable Long id) {
        return ResponseEntity.ok(musicService.getMusicById(id));
    }

    @PostMapping
    public ResponseEntity<MusicModel> addMusic(@RequestBody MusicModel music) {
        return ResponseEntity.ok(musicService.addMusic(music));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicModel> updateMusic(@PathVariable Long id, @RequestBody MusicModel music) {
        return ResponseEntity.ok(musicService.updateMusic(id, music));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMusic(@PathVariable Long id) {
        musicService.deleteMusic(id);
        return ResponseEntity.ok("Music deleted successfully");
    }
}