package com.example.cavoid.database;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Response;
import com.example.cavoid.api.Repository;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

//TODO Change this to a model with observable data fields!
public class ExposureCheck extends AndroidViewModel {
    private ArrayList<String> allFipsFromLastTwoWeeks;
    private volatile int counter;
    private volatile ArrayList<String> fipsToNotify;
    private volatile MutableLiveData<Boolean> isDone;


    public ExposureCheck(@NonNull Application application) {
        super(application);

        this.fipsToNotify = new ArrayList<String>();
        allFipsFromLastTwoWeeks = getPastFips(getApplication().getApplicationContext());
        this.isDone = new MutableLiveData<>();
        this.isDone.setValue(false);
        fipsToNotify(getApplication().getApplicationContext(), allFipsFromLastTwoWeeks);
    }

    public MutableLiveData<Boolean> getIsDone(){
        return isDone;
    }


    private ArrayList<String> getPastFips(Context context) {
        LocationDatabase locDb = LocationDatabase.getDatabase(context.getApplicationContext());
        LocationDao dao = locDb.getLocationDao();
        LocalDate startDate = DateTime.now().toLocalDate();
        LocalDate[] dateList = new LocalDate[15];
        int arrayIndex = 0;
        for (int i = 1; i <= 14; i++) {
            LocalDate prevDay = startDate.minusDays(i);
            dateList[arrayIndex] = prevDay;
            arrayIndex++;
        }
        List<PastLocation> pastLocations = dao.loadAllByDates(dateList);
        ArrayList<String> pastFips = new ArrayList<String>();
        for (PastLocation location : pastLocations) {
            pastFips.add(location.fips);
        }
        return pastFips;
    }

    public void fipsToNotify(Context context, ArrayList<String> pastLocations) {
        for (String location : pastLocations) {
            Repository.getPosTests(context, location, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String percentChange;
                    try {
                        percentChange = response.getString("percent_change_14_days");
                    } catch (JSONException e) {
                        Log.e("fipsToNotify", "Expected percent_change_14_days to be a string" + e.getMessage());
                        percentChange = null;
                    }
                    try {
                        if (Integer.parseInt(percentChange) > 0) {
                            fipsToNotify.add(location);
                            synchronized (ExposureCheck.class){
                                counter = counter + 1;
                                isDone.setValue(counter == pastLocations.size());
                            }

                        }
                    }
                    catch (NumberFormatException exception) {
                        Log.i("fipsToNotify", "Expected percent_change_14_days to have a integer value");
                    }
                }
            });

        }
    }

}
