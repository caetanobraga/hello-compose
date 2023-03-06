package br.com.cwi.crescer.socialNet.security.domain;

import br.com.cwi.crescer.socialNet.domain.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String apelido;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String senha;

    @Column
    private String imagemPerfil;

    @Column(nullable = false)
    private boolean ativo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<br.com.cwi.crescer.socialNet.security.domain.Permissao> permissoes = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "remetente")
    private List<SolicitacaoAmizade> solicitacoesEnviadas;

    @OneToMany(mappedBy = "destinatario")
    private List<SolicitacaoAmizade> solicitacoesRecebidas;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Amigo> amigos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Curtida> curtidasRecebidas = new ArrayList<>();

    public void adicionarAmigo(Usuario amigo) {
        Amigo novaAmizade = new Amigo(this, amigo);
        amigos.add(novaAmizade);
        amigo.getAmigos().add(novaAmizade);
    }

    public void removerAmigo(Usuario amigo) {
        for (Iterator<Amigo> iterator = amigos.iterator();
             iterator.hasNext(); ) {
            Amigo amizade = iterator.next();

            if (amizade.getAmigo().equals(amigo) && amizade.getUsuario().equals(this)) {
                iterator.remove();
                amizade.getAmigo().getAmigos().remove(amizade);
                amizade.setUsuario(null);
                amizade.setAmigo(null);
            }
        }
    }

    public void adicionarPermissao(br.com.cwi.crescer.socialNet.security.domain.Permissao permissao) {
        this.permissoes.add(permissao);
        permissao.setUsuario(this);
    }
}
