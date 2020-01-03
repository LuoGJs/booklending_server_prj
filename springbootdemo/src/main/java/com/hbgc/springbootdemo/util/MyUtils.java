package com.hbgc.springbootdemo.util;


import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.mail.MailUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.web.multipart.MultipartFile;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class MyUtils {

    //生成验证码
    private static char[] ops = new char[]{'+', '-', '*'};

    //调用阿里云发送短信的接口实现用户注册时给用户发送验证码
    //阿里云短信调用接口
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAI4Fdi7tVZFnuUitZofmXi";
    static final String accessKeySecret = "oDCD62EWGoB3VdBJ5gbfO1FfgBQjh2";

    /**
     * + - *
     */

    //java.utils.Date转换为 String
    public static String utilsDateToString(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }


    //生成一个六位的验证码
    public static String createRandomCode() {

        int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);  //随机数生成验证码
        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

        return mobile_code + "";
    }

    //生成一个验证码图片(数学公式)

    //使用画板，验证码生成图片，放入redis后返回
    public static BufferedImage createVerifyCode(HttpSession session) {

        //生成图片的尺寸，宽度和高度
        int width = 80;
        int height = 32;

        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String verifyCode = generateVerifyCode(rdm);

        //把验证码存，计算结果保存到session;
        int rnd = calc(verifyCode);

        session.setAttribute("randomCodeResult", rnd);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(verifyCode + "=", 8, 24);
        g.dispose();

        //输出图片
        return image;
    }

    //计算结果
    public static int calc(String exp) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            return (Integer) engine.eval(exp);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String generateVerifyCode(Random rdm) {
        int num1 = rdm.nextInt(9);//0-8,不想要0；
        int num2 = rdm.nextInt(9);
        num1++; //1-9
        num2++;
        //int num3 = rdm.nextInt(10);
        char op1 = ops[rdm.nextInt(3)];
        //char op2 = ops[rdm.nextInt(3)];
        String exp = "" + num1 + op1 + num2;
        return exp; //返回了一个十以内的数学运算公式
    }

    public static String streamToStr(InputStream inputStream, String chartSet) {

        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, chartSet));
            String con;
            while ((con = br.readLine()) != null) {
                builder.append(con);
            }

            br.close();
            return builder.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    //给某个邮箱发送验证码
    public static void sendEmailValidateCode(String toEmail,String validateCode){
        MailUtil.send(toEmail, "欢迎注册河北工程大学山寨图书馆", validateCode, false);
    }


    //使用阿里云发送短信验证码
    /*
     * randomCode,表示要发送的验证码
     * mobile,表示要发送的手机号码
     * */
    public static void sendMessageCodeByAliyun(String randomCode, String mobile) {
        try {
            DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            IAcsClient client = new DefaultAcsClient(profile);

            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", mobile);
            request.putQueryParameter("SignName", "河北工程大学山寨图书馆");
            request.putQueryParameter("TemplateCode", "SMS_180357192");
            request.putQueryParameter("TemplateParam", "{\"code\":\""+randomCode+"\"}");
            try {
                CommonResponse response = client.getCommonResponse(request);
                System.out.println(response.getData());
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                e.printStackTrace();
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //实现密码加密功能，使用Bcrypt

    public static String encodePassword(String originPass){
        return DigestUtil.bcrypt(originPass);
    }

    //检查密码密码
    public static boolean checkPassword(String originPass,String hashPass){
        return DigestUtil.bcryptCheck(originPass,hashPass);
    }

    public static boolean emailFormat(String email) {
        boolean tag = true;
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            tag = false;
        }
        return tag;
    }


    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 145,147,149
     * 15+除4的任意数(不要写^4，这样的话字母也会被认为是正确的)
     * 166
     * 17+3,5,6,7,8
     * 18+任意数
     * 198,199
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
                "|(18[0-9])|(19[8,9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    //实现文件上传
    public static String uploadFile(MultipartFile file, String path) throws Exception{

        String fileName = file.getOriginalFilename(); //获得上传文件的文件名
        //使用UUID生成一个不重复的文件名字。
        String uploadFileName = createUUIDFileName(fileName);

        //先判断文件的父目录是否存在，如果不存在先创建目录
        File uploadFile = new File(path,uploadFileName);
        if(!uploadFile.getParentFile().exists()){
            uploadFile.getParentFile().mkdirs();
        }
        //在判断文件是否存在，如果存在先删除，再创建。
        if(uploadFile.exists()){
            uploadFile.delete();
        }
        uploadFile.createNewFile();

        file.transferTo(uploadFile); //也就是这一句话实现文件上传
        return uploadFile.getName();

    }

    //获取文件下载目录文件集合
    public static java.util.List<String> getDownloadFileList(String path){
        File downloadDir = new File(path);
        java.util.List<String> filesNameList = new ArrayList<String>();
        File[] files = downloadDir.listFiles();
        for(int i=0;i<files.length;i++){
            if(files[i].isFile()){
               filesNameList.add(files[i].getName());
            }
        }
        return filesNameList;
    }

    //获得上传成功后返回的UUID的文件名，避免用户之间重名的文件覆盖问题
    public static String createUUIDFileName(String fileName){
        String uuid =  UUID.randomUUID().toString().replace("-","");
        return uuid+fileName;
    }

    //实现文件上传到七牛云
    public static String uploadFileToQiniuCloud(MultipartFile file, String path){
        String fileName = file.getOriginalFilename(); //获得上传文件的文件名
        //使用UUID生成一个不重复的文件名字。
//        String uploadFileName = createUUIDFileName(fileName);

        //先判断文件的父目录是否存在，如果不存在先创建目录
        File uploadFile = new File(path,fileName);
        try{
            uploadFile.getParentFile().exists();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!uploadFile.getParentFile().exists()){
            uploadFile.getParentFile().mkdirs();
        }
//        System.out.println("新文件名1："+uploadFileName);
        //在判断文件是否存在，如果存在先删除，再创建。
        if(uploadFile.exists()){
            uploadFile.delete();
        }
        try {
            uploadFile.createNewFile();
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uploadFile.getName();
    }


    //获取操作系统的名称
    public static String getOperateSysName(){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            return "windows";
        }else{
            return "linux";
        }
    }



    public static void main(String[] args) {

        MyUtils.sendMessageCodeByAliyun("5201","18832057024");
    }

}
