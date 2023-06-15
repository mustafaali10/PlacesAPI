package com.example.placesapi

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class MainActivity : AppCompatActivity() {
    private lateinit var placesClient:PlacesClient
    private lateinit var autocompleteFragment: AutocompleteSupportFragment
    override fun onCreate(savedInstanceState: Bundle?) {

        val apiKey="Your API KEY HERE"

        // Initialize Places SDK
        Places.initialize(applicationContext,apiKey)
        placesClient=Places.createClient(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // Create a new instance of AutocompleteSupportFragment
        autocompleteFragment = AutocompleteSupportFragment()
        supportFragmentManager.beginTransaction().replace(R.id.autocomplete_fragment, autocompleteFragment).commit()

        // Set the type of places to search for
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG))

        // Set up a listener to handle the autocomplete results
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                // Handle the selected place
                Toast.makeText(applicationContext, "Place: " + place.name + ", " + place.id + ", " + place.address + ", " + place.latLng, Toast.LENGTH_LONG).show()
            }

            override fun onError(status: com.google.android.gms.common.api.Status) {
                // Handle the error
                Toast.makeText(applicationContext, "$status", Toast.LENGTH_LONG).show()
            }
        })
    }
}