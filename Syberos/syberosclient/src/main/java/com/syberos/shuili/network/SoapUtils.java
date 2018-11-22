package com.syberos.shuili.network;

import com.shuili.callback.RequestCallback;
import com.syberos.shuili.entity.userinfo.RoleExtInfo;
import com.syberos.shuili.entity.userinfo.RoleExtInfoList;
import com.syberos.shuili.entity.userinfo.UserExtendInfo;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/14.
 * 接口需要在线程中调用
 */

public class SoapUtils {
    private final String TAG = SoapUtils.class.getSimpleName();
  //  private final String strIp = "192.168.1.11:9080";
  // private final String strIp = "10.1.195.18";
    private final String strIp = "jdiasp.mwr.gov.cn";

    /** 命名空间. */
    private final  String NAME_SPACE = "http://userext.service.uumsext.dhcc.com.cn/";
    private final  String NAME_SPACE_EXT ="http://orgbaseext.service.uumsext.dhcc.com.cn/";
    private final  String NAME_SPACE_WIUN = "http://orgbasewiun.service.uumsext.dhcc.com.cn/";
    private final String NAME_SPACE_FOR_WATER = "http://uums.service.uums.dhcc.com.cn/";

    private final  String NAME_SPACE_WINU_EXT = "http://orgwiunext.service.uumsext.dhcc.com.cn/";

    /** WSDL文件的URL. 非水利用户*/
    private final  String WSDL_URL = "http://" + strIp + "/uams/ws/uumsext/UserExt?wsdl";
    /**
     * WSDL 文件的URL，水利用户
     */
    private final  String WSDL_URL_WATERINDUSTRY = "http://" + strIp + "/uams/ws/uums/?wsdl";
    private final  String WSDL_URL_EXT = "http://"+strIp +"/uams/ws/uumsext/OrgBaseExt?wsdl";
    private final  String WSDL_URL_WIUM = "http://"+strIp +"/uams/ws/uumsext/OrgBaseWiun?wsdl";

    private final String WSDL_URL_WIUM_EXT = "http://"+strIp +"/uams/ws/uumsext/OrgWiunExt?wsdl";
    private String strNameSpace;
    private String strWsdlUrl;
    private static SoapUtils instance;

    /**
     * wsdlType 根据该类型加载不同的WSDL_URL
     */
    public static enum SoapType{
        WSDL_BASE,
        WSDL_EXT,
        WSDL_WIUM,
        WSDL_BASE_FOR_WATER,
        WSDL_WIUM_EXT,
    }
    private SoapUtils(){

    }
    public static SoapUtils getInstance(){
        synchronized (SoapUtils.class){
            if(instance == null){
                instance = new SoapUtils();
            }
        }
        return instance;
    }

    /**
     * web service interface
     * @param params 请求参数
     * @param methodName  接口名称
     * @param callback 回调
     * @param type  wsdl类型
     */
    public void callWebService(HashMap<String,Object> params, String methodName, RequestCallback<Object> callback, SoapType type) {
        switch (type){
            case WSDL_BASE:
                strNameSpace = NAME_SPACE;
                strWsdlUrl = WSDL_URL;
                break;
            case WSDL_EXT:
                strNameSpace = NAME_SPACE_EXT;
                strWsdlUrl = WSDL_URL_EXT;
                break;
            case WSDL_WIUM:
                strNameSpace = NAME_SPACE_WIUN;
                strWsdlUrl = WSDL_URL_WIUM;
                break;
            case WSDL_BASE_FOR_WATER:
                strNameSpace = NAME_SPACE_FOR_WATER;
                strWsdlUrl = WSDL_URL_WATERINDUSTRY;
                break;
            case WSDL_WIUM_EXT:
                strNameSpace = NAME_SPACE_WINU_EXT;
                strWsdlUrl = WSDL_URL_WIUM_EXT;
        }
        // 命名空间+方法名称；
        String SOAP_ACTION = strNameSpace + methodName;
        // 创建SoapObject实例
        SoapObject request = new SoapObject(strNameSpace, methodName);
        // 生成调用web service 方法的soap请求消息
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        // 设置.net web service
        envelope.dotNet = false;
        envelope.encodingStyle = "UTF-8";
        // 请求参数
        if (params != null && !params.isEmpty()) {
            for(String key :params.keySet()){
                request.addProperty(key,params.get(key));
            }
        }
        // 发送请求
            envelope.setOutputSoapObject(request);
        (new MarshalBase64()).register(envelope);
        HttpTransportSE transport = new HttpTransportSE(strWsdlUrl);
        transport.debug = true;
        Object result = null;
        try {
            // web service请求
            transport.call(SOAP_ACTION, envelope);
            result =  envelope.getResponse();
            callback.sendResult(result);
        } catch (UnknownHostException ex) {
            //网络错误
            callback.sendResultFailed(-2);
        } catch (SoapFault soapFault) {
            // 用户名或密码错误
            callback.sendResultFailed(-3);
            soapFault.printStackTrace();
        } catch (XmlPullParserException e) {
            // xml 解析失败
            callback.sendResultFailed(-4);
            e.printStackTrace();
        } catch (IOException e) {
            // 网络错误
            callback.sendResultFailed(-2);
            e.printStackTrace();
        }
    }

    /**
     *
     * @param info 自定义结构
     * @param methodName 接口名称
     * @param callback 接口回调
     */
    public void updateUserInfo(UserExtendInfo info, String methodName, RequestCallback<UserExtendInfo> callback) {
        String SOAP_ACTION = NAME_SPACE + methodName;// 命名空间+方法名称；
        SoapObject request = new SoapObject(NAME_SPACE, methodName);// 创建SoapObject实例
        // 生成调用web service 方法的soap请求消息
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        // 设置.net web service
        envelope.dotNet = false;
     //   envelope.encodingStyle = "UTF-8";
        // 建立webservice连接对象
        HttpTransportSE transport = new HttpTransportSE(WSDL_URL);
        transport.debug = true;// 是否是调试模式
        PropertyInfo objekt = new PropertyInfo();
        objekt.setName("arg0");
        objekt.setValue(info);
        objekt.setType(info.getClass());
        request.addProperty(objekt);
        envelope.bodyOut = transport;
        envelope.setOutputSoapObject(request);// 设置请求参数
       // new MarshalDate().register(envelope);
        // new MarshalBase64().register(envelope);
        envelope.addMapping(NAME_SPACE,"RoleExtInfoList", RoleExtInfoList.class);
        envelope.addMapping(NAME_SPACE,"RoleExtInfo", RoleExtInfo.class);
       envelope.addMapping(NAME_SPACE, "UserExtendInfo", info.getClass());// 传对象时必须，参数namespace是webservice中指定的，
        try {
            transport.call(SOAP_ACTION, envelope);
            Object obj = envelope.getResponse();// 直接将返回值强制转换为已知对象
            if(callback != null) callback.sendResult((UserExtendInfo) obj);
        } catch (IOException e) {
            callback.sendResultFailed(-2);
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            callback.sendResultFailed(-4);
            e.printStackTrace();
        } catch (Exception ex) {
            callback.sendResultFailed(-3);
            ex.printStackTrace();
        }
    }

}
