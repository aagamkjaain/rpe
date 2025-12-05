import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class day1 {
    public static void main(String[] args) throws IOException {
        String raw = new String(Files.readAllBytes(Paths.get("input.txt"))).trim();
        if (raw.isEmpty()) {
            System.out.println(0);
            return;
        }

        String[] ranges = raw.split(",");
        long maxUpper = 0;
        long[][] pairs = new long[ranges.length][2];

        for (int i = 0; i < ranges.length; i++) {
            String r = ranges[i].trim();
            String[] p = r.split("-");
            long lo = Long.parseLong(p[0].trim());
            long hi = Long.parseLong(p[1].trim());
            pairs[i][0] = lo;
            pairs[i][1] = hi;
            if (hi > maxUpper) maxUpper = hi;
        }

        int maxLen = Long.toString(maxUpper).length();
        int maxBaseLen = maxLen / 2; 

        long[] pow10 = new long[maxBaseLen + 2];
        pow10[0] = 1L;
        for (int i = 1; i < pow10.length; i++) pow10[i] = pow10[i - 1] * 10L;

        List<Long> doubles = new ArrayList<>();
        for (int baseLen = 1; baseLen <= maxBaseLen; baseLen++) {
            long start = pow10[baseLen - 1]; 
            long end = pow10[baseLen] - 1;
            for (long base = start; base <= end; base++) {
                long d = base * pow10[baseLen] + base; 
                if (d > maxUpper) break;
                doubles.add(d);
            }
        }

        Collections.sort(doubles);

        long total = 0L;
        for (long[] pr : pairs) {
            long lo = pr[0], hi = pr[1];
            for (long d : doubles) {
                if (d < lo) continue;
                if (d > hi) break;
                total += d;
            }
        }

        System.out.println(total);
    }
}
