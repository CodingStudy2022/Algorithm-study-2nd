package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2448 {
    static int N;
    static char[][] map;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new char[N][2 * N - 1];
    }

    static void pro() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                map[i][j] = ' ';
            }
        }

        draw(0, N - 1, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    static void draw(int x, int y, int n) {
        if (n == 3) {
            // n = 3 -> 가장 작은 삼각형일 때
            // 첫 번째 줄
            map[x][y] = '*';
            // 두 번째 줄
            map[x + 1][y - 1] = map[x + 1][y + 1] = '*';
            // 세 번째 줄
            map[x + 2][y - 2] = map[x + 2][y - 1] = map[x + 2][y] = map[x + 2][y + 1] = map[x + 2][y + 2] = '*';
        } else {
            // 가장 큰 삼각형을 기준으로 가운데 가로선으로 절반을 쪼갤 때
            // 상단 삼각형
            draw(x, y, n / 2);
            // 좌하단 삼각형
            draw(x + n / 2, y - n / 2, n / 2);
            // 우하단 삼각형
            draw(x + n / 2, y + n / 2, n / 2);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
