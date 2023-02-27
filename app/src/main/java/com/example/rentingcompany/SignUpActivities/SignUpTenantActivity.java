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
import com.example.rentingcompany.Models.Tenant;
import com.example.rentingcompany.R;

public class SignUpTenantActivity extends AppCompatActivity {
    int numFlag = 0;
    int upperCaseflag = 0;
    int specialCharFlag = 0;
    int lowerCaseFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_tenant);


        EditText emailTextField = (EditText) findViewById(R.id.emailTextField);
        EditText firstNameTextField = (EditText) findViewById(R.id.firstNameTextField);
        EditText lastNameTextField = (EditText) findViewById(R.id.lastNameTextField);

        EditText passwordTextField = (EditText) findViewById(R.id.passwordTextField);
        EditText confirmPasswordTextFiled = (EditText) findViewById(R.id.confirmPasswordTextFiled);
        EditText grossMonthlySalaryTextField = (EditText) findViewById(R.id.grossMonthlySalaryTextField);
        EditText occupationTextField = (EditText) findViewById(R.id.occupationTextField);
        EditText familySizeTextField = (EditText) findViewById(R.id.familySizeTextField);
        EditText phoneNumberTextField = (EditText) findViewById(R.id.phoneNumberTextField);


        TextView emailErrorTxtView = (TextView) findViewById(R.id.emailErrorTxtView);
        TextView firstNameErrorTxtView = (TextView) findViewById(R.id.firstNameErrorTxtView);
        TextView lastNmaeErrorTxtView = (TextView) findViewById(R.id.lastNmaeErrorTxtView);
        TextView genderErrorTxtView = (TextView) findViewById(R.id.genderErrorTxtView);
        TextView passwordErrorTxtView = (TextView) findViewById(R.id.passwordErrorTxtView);
        TextView confirmPasswordErrorTxtView = (TextView) findViewById(R.id.confirmPasswordErrorTxtView);
        TextView nationalityErrorTxtView = (TextView) findViewById(R.id.nationalityErrorTxtView);
        TextView grossMonthlySalaryErrorTxtView = (TextView) findViewById(R.id.grossMonthlySalaryErrorTxtView);
        TextView occupationErrorTxtView = (TextView) findViewById(R.id.occupationErrorTxtView);
        TextView familySizeErrorTxtView = (TextView) findViewById(R.id.familySizeErrorTxtView);
        TextView currentresidenceErrorTxtView = (TextView) findViewById(R.id.currentresidenceErrorTxtView);
        TextView cityErrorTxtView = (TextView) findViewById(R.id.cityErrorTxtView);
        TextView phoneNumberErrorTxtView = (TextView) findViewById(R.id.phoneNumberErrorTxtView);


        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        int arr[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        String[] genderOptions = {"Male", "Female"};
        final Spinner genderSpinner = (Spinner)
                findViewById(R.id.genderSpinner);
        ArrayAdapter<String> objGenderArr = new
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genderOptions);
        genderSpinner.setAdapter(objGenderArr);


        String[] nationalityOptions = {"Palestinian", "Algerian", "Jordanian", "Qatari", "Syrian", "Lebanese", "Egyptian", "Turkey", "Tunisia"};
        final Spinner nationalitySpinner = (Spinner)
                findViewById(R.id.nationalitySpinner);
        ArrayAdapter<String> objNationalityArr = new
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nationalityOptions);
        nationalitySpinner.setAdapter(objNationalityArr);


        String[] currentResidenceCountryoptions = {"Palestine", "Algeria", "Jordan", "Qatar", "Syria", "Lebanon"};
        Spinner currentResidenceCountrySpinner = (Spinner)
                findViewById(R.id.currentResidenceCountrySpinner);

        ArrayAdapter<String> arrayAdapter_parent = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, currentResidenceCountryoptions);
        currentResidenceCountrySpinner.setAdapter(arrayAdapter_parent);
        currentResidenceCountrySpinner.setAdapter(arrayAdapter_parent);


        String[] palestineCities = {"Jerusalem", "Ramallah", "Gaza", " Hebron", "Nablus", "Akka", "Bethlehem"};
        String[] AlgeriaCities = {"Oran", "Oran", "Constantine", "Annaba", "Djelfa", "Biskra", "Setif"};
        String[] JordanCities = {"Amman", "Zarqa", "Irbid", "Russeifa", "Wadi as-Ser", "Madaba", "al-Baq'a", "Sahab"};
        String[] QatarCities = {"Doha", "Abu az Zuluf", "Abu Thaylah", "Al Ghanim", "Al Ghuwariyah", "Al `Arish"};
        String[] SyriaCities = {"Aleppo", "Damascus", "Homs", "Latakia", "Hama", "Qamishli", "Tartus"};
        String[] LebanonCities = {"Beirut", "Tripoli", "Sidon", "Zahle", "Batroun", "Tyre"};


        Spinner citySpinner = (Spinner) findViewById(R.id.citySpinner);





