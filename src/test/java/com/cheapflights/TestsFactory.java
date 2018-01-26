package com.cheapflights;

import com.cheapflights.ui.entities.TravelInfo;
import com.cheapflights.ui.tests.CheapFlightsTest;
import com.cheapflights.ui.utils.FileSearchUtil;
import com.cheapflights.ui.utils.JsonUtil;
import com.google.gson.JsonObject;
import org.testng.annotations.Factory;

public class TestsFactory {
    private static String folderPath = "./src/main/resources/travel-info-files";
    private static String extension= ".json";
    private static final int COUNT = FileSearchUtil.getDirectoryFiles(folderPath, extension).size();




}
