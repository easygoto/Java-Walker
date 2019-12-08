package dp.Filter.Filter;

public class HTMLFilter extends BaseFilter {

    @Override
    void rule() {
        replaceList.put("<", "{");
        replaceList.put(">", "}");
    }
}
