package br.com.cwi.crescer.socialNet.security.repository;

import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT a.amigo FROM Amigo a JOIN a.usuario u WHERE u.id = :usuarioId")
    List<Usuario> findAmigosByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("select u from Usuario u WHERE lower(u.nome) LIKE %:searchText% OR lower(u.email) LIKE %:searchText%")
    Page<Usuario> searchByNameOrEmail(String searchText, Pageable pageable);

    @Query("SELECT u FROM Usuario u WHERE (LOWER(u.nome) LIKE %:searchText% OR LOWER(u.email) LIKE %:searchText%) AND u.id IN (SELECT a.amigo.id FROM Amigo a WHERE a.usuario.id = :idUsuario)")
    Page<Usuario> searchByNomeOrEmailAndFriends(String searchText, Pageable pageable, Long idUsuario);

}
