package jp.groupsession.v2.newplugin.helloworld;

  import java.sql.Connection;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import jp.groupsession.v2.struts.AbstractGsAction;
  import org.apache.struts.action.ActionForm;
  import org.apache.struts.action.ActionForward;
  import org.apache.struts.action.ActionMapping;

  /**
   * <br>[機  能] HelloWorldのアクションクラス
   * <br>[解  説]
   * <br>[備  考]
   *
   * @author JTS
   */
  public class HelloWorldAction extends AbstractGsAction {

      /**
       * <br>[機  能] アクションを実行する
       * <br>[解  説]
       * <br>[備  考]
       * @param map ActionMapping
       * @param form ActionForm
       * @param req HttpServletRequest
       * @param res HttpServletResponse
       * @param con DB Connection
       * @return ActionForward
       * @throws Exception 実行時例外
       */
      public ActionForward executeAction(ActionMapping map,
                                          ActionForm form,
                                          HttpServletRequest req,
                                          HttpServletResponse res,
                                          Connection con)
          throws Exception {

          return map.getInputForward();
      }
  }
