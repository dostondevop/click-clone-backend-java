package com.click_clone.click.service;

import com.click_clone.click.entity.AttachmentEntity;
import com.click_clone.click.entity.PosterEntity;
import com.click_clone.click.exception.RecordNotFoundException;
import com.click_clone.click.repository.PosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PosterService {
    private final PosterRepository posterRepository;
    private final UserService userService;

    public List<PosterEntity> getPosterList() {
        return posterRepository.findAllByActiveIsTrueOrderByCreatedByDesc();
    }

    public PosterEntity getPosterById(UUID id) {
        return posterRepository.findByIdAndActiveIsTrue(id)
                .orElseThrow(() -> new RecordNotFoundException("Poster not found."));
    }

    public void create(PosterEntity poster) {
        posterRepository.save(poster);
    }

    public void addImage(UUID id, AttachmentEntity image) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Poster not found."));
        poster.setImageAttachment(image);
        posterRepository.save(poster);
    }

    public void updatePoster(UUID id, String title, String content) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Poster not found."));

        if (title != null) {
            poster.setTitle(title);
        }

        if (content != null) {
            poster.setContent(content);
        }

        posterRepository.save(poster);
    }

    public void addViewer(UUID id) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Poster not found."));
        poster.getViewers().add(userService.getCurrentUser());
        posterRepository.save(poster);
    }

    public void pressALike(UUID id) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Poster not found."));
        poster.getLikedPeople().add(userService.getCurrentUser());
        posterRepository.save(poster);
    }

    public void getLikeBack(UUID id) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Poster not found."));
        poster.getLikedPeople().remove(userService.getCurrentUser());
        posterRepository.save(poster);
    }

    public void inactivatePoster(UUID id) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Poster not found."));
        poster.setActive(false);
        posterRepository.save(poster);
    }
}
