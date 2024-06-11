package TMS.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ArchiveService {
    private static final String ARCHIVE_DIR = "archive";

    public void archiveFile(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("Файл для архивации не найден!");
        }

        Files.move(Paths.get(file.getPath()), Paths.get(ARCHIVE_DIR + "/" + file.getName()));
    }
}