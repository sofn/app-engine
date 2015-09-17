package com.junesix.common.collections.tuple;

import java.io.Serializable;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-02-03 15:04
 */
public class Tuple2<T1, T2> implements Serializable {
    private static final long serialVersionUID = 1526592320897338432L;
    private T1 param1;
    private T2 param2;

    public Tuple2(T1 parma1, T2 parma2) {
        this.param1 = parma1;
        this.param2 = parma2;
    }

    public T1 get1() {
        return param1;
    }

    public T2 get2() {
        return param2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple2 tuple2 = (Tuple2) o;

        if (param1 != null ? !param1.equals(tuple2.param1) : tuple2.param1 != null) return false;
        if (param2 != null ? !param2.equals(tuple2.param2) : tuple2.param2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = param1 != null ? param1.hashCode() : 0;
        result = 31 * result + (param2 != null ? param2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tuple2{" +
                "param1=" + param1 +
                ", param2=" + param2 +
                '}';
    }
}
