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
        return posterRepository.findAllByActiveIsTrue();
    }

    public PosterEntity getPosterById(UUID id) {
        return posterRepository.findByIdAndActiveIsTrue(id)
                .orElseThrow(() -> new RecordNotFoundException("Poster not found."));
    }

    public PosterEntity create(PosterEntity poster) {
        return posterRepository.save(poster);
    }

    public PosterEntity addImage(UUID id, AttachmentEntity image) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Poster not found."));
        poster.setImageAttachment(image);
        return posterRepository.save(poster);
    }

    public PosterEntity updatePoster(UUID id, String title, String content) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Poster not found."));

        if (title != null) {
            poster.setTitle(title);
        }

        if (content != null) {
            poster.setContent(content);
        }

        return posterRepository.save(poster);
    }

    public PosterEntity addViewer(UUID id) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Poster not found."));
        poster.getViewers().add(userService.getCurrentUser());
        return posterRepository.save(poster);
    }

    public PosterEntity pressALike(UUID id) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Poster not found."));
        poster.getLikedPeople().add(userService.getCurrentUser());
        return posterRepository.save(poster);
    }

    public PosterEntity getLikeBack(UUID id) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Poster not found."));
        poster.getLikedPeople().remove(userService.getCurrentUser());
        return posterRepository.save(poster);
    }

    public PosterEntity inactivatePoster(UUID id) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Poster not found."));
        poster.setActive(false);
        return posterRepository.save(poster);
    }
}