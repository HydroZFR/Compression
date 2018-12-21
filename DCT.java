package compression;

final class Dct {

    private static final float[] DCT_SCALING_FACTORS = {
            (float) (0.5 / Math.sqrt(2.0)),
            (float) (0.25 / Math.cos(Math.PI / 16.0)),
            (float) (0.25 / Math.cos(2.0 * Math.PI / 16.0)),
            (float) (0.25 / Math.cos(3.0 * Math.PI / 16.0)),
            (float) (0.25 / Math.cos(4.0 * Math.PI / 16.0)),
            (float) (0.25 / Math.cos(5.0 * Math.PI / 16.0)),
            (float) (0.25 / Math.cos(6.0 * Math.PI / 16.0)),
            (float) (0.25 / Math.cos(7.0 * Math.PI / 16.0)), };

    private static final float[] IDCT_SCALING_FACTORS = {
            (float) (2.0 * 4.0 / Math.sqrt(2.0) * 0.0625),
            (float) (4.0 * Math.cos(Math.PI / 16.0) * 0.125),
            (float) (4.0 * Math.cos(2.0 * Math.PI / 16.0) * 0.125),
            (float) (4.0 * Math.cos(3.0 * Math.PI / 16.0) * 0.125),
            (float) (4.0 * Math.cos(4.0 * Math.PI / 16.0) * 0.125),
            (float) (4.0 * Math.cos(5.0 * Math.PI / 16.0) * 0.125),
            (float) (4.0 * Math.cos(6.0 * Math.PI / 16.0) * 0.125),
            (float) (4.0 * Math.cos(7.0 * Math.PI / 16.0) * 0.125), };

    private static final float A1 = (float) (Math.cos(2.0 * Math.PI / 8.0));
    private static final float A2 = (float) (Math.cos(Math.PI / 8.0) - Math.cos(3.0 * Math.PI / 8.0));
    private static final float A3 = A1;
    private static final float A4 = (float) (Math.cos(Math.PI / 8.0) + Math.cos(3.0 * Math.PI / 8.0));
    private static final float A5 = (float) (Math.cos(3.0 * Math.PI / 8.0));

    private static final float C2 = (float) (2.0 * Math.cos(Math.PI / 8));
    private static final float C4 = (float) (2.0 * Math.cos(2 * Math.PI / 8));
    private static final float C6 = (float) (2.0 * Math.cos(3 * Math.PI / 8));
    private static final float Q = C2 - C6;
    private static final float R = C2 + C6;

    private Dct() {
    }

    public static void scaleQuantizationMatrix(final float[] matrix) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                matrix[8 * y + x] *= DCT_SCALING_FACTORS[y]
                        * DCT_SCALING_FACTORS[x];
            }
        }
    }

    public static void forwardDCT8x8(final float[] matrix) {
        float a00, a10, a20, a30, a40, a50, a60, a70;
        float a01, a11, a21, a31, neg_a41, a51, a61;
        float a22, a23, mul5, a43, a53, a63;
        float a54, a74;

        for (int i = 0; i < 8; i++) {
            a00 = matrix[8 * i] + matrix[8 * i + 7];
            a10 = matrix[8 * i + 1] + matrix[8 * i + 6];
            a20 = matrix[8 * i + 2] + matrix[8 * i + 5];
            a30 = matrix[8 * i + 3] + matrix[8 * i + 4];
            a40 = matrix[8 * i + 3] - matrix[8 * i + 4];
            a50 = matrix[8 * i + 2] - matrix[8 * i + 5];
            a60 = matrix[8 * i + 1] - matrix[8 * i + 6];
            a70 = matrix[8 * i] - matrix[8 * i + 7];
            a01 = a00 + a30;
            a11 = a10 + a20;
            a21 = a10 - a20;
            a31 = a00 - a30;
            neg_a41 = a40 + a50;
            a51 = a50 + a60;
            a61 = a60 + a70;
            a22 = a21 + a31;
            a23 = a22 * A1;
            mul5 = (a61 - neg_a41) * A5;
            a43 = neg_a41 * A2 - mul5;
            a53 = a51 * A3;
            a63 = a61 * A4 - mul5;
            a54 = a70 + a53;
            a74 = a70 - a53;
            matrix[8 * i] = a01 + a11;
            matrix[8 * i + 4] = a01 - a11;
            matrix[8 * i + 2] = a31 + a23;
            matrix[8 * i + 6] = a31 - a23;
            matrix[8 * i + 5] = a74 + a43;
            matrix[8 * i + 1] = a54 + a63;
            matrix[8 * i + 7] = a54 - a63;
            matrix[8 * i + 3] = a74 - a43;
        }

        for (int i = 0; i < 8; i++) {
            a00 = matrix[i] + matrix[56 + i];
            a10 = matrix[8 + i] + matrix[48 + i];
            a20 = matrix[16 + i] + matrix[40 + i];
            a30 = matrix[24 + i] + matrix[32 + i];
            a40 = matrix[24 + i] - matrix[32 + i];
            a50 = matrix[16 + i] - matrix[40 + i];
            a60 = matrix[8 + i] - matrix[48 + i];
            a70 = matrix[i] - matrix[56 + i];
            a01 = a00 + a30;
            a11 = a10 + a20;
            a21 = a10 - a20;
            a31 = a00 - a30;
            neg_a41 = a40 + a50;
            a51 = a50 + a60;
            a61 = a60 + a70;
            a22 = a21 + a31;
            a23 = a22 * A1;
            mul5 = (a61 - neg_a41) * A5;
            a43 = neg_a41 * A2 - mul5;
            a53 = a51 * A3;
            a63 = a61 * A4 - mul5;
            a54 = a70 + a53;
            a74 = a70 - a53;
            matrix[i] = a01 + a11;
            matrix[32 + i] = a01 - a11;
            matrix[16 + i] = a31 + a23;
            matrix[48 + i] = a31 - a23;
            matrix[40 + i] = a74 + a43;
            matrix[8 + i] = a54 + a63;
            matrix[56 + i] = a54 - a63;
            matrix[24 + i] = a74 - a43;
        }
    }
}