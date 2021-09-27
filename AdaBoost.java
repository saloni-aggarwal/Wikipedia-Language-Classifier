import java.util.ArrayList;

public class AdaBoost {

    /**
     * This function build a model using adaboost algorithm to predict the values
     * of given input i.e. if the sentence is in dutch or english
     * @param examples        Labelled data set using which model is trained
     * @param attributes      Features of the dataset
     * @param stumps          Number of stumps to be created
     */
    public static void adaBoost(ArrayList<String[]> examples, ArrayList<Integer> attributes, int stumps){
        //Arraylist to store the updated examples to be used for each stump
        ArrayList<String[]> otherExampleSet = new ArrayList<>();
        //To store the sample weight for each stump
        Double[] sampleWeight = new Double[examples.size()];
        double newWeight = 0.0;
        AdaBoostNode node;
        //To store range after normalizing values
        Double[][] normalizedWeightRange;

        otherExampleSet.addAll(examples);

        double weight = -1.0;

        //Initializing sample weight as 1/number of examples
        if (examples.size() > 0)
            weight = 1 / (double) examples.size();
        for (int i = 0; i < examples.size(); i++)
            sampleWeight[i] = weight;

        //For every stump
        for (int iteration = 0; iteration < stumps; iteration++){
            //Build a stump and calculate error in every stump
            int error = AdaBoostMethods.decisionTreeLearningForAdaBoost(otherExampleSet, attributes);

            //Calculate amount of say
            double amountOfSay = AdaBoostMethods.calcAmountOfSay(error, examples.size());

            //Update the sample weight according to the error
            AdaBoostMethods.updateSampleWeight(otherExampleSet, amountOfSay, sampleWeight);

            //Normalize the newly generated weights
            normalizedWeightRange = AdaBoostMethods.normalizeWeights(sampleWeight, newWeight);

            //Make two node and store the left and right child of stump
            node = new AdaBoostNode(amountOfSay,Lab2.adaBoostIndex,"false",Lab2.leftPredict);
            Lab2.adaBoostTree.add(node);
            Lab2.adaBoostTreeHelper.add(node);
            node = new AdaBoostNode(amountOfSay, Lab2.adaBoostIndex, "true", Lab2.rightPredict);
            Lab2.adaBoostTree.add(node);
            Lab2.adaBoostTreeHelper.add(node);

            //Creating new dataset for the next stump
            for (int i = 0; i < examples.size(); i++) {
                double value = Math.random();
                for (int j = 0; j < normalizedWeightRange.length; j++) {
                    if (value > normalizedWeightRange[j][0] && value < normalizedWeightRange[j][1])
                        otherExampleSet.set(i, examples.get(j));
                }
            }
        }
    }
}
