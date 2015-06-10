package com.junesix.api.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-04-30 18:46
 */
public class RequestLogFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger("REQUEST");

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        res = new HttpServletResponseWrapperURL((HttpServletResponse) res);
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    private class HttpServletResponseWrapperURL extends HttpServletResponseWrapper {

        private HttpServletResponse servletResponse;

        public HttpServletResponseWrapperURL(HttpServletResponse response) {
            super(response);
            this.servletResponse = response;
        }

        //TODO json格式不走这块代码
        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriterURL(servletResponse.getWriter());
        }
    }

    private class PrintWriterURL extends PrintWriter {

        public PrintWriterURL(Writer out) {
            super(out);
        }

        public void write(char buf[], int off, int len) {
            char[] newbuf = Arrays.copyOfRange(buf, off, off + len);
            String str = String.copyValueOf(newbuf);

            StringBuffer sb = new StringBuffer(str);
            sb.append("");
            newbuf = sb.toString().toCharArray();
            LOGGER.info(sb.toString());
            super.write(newbuf, 0, newbuf.length);
        }
    }
}