package br.com.cwi.crescer.socialNet.mapper;

import br.com.cwi.crescer.socialNet.controller.request.AdicionaComentarioRequest;
import br.com.cwi.crescer.socialNet.controller.response.ComentarioResponse;
import br.com.cwi.crescer.socialNet.domain.Comentario;
import br.com.cwi.crescer.socialNet.domain.Post;

public class ComentarioMapper {
    public static Comentario toEntity(AdicionaComentarioRequest request, Post post){
        return Comentario.builder()
                .conteudo(request.getConteudo())
                .post(post)
                .build();
    }


    public static ComentarioResponse toResponse(Comentario entity){
        return ComentarioResponse.builder()
                .conteudo(entity.getConteudo())
                .build();
    }
}

