package com.thetrendybazaar.thetrendybazaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // Database Helpers
    EcommerceDatabaseHelper db ;
    //static CustomerManager customerManager = new CustomerManager();
    //      Text Views
    private  TextView mSuggestionText;

    //      EditTexts
    private EditText mFirstNameEditText,
            mLastNameEditText,
            mEmailEditText,
            mPasswordEditText;

    //      Buttons
    private Button mSignUpButton, mLoginButton;

    //    Vars
    private boolean mValidEmail = false;
    private boolean mValidPassword = false;

    //      ImageViews
    private ImageView mFirstNameCheck,
            mLastNameCheck,
            mEmailCheck,
            mPasswordCheck;

    //      ScrollViews
    private ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new EcommerceDatabaseHelper(this);

//        Database
        DatabaseManager.setDb(db.getReadDb(), db.getWriteDb());
        //DatabaseManager.currentShoppingCarts.se
//       this.deleteDatabase("Ecommerce");
//        String[] names = new String[]{"Nike", "Supreme", "Adidas", "Aeropostale", "American Eagle"};
//        String[] addresses = new String[] {"New York", "Texas", "California", "New Jersey", "Floria"};
//
//        for(int i =0; i <5;i++){
//            //add manufacturers
//            Manufacturer manufacturer = new Manufacturer(null,
//                    names[i%5], addresses[i%5], null, null);
//            DatabaseManager.manufacturers.add(manufacturer);
//        }
//
//        for(int i = 0; i < 15; i++){
//            Customer customer = new Customer(null, "name"+i, "lastName"+i,
//                    i +"@g.com", "password", null, null);
//            DatabaseManager.customers.addCustomer(customer);
//            Item item = new Item(null, i%5 + 1, 10,12.50,
//                    "This is an item description", "Clothing", "Supreme Tee " + i);
//            DatabaseManager.items.add(item);
//            Review review = new Review(null,item.articleId,3,
//                    "This is a detailed review of some random product");
//            DatabaseManager.reviews.add(review);
//            List<Review> reviews = DatabaseManager.reviews.getReviews(customer.id);
//            DatabaseManager.writes.add(customer.id,reviews.get(0));
//
//
//
//            //add to supplies table
//            DatabaseManager.supplies.add(item, i%5+1, (i*25.5 + 7)%50);
//
//        }
//
//        DatabaseManager.employees.add(new Employee(null, "Worker",
//                Calendar.getInstance().getTime(), 1, "Bob",
//                "Builder", "Password"));

//        Text Views
        mSuggestionText = findViewById(R.id.passwordSuggestionText);

//        Edit Texts
        mFirstNameEditText = findViewById(R.id.firstNameFieldWelcome);
        mLastNameEditText = findViewById(R.id.lastNameFieldWelcome);
        mEmailEditText = findViewById(R.id.emailFieldWelcome);
        mPasswordEditText = findViewById(R.id.passwordFieldWelcome);


//        Image Views
        mFirstNameCheck = findViewById(R.id.firstNameCheck);
        mLastNameCheck = findViewById(R.id.lastNameCheck);
        mEmailCheck = findViewById(R.id.emailCheck);
        mPasswordCheck = findViewById(R.id.passwordCheck);

//        Buttons
        mSignUpButton = findViewById(R.id.signUpButtonWelcome);
        mLoginButton = findViewById(R.id.logInButtonWelcome);

//        Scroll View

        mScrollView = findViewById(R.id.scrollViewLogin);

//        Set Listeners
        mSignUpButton.setOnClickListener(clickListener);
        mLoginButton.setOnClickListener(clickListener);

        mFirstNameEditText.addTextChangedListener(new GenericTextWatcher(mFirstNameEditText));
        mFirstNameEditText.setOnFocusChangeListener(focusChangeListener);
        mLastNameEditText.addTextChangedListener(new GenericTextWatcher(mLastNameEditText));
        mLastNameEditText.setOnFocusChangeListener(focusChangeListener);
        mEmailEditText.addTextChangedListener(new GenericTextWatcher(mEmailEditText));
        mEmailEditText.setOnFocusChangeListener(focusChangeListener);
