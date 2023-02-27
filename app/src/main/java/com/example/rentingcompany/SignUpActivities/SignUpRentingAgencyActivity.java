package com.example.rentingcompany.SignUpActivities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rentingcompany.DataBase.DataBaseHelper;
import com.example.rentingcompany.DataBase.SHA;
import com.example.rentingcompany.LogInActivity;
import com.example.rentingcompany.Models.RentingAgency;
import com.example.rentingcompany.R;


public class SignUpRentingAgencyActivity extends AppCompatActivity {

    int numFlag = 0;
    int upperCaseflag = 0;
    int specialCharFlag = 0;
    int lowerCaseFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_renting_agency);

        int arr[] = {0, 0, 0, 0, 0, 0, 0};

        EditText emailAddress = (EditText) findViewById(R.id.emailAdress);
        EditText agencyName = (EditText) findViewById(R.id.agencyName);
        EditText password = (EditText) findViewById(R.id.password);
        EditText confirmpassword = (EditText) findViewById(R.id.confirmpassword);
        EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);

        TextView phoneNumberError = (TextView) findViewById(R.id.phoneNumberError);
        TextView countrySpinnerError = (TextView) findViewById(R.id.countrySpinnerError);
        TextView citySpinnError = (TextView) findViewById(R.id.citySpinnError);
        TextView passwordError = (TextView) findViewById(R.id.passwordError);
        TextView agencyNameError = (TextView) findViewById(R.id.agencyNameError);
        TextView emailAddressError = (TextView) findViewById(R.id.emailAddressError);
        TextView confirmpasswordError = (TextView) findViewById(R.id.confirmpasswordError);

        Button confirm = (Button) findViewById(R.id.confirm);

        String[] currentResidenceCountryoptions = {"Palestine", "Algeria", "Jordan", "Qatar", "Syria", "Lebanon"};
        Spinner countrySpinner = (Spinner)
                findViewById(R.id.countrySpinner);

        ArrayAdapter<String> arrayAdapter_parent = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, currentResidenceCountryoptions);
        countrySpinner.setAdapter(arrayAdapter_parent);


        String[] palestineCities = {"Jerusalem", "Ramallah", "Gaza", " Hebron", "Nablus", "Akka", "Bethlehem"};
        String[] AlgeriaCities = {"Oran", "Oran", "Constantine", "Annaba", "Djelfa", "Biskra", "Setif"};
        String[] JordanCities = {"Amman", "Zarqa", "Irbid", "Russeifa", "Wadi as-Ser", "Madaba", "al-Baq'a", "Sahab"};
        String[] QatarCities = {"Doha", "Abu az Zuluf", "Abu Thaylah", "Al Ghanim", "Al Ghuwariyah", "Al `Arish"};
        String[] SyriaCities = {"Aleppo", "Damascus", "Homs", "Latakia", "Hama", "Qamishli", "Tartus"};
        String[] LebanonCities = {"Beirut", "Tripoli", "Sidon", "Zahle", "Batroun", "Tyre"};


        Spinner citySpinn = (Spinner) findViewById(R.id.citySpinn);

        ArrayAdapter<String> objCCClityArr = new
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, palestineCities);
        citySpinn.setAdapter(objCCClityArr);

        phoneNumber.setText("00970");

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, palestineCities);
                    citySpinn.setAdapter(arrayAdapter_child);
                    phoneNumber.setText("00970");
                }
                if (i == 1) {
                    ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, AlgeriaCities);
                    citySpinn.setAdapter(arrayAdapter_child);
                    phoneNumber.setText("00213");
                }
                if (i == 2) {
                    ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, JordanCities);
                    citySpinn.setAdapter(arrayAdapter_child);
                    phoneNumber.setText("00962");
                }
                if (i == 3) {
                    ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, QatarCities);
                    citySpinn.setAdapter(arrayAdapter_child);
                    phoneNumber.setText("00974");
                }
                if (i == 4) {
                    ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, SyriaCities);
                    citySpinn.setAdapter(arrayAdapter_child);
                    phoneNumber.setText("00963");
                }
                if (i == 5) {
                    ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, LebanonCities);
                    citySpinn.setAdapter(arrayAdapter_child);
                    phoneNumber.setText("00961");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, palestineCities);
                citySpinn.setAdapter(arrayAdapter_child);

            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (emailAddress.getText().toString().isEmpty()) {
                    emailAddressError.setText("Required Field");
                    arr[0] = 0;
                } else {
                    if (emailAddress.getText().toString().trim().matches(emailPattern)) {
                        emailAddressError.setText("");
                        arr[0] = 1;
                    } else {
                        emailAddress.setText("");
                        emailAddressError.setText("Invalid email address");
                        arr[0] = 0;
                    }
                }


                if (agencyName.getText().toString().isEmpty()) {
                    arr[1] = 0;
                    agencyNameError.setText("Required Field");
                } else if (agencyName.getText().toString().length() < 21) {
                    agencyNameError.setText("");
                    arr[1] = 1;
                } else {
                    agencyName.setText("");
                    agencyNameError.setText("Large Input!! maximum letters is 20");
                    arr[1] = 0;
                }


                if (password.getText().toString().isEmpty()) {
                    passwordError.setText("Required Field");
                    arr[2] = 0;
                } else if (password.getText().toString().length() > 7 && password.getText().toString().length() < 16) {
                    String sample = password.getText().toString();
                    String specialChar = "$%#@!{&}";
                    char[] specialCHARS = specialChar.toCharArray();
                    char[] chars = sample.toCharArray();
                    for (char c : chars) {
                        if (Character.isDigit(c)) {
                            numFlag = 1;
                        }
                        if (Character.isUpperCase(c)) {
                            upperCaseflag = 1;
                        }
                        if (Character.isLowerCase(c)) {
                            lowerCaseFlag = 1;
                        }
                        //Check if it consist special character
                        for (char x : specialCHARS) {
                            if (x == c) {
                                specialCharFlag = 1;
                                // emailAddressError.setText(x);
                            }
                        }

                    }

                    if (numFlag == 1 && upperCaseflag == 1 && specialCharFlag == 1 && lowerCaseFlag == 1) {
                        passwordError.setText("");
                        arr[2] = 1;
                    } else {
                        passwordError.setText("Weak Password");
                        password.setText("");
                        arr[2] = 0;
                    }
                } else {
                    passwordError.setText("Weak Password");
                    password.setText("");
                    arr[2] = 0;
                }


                if (confirmpassword.getText().toString().isEmpty()) {
                    confirmpasswordError.setText("Required Field");
                    arr[3] = 0;
                } else if (confirmpassword.getText().toString().equals(password.getText().toString())) {
                    confirmpasswordError.setText("");
                    arr[3] = 1;
                    /*
                     * Encrypt using SHA
                     */
                } else {
                    confirmpassword.setText("");
                    confirmpasswordError.setText("Error! paswword is not the same");
                    arr[3] = 0;
                }


                if (countrySpinner.getSelectedItem().toString().isEmpty()) {
                    countrySpinnerError.setText("Required Field");
                    arr[4] = 0;
                } else {
                    countrySpinnerError.setText("");
                    arr[4] = 1;
                }


                countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (i == 0) {
                            ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, palestineCities);
                            citySpinn.setAdapter(arrayAdapter_child);
                            phoneNumber.setText("00970");
                        }
                        if (i == 1) {
                            ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, AlgeriaCities);
                            citySpinn.setAdapter(arrayAdapter_child);
                            phoneNumber.setText("00213");
                        }
                        if (i == 2) {
                            ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, JordanCities);
                            citySpinn.setAdapter(arrayAdapter_child);
                            phoneNumber.setText("00962");
                        }
                        if (i == 3) {
                            ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, QatarCities);
                            citySpinn.setAdapter(arrayAdapter_child);
                            phoneNumber.setText("00974");
                        }
                        if (i == 4) {
                            ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, SyriaCities);
                            citySpinn.setAdapter(arrayAdapter_child);
                            phoneNumber.setText("00963");
                        }
                        if (i == 5) {
                            ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, LebanonCities);
                            citySpinn.setAdapter(arrayAdapter_child);
                            phoneNumber.setText("00961");
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, palestineCities);
                        citySpinn.setAdapter(arrayAdapter_child);

                    }
                });

                arr[5] = 1;


                if (phoneNumber.getText().toString().length() < 5) {
                    arr[6] = 0;
                    phoneNumberError.setText("Required Field");
                } else if (phoneNumber.getText().toString().matches("[-+]?\\d*\\.?\\d+")) {
                    phoneNumberError.setText("");
                    arr[6] = 1;
                } else {
                    arr[6] = 0;
                    phoneNumberError.setText("Invalid Input!!");
                }


                // Correct password & email
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        for (int i = 0; i < 7; i++) {
                            if (arr[i] == 0) {
                                Toast.makeText(getApplicationContext(),
                                        "Sign Up process failed, Try again please", Toast.LENGTH_LONG).show();
                                break;
                            } else if (i == 6) {
                                //ADD DATA INTO DATABase

                                RentingAgency newRentingAgency = new RentingAgency();
                                newRentingAgency.setEmailAddress(emailAddress.getText().toString());
                                newRentingAgency.setAgencyName(agencyName.getText().toString());
                                newRentingAgency.setPassword(password.getText().toString());
                                newRentingAgency.setConfirmPassword(SHA.encryptSHA512(confirmpassword.getText().toString()));
                                newRentingAgency.setCountry(countrySpinner.getSelectedItem().toString());
                                newRentingAgency.setCity(citySpinn.getSelectedItem().toString());
                                newRentingAgency.setPhoneNumber(phoneNumber.getText().toString());

                                DataBaseHelper dataBaseHelper = new DataBaseHelper(SignUpRentingAgencyActivity.this, "EXP4", null, 1);
                                dataBaseHelper.insertRentingAgency(newRentingAgency);


                                Intent intent = new Intent(SignUpRentingAgencyActivity.this, LogInActivity.class);
                                SignUpRentingAgencyActivity.this.startActivity(intent);
                                finish();
                            }
                        }


                    }
                }, 1000);


            }

        });

/*
        DataBaseHelper dataBaseHelper = new DataBaseHelper(RentingAgency.this, "EXP4", null, 1);
        Cursor allCustomersCursor = dataBaseHelper.getRentingAgencyData();
        while (allCustomersCursor.moveToNext()) {

            System.out.println("\n\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + "emailAddress= " + allCustomersCursor.getString(0)
                    + "\nagencyName= " + allCustomersCursor.getString(1)
                    + "\npassword= " + allCustomersCursor.getString(2)
                    + "\nconfirmpassword= " + allCustomersCursor.getString(3)
                    + "countrySpinner\n" + allCustomersCursor.getString(4)
                    + "citySpinn\n" + allCustomersCursor.getString(5)
                    + "phoneNumber\n" + allCustomersCursor.getString(6) + "\n\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        }**/
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignUpRentingAgencyActivity.this, LogInActivity.class);
        SignUpRentingAgencyActivity.this.startActivity(intent);
        finish();
    }


}