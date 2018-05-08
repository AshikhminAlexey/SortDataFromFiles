import Enums.FileContentType;
import Enums.SortMode;
import Inetrfaces.ISettings;

import java.io.File;

public class Settings implements ISettings {
    public static final Settings Instance = new Settings();
    FileContentType _contentType;
    SortMode _sortMode;
    File _workDir;
    String _outPrefix;

    @Override
    public void GetSettingsFromArgs(String[] args) throws Exception {
        if (args.length != 4) {
            throw new Exception("Invalid parameter count, need four parameter\r\n" +
                    "example: {work path} --out-prefix={out prefix [any string]} --content-type={content type in [i,s]} --sort-mode={sort mode in [a,d]}");
        }

        String path = args[0];
        if (path == null || !((_workDir = new File(path)).isFile() || _workDir.isDirectory())) {
            throw new Exception("First parameter contains invalid path");
        }

        _outPrefix = args[1];
        if (_outPrefix == null || !_outPrefix.contains("--out-prefix=")) {
            throw new Exception("Second parameter doesn't contains '--out-prefix'");
        }

        _outPrefix = _outPrefix.replace("--out-prefix=", "").trim();

        String contentType = args[2];
        if (contentType == null || !contentType.contains("--content-type=")) {
            throw new Exception("Therd parameter doesn't contains '--content-type='");
        } else {
            contentType = contentType.replace("--content-type=", "").trim();
            if (contentType.compareTo("i") == 0) {
                _contentType = FileContentType.TypeInt;
            } else if (contentType.compareTo("s") == 0) {
                _contentType = FileContentType.TypeString;
            } else {
                throw new Exception("Therd parameter after '--content-type=' can contains only 'i' or 's' symbol");
            }
        }

        String sortMode = args[3];
        if (sortMode == null || !sortMode.contains("--sort-mode=")) {
            throw new Exception("Therd parameter doesn't contains '--sort-mode='");
        } else {
            sortMode = sortMode.replace("--sort-mode=", "").trim();
            if (sortMode.compareTo("a") == 0) {
                _sortMode = SortMode.Asc;
            } else if (sortMode.compareTo("d") == 0) {
                _sortMode = SortMode.Desc;
            } else {
                throw new Exception("Therd parameter after '--content-type=' can contains only 'a' or 'd' symbol");
            }
        }
    }

    @Override
    public File GetWorkDir() {
        return _workDir;
    }

    @Override
    public String GetOutPrefix() {
        return _outPrefix;
    }

    @Override
    public FileContentType GetContentType() {
        return _contentType;
    }

    @Override
    public SortMode GetSortMode() {
        return _sortMode;
    }
}
