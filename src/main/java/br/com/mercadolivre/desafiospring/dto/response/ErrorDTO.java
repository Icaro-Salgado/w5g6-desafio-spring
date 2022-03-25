package br.com.mercadolivre.desafiospring.dto.response;

import java.util.List;

public interface ErrorDTO<T> {
    public ErrorDTO<?> modelToDTO(T model, List<String> errorMessages);

}
