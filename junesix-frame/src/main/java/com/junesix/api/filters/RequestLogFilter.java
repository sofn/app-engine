package com.junesix.api.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.util.Arrays;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-04-30 18:46
 */
public class RequestLogFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger("REQUEST");

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        res = new HttpServletResponseJsonWrapper((HttpServletResponse) res);
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    private class HttpServletResponseJsonWrapper extends HttpServletResponseWrapper {

        private HttpServletResponse servletResponse;

        public HttpServletResponseJsonWrapper(HttpServletResponse response) {
            super(response);
            this.servletResponse = response;
        }

        //TODO json格式不走这块代码
        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriterJson(servletResponse.getWriter());
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            OutputStreamWriter writer = new OutputStreamWriter(super.getOutputStream());
            writer.append("");
            writer.flush();
            return super.getOutputStream();
        }
    }

    private class PrintWriterJson extends PrintWriter {

        public PrintWriterJson(Writer out) {
            super(out);
        }

        @Override
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