package com.syberos.shuili.config;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.App;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.entity.common.AttachMentInfo;
import com.syberos.shuili.view.MultimediaView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/8/28.
 */

public class BusinessConfig {
    public static  int getOrgLevel(){
        int count = getNum(App.orgJurd);
        switch (count){
            case 12:  // 部级用户
                return 1;
            case 10:  // 省级用户
                return 2;
            case 8:  // 市级用户
                return 3;
            case 6:  // 县级用户
                return 4;
            default:
                return 1 ;
        }


    }

    private static int getNum(String src){
        int count = 0;
        char[] array = src.toCharArray();
        int size = array.length;
        for(int i = size -1 ; i >0; i--){
            if(String.valueOf(array[i]).equals("0")){
                count ++;
            }else {
                break;
            }
        }
        return count;


    }
    public static void getAttachMents(String bisGuid, String bisTableName, final MultimediaView multimediaView){
        final ArrayList<MultimediaView.LocalAttachment> attachments = new ArrayList<>();
        String url = GlobleConstants.strIP + "/sjjk/v1/jck/attMedBases/";
        HashMap<String,String> params = new HashMap<>();
        params.put("bisGuid",bisGuid);
        params.put("bisTableName",bisTableName);
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                AttachMentInfo attachMentInfo = gson.fromJson(result,AttachMentInfo.class);
                if(attachMentInfo != null && attachMentInfo.getData().size() > 0){

                    for(AttachMentInfo.DataBean item : attachMentInfo.getData()){
                        MultimediaView.LocalAttachment localAttachment = new MultimediaView.LocalAttachment();
                        if("2".equals(item.getMedType())) {
                            localAttachment.type = MultimediaView.LocalAttachmentType.IMAGE;
                        }else if("3".equals(item.getMedType())){
                            localAttachment.type = MultimediaView.LocalAttachmentType.AUDIO;
                        }else if("4".equals(item.getMedType())){
                            localAttachment.type = MultimediaView.LocalAttachmentType.VIDEO;
                        }else {
                            continue;
                        }
                        localAttachment.filePath = item.getMedPath();
                        localAttachment.bExist = true;
                        attachments.add(localAttachment);
                    }
                }
                multimediaView.setData(attachments);
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {

            }
        });
    }
}


