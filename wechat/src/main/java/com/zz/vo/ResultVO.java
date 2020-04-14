package com.zz.vo;

import lombok.Data;

/**
 * @author 小蜜蜂
 */
@Data
public class ResultVO<T> {

    /** 结果.
     * */
    private Boolean success;

    /** 错误码. */
    private Integer errorCode;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T body;
}
