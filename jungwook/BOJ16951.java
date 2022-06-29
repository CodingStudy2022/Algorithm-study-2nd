package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16951 {
    static int N, K;
    static int[] A;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int[] temp = new int[N + 1];
            temp[i] = A[i];
            boolean flag = false;

            // idx : 1 ~ i-1
            for (int j = i - 1; j >= 1; j--) {
                temp[j] = temp[j + 1] - K;
                if (temp[j] <= 0) {
                    flag = true;
                    break;
                }
            }
            if(flag) continue;

            // idx : i+1 ~ N
            for (int j = i + 1; j <= N; j++) {
                temp[j] = temp[j - 1] + K;
            }

            // 기존 배열과 비교하여 개수 세기
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                if(temp[j] != A[j]) cnt++;
            }
            ans = Math.min(cnt, ans);
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
