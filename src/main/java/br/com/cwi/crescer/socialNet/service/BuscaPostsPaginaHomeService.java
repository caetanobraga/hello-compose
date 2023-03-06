package br.com.cwi.crescer.socialNet.service;

import br.com.cwi.crescer.socialNet.controller.response.PostResponse;
import br.com.cwi.crescer.socialNet.mapper.PostMapper;
import br.com.cwi.crescer.socialNet.repository.PostRepository;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import br.com.cwi.crescer.socialNet.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;


@Service
public class BuscaPostsPaginaHomeService {


    @Autowired
    PostRepository postRepository;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    public Page<PostResponse> busca(Pageable pageable) {

        Usuario usuario = usuarioAutenticadoService.get();

        return postRepository.findByUsuarioAndAmigosOrderByDataPostagemDesc(usuario, pageable)
                .map(PostMapper::toResponse);
    }
}
