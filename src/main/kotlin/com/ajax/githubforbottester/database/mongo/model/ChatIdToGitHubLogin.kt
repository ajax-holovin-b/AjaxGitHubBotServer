package com.ajax.githubforbottester.database.mongo.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "chatId_gitHubLogin")
class ChatIdToGitHubLogin(
    @Id val _id: ObjectId = ObjectId.get(),
    val chatId: Long,
    var gitHubLogin: String
)