package spiffysprite.utils;

import spiffysprite.enums.EnumFileExtensions;
import spiffysprite.enums.EnumWorkspaceSubdirectories;
import spiffysprite.models.Palette;
import spiffysprite.models.Sprite;
import spiffysprite.ui.workspacepanel.WorkspacePanel;
import spiffysprite.utils.jsonparser.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class FileUtils {

    /**
     * Returns whether a file exists with fileName in directory filePath.
     *
     * @param filePath directory to search
     * @param fileName file name to check for
     * @return true if fileName exists in filePath; false otherwise
     * @throws IllegalArgumentException if filePath is not a valid directory
     */
    public static boolean doesFileExist(String filePath, String fileName) throws IllegalArgumentException {
        File directory = new File(filePath);
        File[] files = directory.listFiles(file -> file.getName().equals(fileName));

        if (files == null) {
            throw new IllegalArgumentException();
        } else {
            return files.length > 0;
        }
    }

    public static void loadImage(File file) throws IllegalArgumentException {
        String extension = getFileExtension(file);
        if (!Objects.equals(extension, "png")) {
            throw new IllegalArgumentException("file must be a .PNG");
        }

        try {
            Sprite sprite = Sprite.fromBufferedImage(ImageIO.read(file));
        } catch (IOException ioe) {
            ErrorUtils.displayException(ioe);
        }
    }

    /**
     * Creates a new .PNG file containing a lossless image of the specified sprite.
     *
     * @param sprite sprite to create an image of
     * @param filePath path to save the .PNG file to
     * @param fileName name of the .PNG file to create
     * @throws IOException if an error occurs during file creation or writing
     * @throws NullPointerException if sprite is null
     * @throws FileAlreadyExistsException if a file already exists in filePath named fileName
     */
    public static void saveImage(Sprite sprite, String filePath, String fileName) throws IOException,
            NullPointerException {

        File imageFile = new File(filePath, fileName);
        if (doesFileExist(filePath, fileName)) {
            throw new FileAlreadyExistsException(imageFile.getPath());
        }

        ImageIO.write(sprite, "png", imageFile);
    }

    /**
     * Creates a new .JSON file containing a data representation of a sprite.
     *
     * @param json JSON representation of a sprite
     * @param filePath path to save the .JSON file to
     * @param fileName name of the .JSON file to create
     * @throws IOException if an error occurs during file creation or writing
     * @throws FileAlreadyExistsException if a file already exists in filePath named fileName
     */
    public static void saveJSON(JSONObject json, String filePath, String fileName) throws IOException {

        File dataFile = new File(filePath, fileName);
        Path path = Paths.get(dataFile.getPath());
        if (doesFileExist(filePath, fileName)) {
            throw new FileAlreadyExistsException(dataFile.getPath());
        }

        Files.createFile(path);
        Files.writeString(path, json.toPrettyString());
    }



    /**
     * Returns all files in the base workspace directory.
     *
     * @return list of all files in the base workspace directory
     */
    public static ArrayList<File> getFilesFromWorkspace() {
        return getFilesFromWorkspace(null, null);
    }

    /**
     * Returns all files with the specified extension in the base workspace directory.
     *
     * @param extension extension of files to search for
     * @return list of all files with extension in the base workspace directory
     */
    public static ArrayList<File> getFilesFromWorkspace(EnumFileExtensions extension) {
        return getFilesFromWorkspace(extension, null);
    }

    /**
     * Returns all files in the specified subdirectory of the workspace.
     *
     * @param subdirectory directory within the workspace to search
     * @return list of all files in the specified workspace subdirectory
     */
    public static ArrayList<File> getFilesFromWorkspace(EnumWorkspaceSubdirectories subdirectory) {
        return getFilesFromWorkspace(null, subdirectory);
    }

    /**
     * Returns files with the specified extension within a subdirectory of the workspace. If no extension is specified,
     * this method returns all files. If no subdirectory is specified, returns files within the base workspace
     * directory.
     *
     * @param extension extension of files to search for
     * @param subdirectory directory within the workspace to search
     * @return list of files with extension within subdirectory
     */
    public static ArrayList<File> getFilesFromWorkspace(
            EnumFileExtensions extension,
            EnumWorkspaceSubdirectories subdirectory)
            throws IllegalArgumentException {

        File workspaceDirectory = new File(WorkspacePanel.getWorkspaceFilePath() + subdirectory);

        ArrayList<File> fileList = new ArrayList<>();
        File[] files = workspaceDirectory.listFiles();

        if (files == null) {
            return fileList;
        }

        return new ArrayList<>(Arrays.stream(files)
                .filter((file) -> {
                    if (extension == null) {
                        return false;
                    } else if (file.isDirectory()) {
                        return false;
                    } else {
                        return getFileExtension(file).equals(extension.toString());
                    }
                })
                .toList());
    }

    /**
     * Returns the extension of a file. If the file has no extension, returns the file name. If the file has multiple
     * . characters in the name, returns the sub-string of the file name after the final . character.
     * 
     * @param file File to extract the extension from
     * @return extension of file if it exists; file name if not
     */
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        String[] fileNameComponents = fileName.split("\\.");
        return fileNameComponents[fileNameComponents.length - 1];
    }
}
