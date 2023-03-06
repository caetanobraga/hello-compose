package br.com.cwi.crescer.socialNet.service;
import br.com.cwi.crescer.socialNet.controller.request.CreatePostRequest;
import br.com.cwi.crescer.socialNet.domain.Post;
import br.com.cwi.crescer.socialNet.factories.PostFactory;
import br.com.cwi.crescer.socialNet.factories.UsuarioFactory;
import br.com.cwi.crescer.socialNet.mapper.PostMapper;
import br.com.cwi.crescer.socialNet.repository.PostRepository;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import br.com.cwi.crescer.socialNet.security.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

import static br.com.cwi.crescer.socialNet.domain.Tipo.PUBLICO;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class IncluirPostServiceTest {

    @InjectMocks
    private IncluirPostService tested;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PostMapper postMapper;

    @Captor
    private ArgumentCaptor<Post> postCaptor;

    @Test
    @DisplayName("Deve incluir novo post")
    void deveIncluirPost() {

        Usuario usuario = UsuarioFactory.get();

        CreatePostRequest request = new CreatePostRequest();
        request.setTitulo("Post de teste");
        request.setConteudo("Conteudo de teste");
        request.setImgPostUrl("img url");
        request.setTipo(PUBLICO);

        tested.incluir(request, usuario.getId());

        verify(postRepository).save(postCaptor.capture());
    }

    @Test
    @DisplayName("Deve lançar exceção ao incluir novo post")
    void deveLancarExcecaoAoIncluirPost() {
        Usuario usuario = UsuarioFactory.get();

        CreatePostRequest request = new CreatePostRequest();
        request.setConteudo("Conteudo de teste");
        request.setImgPostUrl("img url");
        request.setTipo(PUBLICO);

        when(postRepository.save(any(Post.class))).thenThrow(new RuntimeException("Erro ao salvar post"));

        assertThrows(RuntimeException.class, () -> tested.incluir(request, usuario.getId()));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar incluir post com usuário inválido")
    void deveLancarExcecaoAoIncluirPostComUsuarioInvalido() {

        CreatePostRequest request = new CreatePostRequest();
        request.setTitulo("Post de teste");
        request.setConteudo("Conteudo de teste");
        request.setImgPostUrl("img url");
        request.setTipo(PUBLICO);

        Long usuarioId = 1L;

        when(usuarioRepository.getById(usuarioId)).thenThrow(new EntityNotFoundException("Usuário não encontrado"));

        assertThrows(EntityNotFoundException.class, () -> tested.incluir(request, usuarioId));
        verifyNoInteractions(postRepository);
    }
}
