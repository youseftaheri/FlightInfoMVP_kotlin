package com.yousef.mvpflightinfo.data.model

import com.google.gson.annotations.SerializedName

class AirportsPOJO {
    @SerializedName("AirportResource")
    var airportResource: AirportResource? = null

    class AirportResource {
        @SerializedName("Airports")
        var Airports: Airports? = null
    }

    class Airports {
        @SerializedName("Airport")
        var Airport: List<Airport>? = null
    }

    class Airport {
        @SerializedName("AirportCode")
        var AirportCode: String? = null

        @SerializedName("CityCode")
        var CityCode: String? = null

        @SerializedName("CountryCode")
        var CountryCode: String? = null

        @SerializedName("Position")
        var Position: Position? = null

        @SerializedName("Names")
        var Names: Name? = null
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

    class Name {
        @SerializedName("Name")
        var Name: AirportName? = null
    }

    class AirportName {
        @SerializedName("$")
        var fullName: String? = null
    }
}