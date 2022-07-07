package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {
    static class Node {
        int x, y, dist, cnt;

        public Node(int x, int y, int dist, int cnt) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.cnt = cnt;
        }
    }

    static int N, M;
    static String[] a;
    static boolean[][][] visit;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        a = new String[N];
        for (int i = 0; i < N; i++) {
            a[i] = br.readLine();
        }
        visit = new boolean[N][M][2];
    }

    static void pro() {
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 1, 0));
        visit[0][0][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.x == N - 1 && cur.y == M - 1) {
                return cur.dist;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dir[d][0];
                int ny = cur.y + dir[d][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visit[nx][ny][cur.cnt]) continue;

                // 벽이 아닌 경우
                if (a[nx].charAt(ny) == '0') {
                    q.add(new Node(nx, ny, cur.dist + 1, cur.cnt));
                    visit[nx][ny][cur.cnt] = true;
                } else { // 벽인 경우
                    if (cur.cnt == 0) { // 벽을 한 번도 부시지 않은 경우
                        q.add(new Node(nx, ny, cur.dist + 1, cur.cnt + 1));
                        visit[nx][ny][cur.cnt + 1] = true;
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
