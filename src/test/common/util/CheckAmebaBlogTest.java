package test.common.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.common.util.CheckAmebaBlog;
import main.constants.BlogEnum;

public class CheckAmebaBlogTest {

    @Test
    public void should_return_true_when_url_is_ameba() {
        CheckAmebaBlog checkAmebaBlog = new CheckAmebaBlog();
        // 笠原桃奈ブログ
        String url = "https://ameblo.jp/angerme-ss-shin/theme-10097979200.html";
        assertEquals(checkAmebaBlog.isAmebaBlog(url), BlogEnum.AMEBA);
    }

    @Test
    public void should_return_true_when_url_is_not_ameba() {
        CheckAmebaBlog checkAmebaBlog = new CheckAmebaBlog();
        // 笠原桃奈ブログ
        String url = "https://amebloa.jp/angerme-ss-shin/theme-10097979200.html";
        assertEquals(checkAmebaBlog.isAmebaBlog(url), BlogEnum.NONE);
    }

    @Test
    public void should_return_true_when_url_is_instagram() {
        CheckAmebaBlog checkAmebaBlog = new CheckAmebaBlog();
        // 笠原桃奈ブログ
        String url = "https://www.instagram.com/explore/tags/%E4%B8%AD%E5%B3%B6%E6%97%A9%E8%B2%B4/?hl=ja";
        assertEquals(checkAmebaBlog.isAmebaBlog(url), BlogEnum.INSTAGRAM);
    }
}
