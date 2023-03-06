package br.com.cwi.crescer.socialNet.service;

import br.com.cwi.crescer.socialNet.domain.Amigo;
import br.com.cwi.crescer.socialNet.domain.SolicitacaoAmizade;
import br.com.cwi.crescer.socialNet.repository.AmigoRepository;
import br.com.cwi.crescer.socialNet.repository.SolicitacaoAmizadeRepository;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import br.com.cwi.crescer.socialNet.security.repository.UsuarioRepository;
import br.com.cwi.crescer.socialNet.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

import static br.com.cwi.crescer.socialNet.mapper.AceitaSolicitacaoMapper.toEntity;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class AceitaSolicitacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private AmigoRepository amigoRepository;

    @Autowired
    private SolicitacaoAmizadeRepository solicitacaoAmizadeRepository;

    @Transactional
    public void aceitaSolicitacao(Long solicitacaoId) {

        SolicitacaoAmizade solicitacao = solicitacaoAmizadeRepository.findById(solicitacaoId)
                .orElseThrow(() -> new ResponseStatusException(UNPROCESSABLE_ENTITY,
                        "Solicitação de amizade não encontrada com id " + solicitacaoId));

        Usuario remetente = solicitacao.getRemetente();
        Usuario destinatario = solicitacao.getDestinatario();

        if(destinatario.getId() != usuarioAutenticadoService.getId()){
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY,
                    "Solicitação de amizade não pertence a esse usuário!");
        }

        destinatario.adicionarAmigo(remetente);

        Amigo amigo = toEntity(destinatario, remetente);

        amigoRepository.save(amigo);

        solicitacao.setAceita(true);
        solicitacaoAmizadeRepository.save(solicitacao);
    }
}
