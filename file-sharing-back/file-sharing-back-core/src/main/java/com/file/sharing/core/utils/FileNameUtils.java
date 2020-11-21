package com.file.sharing.core.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.file.sharing.core.objects.Constants.DUPLICATE_SUFFIX_STRING;
import static com.file.sharing.core.objects.Constants.DUPLICATE_SUFFIX_STRING_PATTERN;

public class FileNameUtils {

    public static String getOriginalFileName(String name) {
        return name.substring(0, name.lastIndexOf(" ("+ DUPLICATE_SUFFIX_STRING));
    }

    public static String getDuplicateNameWithSuffix(String fileName, String suffix) {
        return new StringBuilder(fileName).insert(fileName.lastIndexOf('.'), suffix).toString();
    }

    public static String getNextAvailableSuffix(List<String> fileNames) {
        int suffixNumber = getNextAvailableSuffixNumber(fileNames);
        return String.format(DUPLICATE_SUFFIX_STRING_PATTERN, suffixNumber);
    }

    public static int getNextAvailableSuffixNumber(List<String> fileNames) {
        Set<String> fileNameSet = new HashSet<>(fileNames);
        if(fileNames.isEmpty()) {
            return 1;
        }

        String fileNameWithoutExtension = getOriginalFileName(fileNames.get(0));
        String extension = getFileExtension(fileNames.get(0));
        String fileNameFormat = fileNameWithoutExtension + DUPLICATE_SUFFIX_STRING_PATTERN + extension;
        int suffixNumber = 1;
        while(fileNameSet.contains(String.format(fileNameFormat, suffixNumber))) {
            suffixNumber++;
        }

        return suffixNumber;
    }

    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.'));
    }

}
