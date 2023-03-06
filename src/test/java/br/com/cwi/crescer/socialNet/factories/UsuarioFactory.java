package br.com.cwi.crescer.socialNet.factories;

import br.com.cwi.crescer.socialNet.security.domain.Usuario;

import java.time.LocalDate;

public class UsuarioFactory {

    public static Usuario get() {
        Usuario usuario = new Usuario();
        usuario.setId(SimpleFactory.getRandomLong());
        usuario.setNome("Usuário de teste");
        usuario.setEmail("teste@cwi.com.br");
        usuario.setApelido("tibirica");
        usuario.setDataNascimento(LocalDate.ofEpochDay(1988-05-11));
        return usuario;
    }

    public static Usuario getRemetenteSolicitacao() {
        Usuario remetenteSolicitacao = new Usuario();
        remetenteSolicitacao.setId(SimpleFactory.getRandomLong());
        remetenteSolicitacao.setNome("Remetente solicitacao");
        remetenteSolicitacao.setEmail("remetenteSolicitacao@cwi.com.br");
        remetenteSolicitacao.setApelido("Remetente");
        remetenteSolicitacao.setDataNascimento(LocalDate.ofEpochDay(1988-05-11));
        return remetenteSolicitacao;
    }

    public static Usuario getDestinatarioSolicitacao() {
        Usuario destinatarioSolicitacao = new Usuario();
        destinatarioSolicitacao.setId(2L);
        destinatarioSolicitacao.setNome("Destinatario solicitacao");
        destinatarioSolicitacao.setEmail("destinatarioSolicitacao@cwi.com.br");
        destinatarioSolicitacao.setApelido("Destinatario");
        destinatarioSolicitacao.setDataNascimento(LocalDate.ofEpochDay(1988-05-11));
        return destinatarioSolicitacao;
    }
}
