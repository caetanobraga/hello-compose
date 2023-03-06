package br.com.cwi.crescer.socialNet.repository;

import br.com.cwi.crescer.socialNet.domain.SolicitacaoAmizade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SolicitacaoAmizadeRepository extends JpaRepository<SolicitacaoAmizade, Long> {
    boolean existsByRemetenteIdAndDestinatarioId(Long idRemetente, Long idDestinatario);

    @Query("SELECT s FROM SolicitacaoAmizade s WHERE s.destinatario.id = :idDestinatario AND s.aceita = false")
    List<SolicitacaoAmizade> findSolicitacoesPendentesByDestinatarioId(@Param("idDestinatario") Long idDestinatario);

    @Modifying
    @Transactional
    @Query("DELETE FROM SolicitacaoAmizade s WHERE (s.remetente.id = :remetenteId AND " +
            "s.destinatario.id = :destinatarioId) OR (s.remetente.id = :destinatarioId AND " +
            "s.destinatario.id = :remetenteId)")
    void deleteByRemetenteIdAndDestinatarioId(Long remetenteId, Long destinatarioId);
}
