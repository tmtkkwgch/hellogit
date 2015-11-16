package jp.co.sjts.util;

import java.util.ArrayList;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ページング処理に関するユーティリティクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class PageUtil {

    /**
     * [機  能] ページから算出したページ開始行数を返します。<br>
     * [解  説]<br>
     * [備  考]<br>
     * @param page 表示ページ数
     * @param onePageLimit 1ページの最大件数
     * @return ページ開始行数
     */
    public static int getRowNumber(int page, int onePageLimit) {
        return ((page - 1) * onePageLimit) + 1;
    }

    /**
     * [機  能] ページコンボボックスの値を作成する。<br>
     * [解  説]<br>
     * [備  考]<br>
     * @param maxCount 作成する数
     * @param pageLimit 1ページの最大件数
     * @return optionの内容
     */
    public static ArrayList<LabelValueBean> createPageOptions(int maxCount, int pageLimit) {
        return createPageOptions((long) maxCount, pageLimit);
    }

    /**
     * [機  能] ページコンボボックスの値を作成する。<br>
     * [解  説]<br>
     * [備  考]<br>
     * @param maxCount 作成する数
     * @param pageLimit 1ページの最大件数
     * @return optionの内容
     */
    public static ArrayList<LabelValueBean> createPageOptions(long maxCount, int pageLimit) {
        int allPage = getPageCount(maxCount, pageLimit);
        ArrayList < LabelValueBean > lableList =
            new ArrayList < LabelValueBean >();
        for (int j = 0; j < allPage; j++) {
            int z = j + 1;
            lableList.add(new LabelValueBean(z + " / " + allPage, Integer.toString(z)));
        }
        return lableList;
    }

    /**
     * [機  能] 総ページ数を返す<br>
     * [解  説]<br>
     * [備  考]<br>
     * @param cnt データ件数
     * @param onePageLimit 1ページの最大件数
     * @return 総ページ数
     */
    public static int getPageCount(long cnt, int onePageLimit) {
        int ret = 1;
        double ptmp = Double.parseDouble(Long.toString(cnt)) / onePageLimit;
        if (ptmp <= 1) {
            ptmp = 1;
        } else {
            int itmp = (int) ptmp;
            if (itmp < ptmp) {
                itmp++;
            }
            ret = itmp;
        }
        return ret;
    }
}