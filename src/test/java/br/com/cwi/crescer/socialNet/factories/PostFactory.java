package br.com.cwi.crescer.socialNet.factories;

import br.com.cwi.crescer.socialNet.domain.Post;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;

public class PostFactory {

    public static Post get() {
        Usuario usuario = UsuarioFactory.get();


        Post post = new Post();
        post.setId(SimpleFactory.getRandomLong());
        post.setTitulo("Titulo do post");
        post.setConteudo("Conteudo do post");
        post.setImgPostUrl("img url");
        post.setUsuario(usuario);

        return post;
    }
}
