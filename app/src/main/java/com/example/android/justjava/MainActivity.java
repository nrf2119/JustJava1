package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 2;
    CheckBox whippedCreamCheckBox;
    CheckBox chocolateCheckBox;
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        displayQuantity(numberOfCoffees);
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price, whippedCreamCheckBox.isChecked(), chocolateCheckBox.isChecked());
        displayMessage(priceMessage);
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:"));
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order");
//        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }


    }

    public void increment (View view) {
        if (numberOfCoffees == 100){
            Toast.makeText(this, "You can't order more than 100 cups of coffee.", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffees++;
        displayQuantity(numberOfCoffees);
    }
    public void decrement (View view) {
        if (numberOfCoffees == 1){
            Toast.makeText(this, "You can't order less than 1 cup of coffee.", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffees--;
        displayQuantity(numberOfCoffees);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


    private int calculatePrice() {
        int basePrice = 5;
        if (whippedCreamCheckBox.isChecked()) {
            basePrice++;
        }
        if (chocolateCheckBox.isChecked()){
            basePrice+= 2;
        }
        int price = numberOfCoffees * basePrice;
        return price;
    }

    private String createOrderSummary (int price, boolean addWhippedCream, boolean addChocolate) {
        String customerName = nameEditText.getText().toString();
        String priceMessage = "Name: " + customerName;
        priceMessage += "\nAdded Whipped Cream? " + addWhippedCream;
        priceMessage += "\nAdded Chocolate? " + addChocolate;
        priceMessage = priceMessage + "\nQuantity: " + numberOfCoffees;
        priceMessage += "\nPrice: $" + price;
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    public void goToCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}
