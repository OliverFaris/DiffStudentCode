/**
 * Plagiarism Checker
 * A tool for finding the longest shared substring between two documents.
 *
 * @author Zach Blick
 * @author Oliver Faris
 */
public class PlagiarismChecker {

    /**
     * This method finds the longest sequence of characters that appear in both texts in the same order,
     * although not necessarily contiguously.
     * @param doc1 the first document
     * @param doc2 the second
     * @return The length of the longest shared substring.
     */

    // TABULATION SOLUTION
    /*
    public static int longestSharedSubstring(String doc1, String doc2) {
        int len1 = doc1.length();
        int len2 = doc2.length();
        int [][] tab = new int[len1 +1][len2 +1];

        for (int i = 1; i < len1+1; i++) {
            for (int j = 1; j < len2+1; j++) {
                // If the letters are the same go diagonal
                if (doc1.charAt(i-1) == doc2.charAt(j-1))
                    tab[i][j] = 1 + tab[i-1][j-1];
                // If the letters aren't the same, take the max of the two options
                else
                    tab[i][j] = Math.max(tab[i][j-1], tab[i-1][j]);
            }
        }

        return tab[len1][len2];
    }
    */

    // RECURSIVE SOLUTION
    public static int longestSharedSubstring(String doc1, String doc2) {
        int[][] memo = new int[doc1.length()][doc2.length()];
        return findLSS(doc1, doc2, doc1.length()-1, doc2.length()-1, memo);
    }

    public static int findLSS(String doc1, String doc2, int textLen1, int textLen2, int[][] memo) {
        // Base case
        if (textLen1 < 0 || textLen2 < 0)
            return 0;

        // If not memoized already
        if (memo[textLen1][textLen2] == 0) {
            // When letters are the same, remove the char from both texts and add 1 to the longest shared substring between the two texts
            if (doc1.charAt(textLen1) == doc2.charAt(textLen2))
                memo[textLen1][textLen2] = 1 + findLSS(doc1, doc2, textLen1 - 1, textLen2 - 1, memo);
            // When chars aren't the same get the maximum longest shared substring whether it's removing a char from one or the other text
            else
                memo[textLen1][textLen2] = Math.max(findLSS(doc1, doc2, textLen1, textLen2 - 1, memo), findLSS(doc1, doc2, textLen1 - 1, textLen2, memo));
        }

        return memo[textLen1][textLen2];
    }
}
