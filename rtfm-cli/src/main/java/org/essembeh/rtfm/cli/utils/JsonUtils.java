package org.essembeh.rtfm.cli.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.google.gson.Gson;

public class JsonUtils {
	public static final Gson GSON = new Gson();

	public static <T> T load(Path input, Class<T> clazz) throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(input)) {
			return GSON.fromJson(reader, clazz);
		}
	}

	public static <T> T loadGzip(Path input, Class<T> clazz) throws IOException {
		try (InputStream is = Files.newInputStream(input); GZIPInputStream zis = new GZIPInputStream(is); InputStreamReader isr = new InputStreamReader(zis)) {
			return GSON.fromJson(isr, clazz);
		}
	}

	public static void save(Object object, Path output) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(output)) {
			writer.write(GSON.toJson(object));
		}
	}

	public static void saveGzip(Object object, Path output) throws IOException {
		try (OutputStream os = Files.newOutputStream(output);
				GZIPOutputStream zos = new GZIPOutputStream(os);
				OutputStreamWriter osw = new OutputStreamWriter(zos)) {
			GSON.toJson(object, osw);
		}
	}
}
