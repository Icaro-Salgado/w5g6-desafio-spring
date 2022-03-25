package br.com.mercadolivre.desafiospring.database;

import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
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

    public void writeIntoFile(String filename, Object objectToBeSaved) throws DataBaseWriteException {
        try {
            objectMapper.writeValue(new File(pathDatabase.concat(filename)), objectToBeSaved);
        } catch (IOException e) {
           throw new DataBaseWriteException(
                   "Não foi possível escrever na database ".concat(filename)
           );
        }
    }

    public T readFromFile(String filename, Class<T> typeParameterClass) throws DataBaseReadException {
        try {
            return objectMapper.readValue(new File(pathDatabase.concat(filename)), typeParameterClass);
        } catch (IOException e) {
            throw new DataBaseReadException(
                    "Não foi possível ler da database ".concat(filename)
            );
        }
    }

}