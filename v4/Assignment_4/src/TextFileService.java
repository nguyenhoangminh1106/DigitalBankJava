import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFileService {
    private static final String COMMA_DELIMITER = ",";

    public static List<List<String>> readFile(String fileName) {
        List<List<String>> users = new ArrayList<>();

        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)))) {

            sc.useDelimiter(COMMA_DELIMITER);

            while (sc.hasNext()) {

                List<String> user = new ArrayList<>();

                String cccd = sc.next(); // Lay cccd
                sc.skip(COMMA_DELIMITER);
                String name = sc.nextLine(); // Lay ten

                user.add(cccd);
                user.add(name);

                users.add(user); // Them vao list users
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tep khong ton tai");
        }


        return users;
    }

    public static void writeFile(String fileName, List<String> objects) {
            try (FileWriter file = new FileWriter(fileName)) {
                for (String object : objects) { // Duyet tung object va ghi vao file
                    file.write(object);
                    file.write("\n");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
}
