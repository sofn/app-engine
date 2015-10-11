package com.appengine.common.exception;

/**
 * Matrix exception helper for make local or remote excetion.
 *
 * @author fishermen
 * @version V1.0 created at: 2013-2-1
 */

public class MatrixExceptionHelper {

    /**
     * 为前端直接构造的异常
     *
     * @param factor
     * @return
     */
    public static MatrixException localMatrixException(ExcepFactor factor) {
        return new MatrixException(factor);
    }

    /**
     * 为前端直接构造的异常
     *
     * @param factor
     * @param message
     * @return
     */
    public static MatrixException localMatrixException(ExcepFactor factor, Object message) {
        return new MatrixException(factor, message);
    }

    public static MatrixException localMatrixException(ExcepFactor factor, Throwable exception) {
        return new MatrixException(factor, exception);
    }

    /**
     * 为前端直接构造的异常
     *
     * @param factor
     * @param args
     * @return
     */
    public static MatrixException localMatrixException(ExcepFactor factor, Object[] args) {
        return new MatrixException(factor, args);
    }

    /*
        用来重设英文和中文提示
     */
    public static MatrixException localMatrixException(ExcepFactor factor, Object[] args, Object[] argsCn) {
        return new MatrixException(factor, args, argsCn);
    }

    /**
     * 为rpc服务构造的remote exception
     *
     * @param factor
     * @return
     */
    public static MatrixException rpcMatrixException(ExcepFactor factor) {
        return new MatrixException(factor);
    }

    /**
     * 为rpc服务构造的remote exception
     *
     * @param factor
     * @param message
     * @return
     */
    public static MatrixException rpcMatrixException(ExcepFactor factor, Object message) {
        return new MatrixException(factor, message);
    }

    public static MatrixException rpcMatrixException(ExcepFactor factor, Throwable exception) {
        return new MatrixException(factor, exception);
    }

    /**
     * 为rpc服务构造的remote exception
     *
     * @param factor
     * @param args
     * @return
     */
    public static MatrixException rpcMatrixException(ExcepFactor factor, Object[] args) {
        return new MatrixException(factor, args);
    }

}
