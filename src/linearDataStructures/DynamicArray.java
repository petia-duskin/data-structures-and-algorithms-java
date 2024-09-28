package linearDataStructures;

public class DynamicArray {
    private int[] values;
    private int currentIndex = -1;

    public DynamicArray(int length) {
        this.values = new int[length];
    }

    public void insert(int value) {
        reshape();
        values[++currentIndex] = value;
    }

    private void reshape() {
        if (currentIndex == values.length - 1) {
            int[] newArr = new int[values.length * 2];
            for (int i = 0; i < values.length; i++) {
                newArr[i] = values[i];
            }
            values = newArr;
        }
    }

    public void removeAt(int index) {
        if (index < 0 || index > currentIndex) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        shiftValues(index);
        currentIndex--;
    }

    private void shiftValues(int index) {
        for (int i = index; i < currentIndex; i++) {
            values[i] = values[i + 1];
        }
    }

    public int indexOf(int value) {
        for (int i = 0; i <= currentIndex; i++) {
            if (values[i] == value) {
                return i;
            }
        }

        return -1;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int val : values) {
            sb.append(val);
            sb.append(" ");
        }

        return sb.toString();
    }
}
