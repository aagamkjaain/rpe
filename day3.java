public class day3 {
    public static void main(String[] args) throws Exception {
        java.nio.file.Path path = java.nio.file.Paths.get("input.txt");
        long total = 0L;

        try (java.io.BufferedReader br = java.nio.file.Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                int best = 0;
                char[] digits = line.toCharArray();
                for (int i = 0; i < digits.length; i++) {
                    for (int j = i + 1; j < digits.length; j++) {
                        int val = (digits[i] - '0') * 10 + (digits[j] - '0');
                        if (val > best) best = val;
                    }
                }
                total += best;
            }
        }

        System.out.println(total);
    }
}
// ...existing code...