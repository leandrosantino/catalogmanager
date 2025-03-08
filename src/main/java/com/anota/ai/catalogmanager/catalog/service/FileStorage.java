package com.anota.ai.catalogmanager.catalog.service;

import java.io.IOException;

public interface FileStorage {
    void saveTextFile(String text, String filename) throws IOException;
    String readTextFile(String filename) throws IOException;
}
