package com.zz.util;


import com.zz.vo.ResultVO;

/**
 * @author 小蜜蜂
 */
public class ResultVoUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setErrorCode(-1);
        resultVO.setBody(object);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO answerSuccess(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setErrorCode(-1);
        resultVO.setBody(object);
        resultVO.setMsg("回答正确");
        return resultVO;
    }
    public static ResultVO answerError(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setErrorCode(-1);
        resultVO.setBody(object);
        resultVO.setMsg("回答错误");
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error (Integer code, String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.setErrorCode(code);
        resultVO.setMsg(msg);
        return  resultVO;
    }
}
