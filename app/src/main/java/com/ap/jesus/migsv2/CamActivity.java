package com.ap.jesus.migsv2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.SensorManager;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.ArchitectView.ArchitectUrlListener;
import com.wikitude.architect.ArchitectView.CaptureScreenCallback;
import com.wikitude.architect.ArchitectView.SensorAccuracyChangeListener;
import com.wikitude.architect.StartupConfiguration.CameraPosition;

import java.io.File;
import java.io.FileOutputStream;

public class CamActivity extends AbstractArchitectCamActivity {

	/**
	 * last time the calibration toast was shown, this avoids too many toast shown when compass needs calibration
	 */
	private long lastCalibrationToastShownTimeMillis = System.currentTimeMillis();

    protected Bitmap screenCapture = null;

    private static final int WIKITUDE_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 3;

	@Override
	public String getARchitectWorldPath() {
		return "AR/POIs/migs.html";
	}

	@Override
	public String getActivityTitle() {
		return "MIGS";
	}

	@Override
	public int getContentViewId() {
		return R.layout.cam_layout;
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
				if ( accuracy < SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM && CamActivity.this != null && !CamActivity.this.isFinishing() && System.currentTimeMillis() - CamActivity.this.lastCalibrationToastShownTimeMillis > 5 * 1000) {
					Toast.makeText( CamActivity.this, R.string.compass_accuracy_low, Toast.LENGTH_LONG ).show();
					CamActivity.this.lastCalibrationToastShownTimeMillis = System.currentTimeMillis();
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
					final Intent poiDetailIntent = new Intent(CamActivity.this, PoiDetailActivity.class);
					poiDetailIntent.putExtra(PoiDetailActivity.EXTRAS_KEY_POI_ID, String.valueOf(invokedUri.getQueryParameter("id")) );
					poiDetailIntent.putExtra(PoiDetailActivity.EXTRAS_KEY_POI_TITILE, String.valueOf(invokedUri.getQueryParameter("title")) );
					poiDetailIntent.putExtra(PoiDetailActivity.EXTRAS_KEY_POI_DESCR, String.valueOf(invokedUri.getQueryParameter("description")) );
					CamActivity.this.startActivity(poiDetailIntent);
					return true;
				}

				// pressed snapshot button. check if host is button to fetch e.g. 'architectsdk://button?action=captureScreen', you may add more checks if more buttons are used inside AR scene
				else if ("button".equalsIgnoreCase(invokedUri.getHost())) {
					CamActivity.this.architectView.captureScreen(ArchitectView.CaptureScreenCallback.CAPTURE_MODE_CAM_AND_WEBVIEW, new CaptureScreenCallback() {
						
						@Override
						public void onScreenCaptured(final Bitmap screenCapture) {
							if ( ContextCompat.checkSelfPermission(CamActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                                CamActivity.this.screenCapture = screenCapture;
                                ActivityCompat.requestPermissions(CamActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WIKITUDE_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
                            } else {
                                CamActivity.this.saveScreenCaptureToExternalStorage(screenCapture);
                            }
						}
					});
				}
				return true;
			}
		};
	}

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case WIKITUDE_PERMISSIONS_REQUEST_EXTERNAL_STORAGE: {
                if ( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                    this.saveScreenCaptureToExternalStorage(CamActivity.this.screenCapture);
                } else {
                    Toast.makeText(this, "Please allow access to external storage, otherwise the screen capture can not be saved.", Toast.LENGTH_SHORT).show();
                }
            }
        }
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

    protected void saveScreenCaptureToExternalStorage(Bitmap screenCapture) {
        if ( screenCapture != null ) {
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
                CamActivity.this.startActivity(Intent.createChooser(share, chooserTitle));

            } catch (final Exception e) {
                // should not occur when all permissions are set
                CamActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // show toast message in case something went wrong
                        Toast.makeText(CamActivity.this, "Unexpected error, " + e, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }
}
