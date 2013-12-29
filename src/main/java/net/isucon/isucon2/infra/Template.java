package net.isucon.isucon2.infra;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * テンプレート指定アノテーション
 *
 * @see http://d.hatena.ne.jp/backpaper0/20121231/1356964206
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Template {

    String value();
}
