package dp.Filter.Web;

public class HTMLFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        String requestResult = request.getMessage()
                .replace("<", "{")
                .replace(">", "}");
        requestResult += "---HTMLFilter";
        request.setMessage(requestResult);

        filterChain.doFilter(request, response, filterChain);

        String responseResult = response.getMessage();
        responseResult += "---HTMLFilter";
        response.setMessage(responseResult);
    }
}
