package denis.easyweather.app.main;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;

import denis.easyweather.app.common.ApiConfig;
import denis.easyweather.app.common.JSONParser;
import denis.easyweather.app.net.ApiFactory;
import denis.easyweather.app.presenter.Presenter;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by denis on 12/23/15.
 */
public class MainPresenter implements Presenter<MainView> {

    public static final String TAG = "MainPresenter";
    private MainView view;
    private HttpURLConnection urlConnection;
    private BufferedReader reader;
    private String forecastJson;
    private JSONParser parser;

    public MainPresenter() {
//        parser = new JSONParser()
    }

    @Override
    public void attachView(MainView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public void runRequest(String city) {
        //new RequestTask().execute(city);
    }

    private static Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

    public void runRequestRx(String city) {
        ApiFactory.API.getWeather(city, ApiConfig.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(scheduler)
                .subscribe(stringResult -> {
                    //String s = stringResult.toString();
                    //Log.d("result", stringResult.response().body().toString());
                    //view.showWeatherRx(s);
                }, throwable -> {
                    Throwable th = throwable;
                });
    }

    //http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7
    private class RequestTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String format = "json";
            String units = "metric";
            int numDays = 7;
            String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
            String QUERY_PARAM = "q";
            String FORMAT_PARAM = "mode";
            String UNITS_PARAM = "units";
            String DAYS_NUM_PARAM = "cnt";
            String API_KEY_PARAM = "APPID";

            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, params[0])
                    .appendQueryParameter(FORMAT_PARAM, format)
                    .appendQueryParameter(UNITS_PARAM, units)
                    .appendQueryParameter(DAYS_NUM_PARAM, String.valueOf(numDays))
                    .appendQueryParameter(API_KEY_PARAM, ApiConfig.API_KEY)
                    .build();

            Log.d(TAG, builtUri.toString());

            try {
                URL url = new URL(builtUri.toString());
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if(inputStream == null) {
                    forecastJson = null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    forecastJson = null;
                }
                forecastJson = buffer.toString();
            } catch (MalformedURLException e) {
                Log.d(TAG, e.getMessage());
                forecastJson = null;
            } catch (IOException ioe) {
                Log.d(TAG, ioe.getMessage());
                forecastJson = null;
            } finally {
                if(urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ioe) {
                        Log.d(TAG, "error closing stream, ", ioe);
                    }
                }
            }

            return forecastJson;
        }

        @Override
        protected void onPostExecute(String result) {
            String[] weatherData;
            parser = new JSONParser(result);
            try {
                weatherData = parser.parseJsonForDay(7);
                view.showTodayWeather(weatherData);
            } catch (JSONException e) {
                Log.d(TAG, e.getMessage(), e);
            }
            Log.d(TAG, result);
        }
    }
}
