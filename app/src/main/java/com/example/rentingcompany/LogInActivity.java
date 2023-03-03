package com.example.rentingcompany;

import static com.example.rentingcompany.MainActivity.accountStatus;
import static com.example.rentingcompany.MainActivity.email;
import static com.example.rentingcompany.MainActivity.username;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rentingcompany.DataBase.DataBaseHelper;
import com.example.rentingcompany.DataBase.SHA;
import com.example.rentingcompany.SharedPrefrences.SharedPrefManager;
import com.example.rentingcompany.SignUpActivities.SignUpActivity;

import java.util.ArrayList;

public class LogInActivity extends AppCompatActivity {
    ArrayList<String> emailArrayList = new ArrayList();
    ArrayList<String> passwordArrayList = new ArrayList();
    ArrayList<String> status = new ArrayList();
    int emailExist, passwordExist, index = 0;
    int checkBoxFlag;
    float v =0;

    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        EditText emailText =
                (EditText) findViewById(R.id.emailPlainText);

        EditText passwordText =
                (EditText) findViewById(R.id.passwordPlainText);

        //animation fields
        emailText.setTranslationY(300);
        passwordText.setTranslationY(300);

        emailText.setAlpha(v);
        passwordText.setAlpha(v);

        emailText.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        passwordText.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();



        TextView emailError = (TextView) findViewById(R.id.emailErrorPlaintext);
        TextView passwordError = (TextView) findViewById(R.id.passwordErrorPlaintext);


        Button signInButton = (Button) findViewById(R.id.signInButton);
        Button signUpButton = (Button) findViewById(R.id.signUpButton);
        Button guestButton = (Button) findViewById(R.id.guestButton);

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);

        sharedPrefManager = SharedPrefManager.getInstance(this);
        checkBoxFlag = 0;

        emailText.setText(sharedPrefManager.readString("Email", ""));
        passwordText.setText(sharedPrefManager.readString("Password", ""));
        checkBox.setChecked(sharedPrefManager.readBoolean("RememberMe", false));

        if (sharedPrefManager.readBoolean("RememberMe", false))
            checkBoxFlag = 1;




        DataBaseHelper dataBaseHelper1 = new DataBaseHelper(LogInActivity.this, "EXP4", null, 1);
        Cursor allCustomersCursor = dataBaseHelper1.getAllSignInData();
        while (allCustomersCursor.moveToNext()) {

            System.out.println("\n\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + "\nemailAddress= " + allCustomersCursor.getString(0));
//                    + "\nagencyName= " + allCustomersCursor.getString(1)
//                    + "\npassword= " + allCustomersCursor.getString(2)+ "\n\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        }

        emailArrayList.removeAll(emailArrayList);
        passwordArrayList.removeAll(passwordArrayList);
        status.removeAll(status);



        /*
         * ################################      SIGN IN      ################################
         */
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBaseHelper dataBaseHelper = new
                        DataBaseHelper(LogInActivity.this, "EXP4", null, 1);
                Cursor allLogInCursor = dataBaseHelper.getAllSignInData();

                while (allLogInCursor.moveToNext()) {
                    emailArrayList.add(allLogInCursor.getString(0));
                    passwordArrayList.add(allLogInCursor.getString(1));
                    status.add(allLogInCursor.getString(2));

                }


                for(int i = 0; i < emailArrayList.size(); i++) {
                    System.out.println("ArrayList Elements ********************:");
                    System.out.print(emailArrayList.get(i));
                }

                if (emailText.getText().toString().isEmpty()) {
                    emailError.setText("Please Enter Your Email!!");

                } else {
                    emailError.setText("");
                    System.out.println("email array list = ");
                    for (String element : emailArrayList) {

                        if (element.compareTo(emailText.getText().toString()) == 0) {
                            emailExist = 1;
                            accountStatus = status.get(emailArrayList.indexOf(element));
                            break;
                        } else {
                            emailExist = 0;
                            emailError.setText("Incorrect Email!!");
                        }
                        index += 1;
                    }
                }


                /*
                 * add case when both email and password empty
                 */
                if (passwordText.getText().toString().isEmpty()) {
                    passwordError.setText("Please Enter Email Password!!");
                } else if (emailExist == 1) {
                    if (SHA.encryptSHA512(passwordText.getText().toString()).equals(passwordArrayList.get(index))) {
                        passwordExist = 1;
                        index = 0;
                    } else {
                        passwordExist = 0;
                        passwordError.setText("Incorrect Password, try again ");

                    }

                }else{
                    passwordError.setText("Incorrect Password!!");
                }

                //status.get(index); : used inside password if else line 97 give email and password status Tenant or Renting Agency


                if (emailExist == 1 && passwordExist == 1 && checkBoxFlag == 1) {
                    sharedPrefManager.writeString("Email", emailText.getText().toString().trim());
                    sharedPrefManager.writeString("Password", passwordText.getText().toString().trim());
                    sharedPrefManager.writeBoolean("RememberMe", true);
                }

                if (emailExist == 1 && passwordExist == 1 && checkBoxFlag == 0) {
                    sharedPrefManager.writeString("Email", "");
                    sharedPrefManager.writeString("Password", "");
                    sharedPrefManager.writeBoolean("RememberMe", false);

                }

                if (emailExist == 1 && passwordExist == 1) {
                    emailError.setText("");
                    MainActivity.email = emailText.getText().toString();
                    DataBaseHelper DBHelper = new DataBaseHelper(LogInActivity.this, "EXP4", null, 1);
                    Cursor cursor = DBHelper.getReadableDatabase().rawQuery("Select STATUS from SIGNIN WHERE EMAIL LIKE '" + emailText.getText().toString() + "'", null);
                    Cursor cursor2;
                    if (cursor.moveToNext()) {
                        if (cursor.getString(0).equalsIgnoreCase("RENTINGAGENCY")) {
                            cursor2 = DBHelper.getReadableDatabase().rawQuery("SELECT AGENCYNAME FROM RENTINGAGENCY WHERE EMAIL LIKE '" + emailText.getText().toString() + "'", null);
                            if (cursor2.moveToNext())
                                MainActivity.username = cursor2.getString(0);
                        } else {
                            cursor2 = DBHelper.getReadableDatabase().rawQuery("SELECT FIRSTNAME, LASTNAME FROM TENANT WHERE EMAIL LIKE '" + emailText.getText().toString() + "'", null);
                            if (cursor2.moveToNext())
                                MainActivity.username = cursor2.getString(0) + " " + cursor2.getString(1);
                        }
                    }


                    Intent intent;
                    if (accountStatus.equalsIgnoreCase("Renting Agency") || accountStatus.equalsIgnoreCase("RENTINGAGENCY"))
                        intent = new Intent(LogInActivity.this, RentingAgencyActivity.class);
                    else
                        intent = new Intent(LogInActivity.this, TenantActivity.class);

                    LogInActivity.this.startActivity(intent);
                    finish();


                } else if (emailExist == 1) {
                    passwordText.setText("");
                } else {
                    emailText.setText("");
                    passwordText.setText("");
                }

            }


        });

        /*
         * CheckBox case
         */
        checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (checkBox.isChecked())
                    checkBoxFlag = 1;
                else
                    checkBoxFlag = 0;
            }
        });

        //###################### SIGNUP ###############################

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                LogInActivity.this.startActivity(intent);
                finish();
            }

        });

        //###################### Guest ###############################
        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = "";
                username = "Guest";
                accountStatus = "Guest";
                Intent intent = new Intent(LogInActivity.this, TenantActivity.class);
                LogInActivity.this.startActivity(intent);
                finish();
            }
        });


    }

}