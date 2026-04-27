import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'highestValuePalindrome' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER n
     *  3. INTEGER k
     */

    public static String highestValuePalindrome(String s, int n, int k) {
    char[] chars = s.toCharArray();
    int[] diff = new int[n / 2];
    int changes = 0;
    for (int i = 0; i < n / 2; i++) {
        if (chars[i] != chars[n - 1 - i]) {
            changes++;
            diff[i] = 1; 
        }
    }
    if (changes > k) return "-1";
    int newk = k - changes;
    for (int i = 0; i < n / 2; i++) {
        int left = i;
        int right = n - 1 - i;

        if (chars[left] == chars[right]) {
            if (newk >= 2 && chars[left] != '9') {
                chars[left] = chars[right] = '9';
                newk -= 2;
            }
        } else {
            if (newk >= 1 && chars[left] != '9' && chars[right] != '9') {
                chars[left] = chars[right] = '9';
                newk--; 
            } else {
                char max = (char) Math.max(chars[left], chars[right]);
                chars[left] = chars[right] = max;
            }
        }
    }
    if (n % 2 == 1 && newk > 0) {
        chars[n / 2] = '9';
    }

    return new String(chars);
 }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String s = bufferedReader.readLine();

        String result = Result.highestValuePalindrome(s, n, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
