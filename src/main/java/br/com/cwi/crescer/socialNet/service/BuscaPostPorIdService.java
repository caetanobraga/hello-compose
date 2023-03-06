package br.com.cwi.crescer.socialNet.service;

import br.com.cwi.crescer.socialNet.domain.Post;
import br.com.cwi.crescer.socialNet.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;


@Service
public class BuscaPostPorIdService {
    @Autowired
    private PostRepository postRepository;

    public Post porId(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                        (UNPROCESSABLE_ENTITY, "Post não encontrado - "+id));
    }

}