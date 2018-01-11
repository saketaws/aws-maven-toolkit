package com.amazonaws.toolkit.maven.mojos;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.amazonaws.toolkit.core.hackathon.ActionContext;
import com.amazonaws.toolkit.core.hackathon.actions.UploadToS3Action;
import com.amazonaws.toolkit.core.hackathon.analytics.NoOpEventPublisher;
import com.amazonaws.toolkit.core.hackathon.models.AwsScope;
import com.amazonaws.toolkit.core.hackathon.models.UploadToS3Input;
import com.amazonaws.toolkit.core.hackathon.models.UploadToS3Input.EncryptionType;
import com.amazonaws.toolkit.maven.core.NullToolkitProgressor;
import com.amazonaws.toolkit.maven.core.SimpleLogger;

@Mojo(name = "uploadToS3", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST)
public class UploadBuildArtifactsToS3Mojo extends AbstractMojo {
    // TODO: Encryption

    @Parameter(property = "aws-credentials.profileName", required = true)
    private String profileName;

    @Parameter(property = "s3-upload.region", required = true)
    private String region;

    @Parameter(property = "s3-upload.bucketName", required = true)
    private String bucketName;

    @Parameter(property = "s3-upload.source", required = true)
    private File source;

    @Parameter(property = "s3-upload.keyName", required = true)
    private String keyName;

    // Not currently used.
    @Parameter(property = "s3-upload.createBucketIfDoesntExist", defaultValue = "false")
    private boolean createBucketIfDoesntExist;

    public void execute() throws MojoExecutionException, MojoFailureException {
        ActionContext context = new ActionContext(new NoOpEventPublisher().createEvent("Foo"),
                new SimpleLogger(), new NullToolkitProgressor());
        AwsScope scope = new AwsScope(profileName, region);
        UploadToS3Input input = new UploadToS3Input(scope, source.getAbsolutePath(), bucketName, keyName, EncryptionType.NONE, null);
        new UploadToS3Action().execute(input, context);
    }
}
