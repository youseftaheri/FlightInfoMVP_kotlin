package com.yousef.mvpflightinfo.data.model

import com.google.gson.annotations.SerializedName

class AirportLatLongPOJO {
    @SerializedName("AirportResource")
    var airportResource: AirportResource? = null

    class AirportResource {
        @SerializedName("Airports")
        var Airports: Airports? = null
    }

    class Airports {
        @SerializedName("Airport")
        var Airport: Airport? = null
    }

    class Airport {
        @SerializedName("Position")
        var Position: Position? = null
    }

    class Position {
        @SerializedName("Coordinate")
        var Coordinate: Coordinate? = null
    }

    class Coordinate {
        @SerializedName("Latitude")
        var Latitude: String? = null

        @SerializedName("Longitude")
        var Longitude: String? = null
    }
}