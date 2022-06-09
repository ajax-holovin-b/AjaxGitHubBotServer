package com.ajax.githubforbottester.telegram.model

data class PullRequestForReviewData(
    val login: String,
    val repos: String,
    val pullRequestUrl: String
)
