package qa.guru;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qa.guru.model.Human;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class SelenideFilesTest {
    private final ClassLoader cl = SelenideFilesTest.class.getClassLoader();

    @Test
    void zipParsingPdfTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files/Test_farm.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.contains("JUnit 5 User Guide"));
                }
            }
        }
    }

    @Test
    void zipParsingXlsxTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files/Test_farm.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {
                    XLS xls = new XLS(zis);
                    Assertions.assertEquals(
                            "Наименование задачи:",
                            xls.excel.getSheet("Лист1")
                                    .getRow(0)
                                    .getCell(0)
                                    .getStringCellValue()
                    );
                }
            }
        }
    }

    @Test
    void zipParsingCsvTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files/Test_farm.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();
                    Assertions.assertArrayEquals(new String[]{ "1234", "\"ООО \"Ромашка\"\"", "456789"}, content.get(1));
                    }
               }
            }
        }

    @Test
    void jsomParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files/student.json");
             Reader reader = new InputStreamReader(is)) {
             ObjectMapper objectMapper = new ObjectMapper();
             Human human = objectMapper.readValue(reader, Human.class);

            Assertions.assertEquals("Ekaterina", human.getName());
            Assertions.assertEquals(34, human.getAge());
            Assertions.assertEquals("education", human.getHobby().get(0));
            Assertions.assertEquals("reading", human.getHobby().get(1));
            Assertions.assertEquals("Java", human.getCourseInfo().getLanguage());
            Assertions.assertEquals(24, human.getCourseInfo().getGroup());
                }
            }
}
