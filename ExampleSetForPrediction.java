import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ExampleSetForPrediction {
    /**
     * This function finds the features for the input that is needed to be predicted
     * and predict the output accordingly
     * @param filename       File that contain inputs that is to be predicted
     * @return               File containing attributes for each input
     */
    public static ArrayList<String[]> makingExamplesForPrediction (String filename) throws FileNotFoundException {
        ArrayList<String[]> examples = new ArrayList<>();
        File file = new File(filename);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String a_an = "false", de = "false", the = "false", een_en = "false";
            String wordLength = "false", isDoubleWord = "false";
            double average;
            String line = sc.nextLine();
            String[] oneExampleSet = new String[6];
            String[] words = line.split("[ (-,.]");
            for (String value : words) {
                if (value.equals("a") || value.equals("an") || value.equals("A") || value.equals("An"))
                    a_an = "true";
                if (value.equals("de") || value.equals("De")) {
                    de = "true";
                }
                if (value.equals("the") || value.equals("The"))
                    the = "true";
                if (value.equals("een") || value.equals("en") || value.equals("aan"))
                    een_en = "true";
            }
            double letterCount = 0.0;
            for (String s : words) {
                letterCount += s.length();
            }
            int doubleWords = 0;
            average = letterCount/ words.length;
            if(average > 5.0)
                wordLength = "true";
            for (String word : words) {
                String[] wordArray = word.split("");
                for (int j = 0; j < wordArray.length - 1; j++)
                    if (wordArray[j].equals(wordArray[j + 1]))
                        doubleWords++;
            }
            if(doubleWords > 3)
                isDoubleWord = "true";
            oneExampleSet[0] = de;
            oneExampleSet[1] = a_an;
            oneExampleSet[2] = een_en;
            oneExampleSet[3] = the;
            oneExampleSet[4] = wordLength;
            oneExampleSet[5] = isDoubleWord;
            examples.add(oneExampleSet);
        }
        return examples;
    }
}
