package com.white.Controller;

import com.whit.JSON.JsonObject;
import com.whit.Socket.SocketBuildConfig.SocketBuild;
import com.whit.SocketResultApi.DataResultApi;
import com.white.Service.StudentService;
import com.white.Service.TeacherService;
import com.white.pojo.ClassTable;
import com.white.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/30 9:05
 */
@RestController
public class TestController {
    @Resource
    private StudentService service;
    @Resource
    private   TeacherService services;
    @Resource
    private ApplicationContext context;
    private final int MAX_THREAD= 10_000;
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/count/refresh")
    public  DataResultApi  refresh(Student student) {
        final SocketBuild bean = context.getBean(SocketBuild.class);
        bean.refreshAll();
        return  DataResultApi.resultSuc(200).msg(student.getStuName()).data(JsonObject.JSON(student));
    }

    /**
     * 查询分数
     *
     * @return 结果
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/count/selectScore")
    DataResultApi selectScore() {

        final Map<String, Object> map = new HashMap<>(4);
        map.put("stu_id", 1814040107);
        map.put("class_year", 2018);
        map.put("class_term", 1);
        map.put("class_name", null);
        return service.selectScore(map, 1, 10);
    }
    @RequestMapping(value = "/testConcurrency",method = {RequestMethod.GET,RequestMethod.PUT})
    public DataResultApi testConcurrency(){

         final CountDownLatch count=new CountDownLatch(MAX_THREAD);
         final CountDownLatch latch=new CountDownLatch(1);
        final DataResultApi api = DataResultApi.resultSuc(200);
        api.msg("测试并发");
        List<FutureTask> tasks=new LinkedList<>();
        for(int i=0;i<MAX_THREAD;i++){
            Callable<List<ClassTable>> callable=() -> {
                try {
                    count.countDown();
                    count.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return services.queryClass(10001);
            };
            FutureTask<List<ClassTable>> task=new FutureTask(callable);
            tasks.add(task );
           new Thread(task).start();
        }
        AtomicInteger index= new AtomicInteger();
        tasks.forEach(e->{
            try {
                api.data(String.valueOf(index.get()),e.get() );
                index.getAndIncrement();
            } catch (InterruptedException | ExecutionException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
        return api;
    }
}
