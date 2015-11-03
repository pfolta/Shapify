package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by pf41 on 03/11/15.
 */
public class FileController {


    public Document loadDocumentFromFile(String path) throws IOException {
        Document document = null;

        List<String> read = Files.readAllLines(Paths.get(path));

        // TODO: parse file here

        document = new Document();

        return document;
    }

    public void saveDocument(Document document) throws IOException {
        Path path = document.getFile();

        // TODO: convert document to SVG and save

    }

}
