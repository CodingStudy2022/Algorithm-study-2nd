package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2583 {
    static int M, N, K, cnt;
    static int[][] rects;
    static boolean[][] visit;
    static ArrayList<Integer> cons;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visit = new boolean[M][N];
        cons = new ArrayList<>();
        rects = new int[K][4];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            rects[i][0] = x1;
            rects[i][1] = y1;
            rects[i][2] = x2;
            rects[i][3] = y2;
        }
    }

    static void pro() {
        // 사각형 채우기
        for (int i = 0; i < K; i++) {
            int x1 = rects[i][0];
            int y1 = rects[i][1];
            int x2 = rects[i][2];
            int y2 = rects[i][3];

            for (int a = y1; a < y2; a++) {
                for (int b = x1; b < x2; b++) {
                    visit[a][b] = true;
                }
            }
        }

        // 색칠 안된 공간 개수 세기 및 넓이 구하기
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(visit[i][j]) continue;
                cnt = 0;
                dfs(i, j);
                cons.add(cnt);
            }
        }

        // 출력
        sb.append(cons.size()).append('\n');
        Collections.sort(cons);
        for(int i : cons) {
            sb.append(i).append(' ');
        }
        System.out.println(sb.toString());
    }

    static void dfs(int x, int y) {
        visit[x][y] = true;
        cnt++;

        for (int d = 0; d < 4; d++) {
            int nx = x + dir[d][0];
            int ny = y + dir[d][1];
            if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
            if(visit[nx][ny]) continue;
            dfs(nx, ny);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
