package com.click_clone.click.contoller.poster;

import com.click_clone.click.contoller.convertor.AttachmentConvertor;
import com.click_clone.click.contoller.poster.convertor.PosterConvertor;
import com.click_clone.click.contoller.poster.dto.PosterCreateRequestDto;
import com.click_clone.click.contoller.poster.dto.PosterIdRequestDto;
import com.click_clone.click.contoller.poster.dto.PosterResponseDto;
import com.click_clone.click.contoller.poster.dto.PosterUpdateRequestDto;
import com.click_clone.click.entity.AttachmentEntity;
import com.click_clone.click.entity.PosterEntity;
import com.click_clone.click.service.PosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/poster")
@RequiredArgsConstructor
public class PosterController {
    private final PosterService posterService;
    private final PosterConvertor posterConvertor;

    @GetMapping
    public List<PosterResponseDto> getPosterList() {
        List<PosterEntity> posterList = posterService.getPosterList();
        return posterConvertor.posterListToDtoList(posterList);
    }

    @GetMapping("/{id}")
    public PosterResponseDto getPosterById(@PathVariable UUID id) {
        PosterEntity poster = posterService.getPosterById(id);
        return posterConvertor.entityToDto(poster);
    }

    @PostMapping
    public void createPoster(@RequestBody PosterCreateRequestDto request) {
        PosterEntity poster = posterConvertor.dtoToPoster(request);
        posterService.create(poster);
    }

    @PutMapping("/image")
    public void addImage(@RequestParam("posterId") UUID id,
                         @RequestParam("file") MultipartFile file) throws IOException {
        AttachmentEntity attachment = AttachmentConvertor.convertToEntity(file);
        posterService.addImage(id, attachment);
    }

    @PutMapping
    public void updatePoster(@RequestBody PosterUpdateRequestDto request) {
        posterService.updatePoster(request.getId(), request.getTitle(), request.getContent());
    }

    @PutMapping("/viewer")
    public void addViewer(@RequestBody PosterIdRequestDto request) {
        posterService.addViewer(request.getId());
    }

    @PutMapping("/liked")
    public void pressALike(@RequestBody PosterIdRequestDto request) {
        posterService.pressALike(request.getId());
    }

    @PutMapping("/unliked")
    public void getLikeBack(@RequestBody PosterIdRequestDto request) {
        posterService.getLikeBack(request.getId());
    }

    @DeleteMapping
    public void remove(@RequestBody PosterIdRequestDto request) {
        posterService.inactivatePoster(request.getId());
    }
}
