import java.util.ArrayList;

public class DecisionTreeMethods {

    /**
     * This function checks if the attribute list is null or not
     * @param attributes     list of attributes
     * @return                id attribute is null or not
     */
    public static boolean isAttributeEmpty(ArrayList<Integer> attributes) {
        boolean flag = false;
        for (Integer attribute : attributes) {
            if (attribute != -1) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * This function calculates the entropy of a value
     * @param pValues     ratio of positive values
     * @return            calculated entropy
     */
    public static double entropy(double pValues){
        //Finding probability of qValue
        double qValues = 1 - pValues;
        //If both p and q values are 0 then return 0
        if(pValues == 0.0 || qValues == 0.0)
            return 0.0;
            //Else calculate entropy
        else
            return  -((pValues * (Math.log(pValues)/Math.log(2))) + (qValues * (Math.log(qValues)/Math.log(2))));
    }

    /**
     * This function finds the information gain for a particular set of attributes
     * @param anAttribute      list of values for an attribute
     * @param examples         Examples whose decision tree is to be made
     * @return                 information gain for an attribute
     */
    public static double importance( String[] anAttribute, ArrayList<String[]> examples) {

        //Stores total english and dutch values in example
        double p = 0, n = 0;
        //Stores occurrence of english and dutch when attribute is true and false
        double truePK = 0, falsePK = 0;
        double trueNK = 0, falseNK = 0;

        //Calculating values
        for (int i = 0; i < anAttribute.length; i++) {
            if (anAttribute[i].equals("true") && examples.get(i)[6].equals("en"))
                truePK++;
            else if (anAttribute[i].equals("true") && examples.get(i)[6].equals("nl"))
                trueNK++;
            else if (anAttribute[i].equals("false") && examples.get(i)[6].equals("en"))
                falsePK++;
            else if (anAttribute[i].equals("false") && examples.get(i)[6].equals("nl"))
                falseNK++;
        }
        for (String[] example : examples) {
            if (example[6].equals("en"))
                p++;
            else
                n++;
        }

        //Finding entropy of the parent Example
        double parentEntropy = entropy(p/(p+n));
        //To store remainder
        double remainder;

        //If there are no true values then calculate remainder for only false values
        if(truePK == 0.0 && trueNK == 0.0 )
            remainder = (((falsePK + falseNK) / (p + n)) * entropy(falsePK/(falseNK+falsePK)));

            //If there are no false values then calculate remainder for only true values
        else if(falsePK == 0.0 && falseNK == 0.0)
            remainder = (((truePK + trueNK) / (p + n)) * entropy(truePK/(truePK+trueNK)));

            //Else calculate remainder considering both true and false values
        else
            remainder = (((truePK + trueNK) / (p + n)) * entropy(truePK/(truePK+trueNK))) +
                    (((falsePK + falseNK) / (p + n)) * entropy(falsePK/(falseNK+falsePK)));

        //Returning information gain
        return (parentEntropy - remainder);
    }
}
