package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1503 {
    static int N, M;
    static boolean[] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new boolean[1002];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr[Integer.parseInt(st.nextToken())] = true;
        }
    }

    static void pro() {
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= 1000; i++) {
            if(arr[i]) continue;
            for (int j = i; j <= 1000; j++) {
                if(arr[j]) continue;
                for (int k = j; k <= 1001; k++) {
                    if(arr[k]) continue;
                    ans = Math.min(ans, Math.abs(N - (i * j * k)));
                }
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
