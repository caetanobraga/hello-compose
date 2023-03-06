package br.com.cwi.crescer.socialNet.security.controller.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private String apelido;
}