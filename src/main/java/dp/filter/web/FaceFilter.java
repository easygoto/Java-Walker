package dp.filter.web;

/**
 * @author trink
 */
public class FaceFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        String requestResult = request.getMessage()
                .replace(":)", "^V^");
        requestResult += "---FaceFilter";
        request.setMessage(requestResult);

        filterChain.doFilter(request, response, filterChain);

        String responseResult = response.getMessage();
        responseResult += "---FaceFilter";
        response.setMessage(responseResult);
    }
}
