package br.com.cwi.crescer.socialNet.controller;

import br.com.cwi.crescer.socialNet.controller.request.AdicionaComentarioRequest;
import br.com.cwi.crescer.socialNet.controller.request.CreatePostRequest;
import br.com.cwi.crescer.socialNet.controller.response.PostResponse;
import br.com.cwi.crescer.socialNet.service.AdicionaComentarioService;
import br.com.cwi.crescer.socialNet.service.BuscaPostsPaginaHomeService;
import br.com.cwi.crescer.socialNet.service.IncluirPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;


import static br.com.cwi.crescer.socialNet.security.domain.Funcao.Nomes.USUARIO;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private IncluirPostService incluirPostService;

    @Autowired
    private AdicionaComentarioService adicionaComentarioService;

    @Autowired
    private BuscaPostsPaginaHomeService buscaPostsPaginaHomeService;

    @Secured(USUARIO)
    @PostMapping("/{idUsuario}/novo-post")
    @ResponseStatus(CREATED)
    public void incluir(@Valid @RequestBody CreatePostRequest request, @PathVariable Long idUsuario) {
        incluirPostService.incluir(request, idUsuario);
    }

    @Secured(USUARIO)
    @PostMapping("/{idPost}/adiciona-comentario")
    @ResponseStatus(CREATED)
    public void adiciona(@Valid @RequestBody AdicionaComentarioRequest request, @PathVariable Long idPost) {
        adicionaComentarioService.adiciona(request, idPost);
    }

    @Secured(USUARIO)
    @GetMapping("/busca-posts")
    @ResponseStatus(OK)
    public Page<PostResponse> busca(Pageable pageable) {
        return buscaPostsPaginaHomeService.busca(pageable);
    }
}
