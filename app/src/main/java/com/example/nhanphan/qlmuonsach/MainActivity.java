package com.example.nhanphan.qlmuonsach;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView txtHoTen,txtMa,txtSachMuon,txtNgayMuon;
    RadioButton NL,TE;
    Button btThem,btthongke;
    ListView DanhSach;
    ArrayList items=new ArrayList();
    String chon="";
    double Tien=0,TongDoanhThu=0;
    int countDG=0,countNL=0;
    long diffngay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHoTen= (TextView) findViewById(R.id.txtHoTen);
        txtMa= (TextView) findViewById(R.id.txtMa);
        txtNgayMuon= (TextView) findViewById(R.id.txtNgayMuon);
        txtSachMuon= (TextView) findViewById(R.id.txtSachMuon);

        btThem= (Button) findViewById(R.id.button);
        btthongke= (Button) findViewById(R.id.btthongke);

        NL= (RadioButton) findViewById(R.id.rdNL);
        TE= (RadioButton) findViewById(R.id.rbTreem);

        DanhSach= (ListView) findViewById(R.id.DanhSach);

        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDG= countDG+1;
                ArrayAdapter<String> a=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,items);
                DanhSach.setAdapter(a);
                //xu lý ngày
                Date today=new Date(System.currentTimeMillis());//ngay hien tai
                SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
                String ngaymuon=txtNgayMuon.getText().toString();
                String ngaytra=format.format(today.getTime());
                try {
                    Date NgayMuon = format.parse(ngaymuon);
                    Date NgayTra = format.parse(ngaytra);
                    long ngay=NgayTra.getTime()-NgayMuon.getTime();
                    diffngay=ngay/(24*60*60*1000);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(NL.isChecked()==true) {
                    chon = " Người Lớn ";
                    Tien=diffngay * Double.parseDouble(txtSachMuon.getText().toString()) * 5000;
                    TongDoanhThu += Tien;
                    countNL++;
                }
                else {
                    chon = " Trẻ Em ";
                    Tien=diffngay * Double.parseDouble(txtSachMuon.getText().toString()) * 3000;
                    TongDoanhThu += Tien;
                }
                items.add(txtHoTen.getText()+" "+txtMa.getText()+" "+txtSachMuon.getText()+" "+txtNgayMuon.getText()+" "+Tien+"vnd "+chon);
            }
        });
        btthongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thống Kê").setMessage("Tổng Số ĐG: "+countDG +
                        "\n Tổng ĐGNL: "+countNL +
                        "\n Tổng doanh thu: "+TongDoanhThu)
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
            }
        });
    }
}