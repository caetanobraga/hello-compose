package br.com.cwi.crescer.socialNet.mapper;

import br.com.cwi.crescer.socialNet.domain.Amigo;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;

public class AceitaSolicitacaoMapper {

    public static Amigo toEntity(Usuario remetente, Usuario destinatario){
        return Amigo.builder()
                .usuario(destinatario)
                .amigo(remetente)
                .build();
    }
}
