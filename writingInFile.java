import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class writingInFile {

    /**
     * Saving the decision tree model in a file using Serialization
     * @param filename        File where decision tree is to be saved
     */
    public static void writeDecisionTreeInFile(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Lab2.decisionTree);
        oos.close();
    }


    /**
     * This function saves the adaboost model in a file
     * @param fileName      Name of file where adaboost model is to be saved
     */
    public static void writeAdaBoostInFile(String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Lab2.adaBoostTree);
        oos.writeObject(Lab2.adaBoostTreeHelper);
        oos.close();
    }
}