//        mEmailEditText.setOnClickListener(clickListener);
        mPasswordEditText.addTextChangedListener(new GenericTextWatcher(mPasswordEditText));
        mPasswordEditText.setOnFocusChangeListener(focusChangeListener);

    }

    private View.OnClickListener clickListener = (view) -> {
        switch (view.getId()){
            case R.id.signUpButtonWelcome:
                if(mValidPassword && mValidEmail)
                    createAccountAndGoToStore();
                break;

            case R.id.logInButtonWelcome:
                goToLoginActivity();
                break;

            case R.id.emailFieldWelcome:
                mScrollView.scrollTo(0, mScrollView.getBottom());
                break;

            case R.id.passwordFieldWelcome:
                mScrollView.scrollTo(0, mScrollView.getBottom());
                break;
        }
    };

    private void goToLoginActivity() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }

    private void createAccountAndGoToStore() {
        // Database here
        Customer customer = new Customer(null,
                mFirstNameEditText.getText().toString(),
                mLastNameEditText.getText().toString(),
                mEmailEditText.getText().toString(),
                mPasswordEditText.getText().toString(),
                null,
                null);

        DatabaseManager.customers.addCustomer(customer);
        Customer.currentCustomerId = customer.id;
        Toast.makeText(this, "Welcome "+ customer.firstName, Toast.LENGTH_LONG).show();


        Intent storeIntent = new Intent(MainActivity.this, StoreActivity.class);
        startActivity(storeIntent);
        finish();
    }

    private View.OnFocusChangeListener focusChangeListener = (view, hasFocus) -> {

        switch (view.getId()){
            case R.id.passwordFieldWelcome:
                if(hasFocus) {
                    mScrollView.scrollTo(0, mScrollView.getBottom());
                    mSuggestionText.setText("Passwords must be at least 8 characters long");
                    mSuggestionText.setVisibility(View.VISIBLE);
                }
                else mSuggestionText.setVisibility(View.INVISIBLE);
                break;
            case R.id.emailFieldWelcome:
                if(hasFocus && !mValidEmail){
                    mScrollView.scrollTo(0, mScrollView.getBottom());
                    mSuggestionText.setText("Please Enter a valid email");
                    mSuggestionText.setVisibility(View.VISIBLE);
                }else mSuggestionText.setVisibility(View.INVISIBLE);
                break;
        }

    };



    private class GenericTextWatcher implements TextWatcher {

        private final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                        Pattern.CASE_INSENSITIVE);

        private EditText editText;

        GenericTextWatcher(EditText editText){
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            switch (editText.getId()){

                case R.id.firstNameFieldWelcome: checkStringNotEmpty(s, mFirstNameCheck);
                    break;
                case R.id.lastNameFieldWelcome: checkStringNotEmpty(s, mLastNameCheck);
                    break;
                case R.id.emailFieldWelcome: validateEmail(s.toString());
                    break;
                case R.id.passwordFieldWelcome: validatePassword(s.toString());
                    break;
                default:
                    break;
            }

        }

        private void validateEmail(String s) {
            if(s.equals("")) {
                mValidEmail = false;
                mEmailCheck.setVisibility(View.INVISIBLE);
            }
            else if(validEmail(s)) {
                mValidEmail = true;
                mEmailCheck.setImageResource(R.drawable.ic_round_done_24px);
                mEmailCheck.setVisibility(View.VISIBLE);
            }
            else {
                mValidEmail = false;
                mEmailCheck.setImageResource(R.drawable.ic_round_close_24px);
                mEmailCheck.setVisibility(View.VISIBLE);
            }
        }

        private boolean validEmail(String emailStr) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
            return matcher.find();
        }

        private void checkStringNotEmpty(Editable s, ImageView check){
            if(!s.toString().equals("")) check.setVisibility(View.VISIBLE);
            else check.setVisibility(View.INVISIBLE);
        }

        private void validatePassword(String s){
            if(s.equals("")) {
                mValidPassword = false;
                mPasswordCheck.setVisibility(View.INVISIBLE);
            }
            else if(s.length() >= 8) {
                mValidPassword = true;
                mPasswordCheck.setImageResource(R.drawable.ic_round_done_24px);
                mPasswordCheck.setVisibility(View.VISIBLE);
            }
            else {
                mValidPassword = false;
                mPasswordCheck.setImageResource(R.drawable.ic_round_close_24px);
                mPasswordCheck.setVisibility(View.VISIBLE);
            }
        }
    }
}
