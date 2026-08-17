package com.javanauta.usuario.service.business.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TelefoneDTO {

    private Long id;
    private String numero;
    private String ddd;
}
