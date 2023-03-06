package br.com.cwi.crescer.socialNet.controller.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    private String titulo;
    private String descricao;
}