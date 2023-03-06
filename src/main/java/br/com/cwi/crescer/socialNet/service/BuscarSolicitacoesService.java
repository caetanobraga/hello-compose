package br.com.cwi.crescer.socialNet.service;

import br.com.cwi.crescer.socialNet.controller.response.SolicitacaoResponse;
import br.com.cwi.crescer.socialNet.mapper.SolicitacaoAmizadeMapper;
import br.com.cwi.crescer.socialNet.repository.SolicitacaoAmizadeRepository;
import br.com.cwi.crescer.socialNet.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class BuscarSolicitacoesService {

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    SolicitacaoAmizadeRepository solicitacaoAmizadeRepository;

    public List<SolicitacaoResponse> buscar() {
       return solicitacaoAmizadeRepository.findSolicitacoesPendentesByDestinatarioId(usuarioAutenticadoService.getId())
               .stream()
               .map(SolicitacaoAmizadeMapper::toResponse)
               .collect(toList());
    }
}
