package org.essembeh.rtfm.cli.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;

public class JsonUtils {
	public static final Gson GSON = new Gson();

	public static <T> T load(Path input, Class<T> clazz) throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(input)) {
			return GSON.fromJson(reader, clazz);
		}
	}

	public static void save(Object object, Path output) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(output)) {
			writer.write(GSON.toJson(object));
		}
	}
}
