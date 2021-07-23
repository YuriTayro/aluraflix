package com.giovanacgois.aluraflix.usecase.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoResponse {
    private String titulo;
    private String descricao;
    private String url;
}
