package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13413 {
    static int T, N;
    static String start, end;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        start = br.readLine();
        end = br.readLine();
    }

    static void pro() {
        int wCount = 0, bCount = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) != end.charAt(i)) {
                if(start.charAt(i) == 'W') wCount++;
                else bCount++;
            }
        }

        int ans = (Math.min(wCount, bCount) + Math.abs(wCount - bCount));
        sb.append(ans).append('\n');
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            input();
            pro();
        }
        System.out.println(sb);
    }
}
