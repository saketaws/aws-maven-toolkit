package com.amazonaws.toolkit.maven.mojos;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.amazonaws.toolkit.core.hackathon.ActionContext;
import com.amazonaws.toolkit.core.hackathon.actions.*;
import com.amazonaws.toolkit.core.hackathon.analytics.NoOpEventPublisher;
import com.amazonaws.toolkit.core.hackathon.models.FooInput;
import com.amazonaws.toolkit.maven.core.BaseMojoActionExecutionContextProvider;
import com.amazonaws.toolkit.maven.core.NullToolkitProgressor;
import com.amazonaws.toolkit.maven.core.SimpleLogger;


@Mojo(name = "foo", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST)
public class FooMojo
    extends AbstractMojo
{
    @Parameter(property = "foo.projectName", defaultValue = "Default Project.")
    private String projectName;

    public void execute()
        throws MojoExecutionException
    {
        ActionContext context = new ActionContext(new NoOpEventPublisher().createEvent("Foo"),
                new SimpleLogger(), new NullToolkitProgressor());
        FooInput input = new FooInput(projectName);
        new FooAction().execute(input, context);
    }
}
