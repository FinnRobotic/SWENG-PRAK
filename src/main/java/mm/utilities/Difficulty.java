package mm.utilities;

public enum Difficulty {
    EASY,
    MEDIUM,
    HARD,
    CUSTOM;

    @Override
    public String toString() {
        switch (this) {
            case EASY: return "Easy";
            case MEDIUM: return "Medium";
            case HARD: return "Hard";
            case CUSTOM: return "Custom";
            default: return super.toString();
        }
    }
}