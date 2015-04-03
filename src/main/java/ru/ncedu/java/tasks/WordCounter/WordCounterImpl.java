package ru.ncedu.java.tasks.WordCounter;

import java.io.PrintStream;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounterImpl implements WordCounter {

    private String text;

    public WordCounterImpl() {
        this(null);
    }

    public WordCounterImpl(String text) {
        this.text = text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }

    private void checkTextToNull() {
        if (this.text == null) {
            throw new IllegalStateException("Analyzed text is null...");
        }
    }

    @Override
    public Map<String, Long> getWordCounts() {
        checkTextToNull();

        //  make pattern for tags
        Pattern pat = Pattern.compile("<(/?[^>]+)>");
        Matcher mat = pat.matcher(text.toLowerCase());

        pat = Pattern.compile("\\S+");
        mat = pat.matcher(mat.replaceAll(""));

        Map<String, Long> mapWordCounts = new HashMap<>();
        while (mat.find()) {
            if (!mapWordCounts.containsKey(mat.group())) {
                mapWordCounts.put(mat.group(), 1L);
            } else {
                mapWordCounts.put(mat.group(), mapWordCounts.get(mat.group()) + 1);
            }
        }

        return mapWordCounts;
    }

    @Override
    public List<Entry<String, Long>> getWordCountsSorted() {
        checkTextToNull();

        return sortWordCounts(getWordCounts());
    }

    @Override
    public List<Entry<String, Long>> sortWordCounts(Map<String, Long> orig) {
        if (orig != null) {
            List<Entry<String, Long>> list = new ArrayList<>(orig.entrySet());

            Collections.sort(list, new Comparator<Entry<String, Long>>() {
                @Override
                public int compare(Entry<String, Long> o1, Entry<String, Long> o2) {
                    if (!(o1.getValue()).equals(o2.getValue())) {
                        return (o2.getValue()).compareTo(o1.getValue());
                    } else {
                        return (o1.getKey()).compareTo(o2.getKey());
                    }
                }
            });
            return list;
        }
        return null;
    }

    @Override
    public void printWordCounts(PrintStream ps) {
        checkTextToNull();

        for (Entry<String, Long> map : getWordCounts().entrySet()) {
            ps.println(map.getKey() + " " + map.getValue());
        }
    }

    @Override
    public void printWordCountsSorted(PrintStream ps) {
        checkTextToNull();

        for (Entry<String, Long> list : sortWordCounts(getWordCounts())) {
            ps.println(list.getKey() + " " + list.getValue());
        }
    }
}
