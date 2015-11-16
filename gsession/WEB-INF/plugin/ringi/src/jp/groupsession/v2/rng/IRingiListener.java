package jp.groupsession.v2.rng;

import jp.groupsession.v2.cmn.model.RequestModel;


/**
 * <br>[機  能] 稟議の申請、承認などが行われた場合に呼ばれるリスナー
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public interface IRingiListener {

    /**
     * <br>[機  能] 稟議申請時に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param model 稟議リスナーパラメータ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行時例外
     */
    public void doApplyRingi(RingiListenerModel model, RequestModel reqMdl) throws Exception;

    /**
     * <br>[機  能] 稟議承認時に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param model 稟議リスナーパラメータ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行時例外
     */
    public void doApprovalRingi(RingiListenerModel model, RequestModel reqMdl) throws Exception;

    /**
     * <br>[機  能] 稟議承認(完了)時に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param model 稟議リスナーパラメータ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行時例外
     */
    public void doApprovalCompleteRingi(RingiListenerModel model, RequestModel reqMdl)
    throws Exception;

    /**
     * <br>[機  能] 稟議却下時に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param model 稟議リスナーパラメータ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行時例外
     */
    public void doRejectRingi(RingiListenerModel model, RequestModel reqMdl) throws Exception;

    /**
     * <br>[機  能] 稟議差し戻し時に実行される
     * <br>[解  説]
     * <br>[備  考]
     * @param model 稟議リスナーパラメータ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行時例外
     */
    public void doReflectionRingi(RingiListenerModel model, RequestModel reqMdl)
    throws Exception;
}