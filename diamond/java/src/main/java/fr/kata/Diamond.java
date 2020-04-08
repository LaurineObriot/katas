package fr.kata;

import java.util.ArrayList;
import java.util.List;

public class Diamond {

    public List<String> upTo(Character stopLetter) {
        List<String> result = new ArrayList<>();
        result.add(stopLetter.toString());
        return result;
    }
}
