package com.zz.vo;

/**
 * ResponseDataUtil:返回的工具类,主要是方便返回的写法
 * 单纯的返回工具类,直接放在这个包
 *
 * @author zhangxiaoxiang
 */
public class ResponseDataUtil {
    /**
     * 返回成功描述
     * @param msg 提示
     * @return ResponseData
     */
    public static ResponseData success(String msg){
        ResponseData responseData=new ResponseData();
        responseData.setCode(200);
        responseData.setMsg(msg);
        responseData.setData(null);
        return responseData;
    }

    /**
     * 返回成功描述和数据详情
     * @param msg 提示
     * @param data 数据
     * @return ResponseData
     */
    public static ResponseData success(String msg,Object data){
        ResponseData responseData=new ResponseData();
        responseData.setCode(200);
        responseData.setMsg(msg);
        responseData.setData(data);
        return responseData;
    }

    /**
     * 返回失败的带code的异常
     * @param code code
     * @param msg 提示
     * @return ResponseData
     */
    public static ResponseData failure(Integer code,String msg){
        ResponseData responseData=new ResponseData();
        responseData.setCode(code);
        responseData.setMsg(msg);
        responseData.setData(null);
        return responseData;
    }

    /**
     * 登录授权的异常
     * @param msg 提示
     * @return ResponseData
     */
    public static ResponseData authorizationFailed(String msg){
        ResponseData responseData=new ResponseData();
        responseData.setCode(401);
        responseData.setMsg(msg);
        responseData.setData(null);
        return responseData;
    }



    /**
     * 返回失败的描述信息
     * @param msg 提示
     * @return ResponseData
     */
    public static ResponseData failure(String msg){
        ResponseData responseData=new ResponseData();
        responseData.setCode(500);
        responseData.setMsg(msg);
        responseData.setData(null);
        return responseData;
    }

}
