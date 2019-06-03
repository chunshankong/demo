package rpa.compensate;

import com.alibaba.fastjson.JSON;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import rpa.compensate.window.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yangsiguo
 * @date 2019/6/1
 * @desc TODO add description in here
 */
public class CompensateJob implements Job {

    private static volatile boolean processed = false;

    public static synchronized boolean isProcessed() {
        return processed;
    }

    public static synchronized void setProcessed(boolean processed) {
        CompensateJob.processed = processed;
        Frame.callJobProcessed(processed);
    }

    private static volatile int totalJobCount = 0;//总数
    private static volatile int jobCount = 0;//当前行数

    public static int getTotalJobCount() {
        return totalJobCount;
    }

    public static void setTotalJobCount(int totalJobCount) {
        CompensateJob.totalJobCount = totalJobCount;
        Frame.callTotalJobCount(CompensateJob.getTotalJobCount());
    }

    public static int getJobCount() {
        return jobCount;
    }

    public static void setJobCount(int jobCount) {
        CompensateJob.jobCount = jobCount;
        Frame.callJobCount(CompensateJob.getJobCount());
    }

    public static void execute() {

        try {

            if (CompensateJob.isProcessed()) {
                System.out.println("任务处理中，请稍后再试");
                return;
            }
            System.out.println("定时任务开始执行");
            CompensateJob.setProcessed(true);

            List<Map<String, String>> list = DBService.getCompensateData(State.FAILED.getValue());
            CompensateJob.setTotalJobCount(list.size());
            System.out.println(JSON.toJSONString(list));

            List<String> successIds = new ArrayList<>();
            List<String> failedIds = new ArrayList<>();
            for (Map<String, String> map : list) {

                CompensateJob.setJobCount(CompensateJob.getJobCount() + 1);
                boolean success = IE.modifyPage(map);
                String id = map.get("id");
                System.out.println(id);
                System.out.println(success);

            }


            //全部修改成功，点击代偿任务
            executeClickJob();

        } catch (Throwable t) {
            t.printStackTrace();
        }

        CompensateJob.setProcessed(false);
        System.out.println("定时任务执行结束");
    }

    public static void executeClickJob() {
        IE.executeClickJob();
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        execute();

    }

}
