package com.cheapflights;

import org.kohsuke.args4j.Option;

public class Settings {
    @Option(name = "--first", usage = "Set which test to run", required = true)
    public String firstTest;
}
