package com.thetrendybazaar.thetrendybazaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;

public class CheckoutActivity extends AppCompatActivity {

    EditText firstName, lastName, shippingAddress, phoneNumber,
            billingAddress, cardNumber, expiration, cvc;

    TextView orderTotal;
    RadioGroup paymentGroup;
    RadioButton paymentType;
    Button purchaseButton;
    Customer currentCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        currentCustomer = DatabaseManager.customers.select(Customer.currentCustomerId);

        firstName = findViewById(R.id.checkout_firstname);
        lastName = findViewById(R.id.checkout_lastname);
        shippingAddress = findViewById(R.id.check_out_shipping_address);
        phoneNumber = findViewById(R.id.check_out_phone_num);
        billingAddress = findViewById(R.id.check_out_billing_address);
        cardNumber = findViewById(R.id.check_out_card_num);
        expiration = findViewById(R.id.check_out_card_exp_date);
        cvc = findViewById(R.id.check_out_card_cvc);

        firstName.setText("Kavan");
        lastName.setText("Wadhwa");
        shippingAddress.setText("19 Williams Ave Sysosset NY 11791");
        phoneNumber.setText("5163485942");
        billingAddress.setText("19 Williams Ave Sysosset NY 11791");
        cardNumber.setText("123456789012345");
        expiration.setText("01/20");
        cvc.setText("123");

        paymentGroup = findViewById(R.id.payment_type);

        orderTotal = findViewById(R.id.check_out_total);
        purchaseButton = findViewById(R.id.check_out_purchase_btn);

        //added
        String totalPrice = "$" + ShoppingCart.currentShoppingCartPrice;
        orderTotal.setText(totalPrice);



        purchaseButton.setOnClickListener(e -> {
            int paymentTypeId = paymentGroup.getCheckedRadioButtonId();
            if(paymentTypeId == -1)
                Toast.makeText(this, "Please select Payment Type",
                        Toast.LENGTH_LONG).show();
            else {
                paymentType = findViewById(paymentTypeId);

                if(!firstName.getText().toString().equals(currentCustomer.firstName))
                    Toast.makeText(this, "First name doesn't match, name on record",
                            Toast.LENGTH_LONG).show();
                else if(!lastName.getText().toString().equals(currentCustomer.lastName))
                    Toast.makeText(this, "Last name doesn't match, name on record",
                            Toast.LENGTH_LONG).show();
                else if(phoneNumber.getText().toString().length() != 10)
                    Toast.makeText(this, "Please Enter Valid Phone Number",
                            Toast.LENGTH_LONG).show();
                else if(cardNumber.getText().toString().length() != 16)
                    Toast.makeText(this, "Please Enter Valid Card Number",
                            Toast.LENGTH_LONG).show();
                else if(!expiration.getText().toString().matches("\\d\\d/\\d\\d"))
                    Toast.makeText(this, "Exp Date format MM/YY",
                            Toast.LENGTH_LONG).show();
                else if(cvc.getText().toString().length() != 3)
                    Toast.makeText(this, "Please enter valid CVC",
                            Toast.LENGTH_LONG).show();
                else{
                    long cardNumberint = Long.parseLong(cardNumber.getText().toString());
                    String type = paymentType.getText().toString();
                    int securityCode = Integer.parseInt(cvc.getText().toString());
                    String date = expiration.getText().toString();
                    PaymentInfo paymentInfo = new PaymentInfo(cardNumberint, type, securityCode, date);
                    DatabaseManager.paymentInfos.add(paymentInfo);
                    DatabaseManager.adds.add(Customer.currentCustomerId, cardNumberint);
                    DatabaseManager.currentShoppingCarts.deleteAndAddOrder(
                            ShoppingCart.currentShoppingCardId, cardNumberint, shippingAddress.getText().toString());
                    Customer customer = DatabaseManager.customers.select(Customer.currentCustomerId);
                    customer.phone = phoneNumber.getText().toString();
                    customer.address = billingAddress.getText().toString();
                    DatabaseManager.customers.updateCustomer(customer);


                    Toast.makeText(this, "Payment Successfull", Toast.LENGTH_SHORT).show();
                    Intent storeIntent = new Intent(this, StoreActivity.class);
                    startActivity(storeIntent);
                }

            }
        });
    }
}
