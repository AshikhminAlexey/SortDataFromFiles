package Inetrfaces;

import Enums.FileContentType;
import Enums.SortMode;

import java.io.File;

public interface ISettings {
    void GetSettingsFromArgs(String[] args) throws Exception;

    File GetWorkDir();

    String GetOutPrefix();

    FileContentType GetContentType();

    SortMode GetSortMode();
}
