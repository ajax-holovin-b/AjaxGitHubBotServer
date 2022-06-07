package com.ajax.githubforbottester

import com.spotify.github.v3.clients.GitHubClient
import com.spotify.github.v3.clients.GithubAppClient
import com.spotify.github.v3.search.requests.ImmutableSearchParameters
import com.spotify.github.v3.search.requests.SearchParameters
import org.kohsuke.github.GitHubBuilder
import org.kohsuke.github.extras.authorization.JWTTokenProvider
import org.springframework.stereotype.Component
import java.io.File
import java.net.URI

@Component
class GitHubGetter {

    fun getSome() {

        val jwtToken = JWTTokenProvider(
            "208407",
            File("/Users/bohdanholovin/Desktop/ajax/GitHubForBotTester/src/main/resources/new-key2.pem")
        ).encodedAuthorization

        println(jwtToken)

        println("")

        val github = GitHubBuilder()
            .withJwtToken(jwtToken)
            .build()

        // github.

        val accessTokenUrl = github.app

        //
        // val app = github.app.getInstallationById(208407)
        //
        // // val nameToSend = "naumenko.y@ajax.systems"
        //
        // // val list = github.getOrganization("ajax-systems")
        // //     .repositoriesWithOpenPullRequests
        // //     .map { repository ->
        // //         repository.getPullRequests(GHIssueState.OPEN)
        // //             .map { pullRequest ->
        // //                 pullRequest.requestedReviewers
        // //                     .map { Result(repository.name, pullRequest.htmlUrl.toString(), it.login) }
        // //             }
        // //     }

        println()
    }
}
//
// @Component
// class GitHubGetterSpotify {
//
//     fun getSome() {
//
//         val gitHubClient = GitHubClient.create(
//             URI.create("https://api.github.com/"),
//             File("/Users/bohdanholovin/Desktop/ajax/GitHubForBotTester/src/main/resources/testorbotcom.2022-06-07.private-key.pem"),
//             208407)
//
//         val githubAppClient = GithubAppClient.createGithubAppClient()
//
//         val present = gitHubClient.accessToken.isPresent
//         val repositories = gitHubClient.createSearchClient().repositories(ImmutableSearchParameters.builder().q("csa").build())
//         println()
//     }
// }

class Result(
    val repos: String,
    val pullRequest: String,
    val email: String
)