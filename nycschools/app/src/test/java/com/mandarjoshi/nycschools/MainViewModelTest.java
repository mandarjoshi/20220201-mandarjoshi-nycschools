package com.mandarjoshi.nycschools;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import android.text.TextUtils;

import com.mandarjoshi.nycschools.model.SchoolScore;
import com.mandarjoshi.nycschools.viewmodel.MainViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

public class MainViewModelTest {

    private final MainViewModel viewModel = new MainViewModel();
    private MockedStatic<TextUtils> mocked;

    @Before
    public void setUp() {
        mocked = mockStatic(TextUtils.class);
    }

    @After
    public void close() {
        mocked.close();
    }

    @Test
    public void testIsReadingBestScore_when_null() {
        assertFalse(viewModel.isReadingBestScore(null));
    }

    @Test
    public void testIsReadingBestScore_when_score_not_digit() {
        SchoolScore schoolScore = new SchoolScore();
        schoolScore.avgScoreReading = "s";
        when(TextUtils.isDigitsOnly(schoolScore.avgScoreReading)).thenReturn(false);
        assertFalse(viewModel.isReadingBestScore(schoolScore));
    }

    @Test
    public void testIsReadingBestScore_score_401() {
        SchoolScore schoolScore = new SchoolScore();
        schoolScore.avgScoreReading = "401";
        when(TextUtils.isDigitsOnly(schoolScore.avgScoreReading)).thenReturn(true);
        assertTrue(viewModel.isReadingBestScore(schoolScore));
    }

    @Test
    public void testIsReadingBestScore_score_399() {
        SchoolScore schoolScore = new SchoolScore();
        schoolScore.avgScoreReading = "399";
        when(TextUtils.isDigitsOnly(schoolScore.avgScoreReading)).thenReturn(true);
        assertFalse(viewModel.isReadingBestScore(schoolScore));

    }

}
