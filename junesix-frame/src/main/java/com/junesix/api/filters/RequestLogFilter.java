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

    private static final Logger logger = LoggerFactory.getLogger("REQUEST");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, new CopyPrintWrapper((HttpServletResponse) response));

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
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(baos);
            writer.append("haaafdsafd");
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
            logger.info(sb.toString());
            super.write(newbuf, 0, newbuf.length);
        }
    }

    private class CopyPrintWrapper extends HttpServletResponseWrapper {
        public CopyPrintWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            CopyPrintWriter writer = new CopyPrintWriter(super.getWriter());
            logger.info(writer.getCopy());
            return writer;
        }
    }

    public class CopyPrintWriter extends PrintWriter {

        private StringBuilder copy = new StringBuilder();

        public CopyPrintWriter(Writer writer) {
            super(writer);
        }

        @Override
        public void write(int c) {
            copy.append((char) c); // It is actually a char, not an int.
            super.write(c);
        }

        @Override
        public void write(char[] chars, int offset, int length) {
            copy.append(chars, offset, length);
            super.write(chars, offset, length);
        }

        @Override
        public void write(String string, int offset, int length) {
            copy.append(string, offset, length);
            super.write(string, offset, length);
        }

        public String getCopy() {
            return copy.toString();
        }

    }
}