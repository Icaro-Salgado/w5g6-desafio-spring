package br.com.mercadolivre.desafiospring.database;

import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class FileManager<T> {

    private final ObjectMapper objectMapper;

    @Value("${path.database.file}")
    private String pathDatabase;

    public FileManager() {
        this.objectMapper = new ObjectMapper();
    }

    public void writeIntoFile(String filename, Object objectToBeSaved) throws DataBaseWriteException {
        try {
            objectMapper.writeValue(new File(pathDatabase.concat(filename)), objectToBeSaved);
        } catch (IOException e) {
            throw new DataBaseWriteException(
                    "Não foi possível escrever a database ".concat(filename)
            );
        }
    }

    public T readFromFile(String filename, Class<T> typeParameterClass) throws DataBaseReadException {
        try {
            return objectMapper.readValue(new File(pathDatabase.concat(filename)), typeParameterClass);
        } catch (IOException e) {
            throw new DataBaseReadException(
                    "Não foi possível ler a database ".concat(filename)
            );
        }
    }

    private File connect(String Dbname) throws DataBaseException {
        File db = new File(pathDatabase.concat(Dbname));

        try {
            return db.exists() ? db : this.loadDefaultDBFrom(Dbname);

        } catch (SecurityException e) {
            throw new DataBaseException("Não foi possível ler da database devido a permissão do arquivo".concat(Dbname));

        }
    }

    private File loadDefaultDBFrom(String Dbname) throws DataBaseException {
        File defaultDBFile = new File(pathDatabase.concat("default_db/").concat(Dbname));

        if (defaultDBFile.exists()) {
            try {
                Files.copy(defaultDBFile.toPath(), new File(pathDatabase.concat(Dbname)).toPath());
            } catch (IOException e) {
                throw new DataBaseException("Não foi possível gerar uma nova base de dados");
            }
        }

        return new File(pathDatabase.concat(Dbname));
    }
}