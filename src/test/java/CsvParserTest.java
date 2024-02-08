import org.example.CsvJsonParser;
import org.example.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.example.CsvJsonParser.listToJson;
import static org.example.CsvJsonParser.parseCSV;

public class CsvParserTest {

    @Test
    public void testParseCSV() {
        CsvJsonParser parser = new CsvJsonParser();
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";

        List<Employee> result = parseCSV(columnMapping, fileName);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testListToJson() {
        CsvJsonParser parser = new CsvJsonParser();
        List<Employee> employees = List.of(
                new Employee(1, "John", "Doe", "USA", 30),
                new Employee(2, "Anna", "Smith", "UK", 25)
        );

        String json = listToJson(employees);

        Assertions.assertNotNull(json);
        Assertions.assertTrue(json.startsWith("["));
        Assertions.assertTrue(json.endsWith("]"));
    }
}
