package com.ajax.githubforbottester

import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AjaxGitHubBot

fun main(args: Array<String>) {
    val runApplication = runApplication<AjaxGitHubBot>(*args)
    val pullRequestChecker = runApplication.getBean<PullRequestChecker>()
    pullRequestChecker.check()
}
