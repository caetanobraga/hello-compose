package br.com.cwi.crescer.socialNet.mapper;

import br.com.cwi.crescer.socialNet.controller.request.CreatePostRequest;
import br.com.cwi.crescer.socialNet.controller.response.ComentarioResponse;
import br.com.cwi.crescer.socialNet.controller.response.PostResponse;
import br.com.cwi.crescer.socialNet.domain.Comentario;
import br.com.cwi.crescer.socialNet.domain.Post;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class PostMapper {

    public static Post toEntity(CreatePostRequest request, Usuario usuario){
        return Post.builder()
            .usuario(usuario)
            .titulo(request.getTitulo())
            .conteudo(request.getConteudo())
            .imgPostUrl(request.getImgPostUrl())
            .tipo(request.getTipo())
            .dataInclusao(LocalDateTime.now())
            .build();

    }

    public static PostResponse toResponse(Post post) {
        return PostResponse.builder()
                .titulo(post.getTitulo())
                .descricao(post.getConteudo())
                .build();
    }
}
