import java.util.ArrayList;

public class AdaBoostPrediction {

    /**
     * This function predicts the output for a given input using adaboost model
     * @param inputFile     Input whose value is to be predicted
     * @param size          number of stumps to be made
     * @param tree          File containing adaboost model
     * @return              predicted value
     */
    public static String predictingAdaBoost(String[] inputFile, int size, Object tree) {
        ArrayList<AdaBoostNode> makingTree = (ArrayList<AdaBoostNode>) tree;
        //Stores the sum of (prediction value * amount of say of that node)
        double total = 0.0;
        //For every stump
        for (int i = 0; i < size; i++) {
            //If the input is False for the attribute then calculate total according to prediction
            if (inputFile[makingTree.get(i * 2).attribute].equals("false")) {
                if (makingTree.get(i * 2).prediction.equals("en")) {
                    total += makingTree.get(i * 2).amountOfSay * Lab2.English;
                } else {
                    total += makingTree.get(i * 2).amountOfSay * Lab2.Dutch;
                }
                //If its not false
            } else {
                if (makingTree.get((i * 2) + 1).prediction.equals("en")) {
                    total += makingTree.get(i * 2).amountOfSay * Lab2.English;
                } else {
                    total += makingTree.get(i * 2).amountOfSay * Lab2.Dutch;
                }
            }
        }
        //Checking one of which (English or Dutch) total value is close to
        double difference1 = Math.abs(total - Lab2.English);
        double difference2 = Math.abs(total - Lab2.Dutch);
        //The one with least distance is the predicted value
        if (difference1 < difference2)
            return "en";
        else
            return "nl";
    }
}
