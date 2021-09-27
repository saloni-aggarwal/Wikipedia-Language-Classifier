import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ExampleSet {
    /**
     * To make a dataset using the feature set
     * @param fileName             File containing english and dutch sentences
     * @return                     an arraylist containing attributes and label
     */
    public static ArrayList<String[]> makingExampleSet(String fileName) throws FileNotFoundException {
        //To store the attributes along with its label
        ArrayList<String[]> examples = new ArrayList<>();
        File file = new File(fileName);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            //Features used
            String a_an = "false", de = "false", the = "false", een_en = "false";
            String wordLength = "false", isDoubleWord = "false";
            double average;
            String line = sc.nextLine();
            String[] oneExampleSet = new String[7];
            String[] lineArray = line.split("\\|");
            //Storing labels i.e. 'en' or 'nl'
            oneExampleSet[6] = lineArray[0];
            //Splitting line and eliminating other characters
            String[] words = lineArray[1].split("[ (-,.]");
            //For each word in line
            for (String value : words) {
                //Check if its "a" or "an"
                if (value.equals("a") || value.equals("an"))
                    a_an = "true";
                //Check if its "de"
                if (value.equals("de") || value.equals("De")) {
                    de = "true";
                }
                //Check if its "the"
                if (value.equals("the"))
                    the = "true";
                //Check if its "een" or "en" or "aan"
                if (value.equals("een") || value.equals("en") || value.equals("aan"))
                    een_en = "true";
            }
            //Stores number of letters in line
            double letterCount = 0.0;
            //Stores number of double words
            int doubleWords = 0;
            for (String s : words) {
                letterCount += s.length();
            }
            //Finding average words in a sentence
            average = letterCount/ words.length;
            //If its greater than 5 then it is one of feature of dutch
            if(average > 5.0)
                wordLength = "true";
            //Calculating number of letters that occur twice consecutively
            for (String word : words) {
                String[] wordArray = word.split("");
                for (int j = 0; j < wordArray.length - 1; j++)
                    if (wordArray[j].equals(wordArray[j + 1]))
                        doubleWords++;
            }
            //If its greater than 3 then it is feature of a dutch language
            if(doubleWords > 3)
                isDoubleWord = "true";
            //Saving values in array
            oneExampleSet[0] = de;
            oneExampleSet[1] = a_an;
            oneExampleSet[2] = een_en;
            oneExampleSet[3] = the;
            oneExampleSet[4] = wordLength;
            oneExampleSet[5] = isDoubleWord;
            //Saving array in arraylist
            examples.add(oneExampleSet);
        }
        return examples;
    }
}
