package br.thiagobernardo.desafiotrfapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StandardError implements Serializable {
    private static final long serialVersionUID = 8396492450905350836L;

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
