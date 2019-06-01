package rpa.compensate;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yangsiguo
 * @date 2019/6/1
 * @desc TODO add description in here
 */
public class CompensateJob {

    public static void execute(){

        List<Map<String,String>> list = DBService.getCompensateData(State.FAILED.getValue());
        System.out.println( JSON.toJSONString(list));

        List<String> successIds = new ArrayList<>();
        List<String> failedIds = new ArrayList<>();
        for (Map<String, String> map : list) {

            boolean success = IE.modifyPage(map);
            String id = map.get("id");
            System.out.println(id);
            System.out.println(success);

        }

        //全部修改成功，点击代偿任务
        IE.executeClickJob();


    }
}
