package br.com.cwi.crescer.socialNet.controller;

import br.com.cwi.crescer.socialNet.service.CurtirPostService;
import br.com.cwi.crescer.socialNet.service.DescurtirPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

import static br.com.cwi.crescer.socialNet.security.domain.Funcao.Nomes.USUARIO;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/curtida")
public class CurtidaController {

    @Autowired
    CurtirPostService curtirPostService;

    @Autowired
    DescurtirPostService descurtirPostService;

    @Secured(USUARIO)
    @PostMapping("/{idPost}/curtir-post")
    @ResponseStatus(OK)
    public void curtir(@PathVariable Long idPost) throws Exception {
        curtirPostService.adicionarCurtida(idPost);
    }

    @Secured(USUARIO)
    @DeleteMapping("/{idPost}/descurtir-post")
    @ResponseStatus(OK)
    public void descurtir(@PathVariable Long idPost) throws Exception {
        descurtirPostService.descurtir(idPost);
    }
}
