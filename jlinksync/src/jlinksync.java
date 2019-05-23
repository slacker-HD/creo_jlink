import com.ptc.pfc.pfcGlobal.pfcGlobal;
import com.ptc.pfc.pfcSession.Session;

public class jlinksync {
    public static void start() {
        try {
            Session session = pfcGlobal.GetProESession();
            session.UIShowMessageDialog("利用对话框停止下面的代码运行。\n现在可以使用java调试器连接会话开始调试。", null);
            session.UIShowMessageDialog("终于等到可以调试了", null);
        } catch (Exception e) {
        }
    }

    public static void stop() {
        try {
            Session session = pfcGlobal.GetProESession();
            session.UIShowMessageDialog("程序停止了", null);
        } catch (Exception e) {
        }
    }
}