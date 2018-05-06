import Enums.FileContentType;
import Inetrfaces.IConvertArray;
import Inetrfaces.ISettings;

public class ConvertArrayToInt implements IConvertArray {

    Boolean _toInt;

    public ConvertArrayToInt(ISettings settings) {
        _toInt = settings.GetContentType() == FileContentType.TypeInt;
    }

    @Override
    public Object[] ConvertArray(Object[] elements) {
        if (elements == null || elements.length < 1)
            return null;
        if (elements[0] instanceof String) {
            if (_toInt) {
                return ConvertToInt((String[]) elements);
            } else {
                return elements;
            }
        }
        return null;
    }

    public Object[] ConvertToInt(String[] elements) {
        Integer[] intArr = new Integer[elements.length];

        for (int i = 0; i < elements.length; i++) {
            intArr[i] = Integer.parseInt(elements[i]);
        }
        return intArr;
    }

    public String[] ConvertToString(Object[] elements) {
        String[] objectArr = new String[elements.length];

        for (int i = 0; i < elements.length; i++) {
            objectArr[i] = elements[i].toString();
        }
        return objectArr;
    }
}
