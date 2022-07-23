package com.hoppinzq.service.service;

import com.hoppinzq.service.aop.annotation.ApiMapping;
import com.hoppinzq.service.aop.annotation.ApiServiceMapping;
import com.hoppinzq.service.config.WebSocketProcess;
import com.hoppinzq.service.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * @author: zq
 */
@ApiServiceMapping(title = "b站视频解析", description = "b站视频解析",roleType = ApiServiceMapping.RoleType.NO_RIGHT)
public class WebSocketBilibiliService {
    @Autowired
    private WebSocketProcess myWebSocket;

    @ApiMapping(value = "checkVideoResourceJson", title = "you-get查找资源", description = "you-get查找资源")
    public void checkVideoResourceJson(String url,String userno){
        String cmdJSON="you-get --json "+url;//--playlist
        String line = null;
        String uuid= UUIDUtil.getUUID();
        try {
            myWebSocket.sendCmdMessgae(cmdJSON,userno);
            myWebSocket.sendCmdMessgae("清等待。。。。。。",userno);
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(cmdJSON);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(),"UTF-8"));
            while ((line = bufferedReader.readLine()) != null) {
                myWebSocket.sendCmdMessgae(line,userno);
                System.out.println(line);
            }

            String os = System.getProperty("os.name").toLowerCase();
            String cmdDownLoad="you-get ";
            if(os.contains("windows")) {
                cmdDownLoad+="-o D:\\ -O "+uuid+" --force "+url;
            }else{
                cmdDownLoad+="-o /home/ -O "+uuid+" --force "+url;
            }
            myWebSocket.sendCmdMessgae("初始化中。。。",userno);
            myWebSocket.sendCmdMessgae("初始化成功",userno);
            process = runtime.exec(cmdDownLoad);
            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(process.getInputStream(),"UTF-8"));
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(process.getErrorStream(),"UTF-8"));
            while ((line = bufferedReader1.readLine()) != null) {
                if(line.indexOf("%")==4){
                    myWebSocket.sendCmdMessgae(line,userno);
                }
                System.out.println(line);
            }
            while ((line = bufferedReader2.readLine()) != null) {
//                myWebSocket.sendCmdMessgae(-1,line,"videoDownLoadError",llq_uuid_zq);
                //
                System.err.println(line);
                if(line.indexOf("already exists")!=-1){
                    System.err.println("文件已存在！");
                    myWebSocket.sendCmdMessgae(-4,"文件已存在！",userno);
                }else if(line.indexOf("This is a multipart video")!=-1){
                    myWebSocket.sendCmdMessgae(-5,"该链接中有多个视频！",userno);
                }else{
                    myWebSocket.sendCmdMessgae(-1,"下载失败！",userno);
                }
                process.destroy();
                return;
            }
            myWebSocket.sendCmdMessgae("下载成功！",userno);
            myWebSocket.sendCmdMessgae("由于带宽太低，不支持观看的视频，请拉代码自己尝试！",userno);
            myWebSocket.sendCmdMessgae("window系统的服务保存在D盘根目录下，Linux保存在/home下！",userno);
            myWebSocket.sendCmdMessgae("请注意：在项目中找到com.hoppinzq.service.WebSocketBilibiliService,然后将75~89行代码注释，我为了演示就没注释，你必须注释之，否则下载的文件会被立刻删除！",userno);
            myWebSocket.sendCmdMessgae("请注意：在项目中找到com.hoppinzq.service.WebSocketBilibiliService,然后将75~89行代码注释，我为了演示就没注释，你必须注释之，否则下载的文件会被立刻删除！",userno);
            myWebSocket.sendCmdMessgae("请注意：在项目中找到com.hoppinzq.service.WebSocketBilibiliService,然后将75~89行代码注释，我为了演示就没注释，你必须注释之，否则下载的文件会被立刻删除！",userno);
            myWebSocket.sendCmdMessgae("请注意：在项目中找到com.hoppinzq.service.WebSocketBilibiliService,然后将75~89行代码注释，我为了演示就没注释，你必须注释之，否则下载的文件会被立刻删除！",userno);
            myWebSocket.sendCmdMessgae("请注意：在项目中找到com.hoppinzq.service.WebSocketBilibiliService,然后将75~89行代码注释，我为了演示就没注释，你必须注释之，否则下载的文件会被立刻删除！",userno);

            if(os.contains("windows")) {
                File file = new File("D:\\"+uuid+".flv");
                boolean isF=file.delete();
                if(!isF){
                    file = new File("D:\\"+uuid+".mp4");
                    file.delete();
                }
            }else{
                File file = new File("/home/"+uuid+".flv");
                boolean isF=file.delete();
                if(!isF){
                    file = new File("/home/"+uuid+".mp4");
                    file.delete();
                }
            }
            myWebSocket.sendCmdMessgae("完毕",userno);
            process.destroy();

        } catch (Exception e) {
            myWebSocket.sendCmdMessgae(-1,"出错了！",userno);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
