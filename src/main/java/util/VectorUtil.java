package util;

public class VectorUtil {

    public static String toVector(float[] embedding) {

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < embedding.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(embedding[i]);
        }
        sb.append("]");

        return sb.toString();
    }
}
