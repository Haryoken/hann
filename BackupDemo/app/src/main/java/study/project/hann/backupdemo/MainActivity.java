package study.project.hann.backupdemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {
    private EditText txtName;
    private static final int READ_BLOCK_SIZE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (EditText)findViewById(R.id.txtEditName);
        InputStream is = null;
        BufferedReader br = null;
        InputStreamReader isr = null;
        String str = null;
        try {
            is = this.getResources().openRawResource(R.raw.data);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            while((str = br.readLine()) !=null){
                txtName.setText(str);
                break;
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(br!=null){
                    br.close();
                }
                if(isr!=null){
                    isr.close();
                }
                if(is!=null){
                    is.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void clickSaveInternal(View view) throws IOException {
        String str = txtName.getText().toString();
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try{
            fos = openFileOutput("data.txt",MODE_APPEND);
            osw = new OutputStreamWriter(fos);
            osw.write(str);
            osw.flush();
            Toast.makeText(this, "SAVED",Toast.LENGTH_LONG).show();
            txtName.setText("");
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(osw !=null) {
                    osw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(fos!=null){
                fos.close();
            }
        }
    }
    public void clickLoadInternal(View view){
        FileInputStream fis = null;
        InputStreamReader isr=null;
        try{
            fis = openFileInput("data.txt");
            isr = new InputStreamReader(fis);
            char[] buffer = new char[READ_BLOCK_SIZE];
            String s = "Value: ";
            int charRead;
            while((charRead=isr.read(buffer)) >0){
                String reading = String.copyValueOf(buffer,0,charRead);
                s+=reading;
                buffer=new char[READ_BLOCK_SIZE];
            }
            txtName.setText(s);
            Toast.makeText(this,"LOAD SUCCESSFUL",Toast.LENGTH_LONG).show();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void clickSaveExternal(View view){
        String str = txtName.getText().toString();
        File sdCard = Environment.getExternalStorageDirectory();
        String realPath = Environment.getExternalStorageState();
        File directory = new File(realPath+"/MyFiles");
        directory.mkdir();
        File file = new File(directory,"myfile.txt");
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try{
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos);
            osw.write(str);
            osw.flush();
            Toast.makeText(this,"SAVED INTERNAL",Toast.LENGTH_LONG).show();
            txtName.setText("");
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                osw.close();
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void clickLoadExternal(View view){
        String str = txtName.getText().toString();
        File sdCard = Environment.getExternalStorageDirectory();
        String realPath = Environment.getExternalStorageState();
        File directory = new File(realPath+"/MyFiles");
        directory.mkdir();
        File file = new File(directory,"myfile.txt");
        FileInputStream fis = null;
        InputStreamReader isr= null;
        try{
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            char[] buffer = new char[READ_BLOCK_SIZE];
            String s ="Value: ";
            int charRead;
            while((charRead = isr.read(buffer)) >0){
                String reading = String.copyValueOf(buffer,0,charRead);
                s+=reading;
                buffer = new char[READ_BLOCK_SIZE];
            }
            txtName.setText(s);
            Toast.makeText(this,"LOAD SD CARD successfull.",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void clickCopyFile(View view){
        File sdCard = Environment.getExternalStorageDirectory();
        String realPath = sdCard.getAbsolutePath();
        File data = Environment.getDataDirectory();
        String desDir = realPath +"/MyFiles";
        File directory = new File(desDir);
        if(!directory.exists()){
            directory.mkdir();
        }
        String dataPath = "/data/"+this.getPackageName() +"/shared_prefs"; // /databases
        File dataDir = new File(data,dataPath);
        File[] fileList = dataDir.listFiles();
        String result ="Copied File: \n";
        if(fileList != null){
            for(int i = 0; i< fileList.length; i++) {
                File f = fileList[i];
                result += f.getName() +"\n";
                copyFile(f.getAbsolutePath(),desDir+"/"+f.getName());
            }
        }else{
            result +="File Not Found.";
        }
    }
    public void copyFile(String src, String destination){
        File srcFile = new File(src);
        File desFile = new File(destination);
        try{
            FileChannel srcChannel = new FileInputStream(srcFile).getChannel();
            FileChannel desChannel = new FileInputStream(desFile).getChannel();
            try{
                desChannel.transferFrom(srcChannel,0,srcChannel.size());
            }catch(IOException e){

            }finally {
                desChannel.close();
                srcChannel.close();
            }
        }catch(FileNotFoundException e) {

        }catch (IOException e){

        }
    }
}
