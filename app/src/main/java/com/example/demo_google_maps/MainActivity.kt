package com.example.demo_google_maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Transformations.map
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(mapaGoogle: GoogleMap) {
        map = mapaGoogle

        val lat = 37.422160
        val lng = -122.084270
        val zoomLevel = 15f
        map.moveCamera( CameraUpdateFactory.newLatLngZoom(LatLng(lat, lng), zoomLevel ) )
        map.addMarker(MarkerOptions().position(LatLng(lat, lng)))

        addMarker(map)
    }

    private fun addMarker(mapaGoogle: GoogleMap){
        map = mapaGoogle

        map.setOnMapLongClickListener {
            latLng ->
            val newCoord = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude,
            )
            map.clear()
            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title("Nuevo marker")
                    .snippet(newCoord)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
            )

        }
    }

}