package dp.filter.normal;

/**
 * @author trink
 */
public class SensitiveFilter extends BaseFilter {

    @Override
    void rule() {
        replaceList.put("敏感", "不敏感");
        replaceList.put("傻吊", "**");
        replaceList.put("他妈", "**");
    }
}
