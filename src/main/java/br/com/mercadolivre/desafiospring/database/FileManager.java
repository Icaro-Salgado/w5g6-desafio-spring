package br.com.mercadolivre.desafiospring.database;

import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseManagementException;
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

    @Value("${path.database.default.file}")
    private String pathDefaultDatabases;

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
        // TODO: Treat this exception

        try {
            File db = this.connect(filename);
            return objectMapper.readValue(db, typeParameterClass);
        } catch (IOException e) {
            throw new DataBaseReadException(
                    "Não foi possível ler a database ".concat(filename)
            );
        } catch (DataBaseManagementException e) {
            e.printStackTrace();
            return null;
        }
    }

    private File connect(String Dbname) throws DataBaseManagementException {
        File db = new File(pathDatabase.concat(Dbname));

        try {
            return db.exists() ? db : this.loadDefaultDBFrom(Dbname);

        } catch (SecurityException e) {
            throw new DataBaseManagementException("Não foi possível ler da database devido a permissão do arquivo".concat(Dbname));

        }
    }

    private File loadDefaultDBFrom(String Dbname) throws DataBaseManagementException {
        File defaultDBFile = new File(pathDefaultDatabases.concat(Dbname).replace(".json", "").concat("_default.json"));

        if (!defaultDBFile.exists()) {
            throw new DataBaseManagementException("Você precisa criar um modelo padrão para a database");
        }

        // Cria uma nova dabase
        File dbFile = new File(pathDatabase.concat(Dbname));

        try {
            Files.copy(defaultDBFile.toPath(), dbFile.toPath());
        } catch (IOException e) {
            throw new DataBaseManagementException("Não foi possível gerar uma nova base de dados");
        }

        return dbFile;
    }
}