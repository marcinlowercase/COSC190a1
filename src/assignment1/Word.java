package assignment1;

import java.io.File;
import java.util.Objects;

public class Word {

    private int usageTime = 0;
    private String word;

    public Word(String word){
        this.word = word;
    }

    public String getWord() {
        return word;
    }
    public int getUsageTime() {
        return usageTime;
    }

    public void useOneMore(){
        this.usageTime++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word);
    }

    @Override
    public String toString() {
        return word + "\t" + usageTime;
    }


}
