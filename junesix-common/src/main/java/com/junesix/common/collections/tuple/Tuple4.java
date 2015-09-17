package com.junesix.common.collections.tuple;

import java.io.Serializable;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-02-03 15:04
 */
public class Tuple4<T1, T2, T3, T4> implements Serializable {
    private static final long serialVersionUID = 2353783671717192843L;
    private T1 param1;
    private T2 param2;
    private T3 param3;
    private T4 param4;

    public Tuple4(T1 param1, T2 param2, T3 param3, T4 param4) {
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
    }

    public T1 get1() {
        return param1;
    }

    public T2 get2() {
        return param2;
    }

    public T3 get3() {
        return param3;
    }

    public T4 get4() {
        return param4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple4 tuple4 = (Tuple4) o;

        if (param1 != null ? !param1.equals(tuple4.param1) : tuple4.param1 != null) return false;
        if (param2 != null ? !param2.equals(tuple4.param2) : tuple4.param2 != null) return false;
        if (param3 != null ? !param3.equals(tuple4.param3) : tuple4.param3 != null) return false;
        if (param4 != null ? !param4.equals(tuple4.param4) : tuple4.param4 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = param1 != null ? param1.hashCode() : 0;
        result = 31 * result + (param2 != null ? param2.hashCode() : 0);
        result = 31 * result + (param3 != null ? param3.hashCode() : 0);
        result = 31 * result + (param4 != null ? param4.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tuple4{" +
                "param1=" + param1 +
                ", param2=" + param2 +
                ", param3=" + param3 +
                ", param4=" + param4 +
                '}';
    }
}
