package com.jetpacker06.b2.util;

import net.dv8tion.jda.api.interactions.commands.OptionType;

public record SlashCommandField(OptionType optionType, String name, String description, boolean required) {
}
