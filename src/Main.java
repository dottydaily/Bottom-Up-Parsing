import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static String printInputStack(Stack<Character> inputStack) {
        Object[] inputArray = new Object[inputStack.size()];
        inputStack.copyInto(inputArray);

        String result = "";
        for (int i = inputArray.length-1; i >= 0 ; i--) {
            result += ((Character)inputArray[i]).toString();
        }

        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Stack<Grammar> grammars = new Stack<>();

        System.out.println("Bottom-up parsing : Example program");
        System.out.print(">| Enter a total number of your grammars : ");
        int grammarNumber = Integer.parseInt(reader.readLine());

        System.out.println("\n>| Enter grammar");
        System.out.println("   >| Example : Full grammar is A → ( A ) A | H");
        System.out.println("   >| then left operand = A");
        System.out.println("   >| and right operand = (A)A|H");
        System.out.println("   >| NOTE : If you want to input a \"Empty String\", please enter this character : ε\n");

        for (int i = 0 ; i < grammarNumber ; i++) {
            System.out.println(">| Grammar " + (i+1));
            System.out.print("   >| Left operand : ");
            String left = reader.readLine();
            System.out.print("   >| Right operand : ");
            String right = reader.readLine();
            grammars.push(new Grammar(left, right));
        }

        // print every grammar input into this program
        System.out.println("\n//////////Grammar list//////////");
        for (Grammar element : grammars) {
            System.out.println(element);
        }
        System.out.println("////////////////////////////////\n");

        System.out.print(">| Enter your input string : ");
        char[] inputs = reader.readLine().toCharArray();

        Stack<Character> inputStack = new Stack<>();
        for (int i = inputs.length-1 ; i >= 0 ; i--) {
            inputStack.push(inputs[i]);
        }

        // reduce parsingString by checking with grammars
        System.out.println("\n|   Parsing stack   |    Input stack    |        Action        |");
        String parsingString = "";
        boolean isGrammarChanged = false;
        while (!grammars.empty()) {
            Grammar currentGrammar = grammars.pop();
            // reset parsingString to empty string for using in the loop
            parsingString = "";

            while (!inputStack.empty()) {
                // if change to new grammar, skip print the line that move result from previous grammar into new parsing
                if (!isGrammarChanged) {
                    isGrammarChanged = false;
                    System.out.printf("| $ %-15s | %15s $ | %-20s |\n", parsingString, printInputStack(inputStack), "Shift");
                }
                parsingString += inputStack.pop();

                parsingString = currentGrammar.reduce(parsingString, inputStack);
            }

            // if we have replace empty string with leftOperand
            // we need to redo the reduce action (because it skip a number of reduce time)
            // there are some case that inputStack will be empty before complete reduce
            parsingString = currentGrammar.reduce(parsingString, inputStack);

            // put result of parsing stack into inputStack for checking with next grammar
            if (!grammars.isEmpty()) {
                char[] nextInput = parsingString.toCharArray();
                for (int i = nextInput.length-1 ; i >= 0 ; i--) {
                    inputStack.push(nextInput[i]);
                }
            }

            // changing grammar
            isGrammarChanged = true;
        }

        System.out.printf("| $ %-15s | %15s $ | %-20s |\n", parsingString, printInputStack(inputStack), "Accept");

    }
}