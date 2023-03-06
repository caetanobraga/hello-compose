package br.com.cwi.crescer.socialNet.service;

import br.com.cwi.crescer.socialNet.controller.request.CreatePostRequest;
import br.com.cwi.crescer.socialNet.domain.Post;
import br.com.cwi.crescer.socialNet.mapper.PostMapper;
import br.com.cwi.crescer.socialNet.repository.PostRepository;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import br.com.cwi.crescer.socialNet.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IncluirPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void incluir(CreatePostRequest request, Long idUsusario) {
        Usuario usuario = usuarioRepository.getById(idUsusario);

        Post post = PostMapper.toEntity(request, usuario);

        postRepository.save(post);
    }
}
