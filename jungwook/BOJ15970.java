package jungwook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ15970 {
    static int N;
    static HashMap<Integer, ArrayList<Integer>> infos;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        infos = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (!infos.containsKey(y)) {
                infos.put(y, new ArrayList<>());
            }
            infos.get(y).add(x);
        }
    }

    static void pro() {
        for (int color : infos.keySet()) {
            Collections.sort(infos.get(color));
        }

        int ans = 0;
        for (int color : infos.keySet()) {
            for (int i = 0; i < infos.get(color).size(); i++) {
                if (i == 0) {
                    ans += infos.get(color).get(i + 1) - infos.get(color).get(i);
                } else if (i == infos.get(color).size() - 1) {
                    ans += infos.get(color).get(i) - infos.get(color).get(i - 1);
                } else {
                    int temp1 = infos.get(color).get(i + 1) - infos.get(color).get(i);
                    int temp2 = infos.get(color).get(i) - infos.get(color).get(i - 1);
                    ans += Math.min(temp1, temp2);
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
