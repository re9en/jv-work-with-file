package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    private static final int FIRST_PART = 0;
    private static final int SECOND_PART = 1;
    private static final String SUPPLY_CONST = "supply";
    private static final String BUY_CONST = "buy";
    private static final String RESULT_CONST = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFileName(fromFileName));
    }

    private int[] readFileName(String fromFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader =
                    Files.newBufferedReader(Path.of(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[FIRST_PART].equals(SUPPLY_CONST)) {
                    supply += Integer.parseInt(parts[SECOND_PART]);
                } else {
                    buy += Integer.parseInt(parts[SECOND_PART]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("CAN'T READ FILE: " + e);
        }
        return new int[]{supply, buy};
    }

    private void writeToFile(String toFileName, int[] counters) {

        StringBuilder fileNameData = new StringBuilder();

        try (BufferedWriter writer =
                     Files.newBufferedWriter(Path.of(toFileName))) {
            fileNameData.append(SUPPLY_CONST + "," + counters[FIRST_PART]
                            + System.lineSeparator())
                    .append(BUY_CONST + "," + counters[SECOND_PART]
                            + System.lineSeparator())
                    .append(RESULT_CONST + "," + (counters[FIRST_PART] - counters[SECOND_PART])
                            + System.lineSeparator());
            writer.write(String.valueOf(fileNameData));
        } catch (IOException e) {
            throw new RuntimeException("CAN'T WRITE TO FILE: " + e);
        }

    }
}
