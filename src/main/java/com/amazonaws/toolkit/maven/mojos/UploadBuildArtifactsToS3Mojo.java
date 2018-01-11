package com.amazonaws.toolkit.maven.mojos;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.amazonaws.toolkit.core.hackathon.ActionContext;
import com.amazonaws.toolkit.core.hackathon.actions.UploadArtifactsToS3Action;
import com.amazonaws.toolkit.core.hackathon.analytics.NoOpEventPublisher;
import com.amazonaws.toolkit.core.hackathon.models.UploadArtifactsToS3Input;
import com.amazonaws.toolkit.maven.core.NullToolkitProgressor;
import com.amazonaws.toolkit.maven.core.SimpleLogger;

@Mojo(name = "s3UploadArtifacts", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST)
public class UploadBuildArtifactsToS3Mojo extends AbstractMojo {

    @Parameter(property = "s3-upload.bucketName", required = true)
    private String bucketName;

    @Parameter(property = "s3-upload.source", required = true)
    private File source;

    @Parameter(property = "s3-upload.keyName", required = true)
    private String keyName;

    @Parameter(property = "s3-upload.createBucketIfDoesntExist", defaultValue = "false")
    private boolean createBucketIfDoesntExist;

    public void execute() throws MojoExecutionException, MojoFailureException {
        ActionContext context = new ActionContext(new NoOpEventPublisher().createEvent("Foo"),
                new SimpleLogger(), new NullToolkitProgressor());
        UploadArtifactsToS3Input input = new UploadArtifactsToS3Input(bucketName, source, keyName, createBucketIfDoesntExist);
        new UploadArtifactsToS3Action().execute(input, context);
    }

}
