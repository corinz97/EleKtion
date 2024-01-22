var publishCmd = `
./gradlew -PstagingRepositoryId=\${process.env.STAGING_REPO_ID} releaseStagingRepositoryOnMavenCentral || exit 3
./gradlew publishJsPackageToNpmjsRegistry || exit 4
./gradlew publishKotlinMultiplatformPublicationToGithubRepository
./gradlew publishJsPublicationToGithubRepository
./gradlew publishJvmPublicationToGithubRepository
./gradlew publishLinuxArm64PublicationToGithubRepository
./gradlew publishLinuxX64PublicationToGithubRepository
`
var config = require('semantic-release-preconfigured-conventional-commits');
config.plugins.push(
    [
        "@semantic-release/exec",
        {
            "publishCmd": publishCmd,
        }
    ],
    "@semantic-release/github",
    "@semantic-release/git",
)
module.exports = config
