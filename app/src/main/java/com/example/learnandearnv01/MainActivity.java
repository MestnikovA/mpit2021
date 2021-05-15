package com.example.learnandearnv01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnandearnv01.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn, btnRegister;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    String profileName;
    String profileMail;



    androidx.constraintlayout.widget.ConstraintLayout root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = findViewById(R.id.btnSignIn);
        btnRegister = findViewById(R.id.btnRegister);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        profileName = new String("");
        profileMail = new String("");



        root = findViewById(R.id.root_element);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignInWindow();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowRegisterWindow();
            }
        });

    }

    private void showSignInWindow() {

        AlertDialog.Builder dialoglogin = new AlertDialog.Builder(this);
        dialoglogin.setTitle("Войти");
        dialoglogin.setMessage("Введите данные для входа");

        LayoutInflater inflater = LayoutInflater.from(this);
        View sign_in_window = inflater.inflate(R.layout.sign_in_window,null);
        dialoglogin.setView(sign_in_window);

        final MaterialEditText loginmail = sign_in_window.findViewById(R.id.logInMail);

        final MaterialEditText loginpass = sign_in_window.findViewById(R.id.logInPass);


        dialoglogin.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface diCancel, int i) {
                diCancel.dismiss();
            }
        });
        dialoglogin.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(TextUtils.isEmpty(loginmail.getText().toString())){
                    Snackbar.make(root, "Введите вашу почту!", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(loginpass.getText().toString().length()<5){
                    Snackbar.make(root, "Введите пароль, длиннее 5 символов.", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(loginmail.getText().toString(), loginpass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                DatabaseReference dbUsers = FirebaseDatabase.getInstance().getReference("Users");
                                DatabaseReference userId = dbUsers.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                DatabaseReference userType = userId.child("type");
                                DatabaseReference userName = userId.child("name");
                                DatabaseReference userMail = userId.child("mail");
                                final String[] myType = {""};
                                final String[] myName = {""};
                                userName.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        Log.d("МОЙ NAME", String.valueOf(task.getResult().getValue()));
                                        profileName = String.valueOf(task.getResult().getValue());
                                    }
                                });
                                userMail.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        Log.d("МОЙ NAME", String.valueOf(task.getResult().getValue()));
                                        profileMail = String.valueOf(task.getResult().getValue());
                                    }
                                });

                                userType.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        Log.d("МОЙ ТИП", String.valueOf(task.getResult().getValue()));


                                        Intent intent = new Intent(MainActivity.this, WorkerActivityNav.class);
                                        intent.putExtra("PROFILE_NAME", profileName);
                                        intent.putExtra("PROFILE_MAIL", profileMail);
                                        startActivity(intent);

                                    }
                                });

                                //startActivity(new Intent(MainActivity.this, Entered.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(root, "Ошибка" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });

            }
        });


        dialoglogin.show();


    }

    private void ShowRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Введите данные для регистрации");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.register_window,null);
        dialog.setView(register_window);

        final MaterialEditText mail = register_window.findViewById(R.id.registerMail);
        final MaterialEditText name = register_window.findViewById(R.id.registerName);
        final MaterialEditText surname = register_window.findViewById(R.id.registerSurname);
        final MaterialEditText pass = register_window.findViewById(R.id.registerPassword);
        final MaterialCheckBox order = register_window.findViewById(R.id.registerCheckBox);


        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton("Готово", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(TextUtils.isEmpty(mail.getText().toString())){
                    Snackbar.make(root, "Введите вашу почту!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(name.getText().toString())){
                    Snackbar.make(root, "Введите ваше имя!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(surname.getText().toString())){
                    Snackbar.make(root, "Введите вашу фамилию!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(pass.getText().toString().length()<5){
                    Snackbar.make(root, "Введите пароль, длиннее 5 символов.", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(mail.getText().toString(), pass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User();
                                user.setName(name.getText().toString());
                                user.setSurname(surname.getText().toString());
                                user.setPass(pass.getText().toString());
                                user.setMail(mail.getText().toString());
                                if(order.isChecked()){
                                    user.setType("1");
                                }
                                else{
                                    user.setType("0");
                                }

                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Snackbar.make(root, "Успешно!.", Snackbar.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(root, "Ошибка" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
            }
        });


        dialog.show();


    }
}