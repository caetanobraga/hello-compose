package br.com.cwi.crescer.socialNet.controller;


import br.com.cwi.crescer.socialNet.controller.response.SolicitacaoResponse;
import br.com.cwi.crescer.socialNet.repository.SolicitacaoAmizadeRepository;
import br.com.cwi.crescer.socialNet.service.AceitaSolicitacaoService;
import br.com.cwi.crescer.socialNet.service.BuscarSolicitacoesService;
import br.com.cwi.crescer.socialNet.service.DesfazAmizadeService;
import br.com.cwi.crescer.socialNet.service.EnviarSolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.cwi.crescer.socialNet.security.domain.Funcao.Nomes.USUARIO;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/amizades")
public class SolicitacaoController {

    @Autowired
    private EnviarSolicitacaoService enviarSolicitacaoService;

    @Autowired
    private BuscarSolicitacoesService buscarSolicitacoesService;

    @Autowired
    private AceitaSolicitacaoService aceitaSolicitacaoService;

    @Autowired
    private DesfazAmizadeService desfazAmizadeService;

    @Secured(USUARIO)
    @PostMapping("/{idDestinatario}/envia-solicitacao")
    @ResponseStatus(CREATED)
    public void enviar(@PathVariable Long idDestinatario) {
        enviarSolicitacaoService.enviar(idDestinatario);
    }

    @Secured(USUARIO)
    @GetMapping("/busca-solicitacoes")
    @ResponseStatus(OK)
    public List<SolicitacaoResponse> busca() {
        return buscarSolicitacoesService.buscar();
    }

    @Secured(USUARIO)
    @PutMapping("/{idSolicitacao}/aceita-solicitacao")
    @ResponseStatus(OK)
    public void aceita(@PathVariable Long idSolicitacao) {
        aceitaSolicitacaoService.aceitaSolicitacao(idSolicitacao);
    }

    @Secured(USUARIO)
    @DeleteMapping("/{idAmigo}/desfaz-amizade")
    @ResponseStatus(OK)
    public void removeAmizade(@PathVariable Long idAmigo) {
        desfazAmizadeService.desfaz(idAmigo);
    }


}
