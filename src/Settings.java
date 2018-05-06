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
        String path = args[0];
        _workDir = new File(path);
        if (!(_workDir.isFile() || _workDir.isDirectory())) {
            throw new Exception("First parameter contains invalid path");
        }

        _outPrefix = args[1];
        if (!_outPrefix.contains("--out-prefix=")) {
            throw new Exception("Second parameter doesn't contains '--out-prefix'");
        }

        _outPrefix = _outPrefix.replace("--out-prefix=", "").trim();

        String contentType = args[2];
        if (!contentType.contains("--content-type=")) {
            throw new Exception("Therd parameter doesn't contains '--content-type='");
        } else {
            contentType = contentType.replace("--content-type=", "").trim();
            if (contentType.compareTo("i")==0) {
                _contentType = FileContentType.TypeInt;
            }else if (contentType.compareTo("s")==0) {
                _contentType = FileContentType.TypeString;
            } else {
                throw new Exception("Therd parameter after '--content-type=' can contains only 'i' or 's' symbol");
            }
        }

        String sortMode = args[3];
        if (!sortMode.contains("--sort-mode=")) {
            throw new Exception("Therd parameter doesn't contains '--sort-mode='");
        } else {
            sortMode = sortMode.replace("--sort-mode=", "").trim();
            if (sortMode.compareTo("a")==0) {
                _sortMode = SortMode.Asc;
            } else if (sortMode.compareTo("d")==0) {
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
