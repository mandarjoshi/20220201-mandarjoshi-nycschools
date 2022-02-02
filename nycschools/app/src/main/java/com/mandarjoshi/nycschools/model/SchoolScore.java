package com.mandarjoshi.nycschools.model;

import com.squareup.moshi.Json;

public class SchoolScore {
    @Json(name="dbn") public String databaseNumber;
    @Json(name="school_name") public String schoolName;
    @Json(name="num_of_sat_test_takers") public String numOfTakers;
    @Json(name="sat_critical_reading_avg_score") public String avgScoreReading;
    @Json(name="sat_math_avg_score") public String avgScoreMath;
    @Json(name="sat_writing_avg_score") public String avgScoreWriting;
}
