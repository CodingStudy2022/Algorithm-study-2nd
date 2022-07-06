package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1244 {
    static int n, m;
    static int[] checker;
    static int[][] students;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        checker = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            checker[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        students = new int[m + 1][2];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            students[i][0] = x;
            students[i][1] = y;
        }
    }

    static void solve() {
        for (int i = 1; i <= m; i++) {
            int gender = students[i][0];
            int num = students[i][1];

            if (gender == 1) {
                for (int j = 1; j <= n; j++) {
                    if (j % num == 0) {
                        checker[j] = checker[j] == 1 ? 0 : 1;
                    }
                }
            } else {
                checker[num] = checker[num] == 1 ? 0 : 1;

                int idx = 1;
                while (true) {
                    int l = num - idx;
                    int r = num + idx;

                    if(l < 1 || r > n) break;
                    if(checker[l] != checker[r]) break;

                    checker[l] = checker[l] == 1 ? 0 : 1;
                    checker[r] = checker[r] == 1 ? 0 : 1;
                    idx++;
                }
            }
        }

        // 출력
        for (int i = 1; i <= n; i++) {
            sb.append(checker[i]).append(' ');
            if(i % 20 == 0) sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
