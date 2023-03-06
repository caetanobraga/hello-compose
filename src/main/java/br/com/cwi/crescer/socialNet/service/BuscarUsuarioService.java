package br.com.cwi.crescer.socialNet.service;

import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import br.com.cwi.crescer.socialNet.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;


import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class BuscarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario porId(Long id) {
        return usuarioRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(UNPROCESSABLE_ENTITY, "Usuário não encontrado"));
    }
}
