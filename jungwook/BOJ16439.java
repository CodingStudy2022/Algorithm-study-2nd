package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16439 {
    static int N, M, ans;
    static int[][] preferences;
    static int[] selected;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        preferences = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                preferences[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        selected = new int[4];
        ans = 0;
    }

    static void pro() {
        dfs(1);
        System.out.println(ans);
    }

    // M개의 치킨 중에 3개를 순서없이, 중복없이 뽑는 경우의 수(조합)
    static void dfs(int k) {
        if (k == 4) {
            // 치킨 3종류를 다 뽑은 경우
            int score = 0;
            for (int i = 0; i < N; i++) {
                int max = 0;
                for (int j = 1; j < 4; j++) {
                    int idx = selected[j];
                    max = Math.max(max, preferences[i][idx-1]);
                }
                score += max;
            }

            ans = Math.max(ans, score);
        } else {
            for (int cand = selected[k - 1] + 1; cand <= M; cand++) {
                selected[k] = cand;
                dfs(k + 1);
                selected[k] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
