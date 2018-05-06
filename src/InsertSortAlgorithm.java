import Enums.SortMode;
import Inetrfaces.ISettings;
import Inetrfaces.ISortAlgorithm;

public class InsertSortAlgorithm implements ISortAlgorithm {

    SortMode _sortMode;

    public InsertSortAlgorithm(ISettings settings) {
        _sortMode = settings.GetSortMode();
    }

    @Override
    public Object[] SortArray(Object[] elements) {
        switch (_sortMode) {
            case Asc:
                return SortArrayAsc(elements);
            case Desc:
                return SortArrayDesc(elements);
            default:
                return null;
        }
    }

    @Override
    public Object[] SortArrayAsc(Object[] elements) {
        if (elements == null || elements.length < 1)
            return null;
        if (elements[0] instanceof String) return SortArrayAsc((String[]) elements);
        if (elements[0] instanceof Integer) return SortArrayAsc((Integer[]) elements);
        return null;
    }

    @Override
    public Object[] SortArrayDesc(Object[] elements) {
        if (elements == null || elements.length < 1)
            return null;
        if (elements[0] instanceof String) return SortArrayDesc((String[]) elements);
        if (elements[0] instanceof Integer) return SortArrayDesc((Integer[]) elements);
        return null;
    }

    public Integer[] SortArrayAsc(Integer[] input) {

        int temp;
        for (int i = 1; i < input.length; i++) {
            for (int j = i; j > 0; j--) {
                if (input[j] < input[j - 1]) {
                    temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                }
            }
        }
        return input;
    }

    public Integer[] SortArrayDesc(Integer[] input) {

        int temp;
        for (int i = 1; i < input.length; i++) {
            for (int j = i; j > 0; j--) {
                if (input[j] > input[j - 1]) {
                    temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                }
            }
        }
        return input;
    }

    public String[] SortArrayAsc(String[] input) {

        String temp;
        for (int i = 1; i < input.length; i++) {
            for (int j = i; j > 0; j--) {
                if (input[j].compareTo(input[j - 1]) > 0) {
                    temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                }
            }
        }
        return input;
    }

    public String[] SortArrayDesc(String[] input) {

        String temp;
        for (int i = 1; i < input.length; i++) {
            for (int j = i; j > 0; j--) {
                if (input[j].compareTo(input[j - 1]) < 0) {
                    temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                }
            }
        }
        return input;
    }
}
