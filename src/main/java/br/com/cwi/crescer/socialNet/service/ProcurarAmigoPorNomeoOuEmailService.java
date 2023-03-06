package br.com.cwi.crescer.socialNet.service;

import br.com.cwi.crescer.socialNet.security.controller.response.UsuarioResponse;
import br.com.cwi.crescer.socialNet.security.mapper.UsuarioMapper;
import br.com.cwi.crescer.socialNet.security.repository.UsuarioRepository;
import br.com.cwi.crescer.socialNet.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProcurarAmigoPorNomeoOuEmailService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public Page<UsuarioResponse> procurar(String textoBusca, Pageable pageable) {
        return usuarioRepository.searchByNomeOrEmailAndFriends((textoBusca).toLowerCase(), pageable, usuarioAutenticadoService.getId())
                .map(UsuarioMapper::toResponse);
    }
}
