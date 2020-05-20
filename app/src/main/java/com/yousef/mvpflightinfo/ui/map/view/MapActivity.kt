package com.yousef.mvpflightinfo.ui.map.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.yousef.mvpflightinfo.R
import com.yousef.mvpflightinfo.ui.base.view.BaseActivity
import com.yousef.mvpflightinfo.ui.map.interactor.MapMVPInteractor
import com.yousef.mvpflightinfo.ui.map.presenter.MapMVPPresenter
import com.yousef.mvpflightinfo.ui.schedules.view.SchedulesActivity
import com.yousef.mvpflightinfo.utils.Utils
import java.util.*
import javax.inject.Inject

class MapActivity : BaseActivity(), MapMVPView, OnMapReadyCallback {

    @kotlin.jvm.JvmField
    @BindView(R.id.tvOrigin)
    var tvOrigin: TextView? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.tvDestination)
    var tvDestination: TextView? = null
    private var mMap: GoogleMap? = null
    var circleDrawable: Drawable? = null
    var markerIcon: BitmapDescriptor? = null
    var dblOrgLat: Double? = null
    var dblOrgLong: Double? = null
    var dblDstLat: Double? = null
    var dblDstLong: Double? = null

    @Inject
    lateinit var presenter: MapMVPPresenter<MapMVPView, MapMVPInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        setUnBinder(ButterKnife.bind(this))
        presenter.onAttach(this)
        iniUI()
    }

    override fun onDestroy() {
        presenter.onDetach()
        unBinde()
        super.onDestroy()
    }

    fun iniUI() {
        tvOrigin!!.text = presenter!!.getOrigin()
        tvDestination!!.text = presenter!!.getDestination()
        setUp()
    }

    fun setUp() {
        presenter!!.loadData()
    }

    override fun showData() {
        showMarkers()
    }

    fun showMarkers() {
        //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = (supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?)!!
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        circleDrawable = ContextCompat.getDrawable(this, R.drawable.map_marker)
        markerIcon = getMarkerIconFromDrawable(circleDrawable)
        mMap!!.uiSettings.isZoomControlsEnabled = true
        dblOrgLat = presenter!!.getOrgLat()!!.toDouble()
        dblOrgLong = presenter!!.getOrgLng()!!.toDouble()
        dblDstLat = presenter!!.getDstLat()!!.toDouble()
        dblDstLong = presenter!!.getDstLng()!!.toDouble()
        // Add a marker move the camera
        val origin = LatLng(dblOrgLat!!, dblOrgLong!!)
        val destination = LatLng(dblDstLat!!, dblDstLong!!)
        mMap!!.addMarker(MarkerOptions().position(origin).icon(markerIcon)
                .draggable(false).visible(true).title("Origin: " + presenter!!.getOrigin()))
        mMap!!.addMarker(MarkerOptions().position(destination).icon(markerIcon)
                .draggable(false).visible(true).title("Destination: " + presenter!!.getDestination())).showInfoWindow()
        val builder = LatLngBounds.Builder()
        //the include method will calculate the min and max bound.
        builder.include(origin)
        builder.include(destination)
        val bounds = builder.build()
        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
//        val padding = (width * 0.2).toInt()
        val padding = ((if(height < width) width else height) * 0.25).toInt()
        mMap!!.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding))

        //Polyline options
        val options = PolylineOptions()
        val pattern = Arrays.asList(Dash(30F), Gap(10F))
        mMap!!.addPolyline(options.width(10f).color(R.color.colorPrimary).geodesic(false).pattern(pattern).add(origin).add(destination))
    }

    @OnClick(R.id.btBack)
    fun btBackClick() {
        startActivity(Intent(applicationContext, SchedulesActivity::class.java))
        Utils.gotoNextActivityAnimation(this@MapActivity)
    }

    private fun getMarkerIconFromDrawable(drawable: Drawable?): BitmapDescriptor {
        val height = 150
        val width = 100
        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        canvas.setBitmap(bitmap)
        drawable!!.setBounds(0, 0, width, height)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}