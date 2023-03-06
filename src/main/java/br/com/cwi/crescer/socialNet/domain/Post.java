package br.com.cwi.crescer.socialNet.domain;

import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String conteudo;

    private String imgPostUrl;

    @Enumerated(STRING)
    private Tipo tipo;

    private LocalDateTime dataInclusao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "post")
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Curtida> curtidas = new ArrayList<>();
}