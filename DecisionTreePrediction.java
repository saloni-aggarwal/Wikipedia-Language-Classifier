import java.util.ArrayList;

public class DecisionTreePrediction {

    /**
     * This function predicts the output for the unlabelled data set using the
     * decision tree model
     * @param input        Input attributes of the file
     * @param tree         name of file where decision tree model is saved
     * @return             The predicted output
     */
    public static String prediction (String[] input, Object tree) {
        //Creating arraylist of nodes
        ArrayList<Node> trees = (ArrayList<Node>) tree;
        int i = 0;

        while (true) {
            //Retrieving attribute
            int attribute = trees.get(i).attribute;
            //If its -1 then the predicted answer is the value of that node
            if (attribute == -1)
                return trees.get(i).value;
            /*
            Else if the attribute is not -1 then check if the value of that node is
            same as that of value in input array. if yes, increment i by 1.
             */
            else if (input[attribute].equals(trees.get(i).value)) {
                i = i + 1;
            }
            /*
            Else find the attribute in the arraylist with other value and initialize
            i as j+1
             */
            else{
                for(int j = i+1; j < trees.size(); j++ ){
                    if(attribute == trees.get(j).attribute)
                        if(input[attribute].equals(trees.get(j).value))
                            i = j+1;
                }
            }
        }
    }
}
