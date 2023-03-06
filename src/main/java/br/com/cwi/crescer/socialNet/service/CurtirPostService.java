package br.com.cwi.crescer.socialNet.service;

import br.com.cwi.crescer.socialNet.domain.Post;
import br.com.cwi.crescer.socialNet.domain.Curtida;
import br.com.cwi.crescer.socialNet.mapper.CurtidaMapper;
import br.com.cwi.crescer.socialNet.repository.CurtidaRepository;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import br.com.cwi.crescer.socialNet.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CurtirPostService {

    @Autowired
    CurtidaRepository curtidaRepository;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    BuscaPostPorIdService buscaPostPorIdService;

    @Transactional
    public void adicionarCurtida(Long postId) throws Exception {
        Usuario usuario = usuarioAutenticadoService.get();

        Post post = buscaPostPorIdService.porId(postId);

        Curtida curtida = CurtidaMapper.toEntity(usuario, post);

        curtidaRepository.save(curtida);
    }
}
