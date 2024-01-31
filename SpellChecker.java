public class SpellChecker {

   
    public static void main(String[] args) {
        
        String word = args[0];
        int threshold = Integer.parseInt(args[1]);
        String[] dictionary = readDictionary("dictionary.txt");
        // Get the corrected word using spellChecker function
        String correction = spellChecker(word, threshold, dictionary);
        // Print the corrected word
        System.out.println(correction);
    }

    // Function to get the tail of a string
    public static String tail(String str) {
        String tailStr = "";
        for(int i = 0; i < str.length()-1; i++) {
            // Checks if the string has more than one character
            if (str.length() > 1) {
                tailStr += str.charAt(i+1);
            }
        }
        return tailStr;
    }

    // Recursive function to calculate the Levenshtein distance between two words
    public static int levenshtein(String word1, String word2) {
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();
        if(word1.length() == 0) {
            return word2.length();
        } else if (word2.length() == 0) {
            return word1.length();
        } else if (word1.charAt(0) == word2.charAt(0)) {
            return levenshtein(tail(word1), tail(word2));
        } else {
            return 1 + Math.min(Math.min(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2))),
                levenshtein(tail(word1), tail(word2)));
        }
    }

   
    public static String[] readDictionary(String fileName) {
        String[] dictionary = new String[3000];
        In in = new In(fileName);
        int i = 0;
        while (in.hasNextLine() && i < 3000) {
            dictionary[i] = in.readLine();
            i++;
        }
        return dictionary;
    }

    
    public static String spellChecker(String word, int threshold, String[] dictionary) {
        // Convert the word to lowercase for case-insensitive comparison
        word = word.toLowerCase();
        String editedWord = word;
        // Set an initial distance value greater than the threshold(doesnt matter how much bigger)
        int lowestDistance = threshold + 2;
        // Iterate through the dictionary to find the closest word
        for(int i = 0; i < dictionary.length; i++ ){
            int distance = levenshtein(word, dictionary[i]);
            // Update the edited word if a closer match is found("memorization")
            if (distance < lowestDistance) {
                lowestDistance = distance;
                editedWord = dictionary[i]; 
            }
        }
        return editedWord;
    }
}
