package br.com.cwi.crescer.socialNet.service;

import br.com.cwi.crescer.socialNet.repository.AmigoRepository;
import br.com.cwi.crescer.socialNet.repository.SolicitacaoAmizadeRepository;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import br.com.cwi.crescer.socialNet.security.repository.UsuarioRepository;
import br.com.cwi.crescer.socialNet.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class DesfazAmizadeService {

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    BuscarUsuarioService buscarUsuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AmigoRepository amigoRepository;

    @Autowired
    SolicitacaoAmizadeRepository solicitacaoAmizadeRepository;

    @Transactional
    public void desfaz(Long idAmigo) {

        if (idAmigo.equals(usuarioAutenticadoService.getId()) ){
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Id do amigo igual ao seu!");
        }

        Usuario usuario = usuarioAutenticadoService.get();
        Usuario amigo = buscarUsuarioService.porId(idAmigo);

        usuario.removerAmigo(amigo);
        amigo.removerAmigo(usuario);

        amigoRepository.deleteByUsuarioIdAndAmigoId(usuario.getId(), amigo.getId());
        solicitacaoAmizadeRepository.deleteByRemetenteIdAndDestinatarioId(usuario.getId(), idAmigo);

        usuarioRepository.save(usuario);
        usuarioRepository.save(amigo);
    }
}

