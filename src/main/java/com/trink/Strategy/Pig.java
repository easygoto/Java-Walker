package com.trink.Strategy;

public class Pig implements Comparable {

    private int voice;
    private int body;

    private Comparator pigVoiceComparator = new PigVoiceComparator();
    private Comparator pigBodyComparator = new PigBodyComparator();


    public Pig(int voice, int body) {
        this.voice = voice;
        this.body = body;
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Pig{" +
                "voice=" + voice +
                ", body=" + body +
                '}';
    }

    @Override
    public int compareTo(Object object) {
        return pigBodyComparator.compare(this, object);
    }
}
