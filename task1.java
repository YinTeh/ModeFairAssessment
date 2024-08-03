import java.util.*;

public class task1 {

    private static Map<Character, ArrayList<Character>> link = new HashMap<>();
    private static Map<String, Character> skip = new HashMap<>();

    static {
        link.put('A', new ArrayList<>(Arrays.asList('C', 'G', 'I')));
        link.put('B', new ArrayList<>(Arrays.asList('H')));
        link.put('C', new ArrayList<>(Arrays.asList('A', 'G', 'I')));
        link.put('D', new ArrayList<>(Arrays.asList('F')));
        link.put('E', new ArrayList<>(Arrays.asList()));
        link.put('F', new ArrayList<>(Arrays.asList('D')));
        link.put('G', new ArrayList<>(Arrays.asList('A', 'C', 'I')));
        link.put('H', new ArrayList<>(Arrays.asList('B')));
        link.put('I', new ArrayList<>(Arrays.asList('A', 'G', 'C')));
    }

    static {
        skip.put("AC", 'B'); skip.put("CA", 'B');
        skip.put("DF", 'E'); skip.put("FD", 'E');
        skip.put("BH", 'E'); skip.put("HB", 'E');
        skip.put("AI", 'E'); skip.put("IA", 'E');
        skip.put("CG", 'E'); skip.put("GC", 'E');
        skip.put("GI", 'H'); skip.put("IG", 'H');
        skip.put("AG", 'D'); skip.put("GA", 'D');
        skip.put("CI", 'F'); skip.put("IC", 'F');
    }

    public static void main(String[] args) {
        // Get and print the combinations
        List<String> combinations = listPatterns('A', 'I', 'C', 7);
        System.out.println("Total combinations: " + combinations.size());
        for (String combination : combinations) {
            System.out.println(combination);
        }
    }
    // return a list of patterns that fulfilled the requirements
    public static List<String> listPatterns(char first_letter, char second_letter, char third_letter, int num_char) {
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        List<String> combinations = new ArrayList<>();
        generate_combinations(letters, ""+first_letter, num_char - 1, combinations, new HashMap<>(link), second_letter, third_letter);
        return combinations;
    }

    private static void generate_combinations(char[] letters, String current, int remaining, List<String> combinations,
     Map<Character, ArrayList<Character>> linking, char second_letter, char third_letter) {
        if (remaining == 1) {
            if (current.contains(""+second_letter) && current.indexOf(second_letter) > 0 && current.indexOf(second_letter) < current.length()) {
                combinations.add(current +third_letter);
            }
            return;
        }

        for (char letter : letters) {
            char prev = current.charAt(current.length() - 1);
            if (current.indexOf(letter) == -1 && letter != 'A' && letter != 'C' && (!linking.get(prev).contains(letter) || 
            (current.indexOf(skip.get("" + prev + letter)) != -1 && skip.containsKey("" + prev + letter)))) {
                generate_combinations(letters, current + letter, remaining - 1, combinations, new HashMap<>(linking), second_letter, third_letter);
            }
        }
    }
}
