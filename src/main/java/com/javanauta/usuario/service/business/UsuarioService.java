package com.javanauta.usuario.service.business;

import com.javanauta.usuario.infrastructure.entity.Usuario;
import com.javanauta.usuario.infrastructure.exceptions.ConflictExeception;
import com.javanauta.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.usuario.infrastructure.repository.UsuarioRepository;
import com.javanauta.usuario.service.business.converter.UsuarioConverter;
import com.javanauta.usuario.service.business.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }

    // METODO PARA VERIFICAR EMAIL E SE EXISTIR GERAR UMA EXCEÇÃO
    public void emailExiste(String email){
        try{
            boolean existe = verificaEmailExistente(email);
            if(existe){
                throw new ConflictExeception("Email já cadastrado" + email);
            }
        }catch (ConflictExeception e){
            throw new ConflictExeception("Email já cadastrado" + e.getCause());
        }
    }

    // METODO APENAS PARA CHAMAR A FUNÇÃO NA REPOSITORY
    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    // METODO PARA BUSCAR USUARIO
    public Usuario buscaUsuarioPorEmail (String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("email não encontrado" + email));
    }

    // METODO PARA DELETAR USUARIO
    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }
}
