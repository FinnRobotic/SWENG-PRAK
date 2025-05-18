package mm.utilities;

/**
 * Enum representing the difficulty levels available in the game.
 */
public enum Difficulty {
    /** Easy difficulty level */
    EASY,

    /** Medium difficulty level */
    MEDIUM,

    /** Hard difficulty level */
    HARD,

    /** Custom difficulty level, user-defined settings */
    CUSTOM;

    /**
     * Returns a user-friendly string representation of the difficulty level.
     *
     * @return human-readable name of the difficulty
     */
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