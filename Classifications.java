import java.util.ArrayList;

public class Classifications {

    /**
     * This method classifies the dataset in correct label
     * @param examples     Examples whose label is to be checked
     * @return             If its english or dutch or null
     */
    public static String classification(ArrayList<String[]> examples) {
        //Stores total english and dutch values from set of example
        int classEnglish = 0, classDutch = 0;
        //Find total english and dutch values
        for (String[] example : examples) {
            if (example[6].equals("en"))
                classEnglish++;
            else
                classDutch++;
        }
        //If there are no english values, predict dutch
        if (classEnglish == 0)
            return "nl";
            //If there are no dutch values, predict english
        else if (classDutch == 0)
            return "en";
            //Else return null
        else
            return null;
    }
}
