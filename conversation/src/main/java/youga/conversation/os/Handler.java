package youga.conversation.os;


/**
 * Created by YougaKing on 2017/3/17.
 */

public class Handler {


    public interface Callback {
        public boolean handleMessage(Message msg);
    }


    public Handler(Callback callback) {

    }

}
