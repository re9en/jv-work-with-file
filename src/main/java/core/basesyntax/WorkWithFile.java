package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    private static final int FIRST_PART = 0;
    private static final int SECOND_PART = 1;
    private int supply = 0;
    private int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        supply = 0;
        buy = 0;
        readFileName(fromFileName);
        writeToFile(toFileName);
    }

    public void readFileName(String fromFileName) {

        String[] parts = new String[2];
        try (BufferedReader reader =
                    Files.newBufferedReader(Path.of(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parts = line.split(",");
                if (parts[FIRST_PART].equals("supply")) {
                    supply += Integer.parseInt(parts[SECOND_PART]);
                } else {
                    buy += Integer.parseInt(parts[SECOND_PART]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("CAN'T READ FILE: " + e);
        }
    }

    public void writeToFile(String toFileName) {

        StringBuilder fileNameData = new StringBuilder();

        try (BufferedWriter writer =
                     Files.newBufferedWriter(Path.of(toFileName))) {
            fileNameData.append("supply," + supply + System.lineSeparator())
                    .append("buy," + buy + System.lineSeparator())
                    .append("result," + (supply - buy) + System.lineSeparator());
            writer.write(String.valueOf(fileNameData));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
