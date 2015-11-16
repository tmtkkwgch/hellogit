package jp.co.sjts.util.lang;


/**
 * <br>[機  能] Java Classに関するユーティリティ
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ClassUtil {


    /**
     * <br>[機  能] クラス名よりオブジェクトを生成します。
     * <br>[解  説]
     * <br>[備  考]
     * @param className クラス名
     * @return インスタンス化したオブジェクト
     * @throws ClassNotFoundException クラスが見つからなかった場合にスローする
     * @throws InstantiationException クラスがインタフェースまたは abstract クラスの場合にスロー
     * @throws IllegalAccessException インスタンスの生成に失敗した場合にスロー
     */
    public static Object getObject(String className)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException {

        @SuppressWarnings("all")
        Class cls = Class.forName(className);
        return cls.newInstance();
    }
}
