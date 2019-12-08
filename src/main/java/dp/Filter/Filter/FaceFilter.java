package dp.Filter.Filter;

public class FaceFilter extends BaseFilter {

    @Override
    void rule() {
        replaceList.put(":)", "^_^");
    }
}
