package br.com.cwi.crescer.socialNet.security.controller;

import br.com.cwi.crescer.socialNet.security.controller.request.UsuarioRequest;
import br.com.cwi.crescer.socialNet.security.controller.response.UsuarioResponse;
import br.com.cwi.crescer.socialNet.security.service.IncluirUsuarioService;
import br.com.cwi.crescer.socialNet.security.service.ProcurarUsuarioPorNomeoOuEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private ProcurarUsuarioPorNomeoOuEmailService procurarUsuarioPorNomeoOuEmailService;

    @Autowired
    private IncluirUsuarioService service;

    @PostMapping
    public UsuarioResponse incluir(@RequestBody UsuarioRequest request) {
        return service.incluir(request);
    }

    @GetMapping("/busca-contato")
    public Page<UsuarioResponse> buscaPorNomeOuEmail(@RequestParam String textoBusca, Pageable pageable){
        return procurarUsuarioPorNomeoOuEmailService.procurar(textoBusca, pageable);
    }

}
