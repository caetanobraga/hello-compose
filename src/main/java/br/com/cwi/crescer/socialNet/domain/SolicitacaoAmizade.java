package br.com.cwi.crescer.socialNet.domain;

import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class SolicitacaoAmizade {

        @Id
        @GeneratedValue(strategy = IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "remetente_id")
        private Usuario remetente;

        @ManyToOne
        @JoinColumn(name = "destinatario_id")
        private Usuario destinatario;

        private boolean aceita;
}
