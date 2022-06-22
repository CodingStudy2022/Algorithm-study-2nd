package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14594 {
    static int N, M;
    static int[][] behaviors;
    static boolean[] walls;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        behaviors = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            behaviors[i][0] = x;
            behaviors[i][1] = y;
        }
        walls = new boolean[N - 1];
        for(int i=0; i<N-1; i++) walls[i] = true;
    }

    static void pro() {
        // 행동
        for (int i = 0; i < M; i++) {
            int x = behaviors[i][0];
            int y = behaviors[i][1];
            for (int j = x - 1; j < y - 1; j++) {
                walls[j] = false;
            }
        }

        // 개수 세기 및 출력
        int ans = 0;
        for (int i = 0; i < N - 1; i++) {
            if(walls[i]) ans++;
        }
        System.out.println(ans + 1);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
