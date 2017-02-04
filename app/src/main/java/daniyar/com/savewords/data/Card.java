package daniyar.com.savewords.data;

/**
 * Created by yernar on 31/01/17.
 */

public class Card {
    private String english;
    private String russian;
    private Integer attemps;
    private Integer correct;
    private Integer failure;

    Card() {
        attemps = correct = failure = 0;
    }

    public String getEnglish() {
        return english;
    }

    public Card setEnglish(String english) {
        this.english = english;
        return this;
    }

    public String getRussian() {
        return russian;
    }

    public Card setRussian(String russian) {
        this.russian = russian;
        return this;
    }

    public Integer getAttemps() {
        return attemps;
    }

    public Card setAttemps(Integer attemps) {
        this.attemps = attemps;
        return this;
    }

    public Integer getFailure() {
        return failure;
    }

    public Card setFailure(Integer failure) {
        this.failure = failure;
        return this;
    }

    public Integer getCorrect() {
        return correct;
    }

    public Card setCorrect(Integer correct) {
        this.correct = correct;
        return this;
    }
}
