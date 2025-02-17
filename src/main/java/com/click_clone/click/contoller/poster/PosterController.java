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
    public PosterResponseDto createPoster(@RequestBody PosterCreateRequestDto request) {
        PosterEntity poster = posterConvertor.dtoToPoster(request);
        PosterEntity posterEntity = posterService.create(poster);
        return posterConvertor.entityToDto(posterEntity);
    }

    @PutMapping("/image")
    public PosterResponseDto addImage(@RequestParam("posterId") UUID id,
                         @RequestParam("file") MultipartFile file) throws IOException {
        AttachmentEntity attachment = AttachmentConvertor.convertToEntity(file);
        PosterEntity poster = posterService.addImage(id, attachment);
        return posterConvertor.entityToDto(poster);
    }

    @PutMapping
    public PosterResponseDto updatePoster(@RequestBody PosterUpdateRequestDto request) {
        PosterEntity poster = posterService.updatePoster(request.getId(), request.getTitle(),
                request.getContent());
        return posterConvertor.entityToDto(poster);
    }

    @PutMapping("/viewer")
    public PosterResponseDto addViewer(@RequestBody PosterIdRequestDto request) {
        PosterEntity poster = posterService.addViewer(request.getId());
        return posterConvertor.entityToDto(poster);
    }

    @PutMapping("/liked")
    public PosterResponseDto pressALike(@RequestBody PosterIdRequestDto request) {
        PosterEntity poster = posterService.likePoster(request.getId());
        return posterConvertor.entityToDto(poster);
    }

    @DeleteMapping
    public PosterResponseDto remove(@RequestBody PosterIdRequestDto request) {
        PosterEntity poster = posterService.inactivatePoster(request.getId());
        return posterConvertor.entityToDto(poster);
    }
}
