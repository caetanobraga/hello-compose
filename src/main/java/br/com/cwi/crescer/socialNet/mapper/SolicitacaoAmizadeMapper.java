package br.com.cwi.crescer.socialNet.mapper;

import br.com.cwi.crescer.socialNet.controller.response.SolicitacaoResponse;
import br.com.cwi.crescer.socialNet.domain.SolicitacaoAmizade;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;

public class SolicitacaoAmizadeMapper {

    public static SolicitacaoAmizade toEntity(Usuario remetente, Usuario destinatario){
        return SolicitacaoAmizade.builder()
                .remetente(remetente)
                .destinatario(destinatario)
                .aceita(false)
                .build();
    }

    public static SolicitacaoResponse toResponse(SolicitacaoAmizade entity){
        return SolicitacaoResponse.builder()
                .id(entity.getId())
                .remetenteId(entity.getRemetente().getId())
                .aceita(entity.isAceita())
                .remetenteNome(entity.getRemetente().getNome())
                .remetenteImagem(entity.getRemetente().getImagemPerfil())
                .remetenteApelido(entity.getRemetente().getApelido())
                .build();
    }

}
