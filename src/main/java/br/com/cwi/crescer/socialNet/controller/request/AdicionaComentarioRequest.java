package br.com.cwi.crescer.socialNet.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AdicionaComentarioRequest {

    @NotBlank
    private String conteudo;

}

