
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		String tailStr = "";
		for(int i = 0; i < str.length(); i++){
		if (str.length() > 1) {
			tailStr += str.charAt(i+1);
			}
		}
		return tailStr;
}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if(word1.length() == 0){
			return word2.length();
		}
		else if (word2.length() == 0) {
			return word1.length();
		}
		else if (word1.charAt(0)==word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));
		}
		else{
			return 1 + Math.min(Math.min(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2))),
			 levenshtein(tail(word1), tail(word2)));
		}
		}
		
			
	

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		while (fileName.isEmpty()) {
			
		
		for(int i =0; i < fileName.length(); i++){
			dictionary[i] = in.readLine();
		}

	}
	return dictionary;
	}


	public static String spellChecker(String word, int threshold, String[] dictionary) {
		word = word.toLowerCase();
		for(int i = 0; i < dictionary.length; i++ ){
			if (threshold > levenshtein(word, dictionary[i])) {
				return word;
			}
			else{

			}
	}

}
