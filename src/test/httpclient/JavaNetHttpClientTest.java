package test.httpclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import main.httpclient.JavaNetHttpClient;

public class JavaNetHttpClientTest {
    final String FILE_PATH = "./testdata/testdata.html";
    JavaNetHttpClient javaNetHttpClient = JavaNetHttpClient.getInstance();

    /**
     * htmlで終わるURLの場合、そのURLをそのまま返却する
     */
    @Test
    public void shouldReturnSameURLWhenURLEndwithHTML() {
        String momonaBlogUrl = "https://ameblo.jp/angerme-ss-shin/theme-10097979200.html";
        String actual = javaNetHttpClient.fetchInitialURL(momonaBlogUrl);
        assertEquals(actual, momonaBlogUrl);
    }

    /**
     * そうでないURLの場合、htmlで補完する
     */
    @Test
    public void shouldReturnSameURLWhenURLEndwithoutHTML() {
        String momonaBlogUrl = "https://ameblo.jp/angerme-ss-shin/";
        String actual = javaNetHttpClient.fetchInitialURL(momonaBlogUrl);
        assertTrue(actual.endsWith("html"));
    }

    /**
     * そうでないURLの場合、htmlで補完する
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws FileNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void testGetInitialPath() throws NoSuchMethodException, SecurityException, FileNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // メソッドの準備
        Method method = JavaNetHttpClient.class.getDeclaredMethod("getInitialPath", BufferedReader.class);
        method.setAccessible(true);
        File file = new File(FILE_PATH);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String actual = (String) method.invoke(javaNetHttpClient, br);
        assertEquals(actual, "https://ameblo.jp/angerme-ss-shin/entry-12330027077.html");
    }

}
