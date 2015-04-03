package ru.ncedu.java.tasks.WordProcessor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordProcessorImpl implements WordProcessor {

    private String src;

    public WordProcessorImpl() {
        this(null);
    }

    public WordProcessorImpl(String src) {
        this.src = src;
    }

    @Override
    public String getText() {
        return src;
    }

    private void checkTextToNull(String src) {
        if (src == null) {
            throw new IllegalArgumentException("Text is null...");
        }
    }

    @Override
    public void setSource(String src) {
        checkTextToNull(src);

        this.src = src;
    }

    @Override
    public void setSourceFile(String srcFile) throws IOException {
        checkTextToNull(srcFile);

        setSource(new FileInputStream(srcFile));
    }

    @Override
    public void setSource(FileInputStream fis) throws IOException {
        if (fis == null) {
            throw new IllegalArgumentException("InputStream is null...");
        }

        try {
            byte[] str = new byte[fis.available()];
            fis.read(str);
            this.src = new String(str);
        } finally {
            try {
                fis.close();
            } catch (IOException ignore) {
                /*NOP*/
            }
        }
    }

    @Override
    public Set<String> wordsStartWith(String begin) {
        if (this.src == null) {
            throw new IllegalStateException("Text is null...");
        }

        Set<String> parseText = new HashSet<>();

        Pattern pat = Pattern.compile("\\S+");
        Matcher mat = pat.matcher(this.src.toLowerCase());

        while (mat.find()) {
            if (begin == null || begin.length() == 0 ||
                    (begin.toLowerCase()).equals(mat.group().substring(0, begin.length()))) {
                parseText.add(mat.group());
            }
        }
        return parseText;
    }
}
