package br.thiagobernardo.desafiotrfapi.empresa;

public enum TipoEmpresaEnum {
    MATRIZ, FILIAL;

    public static TipoEmpresaEnum toEnum(String key) {
        if (key == null) {
            return null;
        }

        for (TipoEmpresaEnum x : TipoEmpresaEnum.values()) {
            if (key.equals(x.name())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Chave inválida: " + key);
    }
}
