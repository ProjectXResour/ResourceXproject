package xteam.resourcexproject.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import xteam.resourcexproject.R;
import xteam.resourcexproject.helper.ProgressDialogHelper;
import xteam.resourcexproject.helper.UIHelper;
import xteam.resourcexproject.util.WeakHandler;
import xteam.resourcexproject.util.XImageUtil;

public class MainActivity extends AppCompatActivity {
    private ProgressDialogHelper progressDialogHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialogHelper = ProgressDialogHelper.create(this, "发现新版本，正在更新资源", 0);
        progressDialogHelper.setCancelable(false);
        XImageUtil.decryptImageAndCopy(this, new XImageUtil.OnDecryptListener() {
            @Override
            public void onStart(int currentTask, int taskSumNum) {
                if (!progressDialogHelper.isShowing()) {
                    Message message = Message.obtain();
                    message.what = 0;
                    Bundle bundle = message.getData();
                    bundle.putString("title",
                                     "正在升级必须的资源...(" + currentTask + "/" + taskSumNum + ")");
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onProgress(int currentTask, int taskSumNum, int progress) {
                Message message = Message.obtain();
                message.what = 1;
                Bundle bundle = message.getData();
                bundle.putString("title", "正在升级必须的资源...(" + currentTask + "/" + taskSumNum + ")");
                bundle.putInt("progress", progress);
                handler.sendMessage(message);
            }

            @Override
            public void onSuccess() {
                Message message = Message.obtain();
                message.what = 2;
                handler.sendMessage(message);
            }
        });
    }


    private WeakHandler handler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            switch (msg.what) {
                case 0://onstart
                    if (bundle != null) {
                        String title = bundle.getString("title");
                        progressDialogHelper.setTitle(title);
                        progressDialogHelper.show();
                    }
                    break;
                case 1://onprogress
                    if (bundle != null) {
                        String title = bundle.getString("title");
                        progressDialogHelper.setTitle(title);
                        int progress = bundle.getInt("progress");
                        progressDialogHelper.setProgress(progress);
                    }
                    break;
                case 2://onsuccess
                    progressDialogHelper.dismiss();
                    UIHelper.ToastMessage("等待补充recyclerview的具体实现");
                    break;
            }
            return true;
        }
    });
}
