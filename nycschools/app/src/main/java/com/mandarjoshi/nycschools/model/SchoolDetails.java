package com.mandarjoshi.nycschools.model;

import com.squareup.moshi.Json;

public class SchoolDetails {
    @Json(name="dbn") public String databaseNumber;
    @Json(name="school_name") public String schoolName;
    @Json(name="neighborhood") public String neighbourHood;

}