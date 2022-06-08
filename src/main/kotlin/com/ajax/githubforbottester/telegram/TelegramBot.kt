package com.ajax.githubforbottester.telegram

import com.ajax.githubforbottester.github.PullRequestService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class TelegramBot(
    private val pullRequestService: PullRequestService,
    private val defaultBotOptions: DefaultBotOptions
) : TelegramLongPollingBot(defaultBotOptions) {

    override fun getBotToken(): String {
        return "5492484838:AAF_nvAXAI0TfcT4AOd33MDsjaDX79u92bc"
    }

    override fun getBotUsername(): String {
        return "@AjaxGitHubBot"
    }

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage()) handleMessage(update.message)
    }

    private fun handleMessage(message: Message) {
        if (message.hasEntities() && message.entities.any { "bot_command" == it.type }) {

            val messageWords = message.text.split(" ")

            when (messageWords[0]) {
                "/set_email" -> {
                    val email = messageWords[1]

                    pullRequestService.setEmail(message.chatId, email)

                    val sendMessage = SendMessage.builder()
                        .chatId(message.chatId.toString())
                        .text("Email set to $email")
                        .build()

                    execute(sendMessage)
                }
                "/get_current_email" -> {
                    val emailCurrent = pullRequestService.getEmail(message.chatId)
                    val textToSend = when (emailCurrent != null) {
                        true -> emailCurrent
                        false -> "You don`t have any email in bot"
                    }

                    val sendMessage = SendMessage.builder()
                        .chatId(message.chatId.toString())
                        .text(textToSend)
                        .build()

                    execute(sendMessage)
                }
                "/get_review_requests" -> {
                    val emailCurrent = pullRequestService.getEmail(message.chatId)
                    val textToSend = when (emailCurrent != null) {
                        true -> pullRequestService.checkReviewRequests().toString()
                        false -> "You don`t register email in bot"
                    }

                    val sendMessage = SendMessage.builder()
                        .chatId(message.chatId.toString())
                        .text(textToSend)
                        .build()

                    execute(sendMessage)
                }
            }
        }
    }
}