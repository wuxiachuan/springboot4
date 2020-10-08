package com.springboot.Controller;

import com.springboot.dao.QRcodeDao;
import com.springboot.domain.FileContent;
import com.springboot.domain.Result;
import com.springboot.domain.SearchWheelParam;
import com.springboot.domain.WheelInfo;
import com.springboot.service.WheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpHeaders;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wheelTakein")
public class WheelTakeInController {
    @Autowired
    private WheelService wheelService;
    @Autowired
    private QRcodeDao qRcodeDao;

    private SimpleDateFormat dateFormater;

    public WheelTakeInController(){
         this.dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @RequestMapping("/addWheel")
    @ResponseBody
    public Result addWheel(@RequestBody WheelInfo wheelInfo){
        wheelInfo.setInfoTakeFinishTime(dateFormater.format(new Date()));
        WheelInfo result = null;
        String path = null;
        try{
            result = wheelService.insertWheelInfo(wheelInfo);
            path = wheelService.generateQRcode(result.getWheelId().toString());
        }catch(Exception e){
            System.out.println(e);
            return new Result(null,"添加失败",101);
        }
        return new Result(result,"添加成功",100);
    }

    @RequestMapping("/sesrchinfo")
    @ResponseBody
    public Result findWheelInfo(@RequestBody SearchWheelParam param){
        List<WheelInfo> list = null;
        System.out.println(param);
        try{
            list = wheelService.findWheelInfo(param);
        }catch (Exception e){
            return new Result(null,"查询失败",100);
        }
         return new Result(list,"查询成功",100);
    }
    @RequestMapping("/modifyWheel")
    @ResponseBody
    public Result modifyWheel(@RequestBody WheelInfo wheelInfo){
        wheelInfo.setInfoTakeFinishTime(dateFormater.format(new Date()));
        WheelInfo result = null;
        try{
            result = wheelService.updateWheelInfo(wheelInfo);
        }catch(Exception e){
            return new Result(null,"修改失败",101);
        }
        return new Result(result,"修改成功",100);
    }
    @RequestMapping("/deleteWheel")
    @ResponseBody
    public Result deleteWheel(String id){
        try{
            wheelService.deleteWheelInfo(id);
        }catch(Exception e){
            return new Result(null,"删除失败",101);
        }
        return new Result(null,"删除成功",100);
    }
    @RequestMapping("/getQRcode")
    @ResponseBody
    public Result generateQRcode(String id){
        String path = null;
        path = qRcodeDao.findQRcode(Integer.parseInt(id));
        try {
            if (path == null){
                path = wheelService.generateQRcode(id);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Result(null,"二维码生成",101);
        }
        return new Result(path,"二维码生成",100);
    }
//    @RequestMapping("/read/image")
//    public ResponseEntity<byte[]> getImageContent(HttpServletRequest request){
//        HttpHeaders responseHeaders = new HttpHeaders();
//
//        String imageUrl=request.getParameter("imageUrl");
//        FileContent file= FileContent.getImageByte(imageUrl);
//
//        responseHeaders.set("Access-Control-Allow-Origin","*");
//        responseHeaders.set("Content-Type",file.getContent_type());
//
//        return new ResponseEntity<>(file.getContent(), responseHeaders, HttpStatus.OK);
//    }
}
