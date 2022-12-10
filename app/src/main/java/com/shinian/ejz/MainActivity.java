package com.shinian.ejz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileOutputStream;
import android.content.Context;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		//文件私有目录路径
		File file=new File("/data/data/com.shinian.ejz/files/c");
        //文件不存在则移动进去
        if (!file.exists())
            try {
                //输入流 获取assets里的文件
                InputStream input=getResources().getAssets().open("c");
                //输出流
                FileOutputStream out=this.openFileOutput("c", Context.MODE_PRIVATE);
                byte[] b=new byte[input.available()];
                input.read(b);
                out.write(b);
                //关闭
                input.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        //设置用户组可执行权限760 默认文件权限660
        //file.setExecutable(true);        
        // /system/bin/chmod 777
        try {
            execCommand("chmod 777 /data/data/com.shinian.ejz/files/c");
            Toast.makeText(this, "已设置" + file + "为777权限", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, "权限设置失败", Toast.LENGTH_SHORT).show();
        }
        
    }
    
    public void go_ejz(View v){
        try {
            boolean i =execCommand("/data/data/com.shinian.ejz/files/c");
            Toast.makeText(this, "执行二进制状态：" + i + "\n执行成功将会在存储目录生成txt文件", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "执行失败", Toast.LENGTH_SHORT).show();
        
        }
    }
    
    
    //java中执行shell命令
    public boolean execCommand(String command) throws IOException{
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec(command);
        //Process po = Runtime.getRuntime().exec(cn);
        //如果失败请打印此信息
        //InputStream input=proc.getInputStream(); 获得执行信息
        //InputStream input=proc.getErrorStream(); //获得错误信息
        //弹出执行的参数
        Toast.makeText(this,proc.toString(), Toast.LENGTH_SHORT).show();
        try{
            if (proc.waitFor() != 0) {
                System.err.println("exit value = "+ proc.exitValue());
                return false;
            }
          } catch (InterruptedException e) {
            System.err.println(e);
            return false;
        }
        return true;
    }
    
    
    
}
