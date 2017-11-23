package test.common.util;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import main.common.util.FileIO;

public class FileIOTest {
    
    @Test
    public void readURLTest() {
        String str = "./testdata";
        String url = "https://example/testdata/index.html";
        String expect = "momonamomona";
        assertEquals(expect, FileIO.getInstance().readURL(str, url));
    }
    
    @Test
    public void writeURLTest() throws IOException {
        String path = "./testdata";
        String url = "https://example/hoge/index.html";
        // 書き込み
        FileIO.getInstance().writeURL(url, path);
        // 読み込み
        File file = new File(path + "/" + "hoge");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String actual = br.readLine();
        br.close();
        // アサーション
        assertEquals(url, actual);
        // ファイルのお掃除をする
        file.delete();
    }

}
