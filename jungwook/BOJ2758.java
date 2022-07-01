package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2758 {
    static int N, M;
    static long[][] dp;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new long[N + 1][M + 1];
    }

    static void pro() {
        for (int i = 0; i <= M; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // dp[i][j] := i번째 수에 1 ~ j 까지의 수 중에 하나를 선택하는 경우의 수
                // -> [i번째 수로 j를 선택하는 경우의 수] + [1 ~ j-1 까지의 수 중에 하나를 선택하는 경우의 수]
                // [1 ~ j-1 까지의 수 중에 하나를 선택하는 경우의 수] := dp[i][j-1]
                // [i번째 수로 j를 선택하는 경우의 수] := dp[i-1][j/2]
                // dp[i-1][j/2]인 이유 : 이전에 고른 수보다 2배가 커야지만 선택이 가능함.
                // 바꿔말하면 이전의 수는 현재 수의 1/2보다 작아야한다.
                dp[i][j] = dp[i - 1][j / 2] + dp[i][j - 1];
            }
        }

        sb.append(dp[N][M]).append('\n');
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            input();
            pro();
        }
        System.out.println(sb.toString());
    }
}
