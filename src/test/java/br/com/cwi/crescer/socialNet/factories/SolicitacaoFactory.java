package br.com.cwi.crescer.socialNet.factories;

import br.com.cwi.crescer.socialNet.domain.SolicitacaoAmizade;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;


public class SolicitacaoFactory {

    public static SolicitacaoAmizade get() {

        Usuario remetenteSolicitacao= UsuarioFactory.getRemetenteSolicitacao();
        Usuario destinatarioSolicitacao= UsuarioFactory.getDestinatarioSolicitacao();

        SolicitacaoAmizade novaSolicitacao = new SolicitacaoAmizade();
        novaSolicitacao.setId(SimpleFactory.getRandomLong());
        novaSolicitacao.setAceita(false);
        novaSolicitacao.setRemetente(remetenteSolicitacao);
        novaSolicitacao.setDestinatario(destinatarioSolicitacao);

        return novaSolicitacao;
    }
}
