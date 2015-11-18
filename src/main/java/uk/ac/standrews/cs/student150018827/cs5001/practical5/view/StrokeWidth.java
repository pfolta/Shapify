package uk.ac.standrews.cs.student150018827.cs5001.practical5.view;

public enum StrokeWidth {
    NONE(0),
    THIN(1),
    MEDIUM(3),
    THICK(5),
    EXTRA_THICK(8);

    private final int strokeWidth;

    StrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public static StrokeWidth fromInteger(int strokeWidth) {
        switch (strokeWidth) {
            case 0: {
                return NONE;
            }
            case 1: {
                return THIN;
            }
            case 3: {
                return MEDIUM;
            }
            case 5: {
                return THICK;
            }
            case 8: {
                return EXTRA_THICK;
            }
            default: {
                throw new IllegalArgumentException("Corresponding Stroke Width does not exist.");
            }
        }
    }

}