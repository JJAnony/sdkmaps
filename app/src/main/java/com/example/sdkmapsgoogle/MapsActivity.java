package com.example.sdkmapsgoogle;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.sdkmapsgoogle.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng ibirapuera = new LatLng(-23.587097, -46.657635);
        mMap.addMarker(new MarkerOptions().position(ibirapuera).title("Marker in Ibirapuera").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        CircleOptions options = new CircleOptions();
        options.center(ibirapuera);
        options.radius(150);
        options.strokeWidth(0);
        options.fillColor(Color.argb(126,255, 255, 0));
        mMap.addCircle(options);

        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(new LatLng(-23.583311, -46.658743));
        polygonOptions.add(new LatLng(-23.584173, -46.659891));
        polygonOptions.add(new LatLng(-23.584503, -46.659607));
        polygonOptions.add(new LatLng(-23.583635, -46.658455));
        polygonOptions.strokeWidth(0);
        polygonOptions.fillColor(Color.argb(126,255, 255, 255));
        mMap.addPolygon(polygonOptions);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Car").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));
            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.add(new LatLng(-23.588945, -46.660781));
                polylineOptions.add(latLng);
                polylineOptions.color(Color.BLACK);
                polylineOptions.width(10);
                mMap.addPolyline(polylineOptions);
            }
        });

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ibirapuera, 15));
    }
}