package com.ajax.githubforbottester

import org.kohsuke.github.GHIssueState
import org.kohsuke.github.GitHubBuilder
import org.kohsuke.github.extras.authorization.JWTTokenProvider
import org.springframework.stereotype.Component
import java.io.File

@Component
class PullRequestChecker {

    fun check() {

        val jwtToken = JWTTokenProvider(
            "208864",
            File("src/main/resources/fix-org-bot-tester.2022-06-08.private-key.pem")
        ).encodedAuthorization.split(" ")[1]

        val gitHubApp = GitHubBuilder()
            .withJwtToken(jwtToken)
            .build()

        val appId = gitHubApp.app.listInstallations().toList()[0].id
        val token = gitHubApp.app.getInstallationById(appId).createToken().create()

        val gitHub = GitHubBuilder()
            .withAppInstallationToken(token.token)
            .build()

        val listForReview = gitHub.getOrganization("test-org-for-bo")
            .repositoriesWithOpenPullRequests
            .map { repository ->
                repository.getPullRequests(GHIssueState.OPEN)
                    .map { pullRequest ->
                        pullRequest.requestedReviewers
                            .map { Result(it.login, pullRequest.repository.name, pullRequest.htmlUrl.toString()) }
                    }
            }
    }
}

