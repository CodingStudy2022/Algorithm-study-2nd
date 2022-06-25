package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16162 {
    static int N, A, D;
    static int[] notes;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        notes = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            notes[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        int tier = 0;
        for (int i = 1; i <= N; i++) {
            if(notes[i] != A) continue;
            tier++;
            A += D;
        }
        System.out.println(tier);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
