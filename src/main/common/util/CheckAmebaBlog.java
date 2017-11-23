package main.common.util;

import java.util.regex.Pattern;

import main.constants.BlogEnum;

public class CheckAmebaBlog {
    
    public BlogEnum isAmebaBlog(String str){
        // アメブロの場合
        if(Pattern.compile("ameblo.jp").matcher(str).find()) {
            return BlogEnum.AMEBA;
        }
        // インスタグラムの場合
        if(Pattern.compile("www.instagram.com").matcher(str).find()) {
            return BlogEnum.INSTAGRAM;
        }
        // それ以外
        return BlogEnum.NONE;
    }
}
