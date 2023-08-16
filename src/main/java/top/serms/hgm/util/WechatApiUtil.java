package top.serms.hgm.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.serms.hgm.pojo.AccessTokenResponse;
import top.serms.hgm.pojo.MgVideoNum;
import top.serms.hgm.pojo.vo.MgVideoNumVo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WechatApiUtil {


    // 调用微信API接口，获取access_token
    public static AccessTokenResponse requestAccessToken(String appId, String appSecret) {
        String wechatApiUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        String resp = sendGetRequest(wechatApiUrl, "GET");
        Gson gson = new Gson();
        return gson.fromJson(resp, AccessTokenResponse.class);
    }

    // 根据accessToken和dramaId，调用微信API接口，获取媒资列表数据
    public static List<MgVideoNumVo> requestMediaList(String accessToken, String dramaId) {
//        String pathUrl = "http://127.0.0.1:4523/m1/3084253-0-default/serms?access_token=" + accessToken + "drama_id=" + dramaId;
        String pathUrl = "https://api.weixin.qq.com/wxa/sec/vod/listmedia?access_token=" + accessToken + "drama_id=" + dramaId;
        String resp = sendGetRequest(pathUrl, "POST");
        System.out.println(resp);
        JSONObject entries = JSONUtil.parseObj(resp);
        JSONArray mediaInfoList = entries.getJSONArray("media_info_list");
        if (BeanUtil.isEmpty(mediaInfoList)) {
            return null;
        }
        List<MgVideoNumVo> mgVideoNumList = new ArrayList<>();
        // 遍历media_info_list数组，提取media_id值并存入List
        for (int i = 0; i < mediaInfoList.size(); i++) {
            MgVideoNumVo mgVideoNum = new MgVideoNumVo();
            JSONObject mediaInfo = mediaInfoList.getJSONObject(i);
            String mediaId = mediaInfo.getStr("media_id");
            Long expireTime = mediaInfo.getLong("expire_time");
            String getmedialink = getmedialink(accessToken, mediaId);
            String mp4Url = JSONUtil.parseObj(getmedialink).getJSONObject("media_info").getStr("mp4_url");
            mgVideoNum.setMediaId(mediaId);
            mgVideoNum.setMp4Url(mp4Url);
            mgVideoNum.setExpiresTime(expireTime);
            mgVideoNumList.add(mgVideoNum);
        }
        return mgVideoNumList;
    }

    // 获取媒体播放连接
    private static String getmedialink(String accessToken, String mediaId) {
//        https://api.weixin.qq.com/wxa/sec/vod/getmedialink?access_token=ACCESS_TOKEN
//        http://127.0.0.1:4523/m1/3084253-0-default/getmedialink
        String UrlPath = "https://api.weixin.qq.com/wxa/sec/vod/getmedialink?access_token=" + accessToken + "media_id=" + mediaId;
        return sendGetRequest(UrlPath, "POST");
    }

    private static String sendGetRequest(String urlStr, String type) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(type);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            conn.disconnect();

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
