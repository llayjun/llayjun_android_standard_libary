package debug;

import com.example.common.base.BaseApplication;
import com.example.common.sutils.utils.SUtils;

public class ThirdTestApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        SUtils.initialize(this);
    }

}
