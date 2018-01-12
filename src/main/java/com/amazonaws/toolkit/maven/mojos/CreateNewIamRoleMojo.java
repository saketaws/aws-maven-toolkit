package com.amazonaws.toolkit.maven.mojos;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.amazonaws.toolkit.core.hackathon.ActionContext;
import com.amazonaws.toolkit.core.hackathon.actions.CreateNewIamRoleAction;
import com.amazonaws.toolkit.core.hackathon.analytics.NoOpEventPublisher;
import com.amazonaws.toolkit.core.hackathon.models.AwsScope;
import com.amazonaws.toolkit.core.hackathon.models.CreateNewIamRoleInput;
import com.amazonaws.toolkit.maven.core.NullToolkitProgressor;
import com.amazonaws.toolkit.maven.core.SimpleLogger;

@Mojo(name = "createNewIamRole")
public class CreateNewIamRoleMojo extends AbstractMojo {
    @Parameter(property = "aws-credentials.profileName", required = true)
    private String profileName;

    @Parameter(property = "s3-upload.region", required = true)
    private String region;

    @Parameter(property = "roleName")
    private String roleName;

    @Parameter(property = "policyName")
    private String policyName;

    @Parameter(property = "assumeRolePolicy")
    private File assumeRolePolicyFile;

    @Parameter(property = "rolePolicy")
    private File rolePolicyFile;

    public void execute() throws MojoExecutionException, MojoFailureException {
        if (!rolePolicyFile.exists()) {
            throw new MojoExecutionException("Role Policy file is not found");
        }

        if (!assumeRolePolicyFile.exists()) {
            throw new MojoExecutionException("Assume Role Policy file is not found");
        }
        String rolePolicy;
        String assumeRolePolicy;
        try {
            rolePolicy = FileUtils.readFileToString(rolePolicyFile);
            assumeRolePolicy = FileUtils.readFileToString(assumeRolePolicyFile);
        } catch (IOException e) {
            throw new MojoExecutionException("Couldn't read files", e);
        }

        ActionContext context = new ActionContext(new NoOpEventPublisher().createEvent("CreatewNewIamRole"),
                new SimpleLogger(), new NullToolkitProgressor());

        AwsScope awsScope = new AwsScope(profileName, region);

        CreateNewIamRoleInput input = new CreateNewIamRoleInput(roleName, policyName, assumeRolePolicy,
                rolePolicy, awsScope);
        new CreateNewIamRoleAction().execute(input, context);
    }

}
