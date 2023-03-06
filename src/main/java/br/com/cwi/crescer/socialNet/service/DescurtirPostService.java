package br.com.cwi.crescer.socialNet.service;

import br.com.cwi.crescer.socialNet.domain.Curtida;
import br.com.cwi.crescer.socialNet.domain.Post;
import br.com.cwi.crescer.socialNet.repository.CurtidaRepository;
import br.com.cwi.crescer.socialNet.repository.PostRepository;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import br.com.cwi.crescer.socialNet.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class DescurtirPostService {

    @Autowired
    CurtidaRepository curtidaRepository;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    BuscaPostPorIdService buscaPostPorIdService;

    @Transactional
    public void descurtir(Long idPost) throws Exception {
        Usuario usuario = usuarioAutenticadoService.get();

        Post post = buscaPostPorIdService.porId((idPost));

        Optional<Curtida> optionalCurtida = curtidaRepository.findByUsuarioAndPost(usuario, post);

        Curtida curtida = optionalCurtida.orElseThrow(() -> new ResponseStatusException
                (UNPROCESSABLE_ENTITY, "Post não curtido pelo usuário atual!"));

        curtidaRepository.delete(curtida);
    }
}


