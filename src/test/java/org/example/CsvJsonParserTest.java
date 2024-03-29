package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.example.CsvJsonParser.listToJson;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvJsonParserTest {

    private final String[] COLUMN_MAPPING_EMPLOYEE = {"id", "firstName", "lastName", "country", "age"};
    final String VALID_FILE_CSV_PATH = new File(getClass().getClassLoader().getResource("valid_data.csv")
            .getFile()).getAbsolutePath();
    final String INVALID_FILE_CSV_PATH = new File(getClass().getClassLoader().getResource("invalid_data.csv")
            .getFile()).getAbsolutePath();

    @Test
    @DisplayName("Test ParseCSV Positive")
    public void testParseCsvWithValidInputShouldReturnCorrectData() {
        CsvJsonParser parser = new CsvJsonParser();
        List<Employee> resultEmpsList = parser.parseCSV(COLUMN_MAPPING_EMPLOYEE, VALID_FILE_CSV_PATH);

        Assertions.assertNotNull(resultEmpsList, "Список не должен быть null");
        assertEquals(2, resultEmpsList.size(), "Ожидается, что размер списка будет 2");

        var firstEmployee = resultEmpsList.get(0);
        assertEquals(1, firstEmployee.getId());
        assertEquals("John", firstEmployee.getFirstName());
        assertEquals("Smith", firstEmployee.getLastName());
        assertEquals("USA", firstEmployee.getCountry());
        assertEquals(25, firstEmployee.getAge());
    }

    @Test
    @DisplayName("Test ListToJson Positive")
    public void testListToJsonWithValidInputShouldReturnCorrectData() {
        CsvJsonParser parser = new CsvJsonParser();
        List<Employee> resultEmpsList = parser.parseCSV(COLUMN_MAPPING_EMPLOYEE, VALID_FILE_CSV_PATH);
        String jsonActual = listToJson(resultEmpsList);

        String jsonExpected =
                "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25},"
                + "{\"id\":2,\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}]";

        Assertions.assertEquals(jsonExpected, jsonActual, "JSON строка должна соответствовать ожидаемой");

    }


    @Test
    @DisplayName("Test ParseCSV Negative")
    public void testParseCSVWithInvalidInputShouldReturnEmptyList() {
        CsvJsonParser parser = new CsvJsonParser();
        List<Employee> resultEmpsList = parser.parseCSV(COLUMN_MAPPING_EMPLOYEE, INVALID_FILE_CSV_PATH);

        var firstPerson = resultEmpsList.isEmpty();
        Assertions.assertTrue(firstPerson);
    }

}
