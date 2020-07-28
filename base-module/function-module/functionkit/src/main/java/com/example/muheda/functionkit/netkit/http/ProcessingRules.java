package com.example.muheda.functionkit.netkit.http;

import com.example.muheda.functionkit.netkit.model.OriginalResultDto;

/**
 * @author zhangming
 * @Date 2019/8/30
 * @Description: 允许调用者自动处理请求结果的接口
 */
public interface ProcessingRules {

    <T> int processingRules(OriginalResultDto<T> resultDto);

}
