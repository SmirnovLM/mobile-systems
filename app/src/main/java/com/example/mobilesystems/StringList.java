package com.example.mobilesystems;

import java.util.ArrayList;
import java.util.List;

public class StringList {
    private List<String> stringList;

    public StringList() {
        stringList = new ArrayList<>();
    }

    public void addString(String str) {
        stringList.add(str.toLowerCase());
    }

    public void removeLastString() {
        if (!stringList.isEmpty()) {
            stringList.remove(stringList.size() - 1);
        }
    }

    public String getAllWordsAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            String word = stringList.get(i);
            if (i == 0) {
                // Делаем первую букву первого слова заглавной
                word = word.substring(0, 1).toUpperCase() + word.substring(1);
            }
            sb.append(word).append(", ");
        }
        // Удаляем последнюю запятую и пробел
        if (sb.length() > 2) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public List<String> getStringList() {
        return stringList;
    }
}
