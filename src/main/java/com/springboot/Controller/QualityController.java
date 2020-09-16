package com.springboot.Controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dao.ProblemDao;
import com.springboot.dao.WheelDispatchDao;
import com.springboot.domain.Problem;
import com.springboot.domain.Result;
import com.springboot.domain.UserInfo;
import com.springboot.service.ProblemService;
import com.springboot.service.QualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/quality")
public class QualityController {
    @Autowired
    private QualityService qualityService;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    private WheelDispatchDao wheelDispatchDao;

    private SimpleDateFormat dateFormater;

    public QualityController(){
       this.dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    @RequestMapping("/modify")
    @ResponseBody
    public Result modify(@RequestBody Map<String,Object> map){
        String database = (String) map.get("database");
        Object data = map.get("data");
        System.out.println(database);
        qualityService.modifyInfo(database,data);
        return  new Result(null,"添加成功",100);
    }
    @RequestMapping("/addProblem")
    @ResponseBody
    public Result addProblem(@RequestBody Problem problem){
        problemDao.insertProblem(problem);
        return  new Result(problem,"添加成功",100);
    }
    @RequestMapping("/findProblemF")
    @ResponseBody
    public Result findProblemF(String finder){
        List<Problem> list = problemDao.findProblemByFinder(finder);
        return  new Result(list,"添加成功",100);
    }
    @RequestMapping("/findProblemCond")
    @ResponseBody
    public Result findProblemCond(@RequestBody Problem problem){
        List<Problem> list = problemService.findProblemByCond(problem);
        return  new Result(list,"添加成功",100);
    }
    @RequestMapping("/findProblemCondPage")
    @ResponseBody
    public Result findProblemCondPage(@RequestBody Problem problem,String page,String size){
        PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(size));
        List<Problem> list = problemService.findProblemByCond(problem);
        PageInfo res = new PageInfo(list);
        return  new Result(res,"添加成功",100);
    }
    @RequestMapping("/getProblems")
    @ResponseBody
    public Result getProblems( String worker){
        List<Problem> list = problemDao.findProblemByworker(worker);
        return  new Result(list,"添加成功",100);
    }
    @RequestMapping("/getProblemsById")
    @ResponseBody
    public Result getProblemsById( String id){
        List<Problem> list = problemDao.findProblemByWheelId(Integer.parseInt(id));
        return  new Result(list,"添加成功",100);
    }
    @RequestMapping("/resoveProblem")
    @ResponseBody
    public Result resoveProblem(String id){
        Date now = new Date();
        String time = dateFormater.format(now);
        problemDao.resoveProblem(Integer.parseInt(id),time);
        return  new Result(null,"整改成功",100);
    }
    @RequestMapping("/finishProblem")
    @ResponseBody
    public Result finishProblem(String id){
        Date now = new Date();
        String time = dateFormater.format(now);
        problemDao.finishProblem(Integer.parseInt(id),time);
        return  new Result(null,"整改成功",100);
    }

    @RequestMapping("/finishInspection")
    @ResponseBody
    public Result finishInspection(String name,String id){
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormater.format(new Date());
        wheelDispatchDao.finishInspection(name,Integer.parseInt(id),time);
        wheelDispatchDao.flushWheelInfoqualityInspectionFinish(Integer.parseInt(id));
        return  new Result(null,"整改成功",100);
    }
}