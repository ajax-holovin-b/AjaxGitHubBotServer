package com.ajax.githubforbottester.telegram.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.telegram.telegrambots.bots.DefaultBotOptions

@Configuration
@EnableScheduling
class TelegramConfig {

    @Bean
    fun defaultBotOptions() = DefaultBotOptions()
}