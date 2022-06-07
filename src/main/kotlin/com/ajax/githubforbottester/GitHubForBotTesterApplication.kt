package com.ajax.githubforbottester

import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GitHubForBotTesterApplication

fun main(args: Array<String>) {
    val runApplication = runApplication<GitHubForBotTesterApplication>(*args)
    val bean = runApplication.getBean<GitHubGetter>()

    bean.getSome()
}
