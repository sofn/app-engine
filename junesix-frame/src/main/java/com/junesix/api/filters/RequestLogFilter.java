package com.junesix.api.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-04-30 18:46
 */
public class RequestLogFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger("REQUEST");

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        chain.doFilter(req, res);
        String mediaType = response.getContentType();
        /*try {
            ServletOutputStream sos = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        res.getOutputStream().write(baos.toByteArray());
        LOGGER.info(baos.toString());
//        if (MediaType.APPLICATION_JSON.includes(new MediaType(mediaType))) {
//            JsonpAdapterWriter writer = new JsonpAdapterWriter(req, response.getWriter());
//            res.writer(writer);
//        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    /*private static class JsonpAdapterWriter implements ContainerResponseWriter {
        private ContainerResponseWriter origWriter;
        private ByteArrayOutputStream baos;
        private ContainerResponse response;
        private long contentLength;
        private ContainerRequest httpRequest;

        public JsonpAdapterWriter(ContainerRequest httpRequest,
                                  ContainerResponseWriter origWriter) {
            this.origWriter = origWriter;
            this.httpRequest = httpRequest;
        }

        @Override
        public OutputStream writeStatusAndHeaders(long contentLength,
                                                  ContainerResponse response) throws IOException {
            this.response = response;
            this.baos = new ByteArrayOutputStream();
            this.contentLength = contentLength;
            return baos;
        }

        @Override
        public void finish() throws IOException {
            byte[] entity = baos.toByteArray();
            boolean success = this.response.getStatus() == 200;

            List<String> jsonpList = httpRequest.getQueryParameters().get("_callback");
            String jsonpMethod = jsonpList != null && jsonpList.size() > 0 ? jsonpList.get(0) : null;

            //对外输出的状态码恒为 200。为了解决2g网络下移动网关修改错误页面的问题。
            this.response.setStatus(200);
            StringBuilder bufBegin = new StringBuilder();
            if (jsonpMethod != null) {
                bufBegin.append(jsonpMethod).append("(");
            }
            bufBegin.append("{\"apistatus\":").append(success ? 1 : 0)
                    .append(",\"result\":");
            byte[] begin = bufBegin.toString().getBytes("UTF-8");
            StringBuilder bufEnd = new StringBuilder();
            bufEnd.append("}");
            if (jsonpMethod != null) {
                bufEnd.append(")");
            }
            byte[] end = bufEnd.toString().getBytes("UTF-8");
            // 如果contentLength 小于0，则不会在header里输出 content-length
            if (contentLength > 0) {
                contentLength = contentLength + begin.length + end.length;
            }
            // Write out the headers and buffered entity
            OutputStream out = origWriter.writeStatusAndHeaders(contentLength,
                    response);
            out.write(begin);
            out.write(entity);
            out.write(end);
            origWriter.finish();
        }

    }
*/
}