package com.example.demo.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class ClassPath {

    public static String getResourceBasePath() {
        // Obtener el directorio
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            // nothing to do
        }
        if (path == null || !path.exists()) {
            path = new File("");
        }

        String pathStr = path.getAbsolutePath();
        // Si se está ejecutando en eclipse, está al mismo nivel que el directorio de destino. Si se implementa en el servidor, está al mismo nivel que el paquete jar por defecto
        pathStr = pathStr.replace("\\target\\classes", "");

        return pathStr;
    }
}
