package com.amazonaws.toolkit.maven.core;

import java.io.IOException;

import com.amazonaws.toolkit.core.hackathon.ToolkitLogger;

/**
 * Logs info and warnings to Standard Output and errors Standard Error.
 *
 */
public class SimpleLogger implements ToolkitLogger {

    public void close() throws IOException {

    }

    private static String constructLogMessage(String format, Object...params) {
        return String.format(format, params);
    }

    public void info(String format, Object... params) {
        System.out.println(constructLogMessage(format, params));
    }

    public void warning(String format, Object... params) {
        System.out.println(constructLogMessage(format, params));
    }

    public void error(String format, Object... params) {
        System.err.println(constructLogMessage(format, params));
    }
}
