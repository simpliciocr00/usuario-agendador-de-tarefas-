package com.javanauta.usuario.service.business.dto;

import com.javanauta.usuario.infrastructure.entity.Endereco;
import com.javanauta.usuario.infrastructure.entity.Telefone;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTO> enderecos;
    private List<TelefoneDTO> telefones;

}
