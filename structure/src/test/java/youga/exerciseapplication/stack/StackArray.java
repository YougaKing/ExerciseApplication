package youga.exerciseapplication.stack;

/**
 * Created by YougaKing on 2017/1/20.
 */

public class StackArray implements Stack<Object> {

    public static int CAPACITY = 1024;
    protected int mCapacity;
    protected Object[] mObjects;
    protected int top = -1;

    public StackArray() {
        this(CAPACITY);
    }

    public StackArray(int capacity) {
        mCapacity = capacity;
        mObjects = new Object[capacity];
    }

    @Override
    public int getSize() {
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        return top < 0;
    }

    @Override
    public Object top() throws ExceptionStackEmpty {
        if (isEmpty()) {
            throw new ExceptionStackEmpty("空栈");
        }
        return mObjects[top];
    }

    @Override
    public void push(Object object) throws ExceptionStackFull {
        if (getSize() == mCapacity) {
            throw new ExceptionStackFull("栈溢出");
        }
        mObjects[++top] = object;
    }

    @Override
    public Object pop() throws ExceptionStackEmpty {
        if (isEmpty()) {
            throw new ExceptionStackEmpty("空栈");
        }
        Object o = mObjects[top];
        mObjects[top--] = null;
        return o;
    }
}
