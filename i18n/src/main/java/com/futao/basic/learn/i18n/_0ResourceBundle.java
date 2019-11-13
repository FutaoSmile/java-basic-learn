package com.futao.basic.learn.i18n;

import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 国际化-i18n（其来源是英文单词 internationalization的首末字符i和n，18为中间的字符数）是“国际化”的简称
 * <p>
 * 可结合DataFormat，NumberFormat，MessageFormat来实现日期和货币的国际化
 *
 * @author dark
 * Created on 2019/11/13.
 */
@Slf4j
public class _0ResourceBundle {
    public static void main(String[] args) {

        Locale defaultLocale = Locale.getDefault();
        log.info("{}", defaultLocale.getCountry());
        log.info("{}", defaultLocale.getLanguage());

        ResourceBundle loginResourceBundle = ResourceBundle.getBundle("login", defaultLocale);
        log.info("{}", loginResourceBundle.getString("name"));
        log.info("{}", loginResourceBundle.getString("password"));
        log.info("{}", loginResourceBundle.getString("welcome"));

        System.out.println("-----------");

        loginResourceBundle = ResourceBundle.getBundle("login", Locale.US);
        log.info("{}", loginResourceBundle.getString("name"));
        log.info("{}", loginResourceBundle.getString("password"));
        log.info("{}", loginResourceBundle.getString("welcome"));

        System.out.println("-----------");

        loginResourceBundle = ResourceBundle.getBundle("login", Locale.JAPAN);
        log.info("{}", loginResourceBundle.getString("name"));
        log.info("{}", loginResourceBundle.getString("password"));
        log.info("{}", loginResourceBundle.getString("welcome"));

        System.out.println("-----------");

        loginResourceBundle = ResourceBundle.getBundle("login", Locale.ITALIAN);
        log.info("{}", loginResourceBundle.getString("name"));
        log.info("{}", loginResourceBundle.getString("password"));
        log.info("{}", loginResourceBundle.getString("welcome"));


    }
}
