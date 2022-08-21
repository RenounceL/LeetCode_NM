/*
Description
Given a string, determine if it is a palindrome, only letters and numbers are considered and case is ignored

Example 1:
Input: "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama"

Example 2:
Input: "race a car"
Output: false
Explanation: "raceacar"

Example 3:
Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
 */

public class No_125_Valid_Palindrome {
    /**
     * @param s: A string
     * @return: Whether the string is a valid palindrome
     */
    public boolean isPalindrome(String s) {
        // write your code here
        s = s.toLowerCase();
        int i = 0;
        int j = s.length() - 1;
        while(i<j){
            char char1 = s.charAt(i);
            if(!Character.isLetterOrDigit(char1)){
                i++;
                continue;
            }
            char char2 = s.charAt(j);
            if(!Character.isLetterOrDigit(char2)){
                j--;
                continue;
            }
            if(char1 != char2){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
