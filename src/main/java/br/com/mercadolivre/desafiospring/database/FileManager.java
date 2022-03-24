package br.com.mercadolivre.desafiospring.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class FileManager<T> {

    private final ObjectMapper objectMapper;

    public FileManager() { this.objectMapper = new ObjectMapper(); }

    public void writeIntoFile(String filename, Object objectToBeSaved) throws IOException {
        objectMapper.writeValue(new File(filename), objectToBeSaved);
    }

    public T readFromFile(String filename, Class<T> typeParameterClass) throws IOException {
        return objectMapper.readValue(new File(filename), typeParameterClass);
    }

}