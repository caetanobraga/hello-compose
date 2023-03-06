package br.com.cwi.crescer.socialNet.mapper;

import br.com.cwi.crescer.socialNet.controller.response.AmigoResponse;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;

public class ListaDeAmigosMapper {
    public static AmigoResponse toResponse(Usuario entity){
        return AmigoResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .apelido(entity.getApelido())
                .email(entity.getEmail())
                .imagemPerfil(entity.getImagemPerfil())
                .build();
    }
}


