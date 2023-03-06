package br.com.cwi.crescer.socialNet.controller;

import br.com.cwi.crescer.socialNet.controller.response.AmigoResponse;
import br.com.cwi.crescer.socialNet.security.controller.response.UsuarioResponse;
import br.com.cwi.crescer.socialNet.service.ListarAmigosService;
import br.com.cwi.crescer.socialNet.service.ProcurarAmigoPorNomeoOuEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.cwi.crescer.socialNet.security.domain.Funcao.Nomes.USUARIO;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/amigo")
public class AmigoController {

    @Autowired
    ListarAmigosService listarAmigosService;

    @Autowired
    ProcurarAmigoPorNomeoOuEmailService procurarAmigoPorNomeoOuEmailService;

    @Secured(USUARIO)
    @GetMapping("/busca-amigos")
    @ResponseStatus(OK)
    public List<AmigoResponse> listar() {
        return listarAmigosService.listar();
    }

    @Secured(USUARIO)
    @GetMapping("/busca-amigo-por-nome-ou-email")
    @ResponseStatus(OK)
    public Page<UsuarioResponse> buscaPorNomeOuEmail(@RequestParam String textoBusca, Pageable pageable){
        return procurarAmigoPorNomeoOuEmailService.procurar(textoBusca, pageable);
    }

}