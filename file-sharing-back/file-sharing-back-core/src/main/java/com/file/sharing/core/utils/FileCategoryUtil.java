package com.file.sharing.core.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.file.sharing.core.exception.FileExtensionException;
import com.file.sharing.core.objects.file.FileCategories;

/**
 * @author Alexandru Mihai
 * @created Nov 30, 2017
 * 
 */
public final class FileCategoryUtil {

	private static final Map<FileCategories, List<String>> EXT_CATEGORIES = new HashMap<>();

	static {
		EXT_CATEGORIES.put(FileCategories.MUSIC,
				Arrays.asList(".3gp", ".aa", ".aac", ".aax", ".act", ".aiff", ".amr", ".ape", ".au", ".awb", ".dct",
						".dss", ".dvf", ".flac", ".gsm", ".iklax", ".ivs", ".m4a", ".m4b", ".m4p", ".mmf", ".mp3",
						".mpc", ".msv", ".ogg", ".oga", ".mogg", ".opus", ".ra", ".rm", ".raw", ".sln", ".tta", ".vox",
						".wav", ".wma", ".wv", ".webm", ".8svx"));
		EXT_CATEGORIES.put(FileCategories.VIDEOS,
				Arrays.asList(".webm ", ".mkv ", ".flv ", ".vob ", ".ogv ", ".ogg ", ".drc ", ".gif ", ".gifv ",
						".mng ", ".avi ", ".mov ", ".qt ", ".wmv ", ".yuv ", ".rm ", ".rmvb ", ".asf ", ".amv ",
						".mp4 ", ".m4p ", ".m4v ", ".mpg ", ".mp2 ", ".mpeg ", ".mpe ", ".mpv ", ".mpg ", ".mpeg ",
						".m2v ", ".m4v ", ".svi ", ".3gp ", ".3g2 ", ".mxf ", ".roq ", ".nsv ", ".flv ", ".f4v ",
						".f4p ", ".f4a ", ".f4b"));
		EXT_CATEGORIES.put(FileCategories.IMAGES, Arrays.asList(".tif", ".tiff", ".gif", ".jpeg", ".jpg", ".jif",
				".jfif", ".jp2", " .jpx", ".j2k", ".j2c", ".fpx", ".pcd", ".png", ".pdf"));
		EXT_CATEGORIES.put(FileCategories.DOCUMENTS,
				Arrays.asList(".doc", ".docx", ".pdf", ".xls", "xlsx", ".ppt", ".odt"));
	}

	private FileCategoryUtil() {
	}

	/**
	 * @param extension
	 * @return
	 */
	public static FileCategories getCategoryBasedOnExtension(String extension) {
		String ext = Objects.requireNonNull(extension).trim();
		for(Map.Entry<FileCategories, List<String>> e : EXT_CATEGORIES.entrySet()){
			if (e.getValue().contains(ext)) {
				return e.getKey();
			}
		}
		return FileCategories.OTHER;
	}

	/**
	 * @param fileName
	 * @return
	 * 
	 * @throws FileExtensionException
	 *             if the file name does not contain an extension
	 */
	public static String getExtensionFromFileName(String fileName) {

		int i = fileName.lastIndexOf('.');

		if (i > 0) {
			return fileName.substring(i);
		}
		
		throw new FileExtensionException("File name does not contain any extensions");

	}

}
