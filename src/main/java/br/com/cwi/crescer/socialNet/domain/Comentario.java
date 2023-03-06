package br.com.cwi.crescer.socialNet.domain;


import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Comentario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_comentario_id")
    private Usuario usuario;
}
