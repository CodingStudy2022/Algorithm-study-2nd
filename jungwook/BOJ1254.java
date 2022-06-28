package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1254 {
    static String str;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        str = br.readLine();
    }

    static void pro() {
        int ans = str.length();
        for (int i = 0; i < str.length(); i++) {
            if (isPalindrome(str.substring(i))) {
                break;
            }
            ans++;
        }
        System.out.println(ans);
    }

    static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
