import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileService implements Serializable {
    private static final long serialVersionUID = 1L;
    public static <T> List<T> readFile(String fileName) {
        List<T> objects = new ArrayList<>();

        // Trich xuat object stream tu fail
        try(ObjectInputStream file = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            boolean eof = false;

            while(!eof) {
                try {
                    T object = (T) file.readObject(); // Doc Object
                    objects.add(object);
                }
                catch (EOFException e) {
                    eof = true;
                }
            }
        }
        catch (EOFException e) { // Het file
            return new ArrayList<>();
        }
        catch (IOException io) {
            System.out.println("IO Exception: " + io.getMessage());
        }
        catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
        }

        return objects;
    }

    public static <T> void writeFile(String fileName, List<T> objects) {
        try (ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            for (T object : objects) {// Voi moi object trong file
                file.writeObject(object);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
