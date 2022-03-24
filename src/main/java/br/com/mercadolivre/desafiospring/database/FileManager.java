package br.com.mercadolivre.desafiospring.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class FileManager<T> {

    private final ObjectMapper objectMapper;

    @Value("${path.database.file}")
    private  String pathDatabase;

    public FileManager() { this.objectMapper = new ObjectMapper(); }

    public void writeIntoFile(String filename, Object objectToBeSaved) throws IOException {
        objectMapper.writeValue(new File(pathDatabase.concat(filename)), objectToBeSaved);
    }

    public T readFromFile(String filename, Class<T> typeParameterClass) throws IOException {
        return objectMapper.readValue(new File(pathDatabase.concat(filename)), typeParameterClass);
    }

}