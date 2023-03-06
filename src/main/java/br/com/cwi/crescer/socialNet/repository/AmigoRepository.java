package br.com.cwi.crescer.socialNet.repository;

import br.com.cwi.crescer.socialNet.domain.Amigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmigoRepository extends JpaRepository <Amigo, Long>  {


    void deleteByUsuarioIdAndAmigoId(Long idUsuario, Long idAmigo);
}
