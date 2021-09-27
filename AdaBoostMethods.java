import java.util.ArrayList;
import java.util.Arrays;

public class AdaBoostMethods {
    /**
     * This function finds one level decision tree called stump for adaboost model and finds the
     * error in the stump
     * @param examples       Example whose one level decision tree is to be found
     * @param attributes     Features or attributes
     * @return               error in the decision tree
     */
    public static int decisionTreeLearningForAdaBoost(ArrayList<String[]> examples,
                                                      ArrayList<Integer> attributes){
        //To store I.G. of every attribute and initially assigned as -1.0
        double[] gains = new double[8];
        Arrays.fill(gains, -1.0);
        //To store maximum gain
        double max = -1.0;
        //For every attribute remaining
        for (Integer attribute : attributes) {
            String[] oneAttribute = new String[examples.size()];
            int k = 0;
            for (String[] example : examples) {
                oneAttribute[k] = example[attribute];
                k++;
            }
            //Finding gain
            gains[attribute] = DecisionTreeMethods.importance(oneAttribute, examples);
        }

        //Finding maximum gain and storing its index
        for (int i = 0; i < gains.length; i++) {
            if (gains[i] > max) {
                max = gains[i];
                Lab2.adaBoostIndex = i;
            }
        }
        //Storing left and right examples in the arrays
        Lab2.examplesLeft = new ArrayList<>();
        Lab2.examplesRight = new ArrayList<>();
        for (String[] example : examples) {
            if (example[Lab2.adaBoostIndex].equals("False"))
                Lab2.examplesLeft.add(example);
            else
                Lab2.examplesRight.add(example);
        }
        //Calculating error from both child
        return calculatingError();
    }

    /**
     * This function normalizes the weights
     * @param sampleWeight    sample weight of array
     * @param newWeight       Total weight
     * @return                2d array containing normalized range
     */
    public static Double[][] normalizeWeights(Double[] sampleWeight, double newWeight){
        //Calculating normalized weights
        double value = 0.0;
        Double[][] normalizedWeightRange = new Double[sampleWeight.length][2];
        for(int i = 0; i < sampleWeight.length; i++){
            normalizedWeightRange[i][0] = value;
            normalizedWeightRange[i][1] = (value + (sampleWeight[i]/newWeight));
            value = normalizedWeightRange[i][1];
        }
        return normalizedWeightRange;
    }

    /**
     * This function calculates the updated sample weights
     * @param examples        Examples whose sample weight is to be updated
     * @param amountOfSay     amount of say
     * @param sampleWeight    old sample weight array
     */

    public static void updateSampleWeight(ArrayList<String[]> examples, double amountOfSay,
                                          Double[] sampleWeight ){
        //For every example
        for(int i = 0; i < examples.size(); i++){
            //Calculating sample weight
            if(examples.get(i)[Lab2.adaBoostIndex].equals("False")){
                if(examples.get(i)[6].equals(Lab2.leftPredict))
                    sampleWeight[i] = sampleWeight[i] * (Math.exp(-amountOfSay));
                else
                    sampleWeight[i] = sampleWeight[i] * (Math.exp(amountOfSay));
            }
            else{
                if(examples.get(i)[6].equals(Lab2.rightPredict))
                    sampleWeight[i] = sampleWeight[i] * (Math.exp(-amountOfSay));
                else
                    sampleWeight[i] = sampleWeight[i] * (Math.exp(amountOfSay));
            }
        }
    }

    /**
     * This function calculates the amount of say for every stump
     * @param error         Error in stump
     * @param exampleSize   Size of the examples
     * @return              amount of say
     */
    public static double calcAmountOfSay(int error, int exampleSize){
        double amountOfSay;
        if(error == 0)
            amountOfSay = 1.0;
        else {
            double errorRate = ((double) error / (double) exampleSize);
            double value = (1.0 - errorRate) / errorRate;
            amountOfSay = (0.5 * (Math.log(value)));
        }
        return amountOfSay;
    }

    /**
     * This function calculates the error for a stump
     * @return     Total error
     */
    public static int calculatingError(){
        int error = 0;
        //Finding predicted value for left and right child
        Lab2.leftPredict = pluralityValueClass.pluralityValue(Lab2.examplesLeft);
        Lab2.rightPredict = pluralityValueClass.pluralityValue(Lab2.examplesRight);

        /*
        If any of the examples in left and right child don't match their predicted
        value then increase the error accordingly
         */
        for (String[] strings : Lab2.examplesLeft) {
            if (!strings[6].equals(Lab2.leftPredict))
                error++;
        }
        for (String[] strings : Lab2.examplesRight) {
            if (!strings[6].equals(Lab2.rightPredict))
                error++;
        }
        return error;
    }
}
