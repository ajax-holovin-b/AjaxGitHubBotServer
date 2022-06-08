package com.ajax.githubforbottester

import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AjaxGitHubBotServer

fun main(args: Array<String>) {
    val runApplication = runApplication<AjaxGitHubBotServer>(*args)
    val pullRequestChecker = runApplication.getBean<PullRequestChecker>()
    pullRequestChecker.check()
}
