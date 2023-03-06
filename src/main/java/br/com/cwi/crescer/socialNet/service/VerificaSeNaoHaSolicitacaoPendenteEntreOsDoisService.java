package br.com.cwi.crescer.socialNet.service;

import br.com.cwi.crescer.socialNet.repository.SolicitacaoAmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificaSeNaoHaSolicitacaoPendenteEntreOsDoisService {

    @Autowired
    SolicitacaoAmizadeRepository solicitacaoAmizadeRepository;

    public boolean existeSolicitacao(Long idRemetente, Long idDestinatario) {
        return solicitacaoAmizadeRepository.existsByRemetenteIdAndDestinatarioId(idRemetente, idDestinatario) ||
                solicitacaoAmizadeRepository.existsByRemetenteIdAndDestinatarioId(idDestinatario, idRemetente);
    }
}
