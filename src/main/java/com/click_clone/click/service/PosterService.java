package com.click_clone.click.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.click_clone.click.entity.UserEntity;
import com.click_clone.click.entity.PosterEntity;
import com.click_clone.click.entity.AttachmentEntity;
import com.click_clone.click.service.util.MessageUtil;
import com.click_clone.click.repository.PosterRepository;
import com.click_clone.click.exception.RecordNotFoundException;

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
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.POSTER_NOT_FOUND_ERROR));
    }

    public PosterEntity create(PosterEntity poster) {
        return posterRepository.save(poster);
    }

    public PosterEntity addImage(UUID id, AttachmentEntity image) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.POSTER_NOT_FOUND_ERROR));
        poster.setImageAttachment(image);
        return posterRepository.save(poster);
    }

    public PosterEntity updatePoster(UUID id, String title, String content) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.POSTER_NOT_FOUND_ERROR));

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
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.POSTER_NOT_FOUND_ERROR));
        poster.getViewers().add(userService.getCurrentUser());
        return posterRepository.save(poster);
    }

    public PosterEntity likePoster(UUID id) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.POSTER_NOT_FOUND_ERROR));

        UserEntity user = userService.getCurrentUser();
        if (!poster.getLikedPeople().contains(user)) {
            return pressALike(poster, user);
        }
        return getLikeBack(poster, user);
    }

    private PosterEntity pressALike(PosterEntity poster, UserEntity user) {
        poster.getLikedPeople().add(user);
        return posterRepository.save(poster);
    }

    private PosterEntity getLikeBack(PosterEntity poster, UserEntity user) {
        poster.getLikedPeople().remove(user);
        return posterRepository.save(poster);
    }

    public PosterEntity inactivatePoster(UUID id) {
        PosterEntity poster = posterRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.POSTER_NOT_FOUND_ERROR));
        poster.setActive(false);
        return posterRepository.save(poster);
    }
}