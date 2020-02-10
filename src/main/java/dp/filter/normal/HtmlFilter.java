package dp.filter.normal;

/**
 * @author trink
 */
public class HtmlFilter extends BaseFilter {

    @Override
    void rule() {
        replaceList.put("<", "{");
        replaceList.put(">", "}");
    }
}
