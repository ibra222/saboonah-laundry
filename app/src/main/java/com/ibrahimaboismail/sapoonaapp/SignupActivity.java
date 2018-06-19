package com.ibrahimaboismail.sapoonaapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fourhcode.forhutils.FUtilsValidation;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ibrahimaboismail.sapoonaapp.AdminSelcte.SelcteDryClensonMap;
import com.ibrahimaboismail.sapoonaapp.ListAdmin.Admins;
import com.ibrahimaboismail.sapoonaapp.ListAdmin.MainReaponseListAdmin;
import com.ibrahimaboismail.sapoonaapp.Models.Cart.MainCartResponse;
import com.ibrahimaboismail.sapoonaapp.Models.MainSingupResponse;
import com.ibrahimaboismail.sapoonaapp.Models.User;
import com.ibrahimaboismail.sapoonaapp.Utils.Session;
import com.ibrahimaboismail.sapoonaapp.WebService.WebService;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d_200 on 1/26/2018.
 */

public class SignupActivity extends AppCompatActivity {

    Button singup;
    EditText username,password,mobile,address;
    String usernamev,passwordv,addressv;
    private Integer root_id = 0;
    SweetAlertDialog rgstar;
    int mobilev;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        final Spinner adminlist = (Spinner) findViewById(R.id.adminlist);

        WebService.getInstance().getApi().getAdmin().enqueue(new Callback<MainReaponseListAdmin>() {
            @Override
            public void onResponse(Call<MainReaponseListAdmin> call, Response<MainReaponseListAdmin> response) {
                final List<Admins>admins = response.body().getData();
                List<String> listSpinner = new ArrayList<String>();
                for (int i = 0; i < admins.size(); i++){
                    listSpinner.add(admins.get(i).getName());
                }
                // Set hasil result json ke dalam adapter spinner
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignupActivity.this,
                        android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adminlist.setAdapter(adapter);
                adminlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(SignupActivity.this,admins.get(position).getName(), Toast.LENGTH_SHORT).show();

                        root_id = admins.get(position).getId();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
               // ArrayAdapter<Admins>adminsArrayAdapter =
                //setadapter(adminlist,admins);

            }

            @Override
            public void onFailure(Call<MainReaponseListAdmin> call, Throwable t) {

            }
        });




        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        mobile = findViewById(R.id.mobile);
        address = findViewById(R.id.address);

        singup = findViewById(R.id.singup);
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!FUtilsValidation.isEmpty(username,"ادخل اسم المستخدم")
                     && !FUtilsValidation.isEmpty(password,"ادخل كلمة المرور")
                     && !FUtilsValidation.isEmpty(mobile,"ادخل رقم الجوال")
                     && !FUtilsValidation.isEmpty(address,"ادخل العنوان ")){

                    usernamev = username.getText().toString();
                    passwordv = password.getText().toString();
                    mobilev = Integer.valueOf(mobile.getText().toString());
                    addressv = address.getText().toString();

                    // Toast.makeText(SignupActivity.this, usernamev+ ' '+mobilev+ ' '+passwordv+ ' '+addressv, Toast.LENGTH_SHORT).show();
                    final SweetAlertDialog pDialog = new SweetAlertDialog(SignupActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Loading");
                    pDialog.setCancelable(false);
                    pDialog.show();
                    WebService.getInstance().getApi().singup(usernamev,mobilev,root_id,addressv,passwordv).enqueue(new Callback<MainSingupResponse>() {
                        @Override
                        public void onResponse(Call<MainSingupResponse> call, Response<MainSingupResponse> response) {
                            //Toast.makeText(SignupActivity.this, response.body().getMessage().get(0).toString(), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(SignupActivity.this, response.body().getData().toString(), Toast.LENGTH_SHORT).show();
                            pDialog.dismiss();
                            User user = new User();
                            user = response.body().getData();
//                            patient.setIdNumber(Integer.parseInt(response.body().getData().getIdNumber().toString()));
//                            patient.setEmail(response.body().getData().getEmail());
//                            patient.setPassword(response.body().getData().getPassword());
                            Session.getInstance().loginUser(user);
                            FirebaseMessaging.getInstance().subscribeToTopic("test");
                            FirebaseInstanceId.getInstance().getToken();
                            regusertoken(FirebaseInstanceId.getInstance().getToken(),Session.getInstance().getUser().getId(),Session.getInstance().getUser().getRoot_id());

                            sucssesregstar();

                            Intent intent = new Intent(SignupActivity.this,SelcteDryClensonMap.class);
                            startActivity(intent);

                            desmasreges(rgstar);
                        }

                        @Override
                        public void onFailure(Call<MainSingupResponse> call, Throwable t) {

                        }
                    });


                }

//                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
//                startActivity(intent);
            }
        });
    }

    private void regusertoken(String token, int user_id,int root_id){

        WebService.getInstance().getApi().adds(user_id,token,root_id).enqueue(new Callback<MainCartResponse>() {
            @Override
            public void onResponse(Call<MainCartResponse> call, Response<MainCartResponse> response) {
                //Toast.makeText(SignupActivity.this, "ok", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MainCartResponse> call, Throwable t) {

            }
        });

    }

    private void sucssesregstar(){
        rgstar = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        rgstar.setTitleText("تهانينا");
        rgstar.setContentText("تم تسجيل بياناتك في النظام الخاص بينا");
        rgstar.show();
    }

    private void setadapter(Spinner spinner,List<Admins> admins){
        ArrayAdapter<Admins> dataAdapter = new ArrayAdapter<Admins>(this, android.R.layout.simple_spinner_dropdown_item,admins);
        spinner.setAdapter(dataAdapter);
    }

    private void desmasreges(SweetAlertDialog sweetAlertDialog){
        sweetAlertDialog.dismiss();
    }
}

