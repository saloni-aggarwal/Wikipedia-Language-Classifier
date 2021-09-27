import java.util.ArrayList;

public class pluralityValueClass {

    /**
     * This method calculates the plurality value of a given set of examples
     *
     * @param examples    Examples whose plurality value is to be checked
     * @return            plurality value
     */
    public static String pluralityValue(ArrayList<String[]> examples) {
        //Stores total english and dutch values from set of example
        int classEnglish = 0, classDutch = 0;
        //Find total english and dutch values
        for (String[] example : examples) {
            if (example[6].equals("en"))
                classEnglish++;
            else
                classDutch++;
        }
        //If number of english and dutch values are same then predict english
        if(classEnglish == classDutch)
            return "en";
            //if english values are more than dutch values than predict english
        else if (classEnglish > classDutch)
            return "en";
            //else predict dutch
        else
            return "nl";
    }
}
