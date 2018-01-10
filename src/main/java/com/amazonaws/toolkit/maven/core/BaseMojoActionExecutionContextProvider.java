package com.amazonaws.toolkit.maven.core;

import com.amazonaws.toolkit.core.hackathon.IActionExecutionContextProvider;
import com.amazonaws.toolkit.core.hackathon.ToolkitLogger;
import com.amazonaws.toolkit.core.hackathon.ToolkitProgresser;
import com.amazonaws.toolkit.core.hackathon.models.ActionInput;

public abstract class BaseMojoActionExecutionContextProvider<T extends ActionInput> implements IActionExecutionContextProvider<T>{

    public ToolkitLogger createLogger() {
        return new SimpleLogger();
    }

    public ToolkitProgresser createProgresser() {
        return new NullToolkitProgressor();
    }

    // Implemented by each specific Mojo Action.
    public abstract T createActionInput();

}
