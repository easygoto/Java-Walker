package dp.filter.normal;

/**
 * @author trink
 */
public class FaceFilter extends BaseFilter {

    @Override
    void rule() {
        replaceList.put(":)", "^_^");
    }
}
