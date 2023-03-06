package br.com.cwi.crescer.socialNet.security.service;


import br.com.cwi.crescer.socialNet.security.controller.response.UsuarioResponse;
import br.com.cwi.crescer.socialNet.security.mapper.UsuarioMapper;
import br.com.cwi.crescer.socialNet.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProcurarUsuarioPorNomeoOuEmailService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<UsuarioResponse> procurar(String textoPesquisa, Pageable pageable) {

        return usuarioRepository.searchByNameOrEmail((textoPesquisa).toLowerCase(), pageable)
                .map(UsuarioMapper::toResponse);
    }
}


