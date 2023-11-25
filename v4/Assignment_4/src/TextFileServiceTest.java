import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextFileServiceTest {


    @Test
    void readFile() {
            // Tao file thu
            String fileName = "test.txt";
            String fileContent = "001000000000,Minh\n001111111111,Linh\n";
            List<List<String>> expectedResult = new ArrayList<>();
            List<String> fileElement1 = new ArrayList<>();
            List<String> fileElement2 = new ArrayList<>();

            fileElement1.add("001000000000");
            fileElement1.add("Minh");
            fileElement2.add("001111111111");
            fileElement2.add("Linh");
            expectedResult.add(fileElement1);
            expectedResult.add(fileElement2);

            Path filePath = Paths.get(fileName);

            try {
                // Viet noi dung can thu vao file
                Files.write(filePath, fileContent.getBytes(StandardCharsets.UTF_8));

                // Goi phuong thuc
                List<List<String>> result = TextFileService.readFile(fileName);

                // So sanh ket qua
                assertEquals(expectedResult, result);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Xoa file
                try {
                    Files.delete(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


    }

    @Test
    void writeFile() throws IOException {
        List<String> content = new ArrayList<>();
        content.add("001000000000,Minh");
        content.add("001111111111,Linh");

        // Tao file
        File testFile = new File("testFile.txt");
        Path filePath = Paths.get("testFile.txt");


        String testData = "001000000000,Minh\n001111111111,Linh\n";

        // Ghi file
        TextFileService.writeFile("testFile.txt", content);

        String fileContents = Files.readString(testFile.toPath());
        assertEquals(testData, fileContents);

        Files.delete(filePath);
    }
}