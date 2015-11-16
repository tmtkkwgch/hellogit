package jp.co.sjts.util.loader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 指定されたパスをclassPathに追加する
 * <br>[解  説]
 * <br>[備  考] 使用できるClassLoaderはURLClassLoaderの派生クラスに限定される。
 *
 * @author JTS
 */
public class ClasspathHacker {
    /** ロギングクラス */
    private static Log log__ = LogFactory.getLog(ClasspathHacker.class);

    /** メソッドの仮パラメータ型を宣言順に識別するClassオブジェクトの配列 */
    @SuppressWarnings("all")
    private static final Class[] PARAMETERS = new Class[] {URL.class};

    /**
     * <br>[機  能] 指定されたjarパスをclassPathに追加する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param loader クラスローダ
     * @param addPath 追加するパス
     * @param jarFileName jarファイルネーム
     * @throws IOException 入出力時例外
     */
    public static void addFile(ClassLoader loader, String addPath, String jarFileName)
        throws IOException {

        String jarPath =
            "jar:file:"
            + File.separator
            + addPath
            + File.separator
            + jarFileName
            + "!/";

        URL url = new URL(jarPath);
        addURL(loader, url);
    }

    /**
     * <br>[機  能] 指定されたclassパスをclassPathに追加する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param loader クラスローダ
     * @param addPath 追加するパス
     * @throws IOException 入出力時例外
     */
    public static void addFile(ClassLoader loader, String addPath)
        throws IOException {
        File file = new File(addPath);
        addFile(loader, file);
    }

    /**
     * <br>[機  能] 指定されたclassパスをclassPathに追加する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param loader クラスローダ
     * @param classFile 追加するパス
     * @throws IOException 入出力時例外
     */
    @SuppressWarnings("deprecation")
    public static void addFile(ClassLoader loader, File classFile)
        throws IOException {
        addURL(loader, classFile.toURL());
    }

    /**
     * <br>[機  能] 指定されたパスをclassPathに追加する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param loader クラスローダ
     * @param url 追加するURL
     * @throws IOException 入出力時例外
     */
    @SuppressWarnings("unchecked")
    public static void addURL(ClassLoader loader, URL url)
        throws IOException {

        if (!(loader instanceof java.net.URLClassLoader)) {
            log__.info("ロードできないクラスローダ : " + loader.getClass().getName());
            return;
        }

        //URLClassLoader
        URLClassLoader sysloader = (URLClassLoader) loader;
        @SuppressWarnings("all")
        Class sysclass = URLClassLoader.class;
        try {
            log__.debug("クラス名: = " + sysloader.getClass().getName());
            Method method = sysclass.getDeclaredMethod("addURL", PARAMETERS);
            method.setAccessible(true);
            method.invoke(sysloader, new Object[] {url});

        } catch (Throwable t) {
            //t.printStackTrace();
            log__.error("クラスパスの追加に失敗", t);
            throw new IOException("IOException");
        }
    }
}