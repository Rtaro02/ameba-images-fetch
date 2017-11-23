package main.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import main.common.ameba.Ameba;
import main.common.util.CheckAmebaBlog;
import main.common.util.FetchAndSaveImage;
import main.constants.BlogEnum;
import main.httpclient.JavaNetHttpClient;

public class SaveImages {
    private static SaveImages singleton = new SaveImages();

    private SaveImages() {
    }

    public static SaveImages getInstance() {
        return singleton;
    }

    public void excecute(String initialUrl, String path, Integer upperLimit) {
        CheckAmebaBlog checkAmebaBlog = new CheckAmebaBlog();
        if(checkAmebaBlog.isAmebaBlog(initialUrl).equals(BlogEnum.AMEBA)) {
            // amebaの場合実行
            Ameba.getInstance().amebaExecute(initialUrl, path, upperLimit);            
        }
    }
}
