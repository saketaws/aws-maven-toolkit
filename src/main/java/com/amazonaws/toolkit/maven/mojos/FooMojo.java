package com.amazonaws.toolkit.maven.mojos;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import com.amazonaws.toolkit.core.hackathon.actions.*;
import com.amazonaws.toolkit.core.hackathon.models.FooInput;
import com.amazonaws.toolkit.maven.core.BaseMojoActionExecutionContextProvider;


@Mojo(name = "foo", defaultPhase = LifecyclePhase.PROCESS_SOURCES )
public class FooMojo
    extends AbstractMojo
{
    @Parameter
    private String projectName;

    public void execute()
        throws MojoExecutionException
    {
        new FooAction(new FooMojoActionExecutionContextProvider()).execute();
    }

    private class FooMojoActionExecutionContextProvider extends BaseMojoActionExecutionContextProvider<FooInput> {

        @Override
        public FooInput createActionInput() {
            return new FooInput(projectName);
        }

    }
}
