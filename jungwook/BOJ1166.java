package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://verycrazy.tistory.com/116
 */
public class BOJ1166 {
    static int N, L, W, H;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
    }

    static void pro() {
        double left = 0;
        double right = Math.min(L, Math.min(W, H));

        for (int i = 0; i < 5000; i++) {
            double mid = (left + right) / 2;

            if ((long) (L / mid) * (long) (W / mid) * (long) (H / mid) < N) {
                right = mid;
            } else {
                left = mid;
            }
        }

        System.out.println(left);
    }

    static boolean determine(double x) {
        return (long)(L / x) * (long)(W / x) * (long)(H / x) >= N;
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
