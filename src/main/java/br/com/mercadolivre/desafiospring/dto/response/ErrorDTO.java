package br.com.mercadolivre.desafiospring.dto.response;

import java.util.List;

public interface ErrorDTO {
    public ErrorDTO modelToDTO(Object model, List<String> errorMessages);
    public void pushMessage(String errorMessage);
    public void pushMessage(List<String> errorMessages);
    List<String> getErrors();

}
