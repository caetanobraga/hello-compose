package br.com.cwi.crescer.socialNet.mapper;

import br.com.cwi.crescer.socialNet.domain.Curtida;
import br.com.cwi.crescer.socialNet.domain.Post;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;

import java.time.LocalDateTime;

public class CurtidaMapper {
    public static Curtida toEntity(Usuario usuario, Post post){
        return Curtida.builder()
            .usuario(usuario)
            .post(post)
            .dataCriacao(LocalDateTime.now())
            .build();
    }
}
