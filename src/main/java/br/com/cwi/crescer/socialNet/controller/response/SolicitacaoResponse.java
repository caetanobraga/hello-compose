package br.com.cwi.crescer.socialNet.controller.response;


import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SolicitacaoResponse {
    private Long id;
    private Long remetenteId;
    private boolean aceita;
    private String remetenteNome;
    private String remetenteImagem;
    private String remetenteApelido;
}
