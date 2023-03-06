package br.com.cwi.crescer.socialNet.repository;

import br.com.cwi.crescer.socialNet.domain.Curtida;
import br.com.cwi.crescer.socialNet.domain.Post;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurtidaRepository extends JpaRepository<Curtida, Long>{

    Optional<Curtida> findByUsuarioAndPost(Usuario usuario, Post post);
}

