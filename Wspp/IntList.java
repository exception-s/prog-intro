import java.util.Arrays;

public class IntList {
    private int size;
    private int[] array;
    public IntList() {
        array = new int[16];
        size = 0;
    }
    public void append(int value) {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[size] = value;
        size++;
    }
    public int getSize() {
        return size;
    }
    public int get(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Unexpected index");
        }
        return array[index];
    }
    public void set(int index, int value) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Unexpected index");
        }
        array[index] = value;
    }
    public void pop() {
        size--;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int value : array) {
            if (value == 0) {
                break;
            }
            string.append(value);
            string.append(" ");
        }
        return string.toString().trim();
    }
}
