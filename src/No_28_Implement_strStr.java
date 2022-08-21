/*
Description
For a given source string and a target string, you should output the first index(from 0) of target string in the source
string.If the target does not exist in source, just return -1.

Example 1:
input:
source = "source"
target = "target"

Output:
-1

Example 2:
input:
source = "abcdabcdefg"
target = "bcd"

Output:
1
 */


public class No_28_Implement_strStr {
    /**
     * @param source:
     * @param target:
     * @return: return the index
     */
    public int strStr(String source, String target) {
        char[] ch1 = source.toCharArray();
        char[] ch2 = target.toCharArray();

        for (int i = 0; i <= source.length() - target.length(); ++i) {
            int x = i;
            int y = 0;
            while(y < target.length() && ch1[x] == ch2[y]) {
                x++;
                y++;
            }
            if(y == target.length()){
                return i;
            }
        }
        return -1;
    }
}
