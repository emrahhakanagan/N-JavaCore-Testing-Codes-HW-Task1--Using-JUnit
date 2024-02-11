package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.example.CsvJsonParser.listToJson;

public class CsvJsonParserTest {

    private final String[] COLUMN_MAPPING_EMPLOYEE = {"id", "firstName", "lastName", "country", "age"};

    @Test
    @DisplayName("Test ParseCSV Positive")
    public void testParseCsvWithValidInputShouldReturnCorrectData() {
        final String CSV_VALID_FILE_NAME = "valid_data.csv";

        CsvJsonParser parser = new CsvJsonParser();

        List<Employee> resultEmpsList = parser.parseCSV(COLUMN_MAPPING_EMPLOYEE, CSV_VALID_FILE_NAME);

        Assertions.assertNotNull(resultEmpsList, "Список не должен быть null");
        Assertions.assertEquals(2, resultEmpsList.size(), "Ожидается, что размер списка будет 2");

        var firstEmployee = resultEmpsList.get(0);
        Assertions.assertEquals(1, firstEmployee.getId());
        Assertions.assertEquals("John", firstEmployee.getFirstName());
        Assertions.assertEquals("Smith", firstEmployee.getLastName());
        Assertions.assertEquals("USA", firstEmployee.getCountry());
        Assertions.assertEquals(25, firstEmployee.getAge());
    }


    @Test
    @DisplayName("Test ListToJson Positive")
    public void testListToJsonWithValidInputShouldReturnCorrectData() {
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

    @Test
    @DisplayName("Test ParseCSV Negative")
    public void testParseCSVWithInvalidInputShouldReturnEmptyList() {
        final String CSV_INVALID_FILE_NAME = "invalid_data.csv";
        CsvJsonParser parser = new CsvJsonParser();

        List<Employee> resultEmpsList = parser.parseCSV(COLUMN_MAPPING_EMPLOYEE, CSV_INVALID_FILE_NAME);

        var firstPerson = resultEmpsList.isEmpty();
        Assertions.assertTrue(firstPerson);
    }


}
