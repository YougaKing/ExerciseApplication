package youga.exerciseapplication.stack;

/**
 * Created by YougaKing on 2017/1/20.
 */

public interface Stack<T> {
    int getSize();

    boolean isEmpty();

    T top() throws ExceptionStackEmpty;

    void push(T t) throws ExceptionStackFull;

    T pop() throws ExceptionStackEmpty;
}
