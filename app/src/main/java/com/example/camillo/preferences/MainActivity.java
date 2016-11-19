package com.example.camillo.preferences;

import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListener;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.name)
    protected EditText name;
    @BindView(R.id.surname)
    protected EditText nazwisko;
    @BindView(R.id.sex)
    protected RadioGroup mSex;
    @BindView(R.id.marketing)
    protected CheckBox agree;
    private ApplicationSetings setings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setings = new ApplicationSetings(this);

        name.setText(setings.getName());
        nazwisko.setText(setings.getSurname());
        agree.setChecked(setings.getAgree());
        if(setings.getSex()!= null){
            if(setings.getSex().equalsIgnoreCase("F") ) mSex.check(R.id.sexWomen);
            if(setings.getSex().equalsIgnoreCase("m") ) mSex.check(R.id.sexMen);
        }

    }

    @OnClick(R.id.save)
    protected void onSave(){
        setings.setName(name.getText().toString());
        setings.setSurname(nazwisko.getText().toString());
        setings.setAgree(agree.isChecked());
        setings.setSex(mSex.getCheckedRadioButtonId() == R.id.sexWomen ? "F" : "M");
    }

    @OnClick(R.id.read_internal)
    protected void onReadInternal()  {

        try {
            File internalFile = getInternalFile();
            reatFromFile(internalFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void reatFromFile(File internalFile) throws IOException {
        String readSting = FileUtils.readFileToString(internalFile);
        Toast.makeText(this,readSting,Toast.LENGTH_LONG).show();
    }


    @OnClick(R.id.write_internal)
    protected void onWriteInternal()  {
        File internalFile = null;
        try {
            internalFile = getInternalFile();
            writeToFile(internalFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void writeToFile(File internalFile) throws IOException {
        FileUtils.write(internalFile,
                setings.getName()+" "+
                        setings.getSurname()+" "+
                        setings.getSex());
    }

    @NonNull
    private File getInternalFile() throws IOException {
        File  internalFile =  new File(getFilesDir(),"userdata.txt");
        if(!internalFile.exists()){
                internalFile.createNewFile();

        }
        return internalFile;
    }


    private boolean IsExtrenalReadable(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY)
                || IsExtrenalWritable();
    }

    private boolean IsExtrenalWritable(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

    }

    @OnClick(R.id.write_external)
    protected void onWriteExternal(){
        if(IsExtrenalWritable())
        try{
            File appFolder2 = getExternalFile();
            if(!appFolder2.exists()){
                appFolder2.createNewFile();
            }

            writeToFile(appFolder2);

        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    @NonNull
    private File getExternalFile() {
        File appFolder = new File(Environment.getExternalStorageDirectory(),"Szkolenie");
        appFolder.mkdir();
        return new File(appFolder,"userdata.txt");
    }

    @OnClick(R.id.read_exsternal)
    protected void onReadExsternal(){

        if(IsExtrenalReadable()){

            File exstrernalFile = getExternalFile();
            try {
                reatFromFile(exstrernalFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
