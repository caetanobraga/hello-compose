package br.com.cwi.crescer.socialNet.service;

import br.com.cwi.crescer.socialNet.controller.response.AmigoResponse;
import br.com.cwi.crescer.socialNet.mapper.ListaDeAmigosMapper;
import br.com.cwi.crescer.socialNet.security.domain.Usuario;
import br.com.cwi.crescer.socialNet.security.repository.UsuarioRepository;
import br.com.cwi.crescer.socialNet.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarAmigosService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    public List<AmigoResponse> listar() {
        List<Usuario> amigos = usuarioRepository.findAmigosByUsuarioId(usuarioAutenticadoService.getId());
        return amigos.stream()
                .map(ListaDeAmigosMapper::toResponse)
                .collect(Collectors.toList());
    }
}