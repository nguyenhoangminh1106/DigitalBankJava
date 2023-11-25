import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryFileServiceTest {

    @Test
    void readFile() throws IOException {
        List<Customer> content = new ArrayList<>();
        Customer fileElement1 = new Customer("001000000000", "Minh");
        Customer fileElement2 = new Customer("001111111111", "Linh");

        content.add(fileElement1);
        content.add(fileElement2);

        String fileName = "test.dat";
        Path filePath = Paths.get(fileName);

        BinaryFileService.writeFile(fileName, content);
        List<Customer> expectedContent = BinaryFileService.readFile(fileName);

        Customer firstElement = expectedContent.get(0);
        Customer secondElement = expectedContent.get(1);

        assertEquals(fileElement1.getName(), firstElement.getName());
        assertEquals(fileElement2.getName(), secondElement.getName());
        assertEquals(fileElement1.getCustomerId(), firstElement.getCustomerId());
        assertEquals(fileElement2.getCustomerId(), secondElement.getCustomerId());

        Files.delete(filePath);
    }



    @Test
    public void writeFile() throws FileNotFoundException, IOException {
        List<Customer> content = new ArrayList<>();
        Customer fileElement1 = new Customer("001000000000", "Minh");
        Customer fileElement2 = new Customer("001111111111", "Linh");

        content.add(fileElement1);
        content.add(fileElement2);

        String fileName = "test.dat";
        Path filePath = Paths.get(fileName);

        BinaryFileService.writeFile(fileName, content);
        List<Customer> expectedContent = BinaryFileService.readFile(fileName);

        Customer firstElement = expectedContent.get(0);
        Customer secondElement = expectedContent.get(1);

        assertEquals(fileElement1.getName(), firstElement.getName());
        assertEquals(fileElement2.getName(), secondElement.getName());
        assertEquals(fileElement1.getCustomerId(), firstElement.getCustomerId());
        assertEquals(fileElement2.getCustomerId(), secondElement.getCustomerId());

        Files.delete(filePath);
    }
}