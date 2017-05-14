package com.viettel.mbccs.screen.common.success;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.viettel.mbccs.variable.Constants;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by eo_cuong on 4/16/17.
 */

public class ScanbarCodeActivity extends AppCompatActivity
        implements ZBarScannerView.ResultHandler {

    private ZBarScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here

        Log.v("ScanbarCodeActivity", rawResult.getContents()); // Prints scan results
        // Log.v("ScanbarCodeActivity", rawResult.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        Intent intent = new Intent();
        intent.putExtra(Constants.BundleConstant.SCAN_SERIAL, rawResult.getContents());
        setResult(RESULT_OK, intent);
        finish();
    }
}
