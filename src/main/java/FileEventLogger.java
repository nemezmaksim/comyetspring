import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

    private String fileName;
    private File file;

    FileEventLogger(String fileName) {
        this.fileName=fileName;
    }

    @Override
    public void logEvent(Event event)  {
        try {
            FileUtils.writeStringToFile(new File(fileName),event.toString(),true);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void init () {
        file = new File(fileName);

        if (file.exists() && !file.canWrite()){
            throw new IllegalArgumentException("Don't can write to file: "+ fileName);
        }
            else if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new IllegalArgumentException("Don't create file",e);
            }
        }

    }
}
