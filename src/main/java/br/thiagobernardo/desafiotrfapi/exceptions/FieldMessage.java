package br.thiagobernardo.desafiotrfapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldMessage implements Serializable {
    private static final long serialVersionUID = 909080050182765180L;

    private String fieldName;
    private String message;
}
