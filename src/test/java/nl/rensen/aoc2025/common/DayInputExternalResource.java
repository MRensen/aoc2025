package nl.rensen.aoc2025.common;

import org.junit.rules.ExternalResource;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class DayInputExternalResource extends ExternalResource {
    int day = 1;
    List<String> input = null;

    public DayInputExternalResource(int day) {
        this.day = day;
    }

	@Override
	protected void before() throws Throwable {
        String filename = String.format("/input-%02d.txt", day);

        InputStream stream = this.getClass().getResourceAsStream(filename);
        try (BufferedReader r = new BufferedReader(new InputStreamReader(stream))) {
            input =  r.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public List<String> getLines() {
        return input;
    }
}
