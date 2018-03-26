package fr.istic.mmm.busmatch.domain;

/**
 * Created by bob on 26/03/18.
 */

public class TypeUserSearch {
    private int maxAge;
    private int minAge;
    private EGenre genre;

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public EGenre getGenre() {
        return genre;
    }

    public void setGenre(EGenre genre) {
        this.genre = genre;
    }
}
