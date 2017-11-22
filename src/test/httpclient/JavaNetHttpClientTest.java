package test.httpclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.httpclient.JavaNetHttpClient;

public class JavaNetHttpClientTest {
	
	/**
	 * htmlで終わるURLの場合、そのURLをそのまま返却する
	 */
	@Test
	public void shouldReturnSameURLWhenURLEndwithHTML() {
		String momonaBlogUrl = "https://ameblo.jp/angerme-ss-shin/theme-10097979200.html";
		String actual = JavaNetHttpClient.fetchInitialURL(momonaBlogUrl);
		assertEquals(actual, momonaBlogUrl);
	}
	
	/**
	 * そうでないURLの場合、htmlで補完する
	 */
	@Test
	public void shouldReturnSameURLWhenURLEndwithoutHTML() {
		String momonaBlogUrl = "https://ameblo.jp/angerme-ss-shin/";
		String actual = JavaNetHttpClient.fetchInitialURL(momonaBlogUrl);
		assertTrue(actual.endsWith("html"));
	}

	/**
	 * そうでないURLの場合、htmlで補完する
	 */
	@Test
	public void testGetInitialPath() {
		String momonaBlogUrl = "https://ameblo.jp/angerme-ss-shin/";
		String actual = JavaNetHttpClient.fetchInitialURL(momonaBlogUrl);
		assertTrue(actual.endsWith("html"));
	}

}
