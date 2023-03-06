package br.com.cwi.crescer.socialNet.controller.request;

import br.com.cwi.crescer.socialNet.domain.Tipo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter @Setter
public class CreatePostRequest {
    @NotBlank
    private String titulo;
    @NotBlank
    private String conteudo;

    private String imgPostUrl;
    @NotNull
    private Tipo tipo;
}
