package com.giovanacgois.aluraflix.entrypoint;

import com.giovanacgois.aluraflix.usecase.GetVideoByIdInteractor;
import com.giovanacgois.aluraflix.usecase.GetVideosInteractor;
import com.giovanacgois.aluraflix.usecase.SaveVideoInteractor;
import com.giovanacgois.aluraflix.usecase.impl.UpdateVideoInteractorImpl;
import com.giovanacgois.aluraflix.usecase.dto.VideoRequest;
import com.giovanacgois.aluraflix.usecase.dto.VideoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    GetVideosInteractor getVideosInteractor;

    @Autowired
    GetVideoByIdInteractor getVideoByIdInteractor;

    @Autowired
    SaveVideoInteractor saveVideoInteractor;

    @Autowired
    UpdateVideoInteractorImpl updateVideoInteractor;

    @GetMapping
    public List<VideoResponse> getVideos() {
        return getVideosInteractor.execute();
    }

    @GetMapping("/{id}")
    public VideoResponse getVideoById(@PathVariable("id") String id) {

        VideoResponse video = getVideoByIdInteractor.execute(id);

        if (video != null) return video;
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "O vídeo com ID " + id + " não existe na base de dados.");
    }

    @PostMapping
    public VideoResponse saveVideo(@RequestBody @Valid VideoRequest video) {
        return saveVideoInteractor.execute(video);
    }

    @PutMapping("/{id}")
    public VideoResponse updateVideo(@PathVariable("id") String id,
                                     @RequestBody @Valid VideoRequest video) {
        return updateVideoInteractor.execute(id, video);
    }

}
