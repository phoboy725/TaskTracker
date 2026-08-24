package com.phoboy.tasktracker.exception;

public final class ExceptionMessage {

    private ExceptionMessage() {}

    public static final String EMAIL_EXISTS = "This email is already taken";
    public static final String RESOURCE_EXISTS = "Resource with this name already exists";
    public static final String DB_ERROR = "A database error occurred";
    public static final String MINIO_ERROR = "A MinIO error occurred";
    public static final String MINIO_INIT_ERROR = "A MinIO init error occurred";
    public static final String MINIO_DELETE_ERROR = "A MinIO delete object error";
    public static final String MINIO_COPY_ERROR = "A MinIO delete object error";
    public static final String MINIO_READ_ERROR = "A MinIO read from object error. Resource path: [{}]";
    public static final String MINIO_DOWNLOAD_ERROR = "A MinIO download object error. Resource path: [{}]";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String NOT_ENOUGH_SPACE = "Not enough space";
    public static final String RESOURCE_NOT_FOUND = "Resource not found";
    public static final String PASSWORD_INCORRECT = "Password is incorrect";
    public static final String EXTENSION_CHANGE_ERROR = "You can not change file extension! Sorry!";
    public static final String FOLDER_TOO_BIG = "The folder is too big to Zip it!";
    public static final String FOLDER_EMPTY = "The folder is empty. Nothing to download";
    public static final String FOLDER_SELF_PUT = "You can not put folder into itself";
    public static final String TEMP_FILE_DELETE_ERROR = "Could not delete temp file from path [{}]";
    public static final String TEMP_FILE_SAVE_ERROR = "Saving to temp file error";
    public static final String TEMP_FILE_CLEAN_ERROR = "Temp files cleaning error";
    public static final String ZIP_FILE_ERROR = "Failed to write resource entry to ZIP archive";
}
