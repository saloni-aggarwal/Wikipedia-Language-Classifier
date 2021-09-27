In this project, I have investigated the use of decision tree and adaboost
i.e. boosted decision stumps to classify text as one of two languages -
English or Dutch. For this we have collected data and train decision
tree/stumps using the two approaches respectively.

First, for training the model the data has been collected. The data has
then been categorized depending on the features. The features for
classifying dutch and english language are as follows:
- 'de' : It is a dutch word which means 'the' in english. This word is only
used in dutch language and not in english.
- 'a' and 'an' : These are two articles used in english language and are
not used in dutch language.
- 'een', 'en' and 'aan' : een means 'a', en means 'and' and aan means
'on' in dutch language and these words are not used in english.
- 'the' : it is an article very frequently used in english language but not
in dutch.
- average word length : average word length in dutch language is
greater than the english language. So if the average length is greater
than 5 then it is assumed to be true for classifying dutch.
- double letters : In dutch language the letters are frequently repeated
twice together in a word. So the occurrence of letters twice is kept track
of and if any occurences of letter is greater than 3 then it is assumed to
be true for classifying dutch.

For training the dataset, each input line is classified as 'true' or 'false' for
each feature.
- **Training using decision tree** - In this algorithm, a decision tree is trained
using the concept of information gain and entropy. So for this, at each
level one attribute is selected to classify the decision tree. The attribute
that is to be selected should have maximum information gain then
other attributes. This is done by :

                    IG = (Parent’s entropy - Child nodes’ entropy)
                    where the entropy can be calculated as:
                    E(S) = Summation(i=1 to c) −pi· log2 · pi


Once this attribute is selected then the list containing example set is
divided into left and right child according to the 'true' or 'false'. After
this the selected attribute is removed from the list of attributes
remained to select. This function is executed recursively until :

   i) If there are no examples left to classify and hence plurality value
(explained below) of parent example is the predicted answer.
  ii) If all the examples have same label i .e. either 'English' or 'Dutch'. This
is done using classification function (explained below).
   iii) There are no attributes left to select. Hence the plurality value of the
examples is returned.

Once the examples are divided into the left and right child, a node is
created for both the child and saved in an arraylist.

The functions used to implement decision tree are :
    i) pluralityValue() : In this function number of times english and dutch
labels occured is calculated. If the occurence of dutch is greater than
english then 'nl' is predicted. If occurence of english is more than dutch
or if english and dutch occur same number of times then 'en' is
predicted.
    ii) classification() :: In this function number of times english and dutch
labels occured is calculated. If english is not occured single time then 'nl'
is predicted. Or if dutch is not occured single time then 'en' is predicted.

After training the model the trained model is stored in a file.

- **Prediction using decision tree** - For this the user provides a file with
input lines that is to be predicted if its dutch or not. Each line is first
checked and is classified as 'true' or 'false' for each feature. The
assigned value is then passed through the trained decision tree. First,
the attribute that has been selected at level 1 by trained decision tree is
checked. Depending on the value to be true or false, the child node is
then visited. This process is continued till the attribute in decision tree is
stored as -1. The value stored along with this attribute is the predicted
value that will be either 'en' or 'nl'.

- **Training using AdaBoost (decision stump)** - In this algorithm, several
one - level decision tree is made that are called as stumps. Initially, each
data is given a sample weight which is assigned as 1/(number of
samples). Each decision stump made has some errors i.e. labels that
have been wrongly classified. From both the left and right child of these
decision stump the total error is calculated.

                    if Label[j] 6= P[j], for all j = 1, 2 . . . N:
                        error = error + wj
             
Based on this error an amount of say for a stump is calculated. It can be
done by using below formula:

                        α =1/2· log(1−error/error)
                      
Lesser the errors, higher the amount of say is. Higher the amount of say,
more accurate the decision stump is. The decision stump with high
amount of say has more accurate predictions or has more say in the
whole algorithm then other stumps. After calculating the amount of
say, the sample weights of the examples with errors is increased and
weight of correctly classified examples is decreased. This is clled
normalization.

                      if it is an error:
                      wj = wj· e^α
                      else:
                      wj = wj· e^(−α)
                      
When the sample weights are updated, then the weights are
normalized so that its value lies between 0 and 1. For normalizing the
weights:

                      wj = wj/Summation (k=1 to N) wk
                  
Now, this stump along with its amount of say and predicted values
(which is calculated using pluralityValue() function) is saved in a node
and all the nodes are saved in an arraylist. After the first stump is
created, the example set is updated with new values. These new values
are randomly selected and the one with the error is expected to be
selected more often to reduce the error rate. In this particular
implementation, I have created 6 stumps that is equal to the number of
features or attributes. All the stumps along with its amount of say and
prediction is saved in a file.

-**Prediction using AdaBoost** - I have assigned values to english and dutch
language as 1 and -1 respectively. So for each input line, sum of
(amount of say * prediction [i.e. 1 or -1 depending on the prediction]) is
calculated. If the sum is more closer to 1 then 'en' is predicted or if the
sum if closer to -1 then 'nl' is predicted. For each input all the stumps
created is visited and total value is calculated using the above formula.

                            yi = αi· stumps
                            if yi < 0 P return -1
                            if yi ≥ 0 P return 1
                            
-**Accuracy** - The accuracy of both the models implemented i.e. decision
tree and adaboost or decision stump is 90 - 95%.

-**Testing Input** - For testing the algorithm, the input is provided in the
same format as specified in the lab. My code fails to predict the
hypothesis saved in file when learning type is adaboost. However, if the
trained adaboost is saved in "hypothesis1.txt" and same filename is
given as input in place of hypothesis in the command line arguments
while predicting then it will predict the right output.