/*
        emailErrorTxtView.setText("");
        firstNameErrorTxtView.setText("");
        lastNmaeErrorTxtView.setText("");
        genderErrorTxtView.setText("");
        passwordErrorTxtView.setText("");
        confirmPasswordErrorTxtView.setText("");
        nationalityErrorTxtView.setText("");
        grossMonthlySalaryErrorTxtView.setText("");
        occupationErrorTxtView.setText("");
        familySizeErrorTxtView.setText("");
        currentresidenceErrorTxtView.setText("");
        cityErrorTxtView.setText("");
        phoneNumberErrorTxtView.setText("");
*/
        ArrayAdapter<String> objCCClityArr = new
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, palestineCities);


        phoneNumberTextField.setText("00970");

        currentResidenceCountrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, palestineCities);
                    citySpinner.setAdapter(arrayAdapter_child);
                    phoneNumberTextField.setText("00970");
                }
                if (i == 1) {
                    ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, AlgeriaCities);
                    citySpinner.setAdapter(arrayAdapter_child);
                    phoneNumberTextField.setText("00213");
                }
                if (i == 2) {
                    ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, JordanCities);
                    citySpinner.setAdapter(arrayAdapter_child);
                    phoneNumberTextField.setText("00962");
                }
                if (i == 3) {
                    ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, QatarCities);
                    citySpinner.setAdapter(arrayAdapter_child);
                    phoneNumberTextField.setText("00974");
                }
                if (i == 4) {
                    ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, SyriaCities);
                    citySpinner.setAdapter(arrayAdapter_child);
                    phoneNumberTextField.setText("00963");
                }
                if (i == 5) {
                    ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, LebanonCities);
                    citySpinner.setAdapter(arrayAdapter_child);
                    phoneNumberTextField.setText("00961");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, palestineCities);
                citySpinner.setAdapter(arrayAdapter_child);

            }
        });


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //phoneNumberTextField.setText("00970");

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (emailTextField.getText().toString().isEmpty()) {
                    emailErrorTxtView.setText("Required Field");
                    arr[0] = 0;
                } else {
                    if (emailTextField.getText().toString().trim().matches(emailPattern)) {
                        emailErrorTxtView.setText("");
                        arr[0] = 1;
                    } else {
                        emailTextField.setText("");
                        emailErrorTxtView.setText("Invalid email address");
                        arr[0] = 0;
                    }
                }


                if (firstNameTextField.getText().toString().isEmpty()) {
                    firstNameErrorTxtView.setText("Required Field");
                    arr[1] = 0;
                } else {
                    if (2 < firstNameTextField.getText().toString().length() && firstNameTextField.getText().toString().length() < 21) {
                        firstNameErrorTxtView.setText("");
                        arr[1] = 1;
                    } else if (firstNameTextField.getText().toString().length() > 20) {
                        firstNameTextField.setText("");
                        firstNameErrorTxtView.setText("Large Name ; maximum 20 letters");
                        arr[1] = 0;
                    } else if (firstNameTextField.getText().toString().length() < 3) {
                        firstNameTextField.setText("");
                        firstNameErrorTxtView.setText("Short Name ; minimum 3 letters");
                        arr[1] = 0;
                    }
                }


                if (lastNameTextField.getText().toString().isEmpty()) {
                    lastNmaeErrorTxtView.setText("Required Field");
                    arr[2] = 0;
                } else {
                    if (2 < lastNameTextField.getText().toString().length() && lastNameTextField.getText().toString().length() < 21) {
                        lastNmaeErrorTxtView.setText("");
                        arr[2] = 1;
                    } else if (lastNameTextField.getText().toString().length() > 20) {
                        lastNameTextField.setText("");
                        lastNmaeErrorTxtView.setText("Large Name ; maximum 20 letters");
                        arr[2] = 0;
                    } else if (lastNameTextField.getText().toString().length() < 3) {
                        lastNameTextField.setText("");
                        lastNmaeErrorTxtView.setText("Short Name ; minimum 3 letters");
                        arr[2] = 0;
                    }
                }


                if (genderSpinner.getSelectedItem().toString().isEmpty()) {
                    genderErrorTxtView.setText("Required Field");
                    arr[3] = 0;
                } else {
                    genderErrorTxtView.setText("");
                    arr[3] = 1;
                }


                if (passwordTextField.getText().toString().isEmpty()) {
                    passwordErrorTxtView.setText("Required Field");
                    arr[4] = 0;
                } else if (passwordTextField.getText().toString().length() > 7 && passwordTextField.getText().toString().length() < 16) {
                    String sample = passwordTextField.getText().toString();
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
                            }
                        }

                    }

                    if (numFlag == 1 && upperCaseflag == 1 && specialCharFlag == 1 && lowerCaseFlag == 1) {
                        passwordErrorTxtView.setText("");
                        arr[4] = 1;
                    } else {
                        passwordErrorTxtView.setText("Weak Password");
                        passwordTextField.setText("");
                        arr[4] = 0;
                    }
                } else {
                    passwordErrorTxtView.setText("Weak Password");
                    passwordTextField.setText("");
                    arr[4] = 0;
                }


                if (confirmPasswordTextFiled.getText().toString().isEmpty()) {
                    confirmPasswordErrorTxtView.setText("Required Field");
                    arr[5] = 0;
                } else if (confirmPasswordTextFiled.getText().toString().equals(passwordTextField.getText().toString())) {
                    confirmPasswordErrorTxtView.setText("");
                    arr[5] = 1;
                    /*
                     * Encrypt using SHA
                     */
                } else {
                    confirmPasswordTextFiled.setText("");
                    confirmPasswordErrorTxtView.setText("Error! paswword is not the same");
                    arr[5] = 0;
                }


                if (nationalitySpinner.getSelectedItem().toString().isEmpty()) {
                    nationalityErrorTxtView.setText("Required Field");
                    arr[6] = 0;
                } else {
                    nationalityErrorTxtView.setText("");
                    arr[6] = 1;
                }


                if (grossMonthlySalaryTextField.getText().toString().isEmpty()) {
                    arr[7] = 0;
                    grossMonthlySalaryErrorTxtView.setText("Required Field");
                } else if (grossMonthlySalaryTextField.getText().toString().matches("[-+]?\\d*\\.?\\d+")) {
                    grossMonthlySalaryErrorTxtView.setText("");
                    arr[7] = 1;
                } else {
                    grossMonthlySalaryTextField.setText("");
                    grossMonthlySalaryErrorTxtView.setText("Invalid Input!! Use Numbers Only");
                    arr[7] = 0;
                }


                if (occupationTextField.getText().toString().isEmpty()) {
                    arr[8] = 0;
                    occupationErrorTxtView.setText("Required Field");
                } else if (occupationTextField.getText().toString().length() < 21) {
                    occupationErrorTxtView.setText("");
                    arr[8] = 1;
                } else {
                    occupationTextField.setText("");
                    occupationErrorTxtView.setText("Large Input!! maximum letters is 20");
                    arr[8] = 0;
                }


                if (familySizeTextField.getText().toString().isEmpty()) {
                    arr[9] = 0;
                    familySizeErrorTxtView.setText("Required Field");
                } else if (familySizeTextField.getText().toString().matches("[-+]?\\d*\\.?\\d+")) {
                    familySizeErrorTxtView.setText("");
                    arr[9] = 1;
                } else {
                    familySizeTextField.setText("");
                    familySizeErrorTxtView.setText("Invalid Input!! Use Numbers Only");
                    arr[9] = 0;
                }


                if (currentResidenceCountrySpinner.getSelectedItem().toString().isEmpty()) {
                    currentresidenceErrorTxtView.setText("Required Field");
                    arr[10] = 0;
                } else {
                    currentresidenceErrorTxtView.setText("");
                    arr[10] = 1;
                }

                currentResidenceCountrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (i == 0) {
                            ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, palestineCities);
                            citySpinner.setAdapter(arrayAdapter_child);
                            phoneNumberTextField.setText("00970");
                        }
                        if (i == 1) {
                            ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, AlgeriaCities);
                            citySpinner.setAdapter(arrayAdapter_child);
                            phoneNumberTextField.setText("00213");
                        }
                        if (i == 2) {
                            ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, JordanCities);
                            citySpinner.setAdapter(arrayAdapter_child);
                            phoneNumberTextField.setText("00962");
                        }
                        if (i == 3) {
                            ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, QatarCities);
                            citySpinner.setAdapter(arrayAdapter_child);
                            phoneNumberTextField.setText("00974");
                        }
                        if (i == 4) {
                            ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, SyriaCities);
                            citySpinner.setAdapter(arrayAdapter_child);
                            phoneNumberTextField.setText("00963");
                        }
                        if (i == 5) {
                            ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, LebanonCities);
                            citySpinner.setAdapter(arrayAdapter_child);
                            phoneNumberTextField.setText("00961");
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, palestineCities);
                        citySpinner.setAdapter(arrayAdapter_child);

                    }
                });

                arr[11] = 1;


                if (phoneNumberTextField.getText().toString().length() < 5) {
                    arr[12] = 0;
                    phoneNumberErrorTxtView.setText("Required Field");
                } else if (phoneNumberTextField.getText().toString().matches("[-+]?\\d*\\.?\\d+")) {
                    phoneNumberErrorTxtView.setText("");
                    arr[12] = 1;
                } else {
                    arr[12] = 0;
                    phoneNumberErrorTxtView.setText("Invalid Input!!");
                }

                // Correct password & email
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        for (int i = 0; i < 13; i++) {
                            if (arr[i] == 0) {
                                Toast.makeText(getApplicationContext(),
                                        "Sign Up process failed, Try again please", Toast.LENGTH_LONG).show();
                                break;
                            } else if (i == 12) {
                                //ADD DATA INTO DATABase

                                Tenant newTenant = new Tenant();

                                newTenant.setEmailAddress(emailTextField.getText().toString());
                                newTenant.setFirstName(firstNameTextField.getText().toString());
                                newTenant.setLastName(lastNameTextField.getText().toString());
                                newTenant.setGender(genderSpinner.getSelectedItem().toString());
                                newTenant.setPassword(passwordTextField.getText().toString());
                                newTenant.setConfirmPassword(SHA.encryptSHA512(confirmPasswordTextFiled.getText().toString()));
                                newTenant.setNationality(nationalitySpinner.getSelectedItem().toString());
                                newTenant.setGrossMonthlySalary(grossMonthlySalaryTextField.getText().toString());
                                newTenant.setOccupation(occupationTextField.getText().toString());
                                newTenant.setFamilySize(familySizeTextField.getText().toString());
                                newTenant.setCurrentResidenceCountry(currentResidenceCountrySpinner.getSelectedItem().toString());
                                newTenant.setCity(citySpinner.getSelectedItem().toString());
                                newTenant.setPhoneNumber(phoneNumberTextField.getText().toString());

                                DataBaseHelper dataBaseHelper = new DataBaseHelper(SignUpTenantActivity.this, "EXP4", null, 1);
                                dataBaseHelper.insertTenant(newTenant);

                                Intent intent = new Intent(SignUpTenantActivity.this, LogInActivity.class);
                                SignUpTenantActivity.this.startActivity(intent);
                                finish();
                            }
                        }


                    }
                }, 1000);


            }

        });


    }
}
