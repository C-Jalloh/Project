package com.example.Backend.Entities.Music;

import com.example.Backend.Entities.Reviews.ContentType;
import com.example.Backend.Entities.Reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicService {

    private final MusicRepository musicRepository;
    private final ReviewService reviewService;

    public MusicService(MusicRepository musicRepository, ReviewService reviewService) {
        this.musicRepository = musicRepository;
        this.reviewService = reviewService;
    }

    public List<MusicModel> getAllMusic() {
        List<MusicModel> musicList = musicRepository.findAll();
        return musicList.stream().map(music -> {
            Double averageRating = reviewService.getAverageRating(music.getId(), ContentType.MUSIC);
            music.setRating(averageRating);
            return music;
        }).collect(java.util.stream.Collectors.toList());
    }

    public Optional<MusicModel> getMusicById(Long id) {
        return musicRepository.findById(id).map(music -> {
            Double averageRating = reviewService.getAverageRating(music.getId(), ContentType.MUSIC);
            music.setRating(averageRating);
            return music;
        });
    }

    public MusicModel addMusic(MusicModel music) {
        return musicRepository.save(music);
    }

    public MusicModel updateMusic(Long id, MusicModel updatedMusic) {
        return musicRepository.findById(id).map(music -> {
            music.setTitle(updatedMusic.getTitle());
            music.setArtist(updatedMusic.getArtist());
            music.setAlbum(updatedMusic.getAlbum());
            music.setGenre(updatedMusic.getGenre());
            music.setReleaseYear(updatedMusic.getReleaseYear());
            music.setDuration(updatedMusic.getDuration());
            if (updatedMusic.getRating() != null) {
                music.setRating(updatedMusic.getRating());
            } else {
                Double averageRating = reviewService.getAverageRating(music.getId(), ContentType.MUSIC);
                music.setRating(averageRating);
            }
            return musicRepository.save(music);
        }).orElseThrow(() -> new RuntimeException("Music not found"));
    }

    public void deleteMusic(Long id) {
        musicRepository.deleteById(id);
    }
}