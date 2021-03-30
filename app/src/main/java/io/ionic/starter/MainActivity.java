package io.ionic.starter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.getcapacitor.BridgeActivity;
import com.getcapacitor.Plugin;
import com.gspl.leegalitysdk.Leegality;

import java.util.ArrayList;

public class MainActivity extends BridgeActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent intent = new Intent(getApplicationContext(), Leegality.class);
//    intent.putExtra("url", "signing-url-here");
    intent.putExtra("url", "https://karzatechnologies.sandbox.leegality.com/sign/ad33ded8-46ea-4833-8907-311a92a66602");
    startActivityForResult(intent, 121);


    // Initializes the Bridge
    this.init(savedInstanceState, new ArrayList<Class<? extends Plugin>>() {{
      // Additional plugins you've installed go here
      // Ex: add(TotallyAwesomePlugin.class);
    }});
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data){
    //Check requestCode, If it match with the provided REQUEST_CODE then this response is for the signing process.
    if(requestCode == 121){
      String error = data.hasExtra("error") ? data.getExtras().getString("error") : null;
      String message = data.hasExtra("message") ? data.getExtras().getString("message") : null;
      if(error != null){
        Toast.makeText(this,"Error: "+ error,Toast.LENGTH_LONG).show();
      }else if(message != null){
        Toast.makeText(this,"Message: "+ message,Toast.LENGTH_LONG).show();
      }
    }
    super.onActivityResult(requestCode, resultCode, data);
  }


}
