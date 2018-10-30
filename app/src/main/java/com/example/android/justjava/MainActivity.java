/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=0;
    boolean hasWhippedCream=false;
    boolean hasChocolate=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox WhippedCream=(CheckBox)findViewById(R.id.Whipped_cream_checkbox);
        hasWhippedCream= WhippedCream.isChecked();

        CheckBox Chocolate=(CheckBox)findViewById(R.id.chocolate_checkbox);
        hasChocolate=Chocolate.isChecked();

        EditText namefield=(EditText)findViewById(R.id.name_edittext);
        String name=namefield.getText().toString();

        int price=calculatePrice(hasWhippedCream,hasChocolate);
        createOrderSummary(name,price,hasWhippedCream,hasChocolate);


    }

    /*creates order sumary.
    *
    * @param price of order.
    * @return the order summary
     */
    public void createOrderSummary(String name,int price,boolean hasWhippedCream,boolean hasChocolate)
    {
        CheckBox WhippedCream=(CheckBox)findViewById(R.id.Whipped_cream_checkbox);
        hasWhippedCream=WhippedCream.isChecked();
       String summary="Name : "+name;
       summary+="\nAdd Whipped Cream ? "+ hasWhippedCream;
        summary+="\nAdd Chocolate ? "+ hasChocolate;
       summary+="\nQuantity : "+quantity;
       summary+="\nPrice : $"+ price;
       summary+="\nThank You!";
       String subject="Coffe Order by "+name;
       Intent intent=new Intent(Intent.ACTION_SENDTO);
       intent.setData(Uri.parse("mailto:"));//Specifies that only email apps can handle this
       intent.putExtra(Intent.EXTRA_SUBJECT,subject);//creates subject
       intent.putExtra(Intent.EXTRA_TEXT,summary);//creates emailbody
        if(intent.resolveActivity(getPackageManager())!=null){      //checks whether activity is available to handl this or not
            startActivity(intent);
        }
    }

    public void increment(View view) {

        int duration=Toast.LENGTH_SHORT;
        Context context=getApplicationContext();
        if(quantity==100){
            Toast.makeText(context,"You cannot order more than 100 coffees.",duration).show();
            //display(100);
            return;
        }
        quantity=quantity+1;
        display(quantity);
    }
    public void decrement(View view) {
        int duration=Toast.LENGTH_SHORT;
        if(quantity==1){
           Toast.makeText(this,"You cannot order less than 1 coffee.",duration).show();
            //display(0);
            return;
        }
        quantity=quantity-1;
        display(quantity);
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean hasWhippedCream,boolean hasChocolate) {
        int pricePerCup=5;int price;
        if(hasChocolate){
             pricePerCup=pricePerCup+2;
        }
        if(hasWhippedCream){
             pricePerCup=pricePerCup+1;
        }
             price = quantity * pricePerCup;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
}