package com.mhd.basekit.model.net;

import com.example.muheda.functionkit.netkit.http.ProcessingRules;
import com.example.muheda.functionkit.netkit.model.OriginalResultDto;
import com.mhd.basekit.viewkit.util.Common;
import com.mhd.basekit.viewkit.util.route.RouteConstant;
import com.mhd.basekit.viewkit.util.route.RouteUtil;
import com.muheda.mhdsystemkit.sytemUtil.functionutil.MMKVUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

/**
 * @author zhangming
 * @Date 2019/8/30
 * @Description: 包含接口返回的原始字符串等
 */
public class NengYuanResultDto implements ProcessingRules {

    @Override
    public <T> int processingRules(OriginalResultDto<T> resultDto) {
        try {
            String response = resultDto.getResponse();
            JSONObject jsonObject = null;

            jsonObject = new JSONObject(response);
            //jsonObject.optString("data").equals("[]");
            String code = jsonObject.optString("code");
            String message = jsonObject.optString("message");

            if ("300".equals(code)){
                Common.user=null;
                MMKVUtil.deleteKey("user_data");
                EventBus.getDefault().post("loginout");
                RouteUtil.routeSkip(RouteConstant.me_loginActivity, new String[][]{});
            }

//            resultDto.setCode(code);
//            resultDto.setMessage(code);
//            Object data = jsonObject.opt("data");
//            if (data != null) {
//                T t;
//                if (data instanceof JSONArray) {
//                    // 生成List<T> 中的 List<T>
//                    Type listType = new ParameterizedTypeImpl(List.class, new Class[]{resultDto.getmClass()});
//                    // 根据List<T>生成完整的Result<List<T>>
//                    Type type = new ParameterizedTypeImpl(ResultBean.class, new Type[]{listType});
//                    ResultBean resultBean = new Gson().fromJson(response, type);
//                    t = (T) resultBean.getData();
//                } else {
//                    t = (T) new Gson().fromJson(data.toString(), resultDto.getmClass());
//                }
//                resultDto.getCallBack().onSuccess(t);
//            } else {
//                resultDto.getCallBack().onNull(code, message);
//            }
        } catch (Exception e) {
            resultDto.getCallBack().onErrorOrExpection();
            e.printStackTrace();
        }
        return OriginalResultDto.NET_OTHER;
    }
}
