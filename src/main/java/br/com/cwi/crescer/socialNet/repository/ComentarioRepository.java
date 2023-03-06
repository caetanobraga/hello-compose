package br.com.cwi.crescer.socialNet.repository;

import br.com.cwi.crescer.socialNet.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
