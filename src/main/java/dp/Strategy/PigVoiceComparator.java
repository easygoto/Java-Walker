package dp.Strategy;

public class PigVoiceComparator implements Comparator {

    @Override
    public int compare(Object object1, Object object2) {
        Pig pig1 = (Pig) object1;
        Pig pig2 = (Pig) object2;
        return Integer.compare(pig1.getVoice(), pig2.getVoice());
    }
}
