package br.com.cwi.crescer.socialNet.service;

import br.com.cwi.crescer.socialNet.controller.request.AdicionaComentarioRequest;

import br.com.cwi.crescer.socialNet.domain.Comentario;
import br.com.cwi.crescer.socialNet.domain.Post;
import br.com.cwi.crescer.socialNet.mapper.ComentarioMapper;
import br.com.cwi.crescer.socialNet.repository.ComentarioRepository;
import br.com.cwi.crescer.socialNet.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AdicionaComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    BuscaPostPorIdService buscaPostPorIdService;


    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Transactional
    public void adiciona(AdicionaComentarioRequest request, Long idPost) {

        Post post = buscaPostPorIdService.porId(idPost);

        Comentario comentario = ComentarioMapper.toEntity(request, post);

        comentario.setUsuario(usuarioAutenticadoService.get());

        comentarioRepository.save(comentario);
    }
}
