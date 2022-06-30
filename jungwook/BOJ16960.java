package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ16960 {
    static int N, M;
    static ArrayList<Integer>[] switches;
    static boolean[] lamps;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        switches = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            switches[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                switches[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        lamps = new boolean[M + 1];
    }

    static void pro() {
        // 해당 번호 제외
        for (int i = 1; i <= N; i++) {
            lamps = new boolean[M + 1];
            for (int j = 1; j <= N; j++) {
                if(i == j) continue;
                for (int n : switches[j]) {
                    lamps[n] = true;
                }
            }
            if (isOn()) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

    static boolean isOn() {
        for (int i = 1; i <= M; i++) {
            if(!lamps[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
