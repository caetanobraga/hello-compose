package br.com.cwi.crescer.socialNet.service;

import br.com.cwi.crescer.socialNet.domain.SolicitacaoAmizade;
import br.com.cwi.crescer.socialNet.repository.SolicitacaoAmizadeRepository;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import br.com.cwi.crescer.socialNet.security.repository.UsuarioRepository;
import br.com.cwi.crescer.socialNet.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

import static br.com.cwi.crescer.socialNet.mapper.SolicitacaoAmizadeMapper.toEntity;
import static org.springframework.http.HttpStatus.*;

@Service
public class EnviarSolicitacaoService {

    @Autowired
    SolicitacaoAmizadeRepository solicitacaoAmizadeRepository;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    BuscarUsuarioService buscarUsuarioService;

    @Autowired
    VerificaSeNaoHaSolicitacaoPendenteEntreOsDoisService verificaSeNaoHaSolicitacaoPendenteEntreOsDoisService;

    @Transactional
    public void enviar(Long idDestinatario) {

        Usuario remetente = buscarUsuarioService.porId(usuarioAutenticadoService.getId());

        Usuario destinatario = buscarUsuarioService.porId(idDestinatario);

        if (remetente.getId().equals(idDestinatario)) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível enviar uma solicitação para si mesmo");
        }

        if (verificaSeNaoHaSolicitacaoPendenteEntreOsDoisService.existeSolicitacao(remetente.getId(), destinatario.getId())){
            throw new ResponseStatusException(BAD_REQUEST, "Já existe solicitacao ou amizade entre os dois");
        }

        SolicitacaoAmizade solicitacaoAmizade = toEntity(remetente, destinatario);

        solicitacaoAmizadeRepository.save(solicitacaoAmizade);
    }
}
