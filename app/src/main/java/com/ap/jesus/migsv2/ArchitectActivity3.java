package com.ap.jesus.migsv2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.SensorManager;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView.ArchitectUrlListener;
import com.wikitude.architect.ArchitectView.CaptureScreenCallback;
import com.wikitude.architect.ArchitectView.SensorAccuracyChangeListener;
import com.wikitude.architect.StartupConfiguration.CameraPosition;

import java.io.File;
import java.io.FileOutputStream;

public class ArchitectActivity3 extends AbstractArchitectCamActivity {

    /**
     * last time the calibration toast was shown, this avoids too many toast shown when compass needs calibration
     */
    private long lastCalibrationToastShownTimeMillis = System.currentTimeMillis();

    @Override
    public String getARchitectWorldPath() {
        return "AR/migs/index.html";
    }

    @Override
    public String getActivityTitle() {
        return "activityTitle";
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_architect2;
    }

    @Override
    public int getArchitectViewId() {
        return R.id.architectView;
    }

    @Override
    public String getWikitudeSDKLicenseKey() {
        return "nGKzknIn8pLLSg2RS9uR1eMVybYJK82LUEdGeJCiu3+jJu3fy1thRaVlVSUQEx9E7en8cdaQUa9CsvuO/nwKGtpdW/44vO42M0Ax7BzAlwOdHA74ITUPh1Tx+5KPepwIpZ8EW6Wnz6Twfza8y3BXCQNFY2Z+eFKpoPBCk9z1FEpTYWx0ZWRfX3OCY7XT51wOsJMs533j5gAxQyBW1Y9P6rLvsz9LpOYMETv5oEGrtIAg97rd7aV95C8JvwTSFuorQ/a23IJ16D6Br3QPDwosxZEfHeTkw7GlamN+R7PRjUGlinLGlajwfaJ1wplLJSXNMHMSk38OAj0PrCVm6CdB+4xmK9l2pvozp0Wi9agkLPag9S8h9tLUvuekur75KwFbB6RhtKp4YkdeflTExIgX1uNW8afd4C94chfF8V+tqkOVCcJ575ekjTSWr1veQxHL87ZPuyNLybBup0VHvGPEKNcPteAaH4lK2Lt0FdzP7eJm9qU0FBByeTmTiVsojGzOVdjHlShpcGhZH1JYoNUZmGys8ybPYeGb9GQt3rrpZzbl7pZRMIuuZ3V3RKcmNgvfAwUlHsyiGgnEKxYmZbdl3zWl+U+xqns9dde4W8+WtOyD39rgzpq1sRzypR7/TwiI2L5CQX2u5BQwVfH34YnftYVtujJ6kh9NIa4FMck/hefMpY6RwcXLemRMciVoV1MU";
    }

    @Override
    public SensorAccuracyChangeListener getSensorAccuracyListener() {
        return new SensorAccuracyChangeListener() {
            @Override
            public void onCompassAccuracyChanged( int accuracy ) {
				/* UNRELIABLE = 0, LOW = 1, MEDIUM = 2, HIGH = 3 */
                if ( accuracy < SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM && ArchitectActivity3.this != null && !ArchitectActivity3.this.isFinishing() && System.currentTimeMillis() - ArchitectActivity3.this.lastCalibrationToastShownTimeMillis > 5 * 1000) {
                    Toast.makeText( ArchitectActivity3.this, R.string.compass_accuracy_low, Toast.LENGTH_LONG ).show();
                    ArchitectActivity3.this.lastCalibrationToastShownTimeMillis = System.currentTimeMillis();
                }
            }
        };
    }

    @Override
    public ArchitectUrlListener getUrlListener() {
        return new ArchitectUrlListener() {

            @Override
            public boolean urlWasInvoked(String uriString) {
                Uri invokedUri = Uri.parse(uriString);

                // pressed "More" button on POI-detail panel
                if ("markerselected".equalsIgnoreCase(invokedUri.getHost())) {
                    final Intent poiDetailIntent = new Intent(ArchitectActivity3.this, PoiDetailActivity.class);
                    poiDetailIntent.putExtra(PoiDetailActivity.EXTRAS_KEY_POI_ID, String.valueOf(invokedUri.getQueryParameter("id")) );
                    poiDetailIntent.putExtra(PoiDetailActivity.EXTRAS_KEY_POI_TITILE, String.valueOf(invokedUri.getQueryParameter("title")) );
                    poiDetailIntent.putExtra(PoiDetailActivity.EXTRAS_KEY_POI_DESCR, String.valueOf(invokedUri.getQueryParameter("description")) );
                    ArchitectActivity3.this.startActivity(poiDetailIntent);
                    return true;
                }

                // pressed snapshot button. check if host is button to fetch e.g. 'architectsdk://button?action=captureScreen', you may add more checks if more buttons are used inside AR scene
                else if ("button".equalsIgnoreCase(invokedUri.getHost())) {
                    ArchitectActivity3.this.architectView.captureScreen(CaptureScreenCallback.CAPTURE_MODE_CAM_AND_WEBVIEW, new CaptureScreenCallback() {

                        @Override
                        public void onScreenCaptured(final Bitmap screenCapture) {
                            // store screenCapture into external cache directory
                            final File screenCaptureFile = new File(Environment.getExternalStorageDirectory().toString(), "screenCapture_" + System.currentTimeMillis() + ".jpg");

                            // 1. Save bitmap to file & compress to jpeg. You may use PNG too
                            try {
                                final FileOutputStream out = new FileOutputStream(screenCaptureFile);
                                screenCapture.compress(Bitmap.CompressFormat.JPEG, 90, out);
                                out.flush();
                                out.close();

                                // 2. create send intent
                                final Intent share = new Intent(Intent.ACTION_SEND);
                                share.setType("image/jpg");
                                share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(screenCaptureFile));

                                // 3. launch intent-chooser
                                final String chooserTitle = "Share Snaphot";
                                ArchitectActivity3.this.startActivity(Intent.createChooser(share, chooserTitle));

                            } catch (final Exception e) {
                                // should not occur when all permissions are set
                                ArchitectActivity3.this.runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        // show toast message in case something went wrong
                                        Toast.makeText(ArchitectActivity3.this, "Unexpected error, " + e, Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }
                    });
                }
                return true;
            }
        };
    }

    @Override
    public ILocationProvider getLocationProvider(final LocationListener locationListener) {
        return new LocationProvider(this, locationListener);
    }

    @Override
    public float getInitialCullingDistanceMeters() {
        // you need to adjust this in case your POIs are more than 50km away from user here while loading or in JS code (compare 'AR.context.scene.cullingDistance')
        return CULLING_DISTANCE_DEFAULT_METERS;
    }

    @Override
    protected boolean hasGeo() {
        return true;
    }

    @Override
    protected boolean hasIR() {
        return true;
    }

    @Override
    protected CameraPosition getCameraPosition() {
        return CameraPosition.DEFAULT;
    }
}
