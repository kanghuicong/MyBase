package com.kang.mybase.custom.other;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import static com.kang.mybase.util.httpClient.HttpRequest.getApi;


/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public class InitializeService extends IntentService {
    private static final String ACTION_INIT_WHEN_APP_CREATE = "kang";
    private static Context context;

    public InitializeService() {
        super(ACTION_INIT_WHEN_APP_CREATE);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit();
            }
        }
    }

    //初始化操作
    private void performInit() {
        getApi();
    }

    public static void start(Context _context) {
        context = _context;
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        _context.startService(intent);
    }

}

