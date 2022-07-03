package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ16938 {
    static int N, L, R, X, ans;
    static int[] A, selected;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        ans = 0;
    }

    static void pro() {
        Arrays.sort(A, 1, N + 1);

        // 2개 ~ N개 뽑기
        for (int i = 2; i <= N; i++) {
            selected = new int[i + 1];
            recFunc(1, i);
        }

        System.out.println(ans);
    }

    static void recFunc(int k, int total) {
        if (k == total + 1) {
            // 뽑힌 문제들이 조건을 만족하는지 확인
            if (isAvailable()) {
                ans++;
            }
        } else {
            for (int cand = selected[k - 1] + 1; cand <= N; cand++) {
                selected[k] = cand;
                recFunc(k + 1, total);
                selected[k] = 0;
            }
        }
    }

    static boolean isAvailable() {
        int[] temp = new int[selected.length - 1];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = A[selected[i + 1]];
        }

        // L <= sum <= R
        int sum = Arrays.stream(temp).sum();
        if(sum < L || sum > R) return false;

        // max - min >= X
        int max = Arrays.stream(temp).max().getAsInt();
        int min = Arrays.stream(temp).min().getAsInt();
        if(max - min < X) return false;

        return true;
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
