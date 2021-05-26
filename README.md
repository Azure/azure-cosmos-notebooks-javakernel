
# Contributing

This project welcomes contributions and suggestions.  Most contributions require you to agree to a
Contributor License Agreement (CLA) declaring that you have the right to, and actually do, grant us
the rights to use your contribution. For details, visit https://cla.opensource.microsoft.com.

When you submit a pull request, a CLA bot will automatically determine whether you need to provide
a CLA and decorate the PR appropriately (e.g., status check, comment). Simply follow the instructions
provided by the bot. You will only need to do this once across all repos using our CLA.

This project has adopted the [Microsoft Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/).
For more information see the [Code of Conduct FAQ](https://opensource.microsoft.com/codeofconduct/faq/) or
contact [opencode@microsoft.com](mailto:opencode@microsoft.com) with any additional questions or comments.

Requirements: Java JDK 9.0 +

package and build:
mvn clean package install

run server:
mvn cargo:run

stop server:
mvn cargo:stop

TODO
- Add MVN_HOME env variable dependency for jackson and azure-cosmos
- Add test coverage using websocket client project
- setup test pipeline for project
- Add #maven-import: <package>,<version> parsing from snippet
- Test interrupt and how it works on sleep
- create java sample similar to c# sample
- Test standard output interleaving between 2 kernel executions
