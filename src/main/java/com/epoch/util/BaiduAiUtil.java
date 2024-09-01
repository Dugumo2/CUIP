package com.epoch.util;

import com.baidu.aip.contentcensor.AipContentCensor;
import com.baidu.aip.contentcensor.EImgType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class BaiduAiUtil {
    //设置APPID/AK/SK
    private static final String APP_ID = "98378511";
    private static final String API_KEY = "4cNTz1HzV7WYX8at7ZNNVIua";
    private static final String SECRET_KEY = "CJCwMusnKZPSUIPjsrRLMcT9W1Mk2hqU";

    // 初始化一个AipContentCensor
    private static AipContentCensor client = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);

    public JSONObject textCensor(String text) throws JSONException {
        // 调用接口 - 文本审核
        JSONObject res = client.textCensorUserDefined(text);
        return res;
    }

    public JSONObject imageCensor(String url) {
        // 调用接口 - 图片审核(图片url)
        JSONObject response = client.imageCensorUserDefined(url, EImgType.URL, null);
        return response;
    }

}