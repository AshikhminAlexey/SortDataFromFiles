import Inetrfaces.IConvertArray;
import Inetrfaces.ISettings;
import Inetrfaces.ISortAlgorithm;

import java.io.*;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processing {

    private final static int poolSize = 5;

    private ISortAlgorithm _sortAlgorithm;
    private IConvertArray _convertArray;
    private ISettings _settings;

    Processing(ISortAlgorithm sortAlgorithm, IConvertArray convertArray, ISettings settings) {
        _sortAlgorithm = sortAlgorithm;
        _convertArray = convertArray;
        _settings = settings;
    }

    void StartProcess() {
        ProcessFiles(_settings.GetWorkDir());
    }

    private void ProcessFiles(File directory) {
        ExecutorService executors = Executors.newFixedThreadPool(poolSize);

        if (directory.isFile()) {
            executors.submit(new Processor(directory));
        } else if (directory.isDirectory()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                if (file.isFile()) {
                    executors.submit(new Processor(file));
                }
            }
        }
        executors.shutdown();
    }

    private FileProcessing FileRead(File file) {
        FileProcessing fp = new FileProcessing();
        fp.file = file;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            fp.Content = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fp;
    }

    private void FileWrite(FileProcessing fileProcessing) {
        File file = fileProcessing.file;
        File newFile = new File(Settings.Instance.GetWorkDir(), Settings.Instance.GetOutPrefix() + file.getName());

        try (BufferedWriter br = new BufferedWriter(new FileWriter(newFile))) {
            br.write(fileProcessing.SortedContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Processor implements Runnable {
        private final File file;

        Processor(File file) {
            this.file = file;
        }

        @Override
        public void run() {
            FileProcessing fileProcessing = FileRead(file);
            Object[] contentArr = fileProcessing.Content.split("\\R");
            Object[] conArray = _convertArray.ConvertArray(contentArr);
            Object[] sortArray = _sortAlgorithm.SortArray(conArray);
            String[] sortContentArr = _convertArray.ConvertToString(sortArray);
            fileProcessing.SortedContent = String.join("\r\n", sortContentArr);
            FileWrite(fileProcessing);
        }
    }
}
