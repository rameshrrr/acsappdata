package com.rosemellow.alternatecreditscore;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {


    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {


        }
    };


    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    RelativeLayout storagerl, locationrl, smsrl, telephonerl, calenderrl, contactsrl, calllogrl;
    ImageView storageiv, locationiv, smsiv, telephoneiv, calenderiv, contactsiv, calllogiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        final Dialog dialog = new Dialog(BaseActivity.this);
        dialog.setContentView(R.layout.dialog_permissions);
        dialog.setCancelable(false);

        locationiv = dialog.findViewById(R.id.locationiv);
        storageiv = dialog.findViewById(R.id.storageiv);
        smsiv = dialog.findViewById(R.id.smsiv);


        telephoneiv = dialog.findViewById(R.id.telephoneiv);
        calenderiv = dialog.findViewById(R.id.calenderiv);
        contactsiv = dialog.findViewById(R.id.contactsiv);
        calllogiv = dialog.findViewById(R.id.calllogiv);


        storagerl = dialog.findViewById(R.id.storagerl);
        locationrl = dialog.findViewById(R.id.locationrl);
        smsrl = dialog.findViewById(R.id.smsrl);


        telephonerl = dialog.findViewById(R.id.telephonerl);
        calenderrl = dialog.findViewById(R.id.calenderrl);
        contactsrl = dialog.findViewById(R.id.contactsrl);

        calllogrl = dialog.findViewById(R.id.calllogrl);


        if (ContextCompat.checkSelfPermission(BaseActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(BaseActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                    TelephonyManager tMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

                    if (ActivityCompat.checkSelfPermission(BaseActivity.this,
                            android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(BaseActivity.this,
                                    android.Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(BaseActivity.this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    String mPhoneNumber = tMgr.getLine1Number();
                    // Toast.makeText(getApplicationContext(), mPhoneNumber, Toast.LENGTH_LONG).show();
                    Log.e("Efsdcxwesdx", mPhoneNumber);

                    if (mPhoneNumber.equalsIgnoreCase("")) {
                        TelephonyManager tMgr1 = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

                        if (ActivityCompat.checkSelfPermission(BaseActivity.this,
                                android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                                ActivityCompat.checkSelfPermission(BaseActivity.this,
                                        android.Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(BaseActivity.this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                            return;
                        }
                        String mPhoneNumber1 = tMgr1.getLine1Number();
                        Log.e("Efsdcxwesdx", mPhoneNumber);
                        new SavePref(BaseActivity.this).setMyPhoneNumber(mPhoneNumber1);
                    } else {
                        new SavePref(BaseActivity.this).setMyPhoneNumber(mPhoneNumber);
                    }


                    dialog.dismiss();
                }
            }, 1000);

        } else {


            storagerl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    storagepermission();

                }
            });


            locationrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    locationpermission();
                }
            });


            calenderrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    calenderpermission();
                }
            });


            telephonerl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    telephonepermission();
                }
            });


            contactsrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contactspermission();
                }
            });

            calllogrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callpermission();

                }
            });


            smsrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    smspermission();

                }
            });


            Button confirmbtn = dialog.findViewById(R.id.confirmbtn);
            confirmbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkAndRequestPermissions()) {

                    } else {
                        dialog.dismiss();
                    }
                }
            });

            dialog.show();


        }


    }

    private boolean checkAndRequestPermissions() {
        int permissionStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);


        int smsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);

        int accountper = ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS);
        int telephonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        int contactsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        int calenderPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR);

        int numberpermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PRECISE_PHONE_STATE);
        int calllogPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);


        List<String> listPermissionsNeeded = new ArrayList<>();
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (permissionStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (smsPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_SMS);
        }

        if (telephonePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
        }

        if (contactsPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
        }
        if (calenderPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CALENDAR);
        }

        if (calllogPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CALL_LOG);
        }
        if (accountper != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.GET_ACCOUNTS);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        } else {


            TelephonyManager tMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);


            String mPhoneNumber = tMgr.getLine1Number();
            Log.e("Efsdcxwesdx", mPhoneNumber);

            new SavePref(BaseActivity.this).setMyPhoneNumber(mPhoneNumber);


            return true;
        }

    }

    private boolean storagepermission() {
        int permissionStorage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        } else {
            return true;
        }

    }

    private boolean smspermission() {
        int permissionStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_SMS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        } else {
            return true;
        }

    }


    private boolean callpermission() {
        int permissionStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CALL_LOG);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        } else {
            return true;
        }

    }


    private boolean telephonepermission() {
        int permissionStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        } else {
            return true;
        }

    }


    private boolean contactspermission() {
        int permissionStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        } else {
            return true;
        }

    }


    private boolean calenderpermission() {
        int permissionStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CALENDAR);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        } else {
            return true;
        }

    }


    private boolean locationpermission() {

        int locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new
                    String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        } else {
            return true;
        }

    }


    @Override
    protected void onResume() {
        super.onResume();


        if (ContextCompat.checkSelfPermission(BaseActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Glide.with(BaseActivity.this)
                    .load(R.drawable.doneic)
                    .into(storageiv);
        } else {
            Glide.with(BaseActivity.this)
                    .load(R.drawable.undoneic)
                    .into(storageiv);
        }


        if (ContextCompat.checkSelfPermission(BaseActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Glide.with(BaseActivity.this)
                    .load(R.drawable.doneic)
                    .into(locationiv);
        } else {
            Glide.with(BaseActivity.this)
                    .load(R.drawable.undoneic)
                    .into(locationiv);
        }


        if (ContextCompat.checkSelfPermission(BaseActivity.this,
                Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            Glide.with(BaseActivity.this)
                    .load(R.drawable.doneic)
                    .into(smsiv);
        } else {
            Glide.with(BaseActivity.this)
                    .load(R.drawable.undoneic)
                    .into(smsiv);
        }


        if (ContextCompat.checkSelfPermission(BaseActivity.this,
                Manifest.permission.READ_CALL_LOG)
                == PackageManager.PERMISSION_GRANTED) {
            Glide.with(BaseActivity.this)
                    .load(R.drawable.doneic)
                    .into(calllogiv);
        } else {
            Glide.with(BaseActivity.this)
                    .load(R.drawable.undoneic)
                    .into(calllogiv);
        }


        if (ContextCompat.checkSelfPermission(BaseActivity.this,
                Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            Glide.with(BaseActivity.this)
                    .load(R.drawable.doneic)
                    .into(contactsiv);
        } else {
            Glide.with(BaseActivity.this)
                    .load(R.drawable.undoneic)
                    .into(contactsiv);
        }


        if (ContextCompat.checkSelfPermission(BaseActivity.this,
                Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            Glide.with(BaseActivity.this)
                    .load(R.drawable.doneic)
                    .into(telephoneiv);
        } else {
            Glide.with(BaseActivity.this)
                    .load(R.drawable.undoneic)
                    .into(telephoneiv);
        }


        if (ContextCompat.checkSelfPermission(BaseActivity.this,
                Manifest.permission.READ_CALENDAR)
                == PackageManager.PERMISSION_GRANTED) {
            Glide.with(BaseActivity.this)
                    .load(R.drawable.doneic)
                    .into(calenderiv);
        } else {
            Glide.with(BaseActivity.this)
                    .load(R.drawable.undoneic)
                    .into(calenderiv);
        }


        if (ContextCompat.checkSelfPermission(BaseActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(BaseActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(BaseActivity.this,
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 1000);


        }


    }

}

