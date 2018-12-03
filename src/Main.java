import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
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
        for (Grammar element : grammars) {
            System.out.println(element);
        }
    }
}
