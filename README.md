To install: `mvn install`
<br />
To run the foo MOJO: `mvn com.amazonaws:aws-maven-toolkit:0.0.1:foo -Dfoo.projectName=myProject`
<br /> 
To upload files to S3 `mvn com.amazonaws:aws-maven-toolkit:0.0.1:uploadToS3 -Ds3-upload.bucketName=<bucket name> -Ds3-upload.keyName=<key_name> -Ds3-upload.source=<absolute file path> -Daws-credentials.profileName=default -Ds3-upload.region=us-east-1`
<br /> 

To create a new IAM Role `mvn com.amazonaws:aws-maven-toolkit:0.0.1:createNewIamRole -DroleName=basic_lambda_execution_role_from_mojo -DpolicyName=lambda_policy -DassumeRolePolicy=src/main/resources/roles/assume_role_policy.json -DrolePolicy=src/main/resources/roles/basic_role_policy.json -Daws-credentials.profileName=default -Ds3-upload.region=us-east-1`
