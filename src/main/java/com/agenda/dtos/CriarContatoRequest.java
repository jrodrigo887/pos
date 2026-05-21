package com.agenda.dtos;

import com.agenda.domain.Status;
import com.agenda.domain.TipoContato;
import jakarta.validation.constraints.*;

public record CriarContatoRequest(

                @NotBlank(message = "O nome é obrigatório.") @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres.") String nome,

                @NotBlank(message = "O telefone é obrigatório.") String telefone,

                @NotBlank(message = "O e-mail é obrigatório.") @Email(message = "Informe um e-mail válido.") String email,

                String endereco,

                @NotNull(message = "A idade é obrigatória.") @Min(value = 0, message = "Idade inválida.") @Max(value = 150, message = "Idade inválida.") Integer idade,

                @NotNull(message = "O tipo é obrigatório. Use: FAMILIA, AMIGO, TRABALHO ou OUTRO.") TipoContato tipo,

                @NotNull(message = "O status é obrigatório. Use: ATIVO ou INATIVO.") Status status

) {
}
