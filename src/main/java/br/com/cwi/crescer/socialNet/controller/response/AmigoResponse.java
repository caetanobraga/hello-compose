package br.com.cwi.crescer.socialNet.controller.response;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AmigoResponse {
    private Long id;
    private String nome;
    private String apelido;
    private String email;
    private String imagemPerfil;
}
