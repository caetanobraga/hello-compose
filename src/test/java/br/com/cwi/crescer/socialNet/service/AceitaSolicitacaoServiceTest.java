package br.com.cwi.crescer.socialNet.service;

import br.com.cwi.crescer.socialNet.domain.Amigo;
import br.com.cwi.crescer.socialNet.domain.SolicitacaoAmizade;
import br.com.cwi.crescer.socialNet.factories.SolicitacaoFactory;
import br.com.cwi.crescer.socialNet.factories.UsuarioFactory;
import br.com.cwi.crescer.socialNet.mapper.AceitaSolicitacaoMapper;
import br.com.cwi.crescer.socialNet.repository.AmigoRepository;
import br.com.cwi.crescer.socialNet.repository.SolicitacaoAmizadeRepository;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import br.com.cwi.crescer.socialNet.security.repository.UsuarioRepository;
import br.com.cwi.crescer.socialNet.security.service.UsuarioAutenticadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AceitaSolicitacaoServiceTest {

    @InjectMocks
    private AceitaSolicitacaoService tested;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private AmigoRepository amigoRepository;

    @Mock
    private SolicitacaoAmizadeRepository solicitacaoAmizadeRepository;

    @Mock
    private AceitaSolicitacaoMapper aceitaSolicitacaoMapper;

    @Test
    @DisplayName("Deve aceitar solicitacao de amizade")
    void deveAceitarSolicitacao() {

        SolicitacaoAmizade solicitacao = SolicitacaoFactory.get();
        Long solicitacaoId = solicitacao.getId();

        when(solicitacaoAmizadeRepository.findById(solicitacaoId)).thenReturn(Optional.of(solicitacao));

        Usuario remetente = UsuarioFactory.getRemetenteSolicitacao();
        Usuario destinatario = UsuarioFactory.getDestinatarioSolicitacao();

        when(usuarioAutenticadoService.getId()).thenReturn(destinatario.getId());

        tested.aceitaSolicitacao(solicitacaoId);

        destinatario.adicionarAmigo(remetente);

        Amigo amigo = aceitaSolicitacaoMapper.toEntity(destinatario, remetente);

        verify(amigoRepository).save(amigo);
        verify(solicitacaoAmizadeRepository).save(solicitacao);
    }

//    @Test
//    @DisplayName("Deve lançar exceção ao incluir novo post")
//    void deveLancarExcecaoAoIncluirPost() {
//        Usuario usuario = UsuarioFactory.get();
//
//        CreatePostRequest request = new CreatePostRequest();
//        request.setConteudo("Conteudo de teste");
//        request.setImgPostUrl("img url");
//        request.setTipo(PUBLICO);
//
//        when(postRepository.save(any(Post.class))).thenThrow(new RuntimeException("Erro ao salvar post"));
//
//        assertThrows(RuntimeException.class, () -> tested.incluir(request, usuario.getId()));
//    }
//
//    @Test
//    @DisplayName("Deve lançar exceção ao tentar incluir post com usuário inválido")
//    void deveLancarExcecaoAoIncluirPostComUsuarioInvalido() {
//
//        CreatePostRequest request = new CreatePostRequest();
//        request.setTitulo("Post de teste");
//        request.setConteudo("Conteudo de teste");
//        request.setImgPostUrl("img url");
//        request.setTipo(PUBLICO);
//
//        Long usuarioId = 1L;
//
//        when(usuarioRepository.getById(usuarioId)).thenThrow(new EntityNotFoundException("Usuário não encontrado"));
//
//        assertThrows(EntityNotFoundException.class, () -> tested.incluir(request, usuarioId));
//        verifyNoInteractions(postRepository);
//    }
}
