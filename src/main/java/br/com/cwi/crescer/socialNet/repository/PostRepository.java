package br.com.cwi.crescer.socialNet.repository;

import br.com.cwi.crescer.socialNet.domain.Post;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.usuario = :usuario OR p.usuario IN " +
            "(SELECT a.amigo FROM Amigo a WHERE " +
            "a.usuario = :usuario) ORDER BY p.dataInclusao DESC")
    Page<Post> findByUsuarioAndAmigosOrderByDataPostagemDesc(Usuario usuario, Pageable pageable);
}
