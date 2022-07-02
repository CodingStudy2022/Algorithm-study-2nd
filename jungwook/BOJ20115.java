package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ20115 {
    static int N;
    static double[] drinks;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        drinks = new double[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            drinks[i] = Double.parseDouble(st.nextToken());
        }
    }

    static void pro() {
        Arrays.sort(drinks);
        double ans = drinks[N];
        for (int i = N-1; i > 0; i--) {
            ans += drinks[i] / 2;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
