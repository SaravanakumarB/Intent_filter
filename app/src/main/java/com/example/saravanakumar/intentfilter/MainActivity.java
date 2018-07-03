package com.example.saravanakumar.intentfilter;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnImage,btnContact,
            btnSendto,btnGet,btnMap,btnCall,btnMsg,btnBrwoser;
    static final int REQUEST_IMAGE_CAPTURE = 1,PICK_CONTACT=1,PICK_IMAGE_REQUEST=1;
    private String message="Hello My UI";
    private String URL="google.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnImage = (Button) findViewById(R.id.imageCapture);
        btnContact = (Button) findViewById(R.id.pickContact);
        btnSendto = (Button) findViewById(R.id.mailSendTO);
        btnGet = (Button) findViewById(R.id.getContent);
        btnMap = (Button) findViewById(R.id.loadMap);
        btnCall = (Button) findViewById(R.id.calltoContact);
        btnMsg = (Button) findViewById(R.id.sendMsg);
        btnBrwoser = (Button) findViewById(R.id.openBrowser);
        btnImage.setOnClickListener(this);
        btnContact.setOnClickListener(this);
        btnSendto.setOnClickListener(this);
        btnGet.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnMsg.setOnClickListener(this);
        btnBrwoser.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.imageCapture:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
                break;
            case R.id.pickContact:
                Intent pickintent = new Intent(Intent.ACTION_PICK_ACTIVITY);
                startActivityForResult(pickintent,PICK_CONTACT);
                break;
            case R.id.mailSendTO:
                //you file location
                String filelocation="/Users/xxx/Desktop";
                Intent mail1 = new Intent(Intent.ACTION_SENDTO);
                mail1.setType("text/plain");
                mail1.putExtra(Intent.EXTRA_SUBJECT, "");
                mail1.putExtra(Intent.EXTRA_STREAM, Uri.parse( "file://"+filelocation));
                mail1.putExtra(Intent.EXTRA_TEXT, message);
                mail1.setData(Uri.parse("mailto:"));
                startActivity(mail1);
                break;
            case R.id.getContent:
                Intent content = new Intent();
                content.setType("image/*");
                content.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(content, "Select Picture"), PICK_IMAGE_REQUEST);
                break;
            case R.id.loadMap:
                Intent map = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
                startActivity(map);
                break;
            case R.id.calltoContact:
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:0123456789"));
                startActivity(call);
                break;
            case R.id.openBrowser:
                Intent browse = new Intent(Intent.ACTION_WEB_SEARCH);
                browse.putExtra(SearchManager.QUERY,URL);
                    startActivity(browse);
                break;
            case R.id.sendMsg:
                String number = "9876543210";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
                break;
        }
    }
}
