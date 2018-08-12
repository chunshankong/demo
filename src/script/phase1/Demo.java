package script.phase1;

import java.io.File;
import java.io.FileReader;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;

public class Demo {

    static ScriptEngineManager scriptEngineManager;
    static ScriptEngine engine;
    static Invocable jsinvoke;
    static{
        scriptEngineManager = new ScriptEngineManager();
        engine = scriptEngineManager.getEngineByName("javascript");//nashorn
        jsinvoke = (Invocable)engine;
    }

    //通过Java程序调用JS脚本中的函数
    static void callScript() throws Exception {

        //加载脚本 //定义函数
        FileReader hellowScript = new FileReader(new File("resources/hellow.js").getPath());
        engine.eval(hellowScript);

        //调用方法
        Object result = jsinvoke.invokeFunction("hellow",new Object[]{666});
        System.err.println(result);
        hellowScript.close();
    }

    public static void main(String[] args) throws Exception {

        callScript();

        //执行脚本
        FileReader alertScript = new FileReader(new File("resources/alert.js").getPath());
        engine.eval(alertScript);
        alertScript.close();

//        OpenGL.fillRect(100,200,500,500);

    }

    //实现alert函数，暴露给js脚本调用
    public static void alert(String message)throws Exception{

     /*   String mac = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
        String windows="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        UIManager.setLookAndFeel(windows);*/
        JOptionPane.showMessageDialog(null, message, "Java内核程序实现的alert对话框", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showDialog(String title,String message){
        Form.showDialog(title,message);
    }


}
