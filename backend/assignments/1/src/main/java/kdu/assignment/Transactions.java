package kdu.assignment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Transactions {
    static Logging logger=new Logging();
    private Transactions(){
    }

    public static List<JsonNode> loadTransactionsFromJSON(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> transactions = null;

        try {
            transactions = mapper.readValue(new File(fileName), mapper.getTypeFactory().constructCollectionType(List.class, JsonNode.class));
        } catch (IOException e) {
            logger.logInfo(String.valueOf(e));
        }

        return transactions;
    }
}
