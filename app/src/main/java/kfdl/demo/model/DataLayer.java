package kfdl.demo.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.inject.Inject;

import kfdl.demo.App;

public class DataLayer {
    @Inject
    Gson gson;

    public DataLayer() {
        App.getComponent().inject(this);
    }

    public Offer getOfferById(Integer id) {
        List<Offer> offers = gson.fromJson(getFileContents("offers.json"), new TypeToken<List<Offer>>(){}.getType());
        for (Offer offer: offers) {
            if (offer.id.equals(id)) {
                return offer;
            }
        }
        return null;
    }

    public Event getEventById(Integer id) {
        List<Event> events = gson.fromJson(getFileContents("events.json"), new TypeToken<List<Event>>(){}.getType());
        for (Event event: events) {
            if (event.id.equals(id)) {
                return event;
            }
        }
        return null;
    }

    public Place getPlaceById(Integer id) {
        List<Place> places = gson.fromJson(getFileContents("places.json"), new TypeToken<List<Place>>(){}.getType());

        for (Place place: places) {
            if (place.id.equals(id)) {
                return place;
            }
        }
        return null;
    }

    private String getFileContents(String assetFile) {
        try {
            InputStream nouns = App.instance().getAssets().open(assetFile);
            BufferedReader in = new BufferedReader(new InputStreamReader(nouns, "UTF-8"));
            StringBuilder builder = new StringBuilder();
            String str;

            while ((str = in.readLine()) != null) {
                builder.append(str).append("\n");
            }
            in.close();
            return builder.toString().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
